package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="cat_categoria")
public class Categoria {


	@Id
	@Column(name = "c_categoria")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoCategoria;
	
	@Column(name = "s_categoria")
	@NotEmpty(message="El campo nombre categoria no puede estar vacio")
	@Size(max = 50, message = "El campo sobrepasa la cantidad de 50 caracteres")
	private String nombreCategoria;
	
	public Categoria () {}

	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	
}
