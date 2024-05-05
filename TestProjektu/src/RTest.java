import java.util.ArrayList;

public class RTest {

	private ArrayList<Object> ParameterR ;
	
	public RTest(String Nazov, String Autori, int Rok, String Stav, String Zaner) 
	{
		this.Nazov = Nazov ;
		this.Autori = Autori ;
		this.Rok = Rok ;
		this.Stav = Stav ;
		this.Zaner = Zaner ;
		this.ParameterR = new ArrayList<>() ;
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
	public String getZaner()
	{
		return Zaner ;
	}
	public void setZaner(String Zaner)
	{
		this.Zaner = Zaner ;
	}
	
	private String Nazov, Autori, Stav, Zaner ; int Rok ;
	
	public void add(RTest book) {
		ParameterR.add(ParameterR) ;
		
	}
}

