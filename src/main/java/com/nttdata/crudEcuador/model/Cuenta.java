package com.nttdata.crudEcuador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="cuenta")
@Getter
@Setter
public class Cuenta {
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long numerocuenta;
	
	@Column
	private String tipo;
	
	@Column
	private Double saldoinicial;
	
	@Column
	private Boolean estado;
	
	@ManyToOne
	private Cliente cliente;
	
}
