package PFR_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import InputOutput.FindCertainExtension;
import InputOutput.IO_Match;
import InputOutput.IO_Team;
import InputOutput.TScore;
import Players.TMember;
import Players.TMemberRoster;
import Players.TRacket;
import Players.TSeason;
import Players.TTeam;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class TRunner {

	private JFrame frame;

	private TMemberRoster FRoster;
	private TSeason FSeason;
	
	private HashMap<String, TScore> FPlayerScore;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TRunner window = new TRunner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private HashMap<String, IO_Team> FTeamData = new HashMap<String, IO_Team>();
	private void Add(TRacket ARacket, IO_Team ATeam){
		TTeam team = ARacket.FTeams.get(Integer.parseInt(ATeam.GetTeam()));
		IO_Team t = FTeamData.get(team.FID);
		
		if (t==null){
			FTeamData.put(team.FID, ATeam);
		} else {
			t.Add(ATeam);
		}
	}
	
	private void AddRacketScore(TRacket ARacket) throws IOException{
		String[] files = FindCertainExtension.listFile("data\\"+ARacket.FID, ".match");
		for (int i=0; i<files.length; i++){
			IO_Team[] t = IO_Match.ReadFile(files[i], ARacket);
			Add(ARacket, t[0]);
			Add(ARacket, t[1]);
		}
	}
		
	private void SumPlayerScore(){
		FPlayerScore = new HashMap<String, TScore>();

		Iterator<Entry<String, IO_Team>> it = FTeamData.entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<String, IO_Team> pair = it.next();

	    	TScore[] s = pair.getValue().GetScore();
			for (int i=0; i<s.length; i++){
				TScore sc = FPlayerScore.get(s[i].GetPlayer());
				if (sc == null){
					String player;
					String key;
					String team = FSeason.GetTeam(pair.getKey()).FName;
					TMember m = FRoster.GetMember(s[i].GetPlayer());
					if (m==null){
						if (s[i].GetPlayer().equals("") || s[i].GetPlayer().equals("0")) continue;
						player = "?"+s[i].GetPlayer();
						key = player+","+team;
						sc = FPlayerScore.get(key);
					} else {
						if (m.Identifier().equals("130684-3009")) continue;
						player = m.FName;
						key = s[i].GetPlayer();
					}
					
					if (sc == null){
						sc = new TScore(player, team);
						FPlayerScore.put(key, sc);
					}
				}
				sc.Add(s[i]);
			}
	    }
	}
	
	private String ExportTeamQP(){
		TScore[] list = new TScore[FTeamData.size()];
		// remake
		int i = 0;
		Iterator<Entry<String, IO_Team>> it = FTeamData.entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<String, IO_Team> pair = it.next();
	    	pair.getValue().Update();
	    	list[i] = new TScore("", FSeason.GetTeam(pair.getKey()).FName);
	    	list[i].Add(pair.getValue());
	    	i++;
	    }
	    // sort
	    Arrays.sort(list);
	    // export
		String result = "<table style=\"BORDER-COLLAPSE: collapse\" border=\"1\" cellspacing=\"4\" cellpadding=\"4\"><tbody>";
		result+=
				"<th>Nr.</th>"+
				"<th>Lið / Team</th>"+
				"<th>Qp</th>"+
				"<th>Won</th>"+
				"<th>Lost</th>"+
				"<th>180</th>"+
				"<th>Round 9</th>"+
				"<th>7 Hit</th>"+
				"<th>Innskot</th>"+
				"<th>Útskot</th>";
		for (i=0; i<list.length; i++){
			if (i%2==0){
				result+= "<tr bgcolor=\"#DBE5F1\">";
			} else {
				result+= "<tr>";
			}
			
			result+=
					"<td>"+(i+1)+"</td>"+
					"<td>"+list[i].GetTeam()+"</td>"+
					"<td>"+list[i].GetQP()+"</td>"+
					"<td>"+list[i].GetWin()+"</td>"+
					"<td>"+list[i].GetLoss()+"</td>"+
					"<td>"+list[i].Get180()+"</td>"+
					"<td>"+list[i].GetR9()+"</td>"+
					"<td>"+list[i].Get7H()+"</td>"+
					"<td>"+list[i].GetIn()+"</td>"+
					"<td>"+list[i].GetOut()+"</td>";
		}
	    return result+"</tbody></table>";
	}
	
	private String ExportPlayerQP(){
		TScore[] list = new TScore[0];
	    list = FPlayerScore.values().toArray(list);
	    // sort
	    Arrays.sort(list);
	    // export
		String result = "<table style=\"BORDER-COLLAPSE: collapse\" border=\"1\" cellspacing=\"4\" cellpadding=\"4\"><tbody>";
		result+=
				"<th>Nr.</th>"+
				"<th>Nafn / Player</th>"+
				"<th>Lið / Team</th>"+
				"<th>Qp</th>"+
				"<th>Won</th>"+
				"<th>Lost</th>"+
				"<th>180</th>"+
				"<th>Round 9</th>"+
				"<th>7 Hit</th>"+
				"<th>Innskot</th>"+
				"<th>Útskot</th>";
		for (int i=0; i<list.length; i++){
			if (i%2==0){
				result+= "<tr bgcolor=\"#DBE5F1\">";
			} else {
				result+= "<tr>";
			}
			
			result+=
					"<td>"+(i+1)+"</td>"+
					"<td>"+list[i].GetPlayer()+"</td>"+
					"<td>"+list[i].GetTeam()+"</td>"+
					"<td>"+list[i].GetQP()+"</td>"+
					"<td>"+list[i].GetWin()+"</td>"+
					"<td>"+list[i].GetLoss()+"</td>"+
					"<td>"+list[i].Get180()+"</td>"+
					"<td>"+list[i].GetR9()+"</td>"+
					"<td>"+list[i].Get7H()+"</td>"+
					"<td>"+list[i].GetIn()+"</td>"+
					"<td>"+list[i].GetOut()+"</td>";
		}
	    return result+"</tbody></table>";
	}

	private void ExportTeamStat(){
		Iterator<Entry<String, IO_Team>> it = FTeamData.entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<String, IO_Team> pair = it.next();

	    	// get the team
	    	TTeam team = FSeason.GetTeam(pair.getKey());
	    	List<TMember> m = team.FMembers;
	    	
	    	System.out.println();
	    	System.out.println(team.FName);
	    	
	    	// output
	    	String[] out = new String[14];
	    	
	    	out[0] = "<th>"+team.FName+"</th>";
	    	out[1] = "<td>301</td>";
	    	out[2] = "<td>501 Singles</td>";
	    	out[3] = "<td>Krikket</td>";
	    	out[4] = "<td>501 Doubles</td>";
	    	out[5] = "<td>13</td>";
	    	out[6] = "<td>Total</td>";
	    	
	    	out[8] = "<td>180</td>";
	    	out[9] = "<td>Innskot</td>";
	    	out[10] = "<td>Útskot</td>";
	    	out[11] = "<td>QP's</td>";
	    	out[12] = "<td>Round of 9</td>";
	    	out[13] = "<td>7 Hit</td>";
	    	
	    	// get values for members
	    	int count = 1;
	    	for (int i=0; i<m.size(); i++){
	    		TScore s = FPlayerScore.get(m.get(i).Identifier());
	    		if (s!=null){
	    			out[0]+= "<th>"+s.GetPlayer()+"</th>";
		    		out[1]+= "<td>"+s.Get301()+"</td>";
		    		out[2]+= "<td>"+s.Get501()+"</td>";
		    		out[3]+= "<td>"+s.GetKrikket()+"</td>";
		    		out[4]+= "<td>"+s.Get501_2()+"</td>";
		    		out[5]+= "<td>"+s.Get13()+"</td>";
		    		out[6]+= "<td>"+s.GetTotal()+"</td>";
		    		
		    		out[8]+= "<td>"+s.Get180()+"</td>";
		    		out[9]+= "<td>"+s.GetIn()+"</td>";
		    		out[10]+= "<td>"+s.GetOut()+"</td>";
		    		out[11]+= "<td>"+s.GetQP()+"</td>";
		    		out[12]+= "<td>"+s.GetR9()+"</td>";
		    		out[13]+= "<td>"+s.Get7H()+"</td>";
		    		
		    		count++;
	    		}
	    	}
	    	
	    	out[7] = "<td colspan=\""+count+"\"><hr /></td>";
	    	
	    	System.out.print("<table style=\"BORDER-COLLAPSE: collapse\" border=\"1\" cellspacing=\"4\" cellpadding=\"4\"><tbody>");
	    	for (int i=0; i<out.length; i++){
				if (i%2==1){
					System.out.print("<tr bgcolor=\"#DBE5F1\">");
				} else {
					System.out.print("<tr>");
				}
	    		System.out.print(out[i]+"</tr>");
	    	}
	    	System.out.println("</tbody></table>");
	    }
	}
	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public TRunner() throws IOException {
		initialize();
	       
		FRoster = new TMemberRoster("data/Members.dat");
		FSeason = new TSeason(FRoster, "data/2013-14.season");
		
		TRacket[] rackets = FSeason.GetRackets();
		for (TRacket racket: rackets){
			AddRacketScore(racket);
			
			System.out.println();
			System.out.println("Racket "+racket.FName);
			System.out.println(racket);
		}
		
		SumPlayerScore();
		
		//print();
		System.out.println();
		System.out.println("TeamQP");
		System.out.println(ExportTeamQP());
		
		System.out.println();
		System.out.println("PlayerQP");
		System.out.println(ExportPlayerQP());
		
		ExportTeamStat();
		
		//IO_Team t = FTeamData.get("1");
		//TScore s = t.Get("220882-3149");
		//TScore s = t.Get("130684-3009");
		//TScore s = t.Get("190785-2979");
		
		//System.out.println(s);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Missing check for dirty flag and do cleanup, and add listener on exit
				frame.dispose();
			}
		});
		mnNewMenu.add(mntmExit);
		
		JMenu mnNewMenu_1 = new JMenu("Members");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mnNewMenu_1.add(mntmEdit);
	}

}
