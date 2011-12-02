package br.com.fourlinux.videostore.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.fourlinux.videostore.domain.Actor;
import br.com.fourlinux.videostore.ejb.MovieManagerSessionBean;

@FacesConverter(value="br.com.fourlinux.videostore.converters.Actor")
public class ActorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		MovieManagerSessionBean movies;
		try {
			Context jndiContext = new InitialContext();
			movies = (MovieManagerSessionBean) jndiContext
					.lookup("java:module/MovieManagerSessionBean");
		} catch (NamingException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Erro ao montar conversor!",
					null);
			throw new ConverterException(message);
		}

		Long actorId = Long.parseLong(value);
		Actor actor = movies.getActor(actorId);
		if (actor == null) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Ator não encontrado!", null);
			throw new ConverterException(message);
		}
		return actor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		String actorId = null;
		try {
			Actor actor = (Actor) value;
			actorId = Long.toString(actor.getId());
		} catch (Exception exception) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Ator não encontrado!", null);
			throw new ConverterException(message);
		}
		return actorId;
	}

}
