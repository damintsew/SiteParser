package com.damintsev.job;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.RecordState;
import com.damintsev.domain.SiteContent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public class ParseRunner {


    public void runParser(Parse parser) {

        List<SiteContent> siteContentList = parser.loadSiteContent();

//        siteContentList.stream()
//                .map()

        for(SiteContent content : siteContentList) {
            if (parser.isValid(content)) {
                saveContent(parser.parse(content));
            } else {

            }
        }

    }

    private Function<SiteContent, ParsedContent>

    private void saveContent(ParsedContent parsedContent) {

        parsedContent.setState(RecordState.PARSED);


    }
}
