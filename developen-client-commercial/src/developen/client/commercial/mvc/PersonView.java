package developen.client.commercial.mvc;

import java.awt.Dimension;

import developen.client.commercial.search.PersonSearch;
import developen.client.commercial.widget.DBCpfField;
import developen.client.commercial.widget.DBRgField;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.common.commercial.i18n.PersonTag;
import developen.common.commercial.mvc.Person;
import developen.common.framework.utils.Tag;

public class PersonView extends SubjectView {


	private static final long serialVersionUID = 6422871753773624833L;

	private DBCpfField cpfField;

	private DBRgField rgField;

	
	public PersonView(PersonController controller){

		super(controller);

	}


	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT+50));

	}


	public Tag getInternalFrameTitle() {

		return new PersonTag();

	}


	public PersonController getController() {

		return (PersonController) super.getController();

	}


	public DBRowPanel getHeaderPanel(){


		if (headerPanel == null){

			headerPanel = new DBRowPanel(100);

			headerPanel.add(getIdentifierField());

			headerPanel.add(getCpfField());

			headerPanel.add(getRgField());

			headerPanel.add(getDenominationField());

			headerPanel.add(getFieldActive());

		}

		return headerPanel;

	}


	public DBCpfField getCpfField() {


		if (cpfField == null){

			cpfField = new DBCpfField(getController().getModel().getCpf());

			getController().addView(cpfField.getNumberField());

			getController().addView(cpfField);

		}

		return cpfField;


	}


	public DBRgField getRgField(){


		if (rgField == null) {

			rgField = new DBRgField(getController().getModel().getRg());

			getController().addView(rgField.getNumberField());

			getController().addView(rgField.getOrganizationComboBox());

			getController().addView(rgField.getStateComboBox());

			getController().addView(rgField);

		}

		return rgField;

	}


	public Search getIdentifierSearch(){


		if (identifierSearch == null){

			identifierSearch = new PersonSearch(true);

			identifierSearch.addSearchListener(new SearchAdapter() {

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Person)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}

	
}