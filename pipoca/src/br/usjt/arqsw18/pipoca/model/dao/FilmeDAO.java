package br.usjt.arqsw18.pipoca.model.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;

@Repository
public class FilmeDAO {
	
	@PersistenceContext //Injeção de dependencia do EntityManager
	EntityManager manager;
	
	public int inserirFilme(Filme filme) throws IOException {
		manager.persist(filme);		
		return filme.getId();
	}	
	
	public Filme buscarFilme(int id) throws IOException{
		return manager.find(Filme.class, id);		
	}
	
	public void atualizarFilme(Filme filme) throws IOException {
		manager.merge(filme);		
	}
	
	public void removerFilme(Filme filme) throws IOException {
		manager.remove(manager.find(Filme.class, filme.getId()));
	}
	
	public List<Filme> listarFilmes(Genero genero) throws IOException {
		String jpql = "select f from Filme f where f.genero = :genero";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("genero", genero);
		
		@SuppressWarnings("unchecked")
		List<Filme> result = query.getResultList();
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Filme> listarFilmes(double popularidade) throws IOException {
		String jpql = "select f from Filme f where f.popularidade = :chave";
		Query query = manager.createQuery(jpql);
		query.setParameter("chave", popularidade);
		List<Filme> result = query.getResultList();
		return result;
	}
	
	public List<Filme> listarFilmes(Date dtInicio, Date dtFinal) throws IOException {

		String jpql = "select f from Filme f where f.dataLancamento between :dtInicio and :dtFinal";
		Query query = manager.createQuery(jpql);
		
		query.setParameter("dtInicio", dtInicio);
		query.setParameter("dtFinal", dtFinal);

		@SuppressWarnings("unchecked")
		List<Filme> result = query.getResultList();
			
		return result;
	}
	
	
	public List<Filme> listarFilmes(String chave) throws IOException {

		String jpql = "select f from Filme f where f.titulo like :chave";

		Query query = manager.createQuery(jpql);
		query.setParameter("chave", "%" + chave + "%");

		@SuppressWarnings("unchecked")
		List<Filme> result = query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Filme> listarFilmes() throws IOException {
		return manager.createQuery("select f from Filme f").getResultList();
	}
}
