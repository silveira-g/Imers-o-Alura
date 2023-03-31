import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = 
        "https://api.nasa.gov/planetary/apod?api_key=9FgV5L3mhRNZW5bVNThHMKhhtEAKyoJLOs5zKkA3&start_date=2022-10-13&end_date=2022-10-15";
        
        var http = new ClienteHttp();

        String json = http.buscaDados(url);

        // pegar somente os dados que interessam (titulo, poster, classificação)

        var extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> conteudos =    extrator.extraiConteudos(json);

        // exibir e manupular os dados
        var geradora = new GeradorDeFigurinha();
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();
        String texto = "Show";
        
        for ( Conteudo conteudo : conteudos) {
            String urlImagem = conteudo.getUrlImagem();
            String titulo = conteudo.getTitulo();

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "figurinhas/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo, texto);

            System.out.println("\u001b[1m \u001b[44m Titulo: \u001b[m \u001b[m" + conteudo.getTitulo());
            System.out.println();
        }
    }
}
