package GUI.controller;

import net.java.games.input.Component;  // single component, e.g. button
import net.java.games.input.Controller;

/**
 * @author Alexandr Vorobiev
 */
public class ComponentConfig {
    private final Controller controller;
    private final Component component;
    private float maxValue = 1.0f;
    private float minValue = -1.0f;
    private int sentValue = 50;
    private int prevValue = 512;
    private String characteristics = "Button";
    private boolean switchState = false;
    private boolean switchWas0 = true;

    public int getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(int prevValue) {
        this.prevValue = prevValue;
    }

    private boolean x = true;

    public boolean isX() {
        return x;
    }

    public ComponentConfig(Controller controller, Component component) {
        this.controller = controller;
        this.component = component;
    }

    public int getPercentage(float val) {
        float max = maxValue - minValue;
        float value = val - minValue;
        return (int)((value / max) * 100.0f);
    }

    public int getSentValue() {
        return sentValue;
    }

    public void setSentValue(int sentValue) {
        this.sentValue = sentValue;
    }

    public ComponentConfig(Controller controller, Component component, boolean X) {
        this(controller,component);
        this.x = X;
    }
    public Controller getController() {
        return controller;
    }

    public Component getComponent() {
        return component;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void updateMinMax(float v) {
        if (v > maxValue)
            maxValue = v;
        if (v < minValue)
            minValue = v;

    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String s) {
        characteristics = s;
    }

    public boolean getSwitchState () {
        return switchState;
    }

    public void setSwitchState (boolean b) {
        switchState = b;
    }

    public boolean getSwitchWas0 () {
        return switchWas0;
    }

    public void setSwitchWas0 (boolean b) {
        switchWas0 = b;
    }

    @Override
    public String toString() {
        return controller.getName() +";" + component.getName() + ";" + x;
    }
}
