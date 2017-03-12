package io.khasang.moika.dao;

import io.khasang.moika.entity.Bucket;

import java.util.List;

public interface BucketDao {
    List<Bucket> getClientBucket(long clientId);
    Bucket getBucketByClientAndProduct(long clientId, long productId);
    void addBucket(Bucket bucket);
    void updateBucket(Bucket bucket);
    void deleteBucket(Bucket bucket);
}
