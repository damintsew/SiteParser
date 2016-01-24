package com.damintsev.rest.service;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.PostConfig;
import com.damintsev.rest.dao.PostDao;
import com.damintsev.rest.model.Post;
import com.damintsev.rest.service.process.PostProcessing;
import com.damintsev.rest.service.scheduler.PostScheduler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
@Component
public class PostOrchestrationService {

    private PostProcessing processing;
    private PostScheduler scheduler;
    private PostDao postDao;

    public List process(PostConfig config) {

        prepareData(config);

        return new ArrayList<>();
    }

    private List<Post> prepareData(PostConfig config) {
        List<ParsedContent> unpostedData = processing.loadUnpostedData(config.getPostsPerDay());

        return processing.map(unpostedData);

    }

    private void process(Post post) {
        scheduler.setNextPostTime(post);

        post = postDao.publish(post);


    }
}
