package com.damintsev.parser.impl;

import com.damintsev.domain.api.KmpContent;
import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import com.damintsev.domain.SiteContent;
import com.damintsev.parser.AbstractParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public class KmpParser extends AbstractParser {

//    public List<SiteContent> loadSiteContent() {
//        List<Long> parsedIds = em.createQuery("SELECT state.siteContent.id FROM SiteContentState state " +
//                " WHERE state.siteContent.site = :site", Long.class)
//                .setParameter("site", getSite())
//                .getResultList();
//
//        if (parsedIds.isEmpty()) {
//            return em.createQuery("SELECT content FROM SiteContent content " +
//                    " WHERE content.site = :site", SiteContent.class)
//                    .setParameter("site", getSite())
//                    .setMaxResults(1000)
//                    .getResultList();
//        }
//        return em.createQuery("SELECT content FROM SiteContent content " +
//                " WHERE content.site = :site " +
//                "  AND content.id not in (:ids)", SiteContent.class)
//                .setParameter("site", getSite())
//                .setParameter("ids", parsedIds)
//                .setMaxResults(1000)
//                .getResultList();


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
//    }



    public ParsedContent parse(SiteContent content) {

        Document doc = Jsoup.parse(content.getContent());
        Elements storyRows = doc.select(".titlediv").select(".row");
        Element storyDiv = storyRows.get(1);

        Element tagsDiv = storyRows.get(0);

        List<String> tags = tagsDiv.select(".col-sm-6").get(1).select("a")
                .stream().map(Element::text).collect(Collectors.toList());

        KmpContent parser = new KmpContent();
        parser.setContent(storyDiv.text());
        parser.setTags(tags);

        return parser;
    }

    protected Site getSite() {
        return (Site) em.createQuery("SELECT c FROM Site c WHERE c.name = 'killmepls'").getSingleResult();
    }
}
