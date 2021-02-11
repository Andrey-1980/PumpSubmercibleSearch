import Modules.*; import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoadObjects {

    public static Map<String, Skvajina> loadSkvjs() {
        Map<String, Skvajina> skvajins = new HashMap<>();
        try { FileInputStream fis = new FileInputStream("d:\\Workspace\\SKVBase.psb");
              ObjectInputStream ois = new ObjectInputStream(fis);
                while (ois.available() != 0) {  Skvajina skv = (Skvajina) ois.readObject(); skvajins.put(skv.getObjName(), skv); }
              ois.close(); fis.close();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return skvajins; }

    public static Map<String, Home> loadHomes() {
        Map<String, Home> homes = new HashMap<>();
        try { FileInputStream fis = new FileInputStream("d:\\Workspace\\HOMBase.psb");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (ois.available() != 0) {  Home home = (Home) ois.readObject(); homes.put(home.getObjName(), home); }
            ois.close(); fis.close();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return homes; }

    public static Map<String, Pump> loadPumps() {
        Map<String, Pump> pumps = new HashMap<>();
        try { FileInputStream fis = new FileInputStream("d:\\Workspace\\PMPBase.psb");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (ois.available() != 0) {  Pump pump = (Pump) ois.readObject(); pumps.put(pump.getObjName(), pump); }
            ois.close(); fis.close();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return pumps; }
}
