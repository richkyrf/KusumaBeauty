/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proses;

import KomponenGUI.FDateF;
import static KomponenGUI.FDateF.datetostr;
import Master.*;
import LSubProces.DRunSelctOne;
import LSubProces.Insert;
import LSubProces.MultiInsert;
import LSubProces.RunSelct;
import LSubProces.Update;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;
import static java.lang.String.format;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import static javax.print.DocFlavor.INPUT_STREAM.AUTOSENSE;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import static javax.print.PrintServiceLookup.lookupDefaultPrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import static javax.print.attribute.Size2DSyntax.INCH;
import javax.print.attribute.standard.Copies;
import static javax.print.attribute.standard.MediaSize.findMedia;
import static javax.print.attribute.standard.OrientationRequested.LANDSCAPE;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author richky
 */
public class Penjualan extends javax.swing.JFrame {

    /**
     * Creates new form MasterPenjualan
     */
    String IdEdit;

    public Penjualan() {
        initComponents();
        setVisible(true);
        setTitle("Tambah Penjualan");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jbuttonF3.setVisible(false);
        jbuttonF9.setVisible(false);
        JCPasien.requestFocus();
        JTNoTransaksi.setText(getNoPenjualan());
    }

    public Penjualan(Object idEdit) {
        IdEdit = idEdit.toString();
        initComponents();
        setVisible(true);
        setTitle("Ubah Penjualan");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jbuttonF1.setVisible(false);
        jbuttonF2.setVisible(false);
        jbuttonF8.setVisible(false);
        loadeditdata();
        JCPasien.requestFocus();
    }

    void cariBarang(String keywordCari) {
        if (GlobalVar.Var.jcari == null) {
            GlobalVar.Var.jcari = new Jcari("SELECT `JenisBarang`, `NamaBarang` FROM `tbmbarang`a JOIN `tbsmjenisbarang`b ON a.`IdJenisBarang`=b.`IdJenisBarang` WHERE `JenisBarang` ", "SELECT `JenisBarang`, `NamaBarang` FROM `tbmbarang`a JOIN `tbsmjenisbarang`b ON a.`IdJenisBarang`=b.`IdJenisBarang` WHERE `NamaBarang` ", "Jenis Barang", "Nama Barang", "Cari Barang", 1, JTNamaBarang, JTNamaBarang.getText());
        } else {
            GlobalVar.Var.jcari.setState(NORMAL);
            GlobalVar.Var.jcari.toFront();
        }
    }

    void loadeditdata() {
        DRunSelctOne dRunSelctOne = new DRunSelctOne();
        dRunSelctOne.seterorm("Eror gagal Menampilkan Data Penjualan");
        dRunSelctOne.setQuery("SELECT `IdPenjualan` as 'ID', `NoTransaksi` as 'No Transaksi', `NoDO` as 'No DO', DATE_FORMAT(`Tanggal`, '%d-%m-%Y') as 'Tanggal', `JenisPenjualan` as 'Jenis Penjualan', IFNULL(`Plat`,'-') as 'Plat', IFNULL(`JenisKendaraan`,'') as 'Kendaraan', `NamaSupir` as 'Nama Supir', IFNULL(`Gudang`, IFNULL(`Penerima`, '')) as 'Tujuan', a.`Keterangan` FROM `tbpenjualan`a JOIN `tbsmjenispenjualan`b ON a.`IdJenisPenjualan`=b.`IdJenisPenjualan` LEFT JOIN `tbmkendaraan`c ON a.`IdKendaraan`=c.`IdKendaraan` LEFT JOIN `tbmgudang`d on a.`IdGudang`=d.`IdGudang` LEFT JOIN `tbmpenerima`e ON a.`IdPenerima`=e.`IdPenerima` WHERE `NoTransaksi` = '" + IdEdit + "'");
        ArrayList<String> list = dRunSelctOne.excute();
        JTNoTransaksi.setText(list.get(1));
        JTNoDO.setText(list.get(2));
        JDTanggal.setDate(FDateF.strtodate(list.get(3), "dd-MM-yyyy"));
        JCJenisPenjualan.setSelectedItem(list.get(4));
        JCPasien.setSelectedItem(list.get(5));
        JTJenisKendaraan.setText(list.get(6));
        JTNamaSupir.setText(list.get(7));
        if (list.get(4).equals("MUTASI GUDANG")) {
            JCTujuan.load("SELECT `Gudang` FROM `tbmgudang`");
            JCTujuan.setSelectedItem(list.get(8));
        } else {
            JCTujuan.load("SELECT `Penerima` FROM `tbmpenerima`");
            JCTujuan.setSelectedItem(list.get(8));
        }
        JTAKeterangan.setText(list.get(9));
        DefaultTableModel model = (DefaultTableModel) JTable.getModel();
        model.getDataVector().removeAllElements();
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `IdPenjualanDetail`, `NoTransaksi`, `NoKolom`, IF(`StatusRetur`=0 AND `StatusBarcode`=0,IF(a.`IdBarangLain` IS NULL,c.`NamaBarang`,d.`NamaBarangLain`),IF(`StatusRetur`=1 AND `StatusBarcode`=0,CONCAT(IF(a.`IdBarangLain` IS NULL,c.`NamaBarang`,d.`NamaBarangLain`),' (RETUR)'),IF(`StatusRetur`=0 AND `StatusBarcode`=1,CONCAT(IF(a.`IdBarangLain` IS NULL,c.`NamaBarang`,d.`NamaBarangLain`),' (BARCODE)'),CONCAT(IF(a.`IdBarangLain` IS NULL,c.`NamaBarang`,d.`NamaBarangLain`),' (RETUR) (BARCODE)')))) as 'NamaBarang', FORMAT(`Jumlah`,0), FORMAT(`HargaSatuan`,0), FORMAT(`Jumlah`*`HargaSatuan`,0) as 'Sub Total' , `StatusRetur`, `StatusBarcode` FROM `tbpenjualandetail`a LEFT JOIN `tbmpartai`b ON a.`IdPartai`=b.`IdPartai` JOIN `tbmbarang`c ON IF(a.`IdPartai` is null,a.`IdBarang`,b.`IdBarang`)=c.`IdBarang` LEFT JOIN `tbmbaranglain`d on a.`IdBarangLain`=d.`IdbarangLain` WHERE `NoTransaksi` = '" + list.get(1) + "'");
        try {
            ResultSet rs = runSelct.excute();
            int row = 0;
            while (rs.next()) {
                model.addRow(new Object[]{"", "", "", "", ""});
                JTable.setValueAt(rs.getString(3), row, 0);
                JTable.setValueAt(rs.getString(4), row, 1);
                JTable.setValueAt(rs.getString(5).replace(",", "."), row, 2);
                JTable.setValueAt(rs.getString(6).replace(",", "."), row, 3);
                JTable.setValueAt(rs.getString(7).replace(",", "."), row, 4);
                row++;
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            showMessageDialog(null, "Gagal Panggil Data Detail Penjualan");
        } finally {
            runSelct.closecon();
        }
        JTGrandTotal.setText(String.valueOf(getGrandTotal()));
    }

    Boolean checkInput() {
        if (JDTanggal.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Tanggal Penjualan Tidak Boleh Kosong");
            return false;
        } else if (JTable.getRowCount() < 1) {
            JOptionPane.showMessageDialog(this, "Detail Penjualan Tidak Boleh Kosong");
            return false;
        } else {
            return true;
        }
    }

    public static String getNoPenjualan() {
        NumberFormat nf = new DecimalFormat("000000");
        String NoTransaksi = null;
        RunSelct runSelct = new RunSelct();
        runSelct.setQuery("SELECT `NoTransaksi` FROM `tbpenjualan` ORDER BY `NoTransaksi` DESC LIMIT 1");
        try {
            ResultSet rs = runSelct.excute();
            if (!rs.isBeforeFirst()) {
                NoTransaksi = "KB-" + "000001" + "-PJ";
            }
            while (rs.next()) {
                String nopenjualan = rs.getString("NoTransaksi");
                String number = nopenjualan.substring(3, 9);
                //String month = nopenjualan.substring(8, 10);
                int p = 1 + parseInt(number);
                /*if (month.equals(FDateF.datetostr(new Date(), "MM"))) {
                    p = 1 + parseInt(number);
                } else {
                    p = 1;
                }*/
                if (p != 999999) {
                    NoTransaksi = "KB-" + nf.format(p) + "-PJ";
                } else if (p == 999999) {
                    p = 1;
                    NoTransaksi = "KB-" + nf.format(p) + "-PJ";
                }
            }
        } catch (SQLException e) {
            out.println("E6" + e);
            showMessageDialog(null, "Gagal Generate Nomor Penjualan");
        } finally {
            runSelct.closecon();
        }
        return NoTransaksi;
    }

    boolean checkTable() {
        if (JTNamaBarang.getText().replace(" ", "").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Barang Tidak Boleh Kosong");
            JTNamaBarang.requestFocus();
            return false;
        } else if (JTJumlah.getText().replace("0", "").isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jumlah Tidak Boleh Kosong");
            JTJumlah.requestFocus();
            return false;
        } else if (JTHargaSatuan.getInt() == 0) {
            JOptionPane.showMessageDialog(this, "Harga Satuan Tidak Boleh Kosong");
            JTHargaSatuan.requestFocus();
            return false;
        } else if (JTable.getRowCount() > 10) {
            JOptionPane.showMessageDialog(this, "Jenis Barang Yang Diinput Tidak Bisa Lebih Dari 10");
            return false;
        } else if (Float.parseFloat(JTJumlah.getText()) > Float.parseFloat(JTStock.getText().replace(".", "").replace(",", "."))) {
            JOptionPane.showMessageDialog(this, "Jumlah Permintaan Tidak Bisa Melebihi Stok");
            JTJumlah.requestFocus();
            return false;
        } else if (cekdoubleitem(JTNamaBarang.getText())) {
            JOptionPane.showMessageDialog(this, "Tidak Bisa Input Barang Yang Sama");
            JTNamaBarang.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    boolean isAlphanumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a) {
                return false;
            }
        }
        return true;
    }

