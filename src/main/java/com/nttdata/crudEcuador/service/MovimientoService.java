package com.nttdata.crudEcuador.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nttdata.crudEcuador.exception.ResourceNotFoundException;
import com.nttdata.crudEcuador.model.Movimiento;
import com.nttdata.crudEcuador.model.Reporte;

public interface MovimientoService {
	
	Movimiento saveMovimiento(Movimiento movimiento) throws ResourceNotFoundException;
	
	Iterable<Movimiento> findAll();
	
	Page<Movimiento> findAll(Pageable pageable);
	
	Optional<Movimiento> findById(String id);
	
	void deleteById(String id);
	
	List<Reporte> reporte(String clienteid, String fechaInicial,String fechaFinal);
}
