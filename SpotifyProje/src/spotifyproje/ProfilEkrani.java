/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyproje;

import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static spotifyproje.KullaniciEkrani.jLabel2;

/**
 *
 * @author msi
 */
public class ProfilEkrani extends javax.swing.JFrame {

    /**
     * Creates new form ProfilEkrani
     */
    public ProfilEkrani() {
        initComponents();
        popListesi.setVisible(false);
        jazzListesi.setVisible(false);
        klasikListesi.setVisible(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
    }
    
    KullaniciEkrani kullaniciEkrani;
    GirisEkrani girisEkrani;
    
    
    public void popListedeVarMi() throws SQLException {
        java.sql.Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        TableModel model = (DefaultTableModel) popListesi.getModel();
        int secilenIndex = popListesi.getSelectedRow();
        int secilenID = Integer.parseInt(model.getValueAt(secilenIndex,0).toString());
        
        try {
            connect = db.getConnection();
            String sql = "SELECT 1 from sistem.calmalistesi_sarkilar where "
                    + "sistem.calmalistesi_sarkilar.sarkiID = ? and listeSahibi = ?";
 
            statement = connect.prepareStatement(sql);            
            statement.setInt(1, secilenID);
            statement.setString(2, arayanTxt.getText());
            ResultSet rs = statement.executeQuery();
            
            
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Listede Var!");
                System.out.println("-----"+rs.next());
            }
            else{
                popListeneEkle();
                arayanPopListesindeGoster();
                System.out.println("-----"+rs.next());
            }
            
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
        
    }
    
    
    
    public void jazzListedeVarMi() throws SQLException {
        java.sql.Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        TableModel model = (DefaultTableModel) jazzListesi.getModel();
        int secilenIndex = jazzListesi.getSelectedRow();
        int secilenID = Integer.parseInt(model.getValueAt(secilenIndex,0).toString());
        
        try {
            connect = db.getConnection();
            String sql = "SELECT 1 from sistem.calmalistesi_sarkilar where "
                    + "sistem.calmalistesi_sarkilar.sarkiID = ? and listeSahibi = ?";
 
            statement = connect.prepareStatement(sql);            
            statement.setInt(1, secilenID);
            statement.setString(2, arayanTxt.getText());
            ResultSet rs = statement.executeQuery();
            
            
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Listede Var!");
                System.out.println("-----"+rs.next());
            }
            else{
                jazzListeneEkle();
                arayanJazzListesindeGoster();
                System.out.println("-----"+rs.next());
            }
            
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
        
    }
    
    
    public void klasikListedeVarMi() throws SQLException {
        java.sql.Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        TableModel model = (DefaultTableModel) klasikListesi.getModel();
        int secilenIndex = klasikListesi.getSelectedRow();
        int secilenID = Integer.parseInt(model.getValueAt(secilenIndex,0).toString());
        
        try {
            connect = db.getConnection();
            String sql = "SELECT 1 from sistem.calmalistesi_sarkilar where "
                    + "sistem.calmalistesi_sarkilar.sarkiID = ? and listeSahibi = ?";
 
            statement = connect.prepareStatement(sql);            
            statement.setInt(1, secilenID);
            statement.setString(2, arayanTxt.getText());
            ResultSet rs = statement.executeQuery();
            
            
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Listede Var!");
                System.out.println("-----"+rs.next());
            }
            else{
                klasikListeneEkle();
                arayanKlasikListesindeGoster();
                System.out.println("-----"+rs.next());
            }
            
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
        
    }
    
     
    public void popListeneEkle() throws SQLException {
        java.sql.Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        DefaultTableModel model = (DefaultTableModel) popListesi.getModel();
        int secilenIndex = popListesi.getSelectedRow();
        

        try {
            connect = db.getConnection();
            String sql = "insert into sistem.calmalistesi_sarkilar (sarkiID,listeSahibi,listeID) " +
                    "values(?,?,(SELECT listeID from sistem.calmalistesi where kullaniciAdi = ? and Tur = ?))";
                    
            
            statement = connect.prepareStatement(sql);
            
            statement.setString(1, model.getValueAt(secilenIndex, 0).toString());
            statement.setString(2, arayanTxt.getText());
            statement.setString(3, arayanTxt.getText());
            statement.setString(4, "Pop");
            
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kullanicinin Pop Listesine Eklendi");
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
            System.out.println("Hata: "+ex.getErrorCode());
            ex.printStackTrace();
        }
        finally {
            statement.close();
            connect.close();
        }
    }
    
    
    
    public void jazzListeneEkle() throws SQLException {
        java.sql.Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        DefaultTableModel model = (DefaultTableModel) jazzListesi.getModel();
        int secilenIndex = jazzListesi.getSelectedRow();
        

        try {
            connect = db.getConnection();
            String sql = "insert into sistem.calmalistesi_sarkilar (sarkiID,listeSahibi,listeID) " +
                    "values(?,?,(SELECT listeID from sistem.calmalistesi where kullaniciAdi = ? and Tur = ?))";
                    
            
            statement = connect.prepareStatement(sql);
            
            statement.setString(1, model.getValueAt(secilenIndex, 0).toString());
            statement.setString(2, arayanTxt.getText());
            statement.setString(3, arayanTxt.getText());
            statement.setString(4, "Jazz");
            

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kullanicinin Jazz Listesine Eklendi");
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
            System.out.println("Hata: "+ex.getErrorCode());
            ex.printStackTrace();
        }
        finally {
            statement.close();
            connect.close();
        }
    }
    
    
    
    public void klasikListeneEkle() throws SQLException {
        java.sql.Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        DefaultTableModel model = (DefaultTableModel) klasikListesi.getModel();
        int secilenIndex = klasikListesi.getSelectedRow();
        

        try {
            connect = db.getConnection();
            String sql = "insert into sistem.calmalistesi_sarkilar (sarkiID,listeSahibi,listeID) " +
                    "values(?,?,(SELECT listeID from sistem.calmalistesi where kullaniciAdi = ? and Tur = ?))";
                    
            
            statement = connect.prepareStatement(sql);
            
            statement.setString(1, model.getValueAt(secilenIndex, 0).toString());
            statement.setString(2, arayanTxt.getText());
            statement.setString(3, arayanTxt.getText());
            statement.setString(4, "Klasik");
            
           
            
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Kullanicinin Klasik Listesine Eklendi");
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
            System.out.println("Hata: "+ex.getErrorCode());
            ex.printStackTrace();
        }
        finally {
            statement.close();
            connect.close();
        }
    }

    
    public void arayanPopListesindeGoster() {
        TableModel model1 = popListesi.getModel();
        int[] indeksler = popListesi.getSelectedRows();
            
        Object[] row = new Object[1];
        DefaultTableModel model2 = (DefaultTableModel) kullaniciEkrani.popTablosu.getModel();
        System.out.println("Eklendi");     
        for (int i = 0; i < indeksler.length; i++) {
            row[0] = model1.getValueAt(indeksler[i], 1);
            model2.addRow(row);
        } 
    }
    
    
    public void arayanJazzListesindeGoster() {
        TableModel model1 = jazzListesi.getModel();
        int[] indeksler = jazzListesi.getSelectedRows();
            
        Object[] row = new Object[1];
        DefaultTableModel model2 = (DefaultTableModel) kullaniciEkrani.jazzTablosu.getModel();
             
        for (int i = 0; i < indeksler.length; i++) {
            row[0] = model1.getValueAt(indeksler[i], 1);
            model2.addRow(row);
        } 
    }
    
    public void arayanKlasikListesindeGoster() {
        TableModel model1 = klasikListesi.getModel();
        int[] indeksler = klasikListesi.getSelectedRows();
            
        Object[] row = new Object[1];
        DefaultTableModel model2 = (DefaultTableModel) kullaniciEkrani.klasikTablosu.getModel();
             
        for (int i = 0; i < indeksler.length; i++) {
            row[0] = model1.getValueAt(indeksler[i], 1);
            model2.addRow(row);
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

        jLabel1 = new javax.swing.JLabel();
        kullaniciTxt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        abonelikTxt = new javax.swing.JLabel();
        takipBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jazzListesi = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        popListesi = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        klasikListesi = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        geriDonBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        arayanTxt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Kullanıcı Adı:");

        kullaniciTxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        kullaniciTxt.setText("-");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Abonelik Türü:");

        abonelikTxt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        abonelikTxt.setText("-");

        takipBtn.setText("Takip Et");
        takipBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takipBtnActionPerformed(evt);
            }
        });

