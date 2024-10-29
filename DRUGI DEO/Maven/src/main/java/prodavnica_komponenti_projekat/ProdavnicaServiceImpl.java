package prodavnica_komponenti_projekat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import prodavnica_komponenti_projekat.Main.Proizvod_kolicina;

@Remote(ProdavnicaService.class)
@Stateless
public class ProdavnicaServiceImpl implements ProdavnicaService{

	@PersistenceContext(name = "ProdavnicaPU")
	private EntityManager em;
	public ProdavnicaServiceImpl()
	{	
		   EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProdavnicaPU");
	       em = emf.createEntityManager();
	}
	
	@Override
	public void proslediPorudzbinu(List<Proizvod_kolicina> listaProizvoda, String username) {
		int cena = 0, kolicina = 0;
    	Musterija musterija = null;
    	
		if(username != null)
			//musterija = em.createQuery("SELECT m FROM Musterija m WHERE m.ime='"+mu.getIme()+"' AND m.prezime='"+mu.getPrezime()+"' AND m.adresa='"+mu.getAdresa()+"' AND m.telefon = '"mu.getTelefon()"'", Musterija.class).getSingleResult();
			musterija = pronadjiMusteriju(username);
		else
			return;
    	
		if(musterija == null)
			return;
		
		if(listaProizvoda == null)
			return;
		
		List<Proizvod_kolicina> proizvodi_kolicine = new ArrayList<Proizvod_kolicina>();
		for(Proizvod_kolicina proizvod_kolicina : listaProizvoda){
			Proizvod pr = em.createQuery("SELECT p FROM Proizvod p WHERE p.id_proizvod="+proizvod_kolicina.proizvod.getId(),Proizvod.class).getSingleResult();
			if(pr != null){
		        Proizvod_kolicina pk = new Proizvod_kolicina();

				pk.proizvod = pr;
				pk.kolicina = proizvod_kolicina.kolicina;
				if(smanjiKolicinaProizvoda(pr.getId(), proizvod_kolicina.kolicina)) //Narucuju se proizvodi kojih ima na stanju
				{
					proizvodi_kolicine.add(pk);
					cena += pr.getCena();
					kolicina += proizvod_kolicina.kolicina;
				}
			}
		}
		
		if(cena != 0) {
		
			em.getTransaction().begin();

			Narudzbina p = new Narudzbina();
			p.setMusterija(musterija);
			p.setUkupnaCena(cena);
			p.setUkupnaKolicina(kolicina);
			p.setDatumNarudzbine(new Date());
			
			em.persist(p);
			
			for(Proizvod_kolicina proizvodi_kolicina : proizvodi_kolicine){		
					NarudzbinaProizvod pp = new NarudzbinaProizvod();
					pp.setIdPorudzbine(p.getIdNarudzbina());
					pp.setIdProizvoda(proizvodi_kolicina.proizvod.getId());
					pp.setKolicina(proizvodi_kolicina.kolicina);
					em.persist(pp);
			}
		
			em.getTransaction().commit();
		}
	}
	@Override
	public Musterija kreirajMusterija(String username, String ime, String prezime, String adresa, String telefon) {
	    em.getTransaction().begin();
	    Musterija musterija= new Musterija (username, ime, prezime, adresa, telefon);
	    em.persist(musterija);
	    em.getTransaction().commit();	
	    return musterija;
	}
	
	@Override
	public boolean smanjiKolicinaProizvoda(int id_proizvoda, int naruceno) {
		 Proizvod p1 = pronadjiProizvod(id_proizvoda);
		    if(p1 != null)
		    {
		    	stampajProizvod(p1);
		    	int novaKolicina = p1.getKolicina() - naruceno;
			    if(novaKolicina >= 0)
			    {	
			    	
			        em.getTransaction().begin();
			        p1.setKolicina(novaKolicina);
			        em.getTransaction().commit();
			    	return true;
			    }
			    else
			    	return false;
		    }
		    else
		    	return false;
	}

