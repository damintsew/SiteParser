package com.damintsev.rest.service.process;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import com.damintsev.repository.ParsedContentRepository;
import com.damintsev.repository.SiteRepository;
import com.damintsev.rest.model.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public abstract class AbstractPostProcessing implements PostProcessing {

    //todo move here
    @Autowired
    private ParsedContentRepository contentRepository;

    @Autowired
    protected SiteRepository siteRepository;

    @Override
    public Objects loadConfig() {
        return null;
    }

    @Override
    public List<ParsedContent> loadUnpostedData(Integer postsPerDay) {
        return contentRepository.loadUnpostedData(getSourceSite());
    }

    @Override
    public List<Post> map(List<ParsedContent> parsedContents) {
        return parsedContents.stream().map(content -> {
            Post post = mapContent(content);
            post.setUnparsedContent(content);

            return post;
        }).collect(Collectors.toList());
    }

    protected abstract Post mapContent(ParsedContent parsedContents);

    protected abstract String getSourceSiteId();

    private Site getSourceSite() {
        return siteRepository.getSite(getSourceSiteId());
    }
}
