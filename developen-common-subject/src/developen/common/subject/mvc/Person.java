package developen.common.subject.mvc;

import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Table;

@Table
public class Person extends Subject {

	
	private static final long serialVersionUID = 4687517692307945584L;

	@Column
	private Long cpf;

	
	public Long getCpf() {
		
		return cpf;
		
	}

	
	public void setCpf(Long newValue) {
		
		
		Long oldValue = this.cpf;
		
		this.cpf = newValue;
		
		firePropertyChange("Cpf", oldValue, newValue);

		
	}
	
	
}