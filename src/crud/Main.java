/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RPL 2
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      FormSiswa form = new FormSiswa();
      form.setVisible(true);
    } catch (SQLException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
    
}