	public void stampajProizvod(Proizvod p) {
		System.out.println("ID: "+ p.getId()+" Serijski broj: "+p.getSerijskiBroj()+ " Tip komponente: "+p.getTipKomponente()+ " Naziv: "+p.getNaziv()+" Cena:"+ p.getCena()+" Kolicina: "+p.getKolicina());		
	}

	@Override
	public void obrisiMusterija(int id) {
    	Musterija musterija = em.find(Musterija.class, id);
    	em.getTransaction().begin();
    	em.remove(musterija);
    	em.getTransaction().commit();
		
	}
	

	@Override
	public void obrisiMusterija(String username) {
		Musterija m = em.createQuery("SELECT m FROM Musterija m WHERE m.username='"+username+"'",Musterija.class).getSingleResult(); // '"+adresa+"'"
		
    	em.getTransaction().begin();
    	em.remove(m);
    	em.getTransaction().commit();		
	}
		
	@Override
	public Proizvod kreirajProizvod(String serijski_broj, String tip_komponente, String naziv, String slika, int cena, int kolicina) {
	        em.getTransaction().begin();
	        Proizvod proizvod = new Proizvod (serijski_broj, tip_komponente, naziv, slika, cena, kolicina);
	        em.persist(proizvod);
	        em.getTransaction().commit();
	        return proizvod;
	}

	@Override
	public void obrisiProizvod(int id) {
    	Proizvod proizvod = em.find(Proizvod.class, id);
    	em.getTransaction().begin();
    	em.remove(proizvod);
    	em.getTransaction().commit();	
	}

	@Override
	public void obrisiProizvod(String serijskiBroj) {
				Proizvod pr = em.createQuery("SELECT p FROM Proizvod p WHERE p.serijski_broj='"+serijskiBroj+"'",Proizvod.class).getSingleResult(); // '"+adresa+"'"
				
		    	em.getTransaction().begin();
		    	em.remove(pr);
		    	em.getTransaction().commit();		
	}

	@Override
	public void kreirajReklamaciju(Musterija musterija, Proizvod proizvod) {
        em.getTransaction().begin();
        Reklamacija reklamacija = new Reklamacija (musterija.getId(), proizvod.getId(), new Date());
        em.persist(reklamacija);
        em.getTransaction().commit();
		
	}

	@Override
	public void obrisiReklamacija(int id) {
    	Reklamacija reklamacija = em.find(Reklamacija.class, id);
    	em.getTransaction().begin();
    	em.remove(reklamacija);
    	em.getTransaction().commit();	
	}

	@Override
	public void prikaziSveProizvode() {
        TypedQuery<Proizvod> query = em.createQuery("SELECT e FROM Proizvod e", Proizvod.class);
        List<Proizvod> proizvodi = query.getResultList();
        System.out.println("Lista svih proizvoda:");
       for (Proizvod e1: proizvodi) 
            System.out.println("ID:"+e1.getId()+", "+"Serijski broj: "+e1.getSerijskiBroj()+", "+"Tip komponente: "+e1.getTipKomponente()+", "+"Naziv: "+e1.getNaziv()+", "+"Cena: "+e1.getCena()+", "+"Kolicina: "+ e1.getKolicina());       
	}

	@Override
	public void prikaziSveProizvodeSaCenomVecomOd(int cena) {
	    TypedQuery<Proizvod> query = em.createQuery("SELECT e FROM Proizvod e WHERE e.cena >"+cena, Proizvod.class);
	    List<Proizvod> rezultat = query.getResultList();
	    System.out.println("Proizvodi sa cenom vecom od:"+cena);
	   
	    for (Proizvod e1: rezultat) {
	    	
	        System.out.println("ID:"+e1.getId()+", "+"Serijski broj: "+e1.getSerijskiBroj()+", "+"Tip komponente: "+e1.getTipKomponente()+", "+"Naziv: "+e1.getNaziv()+", "+"Cena: "+e1.getCena()+", "+"Kolicina: "+ e1.getKolicina());
	       } 
		
	}
	
