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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
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
import until.ComponentResizer;
import until.MsgBox;
import until.XDate;
import until.XImage;
import until.XJdbc;

public class BanHang extends javax.swing.JFrame {

    ThucDon Thucd = new ThucDon();
    ThucDonDao dao = new ThucDonDao();
    HoaDonCTDao HDCTdao = new HoaDonCTDao();
    HoaDonDao HDdao = new HoaDonDao();

    DecimalFormat dcf = new DecimalFormat("###,###,###.###");

    public BanHang() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        initResize();
        doDuLieuSanPham();
        taoHoaDon();
    }

    private ComponentResizer resize;

    public void initResize() {
        resize = new ComponentResizer();
        resize.setSnapSize(new Dimension(10, 10));
        resize.setMinimumSize(new Dimension(300, 200));
        resize.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        resize.registerComponent(this);
    }

    List<ThucDon> list;

    void doDuLieuSanPham() {
        list = dao.selectAll();
        String[] TenCot = {"Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Đơn vị tính", "Loại", "Hình"};
        DefaultTableModel dtm = new DefaultTableModel(null, TenCot) {
            // ghi đè kiểu của column để chuyển sang kiểu ImageIcon
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) {
                    return ImageIcon.class;
                }
                return Object.class;
            }
        };
        dtm.setRowCount(0);
        int STT = 0;
        for (ThucDon sp : list) {
            ImageIcon Hinh = null;
            if (sp.getHinh() != null) {
                ImageIcon im = new ImageIcon(getClass().getResource("/images/" + sp.getHinh()));
                Hinh = XImage.resizeImg(im, 200, tbldanhsachsanpham.getRowHeight());
            }
            Object[] row = {
                sp.getMaMon(),
                sp.getTenMon(),
                dcf.format(sp.getDonGia()),
                sp.getDonViTinh(),
                sp.getLoai(),
                Hinh};
            dtm.addRow(row);
        }
        tbldanhsachsanpham.setModel(dtm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound10 = new ui.PanelRound();
        pnlChinh = new ui.PanelRound();
        panelRound1 = new ui.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtsoluong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnxoatrang = new javax.swing.JButton();
        txtTongTien = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThucDon = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldanhsachsanpham = new javax.swing.JTable();
        labelRound1 = new ui.LabelRound();
        panelRound18 = new ui.PanelRound();
        txtTimsp = new javax.swing.JTextField();
        btnXoaTimKiemNV = new javax.swing.JButton();
        lblDongChon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnAccount = new ui.LabelRound();
        btnAction = new ui.LabelRound();
        btnAbout = new ui.LabelRound();
        btnHelp = new ui.LabelRound();
        pnlMoving = new ui.PanelRound();
        lblNgay = new ui.LabelRound();
        lblDongHo = new ui.LabelRound();
        labelRound4 = new ui.LabelRound();
        btnMinimize = new ui.LabelRound();
        btnResize = new ui.LabelRound();
        btnExit = new ui.LabelRound();
        labelRound13 = new ui.LabelRound();
        lblSocketState = new javax.swing.JLabel();
        lblDangXuat = new ui.LabelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BÁN HÀNG");
        setUndecorated(true);

        panelRound10.setBackground(new java.awt.Color(51, 51, 51));
        panelRound10.setRoundBottomLeft(20);
        panelRound10.setRoundBottomRight(20);
        panelRound10.setRoundTopLeft(20);
        panelRound10.setRoundTopRight(20);

        pnlChinh.setBackground(new java.awt.Color(153, 153, 153));
        pnlChinh.setRoundBottomRight(50);
        pnlChinh.setRoundTopLeft(50);

        panelRound1.setBackground(new java.awt.Color(51, 51, 51));
        panelRound1.setRoundBottomRight(20);

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Tổng tiền:");

        txtKhachTra.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Khách trả:");

        txtTienThua.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtTienThua.setEnabled(false);
        txtTienThua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienThuaKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Tiền thừa: ");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Số lượng:");

        txtsoluong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtsoluong.setEnabled(false);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hóa đơn");

        btnThanhToan.setBackground(new java.awt.Color(102, 153, 0));
        btnThanhToan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(204, 204, 204));
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnxoatrang.setForeground(new java.awt.Color(153, 153, 153));
        btnxoatrang.setText("Xóa");
        btnxoatrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoatrangActionPerformed(evt);
            }
        });

        txtTongTien.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        txtTongTien.setEnabled(false);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        tblThucDon.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tblThucDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên món", "Số lượng", "Đơn giá", "Giá tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThucDon.setMinimumSize(new java.awt.Dimension(135, 48));
        tblThucDon.setRowHeight(50);
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

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienThua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(txtKhachTra, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnxoatrang)))
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnxoatrang)
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbldanhsachsanpham.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        tbldanhsachsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã món", "Tên món", "Đơn giá", "Đơn vị tính", "Loại", "Hình "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        labelRound1.setBackground(new java.awt.Color(51, 51, 51));
        labelRound1.setForeground(new java.awt.Color(153, 153, 153));
        labelRound1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound1.setText("Thực đơn");
        labelRound1.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        labelRound1.setRoundTopLeft(45);

        panelRound18.setBackground(new java.awt.Color(255, 218, 234));
        panelRound18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtTimsp.setBackground(new java.awt.Color(255, 218, 234));
        txtTimsp.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTimsp.setForeground(new java.awt.Color(102, 102, 102));
        txtTimsp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTimsp.setText("Tìm kiếm");
        txtTimsp.setToolTipText("Gõ tên phim để tìm");
        txtTimsp.setBorder(null);
        txtTimsp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimspFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimspFocusLost(evt);
            }
        });
        txtTimsp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimspKeyReleased(evt);
            }
        });

        btnXoaTimKiemNV.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaTimKiemNV.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnXoaTimKiemNV.setForeground(new java.awt.Color(153, 153, 153));
        btnXoaTimKiemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_clear_search_24px_3.png"))); // NOI18N
        btnXoaTimKiemNV.setBorderPainted(false);
        btnXoaTimKiemNV.setContentAreaFilled(false);
        btnXoaTimKiemNV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaTimKiemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTimKiemNVActionPerformed(evt);
            }
        });

        lblDongChon.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lblDongChon.setForeground(new java.awt.Color(102, 102, 102));
        lblDongChon.setText("Dòng ");

        javax.swing.GroupLayout panelRound18Layout = new javax.swing.GroupLayout(panelRound18);
        panelRound18.setLayout(panelRound18Layout);
        panelRound18Layout.setHorizontalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDongChon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimsp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRound18Layout.setVerticalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXoaTimKiemNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimsp, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDongChon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlChinhLayout = new javax.swing.GroupLayout(pnlChinh);
        pnlChinh.setLayout(pnlChinhLayout);
        pnlChinhLayout.setHorizontalGroup(
            pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChinhLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChinhLayout.createSequentialGroup()
                        .addGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE))
                        .addGap(4, 4, 4))
                    .addGroup(pnlChinhLayout.createSequentialGroup()
                        .addComponent(panelRound18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlChinhLayout.setVerticalGroup(
            pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChinhLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChinhLayout.createSequentialGroup()
                        .addComponent(labelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRound18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Acount");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Actions");

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("About");

        jLabel17.setBackground(new java.awt.Color(204, 51, 0));
        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Help");

        btnAccount.setBackground(new java.awt.Color(51, 51, 51));
        btnAccount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_account_32px.png"))); // NOI18N
        btnAccount.setRoundBottomLeft(20);
        btnAccount.setRoundBottomRight(20);
        btnAccount.setRoundTopLeft(20);
        btnAccount.setRoundTopRight(20);
        btnAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnAccountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnAccountFocusLost(evt);
            }
        });
        btnAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAccountMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAccountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAccountMouseExited(evt);
            }
        });

        btnAction.setBackground(new java.awt.Color(102, 102, 102));
        btnAction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_suitcase_32px_1.png"))); // NOI18N
        btnAction.setRoundBottomLeft(20);
        btnAction.setRoundBottomRight(20);
        btnAction.setRoundTopLeft(20);
        btnAction.setRoundTopRight(20);
        btnAction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActionMouseExited(evt);
            }
        });

        btnAbout.setBackground(new java.awt.Color(51, 51, 51));
        btnAbout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_about_32px.png"))); // NOI18N
        btnAbout.setRoundBottomLeft(20);
        btnAbout.setRoundBottomRight(20);
        btnAbout.setRoundTopLeft(20);
        btnAbout.setRoundTopRight(20);
        btnAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAboutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAboutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAboutMouseExited(evt);
            }
        });

        btnHelp.setBackground(new java.awt.Color(51, 51, 51));
        btnHelp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_help_32px.png"))); // NOI18N
        btnHelp.setRoundBottomLeft(20);
        btnHelp.setRoundBottomRight(20);
        btnHelp.setRoundTopLeft(20);
        btnHelp.setRoundTopRight(20);
        btnHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHelpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHelpMouseExited(evt);
            }
        });

        pnlMoving.setBackground(new java.awt.Color(51, 51, 51));
        pnlMoving.setRoundTopLeft(20);
        pnlMoving.setRoundTopRight(20);
        pnlMoving.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlMovingMouseDragged(evt);
            }
        });
        pnlMoving.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMovingMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlMovingMousePressed(evt);
            }
        });

        lblNgay.setBackground(new java.awt.Color(139, 0, 0));
        lblNgay.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgay.setText("01/01/2022");
        lblNgay.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblNgay.setRoundBottomLeft(30);
        lblNgay.setRoundBottomRight(30);
        lblNgay.setRoundTopLeft(30);
        lblNgay.setRoundTopRight(30);

        lblDongHo.setBackground(new java.awt.Color(139, 0, 0));
        lblDongHo.setForeground(new java.awt.Color(255, 255, 255));
        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setText("24:24");
        lblDongHo.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblDongHo.setRoundBottomLeft(30);
        lblDongHo.setRoundBottomRight(30);
        lblDongHo.setRoundTopLeft(30);
        lblDongHo.setRoundTopRight(30);

        labelRound4.setBackground(new java.awt.Color(139, 0, 0));
        labelRound4.setForeground(new java.awt.Color(255, 255, 255));
        labelRound4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound4.setText("Xin chào nhân viên bán hàng");
        labelRound4.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        labelRound4.setRoundBottomLeft(30);
        labelRound4.setRoundBottomRight(30);
        labelRound4.setRoundTopLeft(30);
        labelRound4.setRoundTopRight(30);

        btnMinimize.setBackground(new java.awt.Color(204, 204, 204));
        btnMinimize.setForeground(new java.awt.Color(51, 51, 51));
        btnMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMinimize.setToolTipText("MInimize");
        btnMinimize.setRoundBottomLeft(50);
        btnMinimize.setRoundBottomRight(50);
        btnMinimize.setRoundTopLeft(50);
        btnMinimize.setRoundTopRight(50);
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseExited(evt);
            }
        });

        btnResize.setBackground(new java.awt.Color(255, 255, 204));
        btnResize.setForeground(new java.awt.Color(51, 51, 51));
        btnResize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnResize.setToolTipText("Restore");
        btnResize.setRoundBottomLeft(50);
        btnResize.setRoundBottomRight(50);
        btnResize.setRoundTopLeft(50);
        btnResize.setRoundTopRight(50);
        btnResize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResizeMouseExited(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(139, 0, 0));
        btnExit.setForeground(new java.awt.Color(51, 51, 51));
        btnExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnExit.setToolTipText("Exit");
        btnExit.setRoundBottomLeft(50);
        btnExit.setRoundBottomRight(50);
        btnExit.setRoundTopLeft(50);
        btnExit.setRoundTopRight(50);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });

        labelRound13.setBackground(new java.awt.Color(51, 51, 51));
        labelRound13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cinesys_round_logo_24px.png"))); // NOI18N
        labelRound13.setRoundBottomLeft(50);
        labelRound13.setRoundBottomRight(50);
        labelRound13.setRoundTopLeft(50);
        labelRound13.setRoundTopRight(50);

        lblSocketState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSocketState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_disconnected_32px.png"))); // NOI18N
        lblSocketState.setToolTipText("Disconnected");

        javax.swing.GroupLayout pnlMovingLayout = new javax.swing.GroupLayout(pnlMoving);
        pnlMoving.setLayout(pnlMovingLayout);
        pnlMovingLayout.setHorizontalGroup(
            pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovingLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnResize, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblSocketState, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlMovingLayout.setVerticalGroup(
            pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovingLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResize, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(pnlMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSocketState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMovingLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        lblDangXuat.setBackground(new java.awt.Color(51, 51, 51));
        lblDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        lblDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_logout_24px.png"))); // NOI18N
        lblDangXuat.setToolTipText("Đăng xuất");
        lblDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblDangXuat.setRoundBottomLeft(50);
        lblDangXuat.setRoundBottomRight(50);
        lblDangXuat.setRoundTopLeft(50);
        lblDangXuat.setRoundTopRight(50);
        lblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDangXuatMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelRound10Layout = new javax.swing.GroupLayout(panelRound10);
        panelRound10.setLayout(panelRound10Layout);
        panelRound10Layout.setHorizontalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound10Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAbout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelRound10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDangXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addComponent(pnlMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound10Layout.setVerticalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addComponent(pnlMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound10Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(41, 41, 41)
                        .addComponent(btnAction, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(40, 40, 40)
                        .addComponent(btnAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addGap(43, 43, 43))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed

    }//GEN-LAST:event_txtTongTienActionPerformed

    private void btnxoatrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoatrangActionPerformed
        xoaSanPhamDonHang();
        demsoluong();
    }//GEN-LAST:event_btnxoatrangActionPerformed

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

    private void btnAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAccountFocusGained
        btnAccount.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_account_32px_1.png")));
    }//GEN-LAST:event_btnAccountFocusGained

    private void btnAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAccountFocusLost
        btnAccount.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_account_32px.png")));
    }//GEN-LAST:event_btnAccountFocusLost

    int chucnang = 2;

    void stateFunction() {
        if (chucnang == 1) {
            btnAccount.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_account_32px_1.png")));
            btnAccount.setBackground(new Color(102, 102, 102));
            btnAction.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_suitcase_32px.png")));
            btnAction.setBackground(new Color(51, 51, 51));
            btnAbout.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_about_32px.png")));
            btnAbout.setBackground(new Color(51, 51, 51));
        } else if (chucnang == 2) {
            btnAccount.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_account_32px.png")));
            btnAccount.setBackground(new Color(51, 51, 51));
            btnAction.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_suitcase_32px_1.png")));
            btnAction.setBackground(new Color(102, 102, 102));
            btnAbout.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_about_32px.png")));
            btnAbout.setBackground(new Color(51, 51, 51));
        } else {
            btnAccount.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_account_32px.png")));
            btnAccount.setBackground(new Color(51, 51, 51));
            btnAction.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_suitcase_32px.png")));
            btnAction.setBackground(new Color(51, 51, 51));
            btnAbout.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_about_32px_1.png")));
            btnAbout.setBackground(new Color(102, 102, 102));
        }
    }

    private void btnAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccountMouseClicked
        chucnang = 1;
        stateFunction();
    }//GEN-LAST:event_btnAccountMouseClicked

    private void btnAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccountMouseEntered
        if (chucnang != 1) {
            btnAccount.setBackground(new Color(70, 70, 70));
        }
    }//GEN-LAST:event_btnAccountMouseEntered

    private void btnAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccountMouseExited
        if (chucnang != 1) {
            btnAccount.setBackground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_btnAccountMouseExited

    private void btnActionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActionMouseClicked
        chucnang = 2;
        stateFunction();
    }//GEN-LAST:event_btnActionMouseClicked

    private void btnActionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActionMouseEntered
        if (chucnang != 2) {
            btnAction.setBackground(new Color(70, 70, 70));
        }
    }//GEN-LAST:event_btnActionMouseEntered

    private void btnActionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActionMouseExited
        if (chucnang != 2) {
            btnAction.setBackground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_btnActionMouseExited

    private void btnAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseClicked
        chucnang = 3;
        stateFunction();
    }//GEN-LAST:event_btnAboutMouseClicked

    private void btnAboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseEntered
        if (chucnang != 3) {
            btnAbout.setBackground(new Color(70, 70, 70));
        }
    }//GEN-LAST:event_btnAboutMouseEntered

    private void btnAboutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseExited
        if (chucnang != 3) {
            btnAbout.setBackground(new Color(51, 51, 51));
        }
    }//GEN-LAST:event_btnAboutMouseExited

    private void btnHelpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseEntered
        btnHelp.setBackground(new Color(70, 70, 70));
    }//GEN-LAST:event_btnHelpMouseEntered

    private void btnHelpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseExited
        btnHelp.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnHelpMouseExited

    private void lblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseClicked
        this.dispose();
        Auth.clear();
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_lblDangXuatMouseClicked

    private void lblDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseEntered
        lblDangXuat.setBackground(new Color(70, 70, 70));
    }//GEN-LAST:event_lblDangXuatMouseEntered

    private void lblDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseExited
        lblDangXuat.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_lblDangXuatMouseExited

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_horizontal_line_24px_1.png")));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setIcon(null);
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnResizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResizeMouseClicked
        if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            this.setExtendedState(JFrame.NORMAL);
        } else {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_btnResizeMouseClicked

    private void btnResizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResizeMouseEntered
        if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            btnResize.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_copy_24px_1.png")));
        } else {
            btnResize.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_rounded_square_24px_1.png")));
        }
    }//GEN-LAST:event_btnResizeMouseEntered

    private void btnResizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResizeMouseExited
        btnResize.setIcon(null);
    }//GEN-LAST:event_btnResizeMouseExited

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        if (MsgBox.confirm(this, "Bạn có chắc muốn thoát?")) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_close_24px.png")));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setIcon(null);
    }//GEN-LAST:event_btnExitMouseExited

    //cai dat title bar custom va frame
    private int xMouse, yMouse;
    private void pnlMovingMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMovingMouseDragged
        if (SwingUtilities.isLeftMouseButton(evt)) {
            if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                this.setExtendedState(JFrame.NORMAL);
            }
            this.setLocation(evt.getXOnScreen() - xMouse, evt.getYOnScreen() - yMouse);
        }
    }//GEN-LAST:event_pnlMovingMouseDragged

    private void pnlMovingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMovingMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2) {
            if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                this.setExtendedState(JFrame.NORMAL);
            } else {
                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        }
    }//GEN-LAST:event_pnlMovingMouseClicked

    private void pnlMovingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMovingMousePressed
        if (this.getExtendedState() != JFrame.MAXIMIZED_BOTH && SwingUtilities.isLeftMouseButton(evt)) {
            xMouse = evt.getX() + 3;
            yMouse = evt.getY() + 3;
        }
    }//GEN-LAST:event_pnlMovingMousePressed

    private void tblThucDonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tblThucDonPropertyChange
        demsoluong();
    }//GEN-LAST:event_tblThucDonPropertyChange

    private void tblThucDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThucDonMouseClicked

    }//GEN-LAST:event_tblThucDonMouseClicked

    private void txtTimspFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimspFocusGained
        if (txtTimsp.getText().equals("Tìm kiếm")) {
            txtTimsp.setText("");
        }
    }//GEN-LAST:event_txtTimspFocusGained

    private void txtTimspFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimspFocusLost
        if (txtTimsp.getText().equals("")) {
            txtTimsp.setText("Tìm kiếm");
        }
    }//GEN-LAST:event_txtTimspFocusLost

    private void txtTimspKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimspKeyReleased
        timPhimTrongNgay();
    }//GEN-LAST:event_txtTimspKeyReleased

    private void btnXoaTimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTimKiemNVActionPerformed
        txtTimsp.setText("");
        timPhimTrongNgay();
        txtTimsp.setText("Tìm kiếm");
    }//GEN-LAST:event_btnXoaTimKiemNVActionPerformed

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
    private ui.LabelRound btnAbout;
    private ui.LabelRound btnAccount;
    private ui.LabelRound btnAction;
    private ui.LabelRound btnExit;
    private ui.LabelRound btnHelp;
    private ui.LabelRound btnMinimize;
    private ui.LabelRound btnResize;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaTimKiemNV;
    private javax.swing.JButton btnxoatrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private ui.LabelRound labelRound1;
    private ui.LabelRound labelRound13;
    private ui.LabelRound labelRound4;
    private ui.LabelRound lblDangXuat;
    private javax.swing.JLabel lblDongChon;
    private ui.LabelRound lblDongHo;
    private ui.LabelRound lblNgay;
    private javax.swing.JLabel lblSocketState;
    private ui.PanelRound panelRound1;
    private ui.PanelRound panelRound10;
    private ui.PanelRound panelRound18;
    private ui.PanelRound pnlChinh;
    private ui.PanelRound pnlMoving;
    private javax.swing.JTable tblThucDon;
    private javax.swing.JTable tbldanhsachsanpham;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimsp;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtsoluong;
    // End of variables declaration//GEN-END:variables

    void timPhimTrongNgay() {
        System.out.println("tim...");
        String keyWord = txtTimsp.getText();

        DefaultTableModel model = (DefaultTableModel) tbldanhsachsanpham.getModel();
        model.setRowCount(0);
        for (ThucDon sp : list) {
            System.out.println("lap");
            System.out.println(sp.getTenMon().toLowerCase());
            if (sp.getTenMon().toLowerCase().contains(keyWord.toLowerCase())
                    || sp.getLoai().toLowerCase().contains(keyWord.toLowerCase())
                    || (sp.getDonGia() + "").toLowerCase().contains(keyWord.toLowerCase())) {
                System.out.println("----co");
                ImageIcon Hinh = null;
                if (sp.getHinh() != null) {
                    ImageIcon im = new ImageIcon(getClass().getResource("/images/" + sp.getHinh()));
                    Hinh = XImage.resizeImg(im, 200, tbldanhsachsanpham.getRowHeight());
                }
                Object[] row = {
                    sp.getMaMon(),
                    sp.getTenMon(),
                    dcf.format(sp.getDonGia()),
                    sp.getDonViTinh(),
                    sp.getLoai(),
                    Hinh};
                model.addRow(row);
            }
        }
        lblDongChon.setText(tbldanhsachsanpham.getRowCount() + " dòng");
        System.out.println("het");
    }

    private void themSanPhamThuCong() {
        int index = tbldanhsachsanpham.getSelectedRow();
        String tenSp = tbldanhsachsanpham.getValueAt(index, 1).toString();
        ThucDon TD = dao.selectByTen(tenSp);
        Auth.HoaDonGiaoDich.themSP(TD, 1);
        duyetDanhSach(TD);
        demsoluong();
    }

    static float tongCong;
    float TienKhachTra;
    float TienThua;

    void tinhTienThua() {

        float TienHoaDon = Float.parseFloat(txtTongTien.getText());

        TienKhachTra = Float.parseFloat(txtKhachTra.getText());
        TienThua = TienKhachTra - TienHoaDon;

        if (TienThua < 0) {
            MsgBox.alert(this, "Kiểm tra lại tiền khách trả.");
            txtKhachTra.requestFocus();
        } else {
            txtTienThua.setText(dcf.format(TienThua) + "");

        }
        if (txtTongTien.getText().isEmpty()) {
            MsgBox.alert(this, "Không có giá trị");
        }
        tongCong = TienHoaDon;
    }

    void demsoluong() {
        int demSoLuong = 0;
        float tongThanhTien = 0;
        for (int i = 0; i < tblThucDon.getRowCount(); i++) {
            int SoLuong = Integer.parseInt(tblThucDon.getValueAt(i, 2).toString());
            float ThanhTien = Float.parseFloat(tblThucDon.getValueAt(i, 4).toString());
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
            if (TD.getTenMon().equals(tblThucDon.getValueAt(row, 1))) {

                int SoLuongMoi = Integer.parseInt(tblThucDon.getValueAt(row, 2).toString()) + 1;

                // cập nhật lại thành tiền
                tblThucDon.setValueAt(SoLuongMoi * TD.getDonGia(), row, 4);
                // cập nhật lại số lượng
                tblThucDon.setValueAt(SoLuongMoi, row, 2);
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
        int mahdMoi = HDdao.maHoaDon();

        MaHoaDon = mahdMoi;
        for (ThucDon SP : Auth.HoaDonGiaoDich.getDSSP().keySet()) {
            HoaDonCT HDCT = new HoaDonCT();
            HDCT.setMaHD(mahdMoi);
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
                Auth.HoaDonGiaoDich = new HoaDon();
                ((DefaultTableModel) tblThucDon.getModel()).setRowCount(0);

                txtsoluong.setText("0");
                txtTongTien.setText("0.0");
                txtKhachTra.setText("");
                txtTienThua.setText("");
//                new BanHang().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            MsgBox.alert(this, "Kiểm tra lại các thông tin");
        }
    }

    void inHoaDon() {
        try {

            HashMap HoaDon = new HashMap();
            String path = getClass().getResource("/ui/report/HoaDon.jrxml").toString().replace("file:/", "");
            JasperReport rpt = JasperCompileManager.compileReport(path);
            HoaDon.put("MaHD", MaHoaDon);
            HoaDon.put("TongCong", tongCong);
            HoaDon.put("tienKhachTra", TienKhachTra);
            HoaDon.put("tienThua", TienThua);
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
