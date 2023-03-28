import java.net.URI;
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

                // exibir e manupular os dados
                for (Map<String,String> serie : listaDeSeries) {
                    System.out.println("\u001b[1m \u001b[44m Titulo: \u001b[m \u001b[m" + serie.get("title"));
                    System.out.println(serie.get("image"));
                    double classificar = Double.parseDouble(serie.get("imDbRating"));
                    int numeroCoracoes = (int) classificar;
                    for (int i=0; i<=numeroCoracoes; i++) {
                        System.out.print("💙️");
                    }
                    System.out.println();
                }
    }
}
