import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinha {
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        //leitura da imagem
        BufferedImage imagemOriginal = ImageIO.read(inputStream);


        //cria nova imagem em memoria com transparencia e tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura +200;
        int novaLargura = largura;
        BufferedImage novaImgagem = new BufferedImage(novaLargura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar imagem original pra nova (em memória)
        Graphics2D grafico = (Graphics2D) novaImgagem.getGraphics();
        grafico.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        grafico.setColor(Color.MAGENTA);
        grafico.setFont(fonte);

        //escrever uma frase na nova imagem
        String texto = "SHOW";
        FontMetrics fontMetrics =  grafico.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, grafico);
        double larguraRetangulo = (int) retangulo.getWidth();
        double larguraFinal = (novaLargura-larguraRetangulo)/2;
        grafico.drawString(texto, (int) larguraFinal, novaAltura-100);     

        //escrever a nova imagem em um arquivo
        ImageIO.write(novaImgagem, "png", new File(nomeArquivo));
    }

}
