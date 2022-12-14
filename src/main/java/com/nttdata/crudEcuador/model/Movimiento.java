package com.nttdata.crudEcuador.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="movimiento")
@Getter
@Setter
public class Movimiento {
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID movimientoid;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date  fecha;
	
	@Column
	private String tipo;
	
	@Column
	private Double valor;
	
	@Column
	private Double saldo;
	
	@Column 
	private Double saldoInicial;
	
	@ManyToOne
	private Cuenta cuenta;
	 
	
}
