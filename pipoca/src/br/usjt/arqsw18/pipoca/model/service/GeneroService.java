package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.dao.GeneroDAO;
import br.usjt.arqsw18.pipoca.model.entity.Genero;

@Service
public class GeneroService {
	private GeneroDAO dao;
	
	public GeneroService() {
		this.dao = new GeneroDAO();
	}
	
	public Genero buscarGenero(int id) throws IOException {
		return dao.buscarGenero(id);
	}
	
	public List<Genero> listarGeneros() throws IOException{
		return dao.listarGeneros();
	}

}
