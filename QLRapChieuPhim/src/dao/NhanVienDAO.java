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

    public void insert(NhanVien model) {
        String sql = "INSERT INTO Users (MaUser, HoTen, sodt, Email, GioiTinh, NgaySinh, NgayVaoLam, ChucVu, Luong, Hinh, GhiChu, MatKhau) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaUser(),
                model.getHoTen(),
                model.getSoDT(),
                model.getEmail(),
                model.getGioiTinh(),
                model.getNgaySinh(),
                model.getNgayVaoLam(),
                model.getChucVu(),
                model.getLuong(),
                model.getHinh(),
                model.getGhiChu(),
                model.getMatKhau());
    }

    public void update(NhanVien model) {
        String sql = "UPDATE Users SET HoTen=?, SoDT=?, Email=?, GioiTinh=?, NgaySinh=?, NgayVaoLam=?, ChucVu=?, Luong=?, Hinh=?, GhiChu=?, MatKhau=? WHERE MaUser=?";
        XJdbc.update(sql,
                model.getHoTen(),
                model.getSoDT(),
                model.getEmail(),
                model.getGioiTinh(),
                model.getNgaySinh(),
                model.getNgayVaoLam(),
                model.getChucVu(),
                model.getLuong(),
                model.getHinh(),
                model.getGhiChu(),
                model.getMatKhau(),
                model.getMaUser());
    }

    public void delete(String MaUser) {
        String sql = "DELETE FROM Users WHERE MaUser=? and MaRole <> 6";
        XJdbc.update(sql, MaUser);
    }

    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM Users where MaRole <> 6";
        return this.selectBySql(sql);
    }

    public NhanVien selectById(String MaUser) {
        String sql = "SELECT * FROM Users WHERE MaUser=? and MaRole <> 6";
        List<NhanVien> list = this.selectBySql(sql, MaUser);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaUser(rs.getString("MaUser"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setSoDT(rs.getString("SoDT"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setNgaySinh(rs.getDate("NgaySinh"));
                    entity.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                    entity.setChucVu(rs.getInt("MaRole"));
                    entity.setLuong(rs.getInt("Luong"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<NhanVien> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM users WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    public List<NhanVien> selectByFilter(Object... args) {
        String sql = "SELECT * FROM users WHERE MaRole <> 6 ? ? ? ? ?";
        return this.selectBySql(sql, args);
    }
}
