package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Telefones;

public class UsuarioDAO extends DAO {
	
	public UsuarioDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Telefones usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO telefones (marca, preco, id) "
				       + " VALUES ('"+usuario.getMarca()+"', " + usuario.getPreco() + ", "  
				       + usuario.getID()+ ");";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Telefones get(int codigo) {
		Telefones usuario = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM telefones WHERE id =" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	usuario = new Telefones(rs.getInt("preco"), rs.getString("marca"), rs.getInt("id"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuario;
	}
	
	
	public List<Telefones> getOrderByID() {
		return get("id");
	}

	
	public List<Telefones> getOrderByPreco() {
		return get("preco");
	}
	
	
	public List<Telefones> getOrderByMarca() {
		return get("marca");		
	}
	
	
	private List<Telefones> get(String orderBy) {	
	
		List<Telefones> usuarios = new ArrayList<Telefones>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM telefones" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Telefones u = new Telefones(rs.getInt("preco"), rs.getString("marca"), rs.getInt("id"));
	            usuarios.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}


	public List<Telefones> getID(int id) {
		List<Telefones> usuarios = new ArrayList<Telefones>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM telefones WHERE id = " + id;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Telefones u = new Telefones(rs.getInt("preco"), rs.getString("marca"), rs.getInt("id"));
	            usuarios.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	
	public boolean update(Telefones usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE telefones SET marca = '" + usuario.getMarca() + "', preco = "  
				       + usuario.getPreco() + ", id = " + usuario.getID() + ""
					   + " WHERE id = " + usuario.getID();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM telefones WHERE id = " + id;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean autenticar(int id, String marca) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM telefones WHERE id = " + id + " AND marca = '" + marca + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}	
}