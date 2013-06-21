package developen.client.subject.mvc;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.text.ParseException;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowLayout;
import developen.client.framework.widget.DBTextField;
import developen.client.subject.search.CitySearch;
import developen.client.subject.widget.DBPhoneField;
import developen.client.subject.widget.DBPostalCodeField;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.subject.i18n.AdressTag;
import developen.common.subject.i18n.CityTag;
import developen.common.subject.i18n.ComplementTag;
import developen.common.subject.i18n.ContactTag;
import developen.common.subject.i18n.DistrictTag;
import developen.common.subject.i18n.EmailTag;
import developen.common.subject.i18n.NumberTag;
import developen.common.subject.i18n.PlayAreaTag;
import developen.common.subject.i18n.WebSiteTag;
import developen.common.subject.mvc.City;

public class AddressView extends DBRowLayout implements View, CheckListener {


	private static final long serialVersionUID = -7233755248665259252L;
	
	private DBTextField playAreaField;
	
	private DBTextField numberField;
	
	private DBTextField districtField;
	
	private DBTextField complementField;
	
	private DBTextField cityField;
	
	private DBPostalCodeField postalCodeField;
	
	private DBPhoneField phoneField;
	
	private DBTextField emailField;
	
	private DBTextField webSiteField;

	private AddressController controller;
	
	
	public AddressView(AddressController controller){

	
		super();
		
		setController(controller);
		
		addSeparator(new AdressTag());
		
		add(getPlayAreaField());
		
		add(getNumberField());
		
		add(getDistrictField());
		
		add(getPostalCodeField());
		
		add(getCityField());
		
		add(getComplementField());
		
		addSeparator(new ContactTag());
		
		add(getPhoneField());
		
		add(getEmailField());
		
		add(getWebSiteField());

		
	}

	
	public AddressController getController() {

		return controller;

	}

	
	public void setController(AddressController controller) {

		this.controller = controller;

	}

	
	public JComponent getComponentAtTop() {

		return getPlayAreaField();

	}

	
	public void onCheck(CheckEvent event) throws Exception {

		
		if (event.getCheckable() == getPlayAreaField())
			
			getController().changePlayAreaProperty(getPlayAreaField().getText());
		
		else
			
			if (event.getCheckable() == getNumberField())
				
				getController().changeNumberProperty(getNumberField().getText());
		
			else
				
				if (event.getCheckable() == getDistrictField())
					
					getController().changeDistrictProperty(getDistrictField().getText());
		
				else
					
					if (event.getCheckable() == getCityField())
						
						try{
							
							getController().changeCityProperty((City) getCityField().getSearch().findBy());
							
						} catch (ManyRecordsFoundException e) {
							
							getCityField().getSearch().openSearchViewWithoutReset(getDesktop());
							
						}
		
					else
						
						if (event.getCheckable() == getPostalCodeField())
							
							getController().changePostalCodeProperty(getPostalCodeField().getCEP());
		
						else
							
							if (event.getCheckable() == getComplementField())
								
								getController().changeComplementProperty(getComplementField().getText());
		
							else
								
								if (event.getCheckable() == getPhoneField())
									
									getController().changePhoneProperty(getPhoneField().getPhone());
		
								else
									
									if (event.getCheckable() == getEmailField())
										
										getController().changeEmailProperty(getEmailField().getText());
		
									else
										
										if (event.getCheckable() == getWebSiteField())
											
											getController().changeWebSiteProperty(getWebSiteField().getText());
		

	}

	
	public DBTextField getPlayAreaField(){

		
		if (playAreaField==null){

			playAreaField = new DBTextField(new PlayAreaTag(), AddressController.PLAY_AREA_PROPERTY);
			
			playAreaField.setColumns(30);
			
			playAreaField.addCheckListener(this);
			
			getController().addView(playAreaField);

		}
		
		return playAreaField;
		

	}

	
	public DBTextField getNumberField(){

		
		if (numberField==null){

			numberField = new DBTextField(new NumberTag(), AddressController.NUMBER_PROPERTY);
			
			numberField.setColumns(10);
			
			numberField.addCheckListener(this);
			
			getController().addView(numberField);

		}
		
		return numberField;
		

	}

	
	public DBTextField getDistrictField(){

		
		if (districtField==null){

			
			districtField = new DBTextField(new DistrictTag(), AddressController.DISTRICT_PROPERTY);
			
			districtField.setColumns(30);
			
			districtField.addCheckListener(this);
			
			getController().addView(districtField);
			

		}
		
		return districtField;

		
	}

	
	public DBPostalCodeField getPostalCodeField(){

		
		if (postalCodeField==null){

			try {

				postalCodeField = new DBPostalCodeField(AddressController.POSTAL_CODE_PROPERTY);
				
				postalCodeField.setColumns(10);
				
				postalCodeField.addCheckListener(this);
				
				getController().addView(postalCodeField);

			} catch (ParseException e) {}

		}
		
		return postalCodeField;

		
	}

	
	public DBTextField getCityField(){

	
		if (cityField==null){			

			CitySearch search = new CitySearch();
			
			search.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {
					
					getController().changeCityProperty((City) event.getSelectedRows().get(0));
					
				}
				
			});

			cityField = new DBTextField(new CityTag(), AddressController.CITY_PROPERTY);
			
			cityField.setColumns(30);
			
			cityField.addCheckListener(this);
			
			cityField.setSearch(search);
			
			getController().addView(cityField);

		}
		
		return cityField;
		

	}

	
	public DBTextField getComplementField(){

		
		if (complementField==null){

			complementField = new DBTextField(new ComplementTag(), AddressController.COMPLEMENT_PROPERTY);
			
			complementField.setColumns(30);
			
			complementField.addCheckListener(this);
			
			getController().addView(complementField);

		}
		
		return complementField;
		

	}

	
	public DBPhoneField getPhoneField(){

		
		if (phoneField==null){

			try {

				phoneField = new DBPhoneField(AddressController.PHONE_PROPERTY);
				
				phoneField.setColumns(10);
				
				phoneField.addCheckListener(this);
				
				getController().addView(phoneField);

			} catch (ParseException e) {}

		}
		
		return phoneField;
		

	}

	
	public DBTextField getEmailField(){

		
		if (emailField==null){

			emailField = new DBTextField(new EmailTag(), AddressController.EMAIL_PROPERTY);
			
			emailField.setColumns(30);
			
			emailField.addCheckListener(this);
			
			getController().addView(emailField);

		}
		
		return emailField;
		

	}
	

	public DBTextField getWebSiteField(){

		
		if (webSiteField==null){

			webSiteField = new DBTextField(new WebSiteTag(), AddressController.WEB_SITE_PROPERTY);
			
			webSiteField.setColumns(30);
			
			webSiteField.addCheckListener(this);
			
			getController().addView(webSiteField);

		}
		
		return webSiteField;

		
	}
	

	public JDesktopPane getDesktop(){

		
		Component c = this;

		while (c.getParent() != null) {

			c = c.getParent();

			if (c instanceof JInternalFrame)

				break;

		}

		return c instanceof JInternalFrame ? ((JInternalFrame)c).getDesktopPane() : null;
		

	}


	public void modelPropertyChanged(PropertyChangeEvent evt) {}

	
}