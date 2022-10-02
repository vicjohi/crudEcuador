package com.nttdata.crudEcuador.service;


import java.util.UUID;

import com.nttdata.crudEcuador.model.Persona;

public interface PersonaService {

	public Persona savePersona(Persona persona);
	public void deletePersona (UUID uuid);
}
