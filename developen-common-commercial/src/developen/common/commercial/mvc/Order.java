package developen.common.commercial.mvc;

import java.util.List;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.Table;


@Table
public class Order extends Model implements Entry, Search{


	private static final long serialVersionUID = 7169413307545191124L;

	private EntryState modelState;

	@Identifier(sequence=true)
	private Long identifier;

	@ManyToOne
	private Subject from;

	@ManyToOne
	private Subject to;

	@OneToMany(mappedBy="order")
	private List<OrderItem> items;


	public Long getIdentifier() {

		return identifier;

	}


	public void setIdentifier(Long newValue) {


		Long oldValue = this.identifier;

		this.identifier = newValue;

		firePropertyChange("Identifier", oldValue, newValue);


	}


	public void setFrom(Subject newValue) {


		Subject oldValue = this.from;

		this.from = newValue;

		firePropertyChange("From", oldValue, newValue);


	}


	public Subject getFrom() {

		return from;

	}


	public void setTo(Subject newValue) {


		Subject oldValue = this.to;

		this.to = newValue;

		firePropertyChange("To", oldValue, newValue);


	}


	public Subject getTo() {

		return to;

	}


	public void setItems(List<OrderItem> newValue) {


		List<OrderItem> oldValue = this.items;

		this.items = newValue;

		firePropertyChange("Items", oldValue, newValue);


	}


	public List<OrderItem> getItems() {

		return items;

	}


	public void setModelState(EntryState state) {


		EntryState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public EntryState getModelState() {

		return modelState;

	}


	public String toString (){

		return getIdentifier().toString();

	}


	public Object[] toColumns() {


		return new Object[]{

				getIdentifier(),

				getFrom(),

				getTo()

		};


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

		Order other = (Order) obj;

		if (identifier == null) {

			if (other.identifier != null)

				return false;

		} else if (!identifier.equals(other.identifier))

			return false;

		return true;


	}


}