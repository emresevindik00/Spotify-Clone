/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyproje;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author msi
 */
public class KullaniciEkrani extends javax.swing.JFrame {

    /**
     * Creates new form KullaniciEkrani
     */
    public KullaniciEkrani() {
        initComponents();
        
    }
    
    ProfilEkrani profilEkrani = new ProfilEkrani();
    GirisEkrani ge;
    EnCokDinlenen enCokDinlenen = new EnCokDinlenen();
    //String kullaniciAdi = ge.kTxt.getText();
    
    
    public void listedeVarMi() throws SQLException {
        java.sql.Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        TableModel model = (DefaultTableModel) jTable1.getModel();
        int secilenIndex = jTable1.getSelectedRow();
        int secilenID = Integer.parseInt(model.getValueAt(secilenIndex,0).toString());
        
        try {
            connect = db.getConnection();
            String sql = "SELECT 1 from sistem.calmalistesi_sarkilar where "
                    + "sistem.calmalistesi_sarkilar.sarkiID = ? and listeSahibi = ?";
 
 

            statement = connect.prepareStatement(sql);            
            statement.setInt(1, secilenID);
            statement.setString(2, jLabel2.getText());
            ResultSet rs = statement.executeQuery();
            
            
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Listede Var!");
                System.out.println("-----"+rs.next());
            }
            else{
                ListeyeEkle();
                ListedeGoster(); 
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
    
    
    
    public void arananListeyiGoster() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        try {
            connect = db.getConnection();
            String sql = "select sistem.sarki.SarkiID,Sarki_Adi,Tur,Abonelik_Turu from sistem.sarki,sistem.calmalistesi_sarkilar,sistem.kullanici "
                    + "where sistem.sarki.SarkiID = sistem.calmalistesi_sarkilar.sarkiID and "
                    + "Kullanici_Adi = listeSahibi and listeSahibi= ?";
            
            statement = connect.prepareStatement(sql);
            statement.setString(1, araTxt.getText());
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                
                String sarkiID = String.valueOf(rs.getInt("SarkiID"));
                String sarki = rs.getString("Sarki_Adi");
                String tur = rs.getString("Tur");
                String abonelik_turu = rs.getString("Abonelik_Turu");
                
                if(abonelik_turu.equals("Premium")){
                    
                    String tbData[] = {sarkiID,sarki};
                    DefaultTableModel tblModel1 = (DefaultTableModel) ProfilEkrani.popListesi.getModel();
                    DefaultTableModel tblModel2 = (DefaultTableModel) ProfilEkrani.jazzListesi.getModel();
                    DefaultTableModel tblModel3 = (DefaultTableModel) ProfilEkrani.klasikListesi.getModel();
                    
                    
                    if(tur.equals("Pop")) {           
                        tblModel1.addRow(tbData);
                    }
                
                    if(tur.equals("Jazz")) {           
                        tblModel2.addRow(tbData);
                    }

                    if(tur.equals("Klasik")) {           
                        tblModel3.addRow(tbData);
                    }
                }
                
                else if(abonelik_turu.equals("Normal")) {
                    profilEkrani.takipBtn.setEnabled(false);
                    profilEkrani.jButton2.setEnabled(false);
                }
  
                
            }
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
    }
    
      
    
    
    public void kullaniciListesiniGoster() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        try {
            connect = db.getConnection();
            String sql = "select Sarki_Adi,Tur from sistem.sarki,sistem.calmalistesi_sarkilar,sistem.kullanici "
                    + "where sistem.sarki.SarkiID = sistem.calmalistesi_sarkilar.sarkiID and "
                    + "Kullanici_Adi = listeSahibi and listeSahibi= ?";
            
            statement = connect.prepareStatement(sql);
            statement.setString(1, jLabel2.getText());
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                
                String sarki = rs.getString("Sarki_Adi");
                String tur = rs.getString("Tur");
                String tbData[] = {sarki};
                DefaultTableModel tblModel1 = (DefaultTableModel) popTablosu.getModel();
                DefaultTableModel tblModel2 = (DefaultTableModel) jazzTablosu.getModel();
                DefaultTableModel tblModel3 = (DefaultTableModel) klasikTablosu.getModel();
                if(tur.equals("Pop")) {           
                    tblModel1.addRow(tbData);
                }
                
                if(tur.equals("Jazz")) {           
                    tblModel2.addRow(tbData);
                }
                
                if(tur.equals("Klasik")) {           
                    tblModel3.addRow(tbData);
                }
                
            }
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
    }

    
    
    public void ListeyeEkle() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        TableModel model = (DefaultTableModel) jTable1.getModel();
        int secilenIndex = jTable1.getSelectedRow();
        
        
        int secilenID = Integer.parseInt(model.getValueAt(secilenIndex,0).toString());
        String secilenTur = model.getValueAt(secilenIndex,5).toString();
        
        try {
            connect = db.getConnection();
            String sql = "insert into sistem.calmalistesi_sarkilar (sarkiID,listeSahibi,listeID)" +
                    "values(?,?,(SELECT listeID from sistem.calmalistesi where kullaniciAdi = ? and Tur = ?))";
            
            statement = connect.prepareStatement(sql);
            statement.setInt(1, secilenID);
            statement.setString(2, jLabel2.getText());
            statement.setString(3, jLabel2.getText());
            statement.setString(4, secilenTur);
            
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Listeye Eklendi");
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
    }
    
    
    
