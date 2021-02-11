package com.kazachenko.pumpsearch.persistance.impl;

import com.kazachenko.pumpsearch.constant.FilePathData;
import com.kazachenko.pumpsearch.module.Home;
import com.kazachenko.pumpsearch.persistance.api.Repository;
import com.kazachenko.pumpsearch.persistance.util.LoadFileUtil;
import com.kazachenko.pumpsearch.persistance.util.SaveFileUtil;

import java.util.List;

public class HomeRepository implements Repository<Home> {

    @Override
    public List<Home> loadAll() {
        return LoadFileUtil.loadAll(FilePathData.HOME_BASE, Home.class);
    }

    @Override
    public void save(Home home) {
        List<Home> homeList = loadAll();
        homeList.add(home);
        SaveFileUtil.save(homeList, FilePathData.HOME_BASE, Home.class);
    }
}
