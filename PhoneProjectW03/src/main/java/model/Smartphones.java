package model;

public class Smartphones {
    
    private String marca;
    private int ano, id;
    private float preco;

    public Smartphones () {
        this.id = -1;
        this.ano = 0;
        this.preco = 0;
        this.marca = "";
    }

    public Smartphones(int id, int ano, float preco, String marca) {
		setID(id);
        setAno(ano);
        setMarca(marca);
		setPreco(preco);
	}

    public void setID (int id){
        this.id = id;
    }

    public void setAno (int ano){
        this.ano = ano;
    }

    public void setPreco (float preco){
        this.preco = preco;
    }

    public void setMarca (String marca){
        this.marca = marca;
    }

    public int getID(){
        return this.id;
    }

    public int getAno(){
        return this.ano;
    }

    public float getPreco(){
        return this.preco;
    }

    public String getMarca(){
        return this.marca;
    }

    public String mostrarProduto(){
        
        String str = "ID = " + this.id + " Marca = " + this.marca + " Preço = " + this.preco + " Ano de fabricação: " + this.ano + "<br>";
        return str;

    }

}