/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

public class Ve {
    private String maVe;
    private String maNV;
    private Date ngayLap;
    private String maGhe;
    private String maPhong;
    private String maPhim;

    public Ve(String maVe, String maNV, Date ngayLap, String maGhe, String maPhong, String maPhim) {
        this.maVe = maVe;
        this.maNV = maNV;
        this.ngayLap = ngayLap;
        this.maGhe = maGhe;
        this.maPhong = maPhong;
        this.maPhim = maPhim;
    }

    public Ve() {
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }
    
    
}