    public void Goster() throws SQLException {
        Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        try {
            connect = db.getConnection();
            String sql = "select * from sistem.sarki";
            
            statement = connect.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String sarkiID = String.valueOf(rs.getInt("SarkıID"));
                String sarki = rs.getString("Sarki_Adi");
                String tarih = rs.getString("Tarih");
                String sanatci = rs.getString("Sanatci");
                //String sanatciID = rs.getString("sanatciID");
                String album = rs.getString("Album");
                //String albumID = rs.getString("AlbumID");
                String tur = rs.getString("Tur");
                //String turID = rs.getString("TurID");
                String sure = rs.getString("Sure"); 
                String dinlenme = rs.getString("Dinlenme_Sayisi");   
                
                String tbData[] = {sarkiID,sarki,tarih,sanatci,album,tur,sure,dinlenme};
                DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
                
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
    
    
    
    public void KullaniciAra() throws SQLException {
        java.sql.Connection connect = null;
        DbHelper db = new DbHelper();
        PreparedStatement statement = null;
        
        try {
            connect = db.getConnection();
            String sql = "select Kullanici_Adi,Abonelik_Turu from sistem.kullanici where Kullanici_Adi = ?";
 

            statement = connect.prepareStatement(sql);            
            statement.setString(1, araTxt.getText());
            
            ResultSet rs = statement.executeQuery();
            
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "Bulundu");
                profilEkrani.kullaniciTxt.setText(rs.getString("Kullanici_Adi"));
                profilEkrani.abonelikTxt.setText(rs.getString("Abonelik_Turu"));
                profilEkrani.setVisible(true);
                   
            }
            else {
                JOptionPane.showMessageDialog(null, "Boyle bir kullanici yok!");
            }
            
            
        } catch (SQLException ex) {
            db.showErrorMessage(ex);
        }
        finally {
            statement.close();
            connect.close();
        }
    }

    
    
    public void ListedeGoster() {
        TableModel model1 = jTable1.getModel();
            int[] indeksler = jTable1.getSelectedRows();
            
            Object[] row = new Object[2];
            DefaultTableModel model2 = (DefaultTableModel) popTablosu.getModel();
            DefaultTableModel model3 = (DefaultTableModel) jazzTablosu.getModel();
            DefaultTableModel model4 = (DefaultTableModel) klasikTablosu.getModel();
             
            for (int i = 0; i < indeksler.length; i++) {
                row[0] = model1.getValueAt(indeksler[i], 1);
                row[1] = model1.getValueAt(indeksler[i], 5);
            
                if(row[1].equals("Pop")) {
                    model2.addRow(row);
                }
            
                if(row[1].equals("Jazz")) {
                    model3.addRow(row);
                }
            
                if(row[1].equals("Klasik")) {
                    model4.addRow(row);
                }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        araTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        popTablosu = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jazzTablosu = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        klasikTablosu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ara:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("-");

        jButton1.setText("Ara");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kullanıcı Adı:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Şarkı ID", "Şarkı", "Tarih", "Sanatçı", "Albüm", "Tür", "Süre", "Dinlenme "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
        }

        jButton2.setText("Listeme Ekle");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Listelerim");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Şarkılar");

        jButton3.setText("En Çok Dinlenenleri Gör");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        popTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pop"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(popTablosu);
        if (popTablosu.getColumnModel().getColumnCount() > 0) {
            popTablosu.getColumnModel().getColumn(0).setResizable(false);
        }

        jazzTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Jazz"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jazzTablosu);
        if (jazzTablosu.getColumnModel().getColumnCount() > 0) {
            jazzTablosu.getColumnModel().getColumn(0).setResizable(false);
        }

        klasikTablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Klasik"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(klasikTablosu);
        if (klasikTablosu.getColumnModel().getColumnCount() > 0) {
            klasikTablosu.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(araTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(338, 338, 338))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(araTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)))
                .addGap(42, 115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        
        try {   
            listedeVarMi(); 
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            //profilEkrani.setVisible(true);
            KullaniciAra();
            arananListeyiGoster();
            profilEkrani.arayanTxt.setText(jLabel2.getText());
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciEkrani.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        enCokDinlenen.setVisible(true);
        try {
            enCokDinlenen.popEnCokDinlenenGoster();
            enCokDinlenen.jazzEnCokDinlenenGoster();
            enCokDinlenen.klasikEnCokDinlenenGoster();
            enCokDinlenen.genelEnCokDinlenenGoster();
        } catch (SQLException ex) {
            Logger.getLogger(KullaniciEkrani.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(KullaniciEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KullaniciEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KullaniciEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KullaniciEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KullaniciEkrani().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField araTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private static javax.swing.JTable jTable1;
    public static javax.swing.JTable jazzTablosu;
    public static javax.swing.JTable klasikTablosu;
    public static javax.swing.JTable popTablosu;
    // End of variables declaration//GEN-END:variables
}
