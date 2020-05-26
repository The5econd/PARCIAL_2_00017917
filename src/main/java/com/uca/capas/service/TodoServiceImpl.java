package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.dao.CategoriaDAO;
import com.uca.capas.dao.LibroDAO;
import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	LibroDAO libroDAO;
	@Autowired
	CategoriaDAO categoriaDAO;
	
	@Override
	public List<Libro> findAll() throws DataAccessException {
		return libroDAO.findAll();
	}
	
	@Override
	public Libro findOne(Integer code) throws DataAccessException {
		return libroDAO.findOne(code);
	}

	@Override
	@Transactional
	public void save(Libro libro) throws DataAccessException {
		libroDAO.save(libro);
	}

	@Override
	@Transactional
	public void delete(Integer codigoLibro) throws DataAccessException {
		libroDAO.delete(codigoLibro);
	}

	
	@Override
	@Transactional
	public void saveCategoria(Categoria categoria) throws DataAccessException {
		categoriaDAO.save(categoria);
	}
	
	@Override
	public Categoria findOneCategoria(Integer code) throws DataAccessException {
		return categoriaDAO.findOne(code);
	}

	@Override
	public List<Categoria> findAllCategoria() throws DataAccessException {
		return categoriaDAO.findAll();
	}
}
