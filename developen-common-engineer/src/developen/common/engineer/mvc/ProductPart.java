package developen.common.engineer.mvc;

import developen.common.framework.mvc.ListEditor;
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class ProductPart extends Model implements Search, ListEditor{


	private static final long serialVersionUID = 2805896150704549531L;

	private ListEditorState modelState;

	@Identifier
	private ProductPartPK identifier;

	@Column
	private Double amount;


	public ProductPartPK getIdentifier() {

		
		if (identifier==null)

			identifier = new ProductPartPK();

		return identifier;

		
	}


	public void setIdentifier(ProductPartPK newValue) {


		ProductPartPK oldValue = this.identifier;

		this.identifier = newValue;

		firePropertyChange("Identifier", oldValue, newValue);


	}


	public Double getAmount() {

		return amount;

	}


	public void setAmount(Double newValue) {


		Double oldValue = this.amount;

		this.amount= newValue;

		firePropertyChange("Amount", oldValue, newValue);


	}


	public void setModelState(ListEditorState state) {


		ListEditorState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public ListEditorState getModelState() {

		return modelState;

	}


	public String toString(){

		return getIdentifier().getPart().getDenomination();

	}


	public Object[] toColumns() {

		return new Object[]{ getIdentifier().getPart(), getAmount() };

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

		ProductPart other = (ProductPart) obj;

		if (identifier == null) {

			if (other.identifier != null)

				return false;

		} else if (!identifier.equals(other.identifier))

			return false;

		return true;


	}


}