package explore.sorter;

import java.lang.reflect.Field;
import java.util.Comparator;
import explore.helper.Sphere;
import org.primefaces.model.SortOrder;
/**
 *
 * @author Nemanja
 */
public class SphereLazySorter implements Comparator<Sphere> {
 
    private String sortField;
     
    private SortOrder sortOrder;
    
    public SphereLazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
    
    @Override
    public int compare(Sphere o1, Sphere o2)  {
        try {
            Field userField = Sphere.class.getDeclaredField(this.sortField);
            userField.setAccessible(true);
            Object value1 = userField.get(o1);
            Object value2 = userField.get(o2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }


}
