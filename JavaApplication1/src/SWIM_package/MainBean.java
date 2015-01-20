package web.beanes;

import com.entities.*;
import com.facade.ConnectionFacade;
import com.facade.UserFacade;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import web.common.Common;

@ManagedBean
@ViewScoped
public class MainBean implements Serializable {

	private Skill selectSkill;
	private List<User> listSearchedUsers;

	@EJB
	private UserFacade uFacade;

	@ManagedProperty(value = "#{lbean}")
	private Login login;
	
	@ManagedProperty(value = "#{commonBean}")
	private Common common_data;
	
	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}

	public void search() {
		if (selectSkill != null) {
			listSearchedUsers = uFacade.findUsersBySkill(selectSkill,login.getUser(),common_data.getStatus_accepted());
		}
	}

	public List<Skill> completeSkill(String condition) {
		List<Skill> suggestions = new ArrayList<Skill>();

		for (Skill s : common_data.getSkills()) {
			if (s.getName().toLowerCase().startsWith(condition.toLowerCase()))
				suggestions.add(s);
		}

		return suggestions;
	}

	public Boolean getIsUser() {
		return false;
	}

	public Skill getSelectSkill() {
		return selectSkill;
	}

	public void setSelectSkill(Skill selectSkill) {
		this.selectSkill = selectSkill;
	}

	public void setuFacade(UserFacade uFacade) {
		this.uFacade = uFacade;
	}

	public List<User> getListSearchedUsers() {
		return listSearchedUsers;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
