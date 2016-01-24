package com.damintsev.domain;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public class PostConfig {

    private Integer postsPerDay;

    private long startTime;

    private long endTime;

    public Integer getPostsPerDay() {
        return postsPerDay;
    }

    public void setPostsPerDay(Integer postsPerDay) {
        this.postsPerDay = postsPerDay;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
