/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.PhongChieu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

public class PhongChieuDao {
    public void insert(PhongChieu model){
        String sql="INSERT INTO PhongChieu (MaPhong, SoLuongGhe, DienTich, MayChieu, AmThanh, TinhTrang, GhiChu VALUES (?, ?, ?)";
            XJdbc.update(sql, 
                model.getMaPhong(),
                model.getSoLuongGhe(),
                model.getDienTich(),
                model.getMayChieu(),
                model.getAmThanh(),
                model.getTinhTrang(),
                model.getGhiChu());
    }
    
    public void update(PhongChieu model){
        String sql="UPDATE PhongChieu SET SoLuongGhe=?, DienTich=?, MayChieu=?, AmThanh=?, TinhTrang=?, GhiChu=? WHERE MaPhong=?";
        XJdbc.update(sql,
                model.getSoLuongGhe(),
                model.getDienTich(),
                model.getMayChieu(),
                model.getAmThanh(),
                model.getTinhTrang(),
                model.getGhiChu(),
                model.getMaPhong());
    }
    
    public void delete(String MaPhong){
        String sql="DELETE FROM PhongChieu WHERE MaPhong=?";
        XJdbc.update(sql, MaPhong);
    }
    
    public List<PhongChieu> selectAll(){
        String sql="SELECT * FROM PhongChieu";
        return this.selectBySql(sql);
    }
    
    public PhongChieu selectById(String maphong){
        String sql="SELECT * FROM PhongChieu WHERE MaPhong=?";
        List<PhongChieu> list = this.selectBySql(sql, maphong);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<PhongChieu> selectBySql(String sql, Object...args){
        List<PhongChieu> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    PhongChieu entity=new PhongChieu();
                    entity.setMaPhong(rs.getString("MaPhong"));
                    entity.setSoLuongGhe(rs.getInt("SoLuongGhe"));
                    entity.setDienTich(rs.getString("DienTich"));
                    entity.setMayChieu(rs.getString("MayChieu"));
                    entity.setAmThanh(rs.getString("AmThanh"));
                    entity.setTinhTrang(rs.getString("TinhTrang"));
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
    public List<PhongChieu> selectByKeyword(String keyword){
        String sql="SELECT * FROM PhongChieu WHERE MaPhong LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
}
