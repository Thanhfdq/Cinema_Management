/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class HoaDonCT {
    private Integer maHD;
    private Integer maMon;
    private Integer soLuong;

    public HoaDonCT() {
   
}

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public Integer getMaMon() {
        return maMon;
    }

    public void setMaMon(Integer maMon) {
        this.maMon = maMon;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public HoaDonCT(Integer maHD, Integer maMon, Integer soLuong) {
        this.maHD = maHD;
        this.maMon = maMon;
        this.soLuong = soLuong;
    }
}