	@Override
	public void prikaziSveMusterijeIzGrada(String adresa) {
	    TypedQuery<Musterija> query = em.createQuery("SELECT e FROM Musterija e WHERE e.adresa = '"+adresa+"'", Musterija.class);
	    List<Musterija> rezultat = query.getResultList();
	    System.out.println("Sve musterije iz grada: "+adresa);
	   
	    for (Musterija e1: rezultat) {
	    	
	        System.out.println("ID:"+e1.getId()+", "+"Ime: "+e1.getIme()+", "+"Prezime: "+e1.getPrezime()+", "+"Mesto: "+e1.getAdresa()+", "+"Telefon: "+e1.getTelefon());
	       }
		
	}
	
	@Override
	public void obrisiSveProizvodeKojiNisuNaStanju() {
    	TypedQuery<Proizvod> query = em.createQuery("SELECT e FROM Proizvod e WHERE e.kolicina = 0", Proizvod.class);
    	List<Proizvod> lista = query.getResultList();
    	System.out.println("Brisanje svih proizvoda sa kolicinom 0!");
    	for(Proizvod pr: lista)
    	{
    		em.getTransaction().begin();
    		em.remove(pr);
    		em.getTransaction().commit();  		
    	}
		
	}

	@Override
	public void stampajMusteriju(String username) {
	    TypedQuery<Musterija> query = em.createQuery("SELECT e FROM Musterija e WHERE e.username='"+username+"'", Musterija.class);
	    List<Musterija> rezultat = query.getResultList();
    	Musterija musterija = em.find(Musterija.class, username);

	    System.out.println("MusterIja sa imenom ime:"+musterija.getIme()+" i prezimenom: "+musterija.getPrezime());
	   
	    for (Musterija e1: rezultat) {
	           System.out.println("ID:"+e1.getId()+", "+"Username: "+e1.getUserame()+", "+"Ime: "+e1.getIme()+", Prezime: "+e1.getPrezime()+", Adresa:"+e1.getAdresa()+", Telefon: "+e1.getTelefon());
	       }		
	}

	@Override
	public Musterija pronadjiMusteriju(int id) {
    	Musterija musterija = em.find(Musterija.class, id);
    	return musterija;
	}

	@Override
	public Musterija pronadjiMusteriju(String username) {
		Musterija m = em.createQuery("SELECT m FROM Musterija m WHERE m.username='"+username+"'",Musterija.class).getSingleResult(); // '"+adresa+"'"
		
		if(m != null)
			return m;
		else return null;
	}
	
	@Override
	public Proizvod pronadjiProizvod(int id) {
    	Proizvod proizvod = em.find(Proizvod.class, id);
    	return proizvod;
	}

	@Override
	public Proizvod pronadjiProizvod(String serijskiBroj) {
		try {
	    TypedQuery<Proizvod> query = em.createQuery("SELECT e FROM Proizvod e WHERE e.serijski_broj='"+serijskiBroj+"'", Proizvod.class);
	    Proizvod rezultat = query.getSingleResult();
	    if(rezultat != null)
	    	return rezultat;
	    return null;
		}
		catch(NoResultException exc) {
			System.out.println(exc);
			return null;
		}
	}

	@Override
	public List<Proizvod> vratiSveProizvode() {
		TypedQuery<Proizvod> query = em.createQuery("SELECT e FROM Proizvod e", Proizvod.class);
        List<Proizvod> proizvodi = query.getResultList();
        return proizvodi;
	}

	@Override
	public void prikaziSveMusterije() {
	       TypedQuery<Musterija> query = em.createQuery("SELECT e FROM Musterija e", Musterija.class);
	        List<Musterija> musterije = query.getResultList();
	        System.out.println("Lista svih proizvoda:");
	       for (Musterija e1: musterije) 
	           System.out.println("ID:"+e1.getId()+", "+"Username: "+e1.getUserame()+", "+"Ime: "+e1.getIme()+", Prezime: "+e1.getPrezime()+", Adresa:"+e1.getAdresa()+", Telefon: "+e1.getTelefon());
	}

}
