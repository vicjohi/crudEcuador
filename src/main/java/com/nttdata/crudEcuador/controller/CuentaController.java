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

import com.nttdata.crudEcuador.model.Cuenta;
import com.nttdata.crudEcuador.service.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;
	
	@PostMapping
	public ResponseEntity<Cuenta> createCliente(@RequestBody Cuenta Cuenta ){
		return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.saveCuenta(Cuenta));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cuenta>> findById(@PathVariable(value ="id") String id){
		Optional<Cuenta> oCuenta = cuentaService.findById(id);
		if(!oCuenta.isPresent()) {
			return ResponseEntity.notFound().build();			
		}
		return ResponseEntity.ok(oCuenta);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cuenta> update(@RequestBody Cuenta cuentaDetail,@PathVariable(value ="id") String cuentaId){
		Optional<Cuenta> cuenta = cuentaService.findById(cuentaId);
		if(!cuenta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		cuenta.get().setCliente(cuentaDetail.getCliente());
		cuenta.get().setEstado(cuentaDetail.getEstado());
		cuenta.get().setSaldoinicial(cuentaDetail.getSaldoinicial());
		cuenta.get().setTipo(cuentaDetail.getTipo());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.saveCuenta(cuentaDetail));
	}
}