    boolean cekdoubleitem(String item) {
        for (int i = 0; i < JTable.getRowCount(); i++) {
            if (item.equals(JTable.getValueAt(i, 1))) {
                return true;
            }
        }
        return false;
    }

    void RefreshTbl() {
        JTable.clearSelection();
        JTNamaBarang.setText("");
        JTJumlah.setText("0");
        JTHargaSatuan.setText("0");
        JTSubTotal.setText("0");
        JTStock.setText("0,00");
    }

    void setHarga() {
        if (!JTNamaBarang.getText().replace(" ", "").equals("")) {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal Panggil Data Harga");
            dRunSelctOne.setQuery("SELECT `Harga` FROM `tbmbarang` WHERE `NamaBarang`= '" + JTNamaBarang.getText() + "'");
            ArrayList<String> list = dRunSelctOne.excute();
            JTHargaSatuan.setText(list.get(0));
        } else {
            JTHargaSatuan.setText("0");
        }
    }

    void setSubTotal() {
        int jumlah = Integer.valueOf(JTJumlah.getText());
        int harga = JTHargaSatuan.getInt();
        int subtotal = jumlah * harga;
        JTSubTotal.setText(String.valueOf(subtotal));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        LBNoTransaksi = new KomponenGUI.JlableF();
        jlableF4 = new KomponenGUI.JlableF();
        jlableF10 = new KomponenGUI.JlableF();
        jlableF3 = new KomponenGUI.JlableF();
        JTNoTransaksi = new KomponenGUI.JtextF();
        jbuttonF1 = new KomponenGUI.JbuttonF();
        jbuttonF2 = new KomponenGUI.JbuttonF();
        jbuttonF4 = new KomponenGUI.JbuttonF();
        jlableF6 = new KomponenGUI.JlableF();
        jlableF8 = new KomponenGUI.JlableF();
        jbuttonF3 = new KomponenGUI.JbuttonF();
        jlableF21 = new KomponenGUI.JlableF();
        jlableF22 = new KomponenGUI.JlableF();
        JDTanggal = new KomponenGUI.JdateCF();
        jlableF23 = new KomponenGUI.JlableF();
        jlableF24 = new KomponenGUI.JlableF();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable = new KomponenGUI.JtableF();
        jlabelF7 = new KomponenGUI.JtextF();
        jlabelF8 = new KomponenGUI.JtextF();
        JTJumlah = new KomponenGUI.JNumberOnly();
        jlabelF9 = new KomponenGUI.JtextF();
        jlabelF10 = new KomponenGUI.JtextF();
        tambahtable = new KomponenGUI.JbuttonF();
        ubahtable = new KomponenGUI.JbuttonF();
        hapustable = new KomponenGUI.JbuttonF();
        hapustable1 = new KomponenGUI.JbuttonF();
        JTStock = new KomponenGUI.JtextF();
        jlabelF11 = new KomponenGUI.JtextF();
        JTHargaSatuan = new KomponenGUI.JRibuanTextField();
        jbuttonF5 = new KomponenGUI.JbuttonF();
        JTSubTotal = new KomponenGUI.JRibuanTextField();
        JCPasien = new KomponenGUI.JcomboboxF();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAKeterangan = new KomponenGUI.JTextAreaF();
        JTGrandTotal = new KomponenGUI.JRibuanTextField();
        jbuttonF7 = new KomponenGUI.JbuttonF();
        jbuttonF8 = new KomponenGUI.JbuttonF();
        jbuttonF9 = new KomponenGUI.JbuttonF();
        jlableF29 = new KomponenGUI.JlableF();
        JTNamaBarang = new KomponenGUI.JtextF();
        jbuttonF11 = new KomponenGUI.JbuttonF();
        JBSearchNamaBarang = new KomponenGUI.JbuttonF();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        LBNoTransaksi.setText("No. Transaksi");

        jlableF4.setText("Total");

        jlableF10.setText(":");

        jlableF3.setText("Keterangan");

        JTNoTransaksi.setEnabled(false);
        JTNoTransaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTNoTransaksiKeyPressed(evt);
            }
        });

        jbuttonF1.setText("Tambah");
        jbuttonF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF1ActionPerformed(evt);
            }
        });

        jbuttonF2.setText("Tambah & Tutup");
        jbuttonF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF2ActionPerformed(evt);
            }
        });

        jbuttonF4.setText("Kembali");
        jbuttonF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF4ActionPerformed(evt);
            }
        });

        jlableF6.setText(":");

        jlableF8.setText(":");

        jbuttonF3.setText("Ubah");
        jbuttonF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF3ActionPerformed(evt);
            }
        });

        jlableF21.setText("Pasien");

        jlableF22.setText(":");

        JDTanggal.setDate(new Date());
        JDTanggal.setDateFormatString("dd-MM-yyyy");
        JDTanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JDTanggalKeyPressed(evt);
            }
        });

        jlableF23.setText("Tanggal");

        jlableF24.setText(":");

        JTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Nama Barang", "Jumlah", "Harga Satuan", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTable);
        if (JTable.getColumnModel().getColumnCount() > 0) {
            JTable.getColumnModel().getColumn(0).setMinWidth(50);
            JTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            JTable.getColumnModel().getColumn(0).setMaxWidth(50);
            JTable.getColumnModel().getColumn(1).setMinWidth(410);
            JTable.getColumnModel().getColumn(1).setPreferredWidth(410);
            JTable.getColumnModel().getColumn(1).setMaxWidth(410);
            JTable.getColumnModel().getColumn(2).setMinWidth(85);
            JTable.getColumnModel().getColumn(2).setPreferredWidth(85);
            JTable.getColumnModel().getColumn(2).setMaxWidth(85);
            JTable.getColumnModel().getColumn(3).setMinWidth(15);
            JTable.getColumnModel().getColumn(3).setPreferredWidth(115);
            JTable.getColumnModel().getColumn(3).setMaxWidth(115);
        }
        JTable.setrender(new int[]{2,3,4}, new String[]{"Number", "Number", "Number"});

        jlabelF7.setText("Nama Barang");
        jlabelF7.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF7.setEnabled(false);

        jlabelF8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jlabelF8.setText("Jumlah");
        jlabelF8.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF8.setEnabled(false);

        JTJumlah.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTJumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTJumlahFocusLost(evt);
            }
        });
        JTJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTJumlahKeyPressed(evt);
            }
        });

        jlabelF9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jlabelF9.setText("Harga Satuan");
        jlabelF9.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF9.setEnabled(false);

        jlabelF10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jlabelF10.setText("Stock");
        jlabelF10.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF10.setEnabled(false);

        tambahtable.setText("TAMBAH");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement == null}"), tambahtable, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        tambahtable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahtableActionPerformed(evt);
            }
        });

        ubahtable.setText("UBAH");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), ubahtable, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        ubahtable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahtableActionPerformed(evt);
            }
        });

        hapustable.setText("HAPUS");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), hapustable, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        hapustable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapustableActionPerformed(evt);
            }
        });

        hapustable1.setText("REFRESH");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, JTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), hapustable1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        hapustable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapustable1ActionPerformed(evt);
            }
        });

        JTStock.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTStock.setText("0,00");
        JTStock.setEnabled(false);

        jlabelF11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jlabelF11.setText("Sub Total");
        jlabelF11.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jlabelF11.setEnabled(false);

        JTHargaSatuan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTHargaSatuanFocusLost(evt);
            }
        });
        JTHargaSatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTHargaSatuanKeyPressed(evt);
            }
        });

        jbuttonF5.setText("Print");
        jbuttonF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF5ActionPerformed(evt);
            }
        });

        JTSubTotal.setEnabled(false);
        JTSubTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTSubTotalKeyPressed(evt);
            }
        });

        JCPasien.load("SELECT '-' as 'NamaPasien' UNION ALL SELECT `NamaPasien` FROM `tbmpasien` ");
        JCPasien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCPasienItemStateChanged(evt);
            }
        });
        JCPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JCPasienKeyPressed(evt);
            }
        });

        JTAKeterangan.setColumns(20);
        JTAKeterangan.setRows(5);
        jScrollPane1.setViewportView(JTAKeterangan);

        JTGrandTotal.setEnabled(false);

        jbuttonF7.setText("+");
        jbuttonF7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF7ActionPerformed(evt);
            }
        });

        jbuttonF8.setText("Tambah & Print");
        jbuttonF8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF8ActionPerformed(evt);
            }
        });

        jbuttonF9.setText("Ubah & Print");
        jbuttonF9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF9ActionPerformed(evt);
            }
        });

        jlableF29.setText("PENJUALAN ");
        jlableF29.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        JTNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTNamaBarangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTNamaBarangKeyReleased(evt);
            }
        });

        jbuttonF11.setText("+");
        jbuttonF11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF11ActionPerformed(evt);
            }
        });

        JBSearchNamaBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/Search.png"))); // NOI18N
        JBSearchNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSearchNamaBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbuttonF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbuttonF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonF9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlableF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlableF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlableF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hapustable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ubahtable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tambahtable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hapustable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlableF21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlableF22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCPasien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbuttonF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlableF29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlabelF7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JTNamaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbuttonF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JBSearchNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlabelF8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JTJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(JTHargaSatuan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlabelF9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlabelF11, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(JTSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JTStock, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                    .addComponent(jlabelF10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(LBNoTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlableF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(JTNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jlableF23, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlableF24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(JDTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlableF29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlableF21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LBNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlableF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JDTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlableF24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlableF23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelF10, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlabelF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabelF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabelF9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlabelF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbuttonF11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JTJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTHargaSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JBSearchNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(tambahtable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ubahtable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapustable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hapustable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlableF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlableF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbuttonF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbuttonF9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlableF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlableF10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTGrandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF1ActionPerformed
        tambahData(false, false);
    }//GEN-LAST:event_jbuttonF1ActionPerformed

    private void jbuttonF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF2ActionPerformed
        tambahData(true, false);
    }//GEN-LAST:event_jbuttonF2ActionPerformed

    private void jbuttonF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF3ActionPerformed
        ubahData(false);
    }//GEN-LAST:event_jbuttonF3ActionPerformed

    private void jbuttonF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF4ActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonF4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void JTNoTransaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNoTransaksiKeyPressed

    }//GEN-LAST:event_JTNoTransaksiKeyPressed

    private void JDTanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JDTanggalKeyPressed

    }//GEN-LAST:event_JDTanggalKeyPressed

    private void tambahtableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahtableActionPerformed
        TambahTabel();
    }//GEN-LAST:event_tambahtableActionPerformed

    private void ubahtableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahtableActionPerformed
        ubahtable();
    }//GEN-LAST:event_ubahtableActionPerformed

    private void hapustableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapustableActionPerformed
        if (JTable.getSelectedRow() != -1) {
            ((DefaultTableModel) JTable.getModel()).removeRow(JTable.getSelectedRow());
            for (int a = 0; a < JTable.getRowCount(); a++) {
                JTable.setValueAt(a + 1, a, 0);
            }
            JOptionPane.showMessageDialog(this, "Berhasil Hapus Data");
            RefreshTbl();
        }
        JTNamaBarang.requestFocus();
        JTGrandTotal.setText(String.valueOf(getGrandTotal()));
    }//GEN-LAST:event_hapustableActionPerformed

    private void hapustable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapustable1ActionPerformed
        RefreshTbl();
    }//GEN-LAST:event_hapustable1ActionPerformed

    private void JTJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTJumlahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTHargaSatuan.requestFocus();
            setSubTotal();
        }
    }//GEN-LAST:event_JTJumlahKeyPressed

    private void JTHargaSatuanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTHargaSatuanKeyPressed
        setSubTotal();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tambahtable.isEnabled()) {
                TambahTabel();
            } else {
                ubahtable();
            }
        }
    }//GEN-LAST:event_JTHargaSatuanKeyPressed

    private void JTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableMouseClicked
        if (JTable.getSelectedRow() != -1) {
            JTNamaBarang.setText(JTable.getValueAt(JTable.getSelectedRow(), 1).toString());
            JTJumlah.setText(JTable.getValueAt(JTable.getSelectedRow(), 2).toString());
            JTHargaSatuan.setText(JTable.getValueAt(JTable.getSelectedRow(), 3).toString().replace(".", ""));
            JTSubTotal.setText(JTable.getValueAt(JTable.getSelectedRow(), 4).toString());
        }
    }//GEN-LAST:event_JTableMouseClicked

    private void jbuttonF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF5ActionPerformed
        printing();
    }//GEN-LAST:event_jbuttonF5ActionPerformed

    private void JTSubTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTSubTotalKeyPressed

    }//GEN-LAST:event_JTSubTotalKeyPressed

    private void JCPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JCPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTNamaBarang.requestFocus();
        }
    }//GEN-LAST:event_JCPasienKeyPressed

    private void JTJumlahFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTJumlahFocusLost
        setSubTotal();
    }//GEN-LAST:event_JTJumlahFocusLost

    private void JTHargaSatuanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTHargaSatuanFocusLost
        setSubTotal();
    }//GEN-LAST:event_JTHargaSatuanFocusLost

    private void jbuttonF7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF7ActionPerformed
        if (GlobalVar.Var.tambahMasterPasien == null) {
            GlobalVar.Var.tambahMasterPasien = new MasterPasien("0");
        } else {
            GlobalVar.Var.tambahMasterPasien.setState(NORMAL);
            GlobalVar.Var.tambahMasterPasien.toFront();
        }
    }//GEN-LAST:event_jbuttonF7ActionPerformed

    private void JCPasienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCPasienItemStateChanged

    }//GEN-LAST:event_JCPasienItemStateChanged

    private void jbuttonF8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF8ActionPerformed
        tambahData(false, true);
    }//GEN-LAST:event_jbuttonF8ActionPerformed

    private void jbuttonF9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF9ActionPerformed
        ubahData(true);
    }//GEN-LAST:event_jbuttonF9ActionPerformed

    private void JTNamaBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNamaBarangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JTJumlah.requestFocus();
        }
    }//GEN-LAST:event_JTNamaBarangKeyPressed

    private void JTNamaBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTNamaBarangKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            cariBarang(null);
        } else if (isAlphanumeric(String.valueOf(evt.getKeyChar()))) {
            cariBarang(JTNamaBarang.getText());
        }
    }//GEN-LAST:event_JTNamaBarangKeyReleased

    private void jbuttonF11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbuttonF11ActionPerformed

    private void JBSearchNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSearchNamaBarangActionPerformed
        cariBarang(null);
    }//GEN-LAST:event_JBSearchNamaBarangActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (jbuttonF1.isVisible()) {
            GlobalVar.Var.tambahPenjualan = null;
        } else {
            GlobalVar.Var.ubahPenjualan = null;
        }
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private KomponenGUI.JbuttonF JBSearchNamaBarang;
    public static KomponenGUI.JcomboboxF JCPasien;
    private KomponenGUI.JdateCF JDTanggal;
    private KomponenGUI.JTextAreaF JTAKeterangan;
    private KomponenGUI.JRibuanTextField JTGrandTotal;
    private KomponenGUI.JRibuanTextField JTHargaSatuan;
    private KomponenGUI.JNumberOnly JTJumlah;
    private KomponenGUI.JtextF JTNamaBarang;
    private KomponenGUI.JtextF JTNoTransaksi;
    private KomponenGUI.JtextF JTStock;
    private KomponenGUI.JRibuanTextField JTSubTotal;
    private KomponenGUI.JtableF JTable;
    private KomponenGUI.JlableF LBNoTransaksi;
    private KomponenGUI.JbuttonF hapustable;
    private KomponenGUI.JbuttonF hapustable1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private KomponenGUI.JbuttonF jbuttonF1;
    private KomponenGUI.JbuttonF jbuttonF11;
    private KomponenGUI.JbuttonF jbuttonF2;
    private KomponenGUI.JbuttonF jbuttonF3;
    private KomponenGUI.JbuttonF jbuttonF4;
    private KomponenGUI.JbuttonF jbuttonF5;
    private KomponenGUI.JbuttonF jbuttonF7;
    private KomponenGUI.JbuttonF jbuttonF8;
    private KomponenGUI.JbuttonF jbuttonF9;
    private KomponenGUI.JtextF jlabelF10;
    private KomponenGUI.JtextF jlabelF11;
    private KomponenGUI.JtextF jlabelF7;
    private KomponenGUI.JtextF jlabelF8;
    private KomponenGUI.JtextF jlabelF9;
    private KomponenGUI.JlableF jlableF10;
    private KomponenGUI.JlableF jlableF21;
    private KomponenGUI.JlableF jlableF22;
    private KomponenGUI.JlableF jlableF23;
    private KomponenGUI.JlableF jlableF24;
    private KomponenGUI.JlableF jlableF29;
    private KomponenGUI.JlableF jlableF3;
    private KomponenGUI.JlableF jlableF4;
    private KomponenGUI.JlableF jlableF6;
    private KomponenGUI.JlableF jlableF8;
    private KomponenGUI.JbuttonF tambahtable;
    private KomponenGUI.JbuttonF ubahtable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    void TambahTabel() {
        if (checkTable()) {
            DefaultTableModel model = (DefaultTableModel) JTable.getModel();
            model.addRow(new Object[]{JTable.getRowCount() + 1, JTNamaBarang.getText(), JTJumlah.getText(), Intformatdigit(JTHargaSatuan.getInt()), JTSubTotal.getText()});
            JOptionPane.showMessageDialog(this, "Berhasil Tambah Permintaan");
            JTNamaBarang.requestFocus();
            RefreshTbl();
            JTGrandTotal.setText(String.valueOf(getGrandTotal()));
        }
    }

    void ubahtable() {
        if (checkTable()) {
            JTable.setValueAt(JTNamaBarang.getText(), JTable.getSelectedRow(), 1);
            JTable.setValueAt(JTJumlah.getText(), JTable.getSelectedRow(), 2);
            JTable.setValueAt(JTHargaSatuan.getText(), JTable.getSelectedRow(), 3);
            JTable.setValueAt(JTSubTotal.getText(), JTable.getSelectedRow(), 4);
            JOptionPane.showMessageDialog(this, "Berhasil Ubah Data");
            RefreshTbl();
            JTNamaBarang.requestFocus();
            JTGrandTotal.setText(String.valueOf(getGrandTotal()));
        }
    }

    void tambahData(Boolean tutup, Boolean print) {
        if (checkInput()) {
            boolean Berhasil;
            MultiInsert multiInsert = new MultiInsert();
            Berhasil = multiInsert.OpenConnection();
            if (Berhasil) {
                Berhasil = multiInsert.setautocomit(false);
                if (Berhasil) {
                    Berhasil = multiInsert.Excute("INSERT INTO `tbpenjualan`(`NoTransaksi`, `Tanggal`, `IdPasien`, `Keterangan`) VALUES ('" + JTNoTransaksi.getText() + "','" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "',(SELECT `IdPasien` FROM `tbmpasien` WHERE `NamaPasien` = '" + JCPasien.getSelectedItem() + "'),'" + JTAKeterangan.getText() + "')", null);
                    if (Berhasil) {
                        for (int i = 0; i < JTable.getRowCount(); i++) {
                            Berhasil = multiInsert.Excute("", null);

                        }
                    }
                }
            }
            if (Berhasil == false) {
                multiInsert.rollback();
                multiInsert.closecon();
                JOptionPane.showMessageDialog(this, "Gagal Tambah Data Penjualan");
            }
            if (Berhasil == true) {
                JOptionPane.showMessageDialog(this, "Berhasil Tambah Data Penjualan");
                multiInsert.Commit();
                multiInsert.closecon();
                if (print) {
                    printing();
                }
                if (ListPenjualan.isVisible()) {
                    GlobalVar.Var.listPenjualan.load();
                }
                if (tutup) {
                    GlobalVar.Var.tambahPenjualan.dispose();
                    GlobalVar.Var.tambahPenjualan = null;
                } else {
                    JTAKeterangan.setText("");
                    JTable.setModel(new javax.swing.table.DefaultTableModel(
                            new Object[][]{},
                            new String[]{
                                "No", "Nama Barang", "Jumlah", "Harga Satuan", "Sub Total"
                            }
                    ));
                    RefreshTbl();
                    JTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                    JTable.getColumnModel().getColumn(1).setPreferredWidth(310);
                    JTable.getColumnModel().getColumn(2).setPreferredWidth(85);
                    JTable.getColumnModel().getColumn(3).setPreferredWidth(115);
                    if (JCJenisPenjualan.getSelectedItem().toString().toUpperCase().equals("MUTASI GUDANG")) {
                        JTNoTransaksi.setText(getNoMutasi());
                    } else {
                        JTNoTransaksi.setText(getNoPenjualan());
                    }
                }
            }
        }
    }

    void ubahData(Boolean print) {
        if (checkInput()) {
            boolean Berhasil;
            MultiInsert multiInsert = new MultiInsert();
            Berhasil = multiInsert.OpenConnection();
            if (Berhasil) {
                Berhasil = multiInsert.setautocomit(false);
                // query insert luar table
                //query insert dalam table ( ketentuan Hasil Harus True Baru jalan
                //cek inset gagal / berhasil 
                // jalankan koding di if paling bawah
                if (Berhasil) {
                    if (JCJenisPenjualan.getSelectedItem().equals("MUTASI GUDANG")) {
                        Berhasil = multiInsert.Excute("UPDATE `tbpenjualan` SET `NoTransaksi`='" + JTNoTransaksi.getText() + "',`NoDO`='" + JTNoDO.getText() + "',`Tanggal`='" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "',`IdJenisPenjualan`=(SELECT `IdJenisPenjualan` FROM `tbsmjenispenjualan` WHERE `JenisPenjualan` = '" + JCJenisPenjualan.getSelectedItem() + "'),`IdKendaraan`=(SELECT `IdKendaraan` FROM `tbmkendaraan` WHERE `Plat` = '" + JCPasien.getSelectedItem() + "'),`NamaSupir` = '" + JTNamaSupir.getText() + "',`IdGudang`=(SELECT `IdGudang` FROM `tbmgudang` WHERE `Gudang` = '" + JCTujuan.getSelectedItem() + "'),`Keterangan`='" + JTAKeterangan.getText() + "' WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'", null);
                    } else {
                        String idKendaraan = "(SELECT `IdKendaraan` FROM `tbmkendaraan` WHERE `Plat` = '" + JCPasien.getSelectedItem() + "')";
                        if (JCPasien.getSelectedItem().equals("-")) {
                            idKendaraan = "null";
                        }
                        Berhasil = multiInsert.Excute("UPDATE `tbpenjualan` SET `NoTransaksi`='" + JTNoTransaksi.getText() + "',`NoDO`='" + JTNoDO.getText() + "',`Tanggal`='" + FDateF.datetostr(JDTanggal.getDate(), "yyyy-MM-dd") + "',`IdJenisPenjualan`=(SELECT `IdJenisPenjualan` FROM `tbsmjenispenjualan` WHERE `JenisPenjualan` = '" + JCJenisPenjualan.getSelectedItem() + "'),`IdKendaraan`=" + idKendaraan + ",`NamaSupir` = '" + JTNamaSupir.getText() + "',`IdPenerima`=(SELECT `Idpenerima` FROM `tbmpenerima` WHERE `Penerima` = '" + JCTujuan.getSelectedItem() + "'),`Keterangan`='" + JTAKeterangan.getText() + "' WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'", null);
                    }
                    if (Berhasil) {
                        Berhasil = multiInsert.Excute("DELETE FROM `tbpenjualandetail` WHERE `NoTransaksi` = '" + JTNoTransaksi.getText() + "'", null);
                        if (Berhasil) {
                            for (int i = 0; i < JTable.getRowCount(); i++) {
                                if (JCNamaBarang.getSelectedItem().toString().toUpperCase().contains("PARTAI")) {
                                    Berhasil = multiInsert.Excute("INSERT INTO `tbpenjualandetail`(`NoTransaksi`, `NoKolom`, `IdPartai`, `Jumlah`, `HargaSatuan`) VALUES ('" + JTNoTransaksi.getText() + "','" + JTable.getValueAt(i, 0) + "','" + JTable.getValueAt(i, 1).toString().split(" \\(PARTAI ")[1].split("\\)")[0] + "','" + JTable.getValueAt(i, 2).toString().replace(".", "") + "','" + JTable.getValueAt(i, 3).toString().replace(".", "") + "')", null);
                                } else if (JCNamaBarang.getSelectedItem().toString().toUpperCase().contains("PLASTIK")) {
                                    Berhasil = multiInsert.Excute("INSERT INTO `tbpenjualandetail`(`NoTransaksi`, `NoKolom`, `IdBarangLain`, `Jumlah`, `HargaSatuan`, `StatusRetur`, `StatusBarcode`) VALUES ('" + JTNoTransaksi.getText() + "','" + JTable.getValueAt(i, 0) + "',(SELECT `IdBarangLain` FROM `tbmbaranglain` WHERE `NamaBarangLain` = '" + JTable.getValueAt(i, 1).toString().split(" \\(")[0] + "'),'" + JTable.getValueAt(i, 2).toString().replace(".", "") + "','" + JTable.getValueAt(i, 3).toString().replace(".", "") + "'," + JTable.getValueAt(i, 1).toString().contains("RETUR") + "," + JTable.getValueAt(i, 1).toString().contains("BARCODE") + ")", null);
                                } else {
                                    Berhasil = multiInsert.Excute("INSERT INTO `tbpenjualandetail`(`NoTransaksi`, `NoKolom`, `IdBarang`, `Jumlah`, `HargaSatuan`, `StatusRetur`, `StatusBarcode`) VALUES ('" + JTNoTransaksi.getText() + "','" + JTable.getValueAt(i, 0) + "',(SELECT `IdBarang` FROM `tbmbarang` WHERE `NamaBarang` = '" + JTable.getValueAt(i, 1).toString().split(" \\(")[0] + "'),'" + JTable.getValueAt(i, 2).toString().replace(".", "") + "','" + JTable.getValueAt(i, 3).toString().replace(".", "") + "'," + JTable.getValueAt(i, 1).toString().contains("RETUR") + "," + JTable.getValueAt(i, 1).toString().contains("BARCODE") + ")", null);
                                }
                            }
                        }
                    }
                }
                if (Berhasil == false) {
                    multiInsert.rollback();
                    multiInsert.closecon();
                    JOptionPane.showMessageDialog(this, "Gagal Ubah Data Penjualan");
                }
                if (Berhasil == true) {
                    JOptionPane.showMessageDialog(this, "Berhasil Ubah Data Penjualan");
                    multiInsert.Commit();
                    multiInsert.closecon();
                    if (print) {
                        printing();
                    }
                    GlobalVar.Var.ubahPenjualan.dispose();
                    GlobalVar.Var.ubahPenjualan = null;
                    if (GlobalVar.Var.listPenjualan != null) {
                        GlobalVar.Var.listPenjualan.load();
                    }
                }
            }
        }
    }

    void printing() {
        String NamaSupir = JTNamaSupir.getText();
        if (JTNamaSupir.getText().length() > 13) {
            NamaSupir = JTNamaSupir.getText().substring(0, 13);
        }
        String Plat = JCPasien.getSelectedItem().toString();
        String JenisKendaraan = JTJenisKendaraan.getText();
        String NoTransaksi = JTNoTransaksi.getText();
        String NoDO = JTNoDO.getText();
        String Tujuan = JCTujuan.getSelectedItem().toString();
        String Tanggal = datetostr(JDTanggal.getDate(), "dd-MM-yyyy");
        String Keterangan = JTAKeterangan.getText();

        String[] Barang = new String[JTable.getRowCount()];
        int[] Jumlah = new int[JTable.getRowCount()];
        String[] Jumlahs = new String[JTable.getRowCount()];
        int[] Harga = new int[JTable.getRowCount()];
        String[] Hargas = new String[JTable.getRowCount()];
        int[] Sub = new int[JTable.getRowCount()];
        String[] Subs = new String[JTable.getRowCount()];

        for (int i = 0; i < JTable.getRowCount(); i++) {
            Barang[i] = JTable.getValueAt(i, 1).toString().split(" \\(PARTAI ")[0];
            Jumlah[i] = Integer.parseInt(JTable.getValueAt(i, 2).toString().replace(".", ""));
            Jumlahs[i] = Intformatdigit(Jumlah[i]);
            Harga[i] = Integer.parseInt(JTable.getValueAt(i, 3).toString().replace(".", ""));
            Hargas[i] = Intformatdigit(Harga[i]);
            Sub[i] = Integer.parseInt(JTable.getValueAt(i, 4).toString().replace(".", ""));
            Subs[i] = Intformatdigit(Sub[i]);
        }

        int Total = getGrandTotal();
        String Totals = Intformatdigit(Total);
        String terbilang = angkaToTerbilang(Long.valueOf(Total)) + "RUPIAH";
        if (terbilang.length() > 66) {
            terbilang = terbilang.substring(0, 66);
        }

        if (JCJenisPenjualan.getSelectedItem().equals("MUTASI GUDANG")) {
            String leftAlignFormat = "%-5s%-11s%-63s%-1s%n";
            String OutFormat = "";
            OutFormat += format("%-81s%n", " _____________________________________________________________________________");
            OutFormat += format("%-54s%-26s%n", " SURAT JALAN", "No Transaksi: " + NoTransaksi);
            OutFormat += format("%-54s%-26s%n", " ", "Tanggal     : " + Tanggal);
            if (JCTujuan.getSelectedItem().equals("PAAL 5") || JCTujuan.getSelectedItem().equals("WESAN")) {
                OutFormat += format("%-54s%-26s%n", " Ke Gudang : " + Tujuan, " ");
            } else {
                OutFormat += format("%-54s%-26s%n", " Kepada    : " + Tujuan, " ");
            }
            OutFormat += format("%-54s%-26s%n", " Plat      : " + Plat, "No DO       : " + NoDO);
            OutFormat += format("%-54s%-26s%n", " Kendaraan : " + JenisKendaraan, " ");
            //                              12345678901234567890123456789012345678901234567890123456789012345678901234567890
            OutFormat += format("%-80s%n", " +---+----------+--------------------------------------------------------------+");
            OutFormat += format("%-80s%n", " | NO|  JUMLAH  | NAMA BARANG                                                  |");
            OutFormat += format("%-80s%n", " +---+----------+--------------------------------------------------------------+");
            for (int i = 0; i < 10; i++) {
                if (i < JTable.getRowCount()) {
                    String satuan;
                    if (Barang[i].toUpperCase().contains("PARTAI") || Barang[i].toUpperCase().contains("@50 KG")) {
                        satuan = " SAK";
                    } else {
                        satuan = " PAK";
                    }
                    OutFormat += format(leftAlignFormat, " | " + (i + 1), "|" + format("%9s", Jumlah[i] + satuan), "| " + Barang[i], "|");
                } else {
                    OutFormat += format(leftAlignFormat, " | " + (i + 1), "|" + " ", "|" + " ", "|");
                }
            }
            OutFormat += format("%-80s%n", " +-----------------------------------------------------------------------------+");
            OutFormat += format("%-80s%n", " Ket : " + Keterangan);
            OutFormat += format("%n", "");
            OutFormat += format("%-67s%-24s%n", " Disiapkan Oleh", "Diterima Oleh \n \n ");
            OutFormat += format("%-67s%-24s%n", " HENDRI", NamaSupir);
            OutFormat += format("%-80s%n", " ______________________________________________________________________________");
            OutFormat += format("%n", "");
            OutFormat += format("%n", "");
            OutFormat += format("%n", "");
            //System.out.println(OutFormat);
            directprinting(OutFormat);
        } else {
            String leftAlignFormat = "%-5s%-37s%-9s%-15s%-13s%-1s%n";
            String OutFormat = "";
            OutFormat += format("%-81s%n", " _____________________________________________________________________________");
            OutFormat += format("%-54s%-26s%n", " FAKTUR PENJUALAN", "No Transaksi: " + NoTransaksi);
            OutFormat += format("%-54s%-26s%n", " ", "Tanggal     : " + Tanggal);
            if (JCJenisPenjualan.getSelectedItem().equals("MUTASI GUDANG")) {
                OutFormat += format("%-54s%-26s%n", " Ke Gudang : " + Tujuan, " ");
            } else {
                OutFormat += format("%-54s%-26s%n", " Kepada    : " + Tujuan, " ");
            }
            OutFormat += format("%-54s%-26s%n", " Plat      : " + Plat, " ");
            OutFormat += format("%-54s%-26s%n", " Kendaraan : " + JenisKendaraan, " ");
            //                              12341234567890123456789012345678901234567812345678912345678901234512345678901234
            //                              12345678901234567890123456789012345678901234567890123456789012345678901234567890
            OutFormat += format("%-80s%n", " +---+------------------------------------+--------+--------------+------------+");
            OutFormat += format("%-80s%n", " | NO| NAMA BARANG                        | JUMLAH | HARGA SATUAN |  SUB TOTAL |");
            OutFormat += format("%-80s%n", " +---+------------------------------------+--------+--------------+------------+");
            for (int i = 0; i < 6; i++) {
                if (i < JTable.getRowCount()) {
                    OutFormat += format(leftAlignFormat, " | " + (i + 1), "| " + Barang[i], "|" + format("%7s", Jumlahs[i]), "|" + format("%13s", Hargas[i]), "|" + format("%11s", Subs[i]), "|");
                } else {
                    OutFormat += format(leftAlignFormat, " | " + (i + 1), "|" + " ", "|" + " ", "|" + " ", "|" + " ", "|");
                }
            }
            OutFormat += format("%-80s%n", " +---+------------------------------------+--------+--------------+------------+");
            OutFormat += format("%-23s%-43s%-13s%-1s%n", " | GRAND TOTAL", " ", "|" + format("%11s", Totals), "|");
            OutFormat += format("%-80s%n", " +----------------------------------------------------------------+------------+");
            OutFormat += format("%-80s%n", " Ket : " + Keterangan);
            OutFormat += format("%n", "");
            OutFormat += format("%-80s%n", " Terbilang : " + terbilang);
            OutFormat += format("%n", "");
            OutFormat += format("%-67s%-24s%n", " Disiapkan Oleh", "Diterima Oleh \n \n ");
            OutFormat += format("%-67s%-24s%n", " HENDRI", NamaSupir);
            OutFormat += format("%-80s%n", " ______________________________________________________________________________");
            OutFormat += format("%n", "");
            OutFormat += format("%n", "");
            OutFormat += format("%n", "");
            //System.out.println(OutFormat);
            directprinting(OutFormat);
        }
    }

    /**
     *
     * @param Teks
     */
    public static void directprinting(String Teks) {
        String testprint = Teks;
        String defaultPrinter
                = lookupDefaultPrintService().getName();
        PrintService service = lookupDefaultPrintService();
        try {
            InputStream in = new ByteArrayInputStream(testprint.getBytes());
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(LANDSCAPE);
            pras.add(findMedia(8.5f, 5.5f, INCH));
            pras.add(new Copies(1));
            DocAttributeSet docs = new HashDocAttributeSet();
            docs.add(LANDSCAPE);
            docs.add(findMedia(8.5f, 5.5f, INCH));
            try {
                DocFlavor flavor = AUTOSENSE;
                Doc doc = new SimpleDoc(in, flavor, docs);
                DocPrintJob job = service.createPrintJob();
                PrintJobWatcher pjw = new PrintJobWatcher(job);
                job.print(doc, pras);
                pjw.waitForDone();
                in.close();
            } catch (PrintException e) {
            }
        } catch (IOException e) {
        }
    }

    /**
     *
     */
    public static class PrintJobWatcher {

        private boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            job.addPrintJobListener(new PrintJobAdapter() {
                @Override
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }

                @Override
                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }

                @Override
                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }

                @Override
                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }

                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }

        /**
         *
         */
        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    String Intformatdigit(int Number) {
        int value = 0;
        value = Number;
        String output;
        String pattern = "#,###,###";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        if (value < 0) {
            value = abs(value);
            output = "(" + myFormatter.format(value) + ")";
        } else if (value > 0 && value < 1) {
            output = "0" + myFormatter.format(value);
        } else {
            output = myFormatter.format(value);
        }
        return output.replace(",", ".");
    }

    String Decformatdigit(double Number) {
        double value = 0;
        value = Number;
        String output;
        String pattern = "#,###,###.00";
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        if (value <= -1) {
            value = abs(value);
            return "-" + myFormatter.format(value);
        } else if (value < 0 && value > -1) {
            value = abs(value);
            return "-0" + myFormatter.format(value);
        } else if (value < 1 && value >= 0) {
            return "0" + myFormatter.format(value);
        } else {
            return myFormatter.format(value);
        }
    }

    Integer getGrandTotal() {
        int GrandTotal = 0;
        for (int x = 0; x < JTable.getRowCount(); x++) {
            GrandTotal = GrandTotal + (Integer.valueOf(JTable.getValueAt(x, 2).toString().replace(".", "")) * Integer.valueOf(JTable.getValueAt(x, 3).toString().replace(".", "")));
        }
        return GrandTotal;
    }

    public static String angkaToTerbilang(Long angka) {
        String[] huruf = {"", "SATU", "DUA", "TIGA", "EMPAT", "LIMA", "ENAM", "TUJUH", "DELAPAN", "SEMBILAN", "SEPULUH", "SEBELAS"};
        if (angka < 12) {
            return huruf[angka.intValue()];
        }
        if (angka >= 12 && angka <= 19) {
            return huruf[angka.intValue() % 10] + " BELAS";
        }
        if (angka >= 20 && angka <= 99) {
            return angkaToTerbilang(angka / 10) + " PULUH " + huruf[angka.intValue() % 10];
        }
        if (angka >= 100 && angka <= 199) {
            return "SERATUS " + angkaToTerbilang(angka % 100);
        }
        if (angka >= 200 && angka <= 999) {
            return angkaToTerbilang(angka / 100) + " RATUS " + angkaToTerbilang(angka % 100);
        }
        if (angka >= 1000 && angka <= 1999) {
            return "SERIBU " + angkaToTerbilang(angka % 1000);
        }
        if (angka >= 2000 && angka <= 999999) {
            return angkaToTerbilang(angka / 1000) + " RIBU " + angkaToTerbilang(angka % 1000);
        }
        if (angka >= 1000000 && angka <= 999999999) {
            return angkaToTerbilang(angka / 1000000) + " JUTA " + angkaToTerbilang(angka % 1000000);
        }
        if (angka >= 1000000000 && angka <= 999999999999L) {
            return angkaToTerbilang(angka / 1000000000) + " MILYAR " + angkaToTerbilang(angka % 1000000000);
        }
        if (angka >= 1000000000000L && angka <= 999999999999999L) {
            return angkaToTerbilang(angka / 1000000000000L) + " TRILIUN " + angkaToTerbilang(angka % 1000000000000L);
        }
        if (angka >= 1000000000000000L && angka <= 999999999999999999L) {
            return angkaToTerbilang(angka / 1000000000000000L) + " QUADRILYUN " + angkaToTerbilang(angka % 1000000000000000L);
        }
        return "";
    }

    void setStok() {
        if (JCNamaBarang.getSelectedIndex() != 0) {
            DRunSelctOne dRunSelctOne = new DRunSelctOne();
            dRunSelctOne.seterorm("Gagal setStok()");
            if (JCNamaBarang.getSelectedItem().toString().contains("PARTAI")) {
                dRunSelctOne.setQuery("SELECT `IdPartai`, `NamaBarang`, SUM(`Stok`) as 'Stok', SUM(`KG`) as 'KG' FROM (\n"
                        + "SELECT `IdPartai`, `NamaBarang`, 0 as 'Stok', 0 as 'KG' FROM `tbmpartai`a JOIN `tbmbarang`b ON a.`IdBarang`=b.`IdBarang` WHERE `IdPartai` = '" + JCNamaBarang.getSelectedItem().toString().split(" \\(PARTAI ")[1].split("\\)")[0] + "'\n"
                        + "UNION ALL\n"
                        + "SELECT a.`IdPartai`, `NamaBarang`, ifnull(`KarungPelita`,0) AS 'Stok', ifnull(`NettoPelita`,0) AS 'KG' FROM `tbpenerimaan`a JOIN `tbmpartai`b ON a.`IdPartai`=b.`IdPartai` JOIN `tbmbarang`c ON b.`IdBarang`=c.`IdBarang` WHERE a.`IdPartai` = '" + JCNamaBarang.getSelectedItem().toString().split(" \\(PARTAI ")[1].split("\\)")[0] + "'\n"
                        + "UNION ALL\n"
                        + "SELECT a.`IdPartai`, `NamaBarang`, ifnull(`Jumlah`*-1,0) AS 'Stok', ifnull(`Jumlah`*-1*`Satuan`,0) AS 'KG' FROM `tbpenjualandetail`a JOIN `tbmpartai`b ON a.`IdPartai`=b.`IdPartai` JOIN `tbmbarang`c ON b.`IdBarang`=c.`IdBarang` WHERE `StatusRetur` = 0 AND a.`IdPartai` = '" + JCNamaBarang.getSelectedItem().toString().split(" \\(PARTAI ")[1].split("\\)")[0] + "'\n"
                        + "UNION ALL\n"
                        + "SELECT a.`IdPartai`, `NamaBarang`, (ROUND(SUM(`JumlahBahan`)))*-1 AS 'Stok', `JumlahBahan`*`Satuan`*-1 AS 'KG' FROM `tbpacking`a JOIN `tbmpartai`b ON a.`IdPartai`=b.`IdPartai` JOIN `tbmbarang`c ON b.`IdBarang`=c.`IdBarang` WHERE a.`IdPartai` = '" + JCNamaBarang.getSelectedItem().toString().split(" \\(PARTAI ")[1].split("\\)")[0] + "' GROUP BY `NoPacking`\n"
                        + "UNION ALL\n"
                        + "SELECT a.`IdPartai`, `NamaBarang`, ifnull(`Sak`,0) AS 'Stok', ifnull(`Jumlah`,0) AS 'KG' FROM `tbpenyesuaian`a JOIN `tbmbarang`b ON a.`IdBarang`=b.`IdBarang` WHERE a.`IdPartai` = '" + JCNamaBarang.getSelectedItem().toString().split(" \\(PARTAI ")[1].split("\\)")[0] + "'\n"
                        + ") as tbTemp GROUP BY `IdPartai`");
            } else if (getNamaBarang().contains("PLASTIK")) {
                String where = " d.`NamaBarangLain` = '" + JCNamaBarang.getSelectedItem() + "'";
                if (JCNamaBarang.getSelectedItem().toString().contains("DALAM")) {
                    where = " c.`NamaBarangLain` = '" + JCNamaBarang.getSelectedItem() + "'";
                }
                dRunSelctOne.setQuery("SELECT `Namabarang`, SUM(`Total Dalam`), SUM(`Total Luar`) FROM ( SELECT `NamaBarang`, IFNULL(SUM(`JumlahHasil`)*-1*`Isi`/c.`BeratPembagi`,0) as 'Total Dalam', IFNULL(SUM(`JumlahHasil`)*-1*`Isi`/d.`BeratPembagi`,0) as 'Total Luar' FROM `tbpacking`a JOIN `tbmbarang`b ON a.`IdBarangHasil`=b.`IdBarang` JOIN `tbmbaranglain`c ON b.`IdPlastikDalam`=c.`IdBarangLain` JOIN `tbmbaranglain`d ON b.`IdPlastikLuar`=d.`IdBarangLain` WHERE " + where + " UNION ALL SELECT `NamaBarangLain`, IF(`IdJenisBarangLain`=1,ifnull(SUM(`Jumlah`),0),0) AS 'Berat Dalam', IF(`IdJenisBarangLain`=2,ifnull(SUM(`Jumlah`),0),0) AS 'Berat Luar' FROM `tbpenyesuaianlain`a JOIN `tbmbaranglain`b ON a.`IdBarangLain`=b.`IdBarangLain` WHERE `NamaBarangLain` = '" + JCNamaBarang.getSelectedItem() + "') tbtemp");
            } else if (getNamaBarang().contains("RETUR")) {
                dRunSelctOne.setQuery("SELECT 0, 0, 0");
            } else {
                dRunSelctOne.setQuery("SELECT `IdBarang`, `NamaBarang`, SUM(`Stok`) as 'Stok', SUM(`KG`) as 'KG' FROM (\n"
                        + "SELECT `IdBarang`, `NamaBarang`, 0 as 'Stok', 0 as 'KG' FROM `tbmbarang` WHERE `NamaBarang` = '" + JCNamaBarang.getSelectedItem() + "'\n"
                        + "UNION ALL\n"
                        + "SELECT a.`IdBarang`, `NamaBarang`, ifnull(`Jumlah`*-1,0) AS 'Stok', ifnull(`Jumlah`*-1*`Satuan`,0) AS 'KG' FROM `tbpenjualandetail`a JOIN `tbmbarang`b ON a.`IdBarang`=b.`IdBarang` JOIN `tbpenjualan`c ON a.`NoTransaksi`=c.`NoTransaksi` WHERE `StatusRetur` = 0 AND `NamaBarang` = '" + JCNamaBarang.getSelectedItem() + "'\n"
                        + "UNION ALL\n"
                        + "SELECT  `IdBarangHasil`, `NamaBarang`, (ROUND(`JumlahHasil` * 10 ) / 10) AS 'Stok', `JumlahHasil`*`Satuan` AS 'KG' FROM `tbpacking`a JOIN `tbmbarang`b ON a.`IdBarangHasil`=b.`IdBarang` WHERE `IdPartai` IS NOT NULL AND `NamaBarang` = '" + JCNamaBarang.getSelectedItem() + "'\n"
                        + "UNION ALL\n"
                        + "SELECT a.`IdBarang`, `NamaBarang`, ifnull(`Sak`,0) AS 'Stok', ifnull(`Jumlah`,0) AS 'KG' FROM `tbpenyesuaian`a JOIN `tbmbarang`b ON a.`IdBarang`=b.`IdBarang` WHERE `NamaBarang` = '" + JCNamaBarang.getSelectedItem() + "'\n"
                        + ") as tbTemp GROUP BY `IdBarang`");
            }
            ArrayList<String> list = dRunSelctOne.excute();
            double dobel = Double.valueOf(list.get(2));
            if (JCNamaBarang.getSelectedItem().toString().contains("DALAM")) {
                dobel = Double.valueOf(list.get(1));
            }
            JTStock.setText(Decformatdigit(dobel));
        }
    }

}
