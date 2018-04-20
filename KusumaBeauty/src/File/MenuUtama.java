/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import List.*;
import Proses.*;
import static GlobalVar.Var.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author richk
 */
public class MenuUtama extends javax.swing.JFrame {

    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Menu Utama");
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlableF1 = new KomponenGUI.JlableF();
        jbuttonF1 = new KomponenGUI.JbuttonF();
        JMenuBar = new javax.swing.JMenuBar();
        JMFile = new javax.swing.JMenu();
        JMITambahUser = new javax.swing.JMenuItem();
        JMIResetPasswordUser = new javax.swing.JMenuItem();
        SFile = new javax.swing.JPopupMenu.Separator();
        JMIGantiPassword = new javax.swing.JMenuItem();
        JMIExit = new javax.swing.JMenuItem();
        JMMaster = new javax.swing.JMenu();
        JMIMasterKaryawan = new javax.swing.JMenuItem();
        JMIMasterKendaraan = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        JMProses = new javax.swing.JMenu();
        JMIProsesAbsenKaryawan = new javax.swing.JMenuItem();
        JMIProsesPacking = new javax.swing.JMenuItem();
        JMList = new javax.swing.JMenu();
        JMIListPacking = new javax.swing.JMenuItem();
        JMIListPenjualan = new javax.swing.JMenuItem();
        JMLaporan = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Antrian", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        jlableF1.setText("Pasien Hari Ini: 0");

        jbuttonF1.setText("Lihat");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlableF1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlableF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbuttonF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JMFile.setText("File");

