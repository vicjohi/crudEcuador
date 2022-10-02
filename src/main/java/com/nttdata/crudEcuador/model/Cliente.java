package com.nttdata.crudEcuador.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Table (name="cliente")
@Getter
@Setter
public class Cliente {
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID clienteid;
	
	@Column
	private String contrasenia;
	
	@Column
	private boolean estado;
	
	@OneToOne
	private Persona persona;
	
}
