package io.khasang.moika.controller;

import io.khasang.moika.dao.BucketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BucketController {
    @Autowired
    BucketDao bucketDao;

    public void addProductToClientBucket(long productId, long clientId, int amount) {
        bucketDao.addProductToClientBucket(productId, clientId, amount);
    }

    public void deleteProductFromClientBucket(long productId, long clientId, int amount) {
        bucketDao.deleteProductFromClientBucket(productId, clientId, amount);
    }

    public void clearClientBucket(long clientId) {
        bucketDao.clearClientBucket(clientId);
    }
}
