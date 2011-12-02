package br.com.fourlinux.videostore;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.fourlinux.videostore.domain.Comment;
import br.com.fourlinux.videostore.domain.Movie;
import br.com.fourlinux.videostore.domain.User;
import br.com.fourlinux.videostore.ejb.CommentsSessionBean;
import br.com.fourlinux.videostore.ejb.MovieManagerSessionBean;
import br.com.fourlinux.videostore.ejb.UserManagerSessionBean;

@ManagedBean
@ViewScoped
public class CommentBean {

	@EJB
	private MovieManagerSessionBean movies;
	@EJB
	private UserManagerSessionBean users;
	@EJB
	private CommentsSessionBean comments;

	private String comment;
	private String email;
	private Long movieId;

	private boolean commentFormDisplayed;

	public void submitComment() {
		User user = users.getUserByEmail(email);
		Movie movie = movies.getMovie(movieId);

		if (user != null) {
			if (movie != null) {
				Comment newComment = new Comment(comment, user, movie);
				comments.addComment(newComment);
				
				hideForm();
			} else {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Filme não encontrado.",
						null);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Usuário com e-mail " + email + " não encontrado.", null);
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

}
