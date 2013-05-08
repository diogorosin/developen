package developen.common.subject.mvc;

import java.util.List;

import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.Table;


@Table
public class SystemPerson extends Person {

	
	private static final long serialVersionUID = 8623553480290797854L;

	@Column
	private String password;
	
	@OneToMany(mappedBy="systemPerson")
	private List<PersonAction> actions;
	
	@OneToMany(mappedBy="systemPerson")
	private List<PersonCompany> companies;

	@ManyToOne
	private SystemCompany lastLoggedSystemCompany;
	
	@ManyToOne
	private Idiom idiom;

	
	public String getPassword() {
		
		return password;
		
	}

	
	public void setPassword(String newValue) {
		
		
		String oldValue = this.password;
		
		this.password = newValue;
		
		firePropertyChange("Password", oldValue, newValue);
		
		
	}

	
	public List<PersonAction> getActions() {
		
		return actions;
		
	}
	

	public void setActions(List<PersonAction> newValue) {
	
		
		List<PersonAction> oldValue = this.actions;
		
		this.actions = newValue;
		
		firePropertyChange("Actions", oldValue, newValue);
		
		
	}

	
	public List<PersonCompany> getCompanies() {
		
		return companies;
		
	}

	
	public void setCompanies(List<PersonCompany> newValue) {
	
		
		List<PersonCompany> oldValue = this.companies;
		
		this.companies = newValue;
		
		firePropertyChange("Companies", oldValue, newValue);
		
		
	}
	

	public Idiom getIdiom() {
		
		return idiom;
		
	}
	

	public void setIdiom(Idiom newValue) {
	
		
		Idiom oldValue = this.idiom;
		
		this.idiom = newValue;
		
		firePropertyChange("Idiom", oldValue, newValue);
		
		
	}
	

	public SystemCompany getLastLoggedSystemCompany() {
		
		return lastLoggedSystemCompany;
		
	}
	

	public void setLastLoggedSystemCompany(SystemCompany newValue) {
	
		
		SystemCompany oldValue = this.lastLoggedSystemCompany;
		
		this.lastLoggedSystemCompany = newValue;
		
		firePropertyChange("LastLoggedSystemCompany", oldValue, newValue);
		
		
	}

	
}