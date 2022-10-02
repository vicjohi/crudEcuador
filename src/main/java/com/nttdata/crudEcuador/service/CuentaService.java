package com.nttdata.crudEcuador.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nttdata.crudEcuador.model.Cuenta;

public interface CuentaService {
	
	Cuenta saveCuenta(Cuenta cuenta);
	
	Iterable<Cuenta> findAll();
	
	Page<Cuenta> findAll(Pageable pageable);
	
	Optional<Cuenta> findById(String id);
	
	void deleteById(String id);
}
