package prodavnica_komponenti_projekat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {
    private static EntityManager em;
 
    public static void main(String[] args) {
 
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("ProdavnicaPU");
        em = emf.createEntityManager();
  
//Dodavanje musterija
        // kreirajMusterija("Stanko98", "Stanko", "Trajkovic", "Novi Sad", "018605434");
       //  prikaziSveMusterije();
//Dodavanje proizvoda
      //   kreirajProizvod("0ADSXX4","CPU", "FX 8950h","C://SLIKA.JPG", 900, 0); //Obavezno promeniti prvi parametar, jer je unique
       //  kreirajProizvod("0AIUYS4","CPU", "I7 9750H","C://SLIKA.JPG", 1500, 0);
        // prikaziSveProizvode();
//Vracanje objekata        
       // Musterija m = pronadjiMusteriju(10); //x - int promenljiva
      //  System.out.println(m.getIme());
    //    Proizvod p = pronadjiProizvod("3245DFG0");
     //   System.out.println(p.getNaziv());
        
//Prikazi
        // prikaziSveProizvode();
        // prikaziSveProizvodeSaCenomVecomOd(1000);
      //   prikaziSveMusterijeIzGrada("Novi Sad");
       //  stampajMusteriju("Filip","Trajkovic");
        
//Brisanje
       //  obrisiSveProizvodeKojiNisuNaStanju();

//Rad sa reklamacijama
        /*
        Musterija musterija =pronadjiMusteriju(29);
        Proizvod pr = pronadjiProizvod(2);
        kreirajReklamaciju(musterija, pr);
		*/
      //  obrisiReklamacija(3);
      
//Kreiranje narudzbine
        

        /*
        Musterija m = pronadjiMusteriju(10);
        List<Proizvod_kolicina> lista = new ArrayList<Proizvod_kolicina>();
        Proizvod_kolicina pk = new Proizvod_kolicina();
        Proizvod_kolicina pa = new Proizvod_kolicina();

        pk.proizvod = pronadjiProizvod(9);
        pk.kolicina = 10;
        lista.add(pk);

        pa.proizvod = pronadjiProizvod(13);
        pa.kolicina = 10;
        lista.add(pa);
        
        for(Proizvod_kolicina pr: lista)
        System.out.println(pr.kolicina +" "+ pr.proizvod.getId());
      
        	proslediPorudzbinu(lista, m);
 */
        
      //  obrisiProizvod("aaa");
      //  obrisiMusterija("cofi");
      //  Musterija m = pronadjiMusteriju("stanko");
      //  stampajMusteriju(m.getIme(), m.getPrezime());
       /*
        prikaziSveMusterije();
        
        Musterija m = pronadjiMusteriju("asdfXj");
        if(m != null)
        	stampajMusteriju(m.getIme(), m.getPrezime());
        else
        	System.out.println("Nije pronadjen stanko");
       */
        
    //   Proizvod p = pronadjiProizvod("0ADS4");
     //  stampajProizvod(p); 
       
       em.close();
        
        //USELESS
        /*
        String conn="jdbc:mysql://localhost:3306/prodavnica_komponenti_projekat";
        String username="root";
        String password="root";
        */
    }
    
    static class Proizvod_kolicina
    {
        public Proizvod proizvod; 
        public int kolicina;  
        Proizvod_kolicina(){}
     };
     
 	public static Proizvod pronadjiProizvod(String serijskiBroj) {
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
 	public static  Musterija pronadjiMusteriju(String username) {
		try {
			Musterija m = em.createQuery("SELECT m FROM Musterija m WHERE m.username='"+username+"'",Musterija.class).getSingleResult();
			System.out.println(m.getIme());
			if(m != null)
				return m;
			else return null;
		}
		catch(NoResultException exc) {
			return null;
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return null;
		}
 	}
 	public static void prikaziSveMusterije() {
	       TypedQuery<Musterija> query = em.createQuery("SELECT e FROM Musterija e", Musterija.class);
	        List<Musterija> musterije = query.getResultList();
	        System.out.println("Lista svih proizvoda:");
	       for (Musterija e1: musterije) 
	           System.out.println("ID:"+e1.getId()+", "+"Username: "+e1.getUserame()+", "+"Ime: "+e1.getIme()+", Prezime: "+e1.getPrezime()+", Adresa:"+e1.getAdresa()+", Telefon: "+e1.getTelefon());
	}
     
     
    public static void proslediPorudzbinu(List<Proizvod_kolicina> listaProizvoda, Musterija mu) {
    	int cena = 0, kolicina = 0;
    	Musterija musterija = null;
    	
		if(mu != null)
			//musterija = em.createQuery("SELECT m FROM Musterija m WHERE m.ime='"+mu.getIme()+"' AND m.prezime='"+mu.getPrezime()+"' AND m.adresa='"+mu.getAdresa()+"' AND m.telefon = '"mu.getTelefon()"'", Musterija.class).getSingleResult();
			musterija = pronadjiMusteriju(mu.getId());
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
    
	public static boolean smanjiKolicinaProizvoda(int id_proizvoda, int naruceno) {
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
	
	private static void stampajProizvod(Proizvod p) {
		System.out.println("ID: "+ p.getId()+" Serijski broj: "+p.getSerijskiBroj()+ " Tip komponente: "+p.getTipKomponente()+ " Naziv: "+p.getNaziv()+" Cena:"+ p.getCena()+" Kolicina: "+p.getKolicina());
	}
    
    private static Musterija kreirajMusterija(String username, String ime,
            String prezime, String adresa, String telefon) {
        em.getTransaction().begin();
        Musterija musterija= new Musterija (username, ime, prezime, adresa, telefon);
        em.persist(musterija);
        em.getTransaction().commit();
        return musterija;
    }
    
    private static void obrisiMusterija(int id) {
    	Musterija musterija = em.find(Musterija.class, id);
    	em.getTransaction().begin();
    	em.remove(musterija);
    	em.getTransaction().commit();
    }
    
	public static void obrisiMusterija(String username) {
		Musterija m = em.createQuery("SELECT m FROM Musterija m WHERE m.username='"+username+"'",Musterija.class).getSingleResult(); // '"+adresa+"'"
		
    	em.getTransaction().begin();
    	em.remove(m);
    	em.getTransaction().commit();		
	}
    
    private static Proizvod kreirajProizvod(String serijski_broj, String tip_komponente,
            String naziv, String slika, int cena, int kolicina) {
        em.getTransaction().begin();
        Proizvod proizvod = new Proizvod (serijski_broj, tip_komponente, naziv, slika, cena, kolicina);
        em.persist(proizvod);
        em.getTransaction().commit();
        return proizvod;
    }
    
    private static void obrisiProizvod(int id) {
    	Proizvod proizvod = em.find(Proizvod.class, id);
    	em.getTransaction().begin();
    	em.remove(proizvod);
    	em.getTransaction().commit();
    }
    
	public static void obrisiProizvod(String serijskiBroj) {
    //	Proizvod proizvod = em.find(Proizvod.class, serijskiBroj);
		Proizvod pr = em.createQuery("SELECT p FROM Proizvod p WHERE p.serijski_broj='"+serijskiBroj+"'",Proizvod.class).getSingleResult(); // '"+adresa+"'"
		
    	em.getTransaction().begin();
    	em.remove(pr);
    	em.getTransaction().commit();		
	}
    
    private static void kreirajReklamaciju(Musterija musterija,
            Proizvod proizvod) {
        em.getTransaction().begin();
        Reklamacija reklamacija = new Reklamacija (musterija.getId(), proizvod.getId(), new Date());
        em.persist(reklamacija);
        em.getTransaction().commit();
    }
    
    private static void obrisiReklamacija(int id) {
    	Reklamacija reklamacija = em.find(Reklamacija.class, id);
    	em.getTransaction().begin();
    	em.remove(reklamacija);
    	em.getTransaction().commit();
    }
    
    private static void prikaziSveProizvode()
    {
      
        TypedQuery<Proizvod> query = em.createQuery("SELECT e FROM Proizvod e", Proizvod.class);
        List<Proizvod> proizvodi = query.getResultList();
        System.out.println("Lista svih proizvoda:");
       for (Proizvod e1: proizvodi) {
            System.out.println("ID:"+e1.getId()+", "+"Serijski broj: "+e1.getSerijskiBroj()+", "+"Tip komponente: "+e1.getTipKomponente()+", "+"Naziv: "+e1.getNaziv()+", "+"Cena: "+e1.getCena()+", "+"Kolicina: "+ e1.getKolicina());
            
       }     
    }
    
    private static void prikaziSveProizvodeSaCenomVecomOd(int cena)
    {
          
    TypedQuery<Proizvod> query = em.createQuery("SELECT e FROM Proizvod e WHERE e.cena >"+cena, Proizvod.class);
    List<Proizvod> rezultat = query.getResultList();
    System.out.println("Proizvodi sa cenom vecom od:"+cena);
   
    for (Proizvod e1: rezultat) {
    	
        System.out.println("ID:"+e1.getId()+", "+"Serijski broj: "+e1.getSerijskiBroj()+", "+"Tip komponente: "+e1.getTipKomponente()+", "+"Naziv: "+e1.getNaziv()+", "+"Cena: "+e1.getCena()+", "+"Kolicina: "+ e1.getKolicina());
       } 
    }
    
    private static void prikaziSveMusterijeIzGrada(String adresa)
    {
          
    TypedQuery<Musterija> query = em.createQuery("SELECT e FROM Musterija e WHERE e.adresa = '"+adresa+"'", Musterija.class);
    List<Musterija> rezultat = query.getResultList();
    System.out.println("Sve musterije iz grada: "+adresa);
   
    for (Musterija e1: rezultat) {
    	
        System.out.println("ID:"+e1.getId()+", "+"Ime: "+e1.getIme()+", "+"Prezime: "+e1.getPrezime()+", "+"Mesto: "+e1.getAdresa()+", "+"Telefon: "+e1.getTelefon());
       } 
    }
    
    private static void obrisiSveProizvodeKojiNisuNaStanju() {
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
    
    private static void stampajMusteriju(String ime, String prezime)
    {
          
    TypedQuery<Musterija> query = em.createQuery("SELECT e FROM Musterija e WHERE e.ime='"+ime+"'and e.prezime='"+prezime+"'", Musterija.class);
    List<Musterija> rezultat = query.getResultList();
    System.out.println("MusterIja sa imenom ime:"+ime+" i prezimenom: "+prezime);
   
    for (Musterija e1: rezultat) {
           System.out.println("ID:"+e1.getId()+", "+"Username: "+e1.getUserame()+" Ime: "+e1.getIme()+", Prezime: "+e1.getPrezime()+", Adresa:"+e1.getAdresa()+", Telefon: "+e1.getTelefon());
       } 
    }
    
    private static Musterija pronadjiMusteriju(int id)
    {
          
    	Musterija musterija = em.find(Musterija.class, id);
    	return musterija;
    }
    
    private static Proizvod pronadjiProizvod(int id)
    {
          
    	Proizvod proizvod = em.find(Proizvod.class, id);
    	return proizvod;
    }
    
    private static void pronadjiProizvod(String ime, String prezime)
    {
          
    TypedQuery<Musterija> query = em.createQuery("SELECT e FROM Musterija e WHERE e.ime='"+ime+"'and e.prezime='"+prezime+"'", Musterija.class);
    List<Musterija> rezultat = query.getResultList();
    System.out.println("Musteirja sa imenom ime:"+ime+" i prezimenom: "+prezime);
   
    for (Musterija e1: rezultat) {
           System.out.println("ID:"+e1.getId()+", "+"Ime: "+e1.getIme()+", Prezime: "+e1.getPrezime()+", Adresa:"+e1.getAdresa()+", Telefon: "+e1.getTelefon());
       } 
    }
 /*
     
    
    private static void updateEmployeeSalary(int id, int plata) {
       Employee employee = em.find(Employee.class, id);
       em.getTransaction().begin();
       employee.setPlata(plata);
       em.getTransaction().commit();
    }

    static void prikazFilterPlata(String conn, String username, String password, int granica)
    {
          ArrayList<Employee> lz=filterPlata(conn, username, password,granica);
         for(int i=0;i<lz.size();i++)
         {
             System.out.println(lz.get(i).getId());
         }
         
    }
    
    
    private static ArrayList<Employee> filterPlata(String connection, String username, String password, int granica)
    {
    	ArrayList<Employee> zaposleni_kolekcija=new ArrayList<Employee>();
          
    	try {
		java.sql.Connection conn = DriverManager.getConnection(connection, username, password);
    	PreparedStatement st = conn.prepareStatement("select * from zaposleni where plata>?");
    	
        st.setInt(1, granica);                  
        st.executeQuery();
        
    	ResultSet rs = st.getResultSet();
    	    while (rs.next()) {
    	                Employee e1 = new Employee(rs.getInt("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("odsek"), rs.getInt("plata"));
    	                zaposleni_kolekcija.add(e1);	
    	    }       
    	} 		
    	catch(Exception e) {
			System.out.println("Greska pristipa bazi");
    	}	

        return zaposleni_kolekcija;
         
    }

    */

}

