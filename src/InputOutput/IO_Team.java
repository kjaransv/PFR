package InputOutput;

import java.util.HashMap;

public class IO_Team extends TScore{
	public IO_Team(String APlayer, String ATeam) {
		super(APlayer, ATeam);
	}

	private HashMap<String, TScore> FScore = new HashMap<String, TScore>();
	
	@Override
	public int GetWin() {
		return super.GetWin()+F13_W;
	}
	
	public TScore Get(String APlayer){
		TScore result = FScore.get(APlayer);
		if (result == null){
			result = new TScore(APlayer, FTeam);
			FScore.put(APlayer, result);
		}
		return result;
	}
	
	public void Add(IO_Team ATeam){
		// self
		super.Add(ATeam);
		
		// fscore
		TScore[] tmp = new TScore[0];
		tmp = ATeam.FScore.values().toArray(tmp);
		for (int i=0; i<tmp.length; i++){
			TScore tmp2 = FScore.get(tmp[i].GetPlayer());
			if (tmp2==null){
				FScore.put(tmp[i].GetPlayer(), tmp[i]);
			} else {
				tmp2.Add(tmp[i]);
			}
		}
	}
	
	public TScore[] GetScore(){
		TScore[] result = new TScore[0];
		result = FScore.values().toArray(result);
		return result;
	}
	
	public void Update(){
		int w13 = F13_W;
		int l13 = F13_L;
		int w501 = F501_W;
		int l501 = F501_L;
		int w501_2 = F501_2_W;
		int l501_2 = F501_2_L;
		int w301 = F301_W;
		int l301 = F301_L;
		int wkrikket = FKrikket_W;
		int lkrikket = FKrikket_L;
		
		FQP = 0;
		F180 = 0;
		FR9 = 0;
		F7H = 0;
		FIn = 0;
		FOut = 0;
		
		for (TScore score: GetScore()){
			Add(score);
		}
		
		F13_W = w13;
		F13_L = l13;
		F501_W = w501;
		F501_L = l501;
		F501_2_W = w501_2;
		F501_2_L = l501_2;
		F301_W = w301;
		F301_L = l301;
		FKrikket_W = wkrikket;
		FKrikket_L = lkrikket;
	}
}
