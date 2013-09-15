package developen.common.commercial.mvc;


import java.util.List;

import developen.common.persistence.annotation.Column;
import developen.common.persistence.annotation.ManyToOne;
import developen.common.persistence.annotation.OneToMany;
import developen.common.persistence.annotation.Table;

@Table
public class Product extends Progeny {


	private static final long serialVersionUID = 3256648280907413897L;

	@ManyToOne
	private ProductGroup productGroup;

	@ManyToOne
	private ProductMark productMark;

	@ManyToOne
	private ProductLine productLine;

	@ManyToOne 
	private ProductModel productModel;

	@ManyToOne 
	private ProductDetail productDetail;

	@ManyToOne 
	private ProductFinish productFinish;


	@Column
	private Double widthValue;

	@ManyToOne
	private MeasureUnit widthUnit;


	@Column
	private Double heightValue;

	@ManyToOne
	private MeasureUnit heightUnit;

	
	@Column
	private Double lengthValue;

	@ManyToOne
	private MeasureUnit lengthUnit;

	
	@Column
	private Double contentValue;

	@ManyToOne
	private MeasureUnit contentUnit;

	
	@Column
	private Double grossWeightValue;

	@ManyToOne
	private MeasureUnit grossWeightUnit;


	@Column
	private Double netWeightValue;

	@ManyToOne
	private MeasureUnit netWeightUnit;

	@ManyToOne
	private Ipi ipi;

	
	@OneToMany(mappedBy="product")
	private List<ProductPart> parts;

	
	public ProductGroup getProductGroup() {

		return productGroup;

	}


	public void setProductGroup(ProductGroup newValue) {


		ProductGroup oldValue = this.productGroup;

		this.productGroup = newValue;

		firePropertyChange("ProductGroup", oldValue, newValue);


	}


	public ProductMark getProductMark() {

		return productMark;

	}


	public void setProductMark(ProductMark newValue) {


		ProductMark oldValue = this.productMark;

		this.productMark = newValue;

		firePropertyChange("ProductMark", oldValue, newValue);


	}


	public ProductLine getProductLine() {

		return productLine;

	}


	public void setProductLine(ProductLine newValue) {


		ProductLine oldValue = this.productLine;

		this.productLine = newValue;

		firePropertyChange("ProductLine", oldValue, newValue);


	}


	public ProductModel getProductModel() {

		return productModel;

	}


	public void setProductModel(ProductModel newValue) {


		ProductModel oldValue = this.productModel;

		this.productModel = newValue;

		firePropertyChange("ProductModel", oldValue, newValue);


	}


	public ProductDetail getProductDetail() {

		return productDetail;

	}


	public void setProductDetail(ProductDetail newValue) {


		ProductDetail oldValue = this.productDetail;

		this.productDetail = newValue;

		firePropertyChange("ProductDetail", oldValue, newValue);


	}


	public ProductFinish getProductFinish() {

		return productFinish;

	}


	public void setProductFinish(ProductFinish newValue) {


		ProductFinish oldValue = this.productFinish;

		this.productFinish = newValue;

		firePropertyChange("ProductFinish", oldValue, newValue);


	}


	public Double getWidthValue() {

		return widthValue;

	}


	public void setWidthValue(Double newValue) {


		Double oldValue = this.widthValue;

		this.widthValue = newValue;

		firePropertyChange("WidthValue", oldValue, newValue);


	}


	public MeasureUnit getWidthUnit() {

		return widthUnit;

	}


	public void setWidthUnit(MeasureUnit newValue) {


		MeasureUnit oldValue = this.widthUnit;

		this.widthUnit = newValue;

		firePropertyChange("WidthUnit", oldValue, newValue);


	}

	
	public Double getHeightValue() {

		return heightValue;

	}


	public void setHeightValue(Double newValue) {


		Double oldValue = this.heightValue;

		this.heightValue = newValue;

		firePropertyChange("HeightValue", oldValue, newValue);


	}


	public MeasureUnit getHeightUnit() {

		return heightUnit;

	}


	public void setHeightUnit(MeasureUnit newValue) {


		MeasureUnit oldValue = this.heightUnit;

		this.heightUnit = newValue;

		firePropertyChange("HeightUnit", oldValue, newValue);


	}

	
	public Double getLengthValue() {

		return lengthValue;

	}


	public void setLengthValue(Double newValue) {


		Double oldValue = this.lengthValue;

		this.lengthValue = newValue;

		firePropertyChange("LengthValue", oldValue, newValue);


	}
	
	
	public MeasureUnit getLengthUnit() {

		return lengthUnit;

	}


	public void setLengthUnit(MeasureUnit newValue) {


		MeasureUnit oldValue = this.lengthUnit;

		this.lengthUnit = newValue;

		firePropertyChange("LengthUnit", oldValue, newValue);


	}
	
	
	public Double getContentValue() {

		return contentValue;

	}


	public void setContentValue(Double newValue) {


		Double oldValue = this.contentValue;

		this.contentValue = newValue;

		firePropertyChange("ContentValue", oldValue, newValue);


	}

	
	public MeasureUnit getContentUnit() {

		return contentUnit;

	}


	public void setContentUnit(MeasureUnit newValue) {


		MeasureUnit oldValue = this.contentUnit;

		this.contentUnit = newValue;

		firePropertyChange("ContentUnit", oldValue, newValue);


	}

	
	public Double getGrossWeightValue() {

		return grossWeightValue;

	}


	public void setGrossWeightValue(Double newValue) {


		Double oldValue = this.grossWeightValue;

		this.grossWeightValue = newValue;

		firePropertyChange("GrossWeightValue", oldValue, newValue);


	}


	public MeasureUnit getGrossWeightUnit() {

		return grossWeightUnit;

	}


	public void setGrossWeightUnit(MeasureUnit newValue) {


		MeasureUnit oldValue = this.grossWeightUnit;

		this.grossWeightUnit = newValue;

		firePropertyChange("GrossWeightUnit", oldValue, newValue);


	}


	public Double getNetWeightValue() {

		return netWeightValue;

	}


	public void setNetWeightValue(Double newValue) {


		Double oldValue = this.netWeightValue;

		this.netWeightValue = newValue;

		firePropertyChange("NetWeightValue", oldValue, newValue);


	}


	public MeasureUnit getNetWeightUnit() {

		return netWeightUnit;

	}


	public void setNetWeightUnit(MeasureUnit newValue) {


		MeasureUnit oldValue = this.netWeightUnit;

		this.netWeightUnit = newValue;

		firePropertyChange("NetWeightUnit", oldValue, newValue);


	}

	
	public Ipi getIpi() {

		return ipi;

	}


	public void setIpi(Ipi newValue) {


		Ipi oldValue = this.ipi;

		this.ipi = newValue;

		firePropertyChange("Ipi", oldValue, newValue);


	}

	
	public List<ProductPart> getParts() {

		return parts;

	}


	public void setParts(List<ProductPart> newValue) {


		List<ProductPart> oldValue = this.parts;

		this.parts = newValue;

		firePropertyChange("Parts", oldValue, newValue);


	}
	

}