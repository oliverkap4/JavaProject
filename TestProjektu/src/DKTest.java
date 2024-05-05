import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DKTest {
    DKTest() {
        prvkyDatabazeR = new HashMap<>() ;
        prvkyDatabazeU = new HashMap<>() ;
    }

    public boolean addBookR(String nazov, RTest roman) 
    {
        if (!prvkyDatabazeR.containsKey(nazov)) 
        {
            prvkyDatabazeR.put(nazov, roman) ;
            return true ;
        } 
        else 
        {
            return false ;
        }
    }

    public boolean addBookU(String nazov, UTest ucebnica) 
    {
        if (!prvkyDatabazeU.containsKey(nazov)) 
        {
            prvkyDatabazeU.put(nazov, ucebnica) ;
            return true ;
        } 
        else 
        {
            return false ;
        }
    }

    public RTest getBookR(String nazov) 
    {
        return prvkyDatabazeR.get(nazov) ;
    }
    public UTest getBookU(String nazov) 
    {
        return prvkyDatabazeU.get(nazov) ;
    }

    public boolean removeBookR(String nazov) 
    {
        if (prvkyDatabazeR.remove(nazov) != null) 
        {
            return true ;
        }
        return false ;
    }
    public boolean removeBookU(String nazov) 
    {
        if (prvkyDatabazeU.remove(nazov) != null) 
        {
            return true ;
        }
        return false ;
    }

    public void printDatabaseR() 
    {
    	 for (Map.Entry<String, RTest> entry : prvkyDatabazeR.entrySet()) 
    	 {
             System.out.println(entry.getValue().getNazov()) ;
         }
    }

    public void printDatabaseU() 
    {
        Set<String> keys = prvkyDatabazeU.keySet() ; 
        for (String key : keys) 
        {
            System.out.println(key) ;
        }
    }
    
    private Map<String, RTest> prvkyDatabazeR ;
    private Map<String, UTest> prvkyDatabazeU ;
    
	public ArrayList<RTest> getAllBooksR() 
	{
		ArrayList<RTest> allRBooks = new ArrayList<>() ;
        for (Map.Entry<String, RTest> entry : prvkyDatabazeR.entrySet()) 
        {
            allRBooks.add(entry.getValue()) ;
        }
        return allRBooks ;
	}

	public ArrayList<UTest> getAllBooksU() 
	{
	    ArrayList<UTest> allUBooks = new ArrayList<>() ;
	    for (Map.Entry<String, UTest> entry : prvkyDatabazeU.entrySet()) 
	    {
	        allUBooks.add(entry.getValue()) ;
	    }
	    return allUBooks ;
	}

	
	
	    private static final String URL = "jdbc:sqlite:SQLKnihy.db" ;

	    public static Connection getConnection() throws SQLException 
	    {
	        return DriverManager.getConnection(URL) ;
	    }
	    
	    public static void vytvoritDK()
	    {
	    	
	    }
	    
	    public static void ulozitDK() 
	    {
	        try (Connection connection = getConnection()) 
	        {
	            Statement statement = connection.createStatement() ;

	            String checkTableQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='knihy'" ;
	            if (!statement.executeQuery(checkTableQuery).next()) 
	            {
	                String createTableQuery = "CREATE TABLE knihy (nazov TEXT PRIMARY KEY, autor TEXT, zaner TEXT, rok INTEGER, stav TEXT)" ;
	                statement.executeUpdate(createTableQuery) ;
	            }

	            nacitatDataDo(statement) ;

	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace() ;
	        }
	    }

	   
	
	public static void nacitatDK() 
	{
	    try (Connection connection = getConnection()) 
	    {
	        Statement A = connection.createStatement() ;

	        String checkTableQuery = "SELECT name FROM sqlite_master WHERE type='table' AND name='knihy'" ;
	        if (!A.executeQuery(checkTableQuery).next()) 
	        {
	            String createTableQuery = "CREATE TABLE knihy (nazov TEXT PRIMARY KEY, autor TEXT, zaner TEXT, rok INTEGER, stav TEXT)" ;
	            A.executeUpdate(createTableQuery) ;
	        }

	        nacitatDataDo(A) ;

	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace() ;
	    }
	}

	private static void nacitatDataDo(Statement statement) 
	{
	    try 
	    {
	        DKTest database = new DKTest() ;

	        ArrayList<RTest> allRBooks = database.getAllBooksR() ;

	        for (RTest rBook : allRBooks) 
	        {
	            String nazov = rBook.getNazov() ;
	            String autor = rBook.getAutori() ;
	            String zaner = rBook.getZaner() ;
	            int rok = rBook.getRokVydania() ;
	            String stav = rBook.getStavDostupnosti() ;

	            String insertQuery = "INSERT INTO knihy (nazov, autor, zaner, rok, stav) VALUES ('" + nazov + "', '" + autor + "', '" + zaner + "', " + rok + ", '" + stav + "')" ;
	            statement.executeUpdate(insertQuery) ;
	        }

	        ArrayList<UTest> allUBooks = database.getAllBooksU() ;

	        for (UTest uBook : allUBooks) 
	        {
	            String nazov = uBook.getNazov() ;
	            String autor = uBook.getAutori() ;
	            String zaner = "Román" ; 
	            int rok = uBook.getRokVydania() ;
	            String stav = uBook.getStavDostupnosti() ;

	            String insertQuery = "INSERT INTO knihy (nazov, autor, zaner, rok, stav) VALUES ('" + nazov + "', '" + autor + "', '" + zaner + "', " + rok + ", '" + stav + "')" ;
	            statement.executeUpdate(insertQuery) ;
	        }
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace() ;
	    }
	}

	private static void Tabulka(Connection connection) throws SQLException 
	{
	    String selectQuery = "SELECT * FROM knihy" ;
	    try (Statement statement = connection.createStatement() ;
	         ResultSet resultSet = statement.executeQuery(selectQuery)) 
	    {
	        while (resultSet.next()) 
	        {
	            String nazov = resultSet.getString("nazov") ;
	            String autor = resultSet.getString("autor") ;
	            String zaner = resultSet.getString("zaner") ;
	            int rok = resultSet.getInt("rok") ;
	            String stav = resultSet.getString("stav") ;
	            System.out.println("Nazov: " + nazov) ;
	            System.out.println("Autor: " + autor) ;
	            System.out.println("Zaner: " + zaner) ;
	            System.out.println("Rok: " + rok) ;
	            System.out.println("Stav: " + stav) ;
	            System.out.println() ; 
	        }
	    }
	}


	
}
