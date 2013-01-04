package com.demo.jsf.adapter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.demo.jsf.model.ScoringFactor;
import com.demo.jsf.services.FactorService;

@ManagedBean(name="factorConverter")
@RequestScoped
public class FactorConverter implements Converter {
	
	@ManagedProperty(name="factorService", value="#{factorService}")
	private FactorService factorService;
	
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {
		if (submittedValue.trim().equals("")) {
			return null;
		} else {
			try {
				return factorService.getFactorById(new Long(submittedValue));

			} catch (NumberFormatException exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid player"));
			}
		}
	}

	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((ScoringFactor) value).getId());
		}
	}

	public FactorService getFactorService() {
		return factorService;
	}

	public void setFactorService(FactorService factorService) {
		this.factorService = factorService;
	}
	
}
