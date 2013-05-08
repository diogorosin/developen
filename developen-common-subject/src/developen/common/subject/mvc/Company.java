package developen.common.subject.mvc;

import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Table;


@Table
public class Company extends Subject {


	private static final long serialVersionUID = 2674088322733329923L;
	
	@Column
	private Long cnpj;

	
	public Long getCnpj() {
		
		return cnpj;
		
	}

	
	public void setCnpj(Long newValue) {
		
		
		Long oldValue = this.cnpj;
		
		this.cnpj = newValue;
		
		firePropertyChange("Cnpj", oldValue, newValue);

		
	}


}