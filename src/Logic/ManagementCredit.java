package Logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//import Serialization.FileCreditSerializable;
//import Serialization.FileSerializable;

public class ManagementCredit {//implements Serializable{

    private ArrayList<Reference> references;
    private ArrayList<Credit> credits;

    public ManagementCredit() {
        references = new ArrayList<>();
        credits = new ArrayList<>();
    }
	
    public String calculateCuota(int value, int numberCuota){
	double cuota = 0;
	switch(numberCuota){
            case 6: 
		cuota = value/numberCuota;
		cuota += value*0.03581933333;
                break;
            case 12:
                cuota = value/numberCuota;
		cuota += value*0.04414366667;
		break;
            case 15:
		cuota = value/numberCuota;
		cuota += value*0.04370233333;
		break;
            case 18:
                cuota = value/numberCuota;
		cuota += value*0.04325544444;
		break;
            case 21:
		cuota = value/numberCuota;
		cuota += value*0.04292295238;
		break;
            case 24:
		cuota = value/numberCuota;
		cuota += value*0.04268533333;
		break;
	}
	return String.valueOf( (int)(cuota) );
    }		
    
    public String calculateResidue(int numberDepts, double cuota, int debts){
        return String.valueOf( (numberDepts - debts) * cuota );
    }
}
