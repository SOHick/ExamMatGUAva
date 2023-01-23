package ru.SOHNick;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

public class JSliderFunctionMin extends JPanel implements ChangeListener
{
    JSlider jSlider = new JSlider();
    public JSliderFunctionMin()
    {
        JSlider slider = new JSlider();
        slider.setMinimum(-4);
        slider.setMaximum(0);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setInverted(false);
        slider.setValue(-20);
        add(slider);
        setJSlider(slider);
        slider.addChangeListener(this);
        FunctionPainter.ValueMinParametric = jSlider.getValue();
        slider.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    slider.setValue(slider.getValue() + 1);
                } else
                if (notches > 0) {
                    slider.setValue(slider.getValue() - 1);
                }
            }
        });
    }

    public  void setJSlider(JSlider slider)
    {
        this.jSlider = slider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        FunctionPainter.ValueMinParametric = jSlider.getValue();
    }
}
