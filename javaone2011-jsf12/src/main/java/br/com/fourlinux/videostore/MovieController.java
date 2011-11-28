package br.com.fourlinux.videostore;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for control the CRUD operations of the movies.
 * 
 * @author Gabriel Ozeas de Oliveira
 * 
 */
public class MovieController {
	/**
	 * All available movies for location
	 */
	private List<Movie> movies = new ArrayList<Movie>();
	private Movie movie;
	
	public MovieController() {
		Movie m1 = new Movie();
		m1.setTitle("Senhor dos Anéis: A Sociedade do Anel");
		m1.setDirector("Peter Jackson");
		m1.setTotalMedias(5);
		m1.setYear(2011);
		m1.setGender("Adventure");
		movies.add(m1);
		
		Movie m2 = new Movie();
		m2.setTitle("Batman: O Cavaleiro das Trevas Ressurge");
		m2.setDirector("Christopher Nolan");
		m2.setYear(2012);
		m2.setGender("Action");
		m2.setAvailable(false);
		movies.add(m2);
		
		Movie m3 = new Movie();
		m3.setTitle("Sherlock Holmes: O Jogo de Sombras");
		m3.setDirector("Guy Ritchie");
		m3.setYear(2011);
		m3.setGender("Crime");
		m3.setAvailable(false);
		movies.add(m3);
		
		Movie m4 = new Movie();
		m4.setTitle("Dragão Vermelho");
		m4.setDirector("Brett Ratner");
		m4.setTotalMedias(3);
		m4.setYear(2002);
		m4.setGender("Drama");
		movies.add(m4);
	}
	
	public String save() {
		return null;
	}
	
	public String delete() {
		return null;
	}
	
	public String listAll() {
		return "listAllMovies";
	}
	
	public String show() {
		return null;
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
}
