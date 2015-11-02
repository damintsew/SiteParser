var Crawler = require("simplecrawler");

var StringDecoder = require('string_decoder').StringDecoder;
var decoder = new StringDecoder('utf8');

var ContentCrawler = function(site) {

    var model = this;
    model.site = site;

    var crawler = new Crawler(site.baseUrl, site.startUrl);

    crawler.maxDepth = 4;
    crawler.maxConcurrency = 15;
    crawler.interval = 100;

    crawler.on("complete", function(queueItem, resourses) {
        console.log('Discovery complete:' + resourses);
    });

    model.crawler = crawler;

    model.addFetchCondition();
    model.error();

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

ContentCrawler.prototype.error = function(callback) {
    var model = this;

    model.crawler.on("queueerror", function (queueItem, responseBuffer, response) {
        console.error("Error: %s", queueItem);

        if (callback) {
            callback();
        }
    });
};

ContentCrawler.prototype.addFetchCondition = function(condition) {
    var model = this,
        regExp = null;
    if (condition || model.site.urlRegExp) {
        regExp = new RegExp(condition || model.site.urlRegExp);
    }

    model.conditionID = model.crawler.addFetchCondition(function(parsedURL) {

        if (parsedURL.path.match(/\.js|\.css|\.gif|\.ico|\.png/i)) {
            return false;
        }

        //console.log(parsedURL.uriPath);

        if (regExp) {
            return parsedURL.path.match(regExp);
        }
        return true;
    });
};

ContentCrawler.prototype.start = function() {
    this.crawler.start();
};

module.exports = ContentCrawler;


//\/story/\d+|\/page/\d+
