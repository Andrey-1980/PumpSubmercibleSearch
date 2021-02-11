package com.kazachenko.pumpsearch;

import com.kazachenko.pumpsearch.module.Hole;
import com.kazachenko.pumpsearch.module.Home;
import com.kazachenko.pumpsearch.module.Pump;
import com.kazachenko.pumpsearch.persistance.api.Repository;
import com.kazachenko.pumpsearch.persistance.impl.HolesRepository;
import com.kazachenko.pumpsearch.persistance.impl.HomeRepository;
import com.kazachenko.pumpsearch.persistance.impl.PumpRepository;

import javax.swing.*;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    final static String mainDlg =
            "1. Ввести данные скважины\n"
                    + "2. Ввести данные дома\n"
                    + "3. Подбор насоса\n"
                    + "4. Список объектов\n"
                    + "5. Данные объекта\n"
                    + "0. Выход";

    public static void main(String[] args) {

        Repository<Hole> holeRepository = new HolesRepository();
        Repository<Home> homeRepository = new HomeRepository();
        Repository<Pump> pumpRepository = new PumpRepository();

        Map<String, Hole> holesMap = holeRepository.loadAll()
                .stream()
                .collect(Collectors.toMap(Hole::getObjName, hole -> hole));

        Map<String, Home> homesMap = homeRepository.loadAll()
                .stream()
                .collect(Collectors.toMap(Home::getObjName, home -> home));

        Map<String, Pump> pumpsMap = pumpRepository.loadAll()
                .stream()
                .collect(Collectors.toMap(Pump::getObjName, pump -> pump));

        int preSelect = Integer.parseInt(JOptionPane.showInputDialog(null, mainDlg, "Сделайте выбор"));
        while (preSelect != 0) {
            switch (preSelect) {
                case 1: {
                    Hole hole = Hole.createSkv();
                    holeRepository.save(hole);
                    holesMap.put(hole.getObjName(), hole);
                }
                break;
                case 2: {
                    Home home = Home.createHome();
                    homeRepository.save(home);
                    homesMap.put(home.getObjName(), home);
                }
                break;
                case 3: {
                    StringBuilder sb = allList(holesMap, homesMap, pumpsMap).append("\nВведите объект для рассчета насоса:\n");
                    String s = JOptionPane.showInputDialog(null, sb, "Сделайте выбор");
                    Pump pump = Pump.selectGrundfosPump(holesMap.get(s), homesMap.get(s));
                    if (pump != null) {
                        pumpRepository.save(pump);
                        pumpsMap.put(s, pump);
                        JOptionPane.showMessageDialog(null, "Подобран насос: \n\n" + pump.getModel());
                    }
                }
                break;
                case 4: {
                    JOptionPane.showMessageDialog(null, allList(holesMap, homesMap, pumpsMap));
                }
                break;
                case 5: {
                    String s = JOptionPane.showInputDialog(allList(holesMap, homesMap, pumpsMap) + "\nВведите название объекта");
                    StringBuilder sb = new StringBuilder();
                    if (holesMap.containsKey(s)) sb.append("Скважина: " + Hole.printSkv(holesMap.get(s)) + "\n\n");
                    if (homesMap.containsKey(s)) sb.append("Дом: " + Home.printHome(homesMap.get(s)) + "\n\n");
                    if (pumpsMap.containsKey(s)) sb.append("Насос: " + Pump.printPump(pumpsMap.get(s)));
                    JOptionPane.showMessageDialog(null, sb);
                }
                break;
                default: {
                    JOptionPane.showMessageDialog(null, "В меню нет пункта с таким номером");
                }
            }
            preSelect = Integer.parseInt(JOptionPane.showInputDialog(null, mainDlg, "Сделайте выбор"));
        }
    }

    static StringBuilder allList(Map<String, Hole> skvajins, Map<String, Home> homes, Map<String, Pump> pumps) {
        StringBuilder strSb = new StringBuilder("ОБЪЕКТЫ:\n\nСкважины:\n");
        for (String s : skvajins.keySet().toArray(String[]::new)) {
            strSb.append(s + "\n");
        }
        strSb.append("\nДома:\n");
        for (String s : homes.keySet().toArray(String[]::new)) {
            strSb.append(s + "\n");
        }
        strSb.append("\nНасосы:\n");
        for (String s : pumps.keySet().toArray(String[]::new)) {
            strSb.append(s + "\n");
        }
        return strSb;
    }
}
