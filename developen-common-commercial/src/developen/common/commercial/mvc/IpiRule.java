package developen.common.commercial.mvc;

import developen.common.framework.mvc.ListEditor;
import developen.common.framework.mvc.ListEditorState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;

@Table
public class IpiRule extends Model implements Search, ListEditor{


	private static final long serialVersionUID = 7645550462047707286L;

	private ListEditorState modelState;

	@Identifier
	private IpiRulePK identifier;

	@ManyToOne
	private IpiCst cst;

	@Column
	private Double ipiAliquot;

	@Column
	private Double ipiStaff;         


	public IpiRulePK getIdentifier() {


		if (identifier==null)

			identifier = new IpiRulePK();

		return identifier;


	}


	public void setIdentifier(IpiRulePK newValue) {


		IpiRulePK oldValue = this.identifier;

		this.identifier = newValue;

		firePropertyChange("Identifier", oldValue, newValue);


	}


	public IpiCst getCst() {

		return cst;

	}


	public void setCst(IpiCst newValue) {


		IpiCst oldValue = this.cst;

		this.cst = newValue;

		firePropertyChange("Cst", oldValue, newValue);


	}


	public Double getIpiAliquot() {

		return ipiAliquot;

	}


	public void setIpiAliquot(Double newValue) {


		Double oldValue = this.ipiAliquot;

		this.ipiAliquot = newValue;

		firePropertyChange("IpiAliquot", oldValue, newValue);


	}


	public Double getIpiStaff() {

		return ipiStaff;

	}


	public void setIpiStaff(Double newValue) {


		Double oldValue = this.ipiStaff;

		this.ipiStaff = newValue;

		firePropertyChange("IpiStaff", oldValue, newValue);


	}
	
	
	public void setModelState(ListEditorState state) {


		ListEditorState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public ListEditorState getModelState() {

		return modelState;

	}


	public Object[] toColumns() {


		return new Object[]{

				getIdentifier().getCfop(),

				getIdentifier().getRule(),

				getIpiAliquot() 

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
		
		IpiRule other = (IpiRule) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}

	
}