package com.damintsev.job.impl;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import com.damintsev.domain.SiteContent;
import com.damintsev.job.Parse;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public class KmpParser extends Parse {

    public List<SiteContent> loadSiteContent() {
        TypedQuery<SiteContent> query = em.createQuery("SELECT unparsed FROM SiteContent unparsed, KmpContent kmp" +
                " WHERE unparsed.site = :site" +
                "  AND unparsed.id not in (kmp.id)", SiteContent.class);

        query.setParameter("site", getSite());
        query.setMaxResults(1000);

        return query.getResultList();
    }



    public ParsedContent parse(SiteContent site) {

    }

    protected Site getSite() {
        return (Site) em.createQuery("SELECT c FROM Site c WHERE c.name = 'killmepls'").getSingleResult();
    }
}
