package model;

public class Telefones {
	
	private int ID;
	private String marca;
	private int preco;
	
	public Telefones() {
		this.ID = -1;
		this.marca = "";
		this.preco = -1;
	}
	
	public Telefones(int ID, String marca, int preco) {
		this.ID = ID;
		this.marca = marca;
		this.preco = preco;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Override
	public String toString() {
		return "Telefones [Marca =" + marca + ", Preco =" + preco + ", ID =" + ID + "]";
	}	
}
