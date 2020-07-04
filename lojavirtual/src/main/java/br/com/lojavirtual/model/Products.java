package br.com.lojavirtual.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "ID_PRODUCT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	
	@Column(name = "ID_ORDER")
	private Long idOrder;
	
	@Column(name = "PRODUCT_NUMBER")
	private String productNumber;

	@Column(name = "PRODUCT_BRAND")
	private String productBrand;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "UNITARY_VALUE")
	private String unitaryValue;
	
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(String unitaryValue) {
		this.unitaryValue = unitaryValue;
	}

	public Products() {
		
	}

	public Products(Long idProduct, Long idOrder, String productNumber, String productBrand, String productName,
			String unitaryValue) {
		super();
		this.idProduct = idProduct;
		this.idOrder = idOrder;
		this.productNumber = productNumber;
		this.productBrand = productBrand;
		this.productName = productName;
		this.unitaryValue = unitaryValue;
	}

	@Override
	public String toString() {
		return "Products [idProduct=" + idProduct + ", idOrder=" + idOrder + ", productNumber=" + productNumber
				+ ", productBrand=" + productBrand + ", productName=" + productName + ", unitaryValue=" + unitaryValue
				+ "]";
	}
	
	

}
