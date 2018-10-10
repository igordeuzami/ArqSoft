package br.usjt.arqsw18.pipoca.model.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import br.usjt.arqsw18.pipoca.model.entity.Filme;

@Repository
public class FilmeDAO {
	@PersistenceContext
	EntityManager manager;
	
	public int inserirFilme(Filme filme) throws IOException {
		manager.persist(filme);
		return filme.getId();
	}

	public Filme buscarFilme(int id) throws IOException{
		return manager.find(Filme.class, id);
	}
	
	public void atualizarFilme(Filme filme) throws IOException{
		System.out.println(filme);
		manager.merge(filme);
	}
	
	public void removerFilme(Filme filme) throws IOException{
		manager.remove(manager.find(Filme.class, filme.getId()));
	}
	
	

	public List<Filme> listarFilmes(String chave) throws IOException {
		
		String jpql = "select f from Filme f where f.titulo like :chave";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("chave", "%"+chave+"%");

		List<Filme> result = query.getResultList();
		return result;
	}
	
	public List<Filme> listarFilmes() throws IOException {
		return manager.createQuery("select f from Filme f").getResultList();
	}

}
