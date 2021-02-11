import Modules.*; import javax.swing.*; import java.util.*;

public class Main {
    //Map<String, Skvajina> skvajins = LoadObjects.loadSkvjs();
    //Map<String, Home> homes = LoadObjects.loadHomes();
    //Map<String, Pump> pumps = LoadObjects.loadPumps();
    final static String mainDlg = "1. Ввести данные скважины\n"+"2. Ввести данные дома\n"+"3. Подбор насоса\n"+"4. Список объектов\n"+
            "5. Данные объекта\n"+"0. Выход";

    public static void main(String[] args) {
        Map<String, Skvajina> skvajins = LoadObjects.loadSkvjs();
        Map<String, Home> homes = LoadObjects.loadHomes();
        Map<String, Pump> pumps = LoadObjects.loadPumps();
        int preSelect = Integer.parseInt(JOptionPane.showInputDialog(null, mainDlg, "Сделайте выбор"));
        while (preSelect != 0) {
            switch (preSelect) {
                case 1: {   Skvajina skv = Skvajina.createSkv(); skvajins.put(skv.getObjName(), skv);  } break;
                case 2: {   Home home = Home.createHome();      homes.put(home.getObjName(), home);     } break;
                case 3: {   StringBuilder sb = allList(skvajins, homes, pumps).append("\nВведите объект для рассчета насоса:\n");
                            String s = JOptionPane.showInputDialog(null, sb, "Сделайте выбор");
                            Pump pump = Pump.selectGrundfosPump(skvajins.get(s), homes.get(s)); if (pump != null) { pumps.put(s, pump);
                            JOptionPane.showMessageDialog(null, "Подобран насос: \n\n"+pump.getModel()); }
                        } break;
                case 4: {   JOptionPane.showMessageDialog(null, allList(skvajins, homes, pumps));    } break;
                case 5: {   String s = JOptionPane.showInputDialog(allList(skvajins, homes, pumps) + "\nВведите название объекта");
                            StringBuilder sb = new StringBuilder();
                            if (skvajins.containsKey(s)) sb.append("Скважина: "+Skvajina.printSkv(skvajins.get(s))+"\n\n");
                            if (homes.containsKey(s)) sb.append("Дом: "+Home.printHome(homes.get(s))+"\n\n");
                            if (pumps.containsKey(s)) sb.append("Насос: "+Pump.printPump(pumps.get(s)));
                            JOptionPane.showMessageDialog(null, sb);
                        } break;
                default : { JOptionPane.showMessageDialog(null, "В меню нет пункта с таким номером"); }
            }
            preSelect = Integer.parseInt(JOptionPane.showInputDialog(null, mainDlg, "Сделайте выбор"));
        }
        SaveObjects.saveAllObjects(skvajins, homes, pumps);
    }

    static StringBuilder allList(Map<String, Skvajina> skvajins, Map<String, Home> homes, Map<String, Pump> pumps) {
            StringBuilder strSb = new StringBuilder("ОБЪЕКТЫ:\n\nСкважины:\n");
            for (String s : skvajins.keySet().toArray(String[]::new)) { strSb.append(s + "\n"); }
            strSb.append("\nДома:\n");
            for (String s : homes.keySet().toArray(String[]::new)) { strSb.append(s + "\n"); }
            strSb.append("\nНасосы:\n");
            for (String s : pumps.keySet().toArray(String[]::new)) { strSb.append(s + "\n"); }
            return strSb;
    }
}
