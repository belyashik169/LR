package two;


public class Point3d {
    private double xCoord; // координата X
    private double yCoord;//координата Y
    private double zCoord; // координата z

    public Point3d(double x, double y, double z) {  //Конструктор инициализации
        setX(x);
        setY(y);
        setZ(z);
    }

    public Point3d(double Coord) {
        setX(Coord);
        setY(Coord);
        setZ(Coord);
    }

    public Point3d() { //Конструктор по умолчанию .
        this(0, 0, 0);
    }

    public double getX() { //Возвращение координаты x
        return xCoord;
    }

    public double getY() { //Возвращение координаты Y
        return yCoord;
    }

    public double getZ() { // возвращение координаты z
        return zCoord;
    }

    public void setX(double val) { //Установка значения координаты X
        xCoord = val;
    }

    public void setY(double val) { //Установка значения координаты Y
        yCoord = val;
    }

    public void setZ(double val) { //Установка значения координаты Z
        zCoord = val;
    }

    public void SET(double val, double va, double v) { 
        xCoord = val;
        yCoord = va;
        zCoord = v;
    }

    public boolean equalsTo(Point3d m) {
        return (xCoord==m.xCoord & yCoord==m.yCoord & zCoord==m.zCoord);
    }

    public static double distanceTo(Point3d m, Point3d n) {
        return Math.sqrt(Math.pow((m.xCoord - n.xCoord), 2) + Math.pow((m.yCoord - n.yCoord), 2) + Math.pow((m.zCoord - n.zCoord), 2));
    }

    public static double computeArea(Point3d m, Point3d n, Point3d o) {
        double a = distanceTo(m, n);
        double b = distanceTo(n, o);
        double c = distanceTo(o, m);
        double p = (a + b + c) / 2;
        return  Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}