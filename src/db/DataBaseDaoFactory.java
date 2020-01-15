package db;

import exceptions.ValidacaoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseDaoFactory {

    public static Connection getConnection() throws SQLException {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("************************************************");
            System.out.println("Erro Class Nao Encontrada - " + e);
            Logger.getLogger(DataBaseDaoFactory.class.getName()).log(Level.SEVERE, null, e);
        } catch (InstantiationException ex) {
            System.out.println("------------------------------------------------");
            System.out.println("Erro Class Nao Encontrada - " + ex);
            Logger.getLogger(DataBaseDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            System.out.println("================================================");
            System.out.println("Erro Class Nao Encontrada - " + ex);
            Logger.getLogger(DataBaseDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        com.mysql.jdbc.Connection conn = null;

        try {
            //String urlPg = "jdbc:mysql://localhost:3306/apcontas";
            String urlPg = "jdbc:mysql://" + getLinhasIP() + ":3306/integracao_bd";
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(urlPg, "root", "root");
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Erro Servidor MySQL." + ex);
        }
        return conn;
    }
    
    public static String getLinhasIP()  {
                    
        FileInputStream stream;
        String ip;
        try {
            stream = new FileInputStream(getDiretorioAtual() + "\\ipbd.ini");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            ip = br.readLine().trim();
            if (ip.equals("")) {
                ip="localhost";
            }
            return  ip;
        } catch (IOException | NullPointerException | ValidacaoException ex) {
            return "localhost";
        }
    }

    public static String getDiretorioAtual() throws ValidacaoException {
        File dir1 = new File(".");
        try {
            return dir1.getCanonicalPath();
        } catch (IOException e) {
            throw new ValidacaoException(e);
        }
    }
}
