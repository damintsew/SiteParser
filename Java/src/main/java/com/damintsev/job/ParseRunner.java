package com.damintsev.job;

import com.damintsev.domain.RecordState;
import com.damintsev.domain.SiteContent;
import com.damintsev.domain.SiteContentState;
import com.damintsev.parser.Parse;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public class ParseRunner {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void runParser(Parse parser) {

        initParser(parser);
        List<SiteContent> siteContentList = loadUnParsedContent(parser);

        Map<Boolean, List<SiteContent>> contentMap = splitByCriteria(parser.getValidator(), siteContentList);

        processInvalidRecord(contentMap.get(false));
        processValidRecord(parser, contentMap.get(true));
    }

    private void initParser(Parse parser) {
        parser.init();
    }

    private List<SiteContent> loadUnParsedContent(Parse parser) {
        return parser.loadSiteContent();
    }

    private Map<Boolean, List<SiteContent>> splitByCriteria(Predicate<SiteContent> validator, List<SiteContent> siteContentList) {
        return siteContentList.parallelStream()
                        .collect(Collectors.partitioningBy(validator));
    }

    private List<SiteContentState> processInvalidRecord(List<SiteContent> siteContentList) {
        return siteContentList.stream()
                .map(content -> new SiteContentState()
                        .withSiteContent(content)
                        .withState(RecordState.INVALID_REG_EXP))
                .map(this::persist)
                .collect(Collectors.<SiteContentState>toList());
    }

    private List<SiteContentState> processValidRecord(Parse parser, List<SiteContent> siteContentList) {
        return siteContentList.stream()
                .map(content -> new SiteContentState()
                        .withSiteContent(content)
                        .withParsedContent(parser.parse(content))
                        .withState(RecordState.PARSED))
                .map(this::persist)
                .collect(Collectors.<SiteContentState>toList());
    }

    private SiteContentState persist(SiteContentState state) {
        if (state.getParsedContent() != null) {
            em.persist(state.getParsedContent());
        }
        if (state.getSiteContent() == null || state.getSiteContent().getSite() == null) {
            System.out.println("asdasd");
        }

        em.persist(state);
        return state;
    }
}
