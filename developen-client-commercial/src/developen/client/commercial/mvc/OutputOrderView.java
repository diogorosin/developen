package developen.client.commercial.mvc;

import developen.client.commercial.search.OutputOrderSearch;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.OutputOrderTag;
import developen.common.commercial.mvc.OutputOrder;
import developen.common.framework.utils.Tag;

public class OutputOrderView extends OrderView {


	private static final long serialVersionUID = -6224324717586281048L;


	public OutputOrderView(OutputOrderController controller) {

		super(controller);

	}


	public OutputOrderController getController(){

		return (OutputOrderController) super.getController();		

	}

	
	public Tag getInternalFrameTitle() {

		return new OutputOrderTag();

	}

	
	public DBTextField getFromField() {


		DBTextField fromField = super.getFromField();
		
		fromField.setCondition(new NeverEnabledCondition());

		return fromField;

		
	}
	
	
	public Search getIdentifierSearch(){


		if (identifierSearch == null){

			identifierSearch = new OutputOrderSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((OutputOrder)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;
		

	}


}