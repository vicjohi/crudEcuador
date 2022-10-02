package com.nttdata.crudEcuador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.crudEcuador.model.Cliente;
import com.nttdata.crudEcuador.model.Movimiento;
import com.nttdata.crudEcuador.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;
	
	@PostMapping
	public ResponseEntity<Movimiento> createCliente(@RequestBody Movimiento movimiento ){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.saveMovimiento(movimiento));		
	}
	
}
