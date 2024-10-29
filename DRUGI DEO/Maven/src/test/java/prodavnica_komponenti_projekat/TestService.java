package prodavnica_komponenti_projekat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import prodavnica_komponenti_projekat.*;

import org.junit.After;
import org.junit.Test;

public class TestService {

	public void test1()
	{
		EntityManager em;
		EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("ProdavnicaPU");
        em = emf.createEntityManager();
		
        TypedQuery<Musterija> query = em.createQuery("SELECT e FROM Musterija e", Musterija.class);
        List<Musterija> musterije = query.getResultList();
        
		assertNotNull(musterije.indexOf(0));
		System.out.println("U bazi ima postojecih musterija");
	}
	public void test2()
	{
		//Da li vraca nesto funkcija koja lista aranzmane?
		ProdavnicaServiceImpl service=new ProdavnicaServiceImpl();
		   List <Proizvod> all=service.vratiSveProizvode();
	       assertNotNull(all);
	       System.out.println("U bazi ima unesenih proizvoda");
	}
	
	public void test3()
	{
		ProdavnicaServiceImpl service=new ProdavnicaServiceImpl();
	       Proizvod e1=service.pronadjiProizvod(13);
	       assertEquals(e1.getNaziv(),"Corsair TX650M");
	       System.out.println("Proizvod pronadjen u test3");
	}
	
	public void test4()
	{
		ProdavnicaServiceImpl service=new ProdavnicaServiceImpl();
		Musterija m = service.pronadjiMusteriju(1);
	       assertNull(m);
	       System.out.println("Pronadjen je trazeni musterija");
	}

	public void testDodavanjaNarudzbine()
	{
		 
	 /*      //Da li se updatuje dostupnost aranzmana
		AgencijaImpl service=new AgencijaImpl();
	       service.updateDostupnost(1, false);
	       Aranzman e1=service.FindAranzman(1);
	       assertEquals(e1.getDostupnost(), false);
	       System.out.println("Dostupnost aranzmana je uspesno izmenjena");
	*/
	}

	
   @Test
   public void testAssertions() 
   {
	   test1();
	   test2();
	   test3();
	   test4();
	   //test5();
	  
	   /*//Da li vraca nesto funkcija koja lista aranzmane?
	   AgencijaImpl service=new AgencijaImpl();
	   List <Aranzman> all=service.vratisvearanzmane();
       assertNotNull(all);
        
       //Da li se aranzman sa idjem1 zove dubai?
       Aranzman e1=service.FindAranzman(1);
       assertEquals(e1.getNaziv(),"Dubai");
    
       //da li se aranzman uspesno kreira
      //service.createAranzman("nikola", 100, true, null, 1, 0, false);
      // e1=service.FindAranzman("nikola");
      // assertEquals(e1.getNaziv(),"nikola");
       
       //da li se aranzman uspesno brise
      
       
       //Da li se placanje racuna uspesno izvrsava
       service.platiRacun(9);
       Racun e2=service.FindRacun(7);
       assertEquals(e2.getPlacen(),true);
       
       //Da li se updatuje dostupnost aranzmana
       service.updateDostupnost(1, false);
       e1=service.FindAranzman(1);
       assertEquals(e1.getDostupnost(), false);*/
       
       
       //Da li radi prikaz placenih racuna
       

       
   }
   @After
   public void testKraj()
       {
    	   System.out.println("Kraj testService!");
       }
	
}
