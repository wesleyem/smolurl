package com.wesleyem.smolurl.service.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.wesleyem.smolurl.enumeration.SmolUrlCharset;
import com.wesleyem.smolurl.exceptions.NoSuchElementFoundException;
import com.wesleyem.smolurl.model.SmolUrl;
import com.wesleyem.smolurl.repository.SmolUrlRepository;
import com.wesleyem.smolurl.service.SmolUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class SmolUrlServiceImpl implements SmolUrlService {

    public static final int DEFAULT_ID_LENGTH = 5;
    private final SmolUrlRepository smolUrlRepository;

    @Override
    public SmolUrl create(String targetUrl) {
        long DEFAULT_DAYS_VALID = 30;
        return create(targetUrl, DEFAULT_DAYS_VALID);
    }

    @Override
    public SmolUrl create(String targetUrl, long daysValid) {
        log.info("Creating a new SmolUrl: {}", targetUrl);
        SmolUrl target = new SmolUrl(targetUrl, daysValid);
        target.setId(getRandomNanoId());
        return smolUrlRepository.save(target);
    }

    /**
     * This will ensure that a random NanoID doesn't already exist and will return a unique one as needed.
     * @return a random NanoID
     */
    private String getRandomNanoId() {
        return NanoIdUtils.randomNanoId(new SecureRandom(), SmolUrlCharset.DEFAULT_ALPHABET.getCharArray(), DEFAULT_ID_LENGTH);
    }

    @Override
    public SmolUrl get(String id) {
        log.info("Trying to find the SmolUrl using id: {}", id);
        Optional<SmolUrl> smolUrl = smolUrlRepository.findById(id);
        return smolUrl
                .orElseThrow(
                        () -> new NoSuchElementFoundException("ID provided cannot be found in collection.")
                );
    }

    @Override
    public SmolUrl update(SmolUrl smolUrl) {
        log.info("Updating the SmolUrl with [id, target]: {}, {}", smolUrl.getId(), smolUrl.getTargetUrl());
        SmolUrl target = smolUrlRepository
                .findById(smolUrl.getId())
                .orElseThrow(() -> new NoSuchElementFoundException("ID provided cannot be found in collection."));
        target.update(smolUrl);
        return smolUrlRepository.save(target);
    }

    @Override
    public void delete(String id) {
        log.info("Deleting the SmolUrl with id: {}", id);
        smolUrlRepository.deleteById(id);
    }

    @Override
    public List<SmolUrl> findAll() {
        return smolUrlRepository.findAll();
    }

    @Override
    public SmolUrl findByTargetUrl(String targetUrl) {
        return smolUrlRepository.findItemByTargetUrl(targetUrl);
    }

}
