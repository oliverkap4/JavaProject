import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DKTest 
{
	private Connection connection;
    DKTest() 
    {
        prvkyDatabazeR = new HashMap<>() ;
        prvkyDatabazeU = new HashMap<>() ;
        try 
        {
            connection = DriverManager.getConnection("jdbc:sqlite:library.db") ;
            vytvoritTabulky() ;
            nacitanieTabuliek() ;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace() ;
        }
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

	public void vytvoritTabulky() 
	{
        try (Statement statement = connection.createStatement()) 
        {
            statement.execute("CREATE TABLE IF NOT EXISTS RBooks (nazov TEXT PRIMARY KEY, autor TEXT, rok INTEGER, stav TEXT, zaner TEXT)") ;
            statement.execute("CREATE TABLE IF NOT EXISTS UBooks (nazov TEXT PRIMARY KEY, autor TEXT, rok INTEGER, stav TEXT, vhodnost TEXT)") ;
        } catch (SQLException e) 
        {
            e.printStackTrace() ;
        }
    }
	
	public void nacitanieTabuliek() 
	{
        try 
        {
            nacitanieRKnih() ;
            nacitanieUKnih() ;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace() ;
        }
    }
	
	public void nacitanieRKnih() throws SQLException 
	{
        String sql = "SELECT * FROM RBooks" ;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) 
        {
            ResultSet resultSet = preparedStatement.executeQuery() ;
            while (resultSet.next()) 
            {
                String nazov = resultSet.getString("nazov") ;
                String autor = resultSet.getString("autor") ;
                int rok = resultSet.getInt("rok") ;
                String stav = resultSet.getString("stav") ;
                String zaner = resultSet.getString("zaner") ;
                RTest book = new RTest(nazov, autor, rok, stav, zaner) ;
                prvkyDatabazeR.put(nazov, book) ;
            }
        }
    }

	public void nacitanieUKnih() throws SQLException 
    {
        String sql = "SELECT * FROM UBooks" ;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) 
        {
            ResultSet resultSet = preparedStatement.executeQuery() ;
            while (resultSet.next()) 
            {
                String nazov = resultSet.getString("nazov") ;
                String autor = resultSet.getString("autor") ;
                int rok = resultSet.getInt("rok") ;
                String stav = resultSet.getString("stav") ;
                String vhodnost = resultSet.getString("vhodnost") ;
                UTest book = new UTest(nazov, autor, rok, stav, vhodnost) ;
                prvkyDatabazeU.put(nazov, book) ;
            }
        }
    }
	
	public void ulozenieDoSQL() 
	{
        try 
        {
            vycistenieTab() ;
            ulozenieRKnich() ;
            ulozenieUKnich() ;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace() ;
        }
    }

	public void vycistenieTab() throws SQLException 
	{
        try (Statement statement = connection.createStatement()) 
        {
            statement.execute("DELETE FROM RBooks") ;
            statement.execute("DELETE FROM UBooks") ;
        }
    }

	public void ulozenieRKnich() throws SQLException 
	{
        String sql = "INSERT INTO RBooks (nazov, autor, rok, stav, zaner) VALUES (?, ?, ?, ?, ?)" ;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) 
        {
            for (Map.Entry<String, RTest> entry : prvkyDatabazeR.entrySet()) 
            {
                RTest book = entry.getValue() ;
                preparedStatement.setString(1, book.getNazov()) ;
                preparedStatement.setString(2, book.getAutori()) ;
                preparedStatement.setInt(3, book.getRokVydania()) ;
                preparedStatement.setString(4, book.getStavDostupnosti()) ;
                preparedStatement.setString(5, book.getZaner()) ;
                preparedStatement.executeUpdate() ;
            }
        }
    }

	public void ulozenieUKnich() throws SQLException 
	{
        String sql = "INSERT INTO UBooks (nazov, autor, rok, stav, vhodnost) VALUES (?, ?, ?, ?, ?)" ;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) 
        {
            for (Map.Entry<String, UTest> entry : prvkyDatabazeU.entrySet()) 
            {
                UTest book = entry.getValue() ;
                preparedStatement.setString(1, book.getNazov()) ;
                preparedStatement.setString(2, book.getAutori()) ;
                preparedStatement.setInt(3, book.getRokVydania()) ;
                preparedStatement.setString(4, book.getStavDostupnosti()) ;
                preparedStatement.setString(5, book.getVhodnost()) ;
                preparedStatement.executeUpdate() ;
            }
        }
    }
	
	public void konec() 
	{
        try 
        {
            if (connection != null) 
            {
                connection.close() ;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace() ;
        }
    }
	
}
