/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityDB;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author eiwte
 */
public class DBController {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentPU");

    public void InputToBase(Student object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void DeleteDataPerRecord(Student stu){
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, stu.getId());
        
        try {
            em.getTransaction().begin();
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    /*public void DeleteAllData(){
        EntityManager em = emf.createEntityManager();
        int delecteCount = em.createQuery("DELETE FROM STUDENT").executeUpdate();
    }*/
    
    public void EditData(Student stu){
        EntityManager em = emf.createEntityManager();
        Student fromDb = fromDb = em.find(Student.class, stu.getId());
                fromDb.setName(stu.getName());
                fromDb.setGpa(stu.getGpa());
        
        try {
            em.getTransaction().begin();
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public List<Student> findByName(String Name){
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Student.findByName");
        query.setParameter("name", Name);
        List<Student> studList = (List<Student>) query.getResultList();
        em.close();
        return studList;
    }
    
}
