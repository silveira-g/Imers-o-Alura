public enum API {
    IMDB_TOP_SERIES ("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json", "Show", new ExtratorDeConteudoDaImdb()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=9FgV5L3mhRNZW5bVNThHMKhhtEAKyoJLOs5zKkA3&start_date=2022-10-13&end_date=2022-10-15", "Lindo", new ExtratorDeConteudoDaNasa());

    private String url;
    private String texto;
    private ExtratorDeConteudo extrator;

    API(String url, String texto, ExtratorDeConteudo extrator) {
        this.url = url;
        this.texto = texto;
        this.extrator = extrator;
    }
    
    public String getUrl() {
        return url;
    }
    public String getTexto() {
        return texto;
    }
    public ExtratorDeConteudo getExtratorDeConteudo(){
        return extrator;
    }
}
