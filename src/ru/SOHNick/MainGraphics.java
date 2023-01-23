package ru.SOHNick;
import Convector.src.ru.Nikita.graphics.Convector;
import Graphics.src.ru.sohick.GraphicsPanel;
import Graphics.src.ru.sohick.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;



public class MainGraphics extends JFrame
{
    SpinnerNumberModel nmxMin;
    SpinnerNumberModel nmxMax;
    SpinnerNumberModel nmyMin;
    SpinnerNumberModel nmyMax;
    public MainGraphics()
    {
        Dimension minSize = new Dimension(800, 600);
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GroupLayout gl = new GroupLayout(getContentPane()); // Наше окно без рамок
        setLayout(gl);
        // По горизонту параллельно будет
        // По вертикали последовательно
        nmxMin = new SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1);
        nmxMax = new SpinnerNumberModel( 5.0, -4.9, 100.0, 0.1);
        nmyMin = new SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1);
        nmyMax = new SpinnerNumberModel( 5.0, -4.9, 100.0, 0.1);
        JSpinner js1 = new JSpinner(nmxMin); // mnxMin = new SpinnerNumberModel(-5.0,-100.0,4.9,0.1)
        JSpinner js2 = new JSpinner(nmyMin); //mnxMax = new SpinnerNumberModel(5.0,-100.0,4.9,0.1)
        JSpinner js3 = new JSpinner(nmxMax);
        JSpinner js4 = new JSpinner(nmyMax);
        JSliderFunctionMax sliderFunctionMax = new JSliderFunctionMax();
        JSliderFunctionMin sliderFunctionMin = new JSliderFunctionMin();
        JPanel kv1 = new JPanel();
        JPanel kv2 = new JPanel();
        JPanel kv3 = new JPanel();
        Checkbox Polynomial = new Checkbox();
        Polynomial.setState(true);
        Checkbox Derivative = new Checkbox();
        Derivative.setState(true);
        Checkbox Point = new Checkbox();
        Point.setState(true);
        JPanel controlPanel = new JPanel();
       // Создание лейбла
        ArrayList<Painter> qwe = new ArrayList<>();
        GraphicsPanel mainPanel = new GraphicsPanel(qwe);
        Convector a = new Convector((Double)js1.getValue(), (Double)js3.getValue(), (Double)js2.getValue(), (Double)js4.getValue(),mainPanel.getWidth(), mainPanel.getHeight());
        var opo = new MovePoint(a);
        var ipi = new CrtPainter2(a);
        kv2.setBackground(Color.GREEN);
        kv3.setBackground(Color.BLUE);
        kv1.setBackground(Color.GRAY);
        var ppe = new FunctionPainter2(a);
        var ppp = new FunctionPainter(a,mainPanel);
        ppe.setColorFunction(Color.GREEN);
        ppe.setColorPoint(Color.GRAY);
        ppp.setColor1(Color.BLUE);
        ppe.stateFunction = true;
        ppe.statePoint = true;
        ppp.stateFunctionParametric =true;
        ppe.FP(MainGraphics::F);
        ppp.FX(MainGraphics::FXT);
        ppp.FY(MainGraphics::FYT);
        mainPanel.add(ppp);
        mainPanel.add(ppe);
        mainPanel.add(opo);
        mainPanel.add(ipi);
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                a.setHeight(mainPanel.getHeight());
                a.setWidth(mainPanel.getWidth());
                a.SetXEdges((Double) js1.getValue(),(Double) js3.getValue());
                a.SetYEdges((Double) js2.getValue(),(Double) js4.getValue());
                mainPanel.repaint();
            }
        });
        mainPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                opo.XY(e.getX(),e.getY());
                mainPanel.repaint();
            }
        });

        Polynomial.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                int stateChange = e.getStateChange();
                if (stateChange == 1) {ppe.stateFunction = true;}
                else if(stateChange == 2) {ppe.stateFunction = false;}
                mainPanel.repaint();
            }
        });
        Derivative.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                int stateChange = e.getStateChange();
                if (stateChange == 1) {ppp.stateFunctionParametric = true;}
                else if(stateChange == 2) {ppp.stateFunctionParametric = false;}
                mainPanel.repaint();
            }
        });
        Point.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int stateChange = e.getStateChange();
                if (stateChange == 1) {ppe.statePoint = true;}
                else if(stateChange == 2) {ppe.statePoint = false;}
                mainPanel.repaint();
            }
        });
        mainPanel.setBackground(Color.white);
        controlPanel.setBackground(Color.lightGray);
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.black, 2),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));


        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                        .addGroup(gl.createParallelGroup()
                               .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addComponent(controlPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        )
                .addGap(8)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,400,GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                                .addComponent(controlPanel,GroupLayout.DEFAULT_SIZE,200,200)

                        )
                .addGap(8)
        );
        //endregion
        kv1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                var newColor =
                        JColorChooser.showDialog(
                                MainGraphics.this,
                                "Выбор цвета графика",
                                kv1.getBackground()
                        );
                if (newColor != null)
                {
                    kv1.setBackground(newColor);
                    ppe.setColorPoint(newColor);
                    mainPanel.repaint();
                }
            }
        });

        kv2.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                var newColor =
                        JColorChooser.showDialog(
                                MainGraphics.this,
                                "Выбор цвета графика",
                                kv2.getBackground()
                        );
                if (newColor != null)
                {
                    kv2.setBackground(newColor);
                    ppe.setColorFunction(newColor);
                    mainPanel.repaint();
                }
            }
        });
        kv3.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                var newColor =
                        JColorChooser.showDialog(
                                MainGraphics.this,
                                "Выбор цвета графика",
                                kv3.getBackground()
                        );
                if (newColor != null)
                {
                    kv3.setBackground(newColor);
                    ppp.setColor1(newColor);
                    mainPanel.repaint();
                }
            }
        });

        JLabel lbl1 = new JLabel("X min:");
        JLabel lbl2 = new JLabel("Y min:");
        JLabel lbl3 = new JLabel("X max:");
        JLabel lbl4 = new JLabel("Y max:");
        JLabel lbl5 = new JLabel("Цвет узлов(точек) f(x):");
        JLabel lbl6 = new JLabel("Функция f(x)=1+(x+2)^2:");
        JLabel lbl7 = new JLabel("Параметрическая функция(x=arcsin(sin(t)),y=arccos(cos(t))) f(t):");
        js1.addChangeListener(e -> {
            nmxMax.setMinimum((Double)nmxMin.getNumber() + 2 * (Double)nmxMax.getStepSize());
            a.SetXEdges((Double) js1.getValue(),(Double) js3.getValue());
            mainPanel.repaint();
        });
        js3.addChangeListener(e -> {
            nmxMin.setMaximum((Double)nmxMax.getNumber() - 2 * (Double)nmxMin.getStepSize());
            a.SetXEdges((Double) js1.getValue(),(Double) js3.getValue());
            mainPanel.repaint();
        });
        js2.addChangeListener(e -> {
            nmyMax.setMinimum((Double)nmyMin.getNumber() + 2 * (Double)nmyMax.getStepSize());
            a.SetYEdges((Double) js2.getValue(),(Double) js4.getValue());
            mainPanel.repaint();
        });
        js4.addChangeListener(e -> {
            nmyMin.setMaximum((Double)nmyMax.getNumber() - 2 * (Double)nmyMin.getStepSize());
            a.SetYEdges((Double) js2.getValue(),(Double) js4.getValue());
            mainPanel.repaint();
        });
        // раскладка для controlPanel
        GroupLayout gl1 = new GroupLayout(controlPanel);
        controlPanel.setLayout(gl1);
        gl1.setHorizontalGroup(gl1.createSequentialGroup()
                .addGap(8)
                // 1 группа
                        .addGroup(gl1.createParallelGroup()
                                .addGroup(gl1.createSequentialGroup()
                                        .addComponent(lbl1,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js1,50, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

                                )
                                .addGroup(gl1.createSequentialGroup()
                                        .addComponent(lbl2,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js2,50, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                        )
                .addGap(25)
                        // 2 группа
                       .addGroup(gl1.createParallelGroup()
                                .addGroup(gl1.createSequentialGroup()
                                        .addComponent(lbl3,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js3,50, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(gl1.createSequentialGroup()
                                        .addComponent(lbl4,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js4,50, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                       )
                .addGap(8,8,Integer.MAX_VALUE)
                // 3 группа
                      .addGroup(gl1.createParallelGroup()
                              .addGroup(gl1.createSequentialGroup()
                                      .addComponent(Point,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                      .addGap(3)
                                      .addComponent(lbl5,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                      .addGap(3)
                                      .addComponent(kv1,10,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                              )
                              .addGroup(gl1.createSequentialGroup()
                                      .addComponent(Polynomial,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                      .addGap(3)
                                      .addComponent(lbl6,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                      .addGap(3)
                                      .addComponent(kv2,10,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                              )
                              .addGroup(gl1.createSequentialGroup()
                                      .addComponent(Derivative,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                      .addGap(3)
                                      .addComponent(lbl7,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                      .addGap(3)
                                      .addComponent(kv3,10,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                              )
                              .addGroup(gl1.createSequentialGroup()
                                      .addComponent(sliderFunctionMin,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                      .addGap(3)
                                      .addComponent(sliderFunctionMax,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)

                              )


                      )
                .addGap(8)
        );
        gl1.setVerticalGroup(gl1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addGap(8)
                        //1 группа
                         .addGroup(gl1.createSequentialGroup()
                                 .addGroup(gl1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl1,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js1,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(gl1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl2,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js2,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                        )

                        //2 группа
                        .addGroup(gl1.createSequentialGroup()
                                .addGroup(gl1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl3,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js3,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(gl1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl4,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(js4,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                               )
                        )
                .addGap(25)
                        // 3 группа
                        .addGroup(gl1.createSequentialGroup()
                                .addGroup(gl1.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(Point,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl5,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(kv1,10,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )

                                .addGap(5)
                                .addGroup(gl1.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(Polynomial,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl6,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(kv2,10,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(5)
                                .addGroup(gl1.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(Derivative,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl7,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(kv3,10,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(5)
                                .addGroup(gl1.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(sliderFunctionMin,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sliderFunctionMax,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(5)
                        )
                .addGap(8)
        );

    }
    public static Double F(Double x)
    {
        return (1+Math.pow(x+2,2));
    }
    public static Double FXT(Double t)
    {
        return (Math.asin(Math.sin(t))); //Math.asin(Math.sin(t)
    }
    public static Double FYT(Double t)
    {
        return (Math.acos(Math.cos(t)));
    }

}