        if (!GlobalVar.VarL.level.equals("Administrator")) {
            JMITambahUser.setVisible(false);
        }
        JMITambahUser.setText("Tambah User");
        JMITambahUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMITambahUserActionPerformed(evt);
            }
        });
        JMFile.add(JMITambahUser);

        if (!GlobalVar.VarL.level.equals("Administrator")) {
            JMIResetPasswordUser.setVisible(false);
            SFile.setVisible(false);
        }
        JMIResetPasswordUser.setText("Reset Password User");
        JMIResetPasswordUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIResetPasswordUserActionPerformed(evt);
            }
        });
        JMFile.add(JMIResetPasswordUser);
        JMFile.add(SFile);

        JMIGantiPassword.setText("Ganti Password");
        JMIGantiPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIGantiPasswordActionPerformed(evt);
            }
        });
        JMFile.add(JMIGantiPassword);

        JMIExit.setText("Exit");
        JMIExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIExitActionPerformed(evt);
            }
        });
        JMFile.add(JMIExit);

        JMenuBar.add(JMFile);

        JMMaster.setText("Master");

        JMIMasterKaryawan.setText("1. Master Pasien");
        JMIMasterKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIMasterKaryawanActionPerformed(evt);
            }
        });
        JMMaster.add(JMIMasterKaryawan);

        JMIMasterKendaraan.setText("2. Master Barang");
        JMIMasterKendaraan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIMasterKendaraanActionPerformed(evt);
            }
        });
        JMMaster.add(JMIMasterKendaraan);

        jMenuItem1.setText("3. Master Dokter");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        JMMaster.add(jMenuItem1);

        jMenuItem2.setText("4. Master Beautician");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        JMMaster.add(jMenuItem2);

        jMenuItem3.setText("5. Master Tindakan");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        JMMaster.add(jMenuItem3);

        jMenuItem4.setText("6. Master Pemasok");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        JMMaster.add(jMenuItem4);

        JMenuBar.add(JMMaster);

        JMProses.setText("Proses");

        JMIProsesAbsenKaryawan.setText("1. Penjualan");
        JMIProsesAbsenKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIProsesAbsenKaryawanActionPerformed(evt);
            }
        });
        JMProses.add(JMIProsesAbsenKaryawan);

        JMIProsesPacking.setText("2. Perawatan");
        JMIProsesPacking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIProsesPackingActionPerformed(evt);
            }
        });
        JMProses.add(JMIProsesPacking);

        JMenuBar.add(JMProses);

        JMList.setText("List");

        JMIListPacking.setText("1. List Penjualan");
        JMIListPacking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIListPackingActionPerformed(evt);
            }
        });
        JMList.add(JMIListPacking);

        JMIListPenjualan.setText("2. List Perawatan");
        JMIListPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIListPenjualanActionPerformed(evt);
            }
        });
        JMList.add(JMIListPenjualan);

        JMenuBar.add(JMList);

        JMLaporan.setText("Laporan");
        JMenuBar.add(JMLaporan);

        setJMenuBar(JMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(608, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMITambahUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMITambahUserActionPerformed
        if (tambahUser == null) {
            tambahUser = new TambahUser();
        } else {
            tambahUser.setState(NORMAL);
            tambahUser.toFront();
        }
    }//GEN-LAST:event_JMITambahUserActionPerformed

    private void JMIGantiPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIGantiPasswordActionPerformed
        if (gantiPassword == null) {
            gantiPassword = new GantiPassword();
        } else {
            gantiPassword.setState(NORMAL);
            gantiPassword.toFront();
        }
    }//GEN-LAST:event_JMIGantiPasswordActionPerformed

    private void JMIExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JMIExitActionPerformed

    private void JMIMasterKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIMasterKaryawanActionPerformed
        if (listMasterPasien == null) {
            listMasterPasien = new List("Master Pasien");
        } else {
            listMasterPasien.setState(NORMAL);
            listMasterPasien.toFront();
        }
    }//GEN-LAST:event_JMIMasterKaryawanActionPerformed

    private void JMIListPackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIListPackingActionPerformed
        if (listPenjualan == null) {
            listPenjualan = new List("Penjualan");
        } else {
            listPenjualan.setState(NORMAL);
            listPenjualan.toFront();
        }
    }//GEN-LAST:event_JMIListPackingActionPerformed

    private void JMIListPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIListPenjualanActionPerformed

    }//GEN-LAST:event_JMIListPenjualanActionPerformed

    private void JMIResetPasswordUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIResetPasswordUserActionPerformed

    }//GEN-LAST:event_JMIResetPasswordUserActionPerformed

    private void JMIMasterKendaraanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIMasterKendaraanActionPerformed
        if (listMasterBarang == null) {
            listMasterBarang = new List("Master Barang");
        } else {
            listMasterBarang.setState(NORMAL);
            listMasterBarang.toFront();
        }
    }//GEN-LAST:event_JMIMasterKendaraanActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (listMasterDokter == null) {
            listMasterDokter = new List("Master Dokter");
        } else {
            listMasterDokter.setState(NORMAL);
            listMasterDokter.toFront();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void JMIProsesPackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIProsesPackingActionPerformed

    }//GEN-LAST:event_JMIProsesPackingActionPerformed

    private void JMIProsesAbsenKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIProsesAbsenKaryawanActionPerformed
        if (tambahPenjualan == null) {
            tambahPenjualan = new Penjualan();
        } else {
            tambahPenjualan.setState(NORMAL);
            tambahPenjualan.toFront();
        }
    }//GEN-LAST:event_JMIProsesAbsenKaryawanActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (listMasterBeautician == null) {
            listMasterBeautician = new List("Master Beautician");
        } else {
            listMasterBeautician.setState(NORMAL);
            listMasterBeautician.toFront();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (listMasterTindakan == null) {
            listMasterTindakan = new List("Master Tindakan");
        } else {
            listMasterTindakan.setState(NORMAL);
            listMasterTindakan.toFront();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (listMasterPemasok == null) {
            listMasterPemasok = new List("Master Pemasok");
        } else {
            listMasterPemasok.setState(NORMAL);
            listMasterPemasok.toFront();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JMFile;
    private javax.swing.JMenuItem JMIExit;
    private javax.swing.JMenuItem JMIGantiPassword;
    private javax.swing.JMenuItem JMIListPacking;
    private javax.swing.JMenuItem JMIListPenjualan;
    private javax.swing.JMenuItem JMIMasterKaryawan;
    private javax.swing.JMenuItem JMIMasterKendaraan;
    private javax.swing.JMenuItem JMIProsesAbsenKaryawan;
    private javax.swing.JMenuItem JMIProsesPacking;
    private javax.swing.JMenuItem JMIResetPasswordUser;
    private javax.swing.JMenuItem JMITambahUser;
    private javax.swing.JMenu JMLaporan;
    private javax.swing.JMenu JMList;
    private javax.swing.JMenu JMMaster;
    private javax.swing.JMenu JMProses;
    private javax.swing.JMenuBar JMenuBar;
    private javax.swing.JPopupMenu.Separator SFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private KomponenGUI.JbuttonF jbuttonF1;
    public static KomponenGUI.JlableF jlableF1;
    // End of variables declaration//GEN-END:variables
}
