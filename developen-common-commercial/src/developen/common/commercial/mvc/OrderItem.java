package developen.common.commercial.mvc;


import developen.common.framework.mvc.ListEditor;
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;


@Table
public class OrderItem extends Model implements Search, ListEditor{


	private static final long serialVersionUID = 7169413307545191124L;

	private ListEditorState modelState;

	@Identifier
    private OrderItemPK identifier;

	@Column
    private Double amount;

	@Column
    private Double price;
	
	@Column
    private Double value;

	
	public OrderItemPK getIdentifier() {
		

		if (identifier==null)

			identifier = new OrderItemPK();
		
		return identifier;
		
		
	}
	

	public void setIdentifier(OrderItemPK newValue) {

		
		OrderItemPK oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);
		
		
	}

	
	public void setAmount(Double newValue) {

		
		Double oldValue = this.amount;
		
		this.amount = newValue;
		
		firePropertyChange("Amount", oldValue, newValue);

		
	}

	
	public Double getAmount() {

		return amount;
		
	}

	
	public void setPrice(Double newValue) {

		
		Double oldValue = this.price;
		
		this.price = newValue;
		
		firePropertyChange("Price", oldValue, newValue);

		
	}

	
	public Double getPrice() {

		return price;
		
	}

	
	public void setValue(Double newValue) {

		
		Double oldValue = this.value;
		
		this.value = newValue;
		
		firePropertyChange("Value", oldValue, newValue);

		
	}

	
	public Double getValue() {

		return value;
		
	}

	public Object[] toColumns() {

		
		return new Object[]{
				
				getIdentifier().getProgeny(), 
				
				getAmount(), 
				
				getPrice(), 
				
				getValue()
				
		};
		
		
	}


	public void setModelState(ListEditorState state) {


		ListEditorState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public ListEditorState getModelState() {

		return modelState;

	}

	
	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result
				
				+ ((identifier == null) ? 0 : identifier.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		OrderItem other = (OrderItem) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}

	
}