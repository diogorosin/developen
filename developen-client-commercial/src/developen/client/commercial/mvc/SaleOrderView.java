package developen.client.commercial.mvc;

import developen.common.commercial.i18n.SaleOrderTag;
import developen.common.framework.utils.Tag;

public class SaleOrderView extends OrderView {


	private static final long serialVersionUID = -6224324717586281048L;

//	private DBTextField customerField;
//
//	private DBTextField systemCompanyField;


	public SaleOrderView(SaleOrderController controller) {

		super(controller);

	}


	public Tag getInternalFrameTitle() {

		return new SaleOrderTag();

	}


//	public DBTextField getFromField() {
//
//
//		if (customerField == null){
//
//			SubjectSearch fromSearch = new SubjectSearch(true);
//
//			fromSearch.addSearchListener(new SearchAdapter(){
//
//				public void onSearchConfirmed(SearchEvent event) throws Exception {
//
//					getController().changeFromProperty((Subject) event.getSelectedRows().get(0));
//
//				}
//
//			});
//
//			customerField = new DBTextField(new CustomerTag(), OrderController.FROM_PROPERTY);
//
//			customerField.setSearch(fromSearch);
//
//			customerField.addCheckListener(this);
//
//			customerField.setColumns(30);
//
//			getController().addView(customerField);
//
//		}
//
//		return customerField;
//
//
//	}
//
//
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