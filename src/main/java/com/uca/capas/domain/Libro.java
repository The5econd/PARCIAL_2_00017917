package com.uca.capas.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(schema="public", name = "cat_libro")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="c_libro")
	private Integer codigoLibro;
	
	@Column(name = "s_titulo")
	@NotEmpty(message = "el campo no puede estar vacio")
	@Size(max = 500, message = "no mas de 500 caracteres")
	private String titulo;
	
	@Column(name = "s_autor")
	@NotEmpty(message = "el campo no puede estar vacio")
	@Size(max = 150, message = "no mas de 150 caracteres")
	private String autor;
	
	@Column(name = "isbn")
	@NotEmpty(message = "el campo no puede estar vacio")
	@Pattern(regexp="^$|[0-9]{10}", message="Solo 10 digitos porfavor")
	private String isbn;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="c_categoria")
	private Categoria categoria;
	
	@Column(name = "f_ingreso")
	private Date fecha;
	
	@Column(name = "b_estado")
	private Boolean estado;
	
	public Libro() {}
	
	public String getEstadoDelegate() {
        if(this.estado == null)return "";
        else {
            return estado == true ?"Activo":"Inactivo";
        }
	}
	
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getCodigoLibro() {
		return codigoLibro;
	}

	public void setCodigoLibro(Integer codigoLibro) {
		this.codigoLibro = codigoLibro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getFecha() throws ParseException {
		DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(fecha));
        String fecha = f.format(date);
        return fecha;
	}

	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
