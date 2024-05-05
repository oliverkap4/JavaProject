import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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

	
	public class DatabaseManager 
	{
	    private static final String URL = "jdbc:sqlite:library.db" ;

	    public static Connection getConnection() throws SQLException 
	    {
	        return DriverManager.getConnection(URL) ;
	    }

	    public static void ulozitDK() 
	    {
	        try (Connection connection = getConnection()) 
	        {
	            
	        } catch (SQLException e) 
	        {
	            e.printStackTrace() ;
	        }
	    }

	    public static void nacitatDK() 
	    {
	        try (Connection connection = getConnection()) 
	        {
	            
	        } catch (SQLException e) 
	        {
	            e.printStackTrace() ;
	        }
	    }
	}

	
}