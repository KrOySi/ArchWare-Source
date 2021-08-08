/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render.tabgui;

import java.util.ArrayList;
import java.util.List;
import me.archware.base.module.Category;
import me.archware.impl.modules.render.tabgui.Tab;

public class TabManager {
    private static List<Tab> REGISTERED_TABS;

    public static void init() {
        REGISTERED_TABS = new ArrayList<Tab>();
        float posY = 15.0f;
        for (Category c : Category.values()) {
            REGISTERED_TABS.add(new Tab(posY, c));
            posY += 12.0f;
        }
        REGISTERED_TABS.get(0).setSelected(true);
    }

    public static List<Tab> getTabs() {
        return REGISTERED_TABS;
    }
}

