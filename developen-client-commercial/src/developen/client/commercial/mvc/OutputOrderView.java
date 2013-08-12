package developen.client.commercial.mvc;

import java.awt.Dimension;

import developen.client.commercial.search.OutputMacroSearch;
import developen.client.commercial.search.OutputOrderSearch;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.OutputMacroTag;
import developen.common.commercial.i18n.OutputOrderTag;
import developen.common.commercial.mvc.OutputMacro;
import developen.common.commercial.mvc.OutputOrder;
import developen.common.framework.utils.Tag;

public class OutputOrderView extends OrderView {


	private static final long serialVersionUID = -6224324717586281048L;

	private DBTextField outputMacroField;


	public OutputOrderView(OutputOrderController controller) {

		super(controller);

	}


	public OutputOrderController getController(){

		return (OutputOrderController) super.getController();		

	}

	
	public DBRowPanel getHeaderPanel(){

		
		DBRowPanel s = super.getHeaderPanel();
		
		s.add(getOutputMacroField());
		
		return s;

		
	}
	

	public Tag getInternalFrameTitle() {

		return new OutputOrderTag();

	}

	
	public DBTextField getFromField() {


		DBTextField fromField = super.getFromField();
		
		fromField.setCondition(new NeverEnabledCondition());

		return fromField;

		
	}
	
	
	public DBTextField getOutputMacroField() {


		if (outputMacroField == null){

			OutputMacroSearch toSearch = new OutputMacroSearch(true);

			toSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeOutputMacroProperty((OutputMacro) event.getSelectedRows().get(0));   

				}

			});

			outputMacroField = new DBTextField(new OutputMacroTag(), OutputOrderController.OUTPUT_MACRO_PROPERTY);

			outputMacroField.setSearch(toSearch);

			outputMacroField.addCheckListener(this);

			outputMacroField.setPreferredSize(new Dimension(400,24));

			getController().addView(outputMacroField);

		}

		return outputMacroField;


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