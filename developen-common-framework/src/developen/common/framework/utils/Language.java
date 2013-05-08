package developen.common.framework.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;



public class Language {


	private ClassLoader loader;

	private List<LanguageChangeListener> languageChangeListeners;

	private HashMap<String, ResourceBundle> resources;


	public void changeLanguage(Locale locale){
		
		
		getResources().clear();
		
		Locale.setDefault(locale);
		
		LanguageChangedEvent event = new LanguageChangedEvent(this);

		for (LanguageChangeListener t : getLanguageChangeListeners())

			t.languageChanged(event);
		
		
	}
	

	public synchronized void addLanguageChangeListener(LanguageChangeListener listener) {


		if(!getLanguageChangeListeners().contains(listener))

			getLanguageChangeListeners().add(listener);


	}


	public synchronized void removeLanguageChangeListener(LanguageChangeListener listener) {

		getLanguageChangeListeners().remove(listener);

	}


	public String get(String key, String resourcePackage){


		ResourceBundle rb = getResources().get(resourcePackage + "/language");

		if (rb==null){

			rb = ResourceBundle.getBundle(

					resourcePackage + "/language",

					Locale.getDefault(),

					getLoader());
			
			getResources().put(resourcePackage + "/language", rb);

		}

		return rb.getString(key);


	}


	public boolean exists(String key, String resourcePackage){


		ResourceBundle rb = getResources().get(resourcePackage + "/language");

		if (rb==null){

			rb = ResourceBundle.getBundle(

					resourcePackage + "/language", 

					Locale.getDefault(),

					getLoader());

			getResources().put(resourcePackage + "/language", rb);

		}

		return rb.containsKey(key);

		
	}


	public ClassLoader getLoader() {
		
		
		if (loader==null)
			
			loader = IconLoader.class.getClassLoader();
		
		return loader;

		
	}


	public List<LanguageChangeListener> getLanguageChangeListeners() {

		
		if (languageChangeListeners==null)
			
			languageChangeListeners = new ArrayList<LanguageChangeListener>();
		
		return languageChangeListeners;
		
		
	}


	public HashMap<String, ResourceBundle> getResources() {
		
		
		if (resources==null)
			
			resources = new HashMap<String, ResourceBundle>();
		
		return resources;

		
	}


}