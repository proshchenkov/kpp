package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Lab5 {
    private JButton squareButton, triangleButton, circleButton, fillButton, clearButton, colorButton;
    static JSpinner redSpinner, greenSpinner, blueSpinner;
    private Paint drawArea;
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                drawArea.clear();
            } else if (e.getSource() == squareButton) {
                drawArea.square();
            } else if (e.getSource() == circleButton) {
                drawArea.circle();
            } else if (e.getSource() == triangleButton) {
                drawArea.triangle();
            } else if (e.getSource() == fillButton) {
                drawArea.fillFigure();
            } else if (e.getSource() == colorButton) {
                drawArea.color();
            }
        }
    };

    public static void main(String[] args) {
        new Lab5().show();
    }

    private void show() {
        JFrame frame = new JFrame("Графический редактор");
        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        drawArea = new Paint();
        JPanel panel = new JPanel();
        clearButton = new JButton("Очистить");
        clearButton.addActionListener(actionListener);
        circleButton = new JButton("Круг");
        circleButton.addActionListener(actionListener);
        triangleButton = new JButton("Треугольник");
        triangleButton.addActionListener(actionListener);
        squareButton = new JButton("Квадрат");
        squareButton.addActionListener(actionListener);
        fillButton = new JButton("Заливка");
        fillButton.addActionListener(actionListener);
        colorButton = new JButton("Цвет");
        colorButton.addActionListener(actionListener);
        SpinnerModel redSpinnerModel = new SpinnerNumberModel(0, 0, 255, 10);
        SpinnerModel greenSpinnerModel = new SpinnerNumberModel(0, 0, 255, 10);
        SpinnerModel blueSpinnerModel = new SpinnerNumberModel(0, 0, 255, 10);
        redSpinner = new JSpinner(redSpinnerModel);
        greenSpinner = new JSpinner(greenSpinnerModel);
        blueSpinner = new JSpinner(blueSpinnerModel);
        panel.add(clearButton);
        panel.add(circleButton);
        panel.add(triangleButton);
        panel.add(squareButton);
        panel.add(fillButton);
        panel.add(redSpinner);
        panel.add(greenSpinner);
        panel.add(blueSpinner);
        panel.add(colorButton);
        container.add(panel, BorderLayout.NORTH);
        container.add(drawArea, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class Triangle {
    private int x1, x2, x3, y1, y2, y3;

    Triangle(int x1, int x2, int x3, int y1, int y2, int y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    int[] getCoordinates() {
        return new int[]{x1, x2, x3, y1, y2, y3};
    }
}

class Square {
    private int x1, y1, x2, y2;

    Square(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    int[] getCoordinates() {
        return new int[]{this.x1, this.y1, this.x2, this.y2};
    }
}

class Circle {
    private int x1, y1, x2, y2;

    Circle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    int[] getCoordinates() {
        return new int[]{this.x1, this.y1, this.x2, this.y2};
    }
}

class Paint extends JComponent {
    private Image image;
    private Graphics2D g2d;
    private String figure = "";
    private ArrayList<Square> squares = new ArrayList<>();
    private ArrayList<Circle> circles = new ArrayList<>();
    private ArrayList<Triangle> triangles = new ArrayList<>();
    private int currentX, currentY, oldX, oldY;

    private boolean pointInTriangle(int P_x, int P_y, int A_x, int A_y, int B_x, int B_y, int C_x, int C_y) {
        int as_x = P_x - A_x;
        int as_y = P_y - A_y;
        boolean s_ab = (B_x - A_x) * as_y - (B_y - A_y) * as_x > 0;
        if ((C_x - A_x) * as_y - (C_y - A_y) * as_x > 0 == s_ab) return false;
        if ((C_x - B_x) * (P_y - B_y) - (C_y - B_y) * (P_x - B_x) > 0 != s_ab) return false;
        return true;
    }

    Paint() {
        setDoubleBuffered(false);
        addMouseMotionListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (figure.equals("paint")) {
                    currentX = e.getX();
                    currentY = e.getY();
                    g2d.fillOval(currentX, currentY, 8, 8);
                    repaint();
                    oldX = currentX;
                    oldY = currentX;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                if (figure.equals("fill")) {
                    int[] coordinates;
                    for (Circle circle : circles) {
                        coordinates = circle.getCoordinates();
                        if ((oldY >= coordinates[1]) && (oldX >= coordinates[0]) && (oldX <= coordinates[0] + coordinates[2]) && (oldY <= coordinates[1] + coordinates[3])) {
                            g2d.fillOval(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                        }
                    }
                    for (Square square : squares) {
                        coordinates = square.getCoordinates();
                        if ((oldY >= coordinates[1]) && (oldX >= coordinates[0]) && (oldX <= coordinates[0] + coordinates[2]) && (oldY <= coordinates[1] + coordinates[3])) {
                            g2d.fillRect(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                        }
                    }
                    for (Triangle triangle : triangles) {
                        coordinates = triangle.getCoordinates();
                        if (pointInTriangle(oldX, oldY, coordinates[2], coordinates[5], coordinates[0], coordinates[3], coordinates[1], coordinates[4])) {
                            g2d.fillPolygon(new int[]{coordinates[0], coordinates[1], coordinates[2]}, new int[]{coordinates[3], coordinates[4], coordinates[5]}, 3);
                        }
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                if (g2d != null) {
                    switch (figure) {
                        case "1":
                            g2d.drawOval(oldX, oldY, Math.abs(currentX - oldX), Math.abs(currentY - oldY));
                            circles.add(new Circle(oldX, oldY, Math.abs(currentX - oldX), Math.abs(currentY - oldY)));
                            break;
                        case "2":
                            g2d.drawRect(oldX, oldY, Math.abs(currentX - oldX), Math.abs(currentY - oldY));
                            squares.add(new Square(oldX, oldY, Math.abs(currentX - oldX), Math.abs(currentY - oldY)));
                            break;
                        case "0":
                            g2d.drawPolygon(new int[]{oldX, currentX, oldX - (currentX - oldX)}, new int[]{oldY, currentY, currentY}, 3);
                            triangles.add(new Triangle(oldX, currentX, oldX - (currentX - oldX), oldY, currentY, currentY));
                            break;
                    }
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2d = (Graphics2D) image.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    void clear() {
        g2d.setPaint(Color.white);
        g2d.fillRect(0, 0, getSize().width, getSize().height);
        g2d.setPaint(new Color((Integer) Lab5.redSpinner.getValue(), (Integer) Lab5.greenSpinner.getValue(), (Integer) Lab5.blueSpinner.getValue()));
        circles.clear();
        triangles.clear();
        squares.clear();
        repaint();
    }

    void triangle() {
        figure = "0";
    }

    void circle() {
        figure = "1";
    }

    void square() {
        figure = "2";
    }

    void fillFigure() {
        figure = "fill";
    }

    void color() {
        g2d.setPaint(new Color((Integer) Lab5.redSpinner.getValue(), (Integer) Lab5.greenSpinner.getValue(), (Integer) Lab5.blueSpinner.getValue()));
    }
}