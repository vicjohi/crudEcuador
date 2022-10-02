package com.nttdata.crudEcuador.service.impl;


import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.crudEcuador.model.Cliente;
import com.nttdata.crudEcuador.repository.ClienteRepository;
import com.nttdata.crudEcuador.service.ClienteService;
import com.nttdata.crudEcuador.service.PersonaService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PersonaService personaService;
	
	@Override
	@Transactional
	public Cliente saveCliente(Cliente cliente) {
		
		personaService.savePersona(cliente.getPersona());
		return clienteRepository.save(cliente);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	@Override	
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {	
		return clienteRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(String id) {		
		return clienteRepository.findById(UUID.fromString(id));
	}
		
	@Override
	@Transactional
	public void deleteById(String id) {
		clienteRepository.deleteById(UUID.fromString(id));
	}

}
