package InputOutput;

public class TScore implements Comparable<TScore>{
	protected String FPlayer;
	protected String FTeam;

	protected int F301_W, F301_L;
	protected int F501_W, F501_L;
	protected int F13_W, F13_L;
	protected int F501_2_W, F501_2_L;
	protected int FKrikket_W, FKrikket_L;
	
	protected int FQP;
	protected int F180;
	protected int FR9;
	protected int F7H;
	protected int FIn;
	protected int FOut;
	
	public TScore(String APlayer, String ATeam){
		FPlayer = APlayer;
		FTeam = ATeam;
	}
	
	public void SetInn(String AValue){
		try {
			int tmp = Integer.parseInt(AValue);
			if (tmp>FIn) FIn = tmp;
		} catch (NumberFormatException e) {
		}
	}
	
	public void SetOut(String AValue){
		try {
			int tmp = Integer.parseInt(AValue);
			if (tmp>FOut) FOut = tmp;
		} catch (NumberFormatException e) {
		}
	}
	
	public void Add13Win(){
		F13_W++;
	}
	
	public void Add501Win(){
		F501_W++;
	}
	
	public void Add501_2Win(){
		F501_2_W++;
	}
	
	public void Add301Win(){
		F301_W++;
	}
	
	public void AddKrikketWin(){
		FKrikket_W++;
	}
	
	public void Add13Loss(){
		F13_L++;
	}
	
	public void Add501Loss(){
		F501_L++;
	}
	
	public void Add501_2Loss(){
		F501_2_L++;
	}
	
	public void Add301Loss(){
		F301_L++;
	}
	
	public void AddKrikketLoss(){
		FKrikket_L++;
	}
	
	public void SetQP(String AValue){
		try {
			int tmp = Integer.parseInt(AValue);
			if (tmp>FQP) FQP = tmp;
		} catch (NumberFormatException e) {
		}
	}
	
	public void Set180(String AValue){
		try {
			int tmp = Integer.parseInt(AValue);
			if (tmp>F180) F180 = tmp;
		} catch (NumberFormatException e) {
		}
	}
	
	public void Set7H(String AValue){
		try {
			int tmp = Integer.parseInt(AValue);
			if (tmp>F7H) F7H = tmp;
		} catch (NumberFormatException e) {
		}
	}
	
	public void SetR9(String AValue){
		try {
			int tmp = Integer.parseInt(AValue);
			if (tmp>FR9) FR9 = tmp;
		} catch (NumberFormatException e) {
		}
	}
	
	public String GetPlayer(){
		return FPlayer;
	}
	
	public String GetTeam(){
		return FTeam;
	}
	
	public void Add(TScore AScore){
		F13_W += AScore.F13_W;
		F13_L += AScore.F13_L;
		F501_W += AScore.F501_W;
		F501_L += AScore.F501_L;
		F501_2_W += AScore.F501_2_W;
		F501_2_L += AScore.F501_2_L;
		F301_W += AScore.F301_W;
		F301_L += AScore.F301_L;
		FKrikket_W += AScore.FKrikket_W;
		FKrikket_L += AScore.FKrikket_L;
		
		FQP += AScore.FQP;
		F180 += AScore.F180;
		FR9 += AScore.FR9;
		F7H += AScore.F7H;

		FIn = Math.max(FIn, AScore.FIn);
		FOut = Math.max(FOut, AScore.FOut);
	}
	
	@Override
	public String toString() {
		//Nr.	Nafn / Player	Lið / Team	Qp	Won	Lost	180	Round 9	7 Hit	Innskot	Útskot
		return FPlayer+"	"+FTeam+"	"+FQP+"	"+GetWin()+"	"+GetLoss()+"	"+F180+"	"+FR9+"	"+F7H+"	"+FIn+"	"+FOut;
	}

	@Override
	public int compareTo(TScore that) {
		int result = Integer.compare(this.FQP, that.FQP);
		
		if(result==0){
			result = Integer.compare(this.F180, that.F180);
		}
		
		return -result;
	}
	
	public int Get180(){
		return F180;
	}
	
	public int Get7H(){
		return F7H;
	}
	
	public int GetIn(){
		return FIn;
	}
	
	public int GetLoss(){
		return F301_L+F501_L+F13_L+F501_2_L+FKrikket_L;
	}
	
	public int GetOut(){
		return FOut;
	}
	
	public int GetQP(){
		return FQP;
	}
	
	public int GetR9(){
		return FR9;
	}
	
	public int GetWin(){
		return F301_W+F501_W+F13_W+F501_2_W+FKrikket_W;
	}
	
	
	public String GetStat(int AWin, int ALoss){
		double tmp = 0;
		if (AWin != 0){
			tmp = (AWin*100.0)/(AWin+ALoss);
		}
		return AWin+"/"+ALoss+" ("+(int)tmp+"%)";
	}
	
	public String Get301(){
		return GetStat(F301_W, F301_L); 
	}
	
	public String Get501(){
		return GetStat(F501_W, F501_L); 
	}
	
	public String GetKrikket(){
		return GetStat(FKrikket_W, FKrikket_L); 
	}	
	
	public String Get501_2(){
		return GetStat(F501_2_W, F501_2_L); 
	}
	
	public String Get13(){
		return GetStat(F13_W, F13_L); 
	}
	
	public String GetTotal(){
		return GetStat(GetWin(), GetLoss()); 
	}
}
