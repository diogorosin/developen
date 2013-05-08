package developen.common.framework.utils;


import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class IconLoader {


	private static ClassLoader loader;


	public static ClassLoader getLoader() {


		if (loader==null)

			loader= IconLoader.class.getClassLoader();

		return loader;
		
		
	}

	
	public static ImageIcon getImageIcon(String resourceName){

		return new ImageIcon(getLoader().getResource(resourceName));

	}


	public static Icon getIcon(String resourceName){

		return (Icon) getImageIcon(resourceName);

	}


	public static Image getImage(String resourceName){

		return getImageIcon(resourceName).getImage(); 

	}


}