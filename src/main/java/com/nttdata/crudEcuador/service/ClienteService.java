package com.nttdata.crudEcuador.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nttdata.crudEcuador.model.Cliente;

public interface ClienteService {
	
	Cliente saveCliente(Cliente cliente);
	
	Iterable<Cliente> findAll();
	
	Page<Cliente> findAll(Pageable pageable);
	
	Optional<Cliente> findById(String id);
		
	void deleteById(String id);
}
