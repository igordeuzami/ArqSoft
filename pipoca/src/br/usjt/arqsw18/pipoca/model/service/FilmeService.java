package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw18.pipoca.model.dao.FilmeDAO;
import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;

@Service
public class FilmeService {
	private FilmeDAO dao;
	
	@Autowired
	public FilmeService(FilmeDAO fdao) {
		dao = fdao;
	}
	
	public Filme buscarFilme(int id) throws IOException{
		return dao.buscarFilme(id);
	}
	
	@Transactional
	public Filme inserirFilme(Filme filme) throws IOException {
		int id = dao.inserirFilme(filme);
		filme.setId(id);
		return filme;
	}
	
	@Transactional
	public void excluirFilme(Filme filme) throws IOException {
		dao.removerFilme(filme);
	}
	
	@Transactional
	public void atualizarFilme(Filme filme) throws IOException {
		dao.atualizarFilme(filme);
	}

	public List<Filme> listarFilmes(String chave) throws IOException{
		return dao.listarFilmes(chave);
	}
	
	public List<Filme> listarFilmes(Genero genero) throws IOException{
		return dao.listarFilmes(genero);
	}
	
	public List<Filme> listarFilmes(double popularidade) throws IOException{
		return dao.listarFilmes(popularidade);
	}
	
	public List<Filme> listarFilmes(Date dtInicio, Date dtFinal) throws IOException{
		return dao.listarFilmes(dtInicio, dtFinal);
	}

	public List<Filme> listarFilmes() throws IOException{
		return dao.listarFilmes();
	}
}
