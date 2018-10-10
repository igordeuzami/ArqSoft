package br.usjt.arqsw18.pipoca.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.usjt.arqsw18.pipoca.model.dao.FilmeDAO;
import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;

@Service
public class FilmeService {
	private FilmeDAO dao;
	private GeneroService gs;
	public static final String API_KEY = "b6b4c4e94d7e361a14398567b0e5b533";
	public static final String URL = "https://api.themoviedb.org/3/movie/popular?api_key="+ API_KEY +"&language=en-US";
	public static final String IMG = "https://image.tmdb.org/t/p/w500/";
	
	@Autowired
	public FilmeService(FilmeDAO fdao) {
		dao = fdao;
		this.gs = gs;	
		
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

	public List<Filme> listarFilmes() throws IOException{
		return dao.listarFilmes();
	}
	
	public List<FilmeTMDb> carregarFilmes() {
		RestTemplate rt = new RestTemplate();
		ResultadoTMDb resultado = rt.getForObject(URL, ResultadoTMDb.class);
		//System.out.println(resultado);
		List<FilmeTMDb> lista = resultado.getResults();
		for(FilmeTMDb filmeTMDb:lista) {
			Filme filme = new Filme();
			filme.setTitulo(filmeTMDb.getTitle());
			filme.setDescricao(filmeTMDb.getOverview());
			filme.setPopularidade(filmeTMDb.getPopularity());
			filme.setPosterPath(IMG + filmeTMDb.getPoster_path());
			System.out.println(lista);
/*			try {
				Genero genero = gs.buscarGenero(filmeTMDb.getGenre_ids()[0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		return lista;
	}

}
