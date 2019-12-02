package com.bdoemu.gameserver.model.actions.templates;

import com.bdoemu.commons.io.FileBinaryReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ActionScriptActionChartT
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 17:06
 * VERSION 1.0
 */

public class ActionScriptActionChartT {

    private final Map<String, ActionChartPackageMapT> actionPackagesMap;

    public ActionScriptActionChartT(final FileBinaryReader reader) {
        this.actionPackagesMap = new HashMap<>();
        for (int actionMapCount = reader.readD(), actionMapIndex = 0; actionMapIndex < actionMapCount; ++actionMapIndex) {
            final ActionChartPackageMapT actionPackageMap = new ActionChartPackageMapT(reader);
            this.actionPackagesMap.put(actionPackageMap.getPackageName().toLowerCase(), actionPackageMap);
        }
    }

    public ActionChartPackageMapT getActionChartPackageMap(final String packageName) {
        if (packageName == null) {
            return null;
        }
        return this.actionPackagesMap.get(packageName.toLowerCase());
    }

    public Map<String, ActionChartPackageMapT> getActionPackagesMap() {
        return actionPackagesMap;
    }
}
