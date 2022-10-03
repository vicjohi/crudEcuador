package com.nttdata.crudEcuador.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.crudEcuador.exception.ResourceNotFoundException;
import com.nttdata.crudEcuador.model.Cuenta;
import com.nttdata.crudEcuador.model.Movimiento;
import com.nttdata.crudEcuador.model.Reporte;
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
	public Movimiento saveMovimiento(Movimiento movimiento) throws ResourceNotFoundException{
		Double valorMovimiento=movimiento.getValor();
		if(movimiento.getTipo().equalsIgnoreCase("retiro")) {
			valorMovimiento=valorMovimiento*-1;
		}		
		Optional<Cuenta> cuenta=cuentaService.findById(movimiento.getCuenta().getNumerocuenta().toString());		
		if(!cuenta.isPresent()) {
			throw  new  ResourceNotFoundException("no existe la cuentaId: "+movimiento.getCuenta().getNumerocuenta());
		}		
		movimiento.setSaldoInicial(cuenta.get().getSaldoinicial());
		movimiento.setFecha(Calendar.getInstance().getTime());
	
		cuentaService.saveCuenta(calcularSaldo(cuenta.get(),valorMovimiento));
		//saldo actualizado
		movimiento.setSaldo(cuenta.get().getSaldoinicial());
		
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

	private Cuenta calcularSaldo(Cuenta saldoInicial,Double valorMovimeinto ) throws ResourceNotFoundException {
		
		Double saldo =0.0;

		if(saldoInicial.getSaldoinicial()==0 && (valorMovimeinto<0)) {
			System.out.println("Saldo no Disponible");
			throw new ResourceNotFoundException("Saldo no Disponible");
		}
		
		saldo = saldoInicial.getSaldoinicial() + valorMovimeinto;
		
		if(saldo<0) {
			System.out.println("Saldo insuficiente, saldo actual "+saldoInicial.getSaldoinicial());
			throw new ResourceNotFoundException("Saldo insuficiente, saldo actual "+saldoInicial.getSaldoinicial());
		}
		
		saldoInicial.setSaldoinicial(saldo);
		
		return saldoInicial;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reporte> reporte(String clienteid, String fechaInicial,String fechaFinal) {
		List<Movimiento> movimientos=null;
		List<Reporte> reportes=new ArrayList<>();
			try {
				movimientos = movimientoRepository.findReporte(UUID.fromString(clienteid),
						new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicial), 
						new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinal));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			movimientos.forEach(x ->{
				Reporte reporte = new Reporte();
				reporte.setCliente(x.getCuenta().getCliente().getPersona().getNombre());
				reporte.setEstadoCuenta(x.getCuenta().getEstado());
				reporte.setFecha(x.getFecha());
				reporte.setNumeroCuenta(x.getCuenta().getNumerocuenta());
				reporte.setSaldoDisponible(x.getSaldo());
				reporte.setSaldoInicial(x.getSaldoInicial());
				reporte.setTipoCuenta(x.getCuenta().getTipo());
				reporte.setValorMovimiento(x.getValor());
				reportes.add(reporte);
			});

			
		return reportes;
	}
		
	
}
