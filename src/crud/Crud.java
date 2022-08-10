/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author RPL 2
 */
public class Crud {
  private String nis,nama,tgl_lahir,alamat,nilai_mm,nilai_ipa,nilai_bi,nilai_bing;
  private String CRUDquery;
  private Connection CRUDKoneksi;
  private PreparedStatement CRUDpsmt;
  private Statement CRUDstat;
  private ResultSet CRUDhasil;
  public Crud(){
    try{
        KoneksiMysql Connection = new KoneksiMysql();
        CRUDKoneksi = Connection.getKoneksi();
    }catch(SQLException e){
        System.out.println("Koneksi Gagal! : "+e);
    } 
  }
    
  void setNis(String nis){
    this.nis = nis;
  }
  String getNis(){
    return nis;
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

  void setMm(String nilai_mm){
    this.nilai_mm = nilai_mm;
  }
  String getMm(){
    return nilai_mm;
  }

  void setIpa(String nilai_ipa){
    this.nilai_ipa = nilai_ipa;
  }
  String getIpa(){
    return nilai_ipa;
  }

  void setBi(String nilai_bi){
    this.nilai_bi = nilai_bi;
  }
  String getBi(){
    return nilai_bi;
  }

  void setBing(String nilai_bing){
    this.nilai_bing = nilai_bing;
  }
  String getBing(){
    return nilai_bing;
  }

  public ResultSet tampil_data(){
    CRUDquery ="SELECT * FROM siswa";
    try{
      CRUDstat = CRUDKoneksi.createStatement();
      CRUDhasil = CRUDstat.executeQuery(CRUDquery);
    }catch(Exception e){
    }
    return CRUDhasil;
  }

  public void tambah_data(String nis, String nama, String tgl_lahir, String alamat, String nilai_mm, String nilai_ipa, String nilai_bi, String nilai_bing){
    CRUDquery ="INSERT INTO siswa values(?,?,?,?,?,?,?,?)";
    try{
      CRUDpsmt = CRUDKoneksi.prepareStatement(CRUDquery);
      CRUDpsmt.setString(1, nis);
      CRUDpsmt.setString(2, nama);
      CRUDpsmt.setString(3, tgl_lahir);
      CRUDpsmt.setString(4, alamat);
      CRUDpsmt.setString(5, nilai_mm);
      CRUDpsmt.setString(6, nilai_ipa);
      CRUDpsmt.setString(7, nilai_bi);
      CRUDpsmt.setString(8, nilai_bing);
      CRUDpsmt.executeUpdate();
      CRUDpsmt.close();
    }catch(SQLException e){
      System.out.println(e);
    }
  }
    
  public void ubah_data(String nis, String nama, String tgl_lahir, String alamat, String nilai_mm, String nilai_ipa, String nilai_bi, String nilai_bing){
    CRUDquery ="UPDATE siswa SET nis = ?, nama = ?, tgl_lahir = ?, alamat = ?, nilai_mm = ?, nilai_ipa = ?, nilai_bi = ?, nilai_bing = ? WHERE nis = ?";
    try{
      CRUDpsmt = CRUDKoneksi.prepareStatement(CRUDquery);
      CRUDpsmt.setString(1, nis);
      CRUDpsmt.setString(2, nama);
      CRUDpsmt.setString(3, tgl_lahir);
      CRUDpsmt.setString(4, alamat);
      CRUDpsmt.setString(5, nilai_mm);
      CRUDpsmt.setString(6, nilai_ipa);
      CRUDpsmt.setString(7, nilai_bi);
      CRUDpsmt.setString(8, nilai_bing);
      CRUDpsmt.setString(9, nis);
      CRUDpsmt.executeUpdate();
      CRUDpsmt.close();
    }catch(SQLException e){
      System.out.println(e);
    }
  }

  public void hapus_data(String nis){
    CRUDquery ="DELETE FROM siswa WHERE nis = ?";
    try{
      CRUDpsmt = CRUDKoneksi.prepareStatement(CRUDquery);
      CRUDpsmt.setString(1, nis);
      CRUDpsmt.executeUpdate();
      CRUDpsmt.close();
    }catch(SQLException e){
      System.out.println(e);
    }
  }

  public ResultSet cari_data(String nis){
    CRUDquery ="SELECT * FROM siswa WHERE nis = ?";
    try{
      CRUDpsmt = CRUDKoneksi.prepareStatement(CRUDquery);
      CRUDpsmt.setString(1, nis);
      CRUDhasil = CRUDpsmt.executeQuery();
    }catch(SQLException e){
      System.out.println(e);
    }
    return CRUDhasil;
  }

  public ResultSet rata_kelas(){
    CRUDquery ="SELECT AVG(nilai_mm+nilai_ipa+nilai_bi+nilai_bing) as rata_kelas FROM siswa";
    try{
      CRUDstat = CRUDKoneksi.createStatement();
      CRUDhasil = CRUDstat.executeQuery(CRUDquery);
    }catch(SQLException e){
      System.out.println(e);
    }
    return CRUDhasil;
  }

  public ResultSet rata_mapel(){
    CRUDquery ="SELECT AVG(nilai_mm) as rata_mm, AVG(nilai_ipa) as rata_ipa,AVG(nilai_bi) as rata_bi, AVG(nilai_bing) as rata_bing FROM siswa";
    try{
      CRUDstat = CRUDKoneksi.createStatement();
      CRUDhasil = CRUDstat.executeQuery(CRUDquery);
    }catch(SQLException e){
      System.out.println(e);
    }
    return CRUDhasil;
  }

  public ResultSet peringkat_siswa(){
    CRUDquery ="SELECT nama, (nilai_mm+nilai_ipa+nilai_bi+nilai_bing) as jumlah_nilai, nilai_mm, nilai_ipa, nilai_bi, nilai_bing FROM siswa ORDER by jumlah_nilai DESC";
    try{
      CRUDstat = CRUDKoneksi.createStatement();
      CRUDhasil = CRUDstat.executeQuery(CRUDquery);
    }catch(SQLException e){
      System.out.println(e);
    }
    return CRUDhasil;
  }
    
}
