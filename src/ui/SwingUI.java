package ui;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TOP;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MeteoData;

import observer.Observer;

import util.MeasurementUnit;

public class SwingUI extends JFrame implements Observer {

    private Map<MeasurementUnit, JLabel> jLabelMap = new EnumMap<>(MeasurementUnit.class);

    private MeteoData data;

    /*
     * A Font object contains information on the font to be used to render text.
     */
    private static Font labelFont = new Font(Font.SERIF, Font.PLAIN, 32);

    /*
     * Create and populate the SwingUI JFrame with panels and labels to show the
     * temperatures.
     */
    public SwingUI(MeteoData data) {

        super("MeteoData");

        this.data = data;

        data.attach(this);

        /*
         * MeteoData frame is a grid of 1 row by an indefinite number of columns.
         */
        this.setLayout(new GridLayout(1, 0));

        /*
         * There are two panels, one each for Kelvin and Celsius, added to the frame.
         * Each Panel is a 2 row by 1 column grid, with the temperature name in the
         * first row and the temperature itself in the second row.
         */
        JPanel panel = new JPanel();
        /*
         * Set up Celsius, Kelvin display display.
         */
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            panel = createPanel(unit);
            this.add(panel);
        }

        /*
         * Set up the frame's default close operation pack its elements, and make the
         * frame visible.
         */
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /*
     * Set the label holding the Celsius, Kelvin temperature.
     */
    public void setJLabel(MeasurementUnit unit, double value) {
        jLabelMap.get(unit).setText(String.format("%6.2f", value));
    }

    private JPanel createPanel(MeasurementUnit unit) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(createLabel(unit.name(), panel));
        jLabelMap.put(unit, createLabel("", panel));
        return panel;
    }

    public void update(int reading) {
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            setJLabel(unit, unit.get(reading));
        }
    }

    /*
     * Create a Label with the initial value <title>, place it in the specified
     * <panel>, and return a reference to the Label in case the caller wants to
     * remember it.
     */
    private JLabel createLabel(String title, JPanel panel) {
        JLabel label = new JLabel(title);

        label.setHorizontalAlignment(CENTER);
        label.setVerticalAlignment(TOP);
        label.setFont(labelFont);
        panel.add(label);

        /*
         * @return label
         */
        return label;
    }

    /*
     * @Override
     * public void update(Map<MeasurementUnit, Double> measurements) {
     * for (MeasurementUnit unit : MeasurementUnit.values()) {
     * setJLabel(unit, measurements.get(unit));
     * }
     * 
     * }
     */

    @Override
    public void update() {
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            setJLabel(unit, data.getReading(unit));
        }
    }

}