package web.converters;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import web.common.Common;

import com.entities.nomenclatures.Nomrating;

@ManagedBean
@RequestScoped
public class NomratingConverter implements Converter {
	@ManagedProperty(value = "#{commonBean}")
	private Common common_data;

	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		List<Nomrating> lc = common_data.getRaitings();
		if (value == null || value == "")
			return null;
		if (lc == null)
			return null;
		for (Nomrating nc : lc) {
			if (nc.getId() == Integer.parseInt(value))
				return nc;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (!(value instanceof Nomrating)) {
			return null;
		}

		return String.valueOf(((Nomrating) value).getId());
	}
}
