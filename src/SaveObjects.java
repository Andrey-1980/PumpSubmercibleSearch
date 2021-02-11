import Modules.*; import java.io.*; import java.util.Map;

public class SaveObjects {

    public static void saveAllObjects(Map<String, Skvajina> skvajins, Map<String, Home> homes, Map<String, Pump> pumps) {
        try { FileOutputStream fos = new FileOutputStream("d:\\Workspace\\SKVBase.psb");
              ObjectOutputStream oos1 = new ObjectOutputStream(fos);
                for (Skvajina skv : skvajins.values()) { oos1.writeObject(skv); } oos1.close();
              fos = new FileOutputStream("d:\\Workspace\\HOMBase.psb");
              ObjectOutputStream oos2 = new ObjectOutputStream(fos);
                for (Home home : homes.values()) { oos2.writeObject(home); } oos2.close();
              fos = new FileOutputStream("d:\\Workspace\\PMPBase.psb");
              ObjectOutputStream oos3 = new ObjectOutputStream(fos);
                for (Pump pump : pumps.values()) { oos3.writeObject(pump); } oos3.close();
              fos.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
