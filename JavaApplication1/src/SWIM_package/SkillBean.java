package web.beanes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import web.common.Common;

import com.entities.Skill;
import com.entities.User;
import com.facade.SkillFacade;

@ManagedBean(name = "sbean")
@ViewScoped
public class SkillBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private SkillFacade sFacade;
	
	@ManagedProperty(value = "#{commonBean}")
	private Common common_data;
	
	@ManagedProperty(value = "#{lbean}")
	private Login login;
	
	private Skill sSkill;
	private Skill nSkill;
	private Skill selectedSkill;
	private List<Skill> skillList;	
	
	@PostConstruct
	public void Init() {
		this.sSkill = new Skill();
		this.nSkill = new Skill();
		this.skillList = sFacade.findSkillsByStatus(common_data.getStatus_accepted());			
	}
	
	public void clear() {
		this.sSkill = new Skill();
		this.skillList = sFacade.findSkillsByStatus(common_data.getStatus_accepted());	
	}
	
	public void search() {		
		this.skillList = new ArrayList<Skill>();

		for (Skill s : sFacade.findSkillsByStatus(common_data.getStatus_accepted())) {
			if (s.getName().toLowerCase().contains(this.getsSkill().getName().toLowerCase()))
				skillList.add(s);
		}
	}
	
	public Skill getsSkill() {
		return sSkill;
	}

	public void setsSkill(Skill sSkill) {
		this.sSkill = sSkill;
	}
	
	public Skill getnSkill() {
		return nSkill;
	}

	public void setnSkill(Skill nSkill) {
		this.nSkill = nSkill;
	}
	
	public Skill getSelectedSkill() {
		return selectedSkill;
	}

	public void setSelectedSkill(Skill selectedSkill) {
		this.selectedSkill = selectedSkill;
	}
	
	public List<Skill> getskillList() {
		return this.skillList;
	}

	public void setskillList(List<Skill> skillList) {
		this.skillList = skillList;
	}
	
	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	
	public void removeSkill() {
		if(selectedSkill != null){
			sFacade.delete(selectedSkill);
			this.SyncSkills();
			SendMessage("Skill successfully removed");
		}
	}
	
	public void addSkill() {
		if(nSkill != null){
			this.nSkill.setStatus(common_data.getStatus_accepted());
			User currentUser = new User();
			currentUser = login.getUser();
			this.nSkill.setUsrid(currentUser);
			if(this.isNewSkill(nSkill))
			{	
				sFacade.save(nSkill);
				this.nSkill = new Skill();
				this.SyncSkills();
				SendMessage("Skill successfully added");	
			}
			else
			{
				 FacesContext.getCurrentInstance().addMessage("SwimMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Skill already exists", ""));  
			}
		}
	}
	
	public void onEdit(RowEditEvent event) {  
		Skill s = (Skill) event.getObject();
		sFacade.update(s);
		this.SyncSkills();	
        FacesMessage msg = new FacesMessage("Skill Updated", s.getName());  
        FacesContext.getCurrentInstance().addMessage("SwimMsg", msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
        
    }  	
		
	private void SendMessage(String msg) {
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage("SwimMsg", fm);
	}
	
	private void SyncSkills()
	{
		skillList = sFacade.findSkillsByStatus(common_data.getStatus_accepted());
		this.common_data.RefreshSkills();
	}
	
	private boolean isNewSkill(Skill n)
	{
		for (Skill s : common_data.getSkills()) {
			if (s.getName().toLowerCase().equals(n.getName().toLowerCase()))
				return false;
		}
		return true;
	}
}
