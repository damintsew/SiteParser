package com.damintsev.rest.model.response;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public class VkResponse {

    private ResponsePayload response;

    public ResponsePayload getResponse() {
        return response;
    }

    public void setResponse(ResponsePayload response) {
        this.response = response;
    }
}
