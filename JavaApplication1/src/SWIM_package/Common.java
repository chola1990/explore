package web.common;

import java.util.List;

import com.entities.Skill;
import com.entities.nomenclatures.*;

//Use this interface for injecting the CommonBean into some other bean
public interface Common {
	public abstract List<Nomcountry> getCountries();

	public abstract List<Nomcity> getCities();

	public abstract List<Nomstatus> getStatuses();

	public abstract List<Nomrole> getRoles();

	public abstract List<Nomrating> getRaitings();
	
	public abstract Nomrole getUser_role();

	public abstract Nomrole getAdmin_role();
	
	public abstract List<Skill> getSkills();
	public abstract void RefreshSkills();
	
	public abstract Skill getSkill(String name);
	public abstract Nomstatus getStatus_accepted();
	public abstract Nomstatus getStatus_denied();
	public abstract Nomstatus getStatus_pending();
}
