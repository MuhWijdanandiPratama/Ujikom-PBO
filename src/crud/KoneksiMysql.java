/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author RPL 2
 */
public class KoneksiMysql {
  private String driverName = "com.mysql.jdbc.Driver";
  private String jdbc = "jdbc:mysql://";
  private String host = "localhost:";
  private String port = "3306/";
  private String database = "ukk";
  private String url = jdbc+host+port+database;
  private String username = "root";
  private String password = "";
  private Connection connect;

  public Connection getKoneksi()throws SQLException{
    if(connect == null){
      try{
        Class.forName(driverName);
        System.out.println("Driver Ditemukan!");
        try{
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Database Ditemukan!");
        }catch(SQLException e){
            System.out.println("Database Tidak Ditemukan!"+e);
            System.exit(0);
        }
      }catch(ClassNotFoundException cnf){
        System.out.println("Driver Tidak Ditemukan! :"+cnf);
        System.exit(0);
      }
    }
    return connect;
  }
}
