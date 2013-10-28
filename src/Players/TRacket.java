package Players;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TRacket {
	public String FID;
	public String FName;
	public List<TTeam> FTeams = new ArrayList<TTeam>();
	public List<TMatch> FMatches = new ArrayList<TMatch>();

	public TRacket(String AID, String AName){
		FID = AID;
		FName = AName;		
	}
	
	public void AddTeam(TTeam ATeam){
		FTeams.add(ATeam);
	}
	
	public void AddMatch(TMatch AMatch){
		FMatches.add(AMatch);
	}
	
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.#");
		
		// create table
		// insert cell data
		String[][] out = new String[FTeams.size()+1][FTeams.size()+4];
		
		// headers
		out[0][out[0].length-2] = "<th>Total</th>";
		out[0][out[0].length-1] = "<th>Rank</th>";
		
		// add matches, and count score
		double[] score = new double[FTeams.size()];
		int[] legs_w = new int[FTeams.size()];
		int[] legs_l = new int[FTeams.size()];
		for (int i=0; i<FMatches.size(); i++){
			TMatch m = FMatches.get(i);
			legs_w[m.FHomeTeam]+= m.FHomeScore;
			legs_l[m.FHomeTeam]+= m.FAwayScore;
			legs_w[m.FAwayTeam]+= m.FAwayScore;
			legs_l[m.FAwayTeam]+= m.FHomeScore;
			
			double home = 0;
			double away = 0;
			
			if (m.FHomeScore<m.FAwayScore){
				away = 1;
			} else if (m.FHomeScore==m.FAwayScore){
				away = 0.5;
				home = 0.5;
			} else {
				home = 1;
			}
			score[m.FAwayTeam]+= away;
			score[m.FHomeTeam]+= home;
			
			// home
			out[m.FHomeTeam+1][m.FAwayTeam+2] = "<td><big>"+df.format(home)+"</big> <sup>"+m.FHomeScore+"</sup>/<sub>"+m.FAwayScore+"</sub></td>";
			// away
			out[m.FAwayTeam+1][m.FHomeTeam+2] = "<td><big>"+df.format(away)+"</big> <sup>"+m.FAwayScore+"</sup>/<sub>"+m.FHomeScore+"</sub></td>";
		}
		int[] rank = new int[FTeams.size()];
		int[] rank2 = new int[FTeams.size()];
		for (int i=0; i<rank.length; i++){
			rank[i] = i;
		}
		for (int i=0; i<rank.length-1; i++){
			for (int j=i+1; j<rank.length; j++){
				boolean swap = false;
				if (score[rank[i]] < score[rank[j]]){
					swap = true;
				} else if (score[rank[i]] == score[rank[j]]){
					if (legs_w[rank[i]]/(float)legs_l[rank[i]] < legs_w[rank[j]]/(float)legs_l[rank[j]]){
						swap = true;
					}
				}
				
				if (swap){
					int tmp = rank[i];
					rank[i] = rank[j];
					rank[j] = tmp;
				}
			}
		}
		for (int i=0; i<rank.length; i++){
			rank2[rank[i]] = i;
		}
		
		// add id, team and black boxes
		for (int i=1; i<=FTeams.size(); i++){
			out[i][0] = "<th>"+i+".</th>";
			out[0][i+1] = "<th>"+i+".</th>";
			out[i][1] = "<th align=\"right\">"+FTeams.get(i-1).FName+"</th>";
			out[i][i+1] = "<td bgcolor=\"#000000\"></td>";
			
			// add total and rank
			out[i][out[0].length-2] = "<th><big>"+df.format(score[i-1])+"</big> <sup>"+legs_w[i-1]+"</sup>/<sub>"+legs_l[i-1]+"</sub></th>";
			out[i][out[0].length-1] = "<th>"+(rank2[i-1]+1)+"</th>";
		}
		
		String result = "<table style=\"BORDER-COLLAPSE: collapse\" border=\"1\" cellspacing=\"4\" cellpadding=\"4\"><tbody>";
		for (int i=0; i<out.length; i++){
			if (i%2==1){
				result+= "<tr bgcolor=\"#DBE5F1\">";
			} else {
				result+= "<tr>";
			}
			for (int j=0; j<out[i].length; j++){
				if (out[i][j] == null){
					result+= "<td></td>";
				} else {
					result+= out[i][j];
				}
			}
			result+= "</tr>";
		}
		
		return result+"</tbody></table>";
	}
}
