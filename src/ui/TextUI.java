package ui;

import MeteoData;

import observer.Observer;
import util.MeasurementUnit;

public class TextUI implements Observer {

    private MeteoData data;

    public TextUI(MeteoData data) {
        this.data = data;
        data.attach(this);
    }

    @Override
    public void update() {
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            System.out.printf("%s: %6.2f %n", unit, data.getReading(unit));
        }
        System.out.println();
    }

}