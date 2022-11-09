/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class PhongChieu {
    private String maPhong;
    private int soLuongGhe;
    private String dienTich;
    private String mayChieu;
    private String amThanh;
    private String tinhTrang;
    private String ghiChu;

    public PhongChieu() {
    }

    public PhongChieu(String maPhong, int soLuongGhe, String dienTich, String mayChieu, String amThanh, String tinhTrang, String ghiChu) {
        this.maPhong = maPhong;
        this.soLuongGhe = soLuongGhe;
        this.dienTich = dienTich;
        this.mayChieu = mayChieu;
        this.amThanh = amThanh;
        this.tinhTrang = tinhTrang;
        this.ghiChu = ghiChu;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public int getSoLuongGhe() {
        return soLuongGhe;
    }

    public void setSoLuongGhe(int soLuongGhe) {
        this.soLuongGhe = soLuongGhe;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public String getMayChieu() {
        return mayChieu;
    }

    public void setMayChieu(String mayChieu) {
        this.mayChieu = mayChieu;
    }

    public String getAmThanh() {
        return amThanh;
    }

    public void setAmThanh(String amThanh) {
        this.amThanh = amThanh;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
   
    
}


