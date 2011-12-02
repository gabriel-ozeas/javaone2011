package br.com.fourlinux.videostore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIES")
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;

	private int year;
	private String director;

	private Genre genre;

	private String moviePicturePath;

	private List<Actor> stars = new ArrayList<Actor>();

	private MovieStatistics statistics = new MovieStatistics();

	private List<Comment> comments = new ArrayList<Comment>();

	public Movie() {
	}

	public Movie(String title, String director, int year, Genre genre) {
		this.title = title;
		this.director = director;
		this.year = year;
		this.genre = genre;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getMoviePicturePath() {
		return moviePicturePath;
	}

	public void setMoviePicturePath(String moviePicturePath) {
		this.moviePicturePath = moviePicturePath;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(name = "MOVIE_STARS", joinColumns = { @JoinColumn(name = "MOVIE_ID") }, inverseJoinColumns = { @JoinColumn(name = "ACTOR_ID") })
	public List<Actor> getStars() {
		return stars;
	}

	public void setStars(List<Actor> starts) {
		this.stars = starts;
	}

	@Embedded
	public MovieStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(MovieStatistics statistics) {
		this.statistics = statistics;
	}

	@OneToMany(mappedBy = "movie")
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((director == null) ? 0 : director.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

}
