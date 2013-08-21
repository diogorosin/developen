package developen.common.commercial.mvc;

import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;

@Table
public class SaleOrder extends OutputOrder {


	private static final long serialVersionUID = 6317620587785600752L;
	
	@ManyToOne
	private SystemPerson seller;

	
	public void setSeller(SystemPerson newValue) {
		
		
		SystemPerson oldValue = this.seller;

		this.seller = newValue;

		firePropertyChange("Seller", oldValue, newValue);


	}
	
	
	public SystemPerson getSeller() {
		
		return seller;		
		
	}

	
}