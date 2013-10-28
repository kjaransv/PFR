package Players;

import java.util.ArrayList;
import java.util.List;

public class TTeam {
	public String FID;
	public String FName;
	public List<TMember> FMembers = new ArrayList<TMember>();
	
	public TTeam(String AID, String AName){
		FID = AID;
		FName = AName;
	}
	
	public void AddMember(TMember AMember){
		FMembers.add(AMember);
	}
}
