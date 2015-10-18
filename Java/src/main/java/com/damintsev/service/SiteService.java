package com.damintsev.service;

import com.damintsev.domain.SiteContent;
import com.damintsev.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Component
public class SiteService {

    @Autowired
    SiteRepository siteRepository;

    @Transactional
    public void saveIsNotExists(SiteContent content) {

        if (siteRepository.siteContentNotExists(content)) {
            siteRepository.save(content);
        }
    }
}
