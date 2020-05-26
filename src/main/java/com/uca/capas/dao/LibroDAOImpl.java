package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.Libro;

@Repository
public class LibroDAOImpl implements LibroDAO{

	@PersistenceContext(unitName="capas")
	private EntityManager eManager;
	
	@Override
	public List<Libro> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_libro");
		Query query = eManager.createNativeQuery(sb.toString(), Libro.class);
		List<Libro> res = query.getResultList();	
		return res;
	}

	@Override
	public Libro findOne(Integer code) throws DataAccessException {
		Libro contribuyente = eManager.find(Libro.class, code);
		return contribuyente;
	}

	@Override
	public void insert(Libro libro) throws DataAccessException {
		eManager.persist(libro);
	}

	@Override
	@Transactional
	public void save(Libro libro) throws DataAccessException {
		try {
			if(libro.getCodigoLibro() == null) {
				eManager.persist(libro);
			}else {
				eManager.merge(libro);
				eManager.flush();
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer codigoLibro) throws DataAccessException {
		Libro libro = eManager.find(Libro.class, codigoLibro);
		eManager.remove(libro);
	}
}
