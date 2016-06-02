package bs.nina.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import util.JDBC;

public class LoadFileContext {
	public static void readFile() throws IOException {
		JDBC jdbc = new JDBC();
		List<String> fileList = jdbc.getAllFile();
		BufferedReader br = null;
		for (int i = 0; i < fileList.size(); i++) {
			String file = fileList.get(i) + ".drl";
			File f = new File(file);
			br = new BufferedReader(new FileReader(f));
			String line = null;
			while ((line = br.readLine()) != null) {
				{
					System.out.println(line);
				}
			}
			br.close();
		}
	}
}
