/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Phim;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

public class PhimDao {
    public void insert(Phim model){
        String sql="INSERT INTO Phim (MaPhim, TenPhim, TheLoai, ThoiLuong, QuocGia, Hinh, GhiChu VALUES (?, ?, ?, ?, ?, ?, ?)";
            XJdbc.update(sql, 
                model.getMaPhim(),
                model.getTenPhim(),
                model.getTheLoai(),
                model.getThoiLuong(),
                model.getQuocGia(),
                model.getHinh(),
                model.getGhiChu());
    }
    
    public void update(Phim model){
        String sql="UPDATE Phim SET TenPhim=?, TheLoai=?, ThoiLuong=?, QuocGia=?, Hinh=?, GhiChu=? WHERE MaPhim=?";
        XJdbc.update(sql,
                model.getTenPhim(),
                model.getTheLoai(),
                model.getThoiLuong(),
                model.getQuocGia(),
                model.getHinh(),
                model.getGhiChu(),
                model.getMaPhim());
    }
    
    public void delete(String MaPhim){
        String sql="DELETE FROM Phim WHERE MaPhim=?";
        XJdbc.update(sql, MaPhim);
    }
    
    public List<Phim> selectAll(){
        String sql="SELECT * FROM Phim";
        return this.selectBySql(sql);
    }
    
    public Phim selectById(String maphim){
        String sql="SELECT * FROM Phim WHERE MaPhim=?";
        List<Phim> list = this.selectBySql(sql, maphim);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<Phim> selectBySql(String sql, Object...args){
        List<Phim> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    Phim entity=new Phim();
                    entity.setMaPhim(rs.getString("MaPhim"));
                    entity.setTenPhim(rs.getString("TenPhim"));
                    entity.setTheLoai(rs.getString("TheLoai"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setQuocGia(rs.getString("QuocGia"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }     
}
