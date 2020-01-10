package com.zqx.seckillProduct.service.impl;

import com.zqx.commom.entity.SeckilUserResult;
import com.zqx.commom.entity.SeckillProductInfo;
import com.zqx.seckillProduct.aop.LockAnnotation;
import com.zqx.seckillProduct.mapper.SeckillProductMapper;
import com.zqx.seckillProduct.service.SeckilUserResultService;
import com.zqx.seckillProduct.service.SeckillByService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//秒杀的事务逻辑处理service，使用多种方法实现秒杀
//1，使用事务加锁
//2，使用多线程
//3.使用悲观锁和乐观锁
@Service
public class SeckillByServiceImpl implements SeckillByService {

    @Autowired
    private SeckillProductMapper seckillProductMapper;

    @Autowired
    private SeckilUserResultService seckilUserResultService;

    private static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    private final static String inventory = "inventory:";
    private final static String seckillNum = "seckillNum:";

    //方法1
    //实现一个锁方法,根据id和用户id锁住用户对指定记录的操作
    @LockAnnotation //给该方法加上锁
    @Transactional //开启事务
    public Map<String, String> procedureLockBy(int userId, int id) {
        Map<String, String> map = new HashMap<>();
        try {
            //上锁
//            lock.lock();

            //此处info和productInfo两个查询会引起事务的脏读情况，当前一个人的事务还未提交时，后一个就查询了info，那么这个人拿到的数据就是脏数据。
            //解决1：提升隔离级别
            //解决2：将锁上移，一个方法体就是事务，这里锁是在事务之内的，应该将锁抽离出在事务的外面加锁，而且锁机制本来就应该独立出来使用aop进行解耦复用。
            SeckillProductInfo info = seckillProductMapper.findSeckillInfoById(id);
            Integer inventory = info.getSeckillInventory();//秒杀库存
            Integer seckillNum = info.getSeckillNum();//秒杀的数量
            seckillNum++;
            if (seckillNum > inventory) {
                map.put("result", "fail");
                map.put("msg", "商品数量已空，谢谢惠顾!");
                return map;
            }
            SeckillProductInfo productInfo = new SeckillProductInfo();
            productInfo.setId(id);
            productInfo.setSeckillNum(seckillNum);
            seckillProductMapper.updateSeckillInfoBySeckNum(productInfo);
            map.put("result", "success");
            map.put("msg", "您已成功抢购!");
            return map;
        } finally {
//            lock.unlock();
        }
    }


    /**
     * 方法2
     * 多线程方式结合线程安全map实现快速秒杀服务。
     *
     * @param userId
     * @param id
     */
    @LockAnnotation
    public void mulitThread(int userId, int id) {
        Integer invenNum = concurrentHashMap.get(inventory + id);
        if (invenNum == null) {
            SeckillProductInfo productInfo = seckillProductMapper.findSeckillInfoById(id);
            concurrentHashMap.put(inventory + id, productInfo.getSeckillInventory());
            concurrentHashMap.put(seckillNum + id, productInfo.getSeckillNum());
            invenNum = productInfo.getSeckillInventory();
        }
        Integer secNum = concurrentHashMap.get(seckillNum + id);//已秒杀的数量
        secNum++;
        concurrentHashMap.put(seckillNum + id, secNum);
        //保存秒杀记录和更新秒杀商品交给子线程处理，减少秒杀方法执行时间
        new Thread(new SeckillThread(secNum, invenNum, userId, id)).start();
    }


    /**
     * 方法3-悲观锁
     * 悲观锁实现语句selec ... for update
     * 悲观锁是mysql中的锁
     * 他能使在事务未提交时，对应用悲观锁的数据，其他数据不能读和写。只有等该事务结束后其他事务才能对同一个数据进行操作。
     * 使用条件是：必须是innoDB引擎和在事务中才生效。
     */
    @Transactional
    public Map<String, String> pessimismLock(int userId, int id) {
        Map<String, String> map = new HashMap<>();
        SeckillProductInfo productInfo = seckillProductMapper.selectForUpdate(id);
        Integer inventory = productInfo.getSeckillInventory();
        Integer seckillNum = productInfo.getSeckillNum();
        if (seckillNum >= inventory) {
            map.put("result", "fail");
            map.put("msg", "商品数量已空，谢谢惠顾!");
            return map;
        }
        seckillNum++;
        SeckillProductInfo info = new SeckillProductInfo();
        productInfo.setId(id);
        productInfo.setSeckillNum(seckillNum);
        //更新秒杀商品的秒杀数量
        seckillProductMapper.updateSeckillInfoBySeckNum(info);
        map.put("result", "success");
        map.put("msg", "您已成功抢购!");
        return map;
    }


    /**
     * 方法3-乐观锁
     * 不采用强制加锁的形式，而是使用一个版本字段，然后通过递增版本来实现并发处理，
     * 当更新时发现version和自己取出来时不一致时，说明在自己执行期间已经有人完成更新，并改变了version，那么就回退自己的操作。
     */
    public Map<String, String> optimisticLock(int userId, int id) {
        Map<String, String> map = new HashMap<>();
        SeckillProductInfo productInfo = seckillProductMapper.findSeckillInfoById(id);
        Integer inventory = productInfo.getSeckillInventory();
        Integer seckillNum = productInfo.getSeckillNum();
        if (seckillNum >= inventory) {
            map.put("result", "fail");
            map.put("msg", "商品数量已空，谢谢惠顾!");
            return map;
        }
        seckillNum++;
        SeckillProductInfo info = new SeckillProductInfo();
        productInfo.setId(id);
        productInfo.setSeckillNum(seckillNum);
        productInfo.setSeckillVersion(productInfo.getSeckillVersion());
        //更新使用了乐观锁，只有版本前后一致才能更新。
        int result = seckillProductMapper.updateSeckillInfoByVersion(info);
        if (result > 0){
            map.put("result", "success");
            map.put("msg", "您已成功抢购!");
        }else {
            map.put("result", "fail");
            map.put("msg", "抢购失败，请重新抢购！");
        }
        return map;
    }


    class SeckillThread implements Runnable {

        private int seckillNum;
        private int inventory;
        private int userId;
        private int productId;

        public SeckillThread(int seckillNum, int inventory, int userId, int productId) {
            this.seckillNum = seckillNum;
            this.inventory = inventory;
            this.userId = userId;
            this.productId = productId;
        }

        @Override
        public void run() {
            SeckilUserResult result = new SeckilUserResult();
            SeckillProductInfo info = seckillProductMapper.findSeckillInfoById(productId);
            result.setProductId(productId);
            result.setUserId(userId);
            result.setSeckillId(info.getId());
            result.setResult(0);
            result.setResultdata("秒杀成功！");
            result.setSecktime(new Date());
            if (seckillNum > inventory) {
                result.setResult(1);
                result.setResultdata("秒杀失败！");
            }
            //保存用户的秒杀结果
            seckilUserResultService.saveSeckilUserResult(result);
            SeckillProductInfo productInfo = new SeckillProductInfo();
            productInfo.setId(productId);
            productInfo.setSeckillNum(seckillNum);
            //更新秒杀商品的秒杀数量
            seckillProductMapper.updateSeckillInfoBySeckNum(productInfo);
        }
    }
}
