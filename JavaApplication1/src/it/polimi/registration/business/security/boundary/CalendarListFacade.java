/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.registration.business.security.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import meteocal.entity.CalendarList;

/**
 *
 * @author Milos
 */
@Stateless
public class CalendarListFacade extends AbstractFacade<CalendarList> {
    @PersistenceContext(unitName = "authPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalendarListFacade() {
        super(CalendarList.class);
    }
    
}
