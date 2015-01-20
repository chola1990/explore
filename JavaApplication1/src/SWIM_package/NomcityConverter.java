package web.converters;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import web.common.Common;

import com.entities.Skill;
import com.entities.nomenclatures.Nomcity;


@ManagedBean
@RequestScoped
public class NomcityConverter implements Converter {
	@ManagedProperty(value="#{commonBean}")
	private Common common_data;

	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		List<Nomcity> lc = common_data.getCities();
		if(value == null || value == "") return null;
		if(lc == null) return null;
		for (Nomcity nc : lc) {
			if(nc.getName().toLowerCase().contains(value.trim().toLowerCase())) 
				return nc;
		}
				
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (!(value instanceof Nomcity)) {
            return null;
        }

        return String.valueOf(((Nomcity) value).getId());
	}

}
