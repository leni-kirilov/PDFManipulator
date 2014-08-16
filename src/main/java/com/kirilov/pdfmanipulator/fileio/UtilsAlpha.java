package com.kirilov.pdfmanipulator.fileio;

import java.io.IOException;
import org.apache.pdfbox.PDFToImage;
import org.apache.pdfbox.exceptions.COSVisitorException;


/**
 * This is test class!
 * 
 * @author Leni Kirilov
 */
@Deprecated
public class UtilsAlpha {

    /**
     * @param args
     * @throws Exception
     * @throws IOException
     * @throws COSVisitorException
     */
    public static void main(String[] args) throws Exception {
        String workDir = "D:\\Lenonardo\\WORK\\Stand Alone Projects\\UploadApplet\\__test\\11\\";

        String pdfToMerge1 = workDir + "pdf1.pdf";
        String pdfToMerge2 = workDir + "pdf2.pdf";
        String mergePdfResult = workDir + "mergedPDF3.pdf";

        String pdfInput = workDir + "pdfToSplitTT.pdf";
        String image = workDir + "bmp1.bmp";
        String image2 = workDir + "pic_22.jpg";
        String imageOrig = workDir + "pic.jpg";
        String image3 = workDir + "pic_resize.jpg";
        String resultImage = workDir + "result.jpg";
        String resultImageOrig = workDir + "result_orig.jpg";
        String resultPDF = workDir + "bmp1_pdf.pdf";
        String fileName = workDir + "test.pdf";

        String bigImage = "D:\\Lenonardo\\KARTA.jpg";

        long a = System.currentTimeMillis();

//        addImageToPDF(workDir + "_addedImage.pdf", bigImage);

//        createThumbFromFirstPage(workDir, workDir + "pdfToSplit.pdf", "");

        // jpgToPDF_x1_5_Scaling(resultImageOrig, workDir + "pdfToSplit.pdf");

        // resizeImageToJPG(workDir + "pdfToSplit.pdf_.JPG", workDir
        // + "pdfToSplit.pdf_.JPG_resized.jpg", 1224, 1584);

        // System.out.println("----");
//         resizeImageToJPG(imageOrig, resultImageOrig, 1000, 500);


//        resizeImageToJPG2(bigImage, bigImage + "resized.jpg", 550, 750);


        System.out.println("TIME: " + (System.currentTimeMillis() - a));
    }

    /**
     * NOT working but if made work could be faster than the other version
     *
     * @category NOT WORKING
     * @throws Exception
     */
    @Deprecated
    public static void pdfToImage() throws Exception {
        String workDir = "D:\\Lenonardo\\WORK\\Stand Alone Projects\\UploadApplet\\__test\\11\\";

        long a = System.currentTimeMillis();

        // String fileName = workDir + "test.pdf";

        // PDFImageWriter imageWriter = new PDFImageWriter();
        // System.out.println(imageWriter.writeImage(PDDocument.load(fileName),
        // "jpg", "", 1, 1,
        // "thumb", BufferedImage.TYPE_4BYTE_ABGR_PRE, 96));

        String[] arg = {"-imageType", "jpg", "-outputPrefix", "thumb", "-endPage", "1",
            workDir + "test.pdf"};
        PDFToImage.main(arg);

        // PDPage page = new PDPage();
        // page.convertToImage();
        // page.convertToImage(imageType, resolution);
        // PDPage.convertToImage();

        System.out.println(System.currentTimeMillis() - a);
    }
}
