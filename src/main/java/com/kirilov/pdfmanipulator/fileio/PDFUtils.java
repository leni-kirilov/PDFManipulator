package com.kirilov.pdfmanipulator.fileio;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

import org.apache.pdfbox.examples.pdmodel.AddImageToPDF;
import org.apache.pdfbox.examples.pdmodel.CreateBlankPDF;
import org.apache.pdfbox.examples.pdmodel.ImageToPDF;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDCcitt;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.util.PDFMergerUtility;

public class PDFUtils {

    private static final Logger logger = Logger.getLogger(PDFUtils.class);
    private static final Map<String, PDDocument> loadedDocuments = new HashMap<String, PDDocument>();
    public static final String WORKDIR = System.getProperty("user.home") + File.separator + "pdfmanipulator_temp";
    public static final String PDF_EXTENSION = ".pdf";
    private static final int  PAGE_BORDER_MARGIN_HORIZONTAL = 20;
    private static final int  PAGE_BORDER_MARGIN_VERTICAL = 20;

    static {
        File workDirFile = new File(WORKDIR);
        if (workDirFile.exists()) {
            for (File file : workDirFile.listFiles()) {
                file.delete();
            }
            logger.info("Older folder was emptied.");
        }

        workDirFile.mkdir();
    }

    public static void removePDDocument(String fileName) {
        loadedDocuments.remove(fileName);
    }

    /**
     * Returns the page count of a document.
     *
     * @param fileName
     * @return
     */
    public static int getPagesCountForPDFDocument(String fileName) {
        PDDocument document = null;
        try {
            document = checkAndLoad(fileName);
            List pages = document.getDocumentCatalog().getAllPages();
            int pagesCount = pages.size();
            document.close();
            return pagesCount;
        } catch (IOException e) {
            logger.error("", e);
            return -1;
        }
    }

    /**
     * SUCCESS for image only PDF files. Creates them too big - must be
     * compressed by the resize method to decrease the size!
     *
     * Makes files for every page!
     *
     * @param pdfFileName
     * @param resultFilePath
     */
    public static File createThumbnailFromPDFFirstPage(String resultFilePath, String pdfFileName) {
        try {
            PDDocument document = checkAndLoad(pdfFileName);
            List pages = document.getDocumentCatalog().getAllPages();
            PDPage page = (PDPage) pages.get(0);
            BufferedImage image = page.convertToImage();

            File resultImage = new File(WORKDIR + File.separator + resultFilePath);
            ImageIO.write(image, "JPG", resultImage);
            
            document.close();
            return resultImage;
        } catch (IOException e) {
            logger.error("IOException.", e);
            return null;
        }
    }

    /**
     * Converts JPG to PDF 1:1.5 size.
     *
     * //TODO * PROBLEM : if the image has xx size, the pdf file has the image
     * x1.5 .... WHY?
     */
    public static void createPDFFromJPG(String pdfOutput, String image) {
        PDDocument doc = null;
        try {
            doc = new PDDocument();
            doc.setAllSecurityToBeRemoved(true);

            PDPage page = new PDPage();
            doc.addPage(page);

            PDXObjectImage ximage = null;
            ximage = new PDJpeg(doc, new FileInputStream(image));

            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.drawImage(ximage, PAGE_BORDER_MARGIN_HORIZONTAL, PAGE_BORDER_MARGIN_VERTICAL);
            contentStream.close();
            doc.save(pdfOutput);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (doc != null) {
                try {
                    doc.close();
                } catch (IOException ex) {
                    logger.error("", ex);
                }
            }
        }
    }

    /**
     * Merges files. resultSize = SUMofSizes(filesTOmerge) can be defined the
     * name of the result PDF
     *
     * @throws Exception
     */
    public static void mergePDFFiles(Collection<String> inputFilesPaths, String resultMergedPDFPath) throws Exception {
        PDFMergerUtility merger = new PDFMergerUtility();

        for (String file : inputFilesPaths) {
            merger.addSource(file);
        }

        merger.setDestinationFileName(resultMergedPDFPath);
        merger.mergeDocuments();
    }

    /**
     * No compression. Splits. nameOfOriginalPDF-0.pdf , ...
     *
     * Splits but too big!
     *
     * @param inputPDFPath
     * @throws Exception
     */
    public static String splitPDFFile(String inputPDFPath) throws Exception {
        String fileName = new File(inputPDFPath).getName();
        PDFSplit.split(inputPDFPath, WORKDIR + File.separator + fileName);
        return WORKDIR;
    }

    public static boolean isPDFFileSecure(String pdfDocumentPath) {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(pdfDocumentPath);
            return doc.isEncrypted();
        } catch (IOException e) {
            logger.debug("problem with pdf file", e);
            return true;
        } finally {
            try {
                doc.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(PDFUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static PDDocument checkAndLoad(String fileName) throws IOException {
        if (!loadedDocuments.containsKey(fileName)) {
            PDDocument document = PDDocument.load(fileName);
            loadedDocuments.put(fileName, document);
        }
        return loadedDocuments.get(fileName);
    }
}
