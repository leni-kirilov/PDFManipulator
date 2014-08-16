package com.kirilov.pdfmanipulator.fileio;

/**
 *
 * @author Leni Kirilov
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.log4j.Logger;

import org.apache.pdfbox.exceptions.InvalidPasswordException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdfwriter.COSWriter;
import org.apache.pdfbox.util.Splitter;

class PDFSplit {

    static void split(String pdfFile, String outputFile) throws Exception {
        InputStream input = null;
        PDDocument document = null;
        try {
            input = new FileInputStream(pdfFile);
            document = parseDocument(input);

            Splitter splitter = new Splitter();
            splitter.setSplitAtPage(1);
            List documents = splitter.split(document);
            for (int i = 0; i < documents.size(); i++) {
                PDDocument doc = (PDDocument) documents.get(i);
                String fileName = FileUtils.getWithoutExtension(outputFile) + "-" + i + ".pdf";
                writeDocument(doc, fileName);
                doc.close();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }

            if (document != null) {
                document.close();
            }
        }
    }

    private static void writeDocument(PDDocument doc, String fileName) throws IOException, COSVisitorException {
        COSWriter writer = null;
        try {
            writer = new COSWriter(new FileOutputStream(fileName));
            writer.write(doc);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * This will parse a document.
     *
     * @param input The input stream for the document.
     *
     * @return The document.
     *
     * @throws IOException If there is an error parsing the document.
     */
    private static PDDocument parseDocument(InputStream input) throws IOException {
        PDFParser parser = new PDFParser(input);
        parser.parse();
        return parser.getPDDocument();
    }
}
