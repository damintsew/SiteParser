package com.damintsev.rest.service.scheduler;

import com.damintsev.rest.model.Post;

import java.util.List;
import java.util.Objects;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public interface PostScheduler {

    void schedulePosts(Object config, List<Post> posts);
}
