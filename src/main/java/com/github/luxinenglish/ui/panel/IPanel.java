package com.github.luxinenglish.ui.panel;
import com.github.luxinenglish.ui.PanelManager;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public interface IPanel {
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getName();

    void setBottom(Node node);
}
