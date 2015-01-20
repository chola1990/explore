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

import web.common.Common;

import com.entities.Skill;
import com.facade.SkillFacade;

@ManagedBean(name = "srbean")
@ViewScoped
public class SkillRequestBean implements Serializable {

	private static final long serialVersionUID = 1L;	

	@EJB
	private SkillFacade sFacade;
	
	@ManagedProperty(value = "#{commonBean}")
	private Common common_data;
	
	private Skill selectedSkill;	
	private List<Skill> skillPendingList;
	
	@PostConstruct
	public void Init() {	
		this.skillPendingList = sFacade.findSkillsByStatus(common_data.getStatus_pending());			
	}
	
	public Skill getSelectedSkill() {
		return selectedSkill;
	}
	
	public void setSelectedSkill(Skill selectedSkill) {
		this.selectedSkill = selectedSkill;
	}
	
	public List<Skill> getSkillPendingList() {
		return skillPendingList;
	}
	
	public void setSkillPendingList(List<Skill> skillPendingList) {
		this.skillPendingList = skillPendingList;
	}
	
	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}
	
	public void acceptSkill(Skill s) {
		if(s != null){
			s.setStatus(this.common_data.getStatus_accepted());
			sFacade.update(s);
			this.SyncSkills();
			SendMessage("Skill accepted");
		}
	}
	
	public void rejectSkill() {
		if(selectedSkill != null){
			selectedSkill.setStatus(this.common_data.getStatus_denied());
			sFacade.update(selectedSkill);
			this.SyncSkills();
			SendMessage("Skill rejected");
		}
	}
	
	private void SendMessage(String msg) {
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage("SwimMsg", fm);
	}
	
	private void SyncSkills()
	{
		this.skillPendingList = sFacade.findSkillsByStatus(common_data.getStatus_pending());
		this.common_data.RefreshSkills();
	}
}
