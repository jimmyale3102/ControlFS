/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectdb;

import Connection.DaoCredit;
import Connection.DaoDebt;
import Connection.DaoPerson;
import Connection.DaoReference;
import Logic.Credit;
import Logic.ManagementCredit;
import Logic.Pay;
import Logic.Person;
import Logic.Reference;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
/**
 *
 * @author Alejandro
 */
public class FXMLDocumentController implements Initializable {
    // Classes
    private Person by, cod, ref1, ref2;
    private Pay debt;
    private Credit credit;
    private DaoCredit daoCredit;
    private DaoDebt daoDebt;
    private DaoPerson daoPerson;
    private DaoReference daoReference;
    private ManagementCredit mgCredit;
    private SimpleDateFormat sdf;
    private Date calendar;
    //Components
    @FXML private JFXTextField txIdBy, txNameBy, txSurnamesBy, txCityBy, txAddressBy,
             txPhoneBy, txIdCod, txNameCod, txSurnamesCod, txCityCod, txAddressCod,
             txPhoneCod, txIdR1, txNameR1, txSurnamesR1, txCityR1, txAddressR1,
             txPhoneR1, txIdR2, txNameR2, txSurnamesR2, txCityR2, txAddressR2,
             txPhoneR2, txValue;
    @FXML private Label lbIdBy, lbNameBy, lbLastNameBy, lbCityBy, lbAddressBy,
            lbPhoneBy, lbIdRef, lbNamesRef, lbSurnamesRef, lbCityRef, lbAddressRef,
            lbPhoneRef, lbValue, lbDate, lbNumberDebts, lbDebt, lbPay, lbForPay,
            lbIdBy1, lbNameBy1, lbLastNameBy1, lbCityBy1, lbAddressBy1, lbPhoneBy1,
            lbValue1, lbDate1, lbNumberDebts1, lbDebt1, lbPay1, lbForPay1, lbMessage, 
            lbMessage2, lbValueDebt;
    @FXML private JFXButton btnDelete, btnAddDebt;
    @FXML private JFXComboBox<String> cbNumberDebts;
    @FXML private ImageView btnAdd, btnAddCredit, btnAddPay, btnFind, btnExit, minimizeButton;
    @FXML private AnchorPane mainWin, windowAdd, windowAdd2, findWindow, windowAddPay,
            windowAddPay2, windowBegin;
    //Tables
    @FXML private JFXTreeTableView tbBuyer;
    @FXML private JFXTreeTableView tbReferences;
    @FXML private JFXTreeTableView tbCredit;
    @FXML private JFXTreeTableView tbForPay;
    //Columns Table Buyers
    @FXML private TreeTableColumn<Person, Integer> clId;
    @FXML private TreeTableColumn<Person, String> clNames;
    @FXML private TreeTableColumn<Person, String> clSurnames;
    @FXML private TreeTableColumn<Person, Integer> clId1;
    @FXML private TreeTableColumn<Person, String> clNames1;
    @FXML private TreeTableColumn<Person, String> clSurnames1;
    //Columns Table References
    @FXML private TreeTableColumn<Person, Integer> clIdRef;
    @FXML private TreeTableColumn<Person, String> clNamesRef;
    @FXML private TreeTableColumn<Person, String> clSurnamesRef;
    @FXML private TreeTableColumn<Person, String> clRelationship;
    //Columns Table credit
    @FXML private TreeTableColumn<Pay, Integer> clNumber;
    @FXML private TreeTableColumn<Pay, Integer> clValue;
    @FXML private TreeTableColumn<Pay, String> clResidue;
    @FXML private TreeTableColumn<Pay, String> clDate;
    //Alert
    private Alert message, messageSuccessful;
    
