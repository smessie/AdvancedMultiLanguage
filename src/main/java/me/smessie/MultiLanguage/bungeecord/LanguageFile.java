package me.smessie.MultiLanguage.bungeecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

public class LanguageFile {

	public static void createFile(String dir, String fileName) {
		
        if (!Main.plugin.getDataFolder().exists()) {
        	Main.plugin.getDataFolder().mkdir();
        }
       
		try {
			File file = new File(Main.plugin.getDataFolder() + dir, fileName);
			if (!file.exists()){
				file.createNewFile();
	            try (InputStream is = Main.plugin.getResourceAsStream(fileName);
	                 OutputStream os = new FileOutputStream(file)) {
	                ByteStreams.copy(is, os);
	            }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}