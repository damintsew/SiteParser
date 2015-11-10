package com.damintsev.extractor;

import com.damintsev.parser.ContentExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 10 Нояб. 2015
 */
@Component
public class ExtractorExecutor {

    private List<ExtractorService> services;

    @Autowired
    private List<ContentExtractor> contentExtractors;

    @PostConstruct
    public void init() {
        services = new ArrayList<>(contentExtractors.size());

        contentExtractors.forEach(contentExtractor -> {
            services.add(new ExtractorService(contentExtractor));
        });
    }

    @Scheduled(fixedRate = 300000)
    public void runContentExtractor() {

        services.forEach(ExtractorService::execute);
    }
}
