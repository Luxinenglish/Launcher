package com.github.luxinenglish.ui.panel;

import javafx.scene.Node;

public interface IMovable {
    void setLeft(Node node);
    void setRight(Node node);
    void setTop (Node node);
    void setBotttom(Node node);
    void setBaseLine(Node node);
    void setCenterH(Node node);
    void setCenterV(Node node);
}
