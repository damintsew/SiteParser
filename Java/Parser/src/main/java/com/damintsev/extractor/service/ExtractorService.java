package com.damintsev.extractor.service;

import com.damintsev.domain.RecordState;
import com.damintsev.domain.SiteContent;
import com.damintsev.domain.SiteContentState;
import com.damintsev.parser.AbstractParser;
import com.damintsev.parser.ContentExtractor;
import com.damintsev.parser.impl.KmpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 10 Нояб. 2015
 */
public class ExtractorService {

    private ContentExtractor extractor;

    public ExtractorService(ContentExtractor contentExtractor) {
        setExtractor(contentExtractor);
    }

    public void execute() {
        List<SiteContent> siteContentList = extractor.loadContent();

        Map<Boolean, List<SiteContent>> contentMap = siteContentList.parallelStream()
                .collect(Collectors.partitioningBy(extractor.getValidator()));

        processRecords(contentMap.get(false), content -> new SiteContentState()
                .withSiteContent(content)
                .withState(RecordState.INVALID_REG_EXP));

        processRecords(contentMap.get(true), content -> {

            SiteContentState state = new SiteContentState()
                    .withSiteContent(content);
            try {
                state
                        .withParsedContent(extractor.extractContent(content))
                        .withState(RecordState.PARSED);
            } catch (Exception e) {
                state.setState(RecordState.PARSING_ERROR);
            }

            return state;
        });
    }

    public void setExtractor(ContentExtractor extractor) {
        this.extractor = extractor;
    }

    private List<SiteContentState> processRecords(List<SiteContent> contents, Function<SiteContent, SiteContentState> siteContentExtractor) {
        return contents.stream()
                .map(siteContentExtractor)
                .map(extractor::persist)
                .collect(Collectors.<SiteContentState>toList());
    }
}
