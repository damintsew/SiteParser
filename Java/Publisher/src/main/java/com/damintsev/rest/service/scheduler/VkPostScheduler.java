package com.damintsev.rest.service.scheduler;

import com.damintsev.domain.PostConfig;
import com.damintsev.rest.model.Post;
import ucar.unidata.util.DateUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 17 Нояб. 2015
 */
public class VkPostScheduler implements PostScheduler {

    private List<Long> availableTimeStamp;

    private long delta;
    private long postTime;

    @Override
    public void init(PostConfig config) {
        postTime = System.currentTimeMillis() + DateUtil.minutesToMillis(5);
        delta = calculateDelta(config);
    }

    @Override
    public Long getNextPostTime() {
        long time = postTime;
        postTime += delta;

        return time;
    }

    @Override
    public void setNextPostTime(Post post) {
        long time = getNextPostTime();
        post.setDate(time);
    }

    private long calculateDelta(PostConfig config) {
        long startTime = config.getStartTime();
        long endTime = config.getEndTime();

        return (startTime - endTime) / config.getPostsPerDay();
    }
}
