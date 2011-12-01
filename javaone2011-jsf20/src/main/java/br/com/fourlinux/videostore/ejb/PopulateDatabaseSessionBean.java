package br.com.fourlinux.videostore.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.fourlinux.videostore.domain.Client;

@Singleton
@LocalBean
@Startup
public class PopulateDatabaseSessionBean {
	@EJB
	private ClientManagerSessionBean clients;
	
	@PostConstruct
	public void populateWithClients() {
		System.out.println(">>> Populating database...");
		
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
		
	    List<Client> clientList = clients.getAllClients();
	    System.out.println(">>> #" + clientList.size() + " people persisted in the database...");
	}
}
