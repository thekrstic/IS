package prodavnica_komponenti_projekat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;


public class TestCase {

	

    private static ProdavnicaServiceImpl service;
    public static Proizvod proizvod;
    public static Musterija musterija;
	
   @BeforeClass
   public static void initTest()
   {
	   service=new ProdavnicaServiceImpl();
	   System.out.println("BeforeClass: Uspesno inicijalizovan test");
   }
	
  @Before
   public void testPrecondition()
   {
	   Musterija m = service.pronadjiMusteriju(5);
	   assertNotNull(m);
	   System.out.println(m.getId()+" "+m.getIme()+" "+m.getPrezime()+" "+m.getAdresa()+" "+m.getTelefon());
	   System.out.println("Before: Preduslov");

   }
   
   @Test
   public void testAssertions() 
   {
	
	   musterija = service.kreirajMusterija("Markoni","Marko","Markovic","Nis", "018234345");
	   System.out.println("Test:izvrsen");

   }
   
   //postoji kreirani musterija
   @After
   public void testPostcondition()
   {
	   int broj = (int) (Math.random()/1000000);
	   proizvod = service.kreirajProizvod(String.valueOf(broj), "Napajanje", "Corsair TX650M", "C://slika.jpg", 9990, 20);
       assertNotNull(proizvod);
       System.out.println("After: post-uslov");
       System.out.println("ID:"+proizvod.getId()+","+"Serijski broj: "+proizvod.getSerijskiBroj()+", Tip: "+proizvod.getTipKomponente()+" Naziv: "+proizvod.getNaziv()+", Cena: "+proizvod.getCena()+"din, Kolicina: "+proizvod.getKolicina());
      
   }
   
   //Brisemo test podatke iz baze
   @AfterClass
   public static void clearTest()
   {
	   service.obrisiMusterija(musterija.getId());
	   service.obrisiProizvod(proizvod.getId());
	   musterija = service.pronadjiMusteriju(musterija.getId());
	   assertNull(musterija);
	   System.out.println("AfterClass:obrisani test podaci");

   }
   
}
