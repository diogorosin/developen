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
public class IcmsRule extends Model implements Search, ListEditor{


	private static final long serialVersionUID = 2805896150704549531L;

	private ListEditorState modelState;

	@Identifier
	private IcmsRulePK identifier;

	@ManyToOne
	private IcmsCst cst;
	
	@ManyToOne
	private IcmsCsosn csosn;

	@Column
	private Double icmsAliquot;

	@Column
	private Double icmsReduction;

	@Column
	private Double icmsAliquotReduced;

	@Column
	private Double icmsAliquotCreditReusable;

	@Column
	private Double icmsSTMarckup;

	@Column
	private Double icmsSTReduction;

	@Column
	private Double icmsSTStaff;         
	
	@Column
	private Long cfopGroup;


	public IcmsRulePK getIdentifier() {


		if (identifier==null)

			identifier = new IcmsRulePK();

		return identifier;


	}


	public void setIdentifier(IcmsRulePK newValue) {


		IcmsRulePK oldValue = this.identifier;

		this.identifier = newValue;

		firePropertyChange("Identifier", oldValue, newValue);


	}


	public IcmsCst getCst() {

		return cst;

	}


	public void setCst(IcmsCst newValue) {


		IcmsCst oldValue = this.cst;

		this.cst = newValue;

		firePropertyChange("Cst", oldValue, newValue);


	}


	public IcmsCsosn getCsosn() {

		return csosn;

	}


	public void setCsosn(IcmsCsosn newValue) {


		IcmsCsosn oldValue = this.csosn;

		this.csosn = newValue;

		firePropertyChange("Csosn", oldValue, newValue);


	}


	public Double getIcmsAliquot() {

		return icmsAliquot;

	}


	public void setIcmsAliquot(Double newValue) {


		Double oldValue = this.icmsAliquot;

		this.icmsAliquot = newValue;

		firePropertyChange("IcmsAliquot", oldValue, newValue);


	}


	public Double getIcmsReduction() {

		return icmsReduction;

	}


	public void setIcmsReduction(Double newValue) {


		Double oldValue = this.icmsReduction;

		this.icmsReduction = newValue;

		firePropertyChange("IcmsReduction", oldValue, newValue);


	}


	public Double getIcmsAliquotReduced() {

		return icmsAliquotReduced;

	}


	public void setIcmsAliquotReduced(Double newValue) {


		Double oldValue = this.icmsAliquotReduced;

		this.icmsAliquotReduced = newValue;

		firePropertyChange("IcmsAliquotReduced", oldValue, newValue);


	}
	
	
	public Double getIcmsAliquotCreditReusable() {

		return icmsAliquotCreditReusable;

	}


	public void setIcmsAliquotCreditReusable(Double newValue) {


		Double oldValue = this.icmsAliquotCreditReusable;

		this.icmsAliquotCreditReusable = newValue;

		firePropertyChange("IcmsAliquotCreditReusable", oldValue, newValue);


	}
	
	
	public Double getIcmsSTMarckup() {

		return icmsSTMarckup;

	}


	public void setIcmsSTMarckup(Double newValue) {


		Double oldValue = this.icmsSTMarckup;

		this.icmsSTMarckup = newValue;

		firePropertyChange("IcmsSTMarckup", oldValue, newValue);


	}
	
	
	public Double getIcmsSTReduction() {

		return icmsSTReduction;

	}


	public void setIcmsSTReduction(Double newValue) {


		Double oldValue = this.icmsSTReduction;

		this.icmsSTReduction = newValue;

		firePropertyChange("IcmsSTReduction", oldValue, newValue);


	}
	
	
	public Double getIcmsSTSTaff() {

		return icmsSTStaff;

	}


	public void setIcmsSTStaff(Double newValue) {


		Double oldValue = this.icmsSTStaff;

		this.icmsSTStaff = newValue;

		firePropertyChange("IcmsSTStaff", oldValue, newValue);


	}
	
	
	public Long getCfopGroup() {

		return cfopGroup;

	}


	public void setCfopGroup(Long newValue) {


		Long oldValue = this.cfopGroup;

		this.cfopGroup = newValue;

		firePropertyChange("CfopGroup", oldValue, newValue);


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

				getIdentifier().getFrom(),

				getIdentifier().getTo(),
				
				getIdentifier().getRule()

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

		IcmsRule other = (IcmsRule) obj;

		if (identifier == null) {

			if (other.identifier != null)

				return false;

		} else if (!identifier.equals(other.identifier))

			return false;

		return true;


	}


}