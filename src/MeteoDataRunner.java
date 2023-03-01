import factory.UIFactory;
import util.UIType;

public class MeteoDataRunner {
    MeteoDataRunner() {
    };

    public static void main(String[] args) {

        MeteoData ws = new MeteoData();

        for (UIType uitype : UIType.values()) {
            UIFactory.createUI(uitype, ws);
        }
        Thread thread = new Thread(ws);
        thread.start();
    }

}