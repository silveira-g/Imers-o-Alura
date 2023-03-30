import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = 
        "https://api.nasa.gov/planetary/apod?api_key=9FgV5L3mhRNZW5bVNThHMKhhtEAKyoJLOs5zKkA3&start_date=2022-10-13&end_date=2022-10-15";
        
        var http = new ClienteHttp();

        String json = http.buscaDados(url);

        // pegar somente os dados que interessam (titulo, poster, classificação)
        var parser = new Parser();
        List<Map<String, String>> listaDeConteudos = parser.parse(json);

        // exibir e manupular os dados
        var geradora = new GeradorDeFigurinha();
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();
        String texto = "Show";
        for (Map<String, String> conteudo : listaDeConteudos) {
            String urlImagem = conteudo.get("url");
            String titulo = conteudo.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "figurinhas/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo, texto);

            System.out.println("\u001b[1m \u001b[44m Titulo: \u001b[m \u001b[m" + conteudo.get("title"));
            System.out.println();
        }
    }
}
