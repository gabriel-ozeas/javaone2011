package br.com.fourlinux.videostore.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.fourlinux.videostore.domain.Actor;
import br.com.fourlinux.videostore.domain.Client;
import br.com.fourlinux.videostore.domain.Genre;
import br.com.fourlinux.videostore.domain.Movie;

@Singleton
@LocalBean
@Startup
public class PopulateDatabaseSessionBean {
	@EJB
	private ClientManagerSessionBean clients;
	
	@EJB
	private MovieManagerSessionBean movies;
	
	@PostConstruct
	public void populateWithClients() {
		populateWithUsers();
		populateWithMoviesAndActors();
	}
	
	private void populateWithUsers() {
		Client c4 = new Client("Gustavo", "Lira", "gustavo@4linux.com.br");
		clients.addClient(c4);
		
		Client c5 = new Client("Gabriel", "Ozeas", "gabriel.ozeas@4linux.com.br");
		clients.addClient(c5);
		
		Client c1 = new Client("Joao", "Jose", "joao.jose@4linux.com.br");
		clients.addClient(c1);
		
		Client c2 = new Client("Maria", "Jose", "maria.jose@4linux.com.br");
		clients.addClient(c2);
		
		Client c3 = new Client("Antonio", "Silva", "antonio.silva@4linux.com.br");
		clients.addClient(c3);
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
		
		Movie m1 = new Movie();
		m1.setTitle("Senhor dos Anéis: A Sociedade do Anel");
		m1.setDirector("Peter Jackson");
		m1.setYear(2011);
		m1.setGenre(Genre.ADVENTURE);
		m1.getStars().add(actor1);
		m1.getStars().add(actor2);
		m1.getStars().add(actor3);
		movies.addMovie(m1);

		Movie m2 = new Movie();
		m2.setTitle("Batman: O Cavaleiro das Trevas Ressurge");
		m2.setDirector("Christopher Nolan");
		m2.setYear(2012);
		m2.setGenre(Genre.ACTION);
		m2.getStars().add(actor1);
		m2.getStars().add(actor2);
		movies.addMovie(m2);

		Movie m3 = new Movie();
		m3.setTitle("Sherlock Holmes: O Jogo de Sombras");
		m3.setDirector("Guy Ritchie");
		m3.setYear(2011);
		m3.setGenre(Genre.CRIME);
		m3.getStars().add(actor1);
		m3.getStars().add(actor2);
		movies.addMovie(m3);

		Movie m4 = new Movie();
		m4.setTitle("Dragão Vermelho");
		m4.setDirector("Brett Ratner");
		m4.setYear(2002);
		m4.setGenre(Genre.DRAMA);
		m4.getStars().add(actor1);
		movies.addMovie(m4);
	}
}
