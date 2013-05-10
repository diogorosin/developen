package developen.common.subject.mvc;

import java.io.Serializable;

import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.View;

@View
public class SubjectView implements Serializable, Search{


	private static final long serialVersionUID = 4234813721853738184L;

	@Column
	private Long identifier;
	
	@Column
	private String denomination;

	@Column
	private Boolean active;

	@Column
	private String type;
	
	@Column
	private Long document;
	
	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String country;
	

	public Long getIdentifier() {
		
		return identifier;
		
	}
	

	public void setIdentifier(Long identifier) {
		
		this.identifier = identifier;
		
	}

	
	public String getDenomination() {
		
		return denomination;
		
	}

	
	public void setDenomination(String denomination) {
		
		this.denomination = denomination;
		
	}

	
	public Boolean getActive() {
		
		return active;
		
	}
	

	public void setActive(Boolean active) {
		
		this.active = active;
		
	}

	
	public String getType() {
		
		return type;
		
	}

	
	public void setType(String type) {
		
		this.type = type;
		
	}

	
	public Long getDocument() {
		
		return document;
		
	}

	
	public void setDocument(Long document) {
		
		this.document = document;
		
	}


	public String getCity() {
		
		return city;
		
	}


	public void setCity(String city) {
		
		this.city = city;
		
	}
	

	public String getState() {
		
		return state;
		
	}


	public void setState(String state) {
		
		this.state = state;
		
	}


	public String getCountry() {
		
		return country;
		
	}


	public void setCountry(String country) {
		
		this.country = country;
		
	}


	public Object[] toColumns() {

		
		return new Object[]{ 
				
				getIdentifier(),
				
				getType(),
				
				getDocument(),
				
				getDenomination(),
				
				getCity().toUpperCase(),
				
				getState(),
				
				getCountry()
				
		};

		
	}


}