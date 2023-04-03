import java.io.File;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    public void cria(InputStream inputStream, String nomeArquivoString) throws Exception{

        // leitura da imagem
      //  BufferedImage imagemOriginal = ImageIO.read(new File("Entrada/filme.jpg"));

      BufferedImage imagemOriginal = ImageIO.read(inputStream);


        //Criar nova imagemtransparência em memória e com novo tamanho
        int larguraOriginal = imagemOriginal.getWidth();
        int alturaOriginal = imagemOriginal.getHeight();
        int novaAltura = alturaOriginal + 200;

        BufferedImage novaImagem = new BufferedImage(larguraOriginal,novaAltura, BufferedImage.TRANSLUCENT);  

         // copiar a imagem original pra novo imagem (em memória)
         Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
         graphics.drawImage(imagemOriginal, 0, 0, null);
 
         // configurar a fonte
         var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
         graphics.setColor(Color.YELLOW);
         graphics.setFont(fonte);
 
         // escrever uma frase na nova imagem
         graphics.drawString("TOPZERA", 100, novaAltura - 100);
 
         // escrever a nova imagem em um arquivo
         ImageIO.write(novaImagem, "png", new File(nomeArquivoString));
        }

        /*public static void main(String[] args) throws Exception {
            var geradora = new GeradorDeFigurinhas();

            geradora.cria();
        }
        */
}
