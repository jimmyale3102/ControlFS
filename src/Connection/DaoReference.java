package Connection;

import Logic.Reference;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class DaoReference {
    ConnectionDB cnt;
    
    public DaoReference(){
        cnt = new ConnectionDB();
    }
    
    public boolean create(Reference reference){
        try {
            String sql = "insert into reference(id, creditCode) "
                    + "values(?,?)";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ps.setInt(1, reference.getId());
            ps.setInt(2, reference.getCreditCode());
            ps.execute();
            ps.close();;
            ps = null;
            cnt.disconnect();
            return true;
        } catch (SQLException ex) {
            System.out.println("REFERENCE WAS NOT INSERTED"); 
        }
        return false;
    }
    
     public ArrayList<Reference> read() {
        ArrayList<Reference> references = new ArrayList<>();
        try {
            String sql = "select * from reference";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Reference ref = new Reference();
                ref.setId(result.getInt("id"));
                ref.setCreditCode(result.getInt("creditCode"));
                references.add(ref);
            }
            ps.close();
            ps = null;
            cnt.disconnect();
        } catch (SQLException ex) {
            System.out.println("Failed read method");
        }
        return references;
    }
     
    public void delete(String id) {
        try {
            String sql = "delete from reference where id=?";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.execute();
            ps.close();
            ps = null;
            cnt.disconnect();
        } catch (SQLException ex) {
            System.out.println("Failed read method");
        }
    }
}
