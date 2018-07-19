package Connection;

import Logic.Person;
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
public class DaoPerson {

    ConnectionDB cnt;

    public DaoPerson() {
        cnt = new ConnectionDB();
    }

    public boolean create(Person person) {
        try {
            String sql = "insert into person(id, names, surnames, city, "
                    + "address, cellphone) values(?,?,?,?,?,?)";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ps.setInt(1, person.getId());
            ps.setString(2, person.getName());
            ps.setString(3, person.getLastName());
            ps.setString(4, person.getCity());
            ps.setString(5, person.getAddress());
            ps.setString(6, person.getCellphoneNumber());
            ps.execute();
            ps.close();
            ps = null;
            cnt.disconnect();
            return true;
        } catch (SQLException ex) {
            System.out.println("PERSON WAS NOT INSERTED");
            return false;
        }
    }

    public ArrayList<Person> read() {
        ArrayList<Person> people = new ArrayList<>();
        try {
            String sql = "select * from person";
            PreparedStatement ps = cnt.connect().prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                Person person = new Person();
                person.setId(result.getInt("id"));
                person.setName(result.getString("names"));
                person.setLastName(result.getString("surnames"));
                person.setCity(result.getString("city"));
                person.setAddress(result.getString("address"));
                person.setCellphoneNumber(result.getString("cellphone"));
                people.add(person);
            }
            ps.close();
            ps = null;
            cnt.disconnect();
        } catch (SQLException ex) {
            System.out.println("Failed read method");
        }
        return people;
    }
    
    public void delete(String id) {
        try {
            String sql = "delete from person where id=?";
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
