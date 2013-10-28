package Players;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TSeason {
	private String FFileName;
	private TMemberRoster FRoster;
	private HashMap<String, TTeam> FTeams = new HashMap<String, TTeam>();
	private List<TRacket> FRackets = new ArrayList<TRacket>();
	
	public TSeason(TMemberRoster ARoster, String AFileName) throws IOException{
		FFileName = AFileName;
		FRoster = ARoster;
		
		ReadFile();
	}
	
	public TRacket[] GetRackets(){
		TRacket[] result = new TRacket[0];
		result = FRackets.toArray(result);
		return result;
	}
	
	public TTeam GetTeam(String ATeam){
		return FTeams.get(ATeam);
	}
	
	private void ReadFile() throws IOException{
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(FFileName));

            String l;
            while ((l = inputStream.readLine()) != null) {
            	//System.out.println(l);
            	String[] tmp = l.split("\t");
            	switch (tmp[0]){
            		case "TEAM":
                		TTeam t = new TTeam(tmp[1], tmp[2]);
                		for (int i=3; i<tmp.length; i++){
                			t.AddMember(FRoster.GetMember(tmp[i]));
                		}
                		FTeams.put(t.FID, t);
            			break;
            		case "RACKET":
            			TRacket r = new TRacket(tmp[1], tmp[2]);
            			for (int i=3; i<tmp.length; i++){
            				r.AddTeam(FTeams.get(tmp[i]));
                		}
            			FRackets.add(r);
            			break;
            		case "MATCH":
            			TRacket r2 = FRackets.get(Integer.parseInt(tmp[1]));
            			TMatch m = new TMatch(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));
            			m.FHomeScore = Integer.parseInt(tmp[4]);
            			m.FAwayScore = Integer.parseInt(tmp[5]);
            			r2.AddMatch(m);
            			break;
            	}
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
	}
}
