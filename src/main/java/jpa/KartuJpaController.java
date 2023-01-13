/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entity.Kartu;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;
import jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author Lenovo
 */
public class KartuJpaController implements Serializable {

    public KartuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("FINAL_exam_jar_0.0.1-SNAPSHOTPU");
    
    public KartuJpaController(){
        
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kartu kartu) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kartu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKartu(kartu.getId()) != null) {
                throw new PreexistingEntityException("Kartu " + kartu + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kartu kartu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kartu = em.merge(kartu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kartu.getId();
                if (findKartu(id) == null) {
                    throw new NonexistentEntityException("The kartu with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kartu kartu;
            try {
                kartu = em.getReference(Kartu.class, id);
                kartu.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kartu with id " + id + " no longer exists.", enfe);
            }
            em.remove(kartu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kartu> findKartuEntities() {
        return findKartuEntities(true, -1, -1);
    }

    public List<Kartu> findKartuEntities(int maxResults, int firstResult) {
        return findKartuEntities(false, maxResults, firstResult);
    }

    private List<Kartu> findKartuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kartu.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Kartu findKartu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kartu.class, id);
        } finally {
            em.close();
        }
    }

    public int getKartuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kartu> rt = cq.from(Kartu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
