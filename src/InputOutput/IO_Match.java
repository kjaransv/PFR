package InputOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Players.TMatch;
import Players.TRacket;

public class IO_Match {
	private static void Do301(IO_Team AHome, IO_Team AAway, String[] AData){
		//Home	In	Out1	Out2	Result	Out1	Out2	In	Away
		TScore h = AHome.Get(AData[0]);
		h.SetInn(AData[1]);
		h.SetOut(AData[2]);
		h.SetOut(AData[3]);
		
		TScore a = AAway.Get(AData[8]);
		a.SetOut(AData[5]);
		a.SetOut(AData[6]);
		a.SetInn(AData[7]);
		
		if (AData[4].equals("1")){
			h.Add301Win();
			a.Add301Loss();
			
			AHome.Add301Win();
			AAway.Add301Loss();
		} else if (AData[4].equals("2")){
			a.Add301Win();
			h.Add301Loss();
			
			AAway.Add301Win();
			AHome.Add301Loss();
		}
	}
	
	private static void Do501_2(IO_Team AHome, IO_Team AAway, String[] AData){
		//Home	Out1	Out2	Result	Out1	Out2	Away	Home	Out1	Out2	Result	Out1	Out2	Away
		TScore h1 = AHome.Get(AData[0]);
		h1.SetOut(AData[1]);
		h1.SetOut(AData[2]);
		TScore h2 = AHome.Get(AData[7]);
		h2.SetOut(AData[8]);
		h2.SetOut(AData[9]);
		
		TScore a1 = AAway.Get(AData[6]);
		a1.SetOut(AData[4]);
		a1.SetOut(AData[5]);
		TScore a2 = AAway.Get(AData[13]);
		a2.SetOut(AData[11]);
		a2.SetOut(AData[12]);
		
		if (AData[3].equals("1")){
			h1.Add501_2Win();
			h2.Add501_2Win();
			a1.Add501_2Loss();
			a2.Add501_2Loss();
			
			AHome.Add501_2Win();
			AAway.Add501_2Loss();
		} else if (AData[3].equals("2")){
			a1.Add501_2Win();
			a2.Add501_2Win();
			h1.Add501_2Loss();
			h2.Add501_2Loss();
			
			AAway.Add501_2Win();
			AHome.Add501_2Loss();
		}
	}
	
	private static void Do501(IO_Team AHome, IO_Team AAway, String[] AData){
		//Home	Out1	Out2	Result	Out1	Out2	Away
		TScore h = AHome.Get(AData[0]);
		h.SetOut(AData[1]);
		h.SetOut(AData[2]);
		
		TScore a = AAway.Get(AData[6]);
		a.SetOut(AData[4]);
		a.SetOut(AData[5]);
		
		if (AData[3].equals("1")){
			h.Add501Win();
			a.Add501Loss();
			
			AHome.Add501Win();
			AAway.Add501Loss();
		} else if (AData[3].equals("2")){
			a.Add501Win();
			h.Add501Loss();
			
			AAway.Add501Win();
			AHome.Add501Loss();
		}
	}
	
	private static void DoKrikket(IO_Team AHome, IO_Team AAway, String[] AData){
		//Home	Out1	Out2	Result	Out1	Out2	Away	Home	Out1	Out2	Result	Out1	Out2	Away
		TScore h1 = AHome.Get(AData[0]);
		TScore h2 = AHome.Get(AData[7]);
		
		TScore a1 = AAway.Get(AData[6]);
		TScore a2 = AAway.Get(AData[13]);
		
		if (AData[3].equals("1")){
			h1.AddKrikketWin();
			h2.AddKrikketWin();
			a1.AddKrikketLoss();
			a2.AddKrikketLoss();
			
			AHome.AddKrikketWin();
			AAway.AddKrikketLoss();
		} else if (AData[3].equals("2")){
			a1.AddKrikketWin();
			a2.AddKrikketWin();
			h1.AddKrikketLoss();
			h2.AddKrikketLoss();
			
			AAway.AddKrikketWin();
			AHome.AddKrikketLoss();
		}
	}
	
