package com.wesleyem.smolurl.service;

import com.wesleyem.smolurl.model.SmolUrl;

import java.util.List;

public interface SmolUrlService {
    SmolUrl create(String targetUrl);

    SmolUrl create(String targetUrl, long daysValid);

    SmolUrl get(String id);

    SmolUrl update(SmolUrl smolUrl);

    void delete(String id);

    List<SmolUrl> findAll();

    SmolUrl findByTargetUrl(String targetUrl);

}
