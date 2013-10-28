package InputOutput;

import java.io.*;

public class FindCertainExtension {
	public static String[] listFile(String folder, String ext) {
		GenericExtFilter filter = new FindCertainExtension().new GenericExtFilter(ext);
 
		File dir = new File(folder);
 
		if(dir.isDirectory()==false){
			return null;
		}
 
		// list out all the file name and filter by the extension
		String[] list = dir.list(filter);
 
		for (int i=0; i<list.length; i++){
			list[i] = folder+File.separator+list[i];
		}
		
		return list;
	}
 
	// inner class, generic extension filter
	public class GenericExtFilter implements FilenameFilter {
 
		private String ext;
 
		public GenericExtFilter(String ext) {
			this.ext = ext;
		}
 
		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}
	}
}
