package com.springboot.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer implements Serializable {

	public Customer(@NotEmpty(message = "Mandatory Field") String customerName,
			@NotEmpty(message = "Mandatory Field") String customerSurname,
			@NotEmpty(message = "Mandatory Field") String customerGender,
			@NotEmpty(message = "Mandatroy Field") String customerEmail) {
		super();
		this.customerName = customerName;
		this.customerSurname = customerSurname;
		this.customerGender = customerGender;
		this.customerEmail = customerEmail;
	}

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;

	@NotEmpty(message = "Mandatory Field")
	private String customerName;
	@NotEmpty(message = "Mandatory Field")
	private String customerSurname;
	@NotEmpty(message = "Mandatory Field")
	private String customerGender;

	@Id
	@NotEmpty(message = "Mandatroy Field")
	private String customerEmail;

}
