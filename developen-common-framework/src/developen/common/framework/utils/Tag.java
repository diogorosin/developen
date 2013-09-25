package developen.common.framework.utils;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;



public class Tag {


	public static final String FIELD = "{$field}";
	
	public static final String MAXLENGHT = "{$maxLength}";
	
	public static final String MINLENGHT = "{$minLength}";
	
	public static final String FIRST_VALUE = "{$first_value}";
	
	public static final String SECOND_VALUE = "{$second_value}";


	private HashMap<String, Object> parameters;
	
	private String key;

	
	public Tag(){

		
		String key = getClass().getSimpleName();
		
		if (key.endsWith("Tag"))
			
			key = key.substring(0, key.length()-3);
		
		key = key.substring(0, 1).toLowerCase() + key.substring(1);
		
		setKey(key);

		
	}
	

	public Tag(String key){

		setKey(key);

	}

	
	public void setKey(String key) {

		this.key = key;

	}


	public String getKey() {

		return key;

	}


	public HashMap<String, Object> getParams(){

		
		if (parameters==null)

			parameters = new HashMap<String, Object>();

		return parameters;

		
	}

	
	public void put(String key, Object value){
		
		getParams().put(key, value);
		
	}
	
	
	public Object get(String key){
		
		return getParams().get(key);
		
	}
	

	public boolean hasHotKey(){

		return I18N.exists(key + ".hotKey", getClass().getPackage().getName().replace(".", "/"));

	}


	public char getHotKey(){

		return I18N.get(key + ".hotKey", getClass().getPackage().getName().replace(".", "/")).charAt(0);

	}


	public boolean hasToolTip(){

		return I18N.exists(key + ".toolTip", getClass().getPackage().getName().replace(".", "/"));

	}


	public String getToolTip(){

		return I18N.get(key + ".toolTip", getClass().getPackage().getName().replace(".", "/"));

	}


	public boolean hasSmallIcon(){

		return I18N.exists(key + ".smallIcon", getClass().getPackage().getName().replace(".", "/"));

	}


	public ImageIcon getSmallIcon(){

		
		String myImageIcon = getClass().getPackage().getName().replace(".", "/");
		
		myImageIcon = myImageIcon.replace("/i18n", "/icon");
		
		myImageIcon += "/" + I18N.get(key + ".smallIcon", getClass().getPackage().getName().replace(".", "/")).trim();
		
		return IconLoader.getImageIcon(myImageIcon);
		

	}

	
	public boolean hasLargeIcon(){

		return I18N.exists(key + ".largeIcon", getClass().getPackage().getName().replace(".", "/"));

	}


	public ImageIcon getLargeIcon(){

		
		String myImageIcon = getClass().getPackage().getName().replace(".", "/");
		
		myImageIcon = myImageIcon.replace("/i18n", "/icon");
		
		myImageIcon += "/" + I18N.get(key + ".largeIcon", getClass().getPackage().getName().replace(".", "/")).trim();
		
		return IconLoader.getImageIcon(myImageIcon);
		

	}

	
	public String toString() {


		String result = I18N.get(getKey(), getClass().getPackage().getName().replace(".", "/"));

		Iterator<Entry<String, Object>> it = getParams().entrySet().iterator();
		
	    while (it.hasNext()) {
	    	
	        Map.Entry<String, Object> pairs = it.next();
	        
	        result = result.replace(
	        		
	        		pairs.getKey(), 
	        		
	        		String.valueOf(pairs.getValue()));
	        
	    }		
		
		return result;


	}


}