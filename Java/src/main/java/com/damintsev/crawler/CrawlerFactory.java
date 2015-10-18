package com.damintsev.crawler;

import com.damintsev.domain.Site;
import com.damintsev.repository.SiteRepository;
import com.damintsev.service.SiteService;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
public class CrawlerFactory implements CrawlController.WebCrawlerFactory {

    private SiteService siteService;
    private Site site;

    public CrawlerFactory(SiteService siteService, Site site) {
        this.siteService = siteService;
        this.site = site;
    }

    public WebCrawler newInstance() throws Exception {
        return new CrawlerInstance(siteService, site);
    }
}
