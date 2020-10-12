package GUI;

import GUI.controller.ComponentConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import static properties.GlobalProperties.checkChannelCharacteristicForComponent;

/**
 *     This file is part of joystick-to-ppm, a port of Flytron's Compufly
 *     to Java for cross platform use.
 *
 *     The source was obtained from code.google.com/p/joystick-to-ppm
 *     Copyright (C) 2011  Alexandr Vorobiev
 *
 *     Revised user interface and added button characteristics
 *     Copyright (C) 2020  Gregor Schlechtriem
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class ButtonAssignDialog extends JDialog implements ActionListener, AdjustmentListener{
    private final JComboBox channels;
    private final JButton okBtn = new JButton("Ok");
    private final ComponentConfig component;
    private final JScrollBar valueON = new JScrollBar(JScrollBar.HORIZONTAL, 50, 0, 0, 100);
    private final JScrollBar valueOFF = new JScrollBar(JScrollBar.HORIZONTAL, 50, 0, 0, 100);
    private final JTextField fieldON = new JTextField();
    private final JTextField fieldOFF = new JTextField();
    private final JToggleButton characteristics = new JToggleButton("Button");
    public ButtonAssignDialog(ComponentConfig component) {
        super(MainFrame.getInstance(), true);
        fieldON.setEditable(false);
        fieldON.setMinimumSize(new Dimension(50, 20));
        fieldON.setText(component.getSentValue() + "%");
        fieldOFF.setEditable(false);
        fieldOFF.setMinimumSize(new Dimension(50, 20));
        fieldOFF.setText(component.getSentValueOFF() + "%");
        String s = checkChannelCharacteristicForComponent(component);
        if (s == null) s = "Button";
        characteristics.setText(s);
        channels = new JComboBox();
        valueON.setValue(component.getSentValue());
        valueON.addAdjustmentListener(this);
        valueOFF.setValue(component.getSentValueOFF());
        valueOFF.addAdjustmentListener(this);
        okBtn.addActionListener(this);
        characteristics.addActionListener(this);
        this.component = component;
        for (int i = 1; i <= DeviceConfigPanel.SERVO_COUNT; i++) {
                channels.addItem(i);
        }
        createLayout();
    }

    private void createLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc = makegbc(0, 0, 2, 1);
        add(new JLabel("Choose channel: "),gbc);

        gbc = makegbc(2, 0, 1, 1);
        add(channels,gbc);

        gbc = makegbc(0, 1, 2, 1);
        add(new JLabel("Set ON value: "),gbc);

        gbc = makegbc(3, 1, 1, 1);
        add(fieldON, gbc); // value

        gbc = makegbc(2, 1, 1, 1);
        add(valueON, gbc); // scrollbar

        gbc = makegbc(0, 2, 2, 1);
        add(new JLabel("Set OFF value: "),gbc);

        gbc = makegbc(3, 2, 1, 1);
        add(fieldOFF, gbc); // value

        gbc = makegbc(2, 2, 1, 1);
        add(valueOFF, gbc); // scrollbar

        gbc = makegbc(0, 3, 2, 1);
        add(new JLabel("Characteristic:"),gbc);

        gbc = makegbc(2, 3, 1, 1);
        add (characteristics,gbc);

        gbc = makegbc(3, 4, 1, 1);
        add(okBtn,gbc);

        setTitle("Assign Buttons");
        setPreferredSize(new Dimension(260, 180));
        setSize(getPreferredSize());
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == characteristics) {
            if (component.getCharacteristics() == "Button") {
                component.setCharacteristics("Switch");
            } else {
                component.setCharacteristics("Button");
            }
            characteristics.setText(component.getCharacteristics());
        } else {
            DeviceConfigPanel.addMapping(component, (Integer) channels.getSelectedItem());
            setVisible(false);
        }
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        if(e.getSource() == valueON) {
            fieldON.setText(e.getValue() + "%");
            component.setSentValue(valueON.getValue());
        } else {
            fieldOFF.setText(e.getValue() + "%");
            component.setSentValueOFF(valueOFF.getValue());
        }
    }

    private GridBagConstraints makegbc(int x, int y, int width, int height) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }
}
