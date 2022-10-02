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

import com.nttdata.crudEcuador.model.Cliente;
import com.nttdata.crudEcuador.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente ){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(cliente));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> findById(@PathVariable(value ="id") String id){
		Optional<Cliente> oCliente = clienteService.findById(id);
		if(!oCliente.isPresent()) {
			return ResponseEntity.notFound().build();			
		}
		return ResponseEntity.ok(oCliente);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@RequestBody Cliente clienteDetail,@PathVariable(value ="id") String clienteId){
		Optional<Cliente> cliente = clienteService.findById(clienteId);
		if(!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.get().setContrasenia(clienteDetail.getContrasenia());
		cliente.get().setEstado(clienteDetail.isEstado());
		cliente.get().setPersona(clienteDetail.getPersona());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(clienteDetail));
	}
}
