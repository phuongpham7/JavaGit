package edu.mumscrum.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
@Entity
public class ProductBackLog {

	@Id
	@GeneratedValue
	private long id;
	@Range(max=100)
	private String name;
	@Enumerated(value=EnumType.STRING)
	private  ProductBackLogType backLogType;

	public ProductBackLog() {
		// TODO Auto-generated constructor stub
	}

}
