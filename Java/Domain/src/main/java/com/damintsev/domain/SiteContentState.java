package com.damintsev.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 19 Окт. 2015
 */
@Entity
@Table(name = "site_content_state")
public class SiteContentState implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY, targetEntity = SiteContent.class)
    @JoinColumn
    private SiteContent siteContent;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = ParsedContent.class)
    @JoinColumn
    private ParsedContent parsedContent;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RecordState state;

    public SiteContent getSiteContent() {
        return siteContent;
    }

    public void setSiteContent(SiteContent siteContent) {
        this.siteContent = siteContent;
    }

    public ParsedContent getParsedContent() {
        return parsedContent;
    }

    public void setParsedContent(ParsedContent parsedContent) {
        this.parsedContent = parsedContent;
    }

    public RecordState getState() {
        return state;
    }

    public void setState(RecordState state) {
        this.state = state;
    }

    public SiteContentState withSiteContent(SiteContent content) {
        setSiteContent(content);
        return this;
    }

    public SiteContentState withState(RecordState state) {
        setState(state);
        return this;
    }

    public SiteContentState withParsedContent(ParsedContent parsedContent) {
        setParsedContent(parsedContent);
        return this;
    }
}
