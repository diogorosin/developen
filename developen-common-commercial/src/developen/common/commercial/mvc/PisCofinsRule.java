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
public class PisCofinsRule extends Model implements Search, ListEditor{


	private static final long serialVersionUID = 7645550462047707286L;

	private ListEditorState modelState;

	@Identifier
	private PisCofinsRulePK identifier;

	@ManyToOne
	private PisCst pisCst;

	@ManyToOne
	private CofinsCst cofinsCst;

	@Column
	private Double pisCumulative;

	@Column
	private Double pisNonCumulative;         

	@Column
	private Double cofinsCumulative;

	@Column
	private Double cofinsNonCumulative;         


	public PisCofinsRulePK getIdentifier() {


		if (identifier==null)

			identifier = new PisCofinsRulePK();

		return identifier;


	}


	public void setIdentifier(PisCofinsRulePK newValue) {


		PisCofinsRulePK oldValue = this.identifier;

		this.identifier = newValue;

		firePropertyChange("Identifier", oldValue, newValue);


	}


	public PisCst getPisCst() {

		return pisCst;

	}


	public void setPisCst(PisCst newValue) {


		PisCst oldValue = this.pisCst;

		this.pisCst = newValue;

		firePropertyChange("PisCst", oldValue, newValue);


	}


	public CofinsCst getCofinsCst() {

		return cofinsCst;

	}


	public void setCofinsCst(CofinsCst newValue) {


		CofinsCst oldValue = this.cofinsCst;

		this.cofinsCst = newValue;

		firePropertyChange("CofinsCst", oldValue, newValue);


	}


	public Double getPisCumulative() {

		return pisCumulative;

	}


	public void setPisCumulative(Double newValue) {


		Double oldValue = this.pisCumulative;

		this.pisCumulative = newValue;

		firePropertyChange("PisCumulative", oldValue, newValue);


	}


	public Double getPisNonCumulative() {

		return pisNonCumulative;

	}


	public void setPisNonCumulative(Double newValue) {


		Double oldValue = this.pisNonCumulative;

		this.pisNonCumulative = newValue;

		firePropertyChange("PisNonCumulative", oldValue, newValue);


	}
	
	
	public Double getCofinsCumulative() {

		return cofinsCumulative;

	}


	public void setCofinsCumulative(Double newValue) {


		Double oldValue = this.cofinsCumulative;

		this.cofinsCumulative = newValue;

		firePropertyChange("CofinsCumulative", oldValue, newValue);


	}


	public Double getCofinsNonCumulative() {

		return cofinsNonCumulative;

	}


	public void setCofinsNonCumulative(Double newValue) {


		Double oldValue = this.cofinsNonCumulative;

		this.cofinsNonCumulative = newValue;

		firePropertyChange("CofinsNonCumulative", oldValue, newValue);


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

				getPisCumulative(),
				
				getPisNonCumulative(),
				
				getCofinsCumulative(),
				
				getCofinsNonCumulative(),

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
		
		PisCofinsRule other = (PisCofinsRule) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}

	
}