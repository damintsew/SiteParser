package com.damintsev.parser.impl;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.SiteContent;
import com.damintsev.domain.api.FilmContent;
import com.damintsev.parser.AbstractParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 24 Янв. 2016
 */
@Component
public class KinoTorrentParser extends AbstractParser {

    private final static String ID = "kino-torrent";

    @Override
    public ParsedContent extractContent(SiteContent content) {

        final Document doc = Jsoup.parse(content.getContent());

        final String filmTitle = doc.select(".title").first().text();
        Map<String, String> properties = extractProp(doc.select(".info").first());

        FilmContent filmContent = new FilmContent();
        filmContent.setFullTitle(filmTitle);
        if (filmTitle.contains("/")) {
            filmContent.setEngTitle(filmTitle.split("/")[0].trim());
            filmContent.setRusTitle(filmTitle.split("/")[1].trim());
        }


        filmContent.setSize(properties.get("Размер".toLowerCase()));
        filmContent.setGenre(properties.get("жанр".toLowerCase()));
        filmContent.setLink(content.getUrl());
        filmContent.setDownloadLink(doc.select("a.downOFF").attr("href"));

        return filmContent;
    }

    @Override
    protected String getSiteId() {
        return ID;
    }

    private Map<String, String> extractProp(Element element) {
        Map<String, String> properties = new HashMap<>();

        element.children().stream().filter(child -> Objects.equals(child.tag().getName(), "div"))
            .forEach(child -> {
                String[] parsedInfo = child.text().split(":");
                if (parsedInfo.length == 2) {
                    properties.put(parsedInfo[0].toLowerCase(), parsedInfo[1]);
                }
            });

        return properties;
    }
}
