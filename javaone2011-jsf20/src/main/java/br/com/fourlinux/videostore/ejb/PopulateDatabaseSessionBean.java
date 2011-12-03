package br.com.fourlinux.videostore.ejb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.fourlinux.videostore.domain.Actor;
import br.com.fourlinux.videostore.domain.Comment;
import br.com.fourlinux.videostore.domain.Genre;
import br.com.fourlinux.videostore.domain.Movie;
import br.com.fourlinux.videostore.domain.User;

@Singleton
@LocalBean
@Startup
public class PopulateDatabaseSessionBean {
	private User c1, c2, c3, c4, c5;
	private Movie m1, m2, m3, m4;
	
	@EJB
	private UserManagerSessionBean clients;
	
	@EJB
	private MovieManagerSessionBean movies;
	
	@EJB
	private CommentsSessionBean comments;
	
	@PostConstruct
	public void populate() {
		populateWithUsers();
		populateWithMoviesAndActors();
		populateWithComments();
	}
	
	private void populateWithUsers() {
		c4 = new User("Gustavo", "Lira", "gustavo@4linux.com.br");
		c4.setBirthday(new Date());
		clients.addUser(c4);
		
		c5 = new User("Gabriel", "Ozeas", "gabriel.ozeas@4linux.com.br");
		c5.setBirthday(new Date());
		clients.addUser(c5);
		
		c1 = new User("Joao", "Jose", "joao.jose@4linux.com.br");
		c1.setBirthday(new Date());
		clients.addUser(c1);
		
		c2 = new User("Maria", "Jose", "maria.jose@4linux.com.br");
		c2.setBirthday(new Date());
		clients.addUser(c2);
		
		c3 = new User("Antonio", "Silva", "antonio.silva@4linux.com.br");
		c3.setBirthday(new Date());
		clients.addUser(c3);
	}
	
	private void populateWithMoviesAndActors() {
		Actor actor1 = new Actor("Will Smith");
		movies.addActor(actor1);
		Actor actor2 = new Actor("Jhonhy Depp");
		movies.addActor(actor2);
		Actor actor3 = new Actor("Tommy Lee Jones");
		movies.addActor(actor3);
		Actor actor4 = new Actor("Angelina Jolie");
		movies.addActor(actor4);
		Actor actor5 = new Actor("Eddie Murphy");
		movies.addActor(actor5);
		
		m1 = new Movie();
		m1.setTitle("Senhor dos Anéis: A Sociedade do Anel");
		m1.setDirector("Peter Jackson");
		m1.setYear(2011);
		m1.setGenre(Genre.ADVENTURE);
		m1.setMoviePicturePath("lords_of_the_rings.jpg");
		m1.getStars().add(actor1);
		m1.getStars().add(actor2);
		m1.getStars().add(actor3);
		movies.addMovie(m1);

		m2 = new Movie();
		m2.setTitle("Batman: O Cavaleiro das Trevas Ressurge");
		m2.setDirector("Christopher Nolan");
		m2.setYear(2012);
		m2.setGenre(Genre.ACTION);
		m2.setMoviePicturePath("batman.jpg");
		m2.getStars().add(actor1);
		m2.getStars().add(actor2);
		movies.addMovie(m2);

		m3 = new Movie();
		m3.setTitle("Sherlock Holmes: O Jogo de Sombras");
		m3.setDirector("Guy Ritchie");
		m3.setYear(2011);
		m3.setGenre(Genre.CRIME);
		m3.setMoviePicturePath("sherlock_holmes.jpg");
		m3.getStars().add(actor1);
		m3.getStars().add(actor2);
		movies.addMovie(m3);

		m4 = new Movie();
		m4.setTitle("Dragão Vermelho");
		m4.setDirector("Brett Ratner");
		m4.setYear(2002);
		m4.setGenre(Genre.DRAMA);
		m4.setMoviePicturePath("reddragon.jpg");
		m4.getStars().add(actor1);
		movies.addMovie(m4);
	}
	
	public void populateWithComments() {
		Comment co1 = new Comment("Filme Massa", c1, m1);
		comments.addComment(co1);
		Comment co2 = new Comment("Recomendo assistir....", c2, m1);
		comments.addComment(co2);
		Comment co3 = new Comment("Muito bom mesmo!!!!!!!!!!", c3, m1);
		comments.addComment(co3);
		Comment co4 = new Comment("Filme horrivel, nunca mais!!!!!!!!!!", c4, m1);
		comments.addComment(co4);
	}
}
