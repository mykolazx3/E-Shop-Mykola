package com.mykola.eshopmykola.repositories.user;

import com.mykola.eshopmykola.models.user.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
