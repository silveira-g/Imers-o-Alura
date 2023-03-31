import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaImdb implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json){ 
        var parser = new Parser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        return listaDeAtributos.stream()
        .map(atributos -> new Conteudo(atributos.get("title"), atributos.get("image"))).toList();


}
}
