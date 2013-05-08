package developen.common.subject.mvc;

import developen.common.framework.mvc.Model;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.Table;

@Table
public class Address extends Model {


	private static final long serialVersionUID = 2441237956344985120L;
	
	@Identifier
	private Long identifier;
	
	@Column
	private String playArea;
	
	@Column
	private String number;
	
	@Column
	private String district;

	@Column
	private String complement;

	@Column
	private Long postalCode;
	
	@ManyToOne
	private City city;
	
	@Column
	private Long phone;
	
	@Column
	private String email;
	
	@Column
	private String webSite;
	

	public Long getIdentifier() {
		
		return identifier;
		
	}


	public void setIdentifier(Long newValue) {
		

		Long oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);

		
	}

	
	public String getPlayArea() {
		
		return playArea;
		
	}

	
	public void setPlayArea(String newValue) {

		
		String oldValue = this.playArea;
		
		this.playArea = newValue;
		
		firePropertyChange("PlayArea", oldValue, newValue);
		
		
	}

	
	public String getNumber() {
		
		return number;
		
	}


	public void setNumber(String newValue) {

		
		String oldValue = this.number;
		
		this.number = newValue;
		
		firePropertyChange("Number", oldValue, newValue);
		
		
	}


	public String getDistrict() {
		
		return district;
		
	}


	public void setDistrict(String newValue) {
		

		String oldValue = this.district;
		
		this.district = newValue;
		
		firePropertyChange("District", oldValue, newValue);
		

	}


	public Long getPostalCode() {
		
		return postalCode;
		
	}


	public void setPostalCode(Long newValue) {
		

		Long oldValue = this.postalCode;
		
		this.postalCode = newValue;
		
		firePropertyChange("PostalCode", oldValue, newValue);

		
	}


	public String getComplement() {
		
		return complement;
		
	}


	public void setComplement(String newValue) {
		

		String oldValue = this.complement;
		
		this.complement = newValue;
		
		firePropertyChange("Complement", oldValue, newValue);

		
	}


	public City getCity() {
		
		return city;
		
	}


	public void setCity(City newValue) {
		
		
		City oldValue = this.city;
		
		this.city = newValue;
		
		firePropertyChange("City", oldValue, newValue);

		
	}


	public Long getPhone() {
		
		return phone;
		
	}


	public void setPhone(Long newValue) {
		
		
		Long oldValue = this.phone;
		
		this.phone = newValue;
		
		firePropertyChange("Phone", oldValue, newValue);
		
		
	}


	public String getEmail() {
		
		return email;
		
	}


	public void setEmail(String newValue) {
		
		
		String oldValue = this.email;
		
		this.email = newValue;
		
		firePropertyChange("Email", oldValue, newValue);
		
		
	}


	public String getWebSite() {
		
		return webSite;

	}


	public void setWebSite(String newValue) {
		
		
		String oldValue = this.webSite;
		
		this.webSite = newValue;
		
		firePropertyChange("WebSite", oldValue, newValue);

		
	}


	public int hashCode() {
		
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result
				
				+ ((identifier == null) ? 0 : identifier.hashCode());
		
		return result;
		
		
	}


	public boolean equals(Object obj) {
		
		
		if (this == obj)
			
			return true;
		
		if (obj == null)
			
			return false;
		
		if (getClass() != obj.getClass())
			
			return false;
		
		Address other = (Address) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}


}