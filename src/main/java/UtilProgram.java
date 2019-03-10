import javafx.css.Styleable;

public class UtilProgram {

    public static void toggle(Styleable element, String className) {
        if(element.getStyleClass().contains(className)) {
            element.getStyleClass().remove(className);
        } else element.getStyleClass().add(className);
    }

}
