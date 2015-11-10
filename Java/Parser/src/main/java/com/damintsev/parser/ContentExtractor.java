package com.damintsev.parser;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.SiteContent;
import com.damintsev.domain.SiteContentState;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 10 Нояб. 2015
 */
public interface ContentExtractor {

    List<SiteContent> loadContent();

    Predicate<SiteContent> getValidator();

    SiteContentState persist(SiteContentState state);

    ParsedContent extractContent(SiteContent content);
}
