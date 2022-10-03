package com.nttdata.crudEcuador.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nttdata.crudEcuador.model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, UUID>{
	
	List<Movimiento> findAllByFechaBetween(Date start,Date end);
	@Query("select a from Movimiento a where a.cuenta.cliente.clienteid = :clienteid and a.fecha BETWEEN :fechaInicial AND :fechaFinal ")
	List<Movimiento> findReporte(UUID clienteid,Date fechaInicial,Date fechaFinal);
}
