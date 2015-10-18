package com.damintsev.service;

import com.damintsev.crawler.CrawlerFactory;
import com.damintsev.domain.Site;
import com.damintsev.repository.SiteRepository;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public class CrawlerRunner {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private SiteService siteService;


    public void runCrawl() {
        List<Site> sites = siteRepository.getAll();
        int numberOfCrawlers = sites.size() * 5;

        for(Site site: sites) {
            CrawlController crawlController = init(site);

            CrawlerFactory factory = new CrawlerFactory(siteService, site);
            crawlController.start(factory, numberOfCrawlers);
        }
    }

    private CrawlController init(Site site) {
        String crawlStorageFolder = "/data/crawl/root";

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed(site.getBaseUrl());

        return controller;
    }
}
