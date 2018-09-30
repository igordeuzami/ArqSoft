package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;

@RestController
public class ManterFilmesRestController {

	@Autowired
	FilmeService filmeService;

	@RequestMapping(method = RequestMethod.GET, value = "rest/filmes")
	public @ResponseBody List<Filme> listarFilmes() {
		System.out.println("rest/filmes");
		try {
			return filmeService.listarFilmes();
		} catch (IOException e) {

			e.printStackTrace();
			return new ArrayList<Filme>();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "rest/filmes")
	public ResponseEntity<Filme> criarFilme(@RequestBody Filme filme) {
		try {
			filmeService.inserirFilme(filme);
			return new ResponseEntity<Filme>(filme, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Filme>(filme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "rest/filmes")
	public ResponseEntity<Filme> excluirFilme(@RequestBody Filme filme) {
		try {
			filmeService.excluirFilme(filme);
			return new ResponseEntity<Filme>(filme, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Filme>(filme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "rest/filmes")
	public ResponseEntity<Filme> atualizarFilme(@RequestBody Filme filme) {
		try {
			filmeService.atualizarFilme(filme);
			return new ResponseEntity<Filme>(filme, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Filme>(filme, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/filmes/filtro/{chave}")
	public ResponseEntity<List<Filme>> listarFilme(@PathVariable("chave") String chave) {
		List<Filme> lista = null;
	
		try {
			lista = filmeService.listarFilmes(chave);			
			return new ResponseEntity<List<Filme>>(lista, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Filme>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/filmes/genero/{idGenero}")
	public ResponseEntity<List<Filme>> listarFilmePorGenero(@PathVariable("idGenero") int idGenero) {
		List<Filme> lista = null;
	
		try {
			Genero genero = new Genero();
			genero.setId(idGenero);

			lista = filmeService.listarFilmes(genero);
			return new ResponseEntity<List<Filme>>(lista, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Filme>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/filmes/popularidade/{popularidade}")
	public ResponseEntity<List<Filme>> listarFilmePorPopularidade(@PathVariable("popularidade") double popularidade) {
		List<Filme> lista = null;
	
		try {
			lista = filmeService.listarFilmes(popularidade);
			return new ResponseEntity<List<Filme>>(lista, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Filme>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/filmes/periodo")
	public ResponseEntity<List<Filme>> listarFilme(@RequestParam("dtInicial")Date dtInicial, @RequestParam("dtFinal") Date dtFinal) {
		List<Filme> lista = null;
	
		try {
			System.out.println(dtInicial);
			System.out.println(dtFinal);
			
			lista = filmeService.listarFilmes(dtInicial, dtFinal);
			return new ResponseEntity<List<Filme>>(lista, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Filme>>(lista, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