        jazzListesi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sarki ID", "Sarki"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jazzListesi);
        if (jazzListesi.getColumnModel().getColumnCount() > 0) {
            jazzListesi.getColumnModel().getColumn(0).setResizable(false);
            jazzListesi.getColumnModel().getColumn(1).setResizable(false);
        }

        popListesi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sarki ID", "Sarki"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(popListesi);
        if (popListesi.getColumnModel().getColumnCount() > 0) {
            popListesi.getColumnModel().getColumn(0).setResizable(false);
        }

        klasikListesi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sarki ID", "Sarki"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(klasikListesi);
        if (klasikListesi.getColumnModel().getColumnCount() > 0) {
            klasikListesi.getColumnModel().getColumn(0).setResizable(false);
            klasikListesi.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton2.setText("Pop Listesine Ekle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        geriDonBtn.setText("Geri Dön");
        geriDonBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geriDonBtnActionPerformed(evt);
            }
        });

        jButton3.setText("Jazz Listesine Ekle");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Klasik Listesine Ekle");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        arayanTxt.setText("-");

        jLabel3.setText("Kullanıcı:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(geriDonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(takipBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(arayanTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(kullaniciTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(abonelikTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addGap(40, 40, 40)
                .addComponent(jButton3)
                .addGap(48, 48, 48)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(geriDonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(kullaniciTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(abonelikTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(takipBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(arayanTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(15, 15, 15))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void takipBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takipBtnActionPerformed
        popListesi.setVisible(true);
        jazzListesi.setVisible(true);
        klasikListesi.setVisible(true);
        takipBtn.setEnabled(false);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
    }//GEN-LAST:event_takipBtnActionPerformed

    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {    
            popListedeVarMi();
        } catch (SQLException ex) {
            Logger.getLogger(ProfilEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void geriDonBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geriDonBtnActionPerformed
        // TODO add your handling code here:
        this.toBack();
        setVisible(false);
        new KullaniciEkrani().toFront();
        new KullaniciEkrani().setState(java.awt.Frame.NORMAL);
    }//GEN-LAST:event_geriDonBtnActionPerformed

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            jazzListedeVarMi();
        } catch (SQLException ex) {
            Logger.getLogger(ProfilEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:
            klasikListedeVarMi();
        } catch (SQLException ex) {
            Logger.getLogger(ProfilEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    
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
            java.util.logging.Logger.getLogger(ProfilEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfilEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfilEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfilEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfilEkrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel abonelikTxt;
    public javax.swing.JLabel arayanTxt;
    public javax.swing.JButton geriDonBtn;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTable jazzListesi;
    public static javax.swing.JTable klasikListesi;
    public javax.swing.JLabel kullaniciTxt;
    public static javax.swing.JTable popListesi;
    public javax.swing.JButton takipBtn;
    // End of variables declaration//GEN-END:variables
}
