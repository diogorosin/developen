package developen.common.subject.mvc;

import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;

@Table
public class City extends Model implements Search{


	private static final long serialVersionUID = 4504937078334056995L;

	@Identifier(sequence=true)
	private Long identifier;

	@Column
	private String denomination;

	@ManyToOne
	private State state;


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


	public State getState() {

		return state;

	}


	public void setState(State newValue) {


		State oldValue = this.state;

		this.state = newValue;

		firePropertyChange("State", oldValue, newValue);


	}


	public String toString(){


		return getDenomination()

				+ " - " + getState().getAcronym()

				+ " (" + getState().getCountry().getDenomination() + ")"; 


	}


	public Object[] toColumns() {

		
		return new Object[]{
				
				getIdentifier(),
				
				getDenomination(),
				
				getState().getAcronym(),
				
				getState().getCountry()
				
		};
		
		
	}


}