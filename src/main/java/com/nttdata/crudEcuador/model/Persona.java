package com.nttdata.crudEcuador.model;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name="persona")
@Getter
@Setter
public class Persona {
	
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID personaid;

	@Column
	private String nombre;
	
	@Column
	private String genero;
	
	@Column
	private Integer edad;
	
	@Column
	private String identificacion;
	
	@Column
	private String direccion;
	
	@Column
	private String telefono;
	
}
