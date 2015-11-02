package com.damintsev.domain;

import javax.persistence.*;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "parsed_content")
public abstract class ParsedContent {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;

//    @JoinColumn()
//    @OneToOne(mappedBy = "parsedContent", fetch = FetchType.LAZY)
//    SiteContentState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
//
//    public SiteContentState getState() {
//        return state;
//    }
//
//    public void setState(SiteContentState state) {
//        this.state = state;
//    }
}
