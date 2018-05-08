/**
 * 
 */
/**
 * @author SourceCode
 *
 */
package application.database;

import java.sql.*;

import javax.swing.JOptionPane;

public class DatabaseHandler {
    private static DatabaseHandler handler;
	private static Connection conn = null;
	private static Statement stmt = null;
	
	
	public  DatabaseHandler() {
		creatconnection();
		setupBookTable();
	}
	public void creatconnection(){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books1","root","");
			stmt= conn.createStatement();
		}catch (Exception e) {
			System.out.println("Error connection" +e);
		}
		
	}//end creat connection
	
	void setupBookTable() {
		
		String TABLE_NAME = "BOOK";
		
		try {
			stmt = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			if(tables.next()) {
				System.out.println("TABLE"+TABLE_NAME+"Already exiset .Read For go");
			} else {
				stmt.execute("create table "+TABLE_NAME +
						"(id VARCHAR(200), " +
						"author VARCHAR(100), " +
						"title VARCHAR(200), " +
						"publisher CHAR(200), " +
						"isAvail BOOLEAN DEFAULT TRUE, " +
						"primary key(id))"
						);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage()+"......Setup DataBase");
		}//end try stuptable
		finally {
			
		}//end finally
		
		
	}//end setupBookTable

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }// end result set

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        } finally {
        }
    }// end execute
    
 
}//end FirstExample