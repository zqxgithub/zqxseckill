package com.zqx.seckillProduct.service;


import com.zqx.commom.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//定义消费者，将注册到eureka的生产者指定到value中，绑定生产者和消费者。将远程服务映射为一个本地java方法
@FeignClient(value = "zqxProduct",path = "productOutControl")
public interface ProductService {

    //要使用类似controller层的写法

    //根据商店id查询商品
    @PostMapping("listProductby")
    public List<ProductInfo> listProductby(@RequestParam("shopid") int shopid);

    //根据商品id查询商品
    @PostMapping("findProductById")
    public ProductInfo findProductById(@RequestParam("id") int id);

}
