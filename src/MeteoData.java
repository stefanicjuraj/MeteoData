import java.util.EnumMap;

import factory.SensorFactory;

import observer.Subject;

import sensor.PressureSensor;
import sensor.Sensor;
import sensor.TemperatureSensor;

import util.MeasurementUnit;
import util.SensorType;

/**
 * Class for a simple computer based MeteoData that reports the current
 * temperature (in Celsius) every second. The data is attached to a sensor
 * that reports the temperature as a 16-bit number (0 to 65535) representing the
 * Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 * 
 */
public class MeteoData extends Subject implements Runnable {

    private EnumMap<SensorType, Sensor> sensorMap = new EnumMap<>(SensorType.class);
    private EnumMap<MeasurementUnit, Double> readingMap = new EnumMap<>(MeasurementUnit.class);

    private static final long PERIOD = 1000; // 1 sec = 1000 ms.

    /*
     * When a MeteoData object is created, it in turn creates the sensor
     * object it will use.
     */

    public MeteoData() {
        super();
        for (SensorType sensorType : SensorType.values()) {
            sensorMap.put(sensorType, SensorFactory.createSensor(sensorType));
        }
    }

    private void getSensorReadings() {
        Sensor sensor = null;
        for (SensorType sensorType : SensorType.values()) {

            sensor = sensorMap.get(sensorType);
            int reading = sensor.read();

            for (MeasurementUnit unit : MeasurementUnit.valuesOf(sensorType)) {
                readingMap.put(unit, unit.get(reading));
            }

        }
        notifyObserver();
    }

    /*
     * Returns the reading from map
     * 
     * @param unit - specifies the measurementunit enum
     * 
     * @return the result of current reading
     */
    public double getReading(MeasurementUnit unit) {
        return readingMap.get(unit);
    }

    /*
     * The "run" method called by the enclosing Thread object when started.
     * Repeatedly sleeps a second, acquires the current temperature from
     * its sensor, and reports this as a formatted output string.
     */
    public void run() {
        while (true) {
            getSensorReadings();

            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Re-interrupted this method
                e.printStackTrace();
            }

            // Added as a check, to add an end condition to the loop
            if (readingMap == null) {
                break;
            }

        }
    }

}