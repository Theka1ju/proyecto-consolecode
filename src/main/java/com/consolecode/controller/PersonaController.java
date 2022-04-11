package com.consolecode.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.consolecode.model.Persona;
import com.consolecode.service.IPersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {
	@Autowired
	IPersonaService service;
	
	@GetMapping
	public ResponseEntity<List<Persona>> listar(){
		List<Persona> obj = service.listar();
		return new ResponseEntity<List<Persona>>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Persona> registrar(@RequestBody Persona persona){
		Persona obj = service.registrar(persona);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPersona()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping
	public ResponseEntity<Persona> actualizar(@RequestBody Persona persona){
		Persona obj=service.actualizar(persona);
		return new ResponseEntity<Persona>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Persona obj = service.ListarPorId(id);
		if(obj == null) {
			throw new Exception("No se encontró el id");
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer codigo) throws Exception {
		Persona obj = service.ListarPorId(codigo);
		if(obj == null) {
			throw new Exception("No se encontró el id");
		}
		return new ResponseEntity<Persona>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Persona>> listPageable(Pageable pageable) throws Exception {
		Page<Persona> persona = service.listPageable(pageable);
		return new ResponseEntity<Page<Persona>>(persona, HttpStatus.OK);
	}
}
