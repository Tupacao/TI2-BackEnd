package app;

import java.util.List;

import dao.DAO;
import dao.UsuarioDAO;
import model.Telefones;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		System.out.println("\n\n==== Inserir usuário === ");
		Telefones usuario = new Telefones(5, "SeuManel", 1200);
		if(usuarioDAO.insert(usuario) == true) {
			System.out.println("Inserção com sucesso -> " + usuario.toString());
		}
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getID() + "): " + usuarioDAO.autenticar(5, "SeuManel"));
			
		System.out.println("\n\n==== Mostrar usuários do ID === ");
		List<Telefones> usuarios = usuarioDAO.getID(5);
		for (Telefones u: usuarios) {
			System.out.println(u.toString());
		}

		System.out.println("\n\n==== Atualizar marca (marca (" + usuario.getMarca() + ") === ");
		usuario.setMarca("SeuManel2");
		usuarioDAO.update(usuario);
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + usuario.getMarca() + "): " + usuarioDAO.autenticar(5, DAO.toMD5("SeuManel")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + usuario.getMarca() + "): " + usuarioDAO.autenticar(5, "SeuManel"));

		System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
		usuarios = usuarioDAO.getOrderByID();
		for (Telefones u: usuarios) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n\n==== Excluir usuário (código " + usuario.getID() + ") === ");
		usuarioDAO.delete(usuario.getID());
		
		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		usuarios = usuarioDAO.getOrderByID();
		for (Telefones u: usuarios) {
			System.out.println(u.toString());
		}
	}
}