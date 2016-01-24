package com.damintsev.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 07 Окт. 2015
 */
@Component
public class ApplicationStart implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SiteRepository siteRepository;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // do what you want - you can use all spring beans (this is the difference between init-method and @PostConstructor where you can't)
        // this class can be annotated as spring service, and you can use @Autowired in it
        System.out.println("lll" + siteRepository.getActiveSites());
    }
}

