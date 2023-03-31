import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        API api = API.NASA;
        String url = api.getUrl();
        String texto = api.getTexto();
        var extrator = api.getExtratorDeConteudo();

        // var extrator = new ExtratorDeConteudoDaImdb();
        var http = new ClienteHttp();

        String json = http.buscaDados(url);

        // pegar somente os dados que interessam (titulo, poster, classificação)

        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        // exibir e manupular os dados
        var geradora = new GeradorDeFigurinha();
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        
        for ( Conteudo conteudo : conteudos) {
            String urlImagem = conteudo.urlImage();
            String titulo = conteudo.titulo();

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "figurinhas/" + titulo + ".png";

            geradora.cria(inputStream, nomeArquivo, texto);

            System.out.println("\u001b[1m \u001b[44m Titulo: \u001b[m \u001b[m" + conteudo.titulo());
            System.out.println();
        }
    }
}
