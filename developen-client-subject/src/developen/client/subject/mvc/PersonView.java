package developen.client.subject.mvc;

import java.text.ParseException;

import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.subject.search.PersonSearch;
import developen.client.subject.widget.DBCPFField;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.subject.i18n.PersonTag;
import developen.common.subject.mvc.Person;

public class PersonView extends SubjectView {

	
	private static final long serialVersionUID = 6422871753773624833L;
	
	private DBCPFField cpfField;

	
	public PersonView(PersonController controller){

		super(controller);

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