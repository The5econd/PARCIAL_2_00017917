package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;


@Repository
public class CategoriaDAOImpl implements CategoriaDAO{

	@PersistenceContext(unitName = "capas")
	private EntityManager eManager;
	
	@Override
	public List<Categoria> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_categoria");
		Query query = eManager.createNativeQuery(sb.toString(), Categoria.class);
		List<Categoria> res = query.getResultList();	
		return res;
	}

	@Override
	public Categoria findOne(Integer code) throws DataAccessException {
		Categoria categoria = eManager.find(Categoria.class, code);
		return categoria;
	}
	
	@Override
	public void insert(Categoria categoria) throws DataAccessException {
		eManager.persist(categoria);
	}

	@Override
	@Transactional
	public void save(Categoria categoria) throws DataAccessException {
		try {
			if(categoria.getCodigoCategoria() == null) {
				eManager.persist(categoria);
			}else {
				eManager.merge(categoria);
				eManager.flush();
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
		
	}

}
