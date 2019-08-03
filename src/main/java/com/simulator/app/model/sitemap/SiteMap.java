package com.simulator.app.model.sitemap;

import java.util.List;
import java.util.Map;

import com.simulator.app.model.siteblock.SiteBlock;

public class SiteMap {

    private static SiteMap instance = null;
    private Map<List,SiteBlock> map = null;

    private SiteMap() {}

    public static SiteMap getInstance() {
        if (instance == null) {
            synchronized(SiteMap.class) {
                if (instance == null) {
                    instance = new SiteMap();
                }
            }
        }
        return instance;
    }

    public Map<List, SiteBlock> getMap() {
        return map;
    }

    public void setMap(Map<List, SiteBlock> map) {
        this.map = map;
    }
}
