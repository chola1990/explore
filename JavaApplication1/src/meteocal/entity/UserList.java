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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Milos
 */
@Entity
@Table(name="USER_LISTS")
public class UserList implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE , generator="ul_gen")
    @TableGenerator(name="ul_gen", table="ID_GEN",
            pkColumnName="ID_NAME", valueColumnName="ID_VAL",
            pkColumnValue="UL_GEN", initialValue = 210000000)
    @Column(name = "id_user_list")
    private Long id;

    
    //Relationship Entities
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userList", orphanRemoval = true, targetEntity = meteocal.entity.User.class)
    private Collection<User> users;
    
    @OneToOne(optional = false, targetEntity = meteocal.entity.Event.class)
    @JoinColumn(name="event", referencedColumnName = "id_event")
    private Event event;
    
    
    //Transient attributes
    @NotNull(message = "May not be empty")
    @Transient 
    private Collection<User> invited;
    
    @NotNull(message = "May not be empty")
    @Transient
    private Collection<User> participants;
    
    
    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<User> getInvited() {
        return invited;
    }

    public void setInvited(Collection<User> invited) {
        this.invited = invited;
    }

    public Collection<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<User> participants) {
        this.participants = participants;
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
        if (!(object instanceof UserList)) {
            return false;
        }
        UserList other = (UserList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "meteocal.entity.UserList[ id=" + id + " ]";
    }
    
}
