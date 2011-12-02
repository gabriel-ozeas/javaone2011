package br.com.fourlinux.videostore.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.fourlinux.videostore.domain.Comment;

@Stateless
@LocalBean
@Named("commentService")
public class CommentsSessionBean {
	@PersistenceContext(unitName="movie-persistence")
	private EntityManager entityManager;
	
	public void addComment(Comment comment) {
		entityManager.persist(comment);
	}
	
	public void removeComment(Comment comment) {
		entityManager.remove(comment);
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getAllCommentsByMovie(Long movieId) {
		Query query = entityManager.createNamedQuery("findAllCommentsByMovie");
		query.setParameter("id", movieId);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getAllCommentsByUser(Long userId) {
		Query query = entityManager.createNamedQuery("findAllCommentsByUser");
		query.setParameter("id", userId);
		return query.getResultList();
	}
}
