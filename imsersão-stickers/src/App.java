import java.io.File;
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
                // fazer uma conexão HTTP e buscar os top 250 filmes
                String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
                URI endereco = URI.create(url);
                var client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                String body = response.body();

                // pegar somente os dados que interessam (titulo, poster, classificação)
                var parser = new Parser();
                List<Map<String, String>> listaDeSeries = parser.parse(body);
                var geradora = new GeradorDeFigurinha();
                // exibir e manupular os dados
                var diretorio = new File("figurinhas/");
                diretorio.mkdir();
                String texto = "Nem perca tempo";
                for (Map<String,String> serie : listaDeSeries) {
                    String urlImagem = serie.get("image");
                    String titulo = serie.get("title");
                    
                    InputStream inputStream = new URL(urlImagem).openStream();

                    String nomeArquivo = "figurinhas/" + titulo + ".png";

                    
                    String nota = serie.get("imDbRating");
                    double notaD = Double.parseDouble(nota);

                    if (notaD == 10 || notaD>=8.5){ 
                        texto="INCRIVEL!!!";
                    }
                    
                    if (notaD <= 8.4 || notaD<=6){ 
                        texto="Bom";
                    }
                    
                    if (notaD >= 6 && notaD<=5){ 
                        texto="Mediano";
                    }
                    


                    geradora.cria(inputStream, nomeArquivo, texto);

                    System.out.println("\u001b[1m \u001b[44m Titulo: \u001b[m \u001b[m" + serie.get("title"));
                    System.out.println(notaD);
                    System.out.println();
                }
    }
}
