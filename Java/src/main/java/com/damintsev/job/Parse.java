package com.damintsev.job;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import com.damintsev.domain.SiteContent;
import com.damintsev.repository.SiteRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public abstract class Parse {

    @PersistenceContext
    protected EntityManager em;

    public abstract List<SiteContent> loadSiteContent();

    protected boolean isValid(SiteContent content) {
        final String url = content.getUrl();

        return url.matches(getSite().getUrlRegExp());
    }

    public abstract ParsedContent parse(SiteContent content);

    protected abstract Site getSite();
}
