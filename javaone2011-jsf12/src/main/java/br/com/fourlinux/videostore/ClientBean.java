package br.com.fourlinux.videostore;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.fourlinux.videostore.domain.Client;
import br.com.fourlinux.videostore.ejb.ClientManagerSessionBean;

public class ClientBean {
	
	private static final String ERROR_CLIENT_NOT_FOUND = "Cliente #%s não encontrado";
	private static final String CLIENT_ADDED = "Cliente #%s adicionado com sucesso!";
	
	@EJB
	private ClientManagerSessionBean clients;
	
	private Client client;
	private Long clientId;
	
	private String emailConfirm;
	private String passwordConfirm;
	
	public List<Client> getAllClients() {
		return clients.getAllClients();
	}
	
	public String showClient() {
		client = getClient(clientId);
		if (client != null) {
			return "showClient";
		} else {
			FacesMessage message = new FacesMessage(null, String.format(ERROR_CLIENT_NOT_FOUND, client.getId()));
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "listAllClients";
		}
	}
	
	public String addNew() {
		client = new Client();
		return "addNewClient";
	}
	
	public String save() {
		if (client != null) {
			clients.addClient(client);
		}
		FacesMessage message = new FacesMessage(null, String.format(CLIENT_ADDED, client.getFirstName()));
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "listAllClients";
	}
	
	public Client getClient(Long id) {
		return clients.getClient(id);
	}

	public ClientManagerSessionBean getClients() {
		return clients;
	}

	public void setClients(ClientManagerSessionBean clients) {
		this.clients = clients;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
