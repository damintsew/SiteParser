package com.damintsev.rest;

import com.damintsev.rest.dao.VkDao;
import com.damintsev.rest.dao.VkPostDao;
import com.damintsev.rest.model.VkPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 14 Нояб. 2015
 */
@Component
public class PublisherExecutor {

    @Autowired
    VkPostDao vkDao;

    @PostConstruct
    public void init() {
        VkPost post = new VkPost();
        post.setOwnerId("-93799965");
//        post.setOwnerId("295518");
        post.setMessage("lalalala");

        vkDao.postGroupWall(post);
    }
}
