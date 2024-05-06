package com.wesleyem.smolurl.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.time.LocalDate;

@Document(collection = "smolurls")
@Data
public class SmolUrl {

    @MongoId
    private String id;

    @CreatedDate
    @Setter(AccessLevel.NONE)
    protected LocalDate created;

    private LocalDate expires;

    private String targetUrl;

    public SmolUrl() {
        this.created = LocalDate.now();
    }

    public SmolUrl(String targetUrl, long daysToAdd) {
        this.created = LocalDate.now();
        this.expires = this.created.plusDays(daysToAdd);
        this.targetUrl = targetUrl;
    }

    public SmolUrl(String targetUrl) {
        this.created = LocalDate.now();
        this.expires = this.created.plusDays(30);
        this.targetUrl = targetUrl;
    }

    public void update(SmolUrl smolUrl) {
        this.targetUrl = smolUrl.getTargetUrl();
        this.expires = smolUrl.getExpires();
    }
}
