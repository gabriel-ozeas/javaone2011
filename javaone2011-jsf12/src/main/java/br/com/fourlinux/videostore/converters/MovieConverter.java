package br.com.fourlinux.videostore.converters;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.fourlinux.videostore.domain.Movie;
import br.com.fourlinux.videostore.ejb.MovieManagerSessionBean;

public class MovieConverter implements Converter {
	@EJB
	private MovieManagerSessionBean movies;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
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
		System.out.println(value);
		Movie movie = (Movie) value;
		return movie.getId().toString();
	}

}
