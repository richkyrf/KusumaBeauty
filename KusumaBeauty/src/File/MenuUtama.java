/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;


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
        JMProses = new javax.swing.JMenu();
        JMIProsesAbsenKaryawan = new javax.swing.JMenuItem();
        JMIProsesPacking = new javax.swing.JMenuItem();
        JMList = new javax.swing.JMenu();
        JMIListPacking = new javax.swing.JMenuItem();
        JMIListPenjualan = new javax.swing.JMenuItem();
        JMLaporan = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

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

        JMIMasterKendaraan.setText("2. Master Obat");
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

        JMIListPenjualan.setText("3. List Perawatan");
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
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JMITambahUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMITambahUserActionPerformed
        if (GlobalVar.Var.tambahUser == null) {
            GlobalVar.Var.tambahUser = new TambahUser();
        } else {
            GlobalVar.Var.tambahUser.setState(NORMAL);
            GlobalVar.Var.tambahUser.toFront();
        }
    }//GEN-LAST:event_JMITambahUserActionPerformed

    private void JMIGantiPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIGantiPasswordActionPerformed
        if (GlobalVar.Var.gantiPassword == null) {
            GlobalVar.Var.gantiPassword = new GantiPassword();
        } else {
            GlobalVar.Var.gantiPassword.setState(NORMAL);
            GlobalVar.Var.gantiPassword.toFront();
        }
    }//GEN-LAST:event_JMIGantiPasswordActionPerformed

    private void JMIExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JMIExitActionPerformed

    private void JMIMasterKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIMasterKaryawanActionPerformed
        /*if (GlobalVar.Var.listKaryawan == null) {
            GlobalVar.Var.listKaryawan = new ListKaryawan();
        } else {
            GlobalVar.Var.listKaryawan.setState(NORMAL);
            GlobalVar.Var.listKaryawan.toFront();
        }*/
    }//GEN-LAST:event_JMIMasterKaryawanActionPerformed

    private void JMIListPackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIListPackingActionPerformed
        /*if (GlobalVar.Var.listPacking == null) {
            GlobalVar.Var.listPacking = new ListPacking();
        } else {
            GlobalVar.Var.listPacking.setState(NORMAL);
            GlobalVar.Var.listPacking.toFront();
        }*/
    }//GEN-LAST:event_JMIListPackingActionPerformed

    private void JMIListPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIListPenjualanActionPerformed
        /*if (GlobalVar.Var.listPenjualan == null) {
            GlobalVar.Var.listPenjualan = new ListPenjualan();
        } else {
            GlobalVar.Var.listPenjualan.setState(NORMAL);
            GlobalVar.Var.listPenjualan.toFront();
        }*/
    }//GEN-LAST:event_JMIListPenjualanActionPerformed

    private void JMIResetPasswordUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIResetPasswordUserActionPerformed
        /*if (GlobalVar.Var.resetPasswordUser == null) {
            GlobalVar.Var.resetPasswordUser = new ResetPasswordUser();
        } else {
            GlobalVar.Var.resetPasswordUser.setState(NORMAL);
            GlobalVar.Var.resetPasswordUser.toFront();
        }*/
    }//GEN-LAST:event_JMIResetPasswordUserActionPerformed

    private void JMIMasterKendaraanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIMasterKendaraanActionPerformed
        /*if (GlobalVar.Var.listKendaraan == null) {
            GlobalVar.Var.listKendaraan = new ListKendaraan();
        } else {
            GlobalVar.Var.listKendaraan.setState(NORMAL);
            GlobalVar.Var.listKendaraan.toFront();
        }*/
    }//GEN-LAST:event_JMIMasterKendaraanActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        /*if (GlobalVar.Var.listPartai == null) {
            GlobalVar.Var.listPartai = new ListPartai();
        } else {
            GlobalVar.Var.listPartai.setState(NORMAL);
            GlobalVar.Var.listPartai.toFront();
        }*/
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void JMIProsesPackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIProsesPackingActionPerformed
        /*if (GlobalVar.Var.tambahPacking == null) {
            GlobalVar.Var.tambahPacking = new Packing();
        } else {
            GlobalVar.Var.tambahPacking.setState(NORMAL);
            GlobalVar.Var.tambahPacking.toFront();
        }*/
    }//GEN-LAST:event_JMIProsesPackingActionPerformed

    private void JMIProsesAbsenKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIProsesAbsenKaryawanActionPerformed
        /*if (GlobalVar.Var.absen == null) {
            GlobalVar.Var.absen = new Absen();
        } else {
            GlobalVar.Var.absen.setState(NORMAL);
            GlobalVar.Var.absen.toFront();
        }*/
    }//GEN-LAST:event_JMIProsesAbsenKaryawanActionPerformed

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
    // End of variables declaration//GEN-END:variables
}
