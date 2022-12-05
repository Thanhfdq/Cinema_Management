/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import dao.GheDao;
import dao.VeDao;
import entity.Ve;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import until.Auth;
import until.ComponentResizer;
import until.MsgBox;
import until.XDate;
import until.XImage;
import until.XJdbc;

/**
 *
 * @author quoct
 */
public class DatVe extends javax.swing.JFrame {
    
    boolean batdau = true;
    //lay thong tin
    String phimCur, ngayChieuCur, gioChieuCur, soGheCur, soPhongCur, maLichChieuCur, anhCur;
    Color daDat = Color.RED;
    Color coTrongDon = Color.ORANGE;
    Color chon = Color.GREEN;
    Color trong = new Color(51, 51, 51);
    Color sai = new Color(139, 0, 0);
    
    List<String[]> dsMaLichChieu = new ArrayList();

    //don dat ve
    List<Object[]> dsDatVe = new ArrayList(); //dsDatVe tam bao gom : NguoiLap,NgayLap,MaGhe,MaPhong,MaLichChieu, gia ve
    int tongTien = 0;
    
    Socket clientSocket;

    /**
     * Creates new form DatVe
     */
    public DatVe() {
        initComponents();
        initResize();
        mKDatabase();
        setBackground(new Color(0, 0, 0, 0));
        lblThemVe.setBackground(sai);
        lblThanhToan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblThemVe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        System.out.println(getClass().getResource("/ui/report/VeXemPhim.jrxml").toString().replace("file:/", ""));
        //chinh do rong cot bang ve
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);
//        jScrollPane1.getViewport().setBackground(new Color(102, 102, 102));
        TableColumnModel model = tblDonMuaVe.getColumnModel();
        model.getColumn(1).setMaxWidth(40);
        model.getColumn(2).setMaxWidth(100);
        model.getColumn(2).setMinWidth(100);
        model.getColumn(3).setMaxWidth(100);
        model.getColumn(3).setMinWidth(100);
        
        model.getColumn(1).setCellRenderer(render);
        model.getColumn(2).setCellRenderer(render);
        model.getColumn(3).setCellRenderer(render);
        
        dtcChonNgay.setDate(new Date());
        lblNgayLap.setText(XDate.toString(new Date(), "dd-MM-YYYY"));
        lblSoLuong.setText("0");
        lblTongTien.setText(tongTien + "");
        //Khong cho sua bang danh dach phim
        tblChonPhim.setDefaultEditor(Object.class, null);
        jScrollPane1.getColumnHeader().setVisible(false);
        jScrollPane1.getViewport().setBackground(new Color(102, 102, 102));
        scrChonPhim.getColumnHeader().setVisible(false);
        scrChonPhim.getViewport().setBackground(new Color(255,218,234));

