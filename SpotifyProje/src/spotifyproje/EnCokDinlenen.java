/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyproje;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author msi
 */
public class EnCokDinlenen extends javax.swing.JFrame {

    /**
     * Creates new form EnCokDinlenen
     */
    public EnCokDinlenen() {
        initComponents();
        
    }
    
    
    public void popEnCokDinlenenGoster() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        try {
            connect = db.getConnection();
            String sql = "select * from sistem.sarki where Tur='Pop' order by Dinlenme_Sayisi DESC limit 10";
            statement = connect.prepareStatement(sql);
            
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                
                String sarki = rs.getString("Sarki_Adi");
                String sanatci = rs.getString("Sanatci");
                String tur = rs.getString("Tur");
                String dinlenme = rs.getString("Dinlenme_Sayisi");   
                
                String tbData[] = {sarki,sanatci,tur,dinlenme};
                DefaultTableModel tblModel = (DefaultTableModel) popTbl.getModel();
                
                tblModel.addRow(tbData);
            }
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
    }
    
    
    public void jazzEnCokDinlenenGoster() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        try {
            connect = db.getConnection();
            String sql = "select * from sistem.sarki where Tur='Jazz' order by Dinlenme_Sayisi DESC limit 10";
            statement = connect.prepareStatement(sql);
            
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                
                String sarki = rs.getString("Sarki_Adi");
                String sanatci = rs.getString("Sanatci");
                String tur = rs.getString("Tur");
                String dinlenme = rs.getString("Dinlenme_Sayisi");   
                
                String tbData[] = {sarki,sanatci,tur,dinlenme};
                DefaultTableModel tblModel = (DefaultTableModel) jazzTbl.getModel();
                
                tblModel.addRow(tbData);
            }
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
    }
    

    public void klasikEnCokDinlenenGoster() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        try {
            connect = db.getConnection();
            String sql = "select * from sistem.sarki where Tur='Jazz' order by Dinlenme_Sayisi DESC limit 10";
            statement = connect.prepareStatement(sql);
            
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                
                String sarki = rs.getString("Sarki_Adi");
                String sanatci = rs.getString("Sanatci");
                String tur = rs.getString("Tur");
                String dinlenme = rs.getString("Dinlenme_Sayisi");   
                
                String tbData[] = {sarki,sanatci,tur,dinlenme};
                DefaultTableModel tblModel = (DefaultTableModel) klasikTbl.getModel();
                
                tblModel.addRow(tbData);
            }
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
    }
    
    
    public void genelEnCokDinlenenGoster() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        try {
            connect = db.getConnection();
            String sql = "select * from sistem.sarki order by Dinlenme_Sayisi DESC limit 10";
            statement = connect.prepareStatement(sql);
            
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                
                String sarki = rs.getString("Sarki_Adi");
                String sanatci = rs.getString("Sanatci");
                String tur = rs.getString("Tur");
                String dinlenme = rs.getString("Dinlenme_Sayisi");   
                
                String tbData[] = {sarki,sanatci,tur,dinlenme};
                DefaultTableModel tblModel = (DefaultTableModel) genelTbl.getModel();
                
                tblModel.addRow(tbData);
            }
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        popTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jazzTbl = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        genelTbl = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        klasikTbl = new javax.swing.JTable();
        geriBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        popTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sarki", "Sanatci", "Tur", "Dinlenme"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(popTbl);

        jLabel1.setText("Klasik");

        jLabel2.setText("Pop");

        jLabel3.setText("Genel");

        jLabel4.setText("Jazz");

        jazzTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sarki", "Sanatci", "Tur", "Dinlenme"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jazzTbl);

        genelTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sarki", "Sanatci", "Tur", "Dinlenme"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(genelTbl);

        klasikTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sarki", "Sanatci", "Tur", "Dinlenme"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(klasikTbl);

        geriBtn.setText("Geri Dön");
        geriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geriBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(geriBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(308, 308, 308)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(132, 132, 132))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(geriBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void geriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geriBtnActionPerformed
        this.toBack();
        setVisible(false);
        new KullaniciEkrani().toFront();
        new KullaniciEkrani().setState(java.awt.Frame.NORMAL);
    }//GEN-LAST:event_geriBtnActionPerformed

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
            java.util.logging.Logger.getLogger(EnCokDinlenen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EnCokDinlenen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EnCokDinlenen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EnCokDinlenen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnCokDinlenen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable genelTbl;
    private javax.swing.JButton geriBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    public static javax.swing.JTable jazzTbl;
    public static javax.swing.JTable klasikTbl;
    public static javax.swing.JTable popTbl;
    // End of variables declaration//GEN-END:variables
}
