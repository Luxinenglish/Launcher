package com.github.luxinenglish.launcher.ui.panel;

import com.github.luxinenglish.launcher.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getName();
    String getStylesheetPath();
}
