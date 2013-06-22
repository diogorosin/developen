package developen.client.subject.mvc;

import java.awt.Dimension;
import java.text.ParseException;

import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.subject.search.PersonSearch;
import developen.client.subject.widget.DBCPFField;
import developen.client.subject.widget.DBRgField;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.subject.i18n.PersonTag;
import developen.common.subject.mvc.Person;

public class PersonView extends SubjectView {


	private static final long serialVersionUID = 6422871753773624833L;

	private DBCPFField cpfField;

	private DBRgField rgField;


	public PersonView(PersonController controller){

		super(controller);

	}

	
	public void buildInterface() {

		setSize(new Dimension(WIDTH,600));

	}

	
	public Tag getInternalFrameTitle() {

		return new PersonTag();

	}


	public PersonController getController() {

		return (PersonController) super.getController();

	}


	public void onCheck(CheckEvent event) throws Exception {


		super.onCheck(event);

		if (event.getCheckable() == getDocumentField())

			getController().changeCPFProperty(getDocumentField().getCPF());


	}

	
	public DBRowPanel getHeaderPanel(){
		
		
		if (headerPanel == null){
			
			headerPanel = new DBRowPanel();

			headerPanel.add(getIdentifierField());

			headerPanel.add(getDocumentField());
			
			headerPanel.add(getRgField());

			headerPanel.add(getDenominationField());

			headerPanel.add(getFieldActive());
			
		}
	
		return headerPanel;
		
	}

	
	public DBCPFField getDocumentField() {


		if (cpfField == null){

			try {

				cpfField = new DBCPFField(PersonController.CPF_PROPERTY);

				cpfField.addCheckListener(this);

				getController().addView(cpfField);

			} catch (ParseException e) {

				Messenger.show(e);

			}

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