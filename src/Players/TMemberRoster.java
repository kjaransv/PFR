package Players;

import java.util.HashMap;
import java.io.*;

public class TMemberRoster {
	private String FFileName;
	private HashMap<String, TMember> FMembers = new HashMap<String, TMember>();
	
	public TMemberRoster(String AFileName) throws IOException{
		// init
		FFileName = AFileName;
		
		ReadFile();
	}
	
	public TMember GetMember(String ACpr){
		return FMembers.get(ACpr);
	}
	
	public void AddMember(TMember AMember){
		FMembers.put(AMember.Identifier(), AMember);
	}
	
	private void ReadFile() throws IOException{
        BufferedReader inputStream = null;

        try {
            inputStream = new BufferedReader(new FileReader(FFileName));

            String l;
            while ((l = inputStream.readLine()) != null) {
            	//System.out.println(l);
            	String[] tmp = l.split("\t", 3);
            	TMember x = new TMember(tmp[0], tmp[1], tmp[2]);
            	FMembers.put(x.Identifier(), x);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
	}
	
	/*
	public void Save() throws IOException{
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter(new FileWriter(FFileName+"2"));

            TMember[] x = new TMember[FMembers.size()];
            FMembers.values().toArray(x);
            
            for (int i=0; i<x.length; i++){
            	TMember m = x[i];
                outputStream.println(m.Identifier()+"	"+m.FName+"	"+m.FNick);
            }
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
	}*/
}
