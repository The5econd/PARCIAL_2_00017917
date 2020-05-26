package com.uca.capas.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.TodoService;

@Controller
public class MainController {

	@Autowired
	private TodoService service;
	
	@RequestMapping("/index")
	public ModelAndView initMain(){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/categoria")
	public ModelAndView categoria(){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("categoria", new Categoria());
		mav.setViewName("guardar_cat");
		return mav;
	}
	
	@RequestMapping("/libro")
	public ModelAndView libro(){
		ModelAndView mav = new ModelAndView();
		
		List<Categoria> categorias = null;
		try{
			categorias = service.findAllCategoria();
		}catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("categorias", categorias);
		mav.addObject("libro", new Libro());
		mav.setViewName("guardar_lib");
		return mav;
	}
	
	@RequestMapping("/saveCategoria")
	public ModelAndView saveCat(@Valid @ModelAttribute Categoria categoria, BindingResult result) throws ParseException{
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.setViewName("guardar_cat");
		}else {
			service.saveCategoria(categoria);
			mav.addObject("mensaje", "categoria guardada con exito");
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping("/saveLibro")
	public ModelAndView guardar(@Valid @ModelAttribute Libro libro, BindingResult result) throws ParseException{
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			List<Categoria> categorias = null;
			try{
				categorias = service.findAllCategoria();
			}catch(Exception e){
				e.printStackTrace();
			}
			mav.addObject("categorias", categorias);
			mav.setViewName("guardar_lib");
		}else {

			List<Categoria> categorias = null;
			try{
				categorias = service.findAllCategoria();
			}catch(Exception e){
				e.printStackTrace();
			}
			mav.addObject("categorias", categorias);
			Date date = new Date(System.currentTimeMillis());
            libro.setFecha(date);
			if(libro.getEstado()==null) {
                libro.setEstado(false);
            }
			service.save(libro);
			mav.addObject("mensaje", "libro guardado con exito");
			mav.setViewName("index");
		}
		return mav;
	}
	
	@RequestMapping("/listadoLibros")
	public ModelAndView listado(){
		ModelAndView mav = new ModelAndView();
		List<Libro> libros = null;
		try{
			libros = service.findAll();
		}catch(Exception e){
			e.printStackTrace();
		}
		List<Categoria> categorias = null;
		try{
			categorias = service.findAllCategoria();
		}catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("categorias", categorias);
		mav.addObject("libros", libros);
		mav.setViewName("libro_list");
		return mav;
	}
}
