package com.damintsev.domain.api;

import com.damintsev.domain.ParsedContent;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 24 Янв. 2016
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "kino_torrent_parsed")
public class FilmContent extends ParsedContent {
    private String title;
    private String size;
    private String link;
    private String downloadLink;
    private String genre;
    private String engTitle;
    private String rusTitle;

    public void setFullTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setEngTitle(String engTitle) {
        this.engTitle = engTitle;
    }

    public String getEngTitle() {
        return engTitle;
    }

    public void setRusTitle(String rusTitle) {
        this.rusTitle = rusTitle;
    }

    public String getRusTitle() {
        return rusTitle;
    }
}
