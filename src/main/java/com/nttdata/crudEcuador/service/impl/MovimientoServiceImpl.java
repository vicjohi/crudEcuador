package com.nttdata.crudEcuador.service.impl;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.crudEcuador.model.Cuenta;
import com.nttdata.crudEcuador.model.Movimiento;
import com.nttdata.crudEcuador.repository.MovimientoRepository;
import com.nttdata.crudEcuador.service.CuentaService;
import com.nttdata.crudEcuador.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	private MovimientoRepository movimientoRepository;
	@Autowired
	private CuentaService cuentaService;
	
	@Override
	@Transactional
	public Movimiento saveMovimiento(Movimiento movimiento) {
		Double valorMovimiento=movimiento.getValor();
		if(movimiento.getTipo().equalsIgnoreCase("retiro")) {
			valorMovimiento=valorMovimiento*-1;
		}		
		Optional<Cuenta> cuenta=cuentaService.findById(movimiento.getCuenta().getNumerocuenta().toString());		
		if(!cuenta.isPresent()) {
			System.out.println("no existe cuenta");
		}		
		movimiento.setFecha(Calendar.getInstance());
		movimiento.setSaldo(cuenta.get().getSaldoinicial());	
		
		cuentaService.saveCuenta(calcularSaldo(cuenta.get(),valorMovimiento));
		movimiento.setCuenta(cuenta.get());
		
		return movimientoRepository.save(movimiento);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Movimiento> findAll() {
		return movimientoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Movimiento> findAll(Pageable pageable) {
		return movimientoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Movimiento> findById(String id) {
		return movimientoRepository.findById(UUID.fromString(id));
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		movimientoRepository.deleteById(UUID.fromString(id));
	}

	private Cuenta calcularSaldo(Cuenta saldoInicial,Double valorMovimeinto ) {
		
		Double saldo =0.0;

		if(saldoInicial.getSaldoinicial()==0 && (valorMovimeinto<0)) {
			System.out.println("Saldo no Disponible");
		}
		
		saldo = saldoInicial.getSaldoinicial() + valorMovimeinto;
		
		if(saldo<0) {
			System.out.println("Saldo insuficiente, saldo actual "+saldoInicial.getSaldoinicial());
		}
		
		saldoInicial.setSaldoinicial(saldo);
		
		return saldoInicial;
	}
}
