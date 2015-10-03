
var ContentCrawler = require('./service/ConternCrawler');

var siteContentService = require('./dao/SiteContentService');

siteContentService.getAllActiveSites().then(function(sites) {
    if(sites.length == 0) {
        console.log("There is no available sites... exiting");
        process.exit(1);
    }
    sites.forEach(runCrawler);
});

function runCrawler(site) {
    var crawler = new ContentCrawler(site);

    crawler.complete(siteContentService.updateIfNotExists);
    crawler.start();
}