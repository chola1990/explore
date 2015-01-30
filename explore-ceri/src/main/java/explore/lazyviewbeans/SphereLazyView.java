package explore.lazyviewbeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import explore.lazydatamodel.SphereLazyDataModel;
import javax.inject.Inject;
import explore.bean.sphereBean;
import explore.helper.Sphere;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Nemanja
 */
@Named(value="sphereLazyView")
@RequestScoped
public class SphereLazyView implements Serializable {
    
    @Inject
    sphereBean sphereData;
    Sphere slectedSphere;
    private SphereLazyDataModel lazyModel;
     
    
     
    @PostConstruct
    public void init() {
        lazyModel = new SphereLazyDataModel(this.sphereData.getSpheres());
    }
 
    public SphereLazyDataModel getLazyModel() {
        return lazyModel;
    }
 
    public Sphere getSelectedSphere() {
        return this.sphereData.getCurrent();
    }
 
    public void setSelectedSphere(Sphere selectedSphere) {
        this.slectedSphere = selectedSphere;
        this.sphereData.setCurrent(selectedSphere);
        this.sphereData.setIndex(selectedSphere.getIndex());
    }
     
    
     
    public void onRowSelect(SelectEvent event) {
       this.sphereData.setCurrent((Sphere)event.getObject());
    }
}
