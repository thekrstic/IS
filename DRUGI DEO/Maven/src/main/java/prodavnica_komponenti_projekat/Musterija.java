package prodavnica_komponenti_projekat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="musterija") 
public class Musterija {

	@TableGenerator(name = "musterija_gen", table = "id_gen_musterija", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "musterija_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "musterija_gen")
    private int id_musterija;
	
    @Column(name = "username")
    private String username;
 
    @Column(name = "ime")
    private String ime;
 
    @Column(name = "prezime")
    private String prezime;
 
    @Column(name = "adresa")
    private String adresa;

    @Column(name = "telefon")
    private String telefon;
    
    public Musterija() {
 
    }
 
    public Musterija(String username, String ime, String prezime, String adresa, String telefon) {
        this.setUserame(username);
    	this.setIme(ime);
        this.setPrezime(prezime);
        this.setAdresa(adresa);
        this.setTelefon(telefon);
    }
 
    public int getId() {
        return id_musterija;
    }
    
    public String getUserame() {
        return username;
    }
 
    public void setUserame(String username) {
        this.username = username;
    }
    
    public String getIme() {
        return ime;
    }
 
    public void setIme(String ime) {
        this.ime = ime;
    }
 
    public String getPrezime() {
        return prezime;
    }
 
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
 
    public String getAdresa() {
        return adresa;
    }
 
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    public String getTelefon() {
        return telefon;
    }
 
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
	
}
