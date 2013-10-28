package Players;

public class TMember {
	private String FCPR;
	public String FName;
	public String FNick;
	
	public String Identifier(){
		return FCPR;
	}
	
	public TMember(String ACPR, String AName, String ANick){
		FCPR = ACPR;
		FName = AName;
		FNick = ANick;
	}
}
