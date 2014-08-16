package com.kirilov.pdfmanipulator.i18n;

/**
 * Translator object is combined with Locale.
 * It provides API to get translation of a text message.
 *
 * @author Leni Kirilov
 */
public interface Translator {

    /**
     * Gets translations.
     *
     * @param text - the desired text you want to receive its translation
     * if does not exist - thro
     *
     * @return translation in required language
     * @throws MissingTranslationException
     */
    public String getTranslation(String text) throws MissingTranslationException;
    public static String YES = "yesMessage";
}
