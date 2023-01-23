package ru.SOHNick;
import Convector.src.ru.Nikita.graphics.Convector;
import Graphics.src.ru.sohick.Painter;

import java.awt.*;

import static java.lang.Float.isNaN;

public class FunctionPainter2 implements Painter
{
    public double xMin,xMax,yMax,yMin;
    Color colorFunction;
    Color colorPoint;
    double scala = Math.pow(10000,1);
    private final Convector a;
    private Function f;
    public boolean stateFunction;
    public boolean statePoint;

    public  FunctionPainter2(Convector a)
    {
        this.a = a;
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

        if(stateFunction)
        {
            drawFunction(g2);
        }
    }
    public  void FP(Function function)
    {
        this.f = function;
    }
    public void drawFunction(Graphics g)
    {
        double step = 0.0001;
        g.setColor(colorFunction);
        int yCenter = a.yCrt2Scr(0);
        for (double j = xMin; j < xMax; j+=step)
        {

            if(!isNaN((float) f.invoke(j)))
            {
                double x = Math.round(j * scala) / scala;
                double y = Math.round(f.invoke(x) * scala) / scala;
                double next = Math.round(f.invoke(x + step) * scala) / scala;
               if(Math.abs(f.invoke(x))<= 1E4)
               {
                   g.drawLine(a.xCrt2Scr(x), a.yCrt2Scr(y), a.xCrt2Scr(x + step), a.yCrt2Scr(next));
               }
                if (y == 0) {
                    if (statePoint) {
                        g.setColor(colorPoint);
                        g.fillOval(a.xCrt2Scr(x) - 3, yCenter - 3, 6, 6);
                    }
                }
            }
            g.setColor(colorFunction);
        }
    }
    public void setColorFunction(Color color) {
        this.colorFunction = color;
    }
    public void setColorPoint(Color color) {
        this.colorPoint = color;
    }

}
