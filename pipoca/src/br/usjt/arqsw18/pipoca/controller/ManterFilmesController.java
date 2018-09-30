package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.entity.Genero;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;
import br.usjt.arqsw18.pipoca.model.service.GeneroService;

@Controller
public class ManterFilmesController {
	
	@Autowired
	private FilmeService fService;
	
	@Autowired
	private GeneroService gService;

	@RequestMapping("index")
	public String iniciar() {
		return "index";
	}

	@RequestMapping("/novo_filme")
	public String novo(Model model, HttpSession session) {
		try {
			List<Genero> generos = gService.listarGeneros();
			session.setAttribute("generos", generos);
			return "CriarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/criar_filme")
	public String criarFilme(@Valid Filme filme, BindingResult erros, Model model) {
		try {
			if (!erros.hasErrors()) {
				Genero genero = new Genero();
				genero.setId(filme.getGenero().getId());
				genero.setNome(gService.buscarGenero(genero.getId()).getNome());
				filme.setGenero(genero);

				filme = fService.inserirFilme(filme);

				model.addAttribute("filme", filme);

				return "VisualizarFilme";
			} else {
				return "CriarFilme";
			}
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/reiniciar_lista")
	public String reiniciarLista(HttpSession session) {
		session.setAttribute("lista", null);
		return "ListarFilmes";
	}

	@RequestMapping("/listar_filmes")
	public String listarFilmes(HttpSession session, Model model, String chave) {
		try {
			// HttpSession session = ((HttpServletRequest) model).getSession();

			ArrayList<Filme> lista;
			if (chave != null && chave.length() > 0) {
				lista = (ArrayList<Filme>) fService.listarFilmes(chave);
			} else {
				lista = (ArrayList<Filme>) fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			return "ListarFilmes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
}
