package com.damintsev;

import com.damintsev.job.CrawlerRunner;
import com.damintsev.job.ParseRunner;
import com.damintsev.parser.impl.KmpParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 08 Окт. 2015
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(JavaConfig.class);

        CrawlerRunner runner = ctx.getBean("crawlerRunner", CrawlerRunner.class);
        runner.runCrawl();

        Thread.sleep(Long.MAX_VALUE);
    }
}
