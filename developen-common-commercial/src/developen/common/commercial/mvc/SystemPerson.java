package developen.common.commercial.mvc;

import java.util.List;

import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.OneToOne;
import developen.common.persistence.annotation.Table;


@Table
public class SystemPerson extends Person {

	
	private static final long serialVersionUID = 8623553480290797854L;

	@Column
	private String password;
	
	@OneToMany(mappedBy="systemPerson")
	private List<SystemPersonSystemAction> systemActions;
	
	@OneToMany(mappedBy="systemPerson")
	private List<SystemPersonSystemCompany> systemCompanies;

	@OneToOne
	private SystemPersonPreference preference;

	
	public String getPassword() {
		
		return password;
		
	}

	
	public void setPassword(String newValue) {
		
		
		String oldValue = this.password;
		
		this.password = newValue;
		
		firePropertyChange("Password", oldValue, newValue);
		
		
	}

	
	public List<SystemPersonSystemAction> getSystemActions() {
		
		return systemActions;
		
	}
	

	public void setSystemActions(List<SystemPersonSystemAction> newValue) {
	
		
		List<SystemPersonSystemAction> oldValue = this.systemActions;
		
		this.systemActions = newValue;
		
		firePropertyChange("SystemActions", oldValue, newValue);
		
		
	}

	
	public List<SystemPersonSystemCompany> getSystemCompanies() {
		
		return systemCompanies;
		
	}

	
	public void setSystemCompanies(List<SystemPersonSystemCompany> newValue) {
	
		
		List<SystemPersonSystemCompany> oldValue = this.systemCompanies;
		
		this.systemCompanies = newValue;
		
		firePropertyChange("SystemCompanies", oldValue, newValue);
		
		
	}
	
	
	public SystemPersonPreference getPreference() {

		
		if (preference==null)
			
			preference = new SystemPersonPreference();
		
		return preference;

		
	}


	public void setPreference(SystemPersonPreference newValue) {


		SystemPersonPreference oldValue = this.preference;

		this.preference = newValue;

		firePropertyChange("Preference", oldValue, newValue);


	}

	
}