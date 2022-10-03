package com.nttdata.crudEcuador.controller;

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
	public ResponseEntity<Cliente> findById(@PathVariable(value ="id") String id) throws ResourceNotFoundException{
		
		Cliente oCliente = clienteService.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No existe el clienteId: "+id));
		
		return ResponseEntity.ok(oCliente);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@RequestBody Cliente clienteDetail,@PathVariable(value ="id") String id)throws ResourceNotFoundException{
		Cliente cliente = clienteService.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No existe el clienteId: "+id));
		
		cliente.setContrasenia(clienteDetail.getContrasenia());
		cliente.setEstado(clienteDetail.isEstado());
		cliente.setPersona(clienteDetail.getPersona());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(cliente));
	}
}
