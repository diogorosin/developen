package developen.client.subject.mvc;

import java.awt.Dimension;
import java.text.ParseException;

import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.subject.search.CompanySearch;
import developen.client.subject.widget.DBCNPJField;
import developen.client.subject.widget.DBIeField;
import developen.common.framework.messenger.Messenger;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;
import developen.common.subject.i18n.CompanyTag;
import developen.common.subject.mvc.Company;

public class CompanyView extends SubjectView {


	private static final long serialVersionUID = -6308377525148332267L;
	
	private DBCNPJField cnpjField;
	
	private DBIeField ieField;
	
	
	public CompanyView(CompanyController controller){

		super(controller);

	}

	
	
	public void buildInterface() {

		setSize(new Dimension(WIDTH,HEIGHT+50));

	}

	
	public Tag getInternalFrameTitle() {

		return new CompanyTag();

	}

	
	public void setController(CompanyController controller) {

		super.setController(controller);

	}

	
	public CompanyController getController() {

		return (CompanyController) super.getController();

	}

	
	public DBRowPanel getHeaderPanel(){
		
		
		if (headerPanel == null){
			
			headerPanel = new DBRowPanel(120);

			headerPanel.add(getIdentifierField());

			headerPanel.add(getDocumentField());
			
			headerPanel.add(getIeField());

			headerPanel.add(getDenominationField());

			headerPanel.add(getFieldActive());
			
		}
	
		return headerPanel;
		
	}

	
	public DBIeField getIeField(){


		if (ieField == null) {

			ieField = new DBIeField(getController().getModel().getIe());
			
			getController().addView(ieField.getNumberField());
			
			getController().addView(ieField);
			
		}

		return ieField;

		
	}

	
	public DBCNPJField getDocumentField() {

		
		if (cnpjField==null){

			try {

				cnpjField = new DBCNPJField(CompanyController.CNPJ_PROPERTY);
				
				cnpjField.addCheckListener(this);
				
				getController().addView(cnpjField);

			} catch (ParseException e) {

				Messenger.show(e);

			}

		}
		
		return cnpjField;

		
	}

	
	public void onCheck(CheckEvent event) throws Exception {


		super.onCheck(event);
		
		if (event.getCheckable() == getDocumentField())
			
			getController().changeCNPJProperty(getDocumentField().getCNPJ());
		

	}

	
	public Search getIdentifierSearch(){


		if (identifierSearch == null){

			identifierSearch = new CompanySearch(true);
			
			identifierSearch.addSearchListener(new SearchAdapter() {
				
				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((Company)event.getSelectedRows().get(0)).getIdentifier());

				}
				
			});

		}
		
		return identifierSearch;
		

	}

	
}