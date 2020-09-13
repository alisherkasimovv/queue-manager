package uz.queue.services;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.image.*;
import com.github.anastaciocintra.output.PrinterOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class PrinterService {

    private EscPos pos;

    public PrinterService() {
    }

    public void printData() {

        try {
            PrintService printService = PrinterOutputStream.getPrintServiceByName("XP-80");
            PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
            pos = new EscPos(printerOutputStream);
            pos.setCharacterCodeTable(EscPos.CharacterCodeTable.CP437_USA_Standard_Europe);

            printImage();

            pos.feed(1);
            pos.cut(EscPos.CutMode.FULL);

            pos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printImage() {
        try {
            Bitonal algorithm = new BitonalThreshold(127);
            // creating the EscPosImage, need buffered image and algorithm.

            BufferedImage githubBufferedImage = getImage("./data/output.png");
            EscPosImage escposImage = new EscPosImage(new CoffeeImageImpl(githubBufferedImage), algorithm);

            BitImageWrapper imageWrapper = new BitImageWrapper();

            imageWrapper.setJustification(EscPosConst.Justification.Center);

            pos.write(imageWrapper, escposImage);
            pos.feed(4);
            pos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedImage getImage(String image) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }

}
