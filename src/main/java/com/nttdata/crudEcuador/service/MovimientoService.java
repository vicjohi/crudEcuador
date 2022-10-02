package com.nttdata.crudEcuador.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nttdata.crudEcuador.model.Movimiento;

public interface MovimientoService {
	
	Movimiento saveMovimiento(Movimiento movimiento);
	
	Iterable<Movimiento> findAll();
	
	Page<Movimiento> findAll(Pageable pageable);
	
	Optional<Movimiento> findById(String id);
	
	void deleteById(String id);
}
