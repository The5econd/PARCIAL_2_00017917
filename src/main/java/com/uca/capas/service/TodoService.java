package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;


public interface TodoService {

public List<Libro> findAll() throws DataAccessException;
	
	public Libro findOne(Integer code) throws DataAccessException;
	
	public void save(Libro libro) throws DataAccessException;
	
	public void delete(Integer codigoLibro) throws DataAccessException;
	
	public List<Categoria> findAllCategoria()throws DataAccessException;
	
	public Categoria findOneCategoria(Integer code)throws DataAccessException;
	
	public void saveCategoria(Categoria categoria) throws DataAccessException;
}
