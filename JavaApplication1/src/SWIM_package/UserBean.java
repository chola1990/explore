package web.beanes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import web.common.Common;
import web.common.EmailSender;

import com.entities.Skill;
import com.entities.User;
import com.entities.Userrating;
import com.entities.nomenclatures.Nomcity;
import com.facade.SkillFacade;
import com.facade.UserFacade;

@ManagedBean
@RequestScoped
public class UserBean  {
	private User currentUser;
	private User user;
	private List<String> uSkills;

	private List<Skill> skillList;
	private String oldpwd;
	private String newpwd;
	private String newskill;
	private Userrating userRating;
	private int rating;
	private int count;
	private int roleId;
	private List<Nomcity> citiesForCountry;
	// This whay you can inject application scope bean

	private List<Skill> allSkills;

	@ManagedProperty(value = "#{lbean}")
	private Login login;
	@ManagedProperty(value = "#{commonBean}")
	private Common common_data;

	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}

	@EJB
	private UserFacade uFacade;

	@EJB
	private SkillFacade sFacade;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserBean() {
		user = new User();
		uSkills = new ArrayList<String>(0);
	}

	@PostConstruct
	public void Init() {
		newskill = null;
		oldpwd = null;
		newpwd = null;
		uSkills = new ArrayList<String>(0);
		setCurrentUser(login.getUser());
		allSkills = common_data.getSkills();
		ResetSkillsSelected();
		SetUserSelectedSkills();
	}

	public String register() throws AddressException, MessagingException {
		if (user != null)
			user.setRole(common_data.getUser_role());
		
		if(uSkills.size() == 0){
			SendMessage("Please select at least one skill");
			return "";
		}
		List<Skill> listSkill = new ArrayList<Skill>(0);
		for (int i = 0; i < uSkills.size(); i++) {
			Object o = (Object) uSkills.get(i);
			if (o instanceof Skill) {
				listSkill.add((Skill) o);
				continue;
			}
			if (o instanceof String) {
				Skill skill = common_data.getSkill((String) o);
				listSkill.add(skill);
				continue;
			}
		}
		user.setSkills(listSkill);
		String pwd = user.getPassword();
		if (uFacade.saveNewUser(user)) {
			SendMessage("Registration was successfull! You will soon receive an email with your credentials!");
			sendemail(pwd);
			// authenticates the newly registered user
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			try {
				request.login(user.getEmail(), pwd);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "user-registered";
			
		} else {
			SendMessage("Please provide all the requierd data");
		}
		return "";
	}

	public void sendemail(String clearPassword) throws AddressException, MessagingException {
		EmailSender email = new EmailSender();
		String recipient = user.getEmail();
		String subject = "SwimV2 login Credentials";
		String content = "Your login  credentials to SWIMv2 are "
				+ " username: ".concat(user.getEmail()).concat(" and password: ").concat(clearPassword);
		email.send(recipient, subject, content);

	}

	public String namesOfSkillsOfUser() {
		skillList = currentUser.getSkills();

		String userskills = " ";

		for (int i = 0; i < skillList.size(); i++) {
			Object o = (Object) skillList.get(i);
			if (o instanceof Skill) {
				userskills = userskills.concat(((Skill) o).getName()).concat(" ");
				continue;
			}
		}

		return userskills;

	}

	public void addSkills() {
		uFacade.updateUserSkills(login.getUser(), allSkills);
		login.refreshUser();
		SetUserSelectedSkills();
		SendMessage("Skills succesffuly modified");
	}

	public void updateUser() {

		if (uFacade.updateUser(currentUser)) {
			SendMessage("Profile  updated successfully");
		} else {
			SendMessage("Please provide correct data");
		}

	}

	public void updatePassword() {
		if (uFacade.updatePassword(currentUser, oldpwd, newpwd)) {
			SendMessage("Password updated successfully");

		} else {
			SendMessage("Please provide correct data");
		}

	}

	public void sendRequestForNewSkill() {
		if (currentUser != null) {
			if (newskill != "") {
				Skill skill = new Skill();
				skill.setName(newskill);
				skill.setStatus(common_data.getStatus_pending());
				skill.setUsrid(currentUser);
				sFacade.save(skill);
				SendMessage("Skill request successfully send");
			} else {
				SendMessage("Try again");
			}
		}
	}

	private void SendMessage(String msg) {
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage("SwimMsg", fm);
	}

	public List<String> getuSkills() {
		return uSkills;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setuSkills(List<String> uSkills) {
		this.uSkills = uSkills;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	// public void setOldPassword(String oldPassword) {

	// }

	public String getNewskill() {
		return newskill;
	}

	public void setNewskill(String newskill) {
		this.newskill = newskill;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		String hashedPassword = DigestUtils.sha256Hex(newpwd);

		this.newpwd = hashedPassword;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		String hashedPassword = DigestUtils.sha256Hex(oldpwd);

		this.oldpwd = hashedPassword;
	}

	public int getRating() {
		userRating = currentUser.getRating();
		if (userRating != null) {
			String value = userRating.getRatingValue();
			float value1 = Float.parseFloat(value);
			rating = (int) Math.round(value1);
		} else {
			rating = 0;
		}
		return rating;
	}

	public void setRating(int rating) {
		userRating = currentUser.getRating();
		if (userRating != null) {
			String value = userRating.getRatingValue();
			float value1 = Float.parseFloat(value);
			rating = (int) Math.round(value1);
		} else {
			rating = 0;
		}

		this.rating = rating;
	}

	public int getCount() {
		userRating = currentUser.getRating();
		if (userRating != null) {
			count = userRating.getRatingCount();
		} else {
			count = 0;
		}

		return count;
	}

	public void setCount(int count) {
		userRating = currentUser.getRating();
		if (userRating != null) {
			this.count = userRating.getRatingCount();
		} else {
			this.count = 0;
		}

	}

	public List<Skill> getAllSkills() {
		return allSkills;
	}

	public void setAllSkills(List<Skill> allSkills) {
		this.allSkills = allSkills;
	}

	private void SetUserSelectedSkills() {
		if (login.getUser() != null) {
			List<Skill> selected_skills = new ArrayList<Skill>(0);
			List<Skill> not_selected_skills = new ArrayList<Skill>(0);
			for (Skill skill : allSkills) {
				boolean found = false;
				for (Skill s : login.getUser().getSkills()) {
					if (s.getId() == skill.getId()) {
						found = true;
									
					}
				}
				
				if(found)
				{
					Skill us = new Skill();
					us = skill;
					us.setSelected(true);
					selected_skills.add(us);
				}
				else
				{
					Skill us = new Skill();
					us = skill;
					us.setSelected(false);
					not_selected_skills.add(us);
				}
			}
			
			allSkills = new ArrayList<Skill>(0);
			allSkills.addAll(selected_skills);
			allSkills.addAll(not_selected_skills);		
		}
	}

	private void ResetSkillsSelected() {
		for (Skill s : allSkills) {
			s.setSelected(false);
		}
	}

	public List<Nomcity> getCitiesForCountry() {
		List<Nomcity> l = new ArrayList<Nomcity>(0);
		if (user != null && user.getCountry() != null) {
			for (Nomcity c : common_data.getCities()) {
				if (c.getCountry().getId() == user.getCountry().getId())
					l.add(c);
			}

		}
		citiesForCountry = l;
		return l;
	}

	public void setCitiesForCountry(List<Nomcity> citiesForCountry) {
		this.citiesForCountry = citiesForCountry;
	}
}
