package br.com.fourlinux.videostore;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.fourlinux.videostore.domain.Actor;
import br.com.fourlinux.videostore.domain.Genre;
import br.com.fourlinux.videostore.domain.Movie;
import br.com.fourlinux.videostore.ejb.MovieManagerSessionBean;

/**
 * Responsible for control the CRUD operations of the movies.
 * 
 * @author Gabriel Ozeas de Oliveira
 * 
 */
public class MovieBean {
	private static final String DELETE_MESSAGE = "Filme %s deletado com sucesso!";
	private static final String NO_MOVIE_SELECTED = "Nenhum filme foi selecionado!";

	@EJB
	private MovieManagerSessionBean movies;
	private Movie movie;

	public MovieBean() {
		movie = new Movie();
	}

	public String addNew() {
		movie = new Movie();
		return "addNewMovie";
	}

	public String save() {
		if (movie != null) {
			movies.addMovie(movie);
			movie = null;
		}
		return "listAllMovies";
	}

	public String delete() {
		if (movie != null) {
			movies.removeMovie(movie);
			FacesMessage deleteMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, String.format(DELETE_MESSAGE,
							movie.getTitle()), null);
			FacesContext.getCurrentInstance().addMessage(null, deleteMessage);
		}
		return null;
	}

	public String listAll() {
		return "listAllMovies";
	}

	public String showMovie() {
		if (movie != null) {
			movie = movies.getMovie(movie.getId());
			return "showMovie";
		} else {
			FacesMessage errorMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, NO_MOVIE_SELECTED, null);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "listAllMovies";
		}

	}

	public List<Movie> getMovies() {
		return movies.getAllMovies();
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<SelectItem> getGenreList() {
		List<SelectItem> genreList = new ArrayList<SelectItem>();
		for (Genre genre : Genre.values()) {
			genreList.add(new SelectItem(genre, genre.getValue()));
		}
		return genreList;
	}
	
	public List<SelectItem> getActorList() {
		List<SelectItem> actorList = new ArrayList<SelectItem>();
		List<Actor> actors = movies.getAllActors();
		
		for (Actor actor : actors) {
			actorList.add(new SelectItem(actor, actor.getName()));
		}
		return actorList;
	}
}