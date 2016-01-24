package com.damintsev.rest.dao;

import com.damintsev.rest.model.Post;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 24 Нояб. 2015
 */
public interface PostDao<T extends Post> {

    T publish(T post);
}
