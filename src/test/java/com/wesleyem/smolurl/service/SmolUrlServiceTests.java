package com.wesleyem.smolurl.service;

import com.wesleyem.smolurl.model.SmolUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class SmolUrlServiceTests {

    public static final String HTTPS_WESLEYEM_COM = "https://wesleyem.com";
    public static final String WEB_ROOT = "/";
    @Autowired
    SmolUrlService smolUrlService;

    @BeforeEach
    void checkDBSeeded() {
        if (smolUrlService.findAll().isEmpty()) {
            smolUrlService.create(HTTPS_WESLEYEM_COM);
        }
    }

    @Test
    void createOneSmolUrl() {
        SmolUrl smol = smolUrlService.create(HTTPS_WESLEYEM_COM, 99);
        SmolUrl smol1 = smolUrlService.get(smol.getId());
        assertThat(smol).isEqualTo(smol1);
    }

    @Test
    void getOneSmolUrl() {
        SmolUrl smol = smolUrlService.findAll().getFirst();
        assertThat(smol).isOfAnyClassIn(com.wesleyem.smolurl.model.SmolUrl.class);
    }

    @Test
    void getOneSmolUrlByTargetUrl() {
        smolUrlService.findByTargetUrl(HTTPS_WESLEYEM_COM);
    }

    @Test
    void updateOneSmolUrl() {
        SmolUrl updateMe = smolUrlService.findAll().getLast();
        updateMe.setExpires(updateMe.getCreated().plusDays(System.currentTimeMillis() % 3));
        smolUrlService.update(updateMe);
        SmolUrl updated = smolUrlService.get(updateMe.getId());
        assertThat(updateMe).isEqualTo(updated);
    }

    @Test
    void deleteOneSmolUrl() {
        SmolUrl deleteMe = smolUrlService.findAll().getFirst();
        smolUrlService.delete(deleteMe.getId());
        SmolUrl deleted = smolUrlService.get(deleteMe.getId());
        assertThat(deleted.getTargetUrl()).isEqualTo(WEB_ROOT);
    }
}
