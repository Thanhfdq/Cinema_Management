/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Ve;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

public class VeDao {
    public void insert(Ve model){
        String sql="INSERT INTO Ve (MaVe, MaNV, NgayLap, MaGhe, MaPhong, MaPhim VALUES (?, ?, ?, ?, ?, ?)";
            XJdbc.update(sql, 
                model.getMaVe(),
                model.getMaNV(),
                model.getNgayLap(),
                model.getMaGhe(),
                model.getMaPhong(),
                model.getMaPhim());
    }
    
    public void update(Ve model){
        String sql="UPDATE Ve SET MaNV, NgayLap, MaGhe, MaPhong, MaPhim WHERE MaVe=?";
        XJdbc.update(sql,
                model.getMaNV(),
                model.getNgayLap(),
                model.getMaGhe(),
                model.getMaPhong(),
                model.getMaPhim(),
                model.getMaVe());
    }
    
    public void delete(String MaVe){
        String sql="DELETE FROM Ve WHERE MaVe=?";
        XJdbc.update(sql, MaVe);
    }
    
    public List<Ve> selectAll(){
        String sql="SELECT * FROM Ve";
        return this.selectBySql(sql);
    }
    
    public Ve selectById(String mave){
        String sql="SELECT * FROM Ve WHERE MaVe=?";
        List<Ve> list = this.selectBySql(sql, mave);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<Ve> selectBySql(String sql, Object...args){
        List<Ve> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    Ve entity=new Ve();
                    entity.setMaVe(rs.getString("MaVe"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayLap(rs.getDate("NgayLap"));
                    entity.setMaGhe(rs.getString("MaGhe"));
                    entity.setMaPhong(rs.getString("MaPhong"));
                    entity.setMaPhim(rs.getString("MaPhim"));
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
