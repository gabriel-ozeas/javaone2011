package br.com.fourlinux.videostore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENTS")
@NamedQueries({
	@NamedQuery(name = "findAllCommentsByMovie", query = "SELECT comment FROM Movie m JOIN m.comments comment WHERE m.id = :id ORDER BY comment.date DESC"),
	@NamedQuery(name = "findAllCommentsByUser", query = "SELECT comment FROM User u JOIN u.comments comment WHERE u.id = :id ORDER BY comment.date DESC")
})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String comment;
	private Date date;
	private User user;
	private Movie movie;
	
	public Comment() {}
	public Comment(String comment, User user, Movie movie) {
		this.comment = comment;
		this.user = user;
		this.movie = movie;
		this.date = new Date();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
