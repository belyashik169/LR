package two;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point3d a = new Point3d();
        Point3d b = new Point3d();
        Point3d c = new Point3d();
        while (true) {
            System.out.println("a");
            a.SET(sc.nextInt(), sc.nextInt(), sc.nextInt());
            System.out.println("b");
            b.SET(sc.nextInt(), sc.nextInt(), sc.nextInt());
            System.out.println("c");
            c.SET(sc.nextInt(), sc.nextInt(), sc.nextInt());
            double q = Point3d.distanceTo(a, b);
            double w = Point3d.distanceTo(b, c);
            double e = Point3d.distanceTo(c, a);
            if (q > 0 & w > 0 & e > 0) break;
            System.out.println("Введены некорректные данные");
            System.out.println("Повторите ввод");
        }
        System.out.println(a.getX() + ";" + a.getY() + ";" + a.getZ());
        System.out.println(b.getX() + ";" + b.getY() + ";" + b.getZ());
        System.out.println(c.getX() + ";" + c.getY() + ";" + c.getZ());
        System.out.println(Point3d.distanceTo(a, b) + ";" + Point3d.distanceTo(b, c) + ";" + Point3d.distanceTo(c, a) + ";");
    }
}
