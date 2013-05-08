package developen.common.engineer.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;

@Embeddable
public class ProductPartPK extends Model {
	
	
	private static final long serialVersionUID = -1129451441319776843L;

	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Product part;

	
	public Product getProduct() {
		
		return product;
		
	}

	
	public void setProduct(Product newValue) {
		

		Product oldValue = this.product;
		
		this.product = newValue;
		
		firePropertyChange("Product", oldValue, newValue);
		

	}

	
	public Product getPart() {
		
		return part;
		
	}

	
	public void setPart(Product newValue) {
		

		Product oldValue = this.part;
		
		this.part = newValue;
		
		firePropertyChange("Part", oldValue, newValue);

		
	}


	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((part == null) ? 0 : part.hashCode());
		
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		
		return result;
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		ProductPartPK other = (ProductPartPK) obj;
		
		if (part == null) {
			
			if (other.part != null)
				
				return false;
			
		} else if (!part.equals(other.part))
			
			return false;
		
		if (product == null) {
			
			if (other.product != null)
				
				return false;
			
		} else if (!product.equals(other.product))
			
			return false;
		
		return true;
		
		
	}


}