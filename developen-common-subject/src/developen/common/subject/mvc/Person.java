package developen.common.subject.mvc;

import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.OneToOne;
import developen.common.persistence.annotation.Table;

@Table
public class Person extends Subject implements Search {


	private static final long serialVersionUID = 4687517692307945584L;

	@Column
	private Long cpf;

	@OneToOne
	private Rg rg;


	public Long getCpf() {

		return cpf;

	}


	public void setCpf(Long newValue) {


		Long oldValue = this.cpf;

		this.cpf = newValue;

		firePropertyChange("Cpf", oldValue, newValue);


	}


	public Rg getRg() {

		
		if (rg==null)
			
			rg = new Rg();
		
		return rg;

		
	}


	public void setRg(Rg newValue) {


		Rg oldValue = this.rg;

		this.rg = newValue;

		firePropertyChange("Rg", oldValue, newValue);


	}


	public Object[] toColumns() {


		return new Object[]{ 

				getIdentifier(), 

				getCpf(), 

				getDenomination(), 

		};


	}


}