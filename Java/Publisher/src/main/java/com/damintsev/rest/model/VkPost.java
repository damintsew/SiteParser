package com.damintsev.rest.model;

import com.damintsev.domain.ParsedContent;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 14 Нояб. 2015
 */
public class VkPost implements Post {

    private Long id;
    private String ownerId;

    @JsonFormat()
    private FromGroup fromGroup;
    @JsonProperty("publish_date")
    private Long date;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public FromGroup getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(FromGroup fromGroup) {
        this.fromGroup = fromGroup;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Post withMessage(String message) {
        setMessage(message);
        return this;
    }

    private enum FromGroup {
        USER, //запись будет опубликована от имени пользователя (по умолчанию
        GROUP //запись будет опубликована от имени группы
    }

    @Override
    public void setUnparsedContent(ParsedContent content) {

    }
}
