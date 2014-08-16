package com.kirilov.pdfmanipulator.i18n;

import java.util.Locale;

/**
 *
 * @author Leni Kirilov
 */
class TranslationTest {

    public static void main(String args[]) throws MissingTranslationException {
//        Locale locale = Locale.getDefault();
        Locale locale = new Locale("asdasdaf");
        
        Translator translator = TranslatorFactory.getTranslator(locale);

        String yesCaption = translator.getTranslation(translator.YES);
        System.out.println(yesCaption);
    }
}
