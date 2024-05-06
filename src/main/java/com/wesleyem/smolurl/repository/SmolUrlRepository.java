package com.wesleyem.smolurl.repository;

import com.wesleyem.smolurl.model.SmolUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SmolUrlRepository extends MongoRepository<SmolUrl, String> {

    @Query("{targetUrl:'?0'}")
    SmolUrl findItemByTargetUrl(String targetUrl);

}
