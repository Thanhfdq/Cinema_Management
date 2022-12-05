/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.HoaDonCTDao;
import dao.HoaDonDao;
import dao.ThucDonDao;
import entity.HoaDon;
import entity.HoaDonCT;

import entity.ThucDon;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import until.Auth;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import until.MsgBox;
import until.XDate;
import until.XJdbc;

public class BanHang extends javax.swing.JFrame {

    ThucDon Thucd = new ThucDon();
    ThucDonDao dao = new ThucDonDao();
    HoaDonCTDao HDCTdao = new HoaDonCTDao();
    HoaDonDao HDdao = new HoaDonDao();

    DecimalFormat dcf = new DecimalFormat("###,###,###.###");

    public BanHang() {
        initComponents();
        XJdbc.setPassword(MsgBox.prompt(this, "Mời bạn nhập mật khẩu Database!!"));
        this.setLocationRelativeTo(null);

        mKDatabase();

    }

    void mKDatabase() {
        XJdbc.setPassword(MsgBox.prompt(this, "Mời bạn nhập mật khẩu!!")) ;

        doDanhSachSanPham();
        taoHoaDon();

    }

    void doDanhSachSanPham() {
        try {
            List<ThucDon> ListSP = dao.selectAll();
            doDuLieuSanPham(ListSP);
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn: " + e.toString());
        }
    }

