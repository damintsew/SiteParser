package com.damintsev.crawler;

import com.damintsev.domain.Site;
import com.damintsev.service.SiteContentService;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
public class CrawlerFactory implements CrawlController.WebCrawlerFactory {

    private SiteContentService siteContentService;
    private Site site;

    public CrawlerFactory(SiteContentService siteContentService, Site site) {
        this.siteContentService = siteContentService;
        this.site = site;
    }

    public WebCrawler newInstance() throws Exception {
        return new CrawlerInstance(siteContentService, site);
    }
}
