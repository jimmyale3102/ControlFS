package Logic;

import java.util.ArrayList;
import java.util.Calendar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Credit {

    private IntegerProperty code;
    private StringProperty value;
    private StringProperty startDate;
    private StringProperty numberDepts;
    private StringProperty cuota;
    private ArrayList<Pay> pays;
    private StringProperty idBuyer;
    private StringProperty idCodeudor;
    private StringProperty residue;
    private StringProperty leftDebt;

    public Credit(){
        
    }
    
    public Credit(int code, String value, String startDate, String idBuyer, 
            String idCodeudor, String numberDepts, String cuota, String residue ) {
        super();
        this.code = new SimpleIntegerProperty(code);
        this.value = new SimpleStringProperty(value);
        this.startDate = new SimpleStringProperty(startDate);
        this.numberDepts = new SimpleStringProperty(numberDepts);
        this.cuota = new SimpleStringProperty(cuota);
        pays = new ArrayList();
        this.idBuyer = new SimpleStringProperty(idBuyer);
        this.idCodeudor = new SimpleStringProperty(idCodeudor);
        this.residue = new SimpleStringProperty(residue);
    }

    public Credit(String value, String startDate, String idBuyer, 
            String idCodeudor, String numberDepts, String cuota) {
        this.value = new SimpleStringProperty(value);
        this.startDate = new SimpleStringProperty(startDate);
        this.idBuyer = new SimpleStringProperty(idBuyer);
        this.idCodeudor = new SimpleStringProperty(idCodeudor);
        this.numberDepts = new SimpleStringProperty(numberDepts);
        this.cuota = new SimpleStringProperty(cuota);
    }

    public int getCode() {
        return code.get();
    }

    public void setCode(int code) {
        this.code = new SimpleIntegerProperty(code);
    }
    
    public IntegerProperty codeProperty(){
        return code;
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value = new SimpleStringProperty(value);
    }
    
    public StringProperty valueProperty(){
        return value;
    }

    public String getStartDate() {
        return startDate.get();
    }

    public void setStartDate(String startDate) {
        this.startDate = new SimpleStringProperty(startDate);
    }
    
    public StringProperty startDateProperty(){
        return startDate;
    }

    public String getNumberDepts() {
        return numberDepts.get();
    }

    public void setNumberDepts(String numberDepts) {
        this.numberDepts = new SimpleStringProperty(numberDepts);
    }
    
    public StringProperty numberDebtsProperty(){
        return numberDepts;
    }

    public String getCuota() {
        return cuota.get();
    }

    public void setCuota(String cuota) {
        this.cuota = new SimpleStringProperty(cuota);
    }
    
    public StringProperty cuotaProperty(){
        return cuota;
    }

    public ArrayList<Pay> getPays() {
        return pays;
    }

    public void setPays(ArrayList<Pay> pays) {
        this.pays = pays;
    }

    public String getIdBuyer() {
        return idBuyer.get();
    }

    public void setIdBuyer(String idBuyer) {
        this.idBuyer = new SimpleStringProperty(idBuyer);
    }
    
    public StringProperty idBuyerProperty(){
        return idBuyer;
    }

    public String getIdCodeudor() {
        return idCodeudor.get();
    }

    public void setIdCodeudor(String idCodeudor) {
        this.idCodeudor = new SimpleStringProperty(idCodeudor);
    }
    
    public StringProperty idCOdeudorProperty(){
        return idCodeudor;
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

    public String getLeftDebt() {
        return leftDebt.get();
    }

    public void setLeftDebt(String leftDebt) {
        this.leftDebt = new SimpleStringProperty(leftDebt);
    }  
    
    public StringProperty leftDebtProperty(){
        return leftDebt;
    }
    
}