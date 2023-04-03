import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Conectar o HTTP e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();
        System.out.println(body);
        
        //System.out.println("Bora mecãoooo!");

        // Extrair os dados necessários (título, pôster e classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);

        // Exibir os dados filtrados
        /* 
        for(Map<String,String> filme : listaFilmes){
            System.out.println("Título: " + filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println("Classificação: " + filme.get("imDbRating"));
            System.out.println();
        }
*/
        var geradora = new GeradorDeFigurinhas();

        for (Map<String,String> filme : listaFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }
    }
}