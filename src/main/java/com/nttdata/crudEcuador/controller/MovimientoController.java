package com.nttdata.crudEcuador.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.crudEcuador.exception.ResourceNotFoundException;
import com.nttdata.crudEcuador.model.Movimiento;
import com.nttdata.crudEcuador.model.Reporte;
import com.nttdata.crudEcuador.service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	private MovimientoService movimientoService;
	
	@PostMapping
	public ResponseEntity<Movimiento> createCliente(@RequestBody Movimiento movimiento ) throws ResourceNotFoundException{
		
		return ResponseEntity.status(HttpStatus.CREATED).body(movimientoService.saveMovimiento(movimiento));		
	}
	
	@GetMapping("/reporte")
	public List<Reporte> reporte(@RequestParam Map<String,String> movParams ){
		List<Reporte> reportes= StreamSupport
				.stream(movimientoService.reporte(movParams.get("clienteid"),movParams.get("fechaInicial"),movParams.get("fechaFinal")).spliterator(), false)
				.collect(Collectors.toList());
		return reportes;
	}
}
