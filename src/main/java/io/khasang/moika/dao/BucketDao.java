package io.khasang.moika.dao;

import io.khasang.moika.entity.Bucket;

import java.util.List;

public interface BucketDao {
    List<Bucket> getClientBucket(long clientId);
    void addProductToClientBucket(long productId, long clientId, int amount);
    void deleteProductFromClientBucket(long productId, long clientId, int amount);
    void clearClientBucket(long clientId);
}
