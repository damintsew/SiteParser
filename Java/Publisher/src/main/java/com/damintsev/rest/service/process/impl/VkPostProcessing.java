package com.damintsev.rest.service.process.impl;

import com.damintsev.domain.ParsedContent;
import com.damintsev.domain.Site;
import com.damintsev.domain.api.KmpContent;
import com.damintsev.rest.model.Post;
import com.damintsev.rest.model.VkPost;
import com.damintsev.rest.service.process.AbstractPostProcessing;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public class VkPostProcessing extends AbstractPostProcessing {

    private final Function<ParsedContent, Post> mapper = (ParsedContent content) -> new VkPost()
            .withMessage(formatMessage((KmpContent) content));

    @Override
    public List<Post> map(List<ParsedContent> parsedContents) {
        return parsedContents.stream()
                .map(mapper).collect(Collectors.toList());
    }

    private String formatMessage(KmpContent content) {
        StringBuilder builder = new StringBuilder();

        builder.append(content.getContent()).append("\n");
        content.getTags().forEach(tag -> builder.append("#").append(tag).append(" "));

        return builder.toString();
    }

    @Override
    protected Site getSourceSite() {
        return null;
    }
}
