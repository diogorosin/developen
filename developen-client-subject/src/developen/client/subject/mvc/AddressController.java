package developen.client.subject.mvc;

import developen.common.framework.exception.InvalidValueException;
import developen.common.framework.exception.NotNullException;
import developen.common.framework.mvc.Controller;
import developen.common.subject.i18n.CityTag;
import developen.common.subject.i18n.DistrictTag;
import developen.common.subject.i18n.IdentifierTag;
import developen.common.subject.i18n.NumberTag;
import developen.common.subject.i18n.PlayAreaTag;
import developen.common.subject.i18n.PostalCodeTag;
import developen.common.subject.mvc.Address;
import developen.common.subject.mvc.City;

public class AddressController extends Controller {


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