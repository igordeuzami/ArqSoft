package br.usjt.arqsw18.pipoca.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Entity(name="tb_filme")
public class Filme implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	
	@NotNull 
	@Size(max=128, min=2)
	private String titulo;
	
	@Size(max=128, min=2)
	private String diretor;
	
	@Size(max=350, min=15)
	private String descricao;
	
	@Size(max=10, min=10)
	private Date dataLancamento;
	
	@Size(max=200, min=15)
	private String posterPath;
	
	@Size(max=100, min=1)
	private int popularidade;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getPosterPath() {
		return posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	public int getPopularidade() {
		return popularidade;
	}
	public void setPopularidade(int popularidade) {
		this.popularidade = popularidade;
	}
	@Override
	public String toString() {
		return "Filme [id=" + id + ", titulo=" + titulo + ", diretor=" + diretor + ", descricao=" + descricao + 
				", dataLancamento=" + dataLancamento + ", posterPath=" + posterPath + ", popularidade="
				+ popularidade + "]";
	}
	
	

}
