package com.nttdata.crudEcuador.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.crudEcuador.model.Persona;
import com.nttdata.crudEcuador.repository.PersonaRepository;
import com.nttdata.crudEcuador.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{
	
@Autowired
private PersonaRepository personaRepository;

@Override
@Transactional
public Persona savePersona(Persona persona) {
	return personaRepository.save(persona);
	
}

@Override
@Transactional
public void deletePersona(UUID uuid) {
	personaRepository.deleteById(uuid);
	
}

}
