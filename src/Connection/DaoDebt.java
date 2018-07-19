package Connection;

import Logic.Credit;
import Logic.Pay;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class DaoDebt {
    
    ConnectionDB cnt;
    SimpleDateFormat formatter;
    
    public DaoDebt(){
        cnt = new ConnectionDB();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    public boolean create(Pay debt){
        String sql = "insert into debt(number, value, date, "
                + "creditCode) values(?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = cnt.connect().prepareStatement(sql);
            ps.setInt( 1, debt.getNumber() );
            ps.setInt( 2,  debt.getValue());
            ps.setDate( 3, Date.valueOf(debt.getDate()) );
            ps.setInt( 4, debt.getCreditCode());
            ps.execute();
            ps.close();
            ps = null;
            cnt.disconnect();
            return true;
        } catch (SQLException ex) {
            System.out.println("DEBT WAS NOT INSERTED");
        }
        return false;
    }
    
    public ArrayList<Pay> read(){
        ArrayList<Pay> depts = new ArrayList<>();
        try {
            String sql = "select * from debt";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Pay dept = new Pay();
                dept.setNumber(result.getInt("number"));
                dept.setDate( formatter.format(result.getDate("date")) );
                dept.setCreditCode(result.getInt("creditCode")) ;
                depts.add(dept);
            }
            ps.close();
            ps = null;
            cnt.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DaoDebt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }
    
    public ArrayList<Pay> readDepts(String creditCode){
        ArrayList<Pay> depts = new ArrayList<>();
        try {
            String sql = "select * from debt where creditCode='" + creditCode + "'";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while(result.next()){
                Pay dept = new Pay();
                dept.setNumber(result.getInt("number"));
                dept.setDate( formatter.format(result.getDate("date")) );
                dept.setCreditCode( result.getInt("creditCode"));
                depts.add(dept);
            }
            ps.close();
            ps = null;
            cnt.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DaoDebt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depts;
    }
    
    public void delete(String creditCode) {
        try {
            String sql = "delete from debt where creditCode=?";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(creditCode));
            ps.execute();
            ps.close();
            ps = null;
            cnt.disconnect();
        } catch (SQLException ex) {
            System.out.println("Failed read method");
        }
    }
    
}
