package com.kirilov.pdfmanipulator.i18n;

import java.util.HashMap;
import java.util.Locale;
import org.apache.log4j.Logger;

/**
 * @author Leni Kirilov
 */
public class TranslatorFactory {
    private static final Logger logger = Logger.getLogger(TranslatorFactory.class);

    private static String TRANSLATIONS_LOCATION = "com.kirilov.pdfmanipulator.i18n.bundles.translations";
//    private static Locale DEFAULT_LOCALE = new Locale("en_US");
    private static HashMap<Locale, Translator> translators = new HashMap<Locale, Translator>();

    private TranslatorFactory() throws Throwable {
        throw new Throwable("Constructor cannot be invoked.");
    }

    public static Translator getTranslator(Locale loc) {
        logger.info(loc.getCountry() + " locale chosen!");
        boolean exists = translators.keySet().contains(loc);

        if (exists) {
            return translators.get(loc);
        } else {
            Translator newTranslator = findTranslator(TRANSLATIONS_LOCATION, loc);
            translators.put(loc, newTranslator);
            return newTranslator;
        }
    }

    private static Translator findTranslator(String location, Locale loc) {
//        try {
        Translator tr = new TranslatorImpl(location, loc);
        return tr;
//        } catch (Exception e) {
//            System.out.println("Didn't find the required Translator.Returning DEFAULT_TRANSLATOR.");
//            if (loc.equals(DEFAULT_LOCALE)) {
//                System.out.println("Didn't find DEFAULT. Returning null.");
//                return null;
//            } else {
//                return findTranslator(location, DEFAULT_LOCALE);
//            }
//        }
    }
}
