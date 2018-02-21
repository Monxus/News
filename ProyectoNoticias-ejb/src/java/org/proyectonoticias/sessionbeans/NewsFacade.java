/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proyectonoticias.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.proyectonoticias.entities.News;

/**
 *
 * @author Ramon
 */
@Stateless
public class NewsFacade extends AbstractFacade<News> {

    @PersistenceContext(unitName = "ProyectoNoticias-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsFacade() {
        super(News.class);
    }
    
    public List<News> findFirstNewsByDate() {
        return em.createNamedQuery("News.findOrderNews").setMaxResults(3).getResultList();
}
    
    public List<News> findMoreNewsByDate(int pag) {
        return em.createNamedQuery("News.findOrderNews").setMaxResults(3).setFirstResult(pag).getResultList();
    }
    
    public List<News> findNewsBySlug(String slug) {
        return em.createNamedQuery("News.findBySlug").setParameter("slug", slug).getResultList();
    } 
}
