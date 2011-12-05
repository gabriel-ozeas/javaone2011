package br.com.fourlinux.videostore.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.fourlinux.videostore.domain.User;

@Stateless
@LocalBean
@Named("userManagerService")
public class UserManagerSessionBean {
	
	@PersistenceContext(unitName="movie-persistence")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Query query = entityManager.createQuery("SELECT u FROM User u");
		return query.getResultList();
	}
	
	public void addUser(User user) {
		entityManager.persist(user);
	}
	
	public User getUser(Long id) {
		return entityManager.find(User.class, id);
	}
	
	public User removeUser(Long id) {
		User user = getUser(id);
		if (user != null) {
			entityManager.remove(user);
			return user;
		}
		return null;
	}
	
	public User getUserByEmail(String email) {
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
		query.setParameter("email", email);
		try {
			return (User) query.getSingleResult();
		} catch(NoResultException exception) {
			return null;
		}
	}
}
