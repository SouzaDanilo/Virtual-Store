package br.com.lojavirtual.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customers  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "ID_COSTUMER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCostumer;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CUSTOMER_PHONE")
	private String customerPhone;
	
	@Column(name = "CUSTOMER_EMAIL")
	private String customerEmail;
	
	@Column(name = "CUSTOMER_CPF")
	private String customerCpf;
	
	@Column(name = "CUSTOMER_AGE")
	private String customerAge;
	
	@Column(name = "BIRTH_DATE")
	private String birthDate;
	
	@Column(name = "STREET")
	private String street;
	
	@Column(name = "NUMBER_HOUSE")
	private String numberHouse;
	
	@Column(name = "NEIGHBORHOOD")
	private String neighborhood;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "UF")
	private String uf;
	
	@Column(name = "CEP")
	private String cep;

	public Long getIdCostumer() {
		return idCostumer;
	}

	public void setIdCostumer(Long idCostumer) {
		this.idCostumer = idCostumer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerCpf() {
		return customerCpf;
	}

	public void setCustomerCpf(String customerCpf) {
		this.customerCpf = customerCpf;
	}

	public String getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(String customerAge) {
		this.customerAge = customerAge;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(String numberHouse) {
		this.numberHouse = numberHouse;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Customers() {
		
	}

	public Customers(Long idCostumer, String customerName, String customerPhone, String customerEmail,
			String customerCpf, String customerAge, String birthDate, String street, String numberHouse,
			String neighborhood, String city, String state, String uf, String cep) {
		super();
		this.idCostumer = idCostumer;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.customerCpf = customerCpf;
		this.customerAge = customerAge;
		this.birthDate = birthDate;
		this.street = street;
		this.numberHouse = numberHouse;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.uf = uf;
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Customers [idCostumer=" + idCostumer + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", customerEmail=" + customerEmail + ", customerCpf=" + customerCpf + ", customerAge="
				+ customerAge + ", birthDate=" + birthDate + ", street=" + street + ", numberHouse=" + numberHouse
				+ ", neighborhood=" + neighborhood + ", city=" + city + ", state=" + state + ", uf=" + uf + ", cep="
				+ cep + "]";
	}
	
	
	
}
