/**
    * @author adamintsev, <a href="mailto:Andey.Damintsev@rreturnonintelligence.com">Andrey Damintsev</a>
    * @since 21 ����. 2015
*/

var Crawler = require("simplecrawler");
//var process = require('process');
var StringDecoder = require('string_decoder').StringDecoder;
var decoder = new StringDecoder('utf8');


var siteContentService = require('./dao/SiteContentService');
var site = null;

siteContentService.getSites().then(function(sites) {
    if(sites.length == 0) {
        console.log("There is no available sites... exiting");
        process.exit(1);
    }
    site = sites[0];
});

var foodCrawler = new Crawler("pbprog.ru", '/databases/foodmeals');
//foodCrawler.interval = 1000;
foodCrawler.maxDepth = 4;

foodCrawler.on("fetchcomplete", function(queueItem, responseBuffer, response) {
    console.log("I just received %s (%d bytes)", queueItem.url, responseBuffer.length);

    var textChunk = decoder.write(responseBuffer);

    siteContentService.updateIfNotExists({
        url: queueItem.url,
        content: textChunk,
        siteId: site.id
    });
});

var conditionID = foodCrawler.addFetchCondition(function(parsedURL) {
    return !parsedURL.path.match(/\.js|\.css|\.gif/i)
        && parsedURL.path.match(/databases\/foodmeals/i);
});

foodCrawler.on("queueerror", function(queueItem, responseBuffer, response) {
    console.log("I just received %s", queueItem);

    // Do something with the data in responseBuffer
});


foodCrawler.start();