    void doDuLieuSanPham(List<ThucDon> list) {
        String[] TenCot = {"#", "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Đơn vị tính", "Loại", "Hình", "Ghi chú"};
        DefaultTableModel dtm = new DefaultTableModel(null, TenCot) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setRowCount(0);
        int STT = 0;
        for (ThucDon sp : list) {
            STT++;

            Object[] row = {
                sp.getMaMon(),
                sp.getTenMon(),
                dcf.format(sp.getDonGia()),
                sp.getDonViTinh(),
                sp.getLoai(),
                sp.getHinh(),
                sp.getGhiChu()

            };
            dtm.addRow(row);
        }
        tbldanhsachsanpham.setModel(dtm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnxoatrang = new javax.swing.JButton();
        txtTongTien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        tab = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThucDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldanhsachsanpham = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BÁN HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hóa đơn");

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnxoatrang.setText("Xóa");
        btnxoatrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoatrangActionPerformed(evt);
            }
        });

        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel6.setText("Tổng tiền:");

        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyPressed(evt);
            }
        });

        jLabel7.setText("Khách trả:");

        txtTienThua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienThuaKeyPressed(evt);
            }
        });

        jLabel8.setText("Tiền thừa: ");

        jLabel2.setText("Số lượng:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(txtKhachTra)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(txtsoluong))
                .addGap(30, 30, 30))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnThanhToan)
                        .addGap(35, 35, 35)
                        .addComponent(btnxoatrang)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnxoatrang))
                .addGap(58, 58, 58))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 90, 280, 450));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 322, 550, 0));

        tblThucDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã món", "Tên món", "Số lượng", "Đơn giá", "Giá tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThucDon.setMinimumSize(new java.awt.Dimension(135, 48));
        tblThucDon.setRowHeight(100);
        tblThucDon.setRowMargin(3);
        tblThucDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThucDonMouseClicked(evt);
            }
        });
        tblThucDon.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tblThucDonPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(tblThucDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        tab.addTab("Đơn hàng", jPanel4);

        tbldanhsachsanpham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbldanhsachsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã món", "Tên món", "Đơn giá", "Đơn vị tính", "Loại", "Hình ", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldanhsachsanpham.setRowHeight(100);
        tbldanhsachsanpham.setRowMargin(3);
        tbldanhsachsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldanhsachsanphamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbldanhsachsanpham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Danh sách sản phẩm", jPanel3);

        jPanel1.add(tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 680, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed

    }//GEN-LAST:event_txtTongTienActionPerformed

    private void tblThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThucDonMouseClicked

    }//GEN-LAST:event_tblThucDonMouseClicked

    private void btnxoatrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoatrangActionPerformed
        xoaSanPhamDonHang();
        demsoluong();
    }//GEN-LAST:event_btnxoatrangActionPerformed

    private void tblThucDonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblThucDonPropertyChange
        demsoluong();
    }//GEN-LAST:event_tblThucDonPropertyChange

    private void tbldanhsachsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldanhsachsanphamMouseClicked
        if (evt.getClickCount() == 2) {
            themSanPhamThuCong();
        }
    }//GEN-LAST:event_tbldanhsachsanphamMouseClicked

    private void txtTienThuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienThuaKeyPressed

    }//GEN-LAST:event_txtTienThuaKeyPressed

    private void txtKhachTraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tinhTienThua();
        }
    }//GEN-LAST:event_txtKhachTraKeyPressed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        xuatHoaDon();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnxoatrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tblThucDon;
    private javax.swing.JTable tbldanhsachsanpham;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtsoluong;
    // End of variables declaration//GEN-END:variables

    private void themSanPhamThuCong() {
        int index = tbldanhsachsanpham.getSelectedRow();
        String tenSp = tbldanhsachsanpham.getValueAt(index, 1).toString();
        ThucDon TD = dao.selectByTen(tenSp);
        nhapSanPhamVaoDon(TD);
        demsoluong();
        tab.setSelectedIndex(0);
    }

    void nhapSanPhamVaoDon(ThucDon TD) {
        Auth.HoaDonGiaoDich.themSP(TD, 1);
        duyetDanhSach(TD);

    }
    static float tongCong;
    
    void tinhTienThua() {
      
        float TienHoaDon = Float.parseFloat(txtTongTien.getText());
        
        float TienKhachTra = Float.parseFloat(txtKhachTra.getText());
        float TienThua = TienKhachTra - TienHoaDon;
        
        if (TienThua < 0) {
            MsgBox.alert(this, "Kiểm tra lại tiền khách trả.");
            txtKhachTra.requestFocus();
        } else {
            txtTienThua.setText(dcf.format(TienThua) + "");

        }
        if (txtTongTien.getText().isEmpty()) {
            MsgBox.alert(this, "Không có giá trị");
        }
        tongCong = TienHoaDon   ;
    }
    
    void demsoluong() {
        int demSoLuong = 0;
        float tongThanhTien = 0;
        for (int i = 0; i < tblThucDon.getRowCount(); i++) {
            int SoLuong = Integer.parseInt(tblThucDon.getValueAt(i, 3).toString());
            float ThanhTien = Float.parseFloat(tblThucDon.getValueAt(i, 5).toString());
            tongThanhTien += ThanhTien;
            demSoLuong += SoLuong;
        }
        txtsoluong.setText(demSoLuong + "");
        txtTongTien.setText(tongThanhTien + "");
    }

    void taoHoaDon() {
        if (Auth.HoaDonGiaoDich == null) {
            Auth.HoaDonGiaoDich = new HoaDon();

        } else {
            capnhatDonHang();

        }
    }

    void xoaSanPhamDonHang() {
        DefaultTableModel dtm = (DefaultTableModel) tblThucDon.getModel();
        dtm.setRowCount(0);
        Auth.HoaDonGiaoDich.getDSSP().clear();
        txtKhachTra.setText("");
        txtTienThua.setText("");
    }

    void capnhatDonHang() {
        if (!Auth.HoaDonGiaoDich.getDSSP().isEmpty()) {
            // duyệt các dòng trong table để put lại số lượng của SP trong TreeMap
            for (int i = 0; i < tblThucDon.getRowCount(); i++) {
                String Tensp = tblThucDon.getValueAt(i, 2).toString();
                int SoLuong = Integer.parseInt(tblThucDon.getValueAt(i, 5).toString());
                ThucDon putSP = dao.selectById(Tensp);
                if (SoLuong == 0) {
                    // nếu số lượng nhập lại là 0 thì xoá sản phẩm khỏi TreeMap
                    Auth.HoaDonGiaoDich.getDSSP().remove(putSP);
                } else {
                    Auth.HoaDonGiaoDich.getDSSP().put(putSP, SoLuong);
                }
            }
            DefaultTableModel dtm = (DefaultTableModel) tblThucDon.getModel();
            dtm.setRowCount(0);
            // duyệt TreeMap và đổ thông tin lên table 
            for (ThucDon key : Auth.HoaDonGiaoDich.getDSSP().keySet()) {
                int STT = tblThucDon.getRowCount() + 1;
                Object[] addrow = {
                    STT,
                    key.getMaMon(),
                    key.getTenMon(),
                    Auth.HoaDonGiaoDich.getDSSP().get(key),
                    dcf.format(key.getDonGia()),
                    Auth.HoaDonGiaoDich.getDSSP().get(key) * key.getDonGia()

                };

                dtm.addRow(addrow);
            }
            demsoluong();
        }
    }

    void duyetDanhSach(ThucDon TD) {
        boolean check = true; // biến kiểm tra để xét thêm mới hay cập nhật 
        // chạy vòng lặp duyệt từng dòng của bảng 
        for (int row = 0; row <= tblThucDon.getRowCount() - 1; row++) {
            // xét tên sản phẩm nếu đã tồn tại thì cập nhật số lượng và thoát vòng lặp
            if (TD.getTenMon().equals(tblThucDon.getValueAt(row, 2))) {

                int SoLuongMoi = Integer.parseInt(tblThucDon.getValueAt(row, 3).toString()) + 1;

                // cập nhật lại thành tiền
                tblThucDon.setValueAt(SoLuongMoi * TD.getDonGia(), row, 5);
                // cập nhật lại số lượng
                tblThucDon.setValueAt(SoLuongMoi, row, 3);
                check = false;
                return;
            }
        }
        if (check) {
            DefaultTableModel dtm = (DefaultTableModel) tblThucDon.getModel();
            int STT = tblThucDon.getRowCount() + 1;
            float ThanhTien = Auth.HoaDonGiaoDich.getDSSP().get(TD) * TD.getDonGia();
            Object[] addrow = {
                STT,
                TD.getMaMon(),
                TD.getTenMon(),
                Auth.HoaDonGiaoDich.getDSSP().get(TD),
                dcf.format(TD.getDonGia()),
                ThanhTien

            };
            dtm.addRow(addrow);
        }
    }

    boolean xetDieuKien() {
        boolean ketqua = true;
        // kiem tra trong list hoa don tam co san pham hay khong
        if (Auth.HoaDonGiaoDich.getDSSP().isEmpty()) {
            ketqua = false;
        }
        // kiem tra tien khach tra
        float TienKhachTra = Float.parseFloat(txtKhachTra.getText());
        float TongCong = Float.parseFloat(txtTongTien.getText());
        if (TienKhachTra < TongCong) {
            ketqua = false;
        }
        return ketqua;
    }
    /*
     * Hàm đưa hoá đơn chi tiết lên database
     */
    
    static int MaHoaDon;
    static int Soluong;
    void themHDCT() throws SQLException {
        // get mã hoá đơn vừa đưa lên database (tức mã mới nhất)
        int dem = HDdao.demHoaDon();
        
        MaHoaDon = dem;
        for (ThucDon SP : Auth.HoaDonGiaoDich.getDSSP().keySet()) {
            HoaDonCT HDCT = new HoaDonCT();
            HDCT.setMaHD(dem);
            HDCT.setMaMon(SP.getMaMon());
            HDCT.setSoLuong(Auth.HoaDonGiaoDich.getDSSP().get(SP));
            
            HDCTdao.themHDCT(HDCT);
        }
    }

    void xuatHoaDon() {
        // kiem tra cac thong tin can thiet
        if (xetDieuKien()) {
            // đưa thông tin hoá đơn lên database: hoá đơn -> hoá đơn chi tiết
            Auth.HoaDonGiaoDich.setMaUser("US001");
            Auth.HoaDonGiaoDich.setNgayLap(XDate.now());
            try {

                HDdao.themHoaDon(Auth.HoaDonGiaoDich);

                themHDCT();
                // sau khi hoàn tất thao tác với database thì reload lại các thông tin
                MsgBox.alert(this, "Đã hoàn tất thanh toán");
                // in hoá đơn cho khách
                inHoaDon();
                Auth.HoaDonGiaoDich.getDSSP().clear();
                Auth.HoaDonGiaoDich = null;
                //new BanHang().setVisible(true);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            MsgBox.alert(this, "Kiểm tra lại các thông tin");
        }
    }

    void inHoaDon() {
        try {
            
            HashMap HoaDon = new HashMap();
            String path = getClass().getResource("/Report/HoaDon.jrxml").toString().replace("file:/", "");
            JasperReport rpt = JasperCompileManager.compileReport(path);
            HoaDon.put("MaHD", MaHoaDon);
            HoaDon.put("TongCong", tongCong);
            Connection conn = DriverManager.getConnection(XJdbc.getDburl(), XJdbc.getUsername(), XJdbc.getPassword());
            JasperPrint p = JasperFillManager.fillReport(rpt, HoaDon, conn);
            //Xem truoc khi in
            System.out.println(MaHoaDon);
            System.out.println(tongCong);
          
            JasperViewer.viewReport(p, false);
            //in hoa don
            JasperPrintManager.printReport(p, false);

        } catch (Exception e) {
            MsgBox.alert(this, "Có lỗi: " + e.toString());
            e.printStackTrace();
        }
    }

}
