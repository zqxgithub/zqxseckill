package com.zqx.seckillProduct.service;

import java.util.Map;


public interface SeckillByService {

    public Map<String, String> procedureLockBy(int userId, int id);

    public void mulitThread(int userId,int id);

    Map<String, String> pessimismLock(int userId, int id);

    Map<String, String> optimisticLock(int userId, int id);
}
