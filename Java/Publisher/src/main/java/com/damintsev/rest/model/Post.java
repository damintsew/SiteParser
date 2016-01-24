package com.damintsev.rest.model;

import com.damintsev.domain.ParsedContent;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public interface Post {

    void setUnparsedContent(ParsedContent content);

    void setDate(Long date);
}
