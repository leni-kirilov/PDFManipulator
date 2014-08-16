package com.kirilov.pdfmanipulator.workplace.controller;

import com.kirilov.pdfmanipulator.i18n.Translator;
import com.kirilov.pdfmanipulator.workplace.WorkPlace2FileItemMediator;
import com.kirilov.pdfmanipulator.workplace.ui.WorkPlace;
import com.kirilov.pdfmanipulator.workplace.ui.WorkPlaceImpl;

/**
 *
 * @author Leni Kirilov
 */
public final class WorkPlaceFactory {

    private static WorkPlace workPlace;

    public static WorkPlace getWorkPlaceInstance(Translator translator) {
        if (workPlace == null) {
            initializeWorkplace(translator);
        }
        return workPlace;
    }

    private static void initializeWorkplace(Translator translator) {
        WorkPlaceController workPlaceController = new WorkPlaceControllerImpl();
        workPlace = new WorkPlaceImpl(workPlaceController, translator);
        WorkPlace2FileItemMediator.workPlace = workPlace;
    }

    private WorkPlaceFactory() {
        throw new RuntimeException("You should not instanciate me");
    }
}
