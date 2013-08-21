package developen.common.commercial.mvc;

import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToOne;
import developen.common.persistence.annotation.Table;


@Table
public class Company extends Subject implements Search {


	private static final long serialVersionUID = 2674088322733329923L;
	
	@OneToOne
	private Cnpj cnpj;

	@OneToOne
	private Ie ie;
	
	@Column
	private String fancyName;
	
	@ManyToOne
	private Cnae cnae;

	
	public Cnpj getCnpj() {

		
		if (cnpj==null)
			
			cnpj = new Cnpj();
		
		return cnpj;

		
	}

	
	public void setCnpj(Cnpj newValue) {
		
		
		Cnpj oldValue = this.cnpj;
		
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

	
	public String getFancyName() {
		
		return fancyName;
		
	}


	public void setFancyName(String newValue) {
		

		String oldValue = this.fancyName;
		
		this.fancyName = newValue;
		
		firePropertyChange("FancyName", oldValue, newValue);

		
	}


	public Cnae getCnae() {
		
		return cnae;
		
	}


	public void setCnae(Cnae newValue) {
		

		Cnae oldValue = this.cnae;
		
		this.cnae = newValue;
		
		firePropertyChange("Cnae", oldValue, newValue);

		
	}

	
	public Object[] toColumns() {

		
		return new Object[]{ 
				
				getIdentifier(), 
				
				getCnpj(), 
				
				getDenomination(), 
				
		};

		
	}

	
}