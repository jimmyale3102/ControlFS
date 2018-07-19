package Logic;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Alejandro
 */
public class Reference extends Person{
    
    private IntegerProperty creditCode;

    public Reference(int id, String name, String lastName, 
            String city, String address, String cellphoneNumber, int creditCode) {
        super(id, name, lastName, city, address, cellphoneNumber);
        this.creditCode = new SimpleIntegerProperty(creditCode);
    }
    
    public Reference(int id, int creditCode){
        super(id);
        this.creditCode = new SimpleIntegerProperty(creditCode);
    }

    public Reference() {

    }

    public int getCreditCode() {
        return creditCode.get();
    } 

    public void setCreditCode(int creditCode) {
        this.creditCode = new SimpleIntegerProperty(creditCode);
    }
    
    public IntegerProperty creditCodeProperty(){
        return creditCode;
    }
    
}
