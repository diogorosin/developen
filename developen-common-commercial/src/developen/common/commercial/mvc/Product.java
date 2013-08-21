package developen.common.commercial.mvc;


import java.util.List;

import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.Table;

@Table
public class Product extends Progeny {


	private static final long serialVersionUID = 3256648280907413897L;
	
	@ManyToOne
	private ProductType productType;
	
	@OneToMany(mappedBy="product")
	private List<ProductPart> parts;
	

	public ProductType getProductType() {

		return productType;
		
	}

	
	public void setProductType(ProductType newValue) {
		
		
		ProductType oldValue = this.productType;
		
		this.productType = newValue;
		
		firePropertyChange("ProductType", oldValue, newValue);

		
	}

	
	public List<ProductPart> getParts() {
		
		return parts;
		
	}

	
	public void setParts(List<ProductPart> newValue) {
	
		
		List<ProductPart> oldValue = this.parts;
		
		this.parts = newValue;
		
		firePropertyChange("Parts", oldValue, newValue);
		
		
	}
	
	
}