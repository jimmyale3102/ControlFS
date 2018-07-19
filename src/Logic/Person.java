package Logic;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person extends RecursiveTreeObject<Person>{

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty lastName;
    private StringProperty city;
    private StringProperty address;
    private StringProperty cellphoneNumber;
    private StringProperty kind;


    public Person() {

    }

    public Person(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public Person(int id, String name, String lastName, String city, String address,
            String cellphoneNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);
        this.city = new SimpleStringProperty(city);
        this.address = new SimpleStringProperty(address);
        this.cellphoneNumber = new SimpleStringProperty(cellphoneNumber);
    }
    
    public Person(int id, String name, String lastName) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
    
    public IntegerProperty idProperty(){
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
    
    public StringProperty nameProperty(){
        return name;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }
    
    public StringProperty lastNameProperty(){
        return lastName;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city = new SimpleStringProperty(city);
    }
    
    public StringProperty cityProperty(){
        return city;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }
    
    public StringProperty addressProperty(){
        return address;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber.get();
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = new SimpleStringProperty(cellphoneNumber);
    }
    
    public StringProperty cellphoneProperty(){
        return cellphoneNumber;
    }
    
    //Relationship
    public void setKind(String kind) {
        this.kind = new SimpleStringProperty(kind);
    }
    
    public StringProperty kindProperty(){
        return kind;
    }
}
