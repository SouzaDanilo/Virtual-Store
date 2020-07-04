package br.com.lojavirtual.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "ID_ORDER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;
	
	@Column(name = "ID_CUSTOMER")
	private Long idCustomer;
	
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	
	@Column(name = "ENTRY_DATE")
	private String entryDate;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CUSTOMER_EMAIL")
	private String customerEmail;
	
	@Column(name = "CUSTOMER_CPF")
	private String customerCpf;
	
	@Column(name = "BILLING_NAME")
	private String billingName;
	
	@Column(name = "FREIGHT_VALUE")
	private String freightValue;
	
	@Column(name = "ORDER_VALUE")
	private String orderValue;
	
	@Column(name = "PAYMENT_CONFIRMATION_DATE")
	private String paymentConfirmationDate;
	
	@Column(name = "DELIVERY_TIME")
	private String deliveryTime;
	
	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public String getFreightValue() {
		return freightValue;
	}

	public void setFreightValue(String freightValue) {
		this.freightValue = freightValue;
	}

	public String getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}

	public String getPaymentConfirmationDate() {
		return paymentConfirmationDate;
	}

	public void setPaymentConfirmationDate(String paymentConfirmationDate) {
		this.paymentConfirmationDate = paymentConfirmationDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Orders() {
		
	}

	public Orders(Long idOrder, Long idCustomer, String requestNumber, String entryDate, String customerName,
			String customerEmail, String customerCpf, String billingName, String freightValue, String orderValue,
			String paymentConfirmationDate, String deliveryTime) {
		super();
		this.idOrder = idOrder;
		this.idCustomer = idCustomer;
		this.requestNumber = requestNumber;
		this.entryDate = entryDate;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerCpf = customerCpf;
		this.billingName = billingName;
		this.freightValue = freightValue;
		this.orderValue = orderValue;
		this.paymentConfirmationDate = paymentConfirmationDate;
		this.deliveryTime = deliveryTime;
	}

	@Override
	public String toString() {
		return "Orders [idOrder=" + idOrder + ", idCustomer=" + idCustomer + ", requestNumber=" + requestNumber
				+ ", entryDate=" + entryDate + ", customerName=" + customerName + ", customerEmail=" + customerEmail
				+ ", customerCpf=" + customerCpf + ", billingName=" + billingName + ", freightValue=" + freightValue
				+ ", orderValue=" + orderValue + ", paymentConfirmationDate=" + paymentConfirmationDate
				+ ", deliveryTime=" + deliveryTime + "]";
	}
	

}
