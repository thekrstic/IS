package prodavnica_komponenti_projekat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="narudzbina_proizvod")
public class NarudzbinaProizvod {

	@TableGenerator(name = "narudzbina_proizvod_gen", table = "id_gen_narudzbina_proizvod", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "narudzbina_proizvod_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "narudzbina_proizvod_gen")
	private int id_narudzbina_proizvod;
	
	@Column(name="id_narudzbina")
	private int id_narudzbina;
	
	@Column(name="id_proizvod")
	private int id_proizvod;
	
	@Column(name="kolicina")
	private int kolicina;
	
	
	public NarudzbinaProizvod() {
	}

	public int getId() {
		return id_narudzbina_proizvod;
	}
	
	/*
	public void setId(int id) {
		this.id_narudzbina_proizvod = id;
	}
	*/

	public int getIdPorudzbine() {
		return id_narudzbina;
	}

	public void setIdPorudzbine(int id_narudzbina) {
		this.id_narudzbina = id_narudzbina;
	}

	public int getIdProizvoda() {
		return id_proizvod;
	}

	public void setIdProizvoda(int id_proizvod) {
		this.id_proizvod = id_proizvod;
	}
	
	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
}
