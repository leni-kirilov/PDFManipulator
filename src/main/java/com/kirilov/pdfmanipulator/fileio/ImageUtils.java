package com.kirilov.pdfmanipulator.fileio;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

/**
 *
 * @author Leni Kirilov
 */
public class ImageUtils {

    private static final Logger logger = Logger.getLogger(ImageUtils.class);

    /**
     * Resize any image format in (JPG, BMP, GIF...) to JPG with correct ratio
     * and size depending on the bounds of page size.
     *
     * @throws Exception
     */
    //TODO fix resize algorithm - use one from work, because it's tested
    public static void resizeImageToJPG(String inputSourceImage, String resultImage, int wantedMaxWidth,
            int wantedMaxHeight) {
        try {
            BufferedImage buffImage = ImageIO.read(new File(inputSourceImage));

            int inputImageWidth = buffImage.getWidth();
            int inputImageHeight = buffImage.getHeight();
            int resultWidth = 0;
            int resultHeight = 0;

            AffineTransform transformer = null;

            if (inputImageHeight > wantedMaxHeight || inputImageWidth > wantedMaxWidth) {

                double ratio = -1;
                double wantedRatio = wantedMaxWidth / (double) wantedMaxHeight;
                double inputRatio = inputImageWidth / (double) inputImageHeight;

                if ((inputImageHeight > inputImageWidth) || (wantedRatio > inputRatio)) {

                    ratio = wantedMaxHeight / (double) inputImageHeight;
                    resultWidth = (int) (inputImageWidth * ratio);
                    resultHeight = wantedMaxHeight;

                } else {
                    ratio = wantedMaxWidth / (double) inputImageWidth;
                    resultHeight = (int) (inputImageHeight * ratio);
                    resultWidth = wantedMaxWidth;
                }

                transformer = AffineTransform.getScaleInstance(ratio, ratio);

            } else {
                resultWidth = inputImageWidth;
                resultHeight = inputImageHeight;

                transformer = AffineTransform.getScaleInstance(1, 1);
            }

            BufferedImage imageDest = new BufferedImage(resultWidth, resultHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = imageDest.createGraphics();

            graphics.drawRenderedImage(buffImage, transformer);
            ImageIO.write(imageDest, "JPG", new File(resultImage));

        } catch (IOException e) {
            logger.error("An IO exception occurred while resizing.", e);
        } catch (NullPointerException e) {
            logger.error("+++++//TODO - display error message when the file is corrupt.");
        }
    }
}
