package prodavnica_komponenti_projekat;

import java.util.List;
import prodavnica_komponenti_projekat.*;

import prodavnica_komponenti_projekat.Main.Proizvod_kolicina;

public interface ProdavnicaService {

	public void proslediPorudzbinu(List<Proizvod_kolicina> listaProizvoda, String username);

    public void kreirajReklamaciju(Musterija musterija, Proizvod proizvod);
    public void obrisiReklamacija(int id);
	
    public void prikaziSveMusterije();
    public void prikaziSveProizvode();
    public List<Proizvod> vratiSveProizvode();
    public void prikaziSveProizvodeSaCenomVecomOd(int cena);
    public void prikaziSveMusterijeIzGrada(String adresa);
    
    public void obrisiSveProizvodeKojiNisuNaStanju();
    
    public Musterija kreirajMusterija(String username, String ime, String prezime, String adresa, String telefon); //+
    public void obrisiMusterija(int id); 
    public void stampajMusteriju(String username);
    public Musterija pronadjiMusteriju(int id);
    public Musterija pronadjiMusteriju(String username); //+
    public void obrisiMusterija(String username); //+
    
    public Proizvod pronadjiProizvod(int id);
    public void stampajProizvod(Proizvod p);
    public Proizvod pronadjiProizvod(String serijskiBroj);
    public void obrisiProizvod(int id);
    public void obrisiProizvod(String serijskiBroj);
    public Proizvod kreirajProizvod(String serijski_broj, String tip_komponente, String naziv, String slika, int cena, int kolicina);
	boolean smanjiKolicinaProizvoda(int id_proizvoda, int naruceno);

}
