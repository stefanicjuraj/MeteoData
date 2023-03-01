package util;

import java.util.*;

public enum MeasurementUnit {
    KELVIN(SensorType.TEMPERATURE, 1, 0),
    CELSIUS(SensorType.TEMPERATURE, 1, -27315),
    FAHRENHEIT(SensorType.TEMPERATURE, 1.8, -45967),
    INHG(SensorType.PRESSURE, 1, 0),
    MBAR(SensorType.PRESSURE, 33.8639, 0),
    PCT(SensorType.HUMIDITY, 100, 0);

    private SensorType type;
    private double conversionFactor1;
    private double conversionFactor2;

    MeasurementUnit(SensorType type, double conversionFactor1, double conversionFactor2) {
        this.type = type;
        this.conversionFactor1 = conversionFactor1;
        this.conversionFactor2 = conversionFactor2;
    }

    public double get(int reading) {
        return (reading * conversionFactor1 + conversionFactor2) / 100.0;
    }

    public static List<MeasurementUnit> valuesOf(SensorType type) {
        List<MeasurementUnit> list = new ArrayList<>();
        for (MeasurementUnit measurementUnit : MeasurementUnit.values()) {
            if (measurementUnit.type == type)
                list.add(measurementUnit);
        }

        /*
         * @return list
         */
        return list;
    }

}