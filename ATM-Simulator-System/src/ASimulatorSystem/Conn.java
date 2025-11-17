package ASimulatorSystem;

import java.sql.*;

import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Conn{
    Connection c;
    Statement s;
    public Conn(){
        Properties props = new Properties();
        String url = "jdbc:mysql:///atm_simulator";
        String user = "root";
        String password = "passward";

        // Try to load configuration from config.properties in project root
        try (InputStream in = new FileInputStream("config.properties")){
            props.load(in);
            url = props.getProperty("db.url", url);
            user = props.getProperty("db.user", user);
            password = props.getProperty("db.password", password);
        }catch(IOException ioe){
            // No config file found — fall back to defaults
        }

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url, user, password);
            s = c.createStatement();
        }catch(Exception e){
            System.out.println("Database connection error: " + e);
            try {
                javax.swing.SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        javax.swing.JOptionPane.showMessageDialog(null,
                            "Could not connect to database.\nPlease check config.properties and ensure MySQL is running.\nError: " + e.getMessage(),
                            "Database Connection Error",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                });
            }catch(Exception ex){
                // ignore - not running in GUI context
            }
        }
    }
}
