package ui;

import java.util.Map;

import util.MeasurementUnit;

public interface MeteoDataUI {

    public void update(Map<MeasurementUnit, Double> measurements);

}