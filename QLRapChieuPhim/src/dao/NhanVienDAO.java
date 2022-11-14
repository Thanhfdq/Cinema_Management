/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.NhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

public class NhanVienDAO {
        public void insert(NhanVien model){
        String sql="INSERT INTO NhanVien (MaNV, HoTen, GioiTinh, NgaySinh, NgayVaoLam, ChucVu, Luong, Hinh, GhiChu, MatKhau) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            XJdbc.update(sql, 
                model.getMaNV(), 
                model.getHoTen(), 
                model.getGioiTinh(), 
                model.getNgaySinh(),
                model.getNgayVaoLam(),
                model.getChucVu(),
                model.getLuong(),
                model.getHinh(),
                model.getGhiChu(),
                model.getMatKhau());
    }
    
    public void update(NhanVien model){
        String sql="UPDATE NhanVien SET HoTen-?, GioiTinh=?, NgaySinh=?, NgayVaoLam=?, ChucVu=?, Luong=?, Hinh=?, GhiChu=?, MatKhau=? WHERE MaNV=?";
        XJdbc.update(sql, 
                model.getHoTen(), 
                model.getGioiTinh(), 
                model.getNgaySinh(),
                model.getNgayVaoLam(),
                model.getChucVu(),
                model.getLuong(),
                model.getHinh(),
                model.getGhiChu(),
                model.getMatKhau(),
                model.getMaNV());
    }
    
    public void delete(String MaNV){
        String sql="DELETE FROM NhanVien WHERE MaNV=?";
        XJdbc.update(sql, MaNV);
    }
    
    public List<NhanVien> selectAll(){
        String sql="SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }
    
    public NhanVien selectById(String manv){
        String sql="SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = this.selectBySql(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<NhanVien> selectBySql(String sql, Object...args){
        List<NhanVien> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    NhanVien entity=new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                    entity.setChucVu(rs.getString("ChucVu"));
                    entity.setLuong(rs.getInt("Luong"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMatKhau(rs.getString("MatKhau"));
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
    public List<NhanVien> selectByKeyword(String keyword){
        String sql="SELECT * FROM nhanvien WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
    
    public List<NhanVien> selectByFilter(int c, String chucvu, Object... args) {
        String sql;
        switch (c) {
            //full
            case 1:
                sql = "select * from NHANVIEN where "
                        + "chucvu like ? "
                        + "and (gioitinh like ?  or gioitinh like ?) "
                        + "and year(ngaysinh) = ? "
                        + "and year(ngayvaolam) = ? "
                        + "and luong >= ? "
                        + "and luong < ?+1000000";
                break;
            //all nam vao lam
            case 2:
                sql = "select * from NHANVIEN where "
                        + "chucvu like ? "
                        + "and (gioitinh like ?  or gioitinh like ?) "
                        + "and year(ngaysinh) = ? "
                        + "and luong >= ? "
                        + "and luong < ?+1000000";
                break;
            //all nam sinh
            case 3:
                sql = "select * from NHANVIEN where "
                        + "chucvu like ? "
                        + "and (gioitinh like ?  or gioitinh like ?) "
                        + "and year(ngayvaolam) = ? "
                        + "and luong >= ? "
                        + "and luong < ?+1000000";
                break;
            //all nam
            case 4:
                sql = "select * from NHANVIEN where "
                        + "chucvu like ? "
                        + "and (gioitinh like ?  or gioitinh like ?) "
                        + "and luong >= ? "
                        + "and luong < ? +1000000";
                break;
            //all luong
            case 5:
                sql = "select * from NHANVIEN where "
                        + "chucvu like ? "
                        + "and (gioitinh like ?  or gioitinh like ?) "
                        + "and year(ngaysinh) = ? "
                        + "and year(ngayvaolam) = ? ";
                break;
            //all nam vao lam - all luong
            case 6:
                sql = "select * from NHANVIEN where "
                        + "chucvu like ? "
                        + "and (gioitinh like ?  or gioitinh like ?) "
                        + "and year(ngaysinh) = ? ";
                break;
            //all nam sinh - all luong
            case 7:
                sql = "select * from NHANVIEN where "
                        + "chucvu like ? "
                        + "and (gioitinh like ?  or gioitinh like ?) "
                        + "and year(ngayvaolam) = ? ";
                break;
            //all nam - all luong
            default:
                sql = "select * from NHANVIEN where "
                        + "chucvu like ? "
                        + "and (gioitinh like ?  or gioitinh like ?) ";
                break;
        }

        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                Connection connection = DriverManager.getConnection(XJdbc.getDburl(),XJdbc.getUsername(),XJdbc.getPassword());
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setObject(1, "%" + chucvu + "%");
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 2, args[i]);
                    if ((c == 1 || c == 2 || c == 3 || c == 4) && i == args.length - 1) {
                        pstmt.setObject(i + 3, args[i]);
                    }
                }

                rs = pstmt.executeQuery();
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                    entity.setChucVu(rs.getString("ChucVu"));
                    entity.setLuong(rs.getInt("Luong"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    list.add(entity);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
//            throw new RuntimeException(ex);

            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            throw new RuntimeException(ex);
        }

        return list;

    }
}
