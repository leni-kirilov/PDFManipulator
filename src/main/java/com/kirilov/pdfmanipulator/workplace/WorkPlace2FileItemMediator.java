package com.kirilov.pdfmanipulator.workplace;

import com.kirilov.pdfmanipulator.workplace.ui.WorkPlace;

/**
 *
 * @author Leni Kirilov
 */
public final class WorkPlace2FileItemMediator {

    public static WorkPlace workPlace;

    public static void changeStateButtonsSelected(boolean state) {
        workPlace.changeStateButtonsSelected(state);
    }
}