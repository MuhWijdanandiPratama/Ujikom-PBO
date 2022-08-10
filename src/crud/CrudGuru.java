/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author RPL 2
 */
public class CrudGuru {
  private String nip,nama,tgl_lahir,alamat,mapel;
  private String CRUDquery;
  private Connection CRUDKoneksi;
  private PreparedStatement CRUDpsmt;
  private Statement CRUDstat;
  private ResultSet CRUDhasil;

  public CrudGuru(){
    try{
      KoneksiMysql Connection = new KoneksiMysql();
      CRUDKoneksi = Connection.getKoneksi();
    }catch(Exception e){
      System.out.println("Koneksi Gagal! : "+e);
    } 
  }

  void setNip(String nip){
    this.nip = nip;
  }
  String getNip(){
    return nip;
  }

  void setNama(String nama){
    this.nama = nama;
  }
  String getNama(){
    return nama;
  }

  void setTglLahir(String tgl_lahir){
    this.tgl_lahir = tgl_lahir;
  }
  String getTglLahir(){
    return tgl_lahir;
  }

  void setAlamat(String alamat){
    this.alamat = alamat;
  }
  String getAlamat(){
    return alamat;
  }

  void setMapel(String mapel){
    this.mapel = mapel;
  }
  String getMapel(){
    return mapel;
  }

  public ResultSet tampil_data(){
    CRUDquery ="SELECT * FROM guru";
    try{
      CRUDstat = CRUDKoneksi.createStatement();
      CRUDhasil = CRUDstat.executeQuery(CRUDquery);
    }catch(Exception e){
    }
    return CRUDhasil;
  }

  public void tambah_data(String nip, String nama, String tgl_lahir, String alamat, String mapel){
    CRUDquery ="INSERT INTO guru values(?,?,?,?,?)";
    try{
      CRUDpsmt = CRUDKoneksi.prepareStatement(CRUDquery);
      CRUDpsmt.setString(1, nip);
      CRUDpsmt.setString(2, nama);
      CRUDpsmt.setString(3, tgl_lahir);
      CRUDpsmt.setString(4, alamat);
      CRUDpsmt.setString(5, mapel);
      CRUDpsmt.executeUpdate();
      CRUDpsmt.close();
    }catch(Exception e){
    }
  }

  public void ubah_data(String nip, String nama, String tgl_lahir, String alamat, String mapel){
    CRUDquery = "UPDATE guru SET nip = ?, nama = ?, tgl_lahir = ?, alamat = ?, mapel = ? WHERE nip = ?";
    try{
      CRUDpsmt = CRUDKoneksi.prepareStatement(CRUDquery);
      CRUDpsmt.setString(1, nip);
      CRUDpsmt.setString(2, nama);
      CRUDpsmt.setString(3, tgl_lahir);
      CRUDpsmt.setString(4, alamat);
      CRUDpsmt.setString(5, mapel);
      CRUDpsmt.setString(6, nip);
      CRUDpsmt.executeUpdate();
      CRUDpsmt.close();
    }catch(SQLException e){
      System.out.println(e);
    }
  }

  public void hapus_data(String nip){
    CRUDquery ="DELETE FROM guru WHERE nip = ?";
    try{
      CRUDpsmt = CRUDKoneksi.prepareStatement(CRUDquery);
      CRUDpsmt.setString(1, nip);
      CRUDpsmt.executeUpdate();
      CRUDpsmt.close();
    }catch(Exception e){
    }
  }

  public ResultSet cari_data(String nip){
    CRUDquery ="SELECT * FROM guru WHERE nip = ?";
    try{
      CRUDpsmt = CRUDKoneksi.prepareStatement(CRUDquery);
      CRUDpsmt.setString(1, nip);
      CRUDhasil = CRUDpsmt.executeQuery();
    }catch(Exception e){
    }
    return CRUDhasil;
  }
}
