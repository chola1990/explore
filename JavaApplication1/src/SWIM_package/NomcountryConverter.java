package web.converters;

import java.util.List;

import javax.faces.bean.*;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.entities.nomenclatures.Nomcountry;

import web.common.Common;

@ManagedBean
@RequestScoped
public class NomcountryConverter implements Converter {

	@ManagedProperty(value="#{commonBean}")
	private Common common_data;

	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		List<Nomcountry> lc = common_data.getCountries();
		if(value == null || value == "") return null;
		if(lc == null) return null;
		for (Nomcountry nc : lc) {
			if(nc.getName().toLowerCase().contains(value.trim().toLowerCase())) return nc;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Nomcountry)) {
            return null;
        }

        return String.valueOf(((Nomcountry) value).getId());
	}

	
}
