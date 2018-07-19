package Connection;

import Logic.Credit;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class DaoCredit {
    
    ConnectionDB cnt;
    SimpleDateFormat formatter;
    
    public DaoCredit(){
        cnt = new ConnectionDB();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public boolean create(Credit credit){
        try {
            String sql = "insert into credit(value, date, idBy, "
                    + "idCod, debts, residue, debt, lack) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ps.setString(1, credit.getValue());
            ps.setDate(2, Date.valueOf(credit.getStartDate()));
            ps.setInt(3, Integer.parseInt(credit.getIdBuyer()));
            ps.setInt(4, Integer.parseInt(credit.getIdCodeudor()));
            ps.setInt(5, Integer.parseInt(credit.getNumberDepts()));
            ps.setString(6, credit.getResidue());
            ps.setInt(7, Integer.parseInt(credit.getCuota()));
            ps.setInt(8, Integer.parseInt(credit.getLeftDebt()));
            ps.execute();
            ps.close();
            ps = null;
            cnt.disconnect();
            return true;
        } catch (SQLException ex) {
            System.out.println("CREDIT WAS NOT INSERTED");
            Logger.getLogger(DaoCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<Credit> read() {
        ArrayList<Credit> credits = new ArrayList<>();
        try {
            String sql = "select * from credit";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Credit credit = new Credit();
                credit.setCode(result.getInt("code"));
                credit.setValue(result.getString("value") );
                credit.setStartDate(formatter.format(result.getDate("date")));
                credit.setIdBuyer(String.valueOf(result.getInt("idBy")));
                credit.setIdCodeudor(String.valueOf(result.getInt("idCod")));
                credit.setNumberDepts(String.valueOf(result.getInt("debts")));
                credit.setResidue(result.getString("residue"));
                credit.setCuota(String.valueOf(result.getInt("debt")));
                credit.setLeftDebt(String.valueOf( result.getInt("lack") ));
                credits.add(credit);
            }
            ps.close();
            ps = null;
            cnt.disconnect();
        } catch (SQLException ex) {
            System.out.println("Failed read method");
        }
        return credits;
    }
    
    public boolean update(Credit credit){
        try {
            String sql = "update credit set residue=? where code=?";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ps.setString(1, credit.getResidue());
            ps.setInt(2, credit.getCode());
            ps.execute();
            ps.close();
            ps = null;
            cnt.disconnect();
            sql = "update credit set lack=? where code=?";
            ps = cnt.connect().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt( credit.getLeftDebt() ));
            ps.setInt(2, credit.getCode());            
            ps.execute();
            ps.close();
            ps = null;
            cnt.disconnect();
            return true;
        } catch (SQLException ex) {
            System.out.println("IT WAS NOT INSERTED");
        }
        return false;
    }
    
    public void delete(String code) {
        try {
            String sql = "delete from credit where code=?";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(code));
            ps.execute();
            ps.close();
            ps = null;
            cnt.disconnect();
        } catch (SQLException ex) {
            System.out.println("Failed read method");
        }
    }
    
}
