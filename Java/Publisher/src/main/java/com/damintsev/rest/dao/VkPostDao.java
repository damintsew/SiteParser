package com.damintsev.rest.dao;

import com.damintsev.rest.config.RestClientConfig;
import com.damintsev.rest.model.AccessToken;
import com.damintsev.rest.model.VkPost;
import com.damintsev.rest.model.response.VkResponse;
import com.damintsev.rest.model.response.WallPostResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 14 Нояб. 2015
 */
@Component
public class VkPostDao {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private RestOperations rest;

    public VkPost postGroupWall(VkPost post) {
        String url = "wall.post?owner_id={owner_id}&message={message}&friends_only={friends_only}&publish_date={publish_date}";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("owner_id", post.getOwnerId());
        parameters.put("message", post.getMessage());
        parameters.put("friends_only", post.getOwnerId());
        parameters.put("publish_date", post.getDate());

        WallPostResponse response = rest.getForObject(url, WallPostResponse.class, parameters);

        post.setId(response.getPostId());

        return post;
    }

    //todo
    public void test() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("owner_id", "295518");

        String posts = rest.getForObject("wall.get?owner_id={owner_id}", String.class, parameters);

        System.out.println("posts = " + posts);
    }
}
