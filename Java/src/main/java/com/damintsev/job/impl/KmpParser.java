package com.damintsev.job.impl;

import com.damintsev.domain.KmpContent;
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
        List<Long> parsedIds = em.createQuery("SELECT state.siteContent.id FROM SiteContentState state " +
                " WHERE state.siteContent.site = :site", Long.class)
                .setParameter("site", getSite())
                .getResultList();
        if (parsedIds.isEmpty()) {
            return em.createQuery("SELECT content FROM SiteContent content " +
                    " WHERE content.site = :site", SiteContent.class)
                    .setParameter("site", getSite())
                    .setMaxResults(1000)
                    .getResultList();
        }
        return em.createQuery("SELECT content FROM SiteContent content " +
                " WHERE content.site = :site " +
                "  AND content.id not in (:ids)", SiteContent.class)
                .setParameter("site", getSite())
                .setParameter("ids", parsedIds)
                .setMaxResults(1000)
                .getResultList();


//        TypedQuery<SiteContent> query = em.createQuery("SELECT unparsed FROM SiteContent unparsed " +
//                " left outer join SiteContentState state on unparsed = state.siteContent" +
//                " WHERE unparsed.site = :site" +
//                "  AND state.state is null "
//                ,SiteContent.class);

//        TypedQuery<SiteContent> query = em.createQuery("SELECT unparsed FROM SiteContent unparsed " +
//                " left outer join unparsed.state as state" +//" on unparsed = state.siteContent" +
//                " WHERE unparsed.site = :site" +
//                "  AND state.state is null "
//                ,SiteContent.class);

        /*
        select * from public.site_content siteconten0_
            left outer join public.site_content_state siteconten1_ on siteconten0_.id = siteconten1_.site_id
                where siteconten0_.site_id=1
                and (siteconten1_.state is null)

                */
//        TypedQuery<SiteContent> query = em.createQuery("SELECT state.siteContent FROM SiteContentState state " +
//                " left outer join state.siteContent " +//" on unparsed = state.siteContent" +
//                " WHERE state.siteContent.site = :site"
////                "  AND state.state is null "
//                , SiteContent.class);

//        TypedQuery<SiteContent> query = em.createQuery("SELECT unparsed FROM SiteContent unparsed, SiteContentState state" +
//                " WHERE unparsed.site.id = :site" +
//                "  AND unparsed.id not in (state.siteContent.id) "
//                , SiteContent.class);

//        query.setParameter("site", getSite());
//        query.setMaxResults(1000);

//        return query.getResultList();
    }



    public ParsedContent parse(SiteContent site) {
        KmpContent parser = new KmpContent();
        parser.setContent("lalal");
        parser.setHeader("lalal");

        return parser;
    }

    protected Site getSite() {
        return (Site) em.createQuery("SELECT c FROM Site c WHERE c.name = 'killmepls'").getSingleResult();
    }
}
