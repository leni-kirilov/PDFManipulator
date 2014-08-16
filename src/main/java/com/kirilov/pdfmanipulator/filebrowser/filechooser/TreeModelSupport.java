package com.kirilov.pdfmanipulator.filebrowser.filechooser;

import java.util.Enumeration;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;

/**
 *
 * @author outsource
 */

// This class takes care of the event listener lists required by TreeModel.
// It also adds "fire" methods that call the methods in TreeModelListener.
// Look in TreeModelSupport for all of the pertinent code.
public abstract class TreeModelSupport implements TreeModel {

    private Vector vector = new Vector();

    @Override
    public void addTreeModelListener(TreeModelListener listener) {
        if (listener != null && !vector.contains(listener)) {
            vector.addElement(listener);
        }
    }

    @Override
    public void removeTreeModelListener(TreeModelListener listener) {
        if (listener != null) {
            vector.removeElement(listener);
        }
    }

    public void fireTreeNodesChanged(TreeModelEvent e) {
        Enumeration listeners = vector.elements();
        while (listeners.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listeners.nextElement();
            listener.treeNodesChanged(e);
        }
    }

    public void fireTreeNodesInserted(TreeModelEvent e) {
        Enumeration listeners = vector.elements();
        while (listeners.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listeners.nextElement();
            listener.treeNodesInserted(e);
        }
    }

    public void fireTreeNodesRemoved(TreeModelEvent e) {
        Enumeration listeners = vector.elements();
        while (listeners.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listeners.nextElement();
            listener.treeNodesRemoved(e);
        }
    }

    public void fireTreeStructureChanged(TreeModelEvent e) {
        Enumeration listeners = vector.elements();
        while (listeners.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listeners.nextElement();
            listener.treeStructureChanged(e);
        }
    }
}
