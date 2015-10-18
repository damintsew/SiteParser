package com.damintsev.domain;

import javax.persistence.*;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 18 Окт. 2015
 */
@MappedSuperclass
@Table(name = "parsed_content")
public class ParsedContent {

    @Id
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private RecordState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecordState getState() {
        return state;
    }

    public void setState(RecordState state) {
        this.state = state;
    }
}
