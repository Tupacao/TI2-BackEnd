package backend;

import static spark.Spark.*;

public class SimpleExample {

    public static void main(String[] args) {
        port(3000);

        get("/hello", (req,res) -> "<h1> HelloWorld <h1>");
        post("/opa", (req,res) -> {
            return "<h1> Olha o boco aqui = " + req.body() + "</h1>";
        });

        get("/private", (req,res) -> {
            return "<h1> N eh pra tu estar aqui </h1>";
        });

        get("/user/:name", (req,res) -> "<h1> O cara do momento = " + req.params("name") + "<h1>");
        get("/news/:section", (request, response) -> {
            response.type("text/xml");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><news>" + request.params("section") + "</news>";
        });

        get("/protected", (request, response) -> {
            halt(403, "<h1>N consegue né?</h1>");
            return null;
        });

        get("/redirect", (request, response) -> {
            response.redirect("/news/world");
            return null;
        });

        get("/html", (req,res) -> {
            res.redirect("http://127.0.0.1:5500/src/main/java/backend/hello.html");
            return null;
        });

        get("/", (request, response) -> "<h1>Padrão demais ce ta CRAZY vei</h1>");

    }
}