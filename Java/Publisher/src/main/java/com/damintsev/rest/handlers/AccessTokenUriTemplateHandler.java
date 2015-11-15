package com.damintsev.rest.handlers;

import org.springframework.web.util.DefaultUriTemplateHandler;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 14 Нояб. 2015
 */
public class AccessTokenUriTemplateHandler extends DefaultUriTemplateHandler {

    private static String ACCESS_TOKEN = null;
    private final static String ACCESS_TEMPLATE = "access_token={access_token}";

    private AccessTokenUriTemplateHandler() {

    }

    public AccessTokenUriTemplateHandler(String token) {
        ACCESS_TOKEN = token;
    }

    @Override
    @SuppressWarnings("unchecked")
    public URI expand(String uriTemplate, Map<String, ?> uriVariables) {

        if (!uriTemplate.contains("?")) {
            uriTemplate += "?" + ACCESS_TEMPLATE;
        } else
            uriTemplate += uriTemplate.endsWith("&") ? ACCESS_TEMPLATE : "&" + ACCESS_TEMPLATE;

        ((Map<String, Object>)uriVariables).put("access_token", ACCESS_TOKEN);

        return super.expand(uriTemplate, uriVariables);
    }

    @Override
    public URI expand(String uriTemplate, Object... uriVariableValues) {
        return super.expand(uriTemplate, uriVariableValues);
    }
}
