package developen.common.subject.mvc;

import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.OneToOne;
import developen.common.persistence.annotation.Table;


@Table
public class Company extends Subject implements Search {


	private static final long serialVersionUID = 2674088322733329923L;
	
	@Column
	private Long cnpj;

	@OneToOne
	private Ie ie;
	
	
	public Long getCnpj() {
		
		return cnpj;
		
	}

	
	public void setCnpj(Long newValue) {
		
		
		Long oldValue = this.cnpj;
		
		this.cnpj = newValue;
		
		firePropertyChange("Cnpj", oldValue, newValue);

		
	}

	
	public Ie getIe() {

		
		if (ie==null)
			
			ie = new Ie();
		
		return ie;

		
	}


	public void setIe(Ie newValue) {


		Ie oldValue = this.ie;

		this.ie = newValue;

		firePropertyChange("Ie", oldValue, newValue);


	}

	
	public Object[] toColumns() {

		
		return new Object[]{ 
				
				getIdentifier(), 
				
				getCnpj(), 
				
				getDenomination(), 
				
		};

		
	}

	
}