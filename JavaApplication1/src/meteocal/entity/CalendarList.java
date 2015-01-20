/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteocal.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Milos
 */
@Entity
@Table(name="CALENDAR_LISTS")
public class CalendarList implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE , generator="cl_gen")
    @TableGenerator(name="cl_gen", table="ID_GEN",
            pkColumnName="ID_NAME", valueColumnName="ID_VAL",
            pkColumnValue="CL_GEN", initialValue = 120000000)
    @Column(name = "id_calendar_list")
    private Long id;
    
    
    //Relationship Entities
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendarList", orphanRemoval = true, targetEntity = meteocal.entity.Calendar.class)
    private Collection<Calendar> calendarList;

    
    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Calendar> getCalendarList() {
        return calendarList;
    }

    public void setCalendarList(Collection<Calendar> calendarList) {
        this.calendarList = calendarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalendarList)) {
            return false;
        }
        CalendarList other = (CalendarList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "meteocal.entity.CalendarList[ id=" + id + " ]";
    }
    
}
