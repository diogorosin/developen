package developen.client.commercial.mvc;

import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.client.subject.search.SubjectSearch;
import developen.common.commercial.i18n.SaleOrderTag;
import developen.common.engineer.i18n.FromTag;
import developen.common.framework.utils.Tag;
import developen.common.subject.mvc.Subject;

public class SaleOrderView extends OrderView {


	private static final long serialVersionUID = -6224324717586281048L;


	public SaleOrderView(SaleOrderController controller) {

		super(controller);

	}


	public Tag getInternalFrameTitle() {

		return new SaleOrderTag();

	}


	public DBTextField getFromField() {


		if (fromField == null){

			SubjectSearch fromSearch = new SubjectSearch(true);

			fromSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeFromProperty((Subject) event.getSelectedRows().get(0));

				}

			});

			fromField = new DBTextField(new FromTag(), OrderController.FROM_PROPERTY);

			fromField.setCondition(new NeverEnabledCondition());

			fromField.setSearch(fromSearch);

			fromField.addCheckListener(this);

			fromField.setColumns(30);
			
			getController().addView(fromField);

		}

		return fromField;


	}

	
//	public DBTextField getToField() {
//
//
//		if (systemCompanyField == null){
//
//			SystemCompanySearch toSearch = new SystemCompanySearch(true);
//
//			toSearch.addSearchListener(new SearchAdapter(){
//
//				public void onSearchConfirmed(SearchEvent event) throws Exception {
//
//					getController().changeToProperty((Subject) event.getSelectedRows().get(0));
//
//				}
//
//			});
//
//			systemCompanyField = new DBTextField(new SystemCompanyTag(), OrderController.TO_PROPERTY);
//
//			systemCompanyField.setSearch(toSearch);
//
//			systemCompanyField.setFixedValue(true);
//
//			systemCompanyField.setCondition(new NeverEnabledCondition());
//
//			systemCompanyField.addCheckListener(this);
//
//			systemCompanyField.setColumns(30);
//
//			getController().addView(systemCompanyField);
//
//		}
//
//		return systemCompanyField;
//
//
//	}


//	public void modelPropertyChanged(PropertyChangeEvent evt) {
//
//
//		super.modelPropertyChanged(evt);
//
//		if (evt.getPropertyName().equals("ModelState")){
//
//			EntryState newValue = (EntryState) evt.getNewValue();
//
//			if (newValue.equals(EntryState.INCLUDING)){
//
//				Component component = this;
//
//				while (component != null){
//
//					if (component instanceof AuditProvider){
//
//						try {
//							
//							getController().changeToProperty((Subject)((AuditProvider)component).getOrganization());
//							
//						} catch (Exception e) {
//
//							e.printStackTrace();
//							
//						}
//
//						break;
//
//					}
//
//					component = component.getParent();
//
//				}
//
//			} 
//
//		}
//
//
//	}


}