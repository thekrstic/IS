package prodavnica_komponenti_projekat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="reklamacija") 
public class Reklamacija {

	@TableGenerator(name = "reklamacija_gen", table = "id_gen_reklamacija", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "reklamacija_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "reklamacija_gen")
    private int id_reklamacija;
 
    @Column(name = "id_musterija")
    private int id_musterija;
 
    @Column(name = "id_proizvod")
    private int id_proizvod;
 
    @Column(name = "datum_reklamacije")
    private Date datum_reklamacije;
    
    public Reklamacija() {
 
    }
 
    public Reklamacija(int id_musterija, int id_proizvod, Date datum) {
        this.setIdMusterija(id_musterija);
        this.setIdProizvod(id_proizvod);
        this.setDatumReklamacije(datum);
    }
 
    public int getId() {
        return id_reklamacija;
    }
 
    public int getIdMusterija() {
        return id_musterija;
    }
 
    public void setIdMusterija(int id_musterija) {
        this.id_musterija = id_musterija;
    }
 
    public int getIdProizvod() {
        return id_proizvod;
    }
 
    public void setIdProizvod(int id_proizvod) {
        this.id_proizvod = id_proizvod;
    }
    
    public Date getDatumReklamacije() {
        return datum_reklamacije;
    }
 
    public void setDatumReklamacije(Date datum_reklamacije) {
        this.datum_reklamacije= datum_reklamacije;
    }
    
	
}
