package prodavnica_komponenti_projekat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="proizvod") 
public class Proizvod {

	@TableGenerator(name = "proizvod_gen", table = "id_gen_proizvod", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "proizvod_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "proizvod_gen")
    private int id_proizvod;
 
    @Column(name = "serijski_broj")
    private String serijski_broj;
    
    @Column(name = "tip_komponente")
    private String tip_komponente;
 
    @Column(name = "naziv")
    private String naziv;
 
    @Column(name = "slika")
    private String slika;

    @Column(name = "cena")
    private int cena;
    
    @Column(name = "kolicina")
    private int kolicina;
    
    public Proizvod() {
 
    }
 
    public Proizvod(String serijski_broj, String tip_komponente, String naziv, String slika, int cena, int kolicina) {
        this.setSerijskiBroj(serijski_broj);
        this.setTipKomponente(tip_komponente);
        this.setNaziv(naziv);
        this.setSlika(slika);
        this.setCena(cena);
        this.setKolicina(kolicina);
    }
 
    public int getId() {
        return id_proizvod;
    }
 
    public String getSerijskiBroj() {
        return serijski_broj;
    }
 
    public void setSerijskiBroj(String serijski_broj) {
        this.serijski_broj = serijski_broj;
    }
 
    public String getTipKomponente() {
        return tip_komponente;
    }
 
    public void setTipKomponente(String tip_komponente) {
        this.tip_komponente = tip_komponente;
    }
    
    public String getNaziv() {
        return naziv;
    }
 
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
 
    public String Slika() {
        return slika;
    }
 
    public void setSlika(String slika) {
        this.slika = slika;
    }
    public int getCena() {
        return cena;
    }
 
    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }
 
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
