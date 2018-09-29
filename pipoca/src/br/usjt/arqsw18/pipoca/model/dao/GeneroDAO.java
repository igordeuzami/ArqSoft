package br.usjt.arqsw18.pipoca.model.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw18.pipoca.model.entity.Genero;

@Repository
public class GeneroDAO {
	@PersistenceContext
	EntityManager manager;

	public Genero buscarGenero(int id){
		return manager.find(Genero.class, id);
//		Genero genero = null;
//		String sql = "select id, nome from genero where id=?";
//
//		try (Connection conn = ConnectionFactory.getConnection(); 
//				PreparedStatement pst = conn.prepareStatement(sql);) {
//
//			pst.setInt(1, id);
//			try (ResultSet rs = pst.executeQuery();) {
//
//				if (rs.next()) {
//					genero = new Genero();
//					genero.setId(id);
//					genero.setNome(rs.getString("nome"));
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new IOException(e);
//		}
//		return genero;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Genero> listarGeneros() {
		return (ArrayList<Genero>) manager.createQuery("select g from Genero g").getResultList();
//		ArrayList<Genero> generos = new ArrayList<>();
//		String sql = "select id, nome from genero order by nome";
//
//		try (Connection conn = ConnectionFactory.getConnection();
//				PreparedStatement pst = conn.prepareStatement(sql);
//				ResultSet rs = pst.executeQuery();) {
//
//			while (rs.next()) {
//				Genero genero = new Genero();
//				genero.setId(rs.getInt("id"));
//				genero.setNome(rs.getString("nome"));
//				generos.add(genero);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new IOException(e);
//		}
//		return generos;
	}
}
