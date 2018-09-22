package br.usjt.arqsw18.pipoca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw18.pipoca.model.entity.Filme;
import br.usjt.arqsw18.pipoca.model.service.FilmeService;

@Controller
public class ManterFilmesController {
	private FilmeService filmeService;
	
	public ManterFilmesController() {
		filmeService = new FilmeService();
	}
	
	@RequestMapping("/novo")
	public String novoFilme(){
		return "NovoFilme";
	}
	
	@RequestMapping("/inserir")
	public String inserirFilme(Filme filme, Model model) {
		String titulo = filmeService.inserirFilme(filme);
		filme.setTitulo(titulo);
		model.addAttribute("filme", filme);
		System.out.println(filme.toString());
		return "Resultado"; //AQUI COLOCA ONDE VAI SER DIRECIONADO  JSP
	}

}
