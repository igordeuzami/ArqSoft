package br.usjt.arqsw18.pipoca.controller;

import java.io.IOException;
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
import br.usjt.arqsw18.pipoca.model.service.FilmeTMDb;
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

			List<Filme> lista;
			if (chave != null && chave.length() > 0) {
				lista = fService.listarFilmes(chave);
			} else {
				lista = fService.listarFilmes();
			}
			session.setAttribute("lista", lista);
			return "ListarFilmes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	@RequestMapping("/listar_filmes_tmdb")
	public String listarFilmesTmdb(HttpSession session, Model model) throws IOException {
		List<FilmeTMDb> lista;
		lista = fService.carregarFilmes();
		session.setAttribute("lista", lista);
		return "ListarFilmesTMDB";
	}

	@RequestMapping("/visualizar_filme")
	public String visualizarFilme(Filme filme, Model model) {
		try {
			filme = fService.buscarFilme(filme.getId());
			model.addAttribute("filme", filme);
			return "VisualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	@RequestMapping("/visualizar_filme_tmdb")
	public String visualizarFilmeTMDB(Filme filme, Model model) {
		try {
			filme = fService.buscarFilme(filme.getId());
			model.addAttribute("filme", filme);
			return "VisualizarFilmeTMDB";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	@RequestMapping("/excluir_filme")
	public String excluirFilme(Filme filme, HttpSession session, Model model) {
		try {
			fService.excluirFilme(filme);
			List<Filme> filmes = (List<Filme>) session.getAttribute("lista");
			session.setAttribute("lista", removerDaLista(filme, filmes));
			return "ListarFilmes";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	private List<Filme> removerDaLista(Filme filme, List<Filme> filmes){
		for(int i = 0; i < filmes.size(); i++) {
			if(filme.getId() == filmes.get(i).getId()) {
				filmes.remove(i);
				break;
			}
		}
		return filmes;
	}
	
	private List<Filme> atualizarDaLista(Filme filme, List<Filme> filmes){
		for(int i = 0; i < filmes.size(); i++) {
			if(filme.getId() == filmes.get(i).getId()) {
				filmes.remove(i);
				filmes.add(i, filme);
				break;
			}
		}
		return filmes;
	}
	
	@RequestMapping("/alterar_filme")
	public String atualizar(Filme filme, Model model, HttpSession session) {
		try {
			List<Genero> generos = gService.listarGeneros();
			session.setAttribute("generos", generos);
			filme = fService.buscarFilme(filme.getId());
			model.addAttribute("filme", filme);
			return "AtualizarFilme";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}

	@RequestMapping("/atualizar_filme")
	public String gravarAtualizacaoFilme(@Valid Filme filme, BindingResult erros, Model model, HttpSession session) {
		try {
			if (!erros.hasErrors()) {
				Genero genero = new Genero();
				genero.setId(filme.getGenero().getId());
				genero.setNome(gService.buscarGenero(genero.getId()).getNome());
				filme.setGenero(genero);

				fService.atualizarFilme(filme);

				model.addAttribute("filme", filme);
				List<Filme> filmes = (List<Filme>) session.getAttribute("lista");
				session.setAttribute("lista", atualizarDaLista(filme, filmes));

				return "VisualizarFilme";
			} else {
				return "AtualizarFilme";
			}
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("erro", e);
			return "Erro";
		}
	}
	
	@RequestMapping("/carregar_filmes")
	public String carregarFilmes() {
		fService.carregarFilmes();
		return "redirect:listar_filmes_tmdb";
	}


}
