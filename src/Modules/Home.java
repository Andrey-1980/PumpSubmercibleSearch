package Modules;
import javax.swing.*; import java.io.Serializable;

public class Home implements Serializable {
    private static final long serialVersionUID = 2L;
    private String objName;  boolean poliv, bass;
    private int lSkv, maxFloor, countCiv, hHome;
    private static double qHome;
    public Home(String objName, int lSkv, int maxFloor, int countCiv, boolean poliv, boolean bass) {
        this.objName = objName; this.lSkv = lSkv; this.maxFloor = maxFloor; this.countCiv = countCiv;
        this.poliv = poliv; this.bass = bass; hHome=20+(maxFloor-1)*3+(lSkv/10+1);
        qHome=(double) countCiv/2; if (poliv) qHome++; if (bass) qHome+=(double) 1/2;    }

    public String getObjName() { return objName; }
    public int getHhome() { return hHome; }
    public double getQhome() { return qHome; }

    public static Home createHome() {
        return new Home(JOptionPane.showInputDialog("Введите название объекта"),
            Integer.parseInt(JOptionPane.showInputDialog("Введите расстояние от дома до скважины")),
            Integer.parseInt(JOptionPane.showInputDialog("Введите самый высокий этаж на котором есть водопотребление")),
            Integer.parseInt(JOptionPane.showInputDialog("Введите количество регулярно проживающих потребителей")),
            Boolean.parseBoolean(JOptionPane.showInputDialog("Есть ли система полива - введите true если есть, false если нет ")),
            Boolean.parseBoolean(JOptionPane.showInputDialog("Есть ли бассейн - введите true если есть, false если нет")));
    }
    public static String printHome(Home home) {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект - "+home.objName+"\nРасстояние от скважины до дома: "+home.lSkv+" м.\nСамый высокий этаж на котором есть водопотребление: "+
           home.maxFloor+"\nКоличество регулярно проживающих потребителей: "+home.countCiv+"\nНеобходимый напор от нулевой отметки "+home.hHome+" м."+
           "\nНеобходимый объем водоснабжения: "+home.qHome+" м3/ч.");
        if (home.poliv) sb.append("\nЕсть система полива");
        if (home.bass) sb.append("\nЕсть бассейн");
        return sb.toString();
    }
}
