package br.com.fourlinux.videostore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import br.com.fourlinux.videostore.domain.Actor;
import br.com.fourlinux.videostore.domain.Genre;
import br.com.fourlinux.videostore.domain.Movie;
import br.com.fourlinux.videostore.domain.User;
import br.com.fourlinux.videostore.ejb.MovieManagerSessionBean;

/**
 * Responsible for control the CRUD operations of the movies.
 * 
 * @author Gabriel Ozeas de Oliveira
 * 
 */
@ManagedBean(name = "movieController")
@ViewScoped
public class MovieBean implements Serializable {
	private static final String DELETE_MESSAGE = "Filme %s deletado com sucesso!";
	private static final String NO_MOVIE_SELECTED = "Nenhum filme foi selecionado!";

	@EJB
	private MovieManagerSessionBean movies;
	private Movie movie;

	private String movieImagePath;

	public MovieBean() {
		movie = new Movie();
	}

	public String addNew() {
		movie = new Movie();
		return "/movie/form?faces-redirect=true";
	}

	public String save() {
		if (movie != null) {
			if (movieImagePath != null) {
				movie.setMoviePicturePath(movieImagePath);
			}
			movies.addMovie(movie);
			movie = null;
		}
		return "/movie/list?faces-redirect=true";
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
		return "/movie/list?faces-redirect=true";
	}

	public String showMovie() {
		if (movie != null) {
			/*
			 * Devido ao redirecionamento, e o managed bean ser do escopo
			 * request, a variável de instância movie irá se perder. Para isso
			 * vamos utilizar os escope Flash do JSF 2.0
			 */
			Flash flashScope = FacesContext.getCurrentInstance()
					.getExternalContext().getFlash();
			flashScope.put("movie", movie);

			return "/movie/show?faces-redirect=true";
		} else {
			FacesMessage errorMessage = new FacesMessage(
					FacesMessage.SEVERITY_INFO, NO_MOVIE_SELECTED, null);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return "/movie/list?faces-redirect=true";
		}
	}
	
	public boolean isToShowFavoriteButton() {
		User user = retrieveUser();
		if (user != null) {
			return !(movies.isFavoriteMovie(movie.getId(), user.getId()));
		} else {
			return false;
		}
	}
	
	public void markAsFavorite() {
		User user = retrieveUser();
		if (user != null) {
			movies.markAsFavorite(movie.getId(), user.getId());
		}
	}
	
	private User retrieveUser() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = context.getSessionMap();
		return (User) sessionMap.get("user");
	}

	/*
	public void uploadMovieImage(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();

		File newFile = new File("/resources/images/movies/" + uploadedFile.getFileName());
		try {
			InputStream fileStream = new BufferedInputStream(uploadedFile.getInputstream());
			FileOutputStream newFileStream = new FileOutputStream(newFile);
			while(fileStream.available() != 0) {
				newFileStream.write(fileStream.read());
			}
			this.movieImagePath = uploadedFile.getFileName();
		} catch (IOException e) {
			FacesMessage errorMessage = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Não foi possível enviar image.", null);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
		}
	}
	*/
	
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