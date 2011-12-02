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
import br.com.fourlinux.videostore.domain.User;
import br.com.fourlinux.videostore.ejb.UserManagerSessionBean;

@FacesConverter(value="br.com.fourlinux.videostore.converters.User")
public class UserConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UserManagerSessionBean users;
		try {
			Context jndiContext = new InitialContext();
			users = (UserManagerSessionBean) jndiContext
					.lookup("java:module/UserManagerSessionBean");
		} catch (NamingException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Erro ao montar conversor!",
					null);
			throw new ConverterException(message);
		}
		
		Long userId = Long.parseLong(value);
		User user = users.getUser(userId);
		if (user == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Filme não encontrado!", null);
			throw new ConverterException(message);
		}
		return user;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		User user = (User) value;
		return user.getId().toString();
	}

}
