/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyproje;

import java.sql.SQLException;

/**
 *
 * @author msi
 */
public class SpotifyProje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        ProfilEkrani pe = new ProfilEkrani();
        System.out.println(pe.popListesi.getColumnName(0));
        GirisEkrani ge= new GirisEkrani();
        ge.setVisible(true);
        
    }
    
}
