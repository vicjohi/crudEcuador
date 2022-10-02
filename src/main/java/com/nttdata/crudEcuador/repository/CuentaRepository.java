package com.nttdata.crudEcuador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.crudEcuador.model.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Long> {

}
