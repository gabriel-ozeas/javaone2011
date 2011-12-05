package br.com.fourlinux.videostore;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.fourlinux.videostore.domain.Comment;
import br.com.fourlinux.videostore.domain.Movie;
import br.com.fourlinux.videostore.domain.User;
import br.com.fourlinux.videostore.ejb.CommentsSessionBean;
import br.com.fourlinux.videostore.ejb.MovieManagerSessionBean;
import br.com.fourlinux.videostore.ejb.UserManagerSessionBean;

@ManagedBean
@ViewScoped
public class CommentBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private MovieManagerSessionBean movies;
	@EJB
	private UserManagerSessionBean users;
	@EJB
	private CommentsSessionBean comments;

	@ManagedProperty(value = "#{movieController}")
	private MovieBean movieBean;

	@Size(min = 2, max = 300)
	private String comment;

	@Size(min = 3, message = "Por favor, entre com um e-mail válido.")
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$", message = "Por favor, entre com um e-mail válido.")
	private String email;

	private Long movieId;

	private boolean commentFormDisplayed;

	public void submitComment() {
		User user = users.getUserByEmail(email);
		// Create user with just the email.
		if (user == null) {
			user = new User();
			user.setEmail(email);
			users.addUser(user);
		}

		Movie movie = movies.getMovie(movieId);

		if (movie != null) {
			Comment newComment = new Comment(comment, user, movie);
			comments.addComment(newComment);

			hideForm();
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Filme não encontrado.", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public void showForm(Long movieId) {
		this.commentFormDisplayed = true;
		this.movieId = movieId;
	}

	public void hideForm() {
		commentFormDisplayed = false;
		comment = null;
		email = null;
	}
	

	public boolean isCommentFormDisplayed() {
		return commentFormDisplayed;
	}

	public void setCommentFormDisplayed(boolean commentFormDisplayed) {
		this.commentFormDisplayed = commentFormDisplayed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	
	public MovieBean getMovieBean() {
		return movieBean;
	}

	public void setMovieBean(MovieBean movieBean) {
		this.movieBean = movieBean;
	}
}
