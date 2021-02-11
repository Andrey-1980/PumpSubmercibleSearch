package Modules;
import javax.swing.*; import java.io.Serializable;

public class Pump implements Serializable {
    private static final long serialVersionUID = 3L;
    private String objName, model;
    private int q, h;
    public Pump(String objName, String model, int q, int h) { this.objName=objName; this.model=model; this.q=q; this.h=h; }

    public String getModel() { return model; }
    public String getObjName() { return objName; }

    public static Pump selectGrundfosPump(Skvajina skv, Home home) {
        double qr, hr;
        int qp, hp;

        if (skv == null) { JOptionPane.showMessageDialog(null, "Нет данных по скважине"); return null; }
        if (home == null) { JOptionPane.showMessageDialog(null, "Нет данных по дому"); return null; }

        if ((double) skv.getDebetS() >= home.getQhome()) qr=home.getQhome();
        else {  JOptionPane.showMessageDialog(null, "Так как необходимый объем водопотребления дома больше дебета скважины" +
                " производительность насоса будет ограничена дебетом скважины");    qr = (double) skv.getDebetS();  }
        hr=skv.getHpump()+home.getHhome();

        if (qr>0&&qr<=1.2) qp=1; else if(qr>1.2&&qr<=2.2) qp=2; else if(qr>2.2&&qr<=3.2) qp=3; else if(qr>3.2&&qr<=5.5) qp=5; else if(qr>5.5&&qr<=7.5) qp=7;
        else { JOptionPane.showMessageDialog(null, "Для расчетной производительности: "+qr+", нет подходящего насоса"); qp=0; }
        switch (qp) {   case 0: hp=0;
            case 1: { if(hr>0&&hr<35)hp=35; else if(hr>=35&&hr<50)hp=50; else if(hr>=50&&hr<65)hp=65; else if(hr>=65&&hr<80)hp=80;
                      else if(hr>=80&&hr<95)hp=95; else if(hr>=95&&hr<110)hp=110; else if(hr>=110&&hr<125)hp=125;
                      else if(hr>=125&&hr<140)hp=140; else if(hr>=140&&hr<155)hp=155; else hp=0; } break;
            case 2: { if(hr>0&&hr<35)hp=35; else if(hr>=35&&hr<55)hp=55; else if(hr>=55&&hr<70)hp=70; else if(hr>=70&&hr<85)hp=85;
                      else if(hr>=85&&hr<100)hp=100; else if(hr>=100&&hr<115)hp=115; else hp=0; } break;
            case 3: { if(hr>0&&hr<30)hp=30; else if(hr>=30&&hr<40)hp=40; else if(hr>=40&&hr<55)hp=55; else if(hr>=55&&hr<65)hp=65;
                      else if(hr>=65&&hr<80)hp=80; else if(hr>=80&&hr<95)hp=95; else if(hr>=95&&hr<105)hp=105; else hp=0; } break;
            case 5: { if(hr>0&&hr<15)hp=15; else if(hr>=15&&hr<25)hp=25; else if(hr>=25&&hr<35)hp=35; else if(hr>=35&&hr<50)hp=50;
                      else if(hr>=50&&hr<60)hp=60; else if(hr>=60&&hr<70)hp=70; else hp=0; } break;
            case 7: { if(hr>0&&hr<15)hp=15; else if(hr>=15&&hr<30)hp=30; else if(hr>=30&&hr<40)hp=40; else hp=0; } break;
            default: { hp=0; }
        }
        return new Pump(skv.getObjName(), "SQ "+qp+"-"+hp, qp, hp);
    }
    public static String printPump(Pump pump) {
        StringBuilder sb = new StringBuilder();
        sb.append("Объект - "+pump.objName+"\nМодель насоса "+pump.model+"\nНоминальная производительность "+pump.q+" м3/ч.\nНоминальный напор "+pump.h+" м.");
        return sb.toString();
    }


}
