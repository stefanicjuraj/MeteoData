package factory;

import MeteoData;
import observer.Observer;
import ui.SwingUI;
import ui.TextUI;
import util.UIType;

public class UIFactory {
    public static Observer createUI(UIType choice, MeteoData ws) {
        switch (choice) {
            case TEXTUI:
                return new TextUI(ws);
            case SWINGUI:
                return new SwingUI(ws);
            default:
                return null;
        }
    }
}