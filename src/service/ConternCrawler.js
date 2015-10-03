var Crawler = require("simplecrawler");

var StringDecoder = require('string_decoder').StringDecoder;
var decoder = new StringDecoder('utf8');

var ContentCrawler = function(site) {

    var crawler = this;
    crawler.site = site;

    var foodCrawler = new Crawler(site.baseUrl, site.startUrl);

    foodCrawler.maxDepth = 4;
    crawler.crawler = foodCrawler;

};

ContentCrawler.prototype.complete = function(callback) {
    var model = this;

    model.crawler.on("fetchcomplete", function(queueItem, responseBuffer, response) {
        console.log("I just received %s (%d bytes)", queueItem.url, responseBuffer.length);

        var textChunk = decoder.write(responseBuffer);

        callback({
            url: queueItem.url,
            content: textChunk,
            siteId: model.site.id
        });
    });
};

ContentCrawler.prototype.addFetchCondition = function() {
    var model = this;

    model.conditionID = model.crawler.addFetchCondition(function(parsedURL) {
        //todo
        return !parsedURL.path.match(/\.js|\.css|\.gif/i)
            && parsedURL.path.match(/databases\/foodmeals/i);
    });
};

ContentCrawler.prototype.start = function() {
    this.crawler.start();
};




foodCrawler.on("queueerror", function(queueItem, responseBuffer, response) {
    console.log("I just received %s", queueItem);

    // Do something with the data in responseBuffer
});

