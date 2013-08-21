package developen.common.commercial.mvc;

import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.OneToOne;
import developen.common.persistence.annotation.Table;

@Table
public class Person extends Subject implements Search {


	private static final long serialVersionUID = 4687517692307945584L;

	@OneToOne
	private Cpf cpf;

	@OneToOne
	private Rg rg;
		

	public Cpf getCpf() {

		
		if (cpf==null)
			
			cpf = new Cpf();
		
		return cpf;

		
	}


	public void setCpf(Cpf newValue) {


		Cpf oldValue = this.cpf;

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