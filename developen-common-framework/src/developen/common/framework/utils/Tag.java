package developen.common.framework.utils;


import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;



public class Tag {


	private List<TagParam> params;

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

	
	public Tag(String tag, List<TagParam> params){

		
		setKey(tag);

		setParams(params);

		
	}


	public void setKey(String key) {

		this.key = key;

	}


	public String getKey() {

		return key;

	}


	public void setParams(List<TagParam> params){

		this.params = params;

	}


	public List<TagParam> getParams(){

		
		if (params==null)

			params = new ArrayList<TagParam>();

		return params;

		
	}


	public void addParam(TagParam param){

		getParams().add(param);

	}


	public void removeParam(TagParam param){

		getParams().remove(param);

	}


	public void clear() {

		getParams().clear();

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

		for (TagParam param : getParams())

			result = result.replace(
					
					param.getName(),
					
					String.valueOf(param.getValue()));

		return result;


	}


}