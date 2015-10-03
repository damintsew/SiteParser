
var SiteContent = require('./Domain').SiteContent;
var Site = require('./Domain').Site;

module.exports.updateIfNotExists = function(siteContent) {

    return SiteContent
        .findOrCreate({where: {url: siteContent.url}, defaults: siteContent})
        .spread(function (siteContent, created) {
            console.log('Created:' + created);

            return siteContent;
        });
};

module.exports.getSites = function() {
    return Site.all();
};

module.exports.getAllActiveSites = function() {
    return Site.all({
        where: {active: true}
    });
};