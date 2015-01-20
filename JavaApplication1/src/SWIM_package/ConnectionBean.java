package web.beanes;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.UserTransaction;

import web.common.Common;

import com.entities.Connections;
import com.entities.Skill;
import com.entities.User;
import com.entities.Userrating;
import com.entities.nomenclatures.Nomrating;
import com.facade.ConnectionFacade;
import com.facade.UserFacade;
import com.sun.org.apache.xpath.internal.operations.Bool;

@ManagedBean(name = "cbean")
@ViewScoped
public class ConnectionsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ConnectionFacade cFacade;
	@EJB
	private UserFacade uFacade;

	@ManagedProperty(value = "#{lbean}")
	private Login login;

	@ManagedProperty(value = "#{commonBean}")
	private Common common_data;

	private List<User> connList;
	private List<User> connPendingList;
	private List<User> connRequestList;
	private List<User> suggConnList;
	private List<User> suggConnListAll;
	private User sUser;
	private User selectedUser;
	private User suggUser;
	private Nomrating userRating;
	private Skill selectSkill;

	@PostConstruct
	public void Init() {
		sUser = new User();
		User loggedUser = login.getUser();
		if (IsConnectionPage()) {
			connList = cFacade.getConnectionsForUser(loggedUser, common_data.getStatus_accepted());
			suggConnListAll = uFacade.suggestAllUsers(loggedUser, common_data.getStatus_accepted(), common_data.getUser_role());
		}
		if (IsConnectionPendingPage())
			connPendingList = cFacade.getConnectionsPendingForUser(loggedUser, common_data.getStatus_pending());
		if (IsConnectionRequestPage())
			connRequestList = cFacade.getConnectionsRequestForUser(loggedUser, common_data.getStatus_pending());
	}

	public void connectTo() {
		if (selectedUser != null) {
			Connections c = new Connections(common_data.getStatus_pending(), login.getUser(), selectedUser);
			cFacade.save(c);
			suggConnListAll = uFacade.suggestAllUsers(login.getUser(), common_data.getStatus_accepted(), common_data.getUser_role());
		}
		SendMessage("Concection request successfully send");
	}

	public void connectToSugg() {
		if (suggUser != null) {
			Connections c = new Connections(common_data.getStatus_pending(), login.getUser(), suggUser);
			cFacade.save(c);
		}
		SendMessage("Concection request successfully send");
	}

	public void clear() {
		sUser = new User();
	}

	public void rateUser() {
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hdnUsrRate");		
		int r_val = (value == null || value == "") ? userRating.getValue() : Integer.parseInt(value);
		if (selectedUser != null && value != null) {
			uFacade.updateRating(selectedUser, r_val);
			userRating = null;
			SendMessage("User successfully rated");
		}
	}

	public void search() {
		if (selectSkill != null) {
			List<Skill> l = new ArrayList<Skill>(0);
			l.add(selectSkill);
			sUser.setSkills(l);
		}
		connList = cFacade.search(login.getUser(), sUser, common_data.getStatus_accepted());
	}

	public void removeConn() {
		if (selectedUser != null) {
			cFacade.DeleteConnection(login.getUser(), selectedUser);
			connList = cFacade.getConnectionsForUser(login.getUser(), common_data.getStatus_accepted());
			SendMessage("Concection successfully removed");
		}
	}

	public void acceptConn() throws IOException {
		if (selectedUser != null) {
			cFacade.UpdateConnectionStatus(selectedUser, login.getUser(), common_data.getStatus_accepted());
			connList = cFacade.getConnectionsForUser(login.getUser(), common_data.getStatus_accepted());
			connRequestList = cFacade.getConnectionsRequestForUser(login.getUser(), common_data.getStatus_pending());
			SendMessage("Concection successfully accepted");
		}

		if (selectedUser != null) {
			suggConnList = uFacade
					.suggestUserAfterAccept(login.getUser(), selectedUser, common_data.getStatus_accepted(), common_data.getUser_role());
		}

		// return "view-suggested-conn";
		// FacesContext.getCurrentInstance().getExternalContext().redirect("ViewSuggestions.xhtml");
	}

	public void denayConn() {
		if (selectedUser != null) {
			cFacade.UpdateConnectionStatus(selectedUser, login.getUser(), common_data.getStatus_denied());
			connRequestList = cFacade.getConnectionsRequestForUser(login.getUser(), common_data.getStatus_pending());
			SendMessage("Concection successfully denied");
		}
	}

	private boolean IsConnectionPage() {
		if (getFullURI() != null && getFullURI().toLowerCase().contains("viewconnections.xhtml"))
			return true;
		else
			return false;
	}

	private boolean IsConnectionPendingPage() {
		if (getFullURI() != null && getFullURI().toLowerCase().contains("viewpendingrequests.xhtml"))
			return true;
		else
			return false;
	}

	private boolean IsConnectionRequestPage() {
		if (getFullURI() != null && getFullURI().toLowerCase().contains("viewconnectionrequests.xhtml"))
			return true;
		else
			return false;
	}

	private String getFullURI() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
		// returns something like "/myapplication/home.faces"
		String fullURI = servletRequest.getRequestURI();
		return fullURI;
	}

	public List<User> getConnList() {
		return connList;
	}

	public void setConnList(List<User> connList) {
		this.connList = connList;
	}

	public List<User> getConnPendingList() {
		return connPendingList;
	}

	public void setConnPendingList(List<User> connPendingList) {
		this.connPendingList = connPendingList;
	}

	public List<User> getConnRequestList() {
		return connRequestList;
	}

	public void setConnRequestList(List<User> connRequestList) {
		this.connRequestList = connRequestList;
	}

	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	private void SendMessage(String msg) {
		FacesMessage fm = new FacesMessage(msg);
		FacesContext.getCurrentInstance().addMessage("SwimMsg", fm);
	}

	public User getsUser() {
		return sUser;
	}

	public void setsUser(User sUser) {
		this.sUser = sUser;
	}

	public Skill getSelectSkill() {
		return selectSkill;
	}

	public void setSelectSkill(Skill selectSkill) {
		this.selectSkill = selectSkill;
	}

	public Nomrating getUserRating() {
		return userRating;
	}

	public void setUserRating(Nomrating userRating) {
		this.userRating = userRating;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public User getSuggUser() {
		return suggUser;
	}

	public void setSuggUser(User suggUser) {
		this.suggUser = suggUser;
	}

	public List<User> getSuggConnList() {
		return suggConnList;
	}

	public void setSuggConnList(List<User> suggConnList) {
		this.suggConnList = suggConnList;
	}

	public List<User> getSuggConnListAll() {
		return suggConnListAll;
	}

	public void setSuggConnListAll(List<User> suggConnListAll) {
		this.suggConnListAll = suggConnListAll;
	}
}
