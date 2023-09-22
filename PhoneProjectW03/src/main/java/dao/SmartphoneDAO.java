package dao;

import model.Smartphones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SmartphoneDAO extends DAO {	
	public SmartphoneDAO() {
		super();
		conectar();
	}
	
	public void finalize() {
		close();
	}
	
	public boolean insert(Smartphones smartphone) {
		boolean status = false;
		try {
			String sql = "INSERT INTO smartphones (marca, ano, id, preco) "
		               + "VALUES ('" + smartphone.getMarca() + "', "
		               + smartphone.getAno() + ", " + smartphone.getID() + ", " + smartphone.getPreco() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public Smartphones get(int id) {
		Smartphones smartphone = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM smartphones WHERE id="+id;
			ResultSet rs = st.executeQuery(sql);
	        if(rs.next()){            
	        	smartphone = new Smartphones(rs.getInt("id"), rs.getInt("ano"), (float)rs.getDouble("preco"), rs.getString("marca"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return smartphone;
	}
	
	
	public List<Smartphones> get() {
		return get("");
	}

	
	public List<Smartphones> getOrderByID() {
		return get("id");		
	}
	
	public int maxINDICE (){
		int lastIndex = -1;
		try {
			String sql = "SELECT MAX(id) AS max_id FROM smartphones";
			Statement statemant = conexao.createStatement();
			ResultSet result = statemant.executeQuery(sql);

			if(result.next()){
				lastIndex = result.getInt("max_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lastIndex;
	} 
	
	public List<Smartphones> getOrderByMarca() {
		return get("marca");	
	}
	
	
	public List<Smartphones> getOrderByPreco() {
		return get("preco");
	}

    public List<Smartphones> getOrderByAno() {
		return get("ano");
	}
	
	private List<Smartphones> get(String orderBy) {
		List<Smartphones> smartphone = new ArrayList<Smartphones>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM smartphones" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Smartphones s = new Smartphones(rs.getInt("id"), rs.getInt("ano"), (float)rs.getDouble("preco"), rs.getString("marca"));
	            smartphone.add(s);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return smartphone;
	}
	
	public boolean update(Smartphones smartphone) {
		boolean status = false;
		try {  
			String sql = "UPDATE smartphones SET marca = '" + smartphone.getMarca() + "', "
					   + "ano = " + smartphone.getAno() + ", " 
					   + "id = " + smartphone.getID() + ","
                       + "preco = " + smartphone.getPreco() 
					   + " WHERE id = " + smartphone.getID();
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
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
			st.executeUpdate("DELETE FROM smartphones WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}