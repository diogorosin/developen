package developen.common.commercial.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Embeddable;
import developen.common.persistence.annotation.ManyToOne;

@Embeddable
public class OrderItemPK extends Model {
	
	
	private static final long serialVersionUID = -492128968425624214L;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Progeny progeny;


	public Order getOrder() {
		
		return order;
		
	}

	
	public void setOrder(Order newValue) {
		
		
		Order oldValue = this.order;
		
		this.order = newValue;
		
		firePropertyChange("Order", oldValue, newValue);

		
	}

	
	public Progeny getProgeny() {
		
		return progeny;
		
	}

	
	public void setProgeny(Progeny newValue) {

		
		Progeny oldValue = this.progeny;
		
		this.progeny = newValue;
		
		firePropertyChange("Progeny", oldValue, newValue);

		
	}

	
	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		
		result = prime * result + ((progeny == null) ? 0 : progeny.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		OrderItemPK other = (OrderItemPK) obj;
		
		if (order == null) {
			
			if (other.order != null)
				
				return false;
			
		} else if (!order.equals(other.order))
			
			return false;
		
		if (progeny == null) {
			
			if (other.progeny != null)
				
				return false;
			
		} else if (!progeny.equals(other.progeny))
			
			return false;
		
		return true;
		
		
	}
	
	
}