package developen.common.framework.utils;


import java.util.Locale;

public class I18N {

	
	private static Language language;

	
	public static void setLanguage(Locale locale){
		
		getLanguage().changeLanguage(locale);
		
	}
	
	
	public static Language getLanguage() {

		
		if (language==null)
			
			language = new Language();
		
		return language;

		
	}
	
	
	public static String get(String key, String resource){
		
		return getLanguage().get(key, resource);
		
	}

	
	public static boolean exists(String key, String resource){
		
		return getLanguage().exists(key, resource);
		
	}
	
	
	public static void addLanguageChangeListener(LanguageChangeListener listener){
		
		getLanguage().addLanguageChangeListener(listener);
		
	}


	public static void removeLanguageChangeListener(LanguageChangeListener listener){
		
		getLanguage().removeLanguageChangeListener(listener);
		
	}

	
}