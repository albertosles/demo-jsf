package com.demo.jsf.web.mb;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String localeCode = "en";
	
	private static Map<String, String> languageMap;
	
	static {
		languageMap = new LinkedHashMap<String, String>();
		languageMap.put(Locale.ENGLISH.getDisplayLanguage(), Locale.ENGLISH.toString());
		languageMap.put(Locale.FRENCH.getDisplayLanguage(), Locale.FRENCH.toString());
	}
	
	public Map<String, String> getLanguageMap() {
		return languageMap;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	
	
	public void localeChangeHandler(ValueChangeEvent e) {
		
		String newLocale = e.getNewValue().toString();
		if (languageMap.containsValue(newLocale)) {
			localeCode = newLocale;
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(newLocale));
		}
	}
	
}