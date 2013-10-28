package Players;

public class TMatch {
	public int FHomeTeam;
	public int FAwayTeam;
	public int FHomeScore;
	public int FAwayScore;
	
	public TMatch(int AHomeTeam, int AAwayTeam){
		FHomeTeam = AHomeTeam;
		FAwayTeam = AAwayTeam;
	}
	
	@Override
	public String toString() {
		return FHomeTeam+"("+FHomeScore+")"+" vs "+FAwayTeam+"("+FAwayScore+")";
	}
}
