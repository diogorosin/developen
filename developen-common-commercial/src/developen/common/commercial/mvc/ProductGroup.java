package developen.common.commercial.mvc;

import developen.common.framework.mvc.Entry;
import developen.common.framework.mvc.EntryState;
import developen.common.framework.mvc.Model;
import developen.common.framework.mvc.Search;
import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.Identifier;
import developen.common.persistence.annotation.Table;

@Table
public class ProductGroup extends Model implements Entry, Search{
	
	
	private static final long serialVersionUID = -680187333503380526L;

	private EntryState modelState;
	
	@Identifier(sequence=true)
	private Long identifier;

	@Column
	private String denomination;

	@Column
	private String shortDenomination;
	
	@Column
	private Boolean productMark;
	
	@Column
	private Boolean productLine;

	@Column
	private Boolean productModel;
	
	@Column
	private Boolean productDetail;

	@Column
	private Boolean productFinish;

	@Column
	private Boolean widthValue;
	
	@Column
	private Boolean heightValue;
	
	@Column
	private Boolean lengthValue;

	@Column
	private Boolean contentValue;
	
	@Column
	private Boolean grossWeightValue;
	
	@Column
	private Boolean netWeightValue;
	
	
	public Long getIdentifier() {
		
		return identifier;
		
	}

	
	public void setIdentifier(Long newValue) {
		

		Long oldValue = this.identifier;
		
		this.identifier = newValue;
		
		firePropertyChange("Identifier", oldValue, newValue);

		
	}


	public String getDenomination() {
		
		return denomination;
		
	}


	public void setDenomination(String newValue) {
		

		String oldValue = this.denomination;
		
		this.denomination = newValue;
		
		firePropertyChange("Denomination", oldValue, newValue);

		
	}


	public String getShortDenomination() {
		
		return shortDenomination;
		
	}
	
	
	public void setShortDenomination(String newValue) {
		
		
		String oldValue = this.shortDenomination;
		
		this.shortDenomination = newValue;
		
		firePropertyChange("ShortDenomination", oldValue, newValue);
		
		
	}

	
	public Boolean getProductMark() {

		return productMark;

	}


	public void setProductMark(Boolean newValue) {


		Boolean oldValue = this.productMark;

		this.productMark = newValue;

		firePropertyChange("ProductMark", oldValue, newValue);


	}


	public Boolean getProductLine() {

		return productLine;

	}


	public void setProductLine(Boolean newValue) {


		Boolean oldValue = this.productLine;

		this.productLine = newValue;

		firePropertyChange("ProductLine", oldValue, newValue);


	}


	public Boolean getProductModel() {

		return productModel;

	}


	public void setProductModel(Boolean newValue) {


		Boolean oldValue = this.productModel;

		this.productModel = newValue;

		firePropertyChange("ProductModel", oldValue, newValue);


	}


	public Boolean getProductDetail() {

		return productDetail;

	}


	public void setProductDetail(Boolean newValue) {


		Boolean oldValue = this.productDetail;

		this.productDetail = newValue;

		firePropertyChange("ProductDetail", oldValue, newValue);


	}


	public Boolean getProductFinish() {

		return productFinish;

	}


	public void setProductFinish(Boolean newValue) {


		Boolean oldValue = this.productFinish;

		this.productFinish = newValue;

		firePropertyChange("ProductFinish", oldValue, newValue);


	}


	public Boolean getWidthValue() {

		return widthValue;

	}


	public void setWidthValue(Boolean newValue) {


		Boolean oldValue = this.widthValue;

		this.widthValue = newValue;

		firePropertyChange("WidthValue", oldValue, newValue);


	}

	
	public Boolean getHeightValue() {

		return heightValue;

	}


	public void setHeightValue(Boolean newValue) {


		Boolean oldValue = this.heightValue;

		this.heightValue = newValue;

		firePropertyChange("HeightValue", oldValue, newValue);


	}

	
	public Boolean getLengthValue() {

		return lengthValue;

	}


	public void setLengthValue(Boolean newValue) {


		Boolean oldValue = this.lengthValue;

		this.lengthValue = newValue;

		firePropertyChange("LengthValue", oldValue, newValue);


	}

	
	public Boolean getContentValue() {

		return contentValue;

	}


	public void setContentValue(Boolean newValue) {


		Boolean oldValue = this.contentValue;

		this.contentValue = newValue;

		firePropertyChange("ContentValue", oldValue, newValue);


	}


	public Boolean getGrossWeightValue() {


		return grossWeightValue;


	}


	public void setGrossWeightValue(Boolean newValue) {


		Boolean oldValue = this.grossWeightValue;

		this.grossWeightValue = newValue;

		firePropertyChange("GrossWeightValue", oldValue, newValue);


	}
	

	public Boolean getNetWeightValue() {

		return netWeightValue;

	}


	public void setNetWeightValue(Boolean newValue) {


		Boolean oldValue = this.netWeightValue;

		this.netWeightValue = newValue;

		firePropertyChange("NetWeightValue", oldValue, newValue);


	}

	
	public void setModelState(EntryState state) {


		EntryState oldValue = this.modelState;

		this.modelState = state;

		firePropertyChange("ModelState", oldValue, state);


	}


	public EntryState getModelState() {

		return modelState;

	}


	public Object[] toColumns() {


		return new Object[]{

				getIdentifier(),

				getDenomination(),
				
				getShortDenomination()

		};


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
		
		ProductGroup other = (ProductGroup) obj;
		
		if (identifier == null) {
			
			if (other.identifier != null)
				
				return false;
			
		} else if (!identifier.equals(other.identifier))
			
			return false;
		
		return true;
		
		
	}
	

	public String toString(){

		return getDenomination(); 

	}


}