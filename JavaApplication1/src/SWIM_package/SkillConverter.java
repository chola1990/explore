package web.converters;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import web.common.Common;
import web.common.CommonBean;

import com.entities.Skill;

@ManagedBean
@RequestScoped
public class SkillConverter implements Converter {

	@ManagedProperty(value = "#{commonBean}")
	private Common common_data;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		List<Skill> lc = getCommon_data().getSkills();
		if (value == null || value == "")
			return null;
		if (lc == null)
			return null;
		
		int id = Integer.parseInt(value);
		
		for (Skill s : lc) {
			//if (s.getName().toLowerCase().contains(value.toLowerCase()))
			if (s.getId() == id)
				return s;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			if ((value instanceof Skill)) {
				return String.valueOf(((Skill) value).getId());
			} else
				return value.toString();
		} else
			return null;
	}

	public Common getCommon_data() {
		if(common_data != null)
			return common_data;
		else
		{
			FacesContext context = FacesContext.getCurrentInstance();
			CommonBean cbean = (CommonBean) context.getApplication().evaluateExpressionGet(context, "#{commonBean}", CommonBean.class);
			return cbean;
		}
	}

	public void setCommon_data(Common common_data) {
		this.common_data = common_data;
	}

}
