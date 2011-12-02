package br.com.fourlinux.videostore.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.fourlinux.videostore.domain.Actor;
import br.com.fourlinux.videostore.domain.Movie;

@Stateless
@LocalBean
@Named("movieService")
public class MovieManagerSessionBean {
	@PersistenceContext(unitName="movie-persistence")
	private EntityManager entityManager;
	
	public void addMovie(Movie movie) {
		entityManager.persist(movie);
	}
	
	public void updateMovie(Movie movie) {
		entityManager.merge(movie);
	}
	
	public void removeMovie(Movie movie) {
		movie = entityManager.find(Movie.class, movie.getId());
		if (movie != null) {
			entityManager.remove(movie);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> getAllMovies() {
		Query query = entityManager.createQuery("SELECT m FROM Movie m");
		return query.getResultList();
	}
	
	public Movie getMovie(Long id) {
		return entityManager.find(Movie.class, id);
	}
	
	public Actor getActor(Long id) {
		return entityManager.find(Actor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Actor> getActorsByMovie(Long movieId) {
		Query query = entityManager.createQuery("SELECT m.stars FROM Movie m WHERE m.id = :id");
		query.setParameter("id", movieId);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Actor> getAllActors() {
		Query query = entityManager.createQuery("SELECT a FROM Actor a");
		return query.getResultList();
	}
	
	public void addActor(Actor actor) {
		entityManager.persist(actor);
	}
}
