package com.damintsev.repository;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
@Repository
public class ParsedContentRepository {

    public List<ParsedContent> loadUnpostedData(Site sourceSite) {
        return null;
    }
}
