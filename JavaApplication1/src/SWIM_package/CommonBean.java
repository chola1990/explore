package web.common;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.entities.Skill;
import com.entities.nomenclatures.Nomcity;
import com.entities.nomenclatures.Nomcountry;
import com.entities.nomenclatures.Nomrating;
import com.entities.nomenclatures.Nomrole;
import com.entities.nomenclatures.Nomstatus;
import com.facade.SkillFacade;
import com.facade.nomenclatures.NomCityFacade;
import com.facade.nomenclatures.NomCountryFacade;
import com.facade.nomenclatures.NomRatingFacade;
import com.facade.nomenclatures.NomRoleFacade;
import com.facade.nomenclatures.NomStatusFacade;

@ManagedBean(eager = true)
@ApplicationScoped
public class CommonBean implements Common {

	@EJB
	private NomCountryFacade ncantFacade;
	@EJB
	private NomCityFacade ncityFacade;
	@EJB
	private NomStatusFacade nsFacade;
	@EJB
	private NomRoleFacade nrlFacade;
	@EJB
	private NomRatingFacade nrtFacade;
	@EJB
	private SkillFacade skilFacade;

	private List<Nomcountry> countries;
	private List<Nomcity> cities;
	private List<Nomstatus> statuses;
	private List<Nomrole> roles;
	private List<Nomrating> raitings;
	private List<Skill> skills;

	// constants
	private Nomrole user_role;
	private Nomrole admin_role;

	private Nomstatus status_accepted;
	private Nomstatus status_denied;
	private Nomstatus status_pending;
	private boolean isCityListDisabled = true;

	public CommonBean() {
		countries = new ArrayList<Nomcountry>(0);
		cities = new ArrayList<Nomcity>(0);
		statuses = new ArrayList<Nomstatus>(0);
		roles = new ArrayList<Nomrole>(0);
		raitings = new ArrayList<Nomrating>(0);
		skills = new ArrayList<Skill>(0);
	}

	@PostConstruct
	public void Init() {
		countries = ncantFacade.findAll();
		cities = ncityFacade.findAll();
		statuses = nsFacade.findAll();
		roles = nrlFacade.findAll();
		raitings = nrtFacade.findAll();
		for (Nomrole nr : roles) {
			if (nr.getName().toLowerCase().contains("admin"))
				admin_role = nr;
			if (nr.getName().toLowerCase().contains("user"))
				user_role = nr;
		}
		for (Nomstatus nr : statuses) {
			if (nr.getName().toLowerCase().contains("accepted"))
				status_accepted = nr;
			if (nr.getName().toLowerCase().contains("denied"))
				status_denied = nr;
			if (nr.getName().toLowerCase().contains("pending"))
				status_pending = nr;
		}
		skills = skilFacade.findSkillsByStatus(this.getStatus_accepted());
	}

	public Nomrole getUser_role() {
		return user_role;
	}

	public Nomrole getAdmin_role() {
		return admin_role;
	}

	public List<Nomcountry> getCountries() {
		return countries;
	}

	public List<Nomcity> getCities() {
		return cities;
	}

	public List<Nomcity> setCities() {
		return cities;
	}

	public List<Nomstatus> getStatuses() {
		return statuses;
	}

	public List<Nomrole> getRoles() {
		return roles;
	}

	public List<Nomrating> getRaitings() {
		return raitings;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	@Override
	public Skill getSkill(String name) {
		for (Skill s : skills) {
			if (s.getName().toLowerCase().equals(name.toLowerCase()))
				return s;
		}
		return null;
	}

	public Nomstatus getStatus_accepted() {
		return status_accepted;
	}

	public Nomstatus getStatus_denied() {
		return status_denied;
	}

	public Nomstatus getStatus_pending() {
		return status_pending;
	}

	public void RefreshSkills() {
		skills = skilFacade.findSkillsByStatus(this.getStatus_accepted());
	}

	public List<Nomcity> getCitiesforCountry(Nomcountry country) {
		List<Nomcity> l = new ArrayList<Nomcity>(0);
		if (country != null) {
			for (Nomcity c : cities) {
				if (c.getCountry().getId() == country.getId())
					l.add(c);
			}
		}
		return l;
	}

}
