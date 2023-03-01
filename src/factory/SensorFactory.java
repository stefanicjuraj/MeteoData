package factory;

import sensor.Sensor;
import util.SensorType;
import sensor.TemperatureSensor;
import sensor.PressureSensor;
import sensor.HumiditySensor;

public class SensorFactory {
    public static Sensor createSensor(SensorType type) {
        switch (type) {
            case TEMPERATURE:
                return new TemperatureSensor();
            case PRESSURE:
                return new PressureSensor();
            case HUMIDITY:
                return new HumiditySensor();
            default:
                return null;
        }
    }
}