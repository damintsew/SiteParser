package com.damintsev.rest.service.process;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import com.damintsev.repository.ParsedContentRepository;
import com.damintsev.rest.model.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public abstract class AbstractPostProcessing implements PostProcessing {

    //todo move here
    @Autowired
    private ParsedContentRepository contentRepository;

    @Override
    public Objects loadConfig() {
        return null;
    }

    @Override
    public List<ParsedContent> loadUnpostedData() {
        return contentRepository.loadUnpostedData(getSourceSite());
    }

    @Override
    public abstract List<Post> map(List<ParsedContent> parsedContents);

    protected abstract Site getSourceSite();
}
