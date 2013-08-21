package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;

@Embeddable
public class StockPK extends Model {


	private static final long serialVersionUID = 7848990418740784705L;
	
	@ManyToOne
	private Product product;

	@ManyToOne
	private SystemCompany systemCompany;
	

	public Product getProduct() {
		
		return product;
		
	}

	
	public void setProduct(Product newValue) {
		

		Product oldValue = this.product;
		
		this.product = newValue;
		
		firePropertyChange("Product", oldValue, newValue);
		

	}


	public SystemCompany getSystemCompany() {
		
		return systemCompany;
		
	}
	
	
	public void setSystemCompany(SystemCompany newValue) {
		
		
		SystemCompany oldValue = this.systemCompany;
		
		this.systemCompany = newValue;
		
		firePropertyChange("SystemCompany", oldValue, newValue);

		
	}


	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		
		result = prime * result
				
				+ ((systemCompany == null) ? 0 : systemCompany.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		StockPK other = (StockPK) obj;
		
		if (product == null) {
			
			if (other.product != null)
				
				return false;
			
		} else if (!product.equals(other.product))
			
			return false;
		
		if (systemCompany == null) {
			
			if (other.systemCompany != null)
				
				return false;
			
		} else if (!systemCompany.equals(other.systemCompany))
			
			return false;
		
		return true;
		
		
	}


}