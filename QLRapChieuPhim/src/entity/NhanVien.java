/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author quoct
 */
public class NhanVien {
    private String MaUser;
    private String HoTen;
    private String SoDT;
    private String Email;
    private boolean GioiTinh;
    private Date NgaySinh;
    private Date NgayVaoLam;
    private int ChucVu;
    private String Hinh;
    private String GhiChu;
    private String MatKhau;
    private int Luong;

    public NhanVien() {
    }

    public NhanVien(String MaUser, String HoTen, String SoDT, String Email, boolean GioiTinh, Date NgaySinh, Date NgayVaoLam, int ChucVu, String Hinh, String GhiChu, String MatKhau, int Luong) {
        this.MaUser = MaUser;
        this.HoTen = HoTen;
        this.SoDT = SoDT;
        this.Email = Email;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.NgayVaoLam = NgayVaoLam;
        this.ChucVu = ChucVu;
        this.Hinh = Hinh;
        this.GhiChu = GhiChu;
        this.MatKhau = MatKhau;
        this.Luong = Luong;
    }

    public String getMaUser() {
        return MaUser;
    }

    public void setMaUser(String MaUser) {
        this.MaUser = MaUser;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String SoDT) {
        this.SoDT = SoDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    

    public boolean getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public Date getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(Date NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public int getChucVu() {
        return ChucVu;
    }

    public void setChucVu(int ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getLuong() {
        return Luong;
    }

    public void setLuong(int Luong) {
        this.Luong = Luong;
    }

   


}
