/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ThucDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import until.XJdbc;

public class ThucDonDao {

    public void insert(ThucDon model) {
        String sql = "INSERT INTO ThucDon (MaMon, TenMon, DonGia, DonViTinh, Loai, Hinh, GhiChu VALUES (?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaMon(),
                model.getTenMon(),
                model.getDonGia(),
                model.getDonViTinh(),
                model.getLoai(),
                model.getHinh(),
                model.getGhiChu());
    }

    public void update(ThucDon model) {
        String sql = "UPDATE ThucDon SET TenMon=?, DonGia=?, DonViTinh=?, Loai=?, Hinh=?, GhiChu=? WHERE MaMon=?";
        XJdbc.update(sql,
                model.getTenMon(),
                model.getDonGia(),
                model.getDonViTinh(),
                model.getLoai(),
                model.getHinh(),
                model.getGhiChu(),
                model.getMaMon());
    }

    public void delete(String MaMon) {
        String sql = "DELETE FROM ThucDon WHERE MaMon=?";
        XJdbc.update(sql, MaMon);
    }

    public List<ThucDon> selectAll() {
        String sql = "SELECT * FROM ThucDon";
        return this.selectBySql(sql);
    }

    public ThucDon selectById(String mamon) {
        String sql = "SELECT * FROM ThucDon WHERE MaMon=?";
        List<ThucDon> list = this.selectBySql(sql, mamon);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ThucDon selectByTen(String tenmon) {
        String sql = "SELECT * FROM ThucDon WHERE  TenMon LIKE ?";
        List<ThucDon> list = this.selectBySql(sql, "%" + tenmon + "%");
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<ThucDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM PhongChieu WHERE MaPhong LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    protected List<ThucDon> selectBySql(String sql, Object... args) {
        List<ThucDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    ThucDon entity = new ThucDon();
                    entity.setMaMon(rs.getInt("MaMon"));
                    entity.setTenMon(rs.getString("TenMon"));
                    entity.setDonGia(rs.getFloat("DonGia"));
                    entity.setDonViTinh(rs.getString("DonViTinh"));
                    entity.setLoai(rs.getString("Loai"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setGhiChu(rs.getString("GhiChu"));
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
}
