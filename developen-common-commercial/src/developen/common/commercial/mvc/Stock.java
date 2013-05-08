package developen.common.commercial.mvc;

import developen.common.commercial.mvc.StockPK;
import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class Stock extends Model {

	
	private static final long serialVersionUID = -7886747243098919249L;

	@Identifier
	private StockPK identifier;
	
	
	public StockPK getIdentifier() {
		
		return identifier;
		
	}


	public void setIdentifier(StockPK newValue) {
		

		StockPK oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);

		
	}

	
}