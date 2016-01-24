package com.damintsev.domain;


import javax.persistence.*;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 07 ���. 2015
 */
@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "base_url")
    private String baseUrl;

    @Column(name = "start_url")
    private String startUrl;

    @Column(name = "url_reg_exp")
    private String urlRegExp;

    @Column(name = "active")
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
    }

    public String getUrlRegExp() {
        return urlRegExp;
    }

    public void setUrlRegExp(String urlRegExp) {
        this.urlRegExp = urlRegExp;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
