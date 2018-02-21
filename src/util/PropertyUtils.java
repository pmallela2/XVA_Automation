package util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyUtils {
	private static ResourceBundle configBundleXVA = null;

	static {
		try {
			File file = new File(System.getProperty("user.dir"));
			URL[] urls = { file.toURI().toURL() };
			ClassLoader loader = new URLClassLoader(urls);
			configBundleXVA = ResourceBundle.getBundle("xva",
					Locale.getDefault(), loader);
			if (configBundleXVA == null) {
				configBundleXVA = ResourceBundle.getBundle("XVA",
						Locale.getDefault(), loader);
			}
		} catch (Exception exception) {
			// GeneralUtil.logger.error("Please provide the environment properties (ipds.properties) file in the root directory.");
			System.out
					.println("Please provide the environment properties (xva.properties) file in the root directory.");
			System.exit(0);
			// exception.printStackTrace();
		}
	}

	/*
	 * public static String getConfigMessageold(String key) { return ""; try{
	 * if(key==null || key.trim().length()==0) {
	 * 
	 * }else{ return configBundleIPDS.getString(key); } }catch(Exception
	 * exception){
	 * 
	 * System.out.println("Key doesn't exist"+key); return ""; System.exit(0);
	 * //exception.printStackTrace(); } }
	 */

	public static String getConfigMessage(String key) {
		String strKey = "";
		try {
			if (!(key == null || key.trim().length() == 0)) {
				strKey = configBundleXVA.getString(key);
			}
		} catch (Exception exception) {
			System.out.println("Key doesn't exist " + key);
			// GeneralUtil.logger.error("Key doesn't exist"+key);
			System.exit(0);
		}
		return strKey;
	}
}
