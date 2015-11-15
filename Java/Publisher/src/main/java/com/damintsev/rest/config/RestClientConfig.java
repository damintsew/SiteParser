package com.damintsev.rest.config;

import com.damintsev.rest.handlers.AccessTokenUriTemplateHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 14 Нояб. 2015
 */
@Configuration
public class RestClientConfig {

    private final static String VK_BASE_URI = "https://api.vk.com/method/";
    public final static String ACCESS_TOKEN = "f410324a848619874086d740a2c7ceb5e048e4280de61e438535a1f88eb080c11b84a8185e4d00df4b89f";

    @Bean
    public RestTemplate configureRestTemplate() {

        RestTemplate template = new RestTemplate(new SimpleClientHttpRequestFactory());

        template.setUriTemplateHandler(uriTemplateHandler());
        template.setMessageConverters(Arrays.asList(new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter()));
        template.setErrorHandler(new DefaultResponseErrorHandler());

        return template;
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        return new ObjectMapper().setPropertyNamingStrategy(
                PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    private DefaultUriTemplateHandler uriTemplateHandler() {
        AccessTokenUriTemplateHandler uriTemplateHandler = new AccessTokenUriTemplateHandler(ACCESS_TOKEN);
        uriTemplateHandler.setBaseUrl(VK_BASE_URI);

        return uriTemplateHandler;
    }

}
