package developen.common.commercial.mvc;

import java.util.List;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.Table;

@Table
public class MeasureUnit extends Model implements Entry, Search{


	private static final long serialVersionUID = 7117879849221675913L;

	private EntryState modelState;

	@Identifier(sequence=true)
	private Long identifier;

	@Column
	private String denomination;

	@Column
	private String acronym;

	@ManyToOne
	private MeasureUnitGroup group;

	@OneToMany(mappedBy="from")
	private List<MeasureUnitConversion> conversions;


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


	public String getAcronym() {

		return acronym;

	}


	public void setAcronym(String newValue) {


		String oldValue = this.acronym;

		this.acronym = newValue;

		firePropertyChange("Acronym", oldValue, newValue);


	}


	public MeasureUnitGroup getGroup() {

		return group;

	}


	public void setGroup(MeasureUnitGroup newValue) {


		MeasureUnitGroup oldValue = this.group;

		this.group = newValue;

		firePropertyChange("Group", oldValue, newValue);


	}


	public List<MeasureUnitConversion> getConversions() {

		return conversions;

	}


	public void setConversions(List<MeasureUnitConversion> newValue) {


		List<MeasureUnitConversion> oldValue = this.conversions;

		this.conversions = newValue;

		firePropertyChange("Conversions", oldValue, newValue);


	}


	public void setModelState(EntryState state) {


		EntryState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public EntryState getModelState() {

		return modelState;

	}


	public String toString(){

		return getAcronym();

	}


	public Object[] toColumns() {


		return new Object[]{

				getIdentifier(),

				getDenomination(),

				getAcronym(),

				getGroup()

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

		MeasureUnit other = (MeasureUnit) obj;

		if (identifier == null) {

			if (other.identifier != null)

				return false;

		} else if (!identifier.equals(other.identifier))

			return false;

		return true;


	}


}