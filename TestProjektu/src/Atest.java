import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Atest 
{
	
	public class Main 
	{
		public static void main(String[] args) 
	    {
	        Scanner skener = new Scanner(System.in) ;
	        DKTest Datab = new DKTest() ;
	        
	        int Voľba ;	
	        boolean Chod = true ;
	        
	        Datab.vytvoritTabulky() ;
	        Datab.nacitanieTabuliek() ;
	       	        
	        System.out.println("Vitajte v aplikácií na manažovanie kníh") ;
	        Menu.totoMenu() ;
	        while (Chod)
			{
	        	try {
	                Voľba = skener.nextInt() ;
	                skener.nextLine() ;
					switch(Voľba)
					{
					case 1:
						System.out.println("Vyberte typ knihy ktorú chcete pridať (Roman/Ucebnica , roman/ucebnica)") ;
						String typKnihy = skener.nextLine().toLowerCase() ;
					    
						if (typKnihy.equalsIgnoreCase("Roman") || typKnihy.equalsIgnoreCase("roman")) 
						{
						    System.out.println("Zadajte názov knihy:") ;
						    String nazovR = skener.nextLine() ;
						    System.out.println("Zadajte autora knihy:") ;
						    String autorR = skener.nextLine();
						    System.out.println("Zadajte rok vydania knihy:") ;
						    int rokR = ibaCeléČísla(skener) ;
						    String stavR = "" ;
						    System.out.println("Zadajte stav dostupnosti knihy (Dostupna/Nedostupna):") ;
						    while (true) 
						    {
						    	skener.nextLine() ;
						    	String stavR1 = skener.nextLine() ;
						    	if (stavR1.equalsIgnoreCase("Dostupna") || stavR1.equalsIgnoreCase("Nedostupna") || stavR1.equalsIgnoreCase("dostupna") || stavR1.equalsIgnoreCase("nedostupna")) 
						    	{
						    		stavR = stavR1 ;
						    		break ;
						    	} 
						    	else 
						    	{
						    		System.out.println("Prosím vyberte Dostupna alebo Nedostupna.") ;
						    	}
						    }
                    
						    String zanerR = "" ;
						    while (true) 
						    {
						    	System.out.println("Zadajte žáner knihy (Fantazia, Romantika, Sci-fi, Klasika, Horor):") ;
						    	String zanerR1 = skener.nextLine() ;
						    	if (zanerR1.equalsIgnoreCase("Fantazia") || zanerR1.equalsIgnoreCase("Romantika") || zanerR1.equalsIgnoreCase("Sci-fi") || zanerR1.equalsIgnoreCase("Klasika") || zanerR1.equalsIgnoreCase("Horor")
						    	||	zanerR1.equalsIgnoreCase("fantazia") || zanerR1.equalsIgnoreCase("romantika") || zanerR1.equalsIgnoreCase("sci-fi") || zanerR1.equalsIgnoreCase("klasika") || zanerR1.equalsIgnoreCase("horor"))
						    	{
						    		zanerR = zanerR1 ;
						    		break ;
						    	} 
						    	else 
						    	{
						    		System.out.println("Prosím vyberte jednu z možností: Fantazia, Romantika, Sci-fi, Klasika, Horor.") ;
						    	}
						    }
						    Datab.addBookR(nazovR, new RTest(nazovR, autorR, rokR, stavR, zanerR)) ;
						    System.out.println("Kniha bola úspešne pridaná do databázy") ;
						    
						} 
						else if (typKnihy.equalsIgnoreCase("Ucebnica") || typKnihy.equalsIgnoreCase("ucebnica")) 
						{						  
						    System.out.println("Zadajte názov knihy:") ;
						    String nazovU = skener.nextLine() ;
						    System.out.println("Zadajte autora knihy:") ;
						    String autorU = skener.nextLine() ;
						    System.out.println("Zadajte rok vydania knihy:") ;
						    int rokU = Integer.parseInt(skener.nextLine()) ;
						    String stavU = "" ;
						    while (true) 
						    {
						    	skener.nextLine() ;
						    	System.out.println("Zadajte stav dostupnosti knihy (Dostupna/Nedostupna):") ;
						    	String stavU1 = skener.nextLine() ;
						    	if (stavU1.equalsIgnoreCase("Dostupna") || stavU1.equalsIgnoreCase("Nedostupna") || stavU1.equalsIgnoreCase("dostupna") || stavU1.equalsIgnoreCase("nedostupna")) 
						    	{
						    		stavU = stavU1 ;
						    		break ;
						    	} 
						    	else 
						    	{
						    		System.out.println("Prosím vyberte Dostupna alebo Nedostupna.") ;
						    	}
						    }
						    String vhodnostU = "" ;
						    while (true) 
						    {
						    	System.out.println("Zadajte stav vhodnosti knihy (Deti/Tinedzeri/Dospely):") ;
						    	String vhodnostU1 = skener.nextLine() ;
						    	if (vhodnostU1.equalsIgnoreCase("deti") || vhodnostU1.equalsIgnoreCase("tinedzeri") || vhodnostU1.equalsIgnoreCase("dospely")
						    	||	vhodnostU1.equalsIgnoreCase("Deti") || vhodnostU1.equalsIgnoreCase("Tinedzeri") || vhodnostU1.equalsIgnoreCase("Dospely")) 
						    	{
						    		vhodnostU = vhodnostU1 ;
						    		break ;
						    	} 
						    	else 
						    	{
						    		System.out.println("Prosím vyberte (Deti/Tinedzeri/Dospely).") ;
						    	}
						    }
						    Datab.addBookU(nazovU, new UTest(nazovU, autorU, rokU, stavU, vhodnostU)) ;
						    System.out.println("Kniha bola úspešne pridaná do databázy") ;
						} 
						else 
						{
						    System.out.println("Prosím, zadajte (Roman/Ucebnica , roman/ucebnica).") ;
						}

					break ;
					case 2:
						System.out.println("Zadajte názov knihy, ktorú chcete upraviť:") ;
					    String nazovKnihy = skener.nextLine() ;
					    RTest knihaNaZmenuR = Datab.getBookR(nazovKnihy) ;
					    UTest knihaNaZmenuU = Datab.getBookU(nazovKnihy) ;
					    if (knihaNaZmenuR != null || knihaNaZmenuU != null) 
					    {
					        if (knihaNaZmenuR != null) {
					            System.out.println("Nájdená kniha: " + knihaNaZmenuR.getNazov()) ;
					        } 
					        else 
					        {
					            System.out.println("Nájdená kniha: " + knihaNaZmenuU.getNazov()) ;
					        }
					        System.out.println("Vyberte, ktorý parameter chcete upraviť:") ;
					        System.out.println("1 .. Názov") ;
					        System.out.println("2 .. Autor") ;
					        System.out.println("3 .. Rok vydania") ;
					        System.out.println("4 .. Stav dostupnosti") ;
					        System.out.println("5 .. Žáner (Iba pre romány)") ;
					        System.out.println("6 .. Vhodnosť (Iba pre učebnice)") ;
					        int parameterChoice = skener.nextInt() ;
					        skener.nextLine() ;
					        
					        switch (parameterChoice) 
					        {
					        case 1:
					            System.out.println("Zadajte nový názov knihy:") ;
					            String newNazov = skener.nextLine() ;
					            if (knihaNaZmenuR instanceof RTest) 
					            {
					                ((RTest) knihaNaZmenuR).setNazov(newNazov) ;
					            } 
					            else if (knihaNaZmenuU instanceof UTest) 
					            {
					                ((UTest) knihaNaZmenuU).setNazov(newNazov) ;
					            }
					            System.out.println("Názov knihy bol úspešne zmenený.") ;
					        break ;
					        case 2:
					            System.out.println("Zadajte nového autora knihy:") ;
					            String newAutor = skener.nextLine() ;
					            if (knihaNaZmenuR instanceof RTest) 
					            {
					                ((RTest) knihaNaZmenuR).setAutori(newAutor) ;
					            } 
					            else if (knihaNaZmenuU instanceof UTest) 
					            {
					                ((UTest) knihaNaZmenuU).setAutori(newAutor) ;
					            }
					            System.out.println("Autor knihy bol úspešne zmenený.") ;
					        break ;
					        case 3:
					            System.out.println("Zadajte nový rok vydania knihy:") ;
					            int newRok = Integer.parseInt(skener.nextLine()) ;
					            if (knihaNaZmenuR instanceof RTest) 
					            {
					                ((RTest) knihaNaZmenuR).setRokVydania(newRok) ;
					            } 
					            else 
					            {
					                ((UTest) knihaNaZmenuU).setRokVydania(newRok) ;
					            }
					            System.out.println("Rok vydania knihy bol úspešne zmenený.") ;
					        break ;
					        case 4:
					            System.out.println("Zadajte nový stav dostupnosti knihy (Dostupna/Nedostupna):") ;
					            String newStav = skener.nextLine() ;
					            if (knihaNaZmenuR instanceof RTest) 
					            {
					                ((RTest) knihaNaZmenuR).setStavDostupnosti(newStav) ;
					            } 
					            else 
					            {
					                ((UTest) knihaNaZmenuU).setStavDostupnosti(newStav) ;
					            }
					            System.out.println("Stav dostupnosti knihy bol úspešne zmenený.") ;
					        break ;
					        case 5:
					            if (knihaNaZmenuR instanceof RTest) 
					            {
					                System.out.println("Zadajte nový žáner knihy (Fantazia, Romantika, Sci-fi, Klasika, Horor):") ;
					                String newZaner = skener.nextLine() ;
					                ((RTest) knihaNaZmenuR).setZaner(newZaner) ;
					                System.out.println("Žáner knihy bol úspešne zmenený.") ;
					            } 
					            else 
					            {
					                System.out.println("Táto kniha nie je román, nemôžete zmeniť žáner.") ;
					            }
					        break ;
					        case 6:
					            if (knihaNaZmenuU instanceof UTest) 
					            {
					                System.out.println("Zadajte novú vhodnosť knihy (Deti/Tinedzeri/Dospely):") ;
					                String newVhodnost = skener.nextLine() ;
					                ((UTest) knihaNaZmenuU).setVhodnost(newVhodnost) ;
					                System.out.println("Vhodnosť knihy bola úspešne zmenená.") ;
					            } 
					            else 
					            {
					                System.out.println("Táto kniha nie je učebnica, nemôžete zmeniť vhodnosť.") ;
					            }
					            break ;
					        default:
					            System.out.println("Neplatná voľba.") ;
					        break ;
					        }
					    }
					    else 
					    {
					        System.out.println("Kniha s daným názvom nebola nájdená.") ;
					    }
					break ;

					case 3:
					    System.out.println("Zadajte názov knihy, ktorú chcete vymazať:") ;
					    String nazovKnihyNaVymazanie = skener.nextLine() ;
					    RTest knihaNaVymazanieR = Datab.getBookR(nazovKnihyNaVymazanie) ;
					    UTest knihaNaVymazanieU = Datab.getBookU(nazovKnihyNaVymazanie) ;
					    if (knihaNaVymazanieR != null || knihaNaVymazanieU != null) 
					    {
					        if (knihaNaVymazanieR != null) 
					        {
					            Datab.removeBookR(nazovKnihyNaVymazanie) ;
					            System.out.println("Kniha " + nazovKnihyNaVymazanie + " bola úspešne vymazaná.") ;
					        } 
					        else 
					        {
					            Datab.removeBookU(nazovKnihyNaVymazanie) ;
					            System.out.println("Kniha " + nazovKnihyNaVymazanie + " bola úspešne vymazaná.") ;
					        }
					    } 
					    else 
					    {
					        System.out.println("Kniha s daným názvom nebola nájdená.") ;
					    }
					    
					break ;
					case 4:
					    System.out.println("Zadajte názov knihy, ktorú chcete označiť (Dostupna/Nedostupna):") ;
					    String nazovKnihyNaAktualizáciu = skener.nextLine() ;
					    RTest knihaNaAktualizáciuR = Datab.getBookR(nazovKnihyNaAktualizáciu) ;
					    UTest knihaNaAktualizáciuU = Datab.getBookU(nazovKnihyNaAktualizáciu) ;
					    if (knihaNaAktualizáciuR != null || knihaNaAktualizáciuU != null) 
					    {
					        System.out.println("Zadajte stav knihy (Dostupna/Nedostupna):") ;
					        String novyStav = skener.nextLine() ;
					        if (knihaNaAktualizáciuR != null) 
					        {
					            knihaNaAktualizáciuR.setStavDostupnosti(novyStav) ;
					            System.out.println("Stav knihy " + nazovKnihyNaAktualizáciu + " bol úspešne aktualizovaný.") ;
					        } 
					        else 
					        {
					            knihaNaAktualizáciuU.setStavDostupnosti(novyStav) ;
					            System.out.println("Stav knihy " + nazovKnihyNaAktualizáciu + " bol úspešne aktualizovaný.") ;
					        }
					    } 
					    else 
					    {
					        System.out.println("Kniha s daným názvom nebola nájdená.") ;
					    }
					    
					break ;
					case 5:
					    ArrayList<RTest> vsetkyRKnihy = (ArrayList<RTest>) Datab.getAllBooksR() ;
					    ArrayList<UTest> vsetkyUKnihy = (ArrayList<UTest>) Datab.getAllBooksU() ;

					    Collections.sort(vsetkyRKnihy, Comparator.comparing(RTest::getNazov)) ;
					    Collections.sort(vsetkyUKnihy, Comparator.comparing(UTest::getNazov)) ;

					    if (vsetkyRKnihy.isEmpty() && vsetkyUKnihy.isEmpty()) 
					    {
					        System.out.println("V databázi nie sú k dispozicií žiadne knihy. Pridajte knihu nasjkôr.") ;
					    } 
					    else 
					    {
					        System.out.println("Výpis kníh v abecednom poradí:") ;
					        for (RTest rBook : vsetkyRKnihy) 
					        {
					            System.out.println("Názov: " + rBook.getNazov()) ;
					            System.out.println("Autori: " + rBook.getAutori()) ;
					            System.out.println("Žáner: " + rBook.getZaner()) ;
					            System.out.println("Rok vydania: " + rBook.getRokVydania()) ;
					            System.out.println("Stav dostupnosti: " + rBook.getStavDostupnosti()) ;
					            System.out.println() ;
					        }
					        for (UTest uBook : vsetkyUKnihy) 
					        {
					            System.out.println("Názov: " + uBook.getNazov()) ;
					            System.out.println("Autori: " + uBook.getAutori()) ;
					            System.out.println("Ročník: " + uBook.getVhodnost()) ;
					            System.out.println("Rok vydania: " + uBook.getRokVydania()) ;
					            System.out.println("Stav dostupnosti: " + uBook.getStavDostupnosti()) ;
					            System.out.println() ;
					        }
					    }
					    
					break ;
					case 6:
					    ArrayList<RTest> allRBooks = (ArrayList<RTest>) Datab.getAllBooksR() ;
					    ArrayList<UTest> allUBooks = (ArrayList<UTest>) Datab.getAllBooksU() ;

					    if (allRBooks.isEmpty() && allUBooks.isEmpty()) 
					    {
					        System.out.println("V databázi nie sú k dispozícii žiadne knihy. Prosím, najprv pridajte knihu.") ;
					        break ;
					    }

					    System.out.println("Zadajte názov knihy, ktorú chcete vyhľadať:") ;
					    String najstMenoKnihy = skener.nextLine() ;

					    if (!allRBooks.isEmpty()) 
					    {
					        RTest najstRKnihu = Datab.getBookR(najstMenoKnihy) ;
					        if (najstRKnihu != null) 
					        {
					            System.out.println("Názov: " + najstRKnihu.getNazov()) ;
					            System.out.println("Autori: " + najstRKnihu.getAutori()) ;
					            System.out.println("Žáner: " + najstRKnihu.getZaner()) ;
					            System.out.println("Rok vydania: " + najstRKnihu.getRokVydania()) ;
					            System.out.println("Stav dostupnosti: " + najstRKnihu.getStavDostupnosti()) ;
					            break ;
					        }
					    }

					    if (!allUBooks.isEmpty()) 
					    {
					        UTest najstUKnihu = Datab.getBookU(najstMenoKnihy) ;
					        if (najstUKnihu != null) 
					        {
					            System.out.println("Názov: " + najstUKnihu.getNazov()) ;
					            System.out.println("Autori: " + najstUKnihu.getAutori()) ;
					            System.out.println("Ročník: " + najstUKnihu.getVhodnost()) ;
					            System.out.println("Rok vydania: " + najstUKnihu.getRokVydania()) ;
					            System.out.println("Stav dostupnosti: " + najstUKnihu.getStavDostupnosti()) ;
					            break ;
					        }
					    }

					    System.out.println("Kniha s daným názvom nebola nájdená.") ;
					    
					break ;
					case 7:
					    System.out.println("Zadajte meno autora, ktorého knihy chcete vypísať:") ;
					    String autorMeno = skener.nextLine() ;

					    ArrayList<RTest> allRBooks1 = (ArrayList<RTest>) Datab.getAllBooksR() ;
					    ArrayList<UTest> allUBooks1 = (ArrayList<UTest>) Datab.getAllBooksU() ;

					    boolean autorWasFound = false ;

					    for (RTest rBook : allRBooks1) 
					    {
					        if (rBook.getAutori().equalsIgnoreCase(autorMeno)) 
					        {
					        	autorWasFound = true ;
					            break ;
					        }
					    }

					    if (!autorWasFound) 
					    {
					        for (UTest uBook : allUBooks1) 
					        {
					            if (uBook.getAutori().equalsIgnoreCase(autorMeno)) 
					            {
					            	autorWasFound = true ;
					                break ;
					            }
					        }
					    }

					    if (!autorWasFound) 
					    {
					        System.out.println("Autor s menom " + autorMeno + " nie je v databáze.") ;
					        break ;
					    }

					    ArrayList<RTest> rKnihyAutor = new ArrayList<>() ;
					    ArrayList<UTest> uKnihyAutor = new ArrayList<>() ;

					    for (RTest rBook : allRBooks1) 
					    {
					        if (rBook.getAutori().equalsIgnoreCase(autorMeno)) 
					        {
					        	rKnihyAutor.add(rBook) ;
					        }
					    }

					    for (UTest uBook : allUBooks1) {
					        if (uBook.getAutori().equalsIgnoreCase(autorMeno)) 
					        {
					        	uKnihyAutor.add(uBook) ;
					        }
					    }

					    Collections.sort(rKnihyAutor, Comparator.comparing(RTest::getRokVydania)) ;
					    Collections.sort(uKnihyAutor, Comparator.comparing(UTest::getRokVydania)) ;

					    System.out.println("Všetky knihy autora " + autorMeno + " v chronologickom poradí:") ;
					    for (RTest rBook : rKnihyAutor) 
					    {
					        System.out.println("Názov: " + rBook.getNazov()) ;
					        System.out.println("Autori: " + rBook.getAutori()) ;
					        System.out.println("Žáner: " + rBook.getZaner()) ;
					        System.out.println("Rok vydania: " + rBook.getRokVydania()) ;
					        System.out.println("Stav dostupnosti: " + rBook.getStavDostupnosti()) ;
					        System.out.println() ;
					    }

					    for (UTest uBook : uKnihyAutor) 
					    {
					        System.out.println("Názov: " + uBook.getNazov()) ;
					        System.out.println("Autori: " + uBook.getAutori()) ;
					        System.out.println("Ročník: " + uBook.getVhodnost()) ;
					        System.out.println("Rok vydania: " + uBook.getRokVydania()) ;
					        System.out.println("Stav dostupnosti: " + uBook.getStavDostupnosti()) ;
					        System.out.println() ;
					    }
					    
					break ;
					case 8:
						 	System.out.println("Vyberte žáner knihy:") ;
						    System.out.println("1. Fantazia") ;
						    System.out.println("2. Romantika") ;
						    System.out.println("3. Sci-fi") ;
						    System.out.println("4. Klasika") ;
						    System.out.println("5. Horor") ;

						    int zanerV = skener.nextInt() ;
						 
						    String zaner = skener.nextLine() ;
						    switch (zanerV) 
						    {
						        case 1:
						        	zaner = "Fantazia" ;
						            break ;
						        case 2:
						        	zaner = "Romantika" ;
						            break ;
						        case 3:
						        	zaner = "Sci-fi" ;
						            break ;
						        case 4:
						        	zaner = "Klasika" ;
						            break ;
						        case 5:
						        	zaner = "Horor" ;
						            break ;
						        default:
						            System.out.println("Neplatná voľba žánru.") ;
						            break ;
						    }

						    if (zaner == null) 
						    {
						        break;
						    }					    
						
					    ArrayList<RTest> allRBooks2 = (ArrayList<RTest>) Datab.getAllBooksR() ;
					    ArrayList<UTest> allUBooks2 = (ArrayList<UTest>) Datab.getAllBooksU() ;

					    ArrayList<RTest> rKnihyZaner = new ArrayList<>() ;
					    ArrayList<UTest> uKnihyZaner = new ArrayList<>() ;

					    for (RTest rBook : allRBooks2) 
					    {
					        if (rBook.getZaner().equalsIgnoreCase(zaner)) 
					        {
					        	rKnihyZaner.add(rBook) ;
					        }
					    }

					    for (UTest uBook : allUBooks2) 
					    {
					        if (uBook.getVhodnost().equalsIgnoreCase(zaner)) 
					        {
					        	uKnihyZaner.add(uBook) ;
					        }
					    }

					    if (rKnihyZaner.isEmpty() && uKnihyZaner.isEmpty()) 
					    {
					        System.out.println("V databázi nie sú k dispozícii žiadne knihy daného žánru " + zaner) ;
					        break ;
					    }
					    else 
					    {
					    	System.out.println("Knihy v žánri " + zaner + ":") ;
					    for (RTest rBook : rKnihyZaner) 
					    {
					        System.out.println("Názov: " + rBook.getNazov()) ;
					        System.out.println("Autori: " + rBook.getAutori()) ;
					        System.out.println("Žáner: " + rBook.getZaner()) ;
					        System.out.println("Rok vydania: " + rBook.getRokVydania()) ;
					        System.out.println("Stav dostupnosti: " + rBook.getStavDostupnosti()) ;
					        System.out.println() ;
					    }

					    for (UTest uBook : uKnihyZaner) 
					    {
					        System.out.println("Názov: " + uBook.getNazov()) ;
					        System.out.println("Autori: " + uBook.getAutori()) ;
					        System.out.println("Ročník: " + uBook.getVhodnost()) ;
					        System.out.println("Rok vydania: " + uBook.getRokVydania()) ;
					        System.out.println("Stav dostupnosti: " + uBook.getStavDostupnosti()) ;
					        System.out.println() ;
					    }
					    }
					break ;
					case 9:
					    ArrayList<RTest> allRBooks3 = (ArrayList<RTest>) Datab.getAllBooksR() ;
					    ArrayList<UTest> allUBooks3 = (ArrayList<UTest>) Datab.getAllBooksU() ;

					    ArrayList<RTest> vypozicaneRKnihy = new ArrayList<>() ;
					    ArrayList<UTest> vypozicaneUKnihy = new ArrayList<>() ;

					    for (RTest rBook : allRBooks3) 
					    {
					        if (rBook.getStavDostupnosti().equalsIgnoreCase("Vypozicana") || rBook.getStavDostupnosti().equalsIgnoreCase("vypozicana") || rBook.getStavDostupnosti().equalsIgnoreCase("Vypožičaná") || rBook.getStavDostupnosti().equalsIgnoreCase("Nedostupná") || rBook.getStavDostupnosti().equalsIgnoreCase("nedostupna")) 
					        {
					            vypozicaneRKnihy.add(rBook) ;
					        }
					    }

					    for (UTest uBook : allUBooks3) 
					    {
					        if (uBook.getStavDostupnosti().equalsIgnoreCase("Vypozicana") || uBook.getStavDostupnosti().equalsIgnoreCase("vypozicana") || uBook.getStavDostupnosti().equalsIgnoreCase("Vypožičaná") || uBook.getStavDostupnosti().equalsIgnoreCase("Nedostupná") || uBook.getStavDostupnosti().equalsIgnoreCase("nedostupna")) 
					        {
					            vypozicaneUKnihy.add(uBook) ;
					        }
					    }


					    if (vypozicaneRKnihy.isEmpty() && vypozicaneUKnihy.isEmpty()) 
					    {
					        System.out.println("V databázi sa nenachádzajú žiadne vypožičané knihy.") ;
					        break ;
					    }
					    else 
					    {
					    System.out.println("Vypožičané knihy:") ;
					    System.out.println("Romány:") ;
					    for (RTest rBook : vypozicaneRKnihy) 
					    {
					        System.out.println("Názov: " + rBook.getNazov()) ;
					        System.out.println("Autori: " + rBook.getAutori()) ;
					        System.out.println() ;
					    }

					    System.out.println("Učebnice:") ;
					    for (UTest uBook : vypozicaneUKnihy) 
					    {
					        System.out.println("Názov: " + uBook.getNazov()) ;
					        System.out.println("Autori: " + uBook.getAutori()) ;
					        System.out.println() ;
					    }
					    }
					    
					break ;
					case 10:
					    System.out.println("Zadajte názov knihy, ktorú chcete uložiť do súboru:") ;
					    String menoUlozit = skener.nextLine() ;

					    RTest rKnihaUlozit = Datab.getBookR(menoUlozit) ;
					    UTest uKnihaUlozit = Datab.getBookU(menoUlozit) ;

					    if (rKnihaUlozit != null || uKnihaUlozit != null) 
					    {
					        try 
					        {
					            PrintWriter vypis = new PrintWriter("book_info.txt") ;

					            if (rKnihaUlozit != null) 
					            {
					            	vypis.println("Názov: " + rKnihaUlozit.getNazov()) ;
					            	vypis.println("Autori: " + rKnihaUlozit.getAutori()) ;
					            	vypis.println("Žáner: " + rKnihaUlozit.getZaner()) ;
					            	vypis.println("Rok vydania: " + rKnihaUlozit.getRokVydania()) ;
					            	vypis.println("Stav dostupnosti: " + rKnihaUlozit.getStavDostupnosti()) ;
					            } 
					            else 
					            {
					            	vypis.println("Názov: " + uKnihaUlozit.getNazov()) ;
					            	vypis.println("Autori: " + uKnihaUlozit.getAutori()) ;
					            	vypis.println("Ročník: " + uKnihaUlozit.getVhodnost()) ;
					            	vypis.println("Rok vydania: " + uKnihaUlozit.getRokVydania()) ;
					            	vypis.println("Stav dostupnosti: " + uKnihaUlozit.getStavDostupnosti()) ;
					            }

					            vypis.close() ;

					            System.out.println("Informácie o knihe " + menoUlozit + " boli úspešne uložené do súboru.") ;
					        } 
					        catch (FileNotFoundException e) 
					        {
					            System.out.println("Nepodarilo sa vytvoriť súbor.") ;
					        }
					    } 
					    else 
					    {
					        System.out.println("Kniha s názvom " + menoUlozit + " nebola nájdená.") ;
					    }
					    
					break ;
					case 11:
					    System.out.println("Zadajte názov knihy, ktorú chcete načítať zo súboru:") ;
					    String knihaNacitat = skener.nextLine() ;

					    try 
					    {
					        File subor = new File("k_info.txt") ;
					        Scanner suborSC = new Scanner(subor) ;
					        boolean knihaNajdena = false ;

					        while (suborSC.hasNextLine()) 
					        {
					            String line = suborSC.nextLine() ;
					            if (line.startsWith("Názov: ") && line.substring(7).equalsIgnoreCase(knihaNacitat)) 
					            {
					            	knihaNajdena = true ;
					                System.out.println("Informácie o knihe " + knihaNacitat + " zo súboru:") ;
					                while (suborSC.hasNextLine()) 
					                {
					                    line = suborSC.nextLine() ;
					                    if (line.isEmpty()) 
					                    {
					                        break ; 
					                    }
					                    System.out.println(line) ;
					                }
					                break ;
					            }
					        }

					        suborSC.close() ;

					        if (!knihaNajdena) 
					        {
					            System.out.println("Kniha s názvom " + knihaNacitat + " nebola nájdená vo súbore.") ;
					        }
					    } 
					    catch (FileNotFoundException e) 
					    {
					        System.out.println("Súbor s informáciami o knihách nebol nájdený.") ;
					    }
					    
					break ;
					case 12:
						System.out.println("Ukončili ste aplikáciu.") ;
						Datab.ulozenieDoSQL() ;
					    Chod = false;
					break ;
					
					default:
                        System.out.println("Neplatná voľba.") ;
                    break ;
					}
					Menu.totoMenu() ;
	        	} 
	        	catch (InputMismatchException e) 
	        	{
	                System.out.println("Neplatný vstup. Prosím zadajte platné číslo.") ;
	                skener.nextLine() ;
	            }
	        	
			}
	        skener.close() ;
	       
	    }
	}
	public class Menu
	{
		public static void totoMenu()
		{
        System.out.println("Vyberte pozadovanu cinnost:") ;
		System.out.println("1 .. Pridanie novej knihy") ;
		System.out.println("2 .. Úprava knihy") ;
		System.out.println("3 .. Vymazanie knihy") ;
		System.out.println("4 .. Značenie stavu knihy Dostupná/Nedostupná") ;
		System.out.println("5 .. Výpis všetkých kníh") ;
		System.out.println("6 .. Vyhľadanie knihy podľa názvu") ;
		System.out.println("7 .. Výpis kníh podľa Autorov (Chronologicky)") ;
		System.out.println("8 .. Výpis kníh podľa Žánru") ;
		System.out.println("9 .. Výpis kníh Nedostupných (Román/Učebnica)") ;
		System.out.println("10.. Uloženie knihy do súboru") ;
		System.out.println("11.. Načítanie informácií o danej knihe zo súboru") ;
		System.out.println("12.. Ukončenie aplikácie") ;
		}
	}
	public static int ibaCeléČísla(Scanner sc) 
	{
		int cislo = 0 ;
		try
		{
			cislo = sc.nextInt() ;
		}
		catch(Exception e)
		{
			System.out.println("Nastala výnimka typu "+e.toString()) ;
			System.out.println("Zadajte prosím celé číslo ") ;
			sc.nextLine() ;
			cislo = ibaCeléČísla(sc) ;
		}
		return cislo ;
	}
}
