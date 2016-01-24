package com.damintsev.rest.service.process;

import com.damintsev.domain.ParsedContent;
import com.damintsev.rest.model.Post;

import java.util.List;
import java.util.Objects;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public interface PostProcessing {

    Objects loadConfig();

    List<ParsedContent> loadUnpostedData(Integer postsPerDay);

    List<Post> map(List<ParsedContent> parsedContents);
}
