package com.nttdata.crudEcuador.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.crudEcuador.model.Persona;
import com.nttdata.crudEcuador.service.PersonaService;


@RestController
@RequestMapping("/api/persona")

public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@PostMapping()
	public ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
		
		return new ResponseEntity<>(personaService.savePersona(persona),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletePersona(@PathVariable("id") String id) {
		
		
		personaService.deletePersona(UUID.fromString(id));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