	private static void Do13(IO_Team AHome, IO_Team AAway, String[] AData){
		// 0	1	2	3	4	5	6	7	8	9	10		11		12		13		14		15		16		17		18
		// h1	h2	h3	h4	h5	a1	a2	a3	a4	a5	h1a2	h2a1	h3a4	h4a3	h2a2	h1a4	h4a1	h3a3	h5a5
		int[] hm = new int[]{0, 1, 2, 3, 1, 0, 3, 2, 4};
		int[] am = new int[]{1, 0, 3, 2, 1, 3, 0, 2, 4};
		// array of players, array of players, array of results
		// iterate, count home, count away, stop at 5
		TScore[] h = new TScore[5];
		TScore[] a = new TScore[5];
		for (int i=0; i<h.length; i++){
			h[i] = AHome.Get(AData[i]);
			a[i] = AAway.Get(AData[i+5]);
		}
		
		int home = 0;
		int away = 0;
		int m = 0;
		while (home<5 && away<5){
			if (AData[10+m].equals("1")){
				h[hm[m]].Add13Win();
				a[am[m]].Add13Loss();
				home++;
			} else if (AData[10+m].equals("2")){
				a[am[m]].Add13Win();
				h[hm[m]].Add13Loss();
				away++;
			}
			m++;
		}
		
		if (home==5){
			AHome.Add13Win();
			AAway.Add13Loss();
		} else if (away==5){
			AAway.Add13Win();
			AHome.Add13Loss();
		}
	}
	
	private static void DoQP(IO_Team ATeam, String[] AData){
		//Home	QP	180	inn	out	7hit	round 9
		TScore p = ATeam.Get(AData[0]);
		p.SetQP(AData[1]);
		p.Set180(AData[2]);
		p.SetInn(AData[3]);
		p.SetOut(AData[4]);
		p.Set7H(AData[5]);
		p.SetR9(AData[6]);
	}
	
	public static IO_Team[] ReadFile(String AFileName, TRacket ARacket) throws IOException{
        BufferedReader inputStream = null;

        TMatch m = null;
        
        IO_Team home = null;
        IO_Team away = null;
        
        int state = 0;
        
        try {
            inputStream = new BufferedReader(new FileReader(AFileName));
            
            String l;
            while ((l = inputStream.readLine()) != null) {
            	String[] tmp = l.split("\t");
            	switch (tmp[0]){
            		case "MATCH":
            			//TRacket r2 = FRackets.get(Integer.parseInt(tmp[1]));
            			m = new TMatch(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));
            			m.FHomeScore = Integer.parseInt(tmp[4]);
            			m.FAwayScore = Integer.parseInt(tmp[5]);
            			ARacket.AddMatch(m);
            	        home = new IO_Team("", tmp[2]);
            	        away = new IO_Team("", tmp[3]);
            			break;
            		case "301":
            			state = 301;            			
            			break;
            		case "501_2":
            			state = 501_2;
            			break;
            		case "501":
            			state = 501;
            			break;
            		case "Krikket":
            			state = 9000;
            			break;
            		case "13":
            			state = 13;
            			break;
            		case "QP Home":
            			state = 1337;
            			break;
            		case "QP Away":
            			state = 1338;
            			break;
            		default: 
            			switch (state){
            				case 301: Do301(home, away, tmp); break;
            				case 501_2: Do501_2(home, away, tmp); break;
            				case 501: Do501(home, away, tmp); break;
            				case 9000: DoKrikket(home, away, tmp); break;
            				case 13: Do13(home, away, tmp); break;
            				case 1337: DoQP(home, tmp); break;
            				case 1338: DoQP(away, tmp); break;
            				default: System.out.println(":"+l);
            			}
            	}
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        
        //System.out.println(home);
        //System.out.println(away);
        //System.out.println(m);
        
        /*
        System.out.println("Home");
        Object[] tmp = home.values().toArray();
        for (int i=0; i<tmp.length; i++){
        	System.out.println(tmp[i]);
        }
        
        System.out.println("Away");
        tmp = away.values().toArray();
        for (int i=0; i<tmp.length; i++){
        	System.out.println(tmp[i]);
        }
        
        System.out.println(m);
        */
        
        return new IO_Team[]{home, away};
	}

}
