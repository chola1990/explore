package web.beanes;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import web.common.Common;
import web.common.EmailSender;

import com.entities.User;
import com.facade.UserFacade;

@ManagedBean(name = "lbean")
@SessionScoped
public class LoginBean implements Login,Serializable {
	private User user;
	private String resetemail;

	@ManagedProperty(value = "#{commonBean}")
	private Common common_data;

	@EJB
	private UserFacade userFacade;

	public String getResetemail() {  
        return resetemail;  
    }  
  
    public void setResetemail(String resetemail) {  
        this.resetemail = resetemail;  
    }  
    
	public User getUser() {
		if (user == null && isLoged()) {
			user = userFacade.findUserByEmail(getPrincipalName());
		}
		return user;
	}

	public String getPrincipalName() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		return context.getUserPrincipal().getName();
	}

	public void Login() {
		throw new NotImplementedException();
	}

	public String logout() {
		getRequest().getSession().invalidate();
		user = null;
		return "logout";
	}

	public boolean isUserAdmin() {
		return getRequest().isUserInRole(common_data.getAdmin_role().getName());
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public Boolean isLoged() {
		if (user != null)
			return true;

		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		if (context != null && context.getUserPrincipal() != null
				&& context.getUserPrincipal().getName() != "")
			return true;

		return false;
	}

	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}

	@Override
	public String getUserFullName() {
		if (isLoged()){
			return getUser().getFullName();
		}

		return "";
	}
	
	public void reset(ActionEvent actionEvent) throws AddressException, MessagingException {  
        FacesMessage msg = null;  
        
        User u = null;
		u = userFacade.findUserByEmail(this.resetemail);		
		                
        if(u != null) { 
        	String newPassword = Long.toHexString(Double.doubleToLongBits(Math.random()));
        	String hashedPassword = DigestUtils.sha256Hex(newPassword);
    		u.setPassword(hashedPassword);  
    		userFacade.update(u);
        	
        	EmailSender email = new EmailSender();
    		String recipient=u.getEmail();
    		String subject = "SwimV2 Password Reset";
    		String content = "Your new login  credentials to SWIMv2 are "
    				+ " username: ".concat(u.getEmail())
    						.concat(" and password: ")
    						.concat(newPassword);
    		email.send(recipient, subject, content);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Reset", "A new password has been sent to " + u.getEmail());        
        }else {  
	        msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Password Reset", "The email provided is not valid.");  
	    }  
        
        FacesContext.getCurrentInstance().addMessage(null, msg);         
    }

	@Override
	public void refreshUser() {
		if(isLoged() && user != null && user.getEmail() != "")
		{
			user = userFacade.findUserByEmail(user.getEmail());
		}
		
	}	
}
