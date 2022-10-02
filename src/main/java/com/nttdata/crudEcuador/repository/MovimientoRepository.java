package com.nttdata.crudEcuador.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.crudEcuador.model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, UUID>{

}
