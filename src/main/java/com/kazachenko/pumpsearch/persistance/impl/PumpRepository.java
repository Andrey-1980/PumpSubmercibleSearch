package com.kazachenko.pumpsearch.persistance.impl;

import com.kazachenko.pumpsearch.constant.FilePathData;
import com.kazachenko.pumpsearch.module.Pump;
import com.kazachenko.pumpsearch.persistance.api.Repository;
import com.kazachenko.pumpsearch.persistance.util.LoadFileUtil;
import com.kazachenko.pumpsearch.persistance.util.SaveFileUtil;

import java.util.List;

public class PumpRepository implements Repository<Pump> {

    @Override
    public List<Pump> loadAll() {
        return LoadFileUtil.loadAll(FilePathData.PMP_BASE, Pump.class);
    }

    @Override
    public void save(Pump model) {
        List<Pump> pumpList = loadAll();
        pumpList.add(model);
        SaveFileUtil.save(pumpList, FilePathData.PMP_BASE, Pump.class);
    }
}
