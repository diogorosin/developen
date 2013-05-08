package developen.client.engineer.mvc;

import developen.client.framework.mvc.SearchController;
import developen.common.engineer.i18n.ProductTag;
import developen.common.framework.utils.Tag;

public class ProductSearchView extends ProgenySearchView {


	private static final long serialVersionUID = 46989562396432704L;

	
	public ProductSearchView(SearchController controller) {

		super(controller);

	}

	public Tag getInternalFrameTitle() {

		return new ProductTag(); 

	}


}