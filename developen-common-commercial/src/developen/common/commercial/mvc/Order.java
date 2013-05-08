package developen.common.commercial.mvc;

import java.util.Date;
import java.util.List;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.Table;
import developen.common.subject.mvc.Subject;
import developen.common.subject.mvc.SystemPerson;


@Table
public class Order extends Model implements Entry{


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
	
	@ManyToOne
    private SystemPerson createdBy;

	@ManyToOne
    private SystemPerson modifiedBy;

	@Column
    private Date createdIn;

	@Column
    private Date modifiedIn;

	
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

	
	public void setCreatedBy(SystemPerson newValue) {

		
		SystemPerson oldValue = this.createdBy;
		
		this.createdBy = newValue;
		
		firePropertyChange("CreatedBy", oldValue, newValue);

		
	}


	public SystemPerson getCreatedBy() {

		return createdBy;
		
	}

	
	public void setModifiedBy(SystemPerson newValue) {

		
		SystemPerson oldValue = this.modifiedBy;
		
		this.modifiedBy = newValue;
		
		firePropertyChange("ModifiedBy", oldValue, newValue);

		
	}


	public SystemPerson getModifiedBy() {

		return modifiedBy;
		
	}

	
	public void setCreatedIn(Date newValue) {

		
		Date oldValue = this.createdIn;
		
		this.createdIn = newValue;
		
		firePropertyChange("CreatedIn", oldValue, newValue);

		
	}

	
	public Date getCreatedIn() {
		
		return createdIn;
		
	}

	
	public void setModifiedIn(Date newValue) {

		
		Date oldValue = this.modifiedIn;
		
		this.modifiedIn = newValue;
		
		firePropertyChange("ModifiedIn", oldValue, newValue);
		
		
	}

	
	public Date getModifiedIn() {
		
		return modifiedIn;
		
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