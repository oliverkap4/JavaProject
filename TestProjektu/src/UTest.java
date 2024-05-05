import java.util.ArrayList;

public class UTest {
	
	private ArrayList<Object> ParameterU ;
	
	public UTest(String Nazov, String Autori, int Rok, String Stav, String Vhodnost) 
	{
		this.Nazov = Nazov ;
		this.Autori = Autori ;
		this.Rok = Rok ;
		this.Stav = Stav ;
		this.Vhodnost = Vhodnost ;
		this.ParameterU = new ArrayList<>() ;
	}
	public String getNazov()
	{
		return Nazov ;
	}
	public void setNazov(String Nazov) 
	{
		this.Nazov = Nazov ;
	}
	public String getAutori()
	{
		return Autori ;
	}
	public void setAutori (String Autori)
	{
		this.Autori = Autori ;
	}
	public int getRokVydania()
	{
		return Rok ;
	}
	public void setRokVydania(int Rok)
	{
		this.Rok = Rok;
	}
	public String getStavDostupnosti()
	{
		return Stav ;
	}
	public void setStavDostupnosti(String Stav)
	{
		this.Stav = Stav ;
	}
	public String getVhodnost()
	{
		return Vhodnost ;
	}
	public void setVhodnost(String Vhodnost)
	{
		this.Vhodnost = Vhodnost ;
	}
	private String Nazov, Autori, Stav, Vhodnost; int Rok ;

	public void add(RTest book) {
		ParameterU.add(ParameterU) ;
		
	}

}
