package io.khasang.moika.controller;

import io.khasang.moika.dao.BucketDao;
import io.khasang.moika.dao.ClientDao;
import io.khasang.moika.dao.ProductDao;
import io.khasang.moika.entity.Bucket;
import io.khasang.moika.entity.Client;
import io.khasang.moika.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class BucketController {
    @Autowired
    BucketDao bucketDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    ProductDao productDao;

    @RequestMapping(value = "/shop/bucket/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void addProductToClientBucket(@RequestParam(name = "productId") long productId,
                                         @RequestParam(name = "clientId") long clientId,
                                         @RequestParam(name = "amount") int amount) {
        Bucket bucket = bucketDao.getBucketByClientAndProduct(productId, clientId);
        if (bucket == null) {
            bucket = new Bucket();
            Client client = clientDao.getClientById(clientId);
            Product product = productDao.getProductById(productId);
            bucket.setClient(client);
            bucket.setProduct(product);
            bucket.setAmount(amount);
            Date expTime = new Date();
            expTime.setTime(expTime.getTime() + 60000);
            bucket.setExpireDatetime(expTime);
            bucketDao.addBucket(bucket);
        }
        else {
            bucket.setAmount(bucket.getAmount() + amount);
            bucketDao.updateBucket(bucket);
        }
    }

    public void deleteProductFromClientBucket(long productId, long clientId, int amount) {
        Bucket bucket = bucketDao.getBucketByClientAndProduct(productId, clientId);
        int curAmount = bucket.getAmount();
        if (curAmount == amount) {
            bucketDao.deleteBucket(bucket);
        }
        else if (curAmount > amount) {
            bucket.setAmount(curAmount - amount);
            bucketDao.updateBucket(bucket);
        }
        else {
            //exception?
        }
    }

    public void clearClientBucket(long clientId) {
        List<Bucket> bucketList = bucketDao.getClientBucket(clientId);
        for (Bucket bucket : bucketList) {
            bucketDao.deleteBucket(bucket);
        }
    }

}
