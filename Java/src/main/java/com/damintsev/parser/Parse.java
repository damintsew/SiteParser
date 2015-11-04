package com.damintsev.parser;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import com.damintsev.domain.SiteContent;
import com.damintsev.repository.SiteRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public abstract class Parse {

    @PersistenceContext
    protected EntityManager em;

    protected Site site;

    protected Predicate<SiteContent> regexpValidation;

    public void init() {
        site = getSite();

        Pattern pattern = Pattern.compile(site.getUrlRegExp());
        regexpValidation = content -> pattern.matcher(content.getUrl()).find();
    }

    public abstract List<SiteContent> loadSiteContent();

    protected boolean isValid(SiteContent content) {
        return regexpValidation.test(content);
    }

    public Predicate<SiteContent> getValidator() {
        return regexpValidation;
    }

    public abstract ParsedContent parse(SiteContent content);

    protected abstract Site getSite();
}
