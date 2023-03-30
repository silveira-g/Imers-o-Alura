import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;


import javax.imageio.ImageIO;

public class GeradorDeFigurinha {
    public void cria() throws Exception{
        //leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(new File("entrada/serie.jpg"));


        //cria nova imagem em memoria com transparencia e tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura +200;
        int novaLargura = largura;
        BufferedImage novaImgagem = new BufferedImage(novaLargura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar imagem original pra nova (em memória)
        Graphics2D grafico = (Graphics2D) novaImgagem.getGraphics();
        grafico.drawImage(imagemOriginal, 0, 0, null);
        //escrever uma frase na nova imagem
        ImageIO.write(novaImgagem, "png", new File("saida/figurinha.png"));

        //escrever a nova imagem em um arquivo
    }
    public static void main(String[] args) throws Exception {
        var geradora = new GeradorDeFigurinha();
        geradora.cria();
    }
}
