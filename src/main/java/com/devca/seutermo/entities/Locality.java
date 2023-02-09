package com.devca.seutermo.entities;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_locality")
public class Locality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String cnpj;

	@Column(nullable = false)
	private String companyName;

	@Column(nullable = false, unique = true)
	private String zip;
	private String address;
	private Integer number;
	private String district;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String uf;

}
