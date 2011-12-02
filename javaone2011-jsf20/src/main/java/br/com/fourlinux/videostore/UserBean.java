package br.com.fourlinux.videostore;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fourlinux.videostore.domain.User;
import br.com.fourlinux.videostore.ejb.UserManagerSessionBean;

@ManagedBean
@RequestScoped
public class UserBean {
	
	private static final String ERROR_CLIENT_NOT_FOUND = "Usuário #%s não encontrado";
	private static final String ERROR_PASSWORD_CONFIRM = "Desculpe, mas o password de confirmação não está coerente.";
	private static final String ERROR_EMAIL_CONFIRM = "Desculpe, mas o e-mail de confirmação não está coerente.";
	private static final String USER_ADDED = "Usuário #%s adicionado com sucesso!";
	private static final String USER_REMOVED = "Usuário %s removido com sucesso!";
	
	@EJB
	private UserManagerSessionBean users;
	
	private User user = new User();
	private Long userId;
	
	private String emailConfirm;
	private String passwordConfirm;
	
	public List<User> getAllUsers() {
		return users.getAllUsers();
	}
	
	public String showUser() {
		user = getUser(userId);
		if (user != null) {
			return "showUser";
		} else {
			FacesMessage message = new FacesMessage(null, String.format(ERROR_CLIENT_NOT_FOUND, user.getId()));
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "listAllUsers";
		}
	}
	
	public String addNew() {
		user = new User();
		emailConfirm = null;
		passwordConfirm = null;
		return "addNewUser";
	}
	
	public String save() {
		boolean valid = true;
		
		if (user != null) {
			if (!emailConfirm.equals(user.getEmail())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR_EMAIL_CONFIRM, null);
				FacesContext.getCurrentInstance().addMessage(null, message);
				valid = false;
			}
			
			if (!passwordConfirm.equals(user.getPassword())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR_PASSWORD_CONFIRM, null);
				FacesContext.getCurrentInstance().addMessage("user-form:email-confirm", message);
				valid = false;
			}
			
			if (valid) {
				users.addUser(user);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(USER_ADDED, user.getFirstName()), null);
				FacesContext.getCurrentInstance().addMessage(null, message);
				return "listAllUsers";
			} else {
				return null;
			}
		}
		return "listAllUsers";
		
	}
	
	public void delete() {
		if (user != null) {
			users.removeUser(user.getId());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, String.format(USER_REMOVED, user.getFirstName()), null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public User getUser(Long id) {
		return users.getUser(id);
	}

	public UserManagerSessionBean getUsers() {
		return users;
	}

	public void setUsers(UserManagerSessionBean users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmailConfirm() {
		return emailConfirm;
	}

	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
