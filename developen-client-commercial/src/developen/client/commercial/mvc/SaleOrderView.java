package developen.client.commercial.mvc;

import java.awt.Dimension;

import developen.client.commercial.search.SaleOrderSearch;
import developen.client.framework.exception.ManyRecordsFoundException;
import developen.client.framework.search.Search;
import developen.client.framework.search.SearchAdapter;
import developen.client.framework.search.SearchEvent;
import developen.client.framework.widget.DBRowPanel;
import developen.client.framework.widget.DBTextField;
import developen.client.framework.widget.NeverEnabledCondition;
import developen.common.commercial.i18n.SaleOrderTag;
import developen.common.commercial.i18n.SellerTag;
import developen.common.commercial.mvc.SaleOrder;
import developen.common.commercial.mvc.SystemPerson;
import developen.common.framework.utils.Tag;
import developen.common.framework.widget.CheckEvent;

public class SaleOrderView extends OutputOrderView {


	private static final long serialVersionUID = -6224324717586281048L;

	private DBTextField seller;


	public SaleOrderController getController(){

		return (SaleOrderController) super.getController();		

	}


	public SaleOrderView(SaleOrderController controller) {

		super(controller);

	}


	public Tag getInternalFrameTitle() {

		return new SaleOrderTag();

	}

	
	public DBRowPanel getHeaderPanel(){
		
	
		DBRowPanel r = super.getHeaderPanel();
		
		r.add(getSellerField());
		
		return r;
		
		
	}
	
	
	public Search getIdentifierSearch(){


		if (identifierSearch == null){

			identifierSearch = new SaleOrderSearch();

			identifierSearch.addSearchListener(new SearchAdapter(){

				public void onSearchConfirmed(SearchEvent event) throws Exception {

					getController().changeIdentifierProperty(((SaleOrder)event.getSelectedRows().get(0)).getIdentifier());

				}

			});

		}

		return identifierSearch;


	}


	public DBTextField getSellerField() {


		if (seller == null){

//			SystemPersonSearch sellerSearch = new SystemPersonSearch(true);
//
//			sellerSearch.addSearchListener(new SearchAdapter(){
//
//				public void onSearchConfirmed(SearchEvent event) throws Exception {
//
//					getController().changeSellerProperty((SystemPerson) event.getSelectedRows().get(0));
//
//				}
//
//			});

			seller = new DBTextField(new SellerTag(), SaleOrderController.SELLER_PROPERTY);

//			seller.setSearch(sellerSearch);

			seller.setCondition(new NeverEnabledCondition());

			seller.addCheckListener(this);

			seller.setPreferredSize(new Dimension(300,24));

			getController().addView(seller);

		}

		return seller;


	}


	public void onCheck(CheckEvent event) throws Exception {

		
		super.onCheck(event);

		if (event.getCheckable() == getSellerField()){

			try{

				getController().changeSellerProperty((SystemPerson) getSellerField().getSearch().findBy());

			} catch (ManyRecordsFoundException e) {

				getSellerField().getSearch().openSearchViewWithoutReset(getDesktopPane());

			}

		} 
		

	}


}