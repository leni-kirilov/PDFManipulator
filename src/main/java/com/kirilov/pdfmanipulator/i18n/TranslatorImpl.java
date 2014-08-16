package com.kirilov.pdfmanipulator.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Leni Kirilov
 */
class TranslatorImpl implements Translator {

    private ResourceBundle translationBundle;

    TranslatorImpl(String location, Locale locale) {
        translationBundle = ResourceBundle.getBundle(location, locale);
    }

    @Override
    public String getTranslation(String text) throws MissingTranslationException {
        try {
            return translationBundle.getString(text);
        } catch (Exception e) {
            throw new MissingTranslationException(e);
        }
    }
}