        //Neu co yeu cau ket noi thi mo ket noi
        if (Auth.connectSocket) {
            //mo socket
            try {
                clientSocket = new Socket(Auth.ip, Auth.port);
                lblSocketState.setIcon(new ImageIcon(getClass().getResource("/icons/icons8_connected_32px.png")));
                lblSocketState.setToolTipText("Connected");
            } catch (IOException ex) {
                System.out.println("Loi ket noi");
                ex.printStackTrace();
            }
        }
    }
    
    void mKDatabase() {
        if (MsgBox.confirm(this, "Chọn dùng Localhost")) {
            XJdbc.setHost("Localhost");
        } else {
            XJdbc.setHost("192.168.1.0");
        }
        XJdbc.setPassword(MsgBox.prompt(this, "Mời bạn nhập mật khẩu Database!!"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmnXoa = new javax.swing.JPopupMenu();
        mnXoa = new javax.swing.JMenuItem();
        mnChiTiet = new javax.swing.JMenuItem();
        panelRound10 = new ui.PanelRound();
        pnlChinh = new ui.PanelRound();
        panelRound1 = new ui.PanelRound();
        lblThanhToan = new ui.LabelRound();
        panelRound3 = new ui.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTongTien = new ui.LabelRound();
        lblNgayLap = new ui.LabelRound();
        lblSoLuong = new ui.LabelRound();
        jLabel4 = new javax.swing.JLabel();
        labelRound2 = new ui.LabelRound();
        labelRound3 = new ui.LabelRound();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonMuaVe = new javax.swing.JTable();
        pnlDanhSachVe = new javax.swing.JPanel();
        panelRound2 = new ui.PanelRound();
        pnlChonPhim = new ui.PanelRound();
        scrChonPhim = new javax.swing.JScrollPane();
        tblChonPhim = new javax.swing.JTable();
        panelRound18 = new ui.PanelRound();
        txtTimPhim = new javax.swing.JTextField();
        btnXoaTimKiemNV = new javax.swing.JButton();
        lblDongChon = new javax.swing.JLabel();
        dtcChonNgay = new com.toedter.calendar.JDateChooser();
        labelRound20 = new ui.LabelRound();
        pnlChonGhe = new ui.PanelRound();
        btnQuayLaiChonPhim = new javax.swing.JButton();
        labelRound1 = new ui.LabelRound();
        panelRound4 = new ui.PanelRound();
        panelRound5 = new ui.PanelRound();
        panelRound6 = new ui.PanelRound();
        panelRound7 = new ui.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelRound9 = new ui.PanelRound();
        pnlGhe = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        verticalIndex = new javax.swing.JPanel();
        labelRound5 = new ui.LabelRound();
        labelRound6 = new ui.LabelRound();
        labelRound7 = new ui.LabelRound();
        labelRound8 = new ui.LabelRound();
        labelRound9 = new ui.LabelRound();
        labelRound10 = new ui.LabelRound();
        labelRound11 = new ui.LabelRound();
        HorizoneIndex = new javax.swing.JPanel();
        labelRound14 = new ui.LabelRound();
        labelRound15 = new ui.LabelRound();
        labelRound16 = new ui.LabelRound();
        labelRound17 = new ui.LabelRound();
        labelRound18 = new ui.LabelRound();
        labelRound19 = new ui.LabelRound();
        panelRound8 = new ui.PanelRound();
        lblNgayChieuCur = new ui.LabelRound();
        lblSoPhongCur = new ui.LabelRound();
        lblGioChieuCur = new ui.LabelRound();
        lblSoGheCur = new ui.LabelRound();
        txtTenPhimCur = new ui.LabelRound();
        lblAnhPhimCur = new ui.LabelRound();
        lblThemVe = new ui.LabelRound();
        labelRound12 = new ui.LabelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAccount = new ui.LabelRound();
        btnAction = new ui.LabelRound();
        btnAbout = new ui.LabelRound();
        btnHelp = new ui.LabelRound();
        pnlMoving = new ui.PanelRound();
        lblNgay = new ui.LabelRound();
        lblDongHo = new ui.LabelRound();
        labelRound4 = new ui.LabelRound();
        lblDangXuat = new ui.LabelRound();
        btnMinimize = new ui.LabelRound();
        btnResize = new ui.LabelRound();
        btnExit = new ui.LabelRound();
        labelRound13 = new ui.LabelRound();
        lblSocketState = new javax.swing.JLabel();

        pmnXoa.setBackground(new java.awt.Color(51, 51, 51));
        pmnXoa.setForeground(new java.awt.Color(255, 255, 255));
        pmnXoa.setPreferredSize(new java.awt.Dimension(237, 70));

        mnXoa.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        mnXoa.setForeground(new java.awt.Color(51, 51, 51));
        mnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_delete_row_24px.png"))); // NOI18N
        mnXoa.setText("Xóa dòng");
        mnXoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mnXoa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        mnXoa.setIconTextGap(7);
        mnXoa.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_two_tickets_24px.png"))); // NOI18N
        mnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnXoaActionPerformed(evt);
            }
        });
        pmnXoa.add(mnXoa);

        mnChiTiet.setBackground(new java.awt.Color(51, 51, 51));
        mnChiTiet.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        mnChiTiet.setForeground(new java.awt.Color(51, 51, 51));
        mnChiTiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_view_details_24px.png"))); // NOI18N
        mnChiTiet.setText("Xem chi Ttết");
        mnChiTiet.setBorder(null);
        mnChiTiet.setContentAreaFilled(false);
        pmnXoa.add(mnChiTiet);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đặt vé");
        setUndecorated(true);

        panelRound10.setBackground(new java.awt.Color(51, 51, 51));
        panelRound10.setRoundBottomLeft(20);
        panelRound10.setRoundBottomRight(20);
        panelRound10.setRoundTopLeft(20);
        panelRound10.setRoundTopRight(20);

        pnlChinh.setBackground(new java.awt.Color(153, 153, 153));
        pnlChinh.setRoundBottomRight(50);
        pnlChinh.setRoundTopLeft(50);

        panelRound1.setBackground(new java.awt.Color(102, 102, 102));
        panelRound1.setRoundBottomRight(20);

        lblThanhToan.setBackground(new java.awt.Color(0, 204, 0));
        lblThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        lblThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThanhToan.setText("Thanh toán");
        lblThanhToan.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lblThanhToan.setRoundBottomLeft(20);
        lblThanhToan.setRoundBottomRight(20);
        lblThanhToan.setRoundTopLeft(20);
        lblThanhToan.setRoundTopRight(20);
        lblThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThanhToanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblThanhToanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblThanhToanMouseExited(evt);
            }
        });

        panelRound3.setBackground(new java.awt.Color(51, 51, 51));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tổng tiền");

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Số lượng");

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Người mua");

        lblTongTien.setBackground(new java.awt.Color(51, 51, 51));
        lblTongTien.setForeground(new java.awt.Color(0, 255, 0));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("labelRound2");
        lblTongTien.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        lblTongTien.setRoundBottomLeft(20);
        lblTongTien.setRoundTopLeft(20);

        lblNgayLap.setBackground(new java.awt.Color(51, 51, 51));
        lblNgayLap.setForeground(new java.awt.Color(255, 255, 255));
        lblNgayLap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_today_24px.png"))); // NOI18N
        lblNgayLap.setText("labelRound3");
        lblNgayLap.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lblNgayLap.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblNgayLap.setRoundBottomLeft(20);
        lblNgayLap.setRoundTopLeft(20);

        lblSoLuong.setBackground(new java.awt.Color(51, 51, 51));
        lblSoLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblSoLuong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoLuong.setText("labelRound4");
        lblSoLuong.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lblSoLuong.setRoundBottomLeft(20);
        lblSoLuong.setRoundTopLeft(20);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ngày lập");

        labelRound2.setBackground(new java.awt.Color(51, 51, 51));
        labelRound2.setForeground(new java.awt.Color(255, 255, 255));
        labelRound2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_two_tickets_24px.png"))); // NOI18N
        labelRound2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N

        labelRound3.setBackground(new java.awt.Color(51, 51, 51));
        labelRound3.setForeground(new java.awt.Color(255, 255, 255));
        labelRound3.setText("VND");
        labelRound3.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound3Layout.createSequentialGroup()
                        .addComponent(lblSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(labelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setBorder(null);

        tblDonMuaVe.setBackground(new java.awt.Color(51, 51, 51));
        tblDonMuaVe.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        tblDonMuaVe.setForeground(new java.awt.Color(255, 255, 255));
        tblDonMuaVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonMuaVe.setFocusable(false);
        tblDonMuaVe.setGridColor(new java.awt.Color(204, 204, 204));
        tblDonMuaVe.setRowHeight(60);
        tblDonMuaVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDonMuaVeMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDonMuaVe);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pnlDanhSachVe.setBackground(new java.awt.Color(153, 153, 153));

        panelRound2.setPreferredSize(new java.awt.Dimension(780, 519));
        panelRound2.setRoundBottomLeft(30);
        panelRound2.setRoundBottomRight(30);
        panelRound2.setRoundTopLeft(30);
        panelRound2.setRoundTopRight(30);
        panelRound2.setLayout(new java.awt.CardLayout());

        pnlChonPhim.setBackground(new java.awt.Color(255, 218, 234));
        pnlChonPhim.setRoundBottomLeft(30);
        pnlChonPhim.setRoundBottomRight(30);
        pnlChonPhim.setRoundTopLeft(30);
        pnlChonPhim.setRoundTopRight(30);

        scrChonPhim.setBorder(null);

        tblChonPhim.setBackground(new java.awt.Color(255, 218, 234));
        tblChonPhim.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        tblChonPhim.setForeground(new java.awt.Color(51, 51, 51));
        tblChonPhim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", " ", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChonPhim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblChonPhim.setRowHeight(150);
        tblChonPhim.setRowMargin(5);
        tblChonPhim.setSelectionBackground(new java.awt.Color(255, 102, 51));
        tblChonPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChonPhimMouseClicked(evt);
            }
        });
        scrChonPhim.setViewportView(tblChonPhim);

        panelRound18.setBackground(new java.awt.Color(255, 218, 234));
        panelRound18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelRound18.setRoundTopLeft(30);
        panelRound18.setRoundTopRight(30);

        txtTimPhim.setBackground(new java.awt.Color(255, 218, 234));
        txtTimPhim.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        txtTimPhim.setForeground(new java.awt.Color(102, 102, 102));
        txtTimPhim.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTimPhim.setText("Tìm kiếm");
        txtTimPhim.setToolTipText("Gõ tên phim để tìm");
        txtTimPhim.setBorder(null);
        txtTimPhim.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimPhimFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimPhimFocusLost(evt);
            }
        });
        txtTimPhim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimPhimKeyReleased(evt);
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

        dtcChonNgay.setBackground(new java.awt.Color(255, 218, 234));
        dtcChonNgay.setForeground(new java.awt.Color(204, 0, 0));
        dtcChonNgay.setDate(new java.util.Date(1668613660000L));
        dtcChonNgay.setDateFormatString("dd-MM-yyyy");
        dtcChonNgay.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        dtcChonNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcChonNgayPropertyChange(evt);
            }
        });

        labelRound20.setBackground(new java.awt.Color(255, 218, 234));
        labelRound20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_view_all_32px.png"))); // NOI18N
        labelRound20.setToolTipText("Xem tất cả lịch chiếu");
        labelRound20.setRoundTopLeft(50);

        javax.swing.GroupLayout panelRound18Layout = new javax.swing.GroupLayout(panelRound18);
        panelRound18.setLayout(panelRound18Layout);
        panelRound18Layout.setHorizontalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound18Layout.createSequentialGroup()
                .addComponent(labelRound20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dtcChonNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDongChon, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimPhim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRound18Layout.setVerticalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXoaTimKiemNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dtcChonNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDongChon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(labelRound20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlChonPhimLayout = new javax.swing.GroupLayout(pnlChonPhim);
        pnlChonPhim.setLayout(pnlChonPhimLayout);
        pnlChonPhimLayout.setHorizontalGroup(
            pnlChonPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrChonPhim, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
            .addComponent(panelRound18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlChonPhimLayout.setVerticalGroup(
            pnlChonPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChonPhimLayout.createSequentialGroup()
                .addComponent(panelRound18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrChonPhim, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        panelRound2.add(pnlChonPhim, "card2");

        pnlChonGhe.setBackground(new java.awt.Color(102, 102, 102));
        pnlChonGhe.setRoundBottomLeft(30);
        pnlChonGhe.setRoundBottomRight(30);
        pnlChonGhe.setRoundTopLeft(30);
        pnlChonGhe.setRoundTopRight(30);

        btnQuayLaiChonPhim.setBackground(new java.awt.Color(51, 51, 51));
        btnQuayLaiChonPhim.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnQuayLaiChonPhim.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLaiChonPhim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_movie_32px.png"))); // NOI18N
        btnQuayLaiChonPhim.setText("Chọn phim khác");
        btnQuayLaiChonPhim.setToolTipText("Quay Lại");
        btnQuayLaiChonPhim.setContentAreaFilled(false);
        btnQuayLaiChonPhim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuayLaiChonPhim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuayLaiChonPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuayLaiChonPhimMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuayLaiChonPhimMouseExited(evt);
            }
        });
        btnQuayLaiChonPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiChonPhimActionPerformed(evt);
            }
        });

        labelRound1.setBackground(new java.awt.Color(153, 153, 153));
        labelRound1.setForeground(new java.awt.Color(255, 255, 255));
        labelRound1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound1.setText("MÀN HÌNH");
        labelRound1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        labelRound1.setRoundTopLeft(20);
        labelRound1.setRoundTopRight(20);

        panelRound4.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        panelRound5.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelRound6.setBackground(new java.awt.Color(204, 204, 0));

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        panelRound7.setBackground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Trống");

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Đã đặt");

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Đang đặt");

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Đang chọn");

        panelRound9.setBackground(new java.awt.Color(153, 153, 153));
        panelRound9.setRoundBottomLeft(20);
        panelRound9.setRoundBottomRight(20);

        pnlGhe.setBackground(new java.awt.Color(153, 153, 153));
        pnlGhe.setLayout(new java.awt.GridLayout(7, 6, 0, 10));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("A1");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton1FocusLost(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton1);

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("A2");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton2FocusLost(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton2);

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("A3");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton3FocusLost(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton3);

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("A4");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton4FocusLost(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton4);

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("A5");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton5FocusLost(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton5);

        jButton6.setBackground(new java.awt.Color(51, 51, 51));
        jButton6.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("A6");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton6FocusLost(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton6);

        jButton7.setBackground(new java.awt.Color(51, 51, 51));
        jButton7.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("B1");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton7FocusLost(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton7);

        jButton8.setBackground(new java.awt.Color(51, 51, 51));
        jButton8.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("B2");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton8FocusLost(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton8);

        jButton9.setBackground(new java.awt.Color(51, 51, 51));
        jButton9.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("B3");
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton9FocusLost(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton9);

        jButton10.setBackground(new java.awt.Color(51, 51, 51));
        jButton10.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("B4");
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton10FocusLost(evt);
            }
        });
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton10);

        jButton11.setBackground(new java.awt.Color(51, 51, 51));
        jButton11.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("B5");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton11FocusLost(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton11);

        jButton12.setBackground(new java.awt.Color(51, 51, 51));
        jButton12.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("B6");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton12FocusLost(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton12);

        jButton13.setBackground(new java.awt.Color(51, 51, 51));
        jButton13.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("C1");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton13FocusLost(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton13);

        jButton14.setBackground(new java.awt.Color(51, 51, 51));
        jButton14.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText("C2");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton14FocusLost(evt);
            }
        });
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton14);

        jButton15.setBackground(new java.awt.Color(51, 51, 51));
        jButton15.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("C3");
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton15FocusLost(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton15);

        jButton16.setBackground(new java.awt.Color(51, 51, 51));
        jButton16.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("C4");
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton16FocusLost(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton16);

        jButton17.setBackground(new java.awt.Color(51, 51, 51));
        jButton17.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("C5");
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton17FocusLost(evt);
            }
        });
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton17);

        jButton18.setBackground(new java.awt.Color(51, 51, 51));
        jButton18.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("C6");
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton18FocusLost(evt);
            }
        });
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton18);

        jButton19.setBackground(new java.awt.Color(51, 51, 51));
        jButton19.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("D1");
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton19FocusLost(evt);
            }
        });
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton19);

        jButton20.setBackground(new java.awt.Color(51, 51, 51));
        jButton20.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("D2");
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton20FocusLost(evt);
            }
        });
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton20);

        jButton21.setBackground(new java.awt.Color(51, 51, 51));
        jButton21.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("D3");
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton21FocusLost(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton21);

        jButton22.setBackground(new java.awt.Color(51, 51, 51));
        jButton22.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("D4");
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton22FocusLost(evt);
            }
        });
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton22);

        jButton23.setBackground(new java.awt.Color(51, 51, 51));
        jButton23.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("D5");
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton23.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton23FocusLost(evt);
            }
        });
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton23);

        jButton24.setBackground(new java.awt.Color(51, 51, 51));
        jButton24.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("D6");
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton24.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton24FocusLost(evt);
            }
        });
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton24);

        jButton25.setBackground(new java.awt.Color(51, 51, 51));
        jButton25.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("E1");
        jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton25.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton25FocusLost(evt);
            }
        });
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton25);

        jButton26.setBackground(new java.awt.Color(51, 51, 51));
        jButton26.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("E2");
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton26.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton26FocusLost(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton26);

        jButton27.setBackground(new java.awt.Color(51, 51, 51));
        jButton27.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("E3");
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton27.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton27FocusLost(evt);
            }
        });
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton27);

        jButton28.setBackground(new java.awt.Color(51, 51, 51));
        jButton28.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("E4");
        jButton28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton28.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton28FocusLost(evt);
            }
        });
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton28);

        jButton29.setBackground(new java.awt.Color(51, 51, 51));
        jButton29.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("E5");
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton29.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton29FocusLost(evt);
            }
        });
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton29);

        jButton30.setBackground(new java.awt.Color(51, 51, 51));
        jButton30.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("E6");
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton30.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton30FocusLost(evt);
            }
        });
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton30);

        jButton31.setBackground(new java.awt.Color(51, 51, 51));
        jButton31.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 255, 255));
        jButton31.setText("F1");
        jButton31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton31.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton31FocusLost(evt);
            }
        });
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton31);

        jButton32.setBackground(new java.awt.Color(51, 51, 51));
        jButton32.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 255, 255));
        jButton32.setText("F2");
        jButton32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton32.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton32FocusLost(evt);
            }
        });
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton32);

        jButton33.setBackground(new java.awt.Color(51, 51, 51));
        jButton33.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton33.setForeground(new java.awt.Color(255, 255, 255));
        jButton33.setText("F3");
        jButton33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton33.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton33FocusLost(evt);
            }
        });
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton33);

        jButton34.setBackground(new java.awt.Color(51, 51, 51));
        jButton34.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton34.setForeground(new java.awt.Color(255, 255, 255));
        jButton34.setText("F4");
        jButton34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton34.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton34FocusLost(evt);
            }
        });
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton34);

        jButton35.setBackground(new java.awt.Color(51, 51, 51));
        jButton35.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton35.setForeground(new java.awt.Color(255, 255, 255));
        jButton35.setText("F5");
        jButton35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton35.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton35FocusLost(evt);
            }
        });
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton35);

        jButton36.setBackground(new java.awt.Color(51, 51, 51));
        jButton36.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jButton36.setForeground(new java.awt.Color(255, 255, 255));
        jButton36.setText("F6");
        jButton36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton36.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton36FocusLost(evt);
            }
        });
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton36);

        jButton37.setBackground(new java.awt.Color(51, 51, 51));
        jButton37.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton37.setForeground(new java.awt.Color(255, 255, 255));
        jButton37.setText("G1&G2");
        jButton37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton37.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton37FocusLost(evt);
            }
        });
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton37);

        jButton38.setBackground(new java.awt.Color(51, 51, 51));
        jButton38.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton38.setForeground(new java.awt.Color(255, 255, 255));
        jButton38.setText("G3&G4");
        jButton38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton38.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton38FocusLost(evt);
            }
        });
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton38);

        jButton39.setBackground(new java.awt.Color(51, 51, 51));
        jButton39.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton39.setForeground(new java.awt.Color(255, 255, 255));
        jButton39.setText("G5&G6");
        jButton39.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton39.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jButton39FocusLost(evt);
            }
        });
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        pnlGhe.add(jButton39);

        verticalIndex.setBackground(new java.awt.Color(153, 153, 153));
        verticalIndex.setLayout(new java.awt.GridLayout(7, 1, 0, 10));

        labelRound5.setBackground(new java.awt.Color(153, 153, 153));
        labelRound5.setForeground(new java.awt.Color(255, 255, 255));
        labelRound5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound5.setText("A");
        labelRound5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelRound5.setRoundTopRight(30);
        verticalIndex.add(labelRound5);

        labelRound6.setBackground(new java.awt.Color(153, 153, 153));
        labelRound6.setForeground(new java.awt.Color(255, 255, 255));
        labelRound6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound6.setText("B");
        labelRound6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        verticalIndex.add(labelRound6);

        labelRound7.setBackground(new java.awt.Color(153, 153, 153));
        labelRound7.setForeground(new java.awt.Color(255, 255, 255));
        labelRound7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound7.setText("C");
        labelRound7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        verticalIndex.add(labelRound7);

        labelRound8.setBackground(new java.awt.Color(153, 153, 153));
        labelRound8.setForeground(new java.awt.Color(255, 255, 255));
        labelRound8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound8.setText("D");
        labelRound8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        verticalIndex.add(labelRound8);

        labelRound9.setBackground(new java.awt.Color(153, 153, 153));
        labelRound9.setForeground(new java.awt.Color(255, 255, 255));
        labelRound9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound9.setText("E");
        labelRound9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        verticalIndex.add(labelRound9);

        labelRound10.setBackground(new java.awt.Color(153, 153, 153));
        labelRound10.setForeground(new java.awt.Color(255, 255, 255));
        labelRound10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound10.setText("F");
        labelRound10.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        verticalIndex.add(labelRound10);

        labelRound11.setBackground(new java.awt.Color(153, 153, 153));
        labelRound11.setForeground(new java.awt.Color(255, 255, 255));
        labelRound11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound11.setText("G");
        labelRound11.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        labelRound11.setRoundBottomRight(30);
        verticalIndex.add(labelRound11);

        HorizoneIndex.setBackground(new java.awt.Color(255, 102, 102));
        HorizoneIndex.setLayout(new java.awt.GridLayout(1, 6));

        labelRound14.setBackground(new java.awt.Color(153, 153, 153));
        labelRound14.setForeground(new java.awt.Color(255, 255, 255));
        labelRound14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound14.setText("1");
        labelRound14.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        HorizoneIndex.add(labelRound14);

        labelRound15.setBackground(new java.awt.Color(153, 153, 153));
        labelRound15.setForeground(new java.awt.Color(255, 255, 255));
        labelRound15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound15.setText("2");
        labelRound15.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        HorizoneIndex.add(labelRound15);

        labelRound16.setBackground(new java.awt.Color(153, 153, 153));
        labelRound16.setForeground(new java.awt.Color(255, 255, 255));
        labelRound16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound16.setText("3");
        labelRound16.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        HorizoneIndex.add(labelRound16);

        labelRound17.setBackground(new java.awt.Color(153, 153, 153));
        labelRound17.setForeground(new java.awt.Color(255, 255, 255));
        labelRound17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound17.setText("4");
        labelRound17.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        HorizoneIndex.add(labelRound17);

        labelRound18.setBackground(new java.awt.Color(153, 153, 153));
        labelRound18.setForeground(new java.awt.Color(255, 255, 255));
        labelRound18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound18.setText("5");
        labelRound18.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        HorizoneIndex.add(labelRound18);

        labelRound19.setBackground(new java.awt.Color(153, 153, 153));
        labelRound19.setForeground(new java.awt.Color(255, 255, 255));
        labelRound19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound19.setText("6");
        labelRound19.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        HorizoneIndex.add(labelRound19);

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound9Layout.createSequentialGroup()
                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HorizoneIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlGhe, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verticalIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(verticalIndex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlGhe, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(HorizoneIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlChonGheLayout = new javax.swing.GroupLayout(pnlChonGhe);
        pnlChonGhe.setLayout(pnlChonGheLayout);
        pnlChonGheLayout.setHorizontalGroup(
            pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChonGheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChonGheLayout.createSequentialGroup()
                        .addComponent(btnQuayLaiChonPhim)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChonGheLayout.createSequentialGroup()
                        .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChonGheLayout.createSequentialGroup()
                                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChonGheLayout.createSequentialGroup()
                                .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChonGheLayout.createSequentialGroup()
                                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlChonGheLayout.createSequentialGroup()
                                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        pnlChonGheLayout.setVerticalGroup(
            pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChonGheLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(btnQuayLaiChonPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChonGheLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlChonGheLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlChonGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );

        panelRound2.add(pnlChonGhe, "card3");

        panelRound8.setBackground(new java.awt.Color(139, 0, 0));
        panelRound8.setRoundBottomLeft(30);
        panelRound8.setRoundTopLeft(30);

        lblNgayChieuCur.setBackground(new java.awt.Color(139, 0, 0));
        lblNgayChieuCur.setForeground(new java.awt.Color(204, 204, 204));
        lblNgayChieuCur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNgayChieuCur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_calendar_1_24px.png"))); // NOI18N
        lblNgayChieuCur.setText("<Ngày chiếu>");
        lblNgayChieuCur.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N

        lblSoPhongCur.setBackground(new java.awt.Color(139, 0, 0));
        lblSoPhongCur.setForeground(new java.awt.Color(204, 204, 204));
        lblSoPhongCur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSoPhongCur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_movie_theater_24px.png"))); // NOI18N
        lblSoPhongCur.setText("<Số phòng>");
        lblSoPhongCur.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N

        lblGioChieuCur.setBackground(new java.awt.Color(139, 0, 0));
        lblGioChieuCur.setForeground(new java.awt.Color(204, 204, 204));
        lblGioChieuCur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGioChieuCur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_clock_24px.png"))); // NOI18N
        lblGioChieuCur.setText("<Giờ chiếu>");
        lblGioChieuCur.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N

        lblSoGheCur.setBackground(new java.awt.Color(139, 0, 0));
        lblSoGheCur.setForeground(new java.awt.Color(204, 204, 204));
        lblSoGheCur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSoGheCur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_sitting_on_chair_24px.png"))); // NOI18N
        lblSoGheCur.setText("<Số ghế>");
        lblSoGheCur.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N

        txtTenPhimCur.setBackground(new java.awt.Color(139, 0, 0));
        txtTenPhimCur.setForeground(new java.awt.Color(204, 204, 204));
        txtTenPhimCur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTenPhimCur.setText("<Tên phim>");
        txtTenPhimCur.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        txtTenPhimCur.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txtTenPhimCur.setRoundTopRight(300);

        lblAnhPhimCur.setBackground(new java.awt.Color(102, 51, 0));
        lblAnhPhimCur.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblAnhPhimCur.setRoundBottomLeft(30);
        lblAnhPhimCur.setRoundBottomRight(30);
        lblAnhPhimCur.setRoundTopLeft(30);
        lblAnhPhimCur.setRoundTopRight(30);

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addComponent(lblAnhPhimCur, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound8Layout.createSequentialGroup()
                        .addComponent(lblNgayChieuCur, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGioChieuCur, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSoPhongCur, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSoGheCur, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                    .addGroup(panelRound8Layout.createSequentialGroup()
                        .addComponent(txtTenPhimCur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(194, 194, 194)))
                .addContainerGap())
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addComponent(txtTenPhimCur, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblGioChieuCur, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNgayChieuCur, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSoGheCur, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSoPhongCur, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(lblAnhPhimCur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblThemVe.setBackground(new java.awt.Color(204, 0, 0));
        lblThemVe.setForeground(new java.awt.Color(255, 255, 255));
        lblThemVe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThemVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_add_32px_1.png"))); // NOI18N
        lblThemVe.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        lblThemVe.setRoundBottomRight(30);
        lblThemVe.setRoundTopRight(30);
        lblThemVe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblThemVeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlDanhSachVeLayout = new javax.swing.GroupLayout(pnlDanhSachVe);
        pnlDanhSachVe.setLayout(pnlDanhSachVeLayout);
        pnlDanhSachVeLayout.setHorizontalGroup(
            pnlDanhSachVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDanhSachVeLayout.createSequentialGroup()
                .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(lblThemVe, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlDanhSachVeLayout.setVerticalGroup(
            pnlDanhSachVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDanhSachVeLayout.createSequentialGroup()
                .addGroup(pnlDanhSachVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblThemVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE))
        );

        labelRound12.setBackground(new java.awt.Color(51, 51, 51));
        labelRound12.setForeground(new java.awt.Color(255, 255, 255));
        labelRound12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_two_tickets_32px.png"))); // NOI18N
        labelRound12.setText("  Thông tin mua vé");
        labelRound12.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N

        javax.swing.GroupLayout pnlChinhLayout = new javax.swing.GroupLayout(pnlChinh);
        pnlChinh.setLayout(pnlChinhLayout);
        pnlChinhLayout.setHorizontalGroup(
            pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChinhLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(pnlDanhSachVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23)
                .addGroup(pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelRound12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlChinhLayout.setVerticalGroup(
            pnlChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChinhLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(pnlDanhSachVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChinhLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(labelRound12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Acount");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Actions");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("About");

        jLabel5.setBackground(new java.awt.Color(204, 51, 0));
        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Help");

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
        lblNgay.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        lblNgay.setRoundBottomLeft(30);
        lblNgay.setRoundBottomRight(30);
        lblNgay.setRoundTopLeft(30);
        lblNgay.setRoundTopRight(30);

        lblDongHo.setBackground(new java.awt.Color(139, 0, 0));
        lblDongHo.setForeground(new java.awt.Color(255, 255, 255));
        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setText("24:24");
        lblDongHo.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        lblDongHo.setRoundBottomLeft(30);
        lblDongHo.setRoundBottomRight(30);
        lblDongHo.setRoundTopLeft(30);
        lblDongHo.setRoundTopRight(30);

        labelRound4.setBackground(new java.awt.Color(139, 0, 0));
        labelRound4.setForeground(new java.awt.Color(255, 255, 255));
        labelRound4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRound4.setText("Xin chào nhân viên đặt vé");
        labelRound4.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        labelRound4.setRoundBottomLeft(30);
        labelRound4.setRoundBottomRight(30);
        labelRound4.setRoundTopLeft(30);
        labelRound4.setRoundTopRight(30);

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
                .addGap(12, 12, 12)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnResize, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSocketState, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlMovingLayout.setVerticalGroup(
            pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovingLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMinimize, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResize, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSocketState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMovingLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRound10Layout = new javax.swing.GroupLayout(panelRound10);
        panelRound10.setLayout(panelRound10Layout);
        panelRound10Layout.setHorizontalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound10Layout.createSequentialGroup()
                        .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAbout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAction, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addComponent(btnHelp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addComponent(pnlChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                        .addComponent(jLabel1)
                        .addGap(41, 41, 41)
                        .addComponent(btnAction, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(40, 40, 40)
                        .addComponent(btnAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(43, 43, 43))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblChonPhimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChonPhimMouseClicked
        int phim = tblChonPhim.getSelectedRow();
        if (evt.getClickCount() == 2) {
            //luu cac gia tri
            maLichChieuCur = dsMaLichChieu.get(phim)[0];
            anhCur = dsMaLichChieu.get(phim)[1];
            txtTenPhimCur.setText(phimCur = tblChonPhim.getValueAt(phim, 2).toString());
            lblNgayChieuCur.setText(ngayChieuCur = XDate.toString(dtcChonNgay.getDate(), "dd-MM-yyyy"));
            lblGioChieuCur.setText((gioChieuCur = tblChonPhim.getValueAt(phim, 3).toString().replace(":00", "")) + ":00");
            lblSoPhongCur.setText(soPhongCur = tblChonPhim.getValueAt(phim, 4).toString().replace("Phòng ", ""));
            lblAnhPhimCur.setIcon(XImage.resizeImg(new ImageIcon(getClass().getResource("/images/" + anhCur)), lblAnhPhimCur.getWidth(), lblAnhPhimCur.getHeight()));
            
            pnlChonPhim.setVisible(false);
            pnlChonGhe.setVisible(true);
            //set trang thai cac ghe
            setTrangThaiGhe();
        }
    }//GEN-LAST:event_tblChonPhimMouseClicked

    private void dtcChonNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtcChonNgayPropertyChange
        if (batdau) {
            batdau = false;
        } else {
            doDuLieuPhim();
        }
    }//GEN-LAST:event_dtcChonNgayPropertyChange

    private void tblDonMuaVeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonMuaVeMouseReleased
        if (SwingUtilities.isRightMouseButton(evt) && tblDonMuaVe.getSelectedRows().length > 0) {
            pmnXoa.show(tblDonMuaVe, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblDonMuaVeMouseReleased
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        chonGhe(0, jButton1);
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        chonGhe(0, jButton2);
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        chonGhe(0, jButton3);
    }//GEN-LAST:event_jButton3ActionPerformed
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        chonGhe(0, jButton4);
    }//GEN-LAST:event_jButton4ActionPerformed
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        chonGhe(0, jButton5);
    }//GEN-LAST:event_jButton5ActionPerformed
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        chonGhe(0, jButton6);
    }//GEN-LAST:event_jButton6ActionPerformed
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        chonGhe(0, jButton7);
    }//GEN-LAST:event_jButton7ActionPerformed
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        chonGhe(0, jButton8);
    }//GEN-LAST:event_jButton8ActionPerformed
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        chonGhe(0, jButton9);
    }//GEN-LAST:event_jButton9ActionPerformed
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        chonGhe(0, jButton10);
    }//GEN-LAST:event_jButton10ActionPerformed
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        chonGhe(0, jButton11);
    }//GEN-LAST:event_jButton11ActionPerformed
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        chonGhe(0, jButton12);
    }//GEN-LAST:event_jButton12ActionPerformed
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        chonGhe(0, jButton13);
    }//GEN-LAST:event_jButton13ActionPerformed
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        chonGhe(0, jButton14);
    }//GEN-LAST:event_jButton14ActionPerformed
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        chonGhe(0, jButton15);
    }//GEN-LAST:event_jButton15ActionPerformed
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        chonGhe(0, jButton16);
    }//GEN-LAST:event_jButton16ActionPerformed
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        chonGhe(0, jButton17);
    }//GEN-LAST:event_jButton17ActionPerformed
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        chonGhe(0, jButton18);
    }//GEN-LAST:event_jButton18ActionPerformed
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        chonGhe(0, jButton19);
    }//GEN-LAST:event_jButton19ActionPerformed
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        chonGhe(0, jButton20);
    }//GEN-LAST:event_jButton20ActionPerformed
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        chonGhe(0, jButton21);
    }//GEN-LAST:event_jButton21ActionPerformed
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        chonGhe(0, jButton22);
    }//GEN-LAST:event_jButton22ActionPerformed
    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        chonGhe(0, jButton23);
    }//GEN-LAST:event_jButton23ActionPerformed
    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        chonGhe(0, jButton24);
    }//GEN-LAST:event_jButton24ActionPerformed
    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        chonGhe(0, jButton25);
    }//GEN-LAST:event_jButton25ActionPerformed
    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        chonGhe(0, jButton26);
    }//GEN-LAST:event_jButton26ActionPerformed
    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        chonGhe(0, jButton27);
    }//GEN-LAST:event_jButton27ActionPerformed
    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        chonGhe(0, jButton28);
    }//GEN-LAST:event_jButton28ActionPerformed
    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        chonGhe(0, jButton29);
    }//GEN-LAST:event_jButton29ActionPerformed
    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        chonGhe(0, jButton30);
    }//GEN-LAST:event_jButton30ActionPerformed
    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        chonGhe(0, jButton31);
    }//GEN-LAST:event_jButton31ActionPerformed
    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        chonGhe(0, jButton32);
    }//GEN-LAST:event_jButton32ActionPerformed
    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        chonGhe(0, jButton33);
    }//GEN-LAST:event_jButton33ActionPerformed
    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        chonGhe(0, jButton34);
    }//GEN-LAST:event_jButton34ActionPerformed
    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        chonGhe(0, jButton35);
    }//GEN-LAST:event_jButton35ActionPerformed
    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        chonGhe(0, jButton36);
    }//GEN-LAST:event_jButton36ActionPerformed
    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        chonGhe(0, jButton37);
    }//GEN-LAST:event_jButton37ActionPerformed
    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        chonGhe(0, jButton38);
    }//GEN-LAST:event_jButton38ActionPerformed
    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        chonGhe(0, jButton39);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton1FocusLost
        chonGhe(1, jButton1);
    }//GEN-LAST:event_jButton1FocusLost
    private void jButton2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton2FocusLost
        chonGhe(1, jButton2);
    }//GEN-LAST:event_jButton2FocusLost
    private void jButton3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton3FocusLost
        chonGhe(1, jButton3);
    }//GEN-LAST:event_jButton3FocusLost
    private void jButton4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton4FocusLost
        chonGhe(1, jButton4);
    }//GEN-LAST:event_jButton4FocusLost
    private void jButton5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton5FocusLost
        chonGhe(1, jButton5);
    }//GEN-LAST:event_jButton5FocusLost
    private void jButton6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton6FocusLost
        chonGhe(1, jButton6);
    }//GEN-LAST:event_jButton6FocusLost

    private void jButton7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton7FocusLost
        chonGhe(1, jButton7);
    }//GEN-LAST:event_jButton7FocusLost

    private void jButton8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton8FocusLost
        chonGhe(1, jButton8);
    }//GEN-LAST:event_jButton8FocusLost

    private void jButton9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton9FocusLost
        chonGhe(1, jButton9);
    }//GEN-LAST:event_jButton9FocusLost

    private void jButton10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton10FocusLost
        chonGhe(1, jButton10);
    }//GEN-LAST:event_jButton10FocusLost

    private void jButton11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton11FocusLost
        chonGhe(1, jButton11);
    }//GEN-LAST:event_jButton11FocusLost

    private void jButton12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton12FocusLost
        chonGhe(1, jButton12);
    }//GEN-LAST:event_jButton12FocusLost

    private void jButton13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton13FocusLost
        chonGhe(1, jButton13);
    }//GEN-LAST:event_jButton13FocusLost

    private void jButton14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton14FocusLost
        chonGhe(1, jButton14);
    }//GEN-LAST:event_jButton14FocusLost

    private void jButton15FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton15FocusLost
        chonGhe(1, jButton15);
    }//GEN-LAST:event_jButton15FocusLost

    private void jButton16FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton16FocusLost
        chonGhe(1, jButton16);
    }//GEN-LAST:event_jButton16FocusLost

    private void jButton17FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton17FocusLost
        chonGhe(1, jButton17);
    }//GEN-LAST:event_jButton17FocusLost

    private void jButton18FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton18FocusLost
        chonGhe(1, jButton18);
    }//GEN-LAST:event_jButton18FocusLost

    private void jButton19FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton19FocusLost
        chonGhe(1, jButton19);
    }//GEN-LAST:event_jButton19FocusLost

    private void jButton20FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton20FocusLost
        chonGhe(1, jButton20);
    }//GEN-LAST:event_jButton20FocusLost

    private void jButton21FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton21FocusLost
        chonGhe(1, jButton21);
    }//GEN-LAST:event_jButton21FocusLost

    private void jButton22FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton22FocusLost
        chonGhe(1, jButton22);
    }//GEN-LAST:event_jButton22FocusLost

    private void jButton23FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton23FocusLost
        chonGhe(1, jButton23);
    }//GEN-LAST:event_jButton23FocusLost

    private void jButton24FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton24FocusLost
        chonGhe(1, jButton24);
    }//GEN-LAST:event_jButton24FocusLost

    private void jButton25FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton25FocusLost
        chonGhe(1, jButton25);
    }//GEN-LAST:event_jButton25FocusLost

    private void jButton26FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton26FocusLost
        chonGhe(1, jButton26);
    }//GEN-LAST:event_jButton26FocusLost

    private void jButton27FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton27FocusLost
        chonGhe(1, jButton27);
    }//GEN-LAST:event_jButton27FocusLost

    private void jButton28FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton28FocusLost
        chonGhe(1, jButton28);
    }//GEN-LAST:event_jButton28FocusLost

    private void jButton29FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton29FocusLost
        chonGhe(1, jButton29);
    }//GEN-LAST:event_jButton29FocusLost

    private void jButton30FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton30FocusLost
        chonGhe(1, jButton30);
    }//GEN-LAST:event_jButton30FocusLost

    private void jButton31FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton31FocusLost
        chonGhe(1, jButton31);
    }//GEN-LAST:event_jButton31FocusLost

    private void jButton32FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton32FocusLost
        chonGhe(1, jButton32);
    }//GEN-LAST:event_jButton32FocusLost

    private void jButton33FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton33FocusLost
        chonGhe(1, jButton33);
    }//GEN-LAST:event_jButton33FocusLost

    private void jButton34FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton34FocusLost
        chonGhe(1, jButton34);
    }//GEN-LAST:event_jButton34FocusLost

    private void jButton35FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton35FocusLost
        chonGhe(1, jButton35);
    }//GEN-LAST:event_jButton35FocusLost

    private void jButton36FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton36FocusLost
        chonGhe(1, jButton36);
    }//GEN-LAST:event_jButton36FocusLost

    private void jButton37FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton37FocusLost
        chonGhe(1, jButton37);
    }//GEN-LAST:event_jButton37FocusLost

    private void jButton38FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton38FocusLost
        chonGhe(1, jButton38);
    }//GEN-LAST:event_jButton38FocusLost

    private void jButton39FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jButton39FocusLost
        chonGhe(1, jButton39);
    }//GEN-LAST:event_jButton39FocusLost

    private void lblThemVeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThemVeMouseClicked
        if (lblThemVe.getBackground() == sai) {
            MsgBox.alert(this, "Vé đã có trong đơn hoặc vé chưa đủ thông tin !\nVui lòng kiểm tra và thử lại sau.");
        } else {
            themVe();
            lblThemVe.setBackground(sai);
            lblSoGheCur.setText("<Số ghế>");
        }
    }//GEN-LAST:event_lblThemVeMouseClicked

    private void mnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnXoaActionPerformed
        xoaVe();
    }//GEN-LAST:event_mnXoaActionPerformed

    private void lblThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThanhToanMouseClicked
        if (dsDatVe.size() > 0) {
            luuVe();
            setTrangThaiGhe();
            dsDatVe = new ArrayList();
            refreshDonMuaVe();
            lblThemVe.setBackground(sai);
            soGheCur = "";
            lblSoGheCur.setText("<Số ghế>");
        } else {
            MsgBox.alert(this, "Chưa có vé nào được tạo !");
        }
    }//GEN-LAST:event_lblThanhToanMouseClicked

    private void txtTimPhimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimPhimKeyReleased
        timPhimTrongNgay();
    }//GEN-LAST:event_txtTimPhimKeyReleased

    private void btnXoaTimKiemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTimKiemNVActionPerformed
        txtTimPhim.setText("");
        timPhimTrongNgay();
        txtTimPhim.setText("Tìm kiếm");
    }//GEN-LAST:event_btnXoaTimKiemNVActionPerformed

    private void lblThanhToanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThanhToanMouseEntered
        lblThanhToan.setBackground(new Color(0, 240, 0));
    }//GEN-LAST:event_lblThanhToanMouseEntered

    private void lblThanhToanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblThanhToanMouseExited
        lblThanhToan.setBackground(new Color(0, 204, 0));
    }//GEN-LAST:event_lblThanhToanMouseExited

    private void txtTimPhimFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimPhimFocusGained
        if (txtTimPhim.getText().equals("Tìm kiếm")) {
            txtTimPhim.setText("");
        }
    }//GEN-LAST:event_txtTimPhimFocusGained

    private void txtTimPhimFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimPhimFocusLost
        if (txtTimPhim.getText().equals("")) {
            txtTimPhim.setText("Tìm kiếm");
        }
    }//GEN-LAST:event_txtTimPhimFocusLost

    private void btnQuayLaiChonPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiChonPhimActionPerformed
        pnlChonGhe.setVisible(false);
        pnlChonPhim.setVisible(true);
        lblThemVe.setBackground(sai);
        soGheCur = "";
        lblSoGheCur.setText("<Số ghế>");
    }//GEN-LAST:event_btnQuayLaiChonPhimActionPerformed

    private void btnQuayLaiChonPhimMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiChonPhimMouseEntered
        btnQuayLaiChonPhim.setContentAreaFilled(true);
    }//GEN-LAST:event_btnQuayLaiChonPhimMouseEntered

    private void btnQuayLaiChonPhimMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiChonPhimMouseExited
        btnQuayLaiChonPhim.setContentAreaFilled(false);
    }//GEN-LAST:event_btnQuayLaiChonPhimMouseExited

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

    private void lblDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseEntered
        lblDangXuat.setBackground(new Color(70, 70, 70));
    }//GEN-LAST:event_lblDangXuatMouseEntered

    private void lblDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseExited
        lblDangXuat.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_lblDangXuatMouseExited
    //cai dat title bar custom va frame
    private int xMouse, yMouse;
    private void pnlMovingMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMovingMousePressed
        if (this.getExtendedState() != JFrame.MAXIMIZED_BOTH && SwingUtilities.isLeftMouseButton(evt)) {
            xMouse = evt.getX() + 3;
            yMouse = evt.getY() + 3;
        }
    }//GEN-LAST:event_pnlMovingMousePressed

    private void pnlMovingMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMovingMouseDragged
        if (SwingUtilities.isLeftMouseButton(evt)) {
            if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                this.setExtendedState(JFrame.NORMAL);
            }
            this.setLocation(evt.getXOnScreen() - xMouse, evt.getYOnScreen() - yMouse);
        }
    }//GEN-LAST:event_pnlMovingMouseDragged

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        if (MsgBox.confirm(this, "Bạn có chắc muốn thoát?")) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitMouseClicked

    private void btnResizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResizeMouseClicked
        if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
            this.setExtendedState(JFrame.NORMAL);
        } else {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_btnResizeMouseClicked

    private void pnlMovingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMovingMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2) {
            if (this.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                this.setExtendedState(JFrame.NORMAL);
            } else {
                this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        }
    }//GEN-LAST:event_pnlMovingMouseClicked

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_close_24px.png")));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setIcon(null);
    }//GEN-LAST:event_btnExitMouseExited

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

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_horizontal_line_24px_1.png")));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setIcon(null);
    }//GEN-LAST:event_btnMinimizeMouseExited
    
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

    private void btnAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAccountFocusGained
        btnAccount.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_account_32px_1.png")));
    }//GEN-LAST:event_btnAccountFocusGained

    private void btnAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnAccountFocusLost
        btnAccount.setIcon(new ImageIcon(getClass().getResource("/icons/" + "icons8_account_32px.png")));
    }//GEN-LAST:event_btnAccountFocusLost

    private void btnAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccountMouseClicked
        chucnang = 1;
        stateFunction();
    }//GEN-LAST:event_btnAccountMouseClicked

    private void btnActionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActionMouseClicked
        chucnang = 2;
        stateFunction();
    }//GEN-LAST:event_btnActionMouseClicked

    private void btnAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseClicked
        chucnang = 3;
        stateFunction();

    }//GEN-LAST:event_btnAboutMouseClicked

    private void lblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDangXuatMouseClicked
        this.dispose();
        Auth.clear();
        new DangNhap().setVisible(true);
    }//GEN-LAST:event_lblDangXuatMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(DatVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatVe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatVe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HorizoneIndex;
    private ui.LabelRound btnAbout;
    private ui.LabelRound btnAccount;
    private ui.LabelRound btnAction;
    private ui.LabelRound btnExit;
    private ui.LabelRound btnHelp;
    private ui.LabelRound btnMinimize;
    private javax.swing.JButton btnQuayLaiChonPhim;
    private ui.LabelRound btnResize;
    private javax.swing.JButton btnXoaTimKiemNV;
    private com.toedter.calendar.JDateChooser dtcChonNgay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private ui.LabelRound labelRound1;
    private ui.LabelRound labelRound10;
    private ui.LabelRound labelRound11;
    private ui.LabelRound labelRound12;
    private ui.LabelRound labelRound13;
    private ui.LabelRound labelRound14;
    private ui.LabelRound labelRound15;
    private ui.LabelRound labelRound16;
    private ui.LabelRound labelRound17;
    private ui.LabelRound labelRound18;
    private ui.LabelRound labelRound19;
    private ui.LabelRound labelRound2;
    private ui.LabelRound labelRound20;
    private ui.LabelRound labelRound3;
    private ui.LabelRound labelRound4;
    private ui.LabelRound labelRound5;
    private ui.LabelRound labelRound6;
    private ui.LabelRound labelRound7;
    private ui.LabelRound labelRound8;
    private ui.LabelRound labelRound9;
    private ui.LabelRound lblAnhPhimCur;
    private ui.LabelRound lblDangXuat;
    private javax.swing.JLabel lblDongChon;
    private ui.LabelRound lblDongHo;
    private ui.LabelRound lblGioChieuCur;
    private ui.LabelRound lblNgay;
    private ui.LabelRound lblNgayChieuCur;
    private ui.LabelRound lblNgayLap;
    private ui.LabelRound lblSoGheCur;
    private ui.LabelRound lblSoLuong;
    private ui.LabelRound lblSoPhongCur;
    private javax.swing.JLabel lblSocketState;
    private ui.LabelRound lblThanhToan;
    private ui.LabelRound lblThemVe;
    private ui.LabelRound lblTongTien;
    private javax.swing.JMenuItem mnChiTiet;
    private javax.swing.JMenuItem mnXoa;
    private ui.PanelRound panelRound1;
    private ui.PanelRound panelRound10;
    private ui.PanelRound panelRound18;
    private ui.PanelRound panelRound2;
    private ui.PanelRound panelRound3;
    private ui.PanelRound panelRound4;
    private ui.PanelRound panelRound5;
    private ui.PanelRound panelRound6;
    private ui.PanelRound panelRound7;
    private ui.PanelRound panelRound8;
    private ui.PanelRound panelRound9;
    private javax.swing.JPopupMenu pmnXoa;
    private ui.PanelRound pnlChinh;
    private ui.PanelRound pnlChonGhe;
    private ui.PanelRound pnlChonPhim;
    private javax.swing.JPanel pnlDanhSachVe;
    private javax.swing.JPanel pnlGhe;
    private ui.PanelRound pnlMoving;
    private javax.swing.JScrollPane scrChonPhim;
    private javax.swing.JTable tblChonPhim;
    private javax.swing.JTable tblDonMuaVe;
    private ui.LabelRound txtTenPhimCur;
    private javax.swing.JTextField txtTimPhim;
    private javax.swing.JPanel verticalIndex;
    // End of variables declaration//GEN-END:variables

    private ComponentResizer resize;
    
    public void initResize() {
        resize = new ComponentResizer();
        resize.setSnapSize(new Dimension(10, 10));
        resize.setMinimumSize(new Dimension(300, 200));
        resize.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        resize.registerComponent(this);
    }
    VeDao daov = new VeDao();
    List<Object[]> listVeDaDatTrongNgay;
    List<Object[]> listDsPhimChieuTrongNgay;
    
    void doDuLieuPhim() {
        anhCur = null;
        lblAnhPhimCur.setIcon(null);
        txtTenPhimCur.setText(phimCur = "<Tên phim>");
        lblNgayChieuCur.setText(ngayChieuCur = "<Ngày chiếu>");
        lblGioChieuCur.setText(ngayChieuCur = "<Giờ chiếu>");
        lblSoPhongCur.setText(soPhongCur = "<Số phòng>");
        lblSoGheCur.setText(soGheCur = "<Số ghế>");

//        System.out.println(XDate.toString(dtcChonNgay.getDate(), "yyyy-MM-dd"));
        txtTimPhim.setText("Tìm kiếm");
        listDsPhimChieuTrongNgay = daov.getDsPhimChieuTrongNgay(XDate.toString(dtcChonNgay.getDate(), "yyyy-MM-dd"));
        String[] TenCot = {"", "", "", "", ""};
        DefaultTableModel dtm = new DefaultTableModel(null, TenCot) {
            // ghi đè kiểu của column để chuyển sang kiểu ImageIcon
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 1) {
                    return ImageIcon.class;
                }
                return Object.class;
            }
        };
        dtm.setRowCount(0);
        
        int STT = 0;
        dsMaLichChieu = new ArrayList();
        for (Object[] ph : listDsPhimChieuTrongNgay) {
            dsMaLichChieu.add(new String[]{ph[4] + "", ph[0].toString()});
//            System.out.println(Arrays.toString(ph));
            STT++;
            ImageIcon Hinh = null;
            if (ph[0] != null) {
                System.out.println(ph[0]);
                ImageIcon im = new ImageIcon(getClass().getResource("/images/" + ph[0]));
                Hinh = XImage.resizeImg(im, 200, tblChonPhim.getRowHeight());
            }
            Object[] row = {
                STT,
                Hinh,
                ph[1],
                ph[2] + ":00",
                "Phòng " + ph[3]
            };
            dtm.addRow(row);
        }
        tblChonPhim.setModel(dtm);
        lblDongChon.setText(tblChonPhim.getRowCount() + " dòng");
        //chinh do rong cot bang phim
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);
        render.setBackground(new Color(255, 218, 234));
        render.setFont(new Font("Arial", Font.ITALIC, 25));
        
        TableColumnModel model = tblChonPhim.getColumnModel();
        model.getColumn(0).setMaxWidth(40);
        model.getColumn(1).setMaxWidth(200);
        model.getColumn(1).setMinWidth(200);
        model.getColumn(3).setMaxWidth(100);
        model.getColumn(4).setMaxWidth(200);
        model.getColumn(4).setMinWidth(200);
        
        model.getColumn(0).setCellRenderer(render);
        model.getColumn(3).setCellRenderer(render);
        model.getColumn(4).setCellRenderer(render);
    }
    
    void timPhimTrongNgay() {
        DefaultTableModel model = (DefaultTableModel) tblChonPhim.getModel();
        model.setRowCount(0);
        dsMaLichChieu = new ArrayList();
        
        String keyWord = txtTimPhim.getText();
        int STT = 0;
        for (Object[] ob : listDsPhimChieuTrongNgay) {
            if ((ob[1] + "").toLowerCase().contains(keyWord.toLowerCase()) || (ob[2] + "").toLowerCase().contains(keyWord.toLowerCase()) || (ob[3] + "").toLowerCase().contains(keyWord.toLowerCase())) {
                dsMaLichChieu.add(new String[]{ob[4] + "", ob[0].toString()});
//            System.out.println(Arrays.toString(ph));
                STT++;
                ImageIcon Hinh = null;
                if (ob[0] != null) {
                    Hinh = XImage.resizeImg(new ImageIcon(getClass().getResource("/images/" + ob[0])), 200, tblChonPhim.getRowHeight());
                }
                model.addRow(new Object[]{
                    STT,
                    Hinh,
                    ob[1],
                    ob[2] + ":00",
                    "Phòng " + ob[3]
                });
            }
        }
        lblDongChon.setText(tblChonPhim.getRowCount() + " dòng");
    }
    
    Color danhDauGhe(String ghe) {
        Color color = trong;

        //ghe da dat
        listVeDaDatTrongNgay = daov.getDsGheDaDatTrongNgay(XDate.toString(dtcChonNgay.getDate(), "yyyy-MM-dd"), soPhongCur, Integer.parseInt(gioChieuCur));
//        System.out.println(ngayChieuCur + " " + soPhongCur + " " + gioChieuCur);
        for (Object[] ob : listVeDaDatTrongNgay) {
//            System.out.println(ob[0]);
            if (ob[0].equals(ghe)) {
                color = this.daDat;
                return color;
            }
        }
        //ghe da co trong don
        for (Object[] objects : dsDatVe) {
//            System.out.println(Arrays.toString(objects));
            if (ngayChieuCur.equals(objects[7]) && ghe.equals(objects[2]) && soPhongCur.equals(objects[3]) && gioChieuCur.equals(objects[8])) {
                color = this.coTrongDon;
                return color;
            }
        }
        return color;
    }
    
    boolean coGhe(JButton button) {
        GheDao daogh = new GheDao();
        return daogh.selectById(button.getText(), soPhongCur) != null;
    }
    
    void setTrangThaiGhe() {
        jButton1.setBackground(danhDauGhe("A1"));
        jButton2.setBackground(danhDauGhe("A2"));
        jButton3.setBackground(danhDauGhe("A3"));
        jButton4.setBackground(danhDauGhe("A4"));
        jButton5.setBackground(danhDauGhe("A5"));
        jButton6.setBackground(danhDauGhe("A6"));
        
        jButton7.setBackground(danhDauGhe("B1"));
        jButton8.setBackground(danhDauGhe("B2"));
        jButton9.setBackground(danhDauGhe("B3"));
        jButton10.setBackground(danhDauGhe("B4"));
        jButton11.setBackground(danhDauGhe("B5"));
        jButton12.setBackground(danhDauGhe("B6"));
        
        jButton13.setBackground(danhDauGhe("C1"));
        jButton14.setBackground(danhDauGhe("C2"));
        jButton15.setBackground(danhDauGhe("C3"));
        jButton16.setBackground(danhDauGhe("C4"));
        jButton17.setBackground(danhDauGhe("C5"));
        jButton18.setBackground(danhDauGhe("C6"));
        
        jButton19.setBackground(danhDauGhe("D1"));
        jButton20.setBackground(danhDauGhe("D2"));
        jButton21.setBackground(danhDauGhe("D3"));
        jButton22.setBackground(danhDauGhe("D4"));
        jButton23.setBackground(danhDauGhe("D5"));
        jButton24.setBackground(danhDauGhe("D6"));
        
        jButton25.setBackground(danhDauGhe("E1"));
        jButton26.setBackground(danhDauGhe("E2"));
        jButton27.setBackground(danhDauGhe("E3"));
        jButton28.setBackground(danhDauGhe("E4"));
        jButton29.setBackground(danhDauGhe("E5"));
        jButton30.setBackground(danhDauGhe("E6"));
        
        jButton31.setBackground(danhDauGhe("F1"));
        jButton32.setBackground(danhDauGhe("F2"));
        jButton33.setBackground(danhDauGhe("F3"));
        jButton34.setBackground(danhDauGhe("F4"));
        jButton35.setBackground(danhDauGhe("F5"));
        jButton36.setBackground(danhDauGhe("F6"));
        
        jButton37.setBackground(danhDauGhe("G1&G2"));
        jButton38.setBackground(danhDauGhe("G3&G4"));
        jButton39.setBackground(danhDauGhe("G5&G6"));
        
        jButton1.setVisible(coGhe(jButton1));
        jButton2.setVisible(coGhe(jButton2));
        jButton3.setVisible(coGhe(jButton3));
        jButton4.setVisible(coGhe(jButton4));
        jButton5.setVisible(coGhe(jButton5));
        jButton6.setVisible(coGhe(jButton6));
        
        jButton7.setVisible(coGhe(jButton7));
        jButton8.setVisible(coGhe(jButton8));
        jButton9.setVisible(coGhe(jButton9));
        jButton10.setVisible(coGhe(jButton10));
        jButton11.setVisible(coGhe(jButton11));
        jButton12.setVisible(coGhe(jButton12));
        
        jButton13.setVisible(coGhe(jButton13));
        jButton14.setVisible(coGhe(jButton14));
        jButton15.setVisible(coGhe(jButton15));
        jButton16.setVisible(coGhe(jButton16));
        jButton17.setVisible(coGhe(jButton17));
        jButton18.setVisible(coGhe(jButton18));
        
        jButton19.setVisible(coGhe(jButton19));
        jButton20.setVisible(coGhe(jButton20));
        jButton21.setVisible(coGhe(jButton21));
        jButton22.setVisible(coGhe(jButton22));
        jButton23.setVisible(coGhe(jButton23));
        jButton24.setVisible(coGhe(jButton24));
        
        jButton25.setVisible(coGhe(jButton25));
        jButton26.setVisible(coGhe(jButton26));
        jButton27.setVisible(coGhe(jButton27));
        jButton28.setVisible(coGhe(jButton28));
        jButton29.setVisible(coGhe(jButton29));
        jButton30.setVisible(coGhe(jButton30));
        
        jButton31.setVisible(coGhe(jButton31));
        jButton32.setVisible(coGhe(jButton32));
        jButton33.setVisible(coGhe(jButton33));
        jButton34.setVisible(coGhe(jButton34));
        jButton35.setVisible(coGhe(jButton35));
        jButton36.setVisible(coGhe(jButton36));
        
        jButton37.setVisible(coGhe(jButton37));
        jButton38.setVisible(coGhe(jButton38));
        jButton39.setVisible(coGhe(jButton39));
//        System.out.println("da chay hamf set");
    }
    
    void chonGhe(int c, JButton button) {
        //nut mat focus
        if (c == 1 && button.getBackground() != daDat && button.getBackground() != coTrongDon) {
            button.setBackground(trong);
            return;
        }
        //Chon ghe trong
        if (button.getBackground() != daDat && button.getBackground() != coTrongDon) {
            button.setBackground(chon);
            lblThemVe.setBackground(new Color(204, 0, 0));
            soGheCur = button.getText();
            lblSoGheCur.setText(button.getText());
        }
        //Chon ghe da dat hoac da co trong don
        if (button.getBackground() == daDat || button.getBackground() == coTrongDon) {
            lblThemVe.setBackground(sai);
            soGheCur = "";
            lblSoGheCur.setText("<Trống>");
        }
    }
    
    void refreshDonMuaVe() {
        //hien thi len bang don mua ve
        fillTableDonMuaVe();
        //set so luong ve va tong tien
        lblSoLuong.setText(tblDonMuaVe.getRowCount() + "");
        tinhTongTien();
        lblTongTien.setText(tongTien + "");
        //set trang thai ghe
        setTrangThaiGhe();
    }
    
    void resetDonMuaVe() {
        
    }
    
    void xoaVe() {
        int[] ve = tblDonMuaVe.getSelectedRows();
        for (int i = ve.length - 1; i > -1; i--) {
            for (int j = dsDatVe.size() - 1; j > -1; j--) {
                if (ve[i] == j) {
                    dsDatVe.remove(j);
                    break;
                }
            }
        }
        refreshDonMuaVe();
    }
    
    void themVe() {
        //luu vao danh sach tam
        dsDatVe.add(new Object[]{
            Auth.user.getMaUser(),
            XDate.toString(new Date(), "yyyy-MM-dd"),
            soGheCur,
            soPhongCur,
            maLichChieuCur,
            daov.getGiaGhe(soGheCur, soPhongCur),
            txtTenPhimCur.getText(),
            ngayChieuCur,
            gioChieuCur
        }
        );
        refreshDonMuaVe();
    }
    
    void fillTableDonMuaVe() {
        DefaultTableModel model = (DefaultTableModel) tblDonMuaVe.getModel();
        model.setRowCount(0);
        for (Object[] objects : dsDatVe) {
            model.addRow(
                    new Object[]{objects[6], objects[2], objects[3], objects[7]}
            );
        }
    }
    
    void tinhTongTien() {
        tongTien = 0;
        for (Object[] objects : dsDatVe) {
            tongTien += Integer.parseInt(objects[5].toString().substring(0, objects[5].toString().length() - 2));
        }
    }
    
    void luuVe() {
        int soVe = 0;
        for (Object[] objects : dsDatVe) {
            Ve ve = new Ve();
            ve.setMaNV(objects[0] != null ? objects[0].toString() : "");
            ve.setMaGhe(objects[2].toString());
            ve.setMaPhong(objects[3].toString());
            ve.setMaLichChieu(Integer.parseInt(objects[4].toString()));
            daov.insert(ve);
            //tao ma vach
            String maVach = daov.getMaVe(objects[2], objects[3], objects[4]);
            String pathMaVach = (getClass().getResource("/BarcodeImg/") + maVach + ".png").substring(6);
            System.out.println(pathMaVach);
            taoMaVach(pathMaVach, maVach);
            ++soVe;
            
            try {
                Hashtable map = new Hashtable();
                String path = getClass().getResource("/ui/report/VeXemPhim.jrxml").toString().replace("file:/", "");
                JasperReport rpt = JasperCompileManager.compileReport(path);
                map.put("mave", maVach);
                map.put("pathBarCode", pathMaVach);
                map.put("pathImg", getClass().getResource("/icons/cinesys_round_logo.png").toString());
                Connection conn = DriverManager.getConnection(XJdbc.getDburl(), XJdbc.getUsername(), XJdbc.getPassword());
                JasperPrint p = JasperFillManager.fillReport(rpt, map, conn);
                //Xem truoc khi in
//                JasperViewer.viewReport(p, false);
                //in hoa don
                JasperPrintManager.printReport(p, false);
            } catch (JRException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        MsgBox.alert(this, "Thanh toán " + soVe + " vé mới thành công!");
        
        if (Auth.connectSocket) {
            try {
                PrintStream ps = new PrintStream(clientSocket.getOutputStream());
                new DataOutputStream(clientSocket.getOutputStream()).writeUTF(Auth.user.getHoTen() + " vửa bán " + soVe + " vé mới thành công!");
//                ps.println(Auth.user.getHoTen() + " vửa bán " + soVe + " vé mới thành công!");
                System.out.println("da gui tin hieu");
            } catch (IOException ex) {
                System.out.println("loi duong truyen");
                ex.printStackTrace();
            }
        }
    }
    
    void taoMaVach(String path, String codeString) {
        try {
            Code128Writer writer = new Code128Writer();
            BitMatrix matrix = writer.encode(codeString, BarcodeFormat.CODE_128, 1000, 300);
            MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(path));
            MsgBox.alert(this, "Tạo mã vạch thành công");
        } catch (WriterException | IOException ex) {
            System.out.println("Tao barcode bi loi");
            ex.printStackTrace();
        }
    }
    
}
