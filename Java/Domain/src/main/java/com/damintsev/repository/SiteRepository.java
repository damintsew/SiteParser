package com.damintsev.repository;

import com.damintsev.domain.Site;
import com.damintsev.domain.SiteContent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 07 Окт. 2015
 */
@Repository
public class SiteRepository {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<Site> getActiveSites() {
        return em.createQuery("FROM Site s WHERE s.active = true").getResultList();
    }

    public boolean siteContentNotExists(SiteContent siteContent) {
        try {
            return em.createQuery("SELECT s FROM SiteContent s " +
                    "WHERE s.site = :site " +
                    " AND s.url = :url")
                    .setParameter("site", siteContent.getSite())
                    .setParameter("url", siteContent.getUrl()).getSingleResult() == null;
        } catch (NoResultException e) {
            return true;
        }
    }

    public void save(SiteContent content) {
        em.persist(content);
    }

    public Site getSite(String siteId) {//todo change to id
        return (Site) em.createQuery("SELECT c FROM Site c WHERE c.name = :siteId")
                .setParameter("siteId", siteId)
                .getSingleResult();
    }
}
