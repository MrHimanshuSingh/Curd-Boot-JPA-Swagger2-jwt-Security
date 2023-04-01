package com.springboot.pojo;

import java.io.Serializable;

import javax.persistence.Column;
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



	public Customer(@NotEmpty(message = "Mandatory Field") String customer_Name,
			@NotEmpty(message = "Mandatory Field") String customer_Surname,
			@NotEmpty(message = "Mandatory Field") String customer_Gender,
			@NotEmpty(message = "Mandatroy Field") String customer_Email) {
		super();
		this.customer_Name = customer_Name;
		this.customer_Surname = customer_Surname;
		this.customer_Gender = customer_Gender;
		this.customer_Email = customer_Email;
	}

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customer_Id;
	
	@NotEmpty(message = "Mandatory Field")
	private String customer_Name, customer_Surname, customer_Gender;
	
	@Id
	@NotEmpty(message = "Mandatroy Field")
	@Column(name = "Email")
	private String customer_Email;

}
