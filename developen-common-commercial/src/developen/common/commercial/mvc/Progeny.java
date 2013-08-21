package developen.common.commercial.mvc;


import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;


@Table
public class Progeny extends Model implements Entry, Search{


	private static final long serialVersionUID = 4469029155239442907L;

	private EntryState modelState;

	@Identifier(sequence=true)
	private Long identifier;

	@Column
	private String denomination;

	@Column
	private Boolean active;

	@Column 
	private Double price;

	@ManyToOne
	private UnitMeasure unitMeasure;
	
	@ManyToOne
	private Icms icms;


	public Long getIdentifier() {

		return identifier;

	}


	public void setIdentifier(Long newValue) {


		Long oldValue = this.identifier;

		this.identifier = newValue;

		firePropertyChange("Identifier", oldValue, newValue);


	}


	public String getDenomination() {

		return denomination;

	}


	public void setDenomination(String newValue) {


		String oldValue = this.denomination;

		this.denomination = newValue;

		firePropertyChange("Denomination", oldValue, newValue);


	}


	public Boolean getActive() {

		return active;

	}


	public void setActive(Boolean newValue) {


		Boolean oldValue = this.active;

		this.active = newValue;

		firePropertyChange("Active", oldValue, newValue);


	}


	public void setModelState(EntryState state) {


		EntryState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public Double getPrice() {

		return price;

	}


	public void setPrice(Double newValue) {


		Double oldValue = this.price;

		this.price = newValue;

		firePropertyChange("Price", oldValue, newValue);


	}


	public UnitMeasure getUnitMeasure() {

		return unitMeasure;

	}


	public void setUnitMeasure(UnitMeasure newValue) {


		UnitMeasure oldValue = this.unitMeasure;

		this.unitMeasure = newValue;

		firePropertyChange("UnitMeasure", oldValue, newValue);


	}


	public Icms getIcms() {

		return icms;

	}


	public void setIcms(Icms newValue) {


		Icms oldValue = this.icms;

		this.icms = newValue;

		firePropertyChange("Icms", oldValue, newValue);


	}


	public EntryState getModelState() {

		return modelState;

	}


	public String toString(){

		return getDenomination() + " (" + getUnitMeasure().getAcronym() + ")";

	}


	public Object[] toColumns() {

		
		return new Object[]{

				getIdentifier(),

				getDenomination(),

				getUnitMeasure().getAcronym()				

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

		Progeny other = (Progeny) obj;

		if (identifier == null) {

			if (other.identifier != null)

				return false;

		} else if (!identifier.equals(other.identifier))

			return false;

		return true;


	}


}