    //Collections
    private ObservableList numbers = FXCollections.observableArrayList("6", 
            "12", "15", "18", "21", "24");
    private ObservableList<Person> people = FXCollections.observableArrayList();
    private ObservableList<Person> buyers = FXCollections.observableArrayList();
    private ObservableList<Person> referencesBuyer = FXCollections.observableArrayList();
    private ObservableList<Reference> references = FXCollections.observableArrayList();
    private ObservableList<Credit> credits = FXCollections.observableArrayList();
    private ObservableList<Pay> debts = FXCollections.observableArrayList();
    private ObservableList<Pay> debtsAux = FXCollections.observableArrayList();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        message = new Alert(Alert.AlertType.ERROR);
        messageSuccessful = new Alert(Alert.AlertType.INFORMATION);
        messageSuccessful.setContentText("Se ha registrado correctamente el crédito");
                        
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar = new Date();
        //Classes
        mgCredit = new ManagementCredit();
        by = new Person();
        cod = new Person();
        ref1 = new Person();
        ref2 = new Person();
        credit = new Credit();
        debt = new Pay();
        daoCredit = new DaoCredit();
        daoDebt = new DaoDebt();
        daoPerson = new DaoPerson();
        daoReference = new DaoReference();
        //Tables
        beginTableBuyer();
        cbNumberDebts.setItems(numbers);
        tbBuyer.setPlaceholder(new Label("No existen compradores"));
        tbBuyer.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!referencesBuyer.isEmpty()){
                    referencesBuyer.remove(2);
                    referencesBuyer.remove(1);
                    referencesBuyer.remove(0);
                }
                showBuyerInformation(tbBuyer.getSelectionModel().getSelectedIndex());
            }
        });
        tbForPay.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lbMessage.setVisible(false);
                lbMessage2.setVisible(false);
                showInformationForPay(tbForPay.getSelectionModel().getSelectedIndex());
            }
        });
        tbReferences.setPlaceholder(new Label("No se ha seleccionado un comprador"));
        tbReferences.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showReferencesInformation(tbReferences.getSelectionModel().getSelectedIndex());
            }
        });
        
        //Components
        btnDelete.setDisable(true);
        btnAddDebt.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addPay();
            }
        }); 
        btnAdd.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                validateFields();
            }
        }); 
        //Validation id
        txIdBy.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txIdBy.getText().length() == 10){
                    event.consume();
                }
            }
        });
        txIdCod.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txIdCod.getText().length() == 10){
                    event.consume();
                }
            }
        });
        txIdR1.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txIdR1.getText().length() == 10){
                    event.consume();
                }
            }
        });
        txIdR2.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txIdR2.getText().length() == 10){
                    event.consume();
                }
            }
        });
        //Validation names
        txNameBy.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txNameBy.getText().length() == 20){
                    event.consume();
                }
            }
        });
        txNameCod.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txNameCod.getText().length() == 20){
                    event.consume();
                }
            }
        });
        txNameR1.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txNameR1.getText().length() == 20){
                    event.consume();
                }
            }
        });
        txNameR2.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txNameR2.getText().length() == 20){
                    event.consume();
                }
            }
        });
        //Validation Surnames
        txSurnamesBy.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txSurnamesBy.getText().length() == 20){
                    event.consume();
                }
            }
        });
        txSurnamesCod.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txSurnamesCod.getText().length() == 20){
                    event.consume();
                }
            }
        });
        txSurnamesR1.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txSurnamesR1.getText().length() == 20){
                    event.consume();
                }
            }
        });
        txSurnamesR2.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txSurnamesR2.getText().length() == 20){
                    event.consume();
                }
            }
        });
        //Validate Cities
        txCityBy.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txCityBy.getText().length() == 15){
                    event.consume();
                }
            }
        });
        txCityCod.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txCityCod.getText().length() == 15){
                    event.consume();
                }
            }
        });
        txCityR1.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txCityR1.getText().length() == 15){
                    event.consume();
                }
            }
        });
        txCityR2.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(! (Character.isLetter(c) || Character.isSpaceChar(c)) ){
                    event.consume();
                }
                if(txCityR2.getText().length() == 15){
                    event.consume();
                }
            }
        });
        //Validation address
        txAddressBy.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(txAddressBy.getText().length() == 25){
                    event.consume();
                }
            }
        });
        txAddressCod.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(txAddressCod.getText().length() == 25){
                    event.consume();
                }
            }
        });
        txAddressR1.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(txAddressR1.getText().length() == 25){
                    event.consume();
                }
            }
        });
        txAddressR2.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(txAddressR2.getText().length() == 25){
                    event.consume();
                }
            }
        });
        //Validation Phone
        txPhoneBy.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txPhoneBy.getText().length() == 10){
                    event.consume();
                }
            }
        });
        txPhoneCod.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txPhoneCod.getText().length() == 10){
                    event.consume();
                }
            }
        });
        txPhoneR1.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txPhoneR1.getText().length() == 10){
                    event.consume();
                }
            }
        });
        txPhoneR2.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txPhoneR2.getText().length() == 10){
                    event.consume();
                }
            }
        });
        //Validate Value
        txValue.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>(){
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                char c = event.getCharacter().charAt(0);
                if(!Character.isDigit(c)){
                    event.consume();
                }
                if(txValue.getText().length() == 8){
                    event.consume();
                }
                if(txValue.getText().length() > 0){
                    cbNumberDebts.setDisable(false);
                } else {
                    cbNumberDebts.setDisable(true);
                }
            }
        });
        
        //BtnDelete
        btnDelete.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                daoDebt.delete(String.valueOf( credit.getCode() ));
                daoReference.delete(String.valueOf( ref1.getId() ));
                daoReference.delete(String.valueOf( ref2.getId() ));
                daoCredit.delete(String.valueOf( credit.getCode() ));
                daoPerson.delete(String.valueOf( by.getId() ));
                daoPerson.delete(String.valueOf( cod.getId() ));
                daoPerson.delete(String.valueOf( ref1.getId() ));
                daoPerson.delete(String.valueOf( ref2.getId() ));
                debts.clear();
                referencesBuyer.clear();
                beginTableBuyer();
                cleanFields();
            }
        });
        
        
    }    
    
    private void beginTableBuyer(){
        buyers.clear();
        credits = FXCollections.observableArrayList(daoCredit.read());
        debts = FXCollections.observableArrayList(daoDebt.read());
        people = FXCollections.observableArrayList(daoPerson.read());
        references = FXCollections.observableArrayList(daoReference.read());
        for(int i = 0; i < credits.size(); i++){
            for(int j = 0; j < people.size(); j++){
                if( Integer.parseInt(credits.get(i).getIdBuyer()) == people.get(j).getId() ){
                    int id = people.get(j).getId();
                    String name = people.get(j).getName();
                    String lastName = people.get(j).getLastName();
                    buyers.add(new Person(id, name, lastName));
                }
            }
        }
        clId.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Person, Integer> param) {
                return param.getValue().getValue().idProperty().asObject();
            }
        });
        clNames.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().nameProperty();
            }
        } );
        clSurnames.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().lastNameProperty();
            }
        });
        final TreeItem<Person> root = new RecursiveTreeItem<Person>(buyers,RecursiveTreeObject::getChildren);
        tbBuyer.getColumns().setAll(clId, clNames, clSurnames);
        tbBuyer.setRoot(root);
        tbBuyer.setShowRoot(false);
    }
    
    private void showBuyerInformation(int row){
        for(int i = 0; i < people.size(); i++){
            if(buyers.get(row).getId() == people.get(i).getId()){
                by = people.get(i); 
                for(int j = 0; j < credits.size(); j++){
                    if( String.valueOf( by.getId() ).equals( credits.get(j).getIdBuyer() ) ){
                        credit = credits.get(j);
                    }
                }
            }
        }        
        lbIdBy.setText(String.valueOf( by.getId() ));
        lbNameBy.setText(by.getName());
        lbLastNameBy.setText(by.getLastName());
        lbCityBy.setText(by.getCity());
        lbAddressBy.setText(by.getAddress());
        lbPhoneBy.setText(by.getCellphoneNumber());
        printTableReferences();
        printTableCredit();
    }
    
    private void printTableReferences(){
        int cont = 0;
        for(int i = 0; i < references.size(); i++){
            if(references.get(i).getCreditCode() == credit.getCode()){
                for(int j = 0; j < people.size(); j++){
                    if(references.get(i).getId() == people.get(j).getId()){
                        if(cont == 0){
                            ref1 = people.get(j);
                            ref1.setKind("Ref_1");
                        } else {
                            ref2 = people.get(j);
                            ref2.setKind("Ref_2");
                        }
                        cont++;
                    }
                    if(credit.getIdCodeudor().equals( String.valueOf( people.get(j).getId() ) )){
                        cod = people.get(j);
                        cod.setKind("Cod");
                    }
                }
            }
        }
        referencesBuyer.add(cod);
        referencesBuyer.add(ref1);
        referencesBuyer.add(ref2);
        clIdRef.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Person, Integer> param) {
                return param.getValue().getValue().idProperty().asObject();
            }
        });
        clNamesRef.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().nameProperty();
            }
        } );
        clSurnamesRef.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().lastNameProperty();
            }
        });
        clRelationship.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().kindProperty();
            }
        });
        final TreeItem<Person> root = new RecursiveTreeItem<Person>(referencesBuyer,RecursiveTreeObject::getChildren);
        tbReferences.getColumns().setAll(clIdRef, clNamesRef, clSurnamesRef, clRelationship);
        tbReferences.setRoot(root);
        tbReferences.setShowRoot(false);
    }
    
    private void showReferencesInformation(int row){
        switch(row){
            case 0:
                lbIdRef.setText(String.valueOf( cod.getId() ));
                lbNamesRef.setText(cod.getName());
                lbSurnamesRef.setText(cod.getLastName());
                lbCityRef.setText(cod.getCity());
                lbAddressRef.setText(cod.getAddress());
                lbPhoneRef.setText(cod.getCellphoneNumber());
                break;
            case 1:
                lbIdRef.setText(String.valueOf( ref1.getId() ));
                lbNamesRef.setText(ref1.getName());
                lbSurnamesRef.setText(ref1.getLastName());
                lbCityRef.setText(ref1.getCity());
                lbAddressRef.setText(ref1.getAddress());
                lbPhoneRef.setText(ref1.getCellphoneNumber());
                break;
            case 2:
                lbIdRef.setText(String.valueOf( ref2.getId() ));
                lbNamesRef.setText(ref2.getName());
                lbSurnamesRef.setText(ref2.getLastName());
                lbCityRef.setText(ref2.getCity());
                lbAddressRef.setText(ref2.getAddress());
                lbPhoneRef.setText(ref2.getCellphoneNumber());
                break;
        }
    }
    
    private void printTableCredit(){
        if(!debts.isEmpty()){
            cleanDebts();
        }
        debts = FXCollections.observableArrayList( daoDebt.readDepts(String.valueOf( credit.getCode() )) );
        for(int i = 0; i < debts.size(); i++){
            debts.get(i).setNumber(i+1);
            debts.get(i).setResidue(mgCredit.calculateResidue(
                Integer.parseInt( credit.getNumberDepts() ), 
                Double.parseDouble( credit.getCuota() ), (i + 1)));
            debts.get(i).setValue(Integer.parseInt( credit.getCuota() ));
        }
        clNumber.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Pay, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Pay, Integer> param) {
                return param.getValue().getValue().numberProperty().asObject();
            }
        });
        clValue.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Pay, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Pay, Integer> param) {
                return param.getValue().getValue().valueProperty().asObject();
            }
        } );
        clResidue.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Pay, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Pay, String> param) {
                return param.getValue().getValue().residueProperty();
            }
        });
        clDate.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Pay, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Pay, String> param) {
                return param.getValue().getValue().dateProperty();
            }
        });
        final TreeItem<Pay> root = new RecursiveTreeItem<Pay>(debts, RecursiveTreeObject::getChildren);
        tbCredit.getColumns().setAll(clNumber, clValue, clResidue, clDate);
        tbCredit.setRoot(root);
        tbCredit.setShowRoot(false);
        showCreditInformation();
    }
    
    private void cleanDebts(){
         for(int i = 0; i < debts.size(); i++){
             debts.remove(debts.size() - (i+1));
         }
    }
    
    private void showCreditInformation(){
        lbValue.setText(credit.getValue());
        lbDate.setText(credit.getStartDate());
        lbNumberDebts.setText(credit.getNumberDepts());
        lbDebt.setText(credit.getCuota());
        int pagas = Integer.parseInt(credit.getNumberDepts()) - Integer.parseInt( credit.getLeftDebt() );
        lbPay.setText(String.valueOf(pagas));
        lbForPay.setText(credit.getLeftDebt());
        if(lbForPay.getText().equals("0")){
            btnDelete.setDisable(false);
        } else {
            btnDelete.setDisable(true);
        }
    }
    
    private void cleanFieldsForPay(){
        lbIdBy1.setText("");
        lbNameBy1.setText("");
        lbLastNameBy1.setText("");
        lbCityBy1.setText("");
        lbAddressBy1.setText("");
        lbPhoneBy1.setText("");
        lbValue1.setText("");
        lbDate1.setText("");
        lbNumberDebts1.setText("");
        lbDebt1.setText("");
        lbPay1.setText("");
        lbForPay1.setText("");
        btnAddDebt.setDisable(true);
    }
    
    private void cleanFields(){
        lbIdBy.setText("");
        lbNameBy.setText("");
        lbLastNameBy.setText("");
        lbCityBy.setText("");
        lbAddressBy.setText("");
        lbPhoneBy.setText("");
        lbIdRef.setText("");
        lbNamesRef.setText("");
        lbSurnamesRef.setText("");
        lbCityRef.setText("");
        lbAddressRef.setText("");
        lbPhoneRef.setText("");
        lbValue.setText("");
        lbDate.setText("");
        lbNumberDebts.setText("");
        lbDebt.setText("");
        lbPay.setText("");
        lbForPay.setText("");
        btnDelete.setDisable(true);
        cbNumberDebts.setDisable(true);
    }
    
    private void printTableForPay(){
        buyers.clear();
        credits = FXCollections.observableArrayList(daoCredit.read());
        debts = FXCollections.observableArrayList(daoDebt.read());
        people = FXCollections.observableArrayList(daoPerson.read());
        references = FXCollections.observableArrayList(daoReference.read());
        for(int i = 0; i < credits.size(); i++){
            for(int j = 0; j < people.size(); j++){
                if( Integer.parseInt(credits.get(i).getIdBuyer()) == people.get(j).getId() ){
                    if(!credits.get(i).getLeftDebt().equals("0")){
                        int id = people.get(j).getId();
                        String name = people.get(j).getName();
                        String lastName = people.get(j).getLastName();
                        buyers.add(new Person(id, name, lastName));
                    }
                }
            }
        }
        clId1.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TreeTableColumn.CellDataFeatures<Person, Integer> param) {
                return param.getValue().getValue().idProperty().asObject();
            }
        });
        clNames1.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().nameProperty();
            }
        } );
        clSurnames1.setCellValueFactory( new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().lastNameProperty();
            }
        });
        final TreeItem<Person> root = new RecursiveTreeItem<Person>(buyers,RecursiveTreeObject::getChildren);
        tbForPay.getColumns().setAll(clId1, clNames1, clSurnames1);
        tbForPay.setRoot(root);
        tbForPay.setShowRoot(false);
    }
    
    private void showInformationForPay(int row){
        for(int i = 0; i < people.size(); i++){
            if(buyers.get(row).getId() == people.get(i).getId()){
                by = people.get(i); 
                for(int j = 0; j < credits.size(); j++){
                    if( String.valueOf( by.getId() ).equals( credits.get(j).getIdBuyer() ) ){
                        credit = credits.get(j);
                    }
                }
            }
        }        
        lbIdBy1.setText(String.valueOf( by.getId() ));
        lbNameBy1.setText(by.getName());
        lbLastNameBy1.setText(by.getLastName());
        lbCityBy1.setText(by.getCity());
        lbAddressBy1.setText(by.getAddress());
        lbPhoneBy1.setText(by.getCellphoneNumber());
        //Show Information credit
        lbValue1.setText(credit.getValue());
        lbDate1.setText(credit.getStartDate());
        lbNumberDebts1.setText(credit.getNumberDepts());
        lbDebt1.setText(credit.getCuota());
        int pagas = Integer.parseInt(credit.getNumberDepts()) - Integer.parseInt( credit.getLeftDebt() );
        lbPay1.setText(String.valueOf(pagas));
        lbForPay1.setText(credit.getLeftDebt());
        btnAddDebt.setDisable(false);
    }
    
    private void addPay(){
        String residue = "";
        debt = new Pay((debts.size() + 1) , Integer.parseInt( credit.getCuota() ), 
                    sdf.format( calendar ),
                credit.getCode() );
        if(daoDebt.create(debt)){
            lbMessage.setVisible(true);
        }
        credit.setLeftDebt( String.valueOf( Integer.parseInt(credit.getLeftDebt()) - 1 ) );
        //Obtiene cuotas de acuerdo al crédito
        debts = FXCollections.observableArrayList( daoDebt.readDepts(String.valueOf(credit.getCode())) );
        //Calcula el residuo
        residue = mgCredit.calculateResidue(
                Integer.parseInt(credit.getNumberDepts()),
                Double.parseDouble(credit.getCuota()), debts.size());
        credit.setResidue(residue);
        daoCredit.update(credit);
        if (credit.getLeftDebt().equals("0")) {
            lbMessage2.setVisible(true);
        }
        printTableForPay();
        cleanFieldsForPay();
    }
    
    private void validateFields(){
        if(txIdBy.getText().length() == 0 || txNameBy.getText().length() == 0 ||
                txSurnamesBy.getText().length() == 0 || txCityBy.getText().length() == 0
                || txAddressBy.getText().length() == 0 || txPhoneBy.getText().length() == 0
                
                || txIdCod.getText().length() == 0 || txNameCod.getText().length() == 0 
                || txSurnamesCod.getText().length() == 0 || txCityCod.getText().length() == 0
                || txAddressCod.getText().length() == 0 || txPhoneCod.getText().length() == 0
                
                || txIdR1.getText().length() == 0 || txNameR1.getText().length() == 0 
                || txSurnamesR1.getText().length() == 0 || txCityR1.getText().length() == 0
                || txAddressR1.getText().length() == 0 || txPhoneR1.getText().length() == 0
                
                || txIdR2.getText().length() == 0 || txNameR2.getText().length() == 0
                || txSurnamesR2.getText().length() == 0 || txCityR2.getText().length() == 0
                || txAddressR2.getText().length() == 0 || txPhoneR2.getText().length() == 0
                || txValue.getText().length() == 0){
            message.setContentText("Hay campos vacíos");
            message.show();
        } else {
            addCredit();
        }
    }
    
    private void addCredit(){
        Person buyer = new Person(Integer.parseInt(txIdBy.getText()), txNameBy.getText(),
                txSurnamesBy.getText(), txCityBy.getText(),
                txAddressBy.getText(), txPhoneBy.getText());
        Person codeudor = new Person(Integer.parseInt(txIdCod.getText()), txNameCod.getText(),
                txSurnamesCod.getText(), txCityCod.getText(),
                txAddressCod.getText(), txPhoneCod.getText());  
        ref1 = new Person (Integer.parseInt(txIdR1.getText()), txNameR1.getText(),
                txSurnamesR1.getText(), txCityR1.getText(),
                txAddressR1.getText(), txPhoneR1.getText());  
        ref2 = new Person (Integer.parseInt(txIdR2.getText()), txNameR2.getText(),
                txSurnamesR2.getText(), txCityR2.getText(),
                txAddressR2.getText(), txPhoneR2.getText());  
        Credit creditAux = new Credit(txValue.getText(), sdf.format(calendar), 
                txIdBy.getText(), txIdCod.getText(), cbNumberDebts.getSelectionModel().getSelectedItem().toString(), 
                lbValueDebt.getText().substring(1));
        creditAux.setResidue(mgCredit.calculateResidue(
                Integer.parseInt( cbNumberDebts.getSelectionModel().getSelectedItem().toString() ), 
                Double.parseDouble(lbValueDebt.getText().substring(1)), 0));
        creditAux.setLeftDebt(cbNumberDebts.getSelectionModel().getSelectedItem().toString());
        if(daoPerson.create(buyer) && daoPerson.create(codeudor)){
            if(daoCredit.create(creditAux)){
                credits = FXCollections.observableArrayList(daoCredit.read());
                Reference ref1Aux = new Reference(Integer.parseInt(txIdR1.getText()), credits.get(credits.size()-1).getCode());
                Reference ref2Aux = new Reference(Integer.parseInt(txIdR2.getText()), credits.get(credits.size()-1).getCode());
                if(daoPerson.create(ref1) && daoPerson.create(ref2)){
                    if(daoReference.create(ref1Aux) && daoReference.create(ref2Aux)){
                        messageSuccessful.show();
                        windowAdd.setVisible(false);
                        windowAdd2.setVisible(false);
                        windowBegin.setVisible(true);
                        cleanFieldsAdd();
                    }
                } else {
                    message.setContentText("Revise los datos de las referencias");
                    message.show();
                    daoPerson.delete(txIdBy.getText());
                    daoPerson.delete(txIdCod.getText());
                    daoPerson.delete(txIdR1.getText());
                    daoPerson.delete(txIdR2.getText());
                }
            } else {
                message.setContentText("Revise los datos del crédito");
                message.show();
                daoPerson.delete(txIdBy.getText());
                daoPerson.delete(txIdCod.getText());
            }
        } else {
            message.setContentText("Revise los datos del comprador o codeudor");
            message.show();
            daoPerson.delete(txIdBy.getText());
            daoPerson.delete(txIdCod.getText());
        }
    }
    
    private void cleanFieldsAdd(){
        txIdBy.setText("");
        txNameBy.setText("");
        txSurnamesBy.setText("");
        txCityBy.setText("");
        txAddressBy.setText("");
        txPhoneBy.setText("");
        txIdCod.setText("");
        txNameCod.setText("");
        txSurnamesCod.setText("");
        txCityCod.setText("");
        txAddressCod.setText("");
        txPhoneCod.setText("");
        txIdR1.setText("");
        txNameR1.setText("");
        txSurnamesR1.setText("");
        txCityR1.setText("");
        txAddressR1.setText("");
        txPhoneR1.setText("");
        txIdR2.setText("");
        txNameR2.setText("");
        txSurnamesR2.setText("");
        txCityR2.setText("");
        txAddressR2.setText("");
        txPhoneR2.setText("");
        txValue.setText("");
        lbValueDebt.setText(String.valueOf(lbValueDebt.getText().charAt(0)));
        cbNumberDebts.setPromptText("");
    }
    
    @FXML
    private void viewItems(ActionEvent event){
        lbValueDebt.setText(String.valueOf(lbValueDebt.getText().charAt(0)));
        String cuota = mgCredit.calculateCuota(
            Integer.parseInt(txValue.getText()),
            Integer.parseInt(cbNumberDebts.getValue().toString()));
        lbValueDebt.setText(lbValueDebt.getText() + cuota);
    }
    
    @FXML
    private void minimizeAction(ActionEvent evt){
        Stage stage = (Stage)minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    private void handleButtonAction(MouseEvent event) {
        if(event.getTarget() == btnExit){
            System.exit(0);
        }
        if(event.getTarget() == btnAddCredit){
            windowAdd2.setVisible(true);
            windowAdd.setVisible(true);
            windowAddPay.setVisible(false);
            windowAddPay2.setVisible(false);
            findWindow.setVisible(false);
            windowBegin.setVisible(false);
        }
        if(event.getTarget() == btnFind){
            windowAdd2.setVisible(false);
            windowAdd.setVisible(false);
            windowAddPay.setVisible(false);
            windowAddPay2.setVisible(false);
            beginTableBuyer();
            findWindow.setVisible(true);
            windowBegin.setVisible(false);
        }
        if(event.getTarget() == btnAddPay){
            windowAdd2.setVisible(false);
            windowAdd.setVisible(false);
            findWindow.setVisible(false);
            windowBegin.setVisible(false);
            cleanFieldsForPay();
            printTableForPay();
            windowAddPay.setVisible(true);
            windowAddPay2.setVisible(true);
            btnAddDebt.setDisable(true);
        }
    }
    
}