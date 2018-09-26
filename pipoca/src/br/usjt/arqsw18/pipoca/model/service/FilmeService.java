package br.usjt.arqsw18.pipoca.model.service;

import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.entity.Filme;


public class FilmeService {
	public String inserirFilme(Filme filme) {		
		return filme.getTitulo();
	}

}
