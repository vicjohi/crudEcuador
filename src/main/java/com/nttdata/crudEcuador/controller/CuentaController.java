package com.nttdata.crudEcuador.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.crudEcuador.exception.ResourceNotFoundException;
import com.nttdata.crudEcuador.model.Cuenta;
import com.nttdata.crudEcuador.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;
	
	@PostMapping
	public ResponseEntity<Cuenta> createCliente(@RequestBody Cuenta cuenta ){
				
		return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.saveCuenta(cuenta));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> findById(@PathVariable(value ="id") String id) throws ResourceNotFoundException{
		Cuenta oCuenta = cuentaService.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No existe el numero de CuentaId: "+id));
	
		return ResponseEntity.ok(oCuenta);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cuenta> update(@RequestBody Cuenta cuentaDetail,@PathVariable(value ="id") String id) throws ResourceNotFoundException{
		Cuenta cuenta = cuentaService.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No existe la cuentaId: "+id));
		
		cuenta.setCliente(cuentaDetail.getCliente());
		cuenta.setEstado(cuentaDetail.getEstado());
		cuenta.setSaldoinicial(cuentaDetail.getSaldoinicial());
		cuenta.setTipo(cuentaDetail.getTipo());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.saveCuenta(cuenta));
	}
}
