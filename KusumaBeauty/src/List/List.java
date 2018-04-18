/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import LSubProces.Delete;
import Master.*;
import javax.swing.JOptionPane;
import static GlobalVar.Var.*;
import Proses.Penjualan;
import javax.swing.JFrame;

/**
 *
 * @author richky
 */
public class List extends javax.swing.JFrame {

    /**
     * Creates new form ListKaryawan
     */
    String Type;

    public List(String type) {
        Type = type;
        initComponents();
        setVisible(true);
        switch (Type) {
            case "Master Barang":
                setTitle("List Master Barang");
                break;
            case "Master Dokter":
                setTitle("List Master Dokter");
                break;
            case "Master Pasien":
                setTitle("List Master Pasien");
                break;
            case "Master Beautician":
                setTitle("List Master Beautician");
                break;
            case "Penjualan":
                setTitle("List Penjualan");
                break;
            default:
                throw new AssertionError();
        }
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        refreshAll();
        if (!GlobalVar.VarL.level.equals("Administrator")) {
            jbuttonF2.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbuttonF1 = new KomponenGUI.JbuttonF();
        jbuttonF2 = new KomponenGUI.JbuttonF();
        jbuttonF3 = new KomponenGUI.JbuttonF();
        jbuttonF4 = new KomponenGUI.JbuttonF();
        jcomCari1 = new KomponenGUI.JcomCari();
        jbuttonF5 = new KomponenGUI.JbuttonF();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jbuttonF1.setText("Ubah");
        jbuttonF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF1ActionPerformed(evt);
            }
        });

        jbuttonF2.setText("Hapus");
        jbuttonF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF2ActionPerformed(evt);
            }
        });

        jbuttonF3.setText("Refresh");
        jbuttonF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF3ActionPerformed(evt);
            }
        });

        jbuttonF4.setText("Kembali");
        jbuttonF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF4ActionPerformed(evt);
            }
        });

        jbuttonF5.setText("Tambah");
        jbuttonF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonF5ActionPerformed(evt);
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
                        .addComponent(jcomCari1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jbuttonF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbuttonF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcomCari1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF1ActionPerformed
        ubah();
    }//GEN-LAST:event_jbuttonF1ActionPerformed

    private void jbuttonF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF4ActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonF4ActionPerformed

    private void jbuttonF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF2ActionPerformed
        hapus();
    }//GEN-LAST:event_jbuttonF2ActionPerformed

    private void jbuttonF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF3ActionPerformed
        refreshAll();
    }//GEN-LAST:event_jbuttonF3ActionPerformed

    private void jbuttonF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonF5ActionPerformed
        tambah();
    }//GEN-LAST:event_jbuttonF5ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        switch (Type) {
            case "Master Barang":
                listMasterBarang = null;
                break;
            case "Master Dokter":
                listMasterDokter = null;
                break;
            case "Master Pasien":
                listMasterPasien = null;
                break;
            case "Master Beautician":
                listMasterBeautician = null;
                break;
            case "Penjualan":
                listPenjualan = null;
                break;
            default:
                throw new AssertionError();
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
            java.util.logging.Logger.getLogger(List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(List.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new List("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private KomponenGUI.JbuttonF jbuttonF1;
    private KomponenGUI.JbuttonF jbuttonF2;
    private KomponenGUI.JbuttonF jbuttonF3;
    private KomponenGUI.JbuttonF jbuttonF4;
    private KomponenGUI.JbuttonF jbuttonF5;
    private KomponenGUI.JcomCari jcomCari1;
    // End of variables declaration//GEN-END:variables

    void refreshAll() {
        jcomCari1.refresh();
        load();
    }

    void tambah() {
        switch (Type) {
            case "Master Barang":
                if (tambahMasterBarang == null) {
                    tambahMasterBarang = new MasterBarang();
                } else {
                    tambahMasterBarang.setState(NORMAL);
                    tambahMasterBarang.toFront();
                }
                break;
            case "Master Dokter":
                if (tambahMasterDokter == null) {
                    tambahMasterDokter = new MasterDokter();
                } else {
                    tambahMasterDokter.setState(NORMAL);
                    tambahMasterDokter.toFront();
                }
                break;
            case "Master Pasien":
                if (tambahMasterPasien == null) {
                    tambahMasterPasien = new MasterPasien();
                } else {
                    tambahMasterPasien.setState(NORMAL);
                    tambahMasterPasien.toFront();
                }
                break;
            case "Master Beautician":
                if (tambahMasterBeautician == null) {
                    tambahMasterBeautician = new MasterBeautician();
                } else {
                    tambahMasterBeautician.setState(NORMAL);
                    tambahMasterBeautician.toFront();
                }
                break;
            case "Penjualan":
                if (tambahPenjualan == null) {
                    tambahPenjualan = new Penjualan();
                } else {
                    tambahPenjualan.setState(NORMAL);
                    tambahPenjualan.toFront();
                }
                break;
            default:
                throw new AssertionError();
        }

    }

    void hapus() {
        if (jcomCari1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Silahkan Pilih Data Terlebih Dahulu", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Delete delete = new Delete();
            Boolean berhasil = false;
            switch (Type) {
                case "Master Barang":
                    berhasil = delete.Hapus(jcomCari1.GetIDTable(), "DELETE FROM `tbmbarang` WHERE `IdBarang` = " + jcomCari1.GetIDTable(), "Barang", this);
                    break;
                case "Master Dokter":
                    berhasil = delete.Hapus(jcomCari1.GetIDTable(), "DELETE FROM `tbmdokter` WHERE `IdDokter` = " + jcomCari1.GetIDTable(), "Dokter", this);
                    break;
                case "Master Pasien":
                    berhasil = delete.Hapus(jcomCari1.GetIDTable(), "DELETE FROM `tbmpasien` WHERE `IdPasien` = " + jcomCari1.GetIDTable(), "Pasien", this);
                    break;
                case "Master Beautician":
                    berhasil = delete.Hapus(jcomCari1.GetIDTable(), "DELETE FROM `tbmbeautician` WHERE `IdBeautician` = " + jcomCari1.GetIDTable(), "Beautician", this);
                    break;
                case "Penjualan":
                    berhasil = delete.Hapus(jcomCari1.GetIDTable(), "DELETE FROM `tbpenjualan` WHERE `IdPenjualan` = " + jcomCari1.GetIDTable(), "Penjualan", this);
                    break;
                default:
                    throw new AssertionError();
            }
            if (berhasil) {
                refreshAll();
            }
        }
    }

    void ubah() {
        if (jcomCari1.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Silahkan Pilih Data Terlebih Dahulu", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            switch (Type) {
                case "Master Barang":
                if (ubahMasterBarang == null) {
                    ubahMasterBarang = new MasterBarang(jcomCari1.GetIDTable());
                } else {
                    ubahMasterBarang.setState(NORMAL);
                    ubahMasterBarang.toFront();
                }
                    break;
                case "Master Dokter":
                if (ubahMasterDokter == null) {
                    ubahMasterDokter = new MasterDokter(jcomCari1.GetIDTable());
                } else {
                    ubahMasterDokter.setState(NORMAL);
                    ubahMasterDokter.toFront();
                }
                    break;
                case "Master Pasien":
                if (ubahMasterPasien == null) {
                    ubahMasterPasien = new MasterPasien(jcomCari1.GetIDTable());
                } else {
                    ubahMasterPasien.setState(NORMAL);
                    ubahMasterPasien.toFront();
                }
                    break;
                case "Master Beautician":
                if (ubahMasterBeautician == null) {
                    ubahMasterBeautician = new MasterBeautician(jcomCari1.GetIDTable());
                } else {
                    ubahMasterBeautician.setState(NORMAL);
                    ubahMasterBeautician.toFront();
                }
                    break;
                case "Penjualan":
                if (ubahPenjualan == null) {
                    ubahPenjualan = new Penjualan(jcomCari1.GetIDTable());
                } else {
                    ubahPenjualan.setState(NORMAL);
                    ubahPenjualan.toFront();
                }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void load() {
        switch (Type) {
            case "Master Barang":
                jcomCari1.setQuery("SELECT `IdBarang` as 'ID', `NamaBarang` as 'Nama Barang', `JenisBarang`, `Harga`, a.`Keterangan`, IF(`Status`=1,'Aktif','Tidak Aktif') as 'Status' FROM `tbmbarang`a JOIN `tbsmjenisbarang`b ON a.`IdJenisBarang`=b.`IdJenisBarang` WHERE 1");
                jcomCari1.setRender(new int[]{3}, new String[]{"Number"});
                jcomCari1.setOrder(" ORDER BY `JenisBarang`, `NamaBarang` ");
                break;
            case "Master Dokter":
                jcomCari1.setQuery("SELECT `IdDokter` as 'ID', `NamaDokter` as 'Nama Dokter', `NoTelepon` as 'No Telpon', `Alamat`, `Keterangan`, IF(`Status`=1,'Aktif','Tidak Aktif') as 'Status' FROM `tbmdokter` WHERE 1 ");
                jcomCari1.setOrder(" ORDER BY `NamaDokter` ");
                break;
            case "Master Pasien":
                jcomCari1.setQuery("SELECT `IdPasien` as 'ID', `KodePasien` as 'Kode', `NamaPasien` as 'Nama', `JenisKelamin` as 'Jenis Kelamin', DATE_FORMAT(`TanggalDaftar`,'%d-%m-%Y') as 'Tanggal Daftar', DATE_FORMAT(`TanggaLahir`,'%d-%m-%Y') as 'Tanggal Lahir', `NoTelpon` as 'No. Telpon', `Pekerjaan`, `Email`, `Alamat`, `Keterangan`, IF(`Status`=1,'Aktif','Tidak Aktif') as 'Status' FROM `tbmpasien` WHERE 1");
                jcomCari1.setOrder(" ORDER BY `NamaPasien` ");
                break;
            case "Master Beautician":
                jcomCari1.setQuery("SELECT `IdBeautician` as 'ID', `NamaBeautician` as 'Nama Beautician', `NoTelepon` as 'No Telpon', `Alamat`, `Keterangan`, IF(`Status`=1,'Aktif','Tidak Aktif') as 'Status' FROM `tbmbeautician` WHERE 1 ");
                jcomCari1.setOrder(" ORDER BY `NamaBeautician` ");
                break;
            case "Penjualan":
                jcomCari1.setQuery("SELECT `IdPenjualan` as 'ID', `NoTransaksi` as 'No. Transaksi', DATE_FORMAT(`Tanggal`,'%d-%m-%Y') as 'Tanggal', IFNULL(`NamaPasien`,'-') as 'Nama Pasien', a.`Keterangan` FROM `tbpenjualan`a LEFT JOIN `tbmpasien`b ON a.`IdPasien`=b.`IdPasien` WHERE 1");
                jcomCari1.setOrder(" ORDER BY `NoTransaksi` DESC ");
                break;
            default:
                throw new AssertionError();
        }
        jcomCari1.tampilkan();
    }

}
