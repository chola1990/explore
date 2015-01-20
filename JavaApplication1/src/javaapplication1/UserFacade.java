/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.registration.business.security.boundary;

import meteocal.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import meteocal.entity.Calendar;

/**
 *
 * @author miglie
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "authPU")
    EntityManager em;

    
     public User createNew(){
        User tmp = new User();
        Calendar tmp_cal = new Calendar();
        tmp.setMyCalendar(tmp_cal);
        tmp_cal.setOwner(tmp);
        em.persist(tmp);
        em.flush();
        return tmp;
    }
    
    public void save(User usr) {
        User tmp;
        if(usr.getId() != null) 
        {
            tmp = em.find(User.class, (long)usr.getId());
            if(tmp!=null)
            {
                em.merge(usr);
                em.flush();
            }
        }
    }
    
    public void delete(int usrId) {
        User usr;
        usr = em.find(User.class, (long)usrId);
        if(usr != null)
        {
            em.remove(usr);
        }
        em.flush();
    }

    public User getEventType(int usrId) {
        User usr;
        usr = em.find(User.class, (long)usrId);
        if(usr != null)
            return usr;
        else
            return new User();
    }

    public List<User> getDB_Table() {
        TypedQuery<User> query = em.createQuery("SELECT usr FROM User AS usr", User.class);
        return query.getResultList();
    }
    

    public void unregister() {
        em.remove(getLoggedUser());
    }

    public User getLoggedUser() {
        return new User();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserFacade(){
       super(User.class);
    }
}
