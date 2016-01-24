package com.damintsev.parser;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import com.damintsev.domain.SiteContent;
import com.damintsev.domain.SiteContentState;
import com.damintsev.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public abstract class AbstractParser implements ContentExtractor {

    @PersistenceContext
    protected EntityManager em;

    @Autowired
    private SiteRepository siteRepository;

    protected Site site;

    protected Predicate<SiteContent> regexpValidation;

    @PostConstruct
    public void init() {
        site = getSite();

        final Pattern pattern = Pattern.compile(site.getUrlRegExp());
        regexpValidation = content -> pattern.matcher(content.getUrl()).find();
    }

    public List<SiteContent> loadContent() {

            List<Long> parsedIds = em.createQuery("SELECT state.siteContent.id FROM SiteContentState state " +
                    " WHERE state.siteContent.site = :site", Long.class)
                    .setParameter("site", getSite())
                    .getResultList();

            if (parsedIds.isEmpty()) {
                return em.createQuery("SELECT content FROM SiteContent content " +
                        " WHERE content.site = :site", SiteContent.class)
                        .setParameter("site", getSite())
                        .setMaxResults(100)
                        .getResultList();
            }
            return em.createQuery("SELECT content FROM SiteContent content " +
                    " WHERE content.site = :site " +
                    "  AND content.id not in (:ids)", SiteContent.class)
                    .setParameter("site", getSite())
                    .setParameter("ids", parsedIds)
                    .setMaxResults(100)
                    .getResultList();
    }

    @Transactional
    public SiteContentState persist(SiteContentState state) {

        if (state.getParsedContent() != null) {
            em.persist(state.getParsedContent());
        }

        em.persist(state);
        return state;
    }

    protected boolean isValid(SiteContent content) {
        return regexpValidation.test(content);
    }

    public Predicate<SiteContent> getValidator() {
        return regexpValidation;
    }

    public abstract ParsedContent extractContent(SiteContent content);

    protected Site getSite() {
        return siteRepository.getSite(getSiteId());
    }

    protected abstract String getSiteId();
}
