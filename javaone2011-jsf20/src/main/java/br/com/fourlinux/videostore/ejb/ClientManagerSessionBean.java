package br.com.fourlinux.videostore.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.fourlinux.videostore.domain.Client;

@Stateless
@LocalBean
@Named("clientManagerService")
public class ClientManagerSessionBean {
	
	@PersistenceContext(unitName="movie-persistence")
	private EntityManager entityManager;
	
	public List<Client> getAllClients() {
		Query query = entityManager.createQuery("SELECT c FROM Client c");
		return query.getResultList();
	}
	
	public void addClient(Client client) {
		entityManager.persist(client);
	}
	
	public Client getClient(Long id) {
		return entityManager.find(Client.class, id);
	}
	
	public Client removeClient(Long id) {
		Client client = getClient(id);
		if (client != null) {
			entityManager.remove(client);
			return client;
		}
		return null;
	}
}
