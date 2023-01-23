package ru.SOHNick;
import Convector.src.ru.Nikita.graphics.Convector;
import Graphics.src.ru.sohick.GraphicsPanel;
import Graphics.src.ru.sohick.Painter;

import java.awt.*;

import static java.lang.Float.isNaN;

public class FunctionPainter implements Painter
{
    public double xMin,xMax,yMax,yMin;
    private final GraphicsPanel mainPanel;
    public static int ValueMaxParametric;
    public static int ValueMinParametric;
    Color colorPolynomial;
    public boolean stateFunctionParametric;
    double scala = Math.pow(10000,1);
    private final Convector a;
    private Function fX;
    private Function fY;

    public  FunctionPainter(Convector a, GraphicsPanel mainPanel)
    {
        this.a = a;
        this.mainPanel = mainPanel;
    }
    public  void FX(Function function)
    {
       this.fX = function;
    }
    public  void FY(Function function)
    {
        this.fY = function;
    }
    @Override
    public void paint(Graphics g, int Wight, int Height)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        this.xMin = a.xMin;
        this.xMax = a.xMax;
        this.yMin = a.yMin;
        this.yMax = a.yMax;
        if(stateFunctionParametric)
        {
            drawFunction(g2);
            mainPanel.repaint();
        }

    }
    public void drawFunction(Graphics g)
    {
        g.setColor(colorPolynomial);
        double step = 0.0001;

        for (double j = ValueMinParametric; j < ValueMaxParametric; j+= step)
        {
            double k = Math.round(j * scala) / scala;
            double x = Math.round(fX.invoke(k) * scala)/scala;
            double y = Math.round(fY.invoke(k) * scala)/scala;
            double nextX = Math.round(fX.invoke(k+ step) * scala)/scala;
            double nextY = Math.round(fY.invoke(k+ step) * scala)/scala;

            if(!isNaN((float) fX.invoke(j)) & !isNaN((float) fY.invoke(j))) {
                if(Math.abs(fX.invoke(x))<= 1E4 & Math.abs(fY.invoke(x))<= 1E4)
                {
                    g.drawLine(a.xCrt2Scr(x), a.yCrt2Scr(y), a.xCrt2Scr(nextX), a.yCrt2Scr(nextY));
                }
            }
        }
    }
    public void setColor1(Color color) {
        this.colorPolynomial = color;
    }

}
