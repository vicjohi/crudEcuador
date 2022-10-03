package com.nttdata.crudEcuador.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reporte implements Serializable {
	
	private static final long serialVersionUID = 7057694299573586689L;

	private Date fecha;
	
	private String cliente;
	
	private Long numeroCuenta;
	
	private String tipoCuenta;
	
	private Double saldoInicial;
	
	private Boolean estadoCuenta;
	
	private Double valorMovimiento;
	
	private Double saldoDisponible;
}
