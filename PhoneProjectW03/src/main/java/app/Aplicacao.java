package app;

import static spark.Spark.*;
import dao.SmartphoneDAO;
import model.Smartphones;

class Aplicacao {
    public static void main(String[] args) {
        
        SmartphoneDAO dao = new SmartphoneDAO();

        port(5500);

        staticFiles.location("/public");

        get("/", (req,res) -> {
            res.redirect("/index.html");
            return null;
        });

        post("/adicionar", (req,res) -> {
            
            int INDICE = dao.maxINDICE();

            String marca = req.queryParams("marca");
            float preco = Float.parseFloat(req.queryParams("preco"));
            int ano = Integer.parseInt(req.queryParams("ano"));

            Smartphones smart = new Smartphones(++INDICE, ano, preco, marca);

            dao.insert(smart);

            return smart.mostrarProduto();
        });

        get("/mostrar", (req,res) -> {

            int ID = Integer.parseInt(req.queryParams("id"));
            int teste = dao.maxINDICE();

            if (ID > teste) {
                return "<h1> ID invalido </h1>";
            } else {
                Smartphones str = new Smartphones();
                str = dao.get(ID);
    
                return str.mostrarProduto();
            }

        });

        get("/remove", (req,res) -> {

            int ID = Integer.parseInt(req.queryParams("id"));
            Smartphones str = new Smartphones();
            int teste = dao.maxINDICE();

            if (ID > teste) {
                return "<h1> ID invalido </h1>";
            } else {

                str = dao.get(ID);
                dao.delete(ID);

                return str.mostrarProduto();
            }
            
        });

        get("/listar", (req,res) -> {

            int INDICE = dao.maxINDICE();

            Smartphones str[] = new Smartphones[INDICE];

            for (int i = 0; i < INDICE; i++) {
                str[i] = dao.get(i);
            }

            String resp = "";

            for (int i = 0; i < str.length; i++) {
                resp+= str[i].mostrarProduto();
            }

            return resp;
        });

        get("/update", (req,res) ->{

            int id = Integer.parseInt(req.queryParams("id"));
            String marca = req.queryParams("marca");
            float preco = Float.parseFloat(req.queryParams("preco"));
            int ano = Integer.parseInt(req.queryParams("ano"));
            
            Smartphones smart = new Smartphones(id, ano, preco, marca);

            dao.update(smart);

            return smart.mostrarProduto();
        });

        System.out.println("http://127.0.0.1:5500/");

    }
}