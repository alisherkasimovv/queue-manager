package uz.queue.services;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Drawer class is used for printing text to PNG file output.
 *
 * 08 September 2020 - Class created;
 */
@Service
public class Drawer {

    private Graphics2D graphics2D;
    private final String logoFilePath;

    public Drawer() {
        // TODO Change hardcoded file choose to database driven
        this.logoFilePath = "./data/logo_380.jpg";
    }

    public void createImageWithText(String[] drawableText) {
        BufferedImage bufferedImage = new BufferedImage(450, 600, BufferedImage.TYPE_INT_ARGB);
        graphics2D = bufferedImage.createGraphics();
        drawImageToCanvas();

        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        Font fontQueue = new Font("Fira Sans", Font.BOLD, 100);
        Font fontSpec = new Font("Fira Sans", Font.BOLD, 40);

        Font font = new Font("Fira Sans", Font.PLAIN, 30);

        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(font);

        FontMetrics metrics = graphics2D.getFontMetrics(font);
        int x = (bufferedImage.getWidth() - metrics.stringWidth(drawableText[0])) / 2;
        graphics2D.drawString(drawableText[0], x, 200);


        graphics2D.setFont(fontQueue);
        metrics = graphics2D.getFontMetrics(fontQueue);
        x = (bufferedImage.getWidth() - metrics.stringWidth(drawableText[1])) / 2;
        graphics2D.drawString(drawableText[1], x, 300);

        graphics2D.setFont(fontSpec);
        metrics = graphics2D.getFontMetrics(fontSpec);
        x = (bufferedImage.getWidth() - metrics.stringWidth(drawableText[2])) / 2;
        graphics2D.drawString(drawableText[2], x, 350);


        graphics2D.setFont(font);

        metrics = graphics2D.getFontMetrics(font);
        x = (bufferedImage.getWidth() - metrics.stringWidth(drawableText[3])) / 2;
        graphics2D.drawString(drawableText[3], x, 450);
        x = (bufferedImage.getWidth() - metrics.stringWidth(drawableText[4])) / 2;
        graphics2D.drawString(drawableText[4], x, 500);
        x = (bufferedImage.getWidth() - metrics.stringWidth(drawableText[5])) / 2;
        graphics2D.drawString(drawableText[5], x, 550);
        graphics2D.setBackground(Color.WHITE);

        try {
            String outputFileName = "./data/output.png";
            File outputFile = new File(outputFileName);
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints logo.
     */
    private void drawImageToCanvas() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(this.logoFilePath));
            graphics2D.drawImage(img, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
