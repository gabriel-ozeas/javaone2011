package br.com.fourlinux.videostore;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 * Responsible for control the CRUD operations of the movies.
 * 
 * @author Gabriel Ozeas de Oliveira
 * 
 */
@ManagedBean
@SessionScoped
public class MovieController {
	/**
	 * All available movies for location
	 */
	private List<Movie> movies = new ArrayList<Movie>();
	private List<String> genres = new ArrayList<String>();
	private Movie movie;

	private static final String DELETE_MESSAGE = "Filme %s deletado com sucesso!";
	private static final String NO_MOVIE_SELECTED = "Nenhum filme foi selecionado!";

	@PostConstruct
	public void initializeController() {
		movie = new Movie();
		populateMovieList();
	}

	public String addNew() {
		movie = new Movie();
		return "/movie/form?faces-redirect=true";
	}

	public String save() {
		if (movie != null) {
			movies.add(movie);
			movie = null;
		}
		return "/movie/list?faces-redirect=true";
	}

	public String delete() {
		if (movie != null && movies.contains(movie)) {
			movies.remove(movie);
			FacesMessage deleteMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, String.format(DELETE_MESSAGE,
							movie.getTitle()), null);
			FacesContext.getCurrentInstance().addMessage(null, deleteMessage);
		}
		return null;
	}

	public String listAll() {
		return "/movie/list?faces-redirect=true";
	}

	public String showMovie() {
		if (movie != null) {
			return "/movie/show?faces-redirect=true";
		} else {
			FacesMessage errorMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, NO_MOVIE_SELECTED, null);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "/movie/list";
		}

	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<SelectItem> getGenreList() {
		List<SelectItem> genreList = new ArrayList<SelectItem>();
		for (String genre : genres) {
			genreList.add(new SelectItem(genre, genre));
		}
		return genreList;
	}

	private void populateMovieList() {
		Movie m1 = new Movie();
		m1.setTitle("Senhor dos Anéis: A Sociedade do Anel");
		m1.setDirector("Peter Jackson");
		m1.setTotalMedias(5);
		m1.setYear(2011);
		m1.setGenre("Adventure");
		movies.add(m1);

		Movie m2 = new Movie();
		m2.setTitle("Batman: O Cavaleiro das Trevas Ressurge");
		m2.setDirector("Christopher Nolan");
		m2.setYear(2012);
		m2.setGenre("Action");
		m2.setAvailable(false);
		movies.add(m2);

		Movie m3 = new Movie();
		m3.setTitle("Sherlock Holmes: O Jogo de Sombras");
		m3.setDirector("Guy Ritchie");
		m3.setYear(2011);
		m3.setGenre("Crime");
		m3.setAvailable(false);
		movies.add(m3);

		Movie m4 = new Movie();
		m4.setTitle("Dragão Vermelho");
		m4.setDirector("Brett Ratner");
		m4.setTotalMedias(3);
		m4.setYear(2002);
		m4.setGenre("Drama");
		movies.add(m4);

		// Populating genre list
		genres.add("Drama");
		genres.add("Action");
		genres.add("Crime");
		genres.add("Adventure");
		genres.add("Terror");
	}
}