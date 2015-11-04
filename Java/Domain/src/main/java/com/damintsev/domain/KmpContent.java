package com.damintsev.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "kill_me_pls_content")
public class KmpContent extends ParsedContent {

    @Column(columnDefinition="TEXT")
    private String content;

    @Column()
    @CollectionTable(name = "kill_me_pls_tags")
    @ElementCollection
    private List<String> tags;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
