package com.kirilov.pdfmanipulator.i18n;

/**
 *
 * @author Leni Kirilov
 */
public class MissingTranslationException extends RuntimeException {

    MissingTranslationException(Exception e) {
        super(e);
    }
}
