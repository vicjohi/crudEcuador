package com.nttdata.crudEcuador.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nttdata.crudEcuador.model.Cliente;
import com.nttdata.crudEcuador.model.Cuenta;
import com.nttdata.crudEcuador.repository.CuentaRepository;
import com.nttdata.crudEcuador.service.ClienteService;
import com.nttdata.crudEcuador.service.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService{
	
	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private ClienteService clienteService;
	
	@Override
	@Transactional
	public Cuenta saveCuenta(Cuenta cuenta) {
		
		Optional<Cliente> cliente =clienteService.findById(cuenta.getCliente().getClienteid().toString());
		if(!cliente.isPresent()) {
			System.out.println("No existe el cliente");
		}
		
		cuenta.setCliente(cliente.get());		
		
		return cuentaRepository.save(cuenta);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Cuenta> findAll() {
		return cuentaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cuenta> findAll(Pageable pageable) {
		return cuentaRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cuenta> findById(String id) {
		return cuentaRepository.findById(Long.parseLong(id));
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		cuentaRepository.deleteById(Long.parseLong(id));
	}

}
