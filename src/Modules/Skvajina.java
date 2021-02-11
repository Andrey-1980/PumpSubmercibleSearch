package Modules;
import javax.swing.*; import java.io.Serializable;

public class Skvajina implements Serializable {
    private static final long serialVersionUID = 1L;
    private String objName;
    private int hSkv, hStat, hDin, debetS, hPump;
    public Skvajina(String objName, int hSkv, int hStat, int hDin, int debet) {
        this.objName=objName; this.hSkv = hSkv; this.hStat = hStat; this.hDin = hDin; this.debetS = debet;
        if ((hSkv-hDin) > 3)    { this.hPump = hDin+1; }
        else { JOptionPane.showMessageDialog(null, "Столб воды < 3 м от дна скважины\n Проверьте введенные данные !"); this.hPump=0; }    }

    public String getObjName() { return objName; }
    public int getHpump() { return hPump; }
    public int getDebetS() { return debetS; }

    public static Skvajina createSkv() {
        return new Skvajina(JOptionPane.showInputDialog("Введите название объекта"),
            Integer.parseInt(JOptionPane.showInputDialog("Введите глубину скважины")),
            Integer.parseInt(JOptionPane.showInputDialog("Введите статический уровень")),
            Integer.parseInt(JOptionPane.showInputDialog("Введите динамический уровень")),
            Integer.parseInt(JOptionPane.showInputDialog("Введите дебет скважины")));
    }
    public static String printSkv(Skvajina skv) {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект - "+skv.objName+"\nГлубина скважины "+skv.hSkv+" м.\nСтатический уровень "+skv.hStat+" м.\nДинамический уровень "+skv.hDin
           +" м.\nДебет "+skv.debetS+" м3/ч\nГлубина установки насоса "+skv.hPump+" м.\nНеобходимый напор насоса до нулевой отметки "+skv.hDin+" м.");
        return sb.toString();
    }
}
