package prodavnica_komponenti_projekat;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="narudzbina") 
public class Narudzbina {

	@TableGenerator(name = "narudzbina_gen", table = "id_gen_narudzbina", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1, pkColumnValue = "narudzbina_gen")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "narudzbina_gen")
    private int id_narudzbina;
 
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_musterija")
	private Musterija musterija;
 
	@Column(name="ukupna_cena")
	private int ukupnaCena;
 
	@Column(name="ukupna_kolicina")
	private int ukupnaKolicina;
	
    @Column(name = "datum_narudzbine")
    private Date datum_narudzbine;
    
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="porudzbina_proizvod",joinColumns=
	@JoinColumn(name="porudzbina"),inverseJoinColumns=@JoinColumn(name="proizvod"))
    private List<Proizvod> izabraniProizvodi;
    
    public Narudzbina() {
 
    }
 
    public Narudzbina(Musterija musterija, List<Proizvod> lista, Date datum) {
        this.setMusterija(musterija);
        this.setIzabraniProizvodi(lista);
        this.setDatumNarudzbine(datum);
    }
 
	public void izbaciProizvod(Proizvod p){
		this.izabraniProizvodi.remove(p);
		this.ukupnaCena -= p.getCena();
	}
	
	public int getIdNarudzbina() {
		return id_narudzbina;
	}

	public void setIdNarudzbina(int id_narudzbina) {
		this.id_narudzbina = id_narudzbina;
	}

	public int getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(int ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	
	public int getUkupnaKolicina() {
		return ukupnaKolicina;
	}

	public void setUkupnaKolicina(int ukupnaKolicina) {
		this.ukupnaKolicina = ukupnaKolicina;
	}

	public List<Proizvod> getIzabraniProizvodi() {
		return izabraniProizvodi;
	}

	public void setIzabraniProizvodi(List<Proizvod> izabraniProizvodi) {
		this.izabraniProizvodi = izabraniProizvodi;
		this.ukupnaCena = 0;
		for(Proizvod p : this.izabraniProizvodi){
			this.ukupnaCena += p.getCena();
		}
	}

	public Musterija getMusterija() {
		return musterija;
	}

	public void setMusterija(Musterija musterija) {
		this.musterija = musterija;
	}
    
    public Date getDatumNarudzbine() {
        return datum_narudzbine;
    }
 
    public void setDatumNarudzbine(Date datum_narudzbine) {
        this.datum_narudzbine= datum_narudzbine;
    }
    
	
}
