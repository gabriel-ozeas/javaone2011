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

import br.com.fourlinux.videostore.domain.Movie;
import br.com.fourlinux.videostore.ejb.MovieManagerSessionBean;

@FacesConverter(value="br.com.fourlinux.videostore.converters.Movie")
public class MovieConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
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
		
		Long movieId = Long.parseLong(value);
		Movie movie = movies.getMovie(movieId);
		if (movie == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Filme não encontrado!", null);
			throw new ConverterException(message);
		}
		return movie;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Movie movie = (Movie) value;
		if (movie == null) {
			return null;
		} else {
			return movie.getId().toString();
		}
		
	}

}
