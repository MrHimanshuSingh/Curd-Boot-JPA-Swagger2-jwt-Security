package com.springboot.MainApp.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private Integer id;

	@Column(name = "name")
	private String conutryName;

	@Column(name = "state_id")
	@OneToMany(cascade = CascadeType.ALL)
	private Set<State> statesName;

	public Country(String conutryName, Set<State> statesName) {
		super();
		this.conutryName = conutryName;
		this.statesName = statesName;
	}

}
