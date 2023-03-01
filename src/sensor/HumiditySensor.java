package sensor;

public class HumiditySensor implements Sensor {

    @Override
    public int read() {
        HumidityReader reader = new HumidityReader();
        return reader.get();
    }

}