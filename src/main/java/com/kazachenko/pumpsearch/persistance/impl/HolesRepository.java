package com.kazachenko.pumpsearch.persistance.impl;

import com.kazachenko.pumpsearch.constant.FilePathData;
import com.kazachenko.pumpsearch.module.Hole;
import com.kazachenko.pumpsearch.persistance.api.Repository;
import com.kazachenko.pumpsearch.persistance.util.LoadFileUtil;
import com.kazachenko.pumpsearch.persistance.util.SaveFileUtil;


import java.util.List;

public class HolesRepository implements Repository<Hole> {

    @Override
    public List<Hole> loadAll() {
        return LoadFileUtil.loadAll(FilePathData.HOLE_BASE, Hole.class);
    }

    @Override
    public void save(Hole hole) {
        List<Hole> holeList = loadAll();
        holeList.add(hole);
        SaveFileUtil.save(holeList, FilePathData.HOLE_BASE, Hole.class);
    }
}
