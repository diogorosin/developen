package developen.client.subject.widget;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.text.ParseException;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.subject.search.CitySearch;
import developen.common.framework.exception.InvalidValueException;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.framework.mvc.View;
import developen.common.framework.widget.CheckEvent;
import developen.common.framework.widget.CheckListener;
import developen.common.subject.i18n.AdressTag;
import developen.common.subject.i18n.CityTag;
import developen.common.subject.i18n.ComplementTag;
import developen.common.subject.i18n.ContactTag;
import developen.common.subject.i18n.DistrictTag;
import developen.common.subject.i18n.EmailTag;
import developen.common.subject.i18n.IdentifierTag;
import developen.common.subject.i18n.NumberTag;
import developen.common.subject.i18n.PlayAreaTag;
import developen.common.subject.i18n.PostalCodeTag;
import developen.common.subject.i18n.WebSiteTag;
import developen.common.subject.mvc.Address;
import developen.common.subject.mvc.City;

public class DBAddressField extends JComponent {


	private static final long serialVersionUID = -6299602364215133877L;

	private AddressController controller;

	private AddressView view;


	public DBAddressField(Address model){


		controller = new AddressController();

		view = new AddressView(controller);

		controller.addView(view);

		controller.setModel(model);

		setLayout(new BorderLayout());

		add(view, BorderLayout.CENTER);


	}


	public DBTextField getPlayAreaField(){

		return view.getPlayAreaField();

	}


	public DBTextField getNumberField(){

		return view.getNumberField();

	}


	public DBTextField getDistrictField(){

		return view.getDistrictField();

	}


	public DBPostalCodeField getPostalCodeField(){

		return view.getPostalCodeField();

	}


	public DBTextField getCityField(){

		return view.getCityField();

	}


	public DBTextField getComplementField(){

		return view.getComplementField();

	}


	public DBPhoneField getPhoneField(){

		return view.getPhoneField();

	}


	public DBTextField getEmailField(){

		return view.getEmailField();

	}


	public DBTextField getWebSiteField(){

		return view.getWebSiteField();

	}


	public void requestFocus(){


		if (view.getPlayAreaField().isFocusable())

			view.getPlayAreaField().requestFocus();


	}


	class AddressController extends Controller {


		public static final String IDENTIFIER_PROPERTY = "Identifier";

		public static final String PLAY_AREA_PROPERTY = "PlayArea";

		public static final String NUMBER_PROPERTY = "Number";

		public static final String DISTRICT_PROPERTY = "District";

		public static final String POSTAL_CODE_PROPERTY = "PostalCode";

		public static final String CITY_PROPERTY = "City";

		public static final String COMPLEMENT_PROPERTY = "Complement";

		public static final String PHONE_PROPERTY = "Phone";

		public static final String EMAIL_PROPERTY = "Email";

		public static final String WEB_SITE_PROPERTY = "WebSite";


		public Address getModel(){

			return (Address) super.getModel();

		}


		public void changeIdentifierProperty(Integer newValue) throws Exception {


			if (newValue==null)

				throw new NotNullException(new IdentifierTag());

			setModelProperty(IDENTIFIER_PROPERTY, newValue);


		}


		public void changePlayAreaProperty(String newValue) throws Exception {


			if (newValue==null || newValue.isEmpty())

				throw new NotNullException(new PlayAreaTag());

			setModelProperty(PLAY_AREA_PROPERTY, newValue);


		}


		public void changeNumberProperty(String newValue) throws Exception {


			if (newValue==null || newValue.isEmpty())

				throw new NotNullException(new NumberTag());

			setModelProperty(NUMBER_PROPERTY, newValue);


		}


		public void changeDistrictProperty(String newValue) throws Exception {


			if (newValue==null || newValue.isEmpty())

				throw new NotNullException(new DistrictTag());

			setModelProperty(DISTRICT_PROPERTY, newValue);


		}


		public void changePostalCodeProperty(Long newValue) throws Exception {


			if (newValue==null)

				throw new NotNullException(new PostalCodeTag());

			else 

				if (newValue > 0 && newValue<10000000)

					throw new InvalidValueException(newValue, new PostalCodeTag());

			setModelProperty(POSTAL_CODE_PROPERTY, newValue);


		}


		public void changeCityProperty(City newValue) throws Exception {


			if (newValue==null)

				throw new NotNullException(new CityTag());

			setModelProperty(CITY_PROPERTY, newValue);


		}


		public void changeComplementProperty(String newValue) {

			setModelProperty(COMPLEMENT_PROPERTY, newValue);

		}


		public void changePhoneProperty(Long newValue) {

			setModelProperty(PHONE_PROPERTY, newValue);

		}


		public void changeEmailProperty(String newValue) {

			setModelProperty(EMAIL_PROPERTY, newValue);

		}


		public void changeWebSiteProperty(String newValue) {

			setModelProperty(WEB_SITE_PROPERTY, newValue);

		}


	}


	class AddressView extends DBRowPanel implements View, CheckListener {


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

				playAreaField.setPreferredSize(new Dimension(400,24));

				playAreaField.addCheckListener(this);

				getController().addView(playAreaField);

			}

			return playAreaField;


		}


		public DBTextField getNumberField(){


			if (numberField==null){

				numberField = new DBTextField(new NumberTag(), AddressController.NUMBER_PROPERTY);

				numberField.setPreferredSize(new Dimension(75,24));

				numberField.addCheckListener(this);

				getController().addView(numberField);

			}

			return numberField;


		}


		public DBTextField getDistrictField(){


			if (districtField==null){

				districtField = new DBTextField(new DistrictTag(), AddressController.DISTRICT_PROPERTY);

				districtField.setPreferredSize(new Dimension(400,24));

				districtField.addCheckListener(this);

				getController().addView(districtField);

			}

			return districtField;


		}


		public DBPostalCodeField getPostalCodeField(){


			if (postalCodeField==null){

				try {

					postalCodeField = new DBPostalCodeField(AddressController.POSTAL_CODE_PROPERTY);

					postalCodeField.setPreferredSize(new Dimension(150,24));

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

				cityField.setPreferredSize(new Dimension(400,24));

				cityField.addCheckListener(this);

				cityField.setSearch(search);

				getController().addView(cityField);

			}

			return cityField;


		}


		public DBTextField getComplementField(){


			if (complementField==null){

				complementField = new DBTextField(new ComplementTag(), AddressController.COMPLEMENT_PROPERTY);

				complementField.setPreferredSize(new Dimension(400,24));

				complementField.addCheckListener(this);

				getController().addView(complementField);

			}

			return complementField;


		}


		public DBPhoneField getPhoneField(){


			if (phoneField==null){

				try {

					phoneField = new DBPhoneField(AddressController.PHONE_PROPERTY);

					phoneField.setPreferredSize(new Dimension(150,24));

					phoneField.addCheckListener(this);

					getController().addView(phoneField);

				} catch (ParseException e) {}

			}

			return phoneField;


		}


		public DBTextField getEmailField(){


			if (emailField==null){

				emailField = new DBTextField(new EmailTag(), AddressController.EMAIL_PROPERTY);

				emailField.setPreferredSize(new Dimension(400,24));

				emailField.addCheckListener(this);

				getController().addView(emailField);

			}

			return emailField;


		}


		public DBTextField getWebSiteField(){


			if (webSiteField==null){

				webSiteField = new DBTextField(new WebSiteTag(), AddressController.WEB_SITE_PROPERTY);

				webSiteField.setPreferredSize(new Dimension(400,24));

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


}