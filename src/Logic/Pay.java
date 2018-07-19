package Logic;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pay extends RecursiveTreeObject<Pay>{

    private IntegerProperty number;
    private IntegerProperty value;
    private StringProperty date;
    private IntegerProperty creditCode;
    private StringProperty residue;

    public Pay(int number, int value, String date, int creditCode) {
        this.number = new SimpleIntegerProperty(number);
        this.value = new SimpleIntegerProperty(value);
        this.date = new SimpleStringProperty(date);
        this.creditCode = new SimpleIntegerProperty(creditCode);
    }

    public Pay() {

    }

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number = new SimpleIntegerProperty(number);
    }
    
    public IntegerProperty numberProperty(){
        return number;
    }

    public int getValue() {
        return value.get();
    }

    public void setValue(int value) {
        this.value = new SimpleIntegerProperty(value);
    }
    
    public IntegerProperty valueProperty(){
        return value;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }
    
    public StringProperty dateProperty(){
        return date;
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

    public String getResidue() {
        return residue.get();
    }

    public void setResidue(String residue) {
        this.residue = new SimpleStringProperty(residue);
    }
    
    public StringProperty residueProperty(){
        return residue;
    }
}
