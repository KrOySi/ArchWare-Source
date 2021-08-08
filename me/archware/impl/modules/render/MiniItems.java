/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;

public class MiniItems
extends Module {
    private final NumericValue size = new NumericValue("Size", "ViewModelSize", (Module)this, 0.5f, 0.1f, 1.0f);
    private final NumericValue x = new NumericValue("x", "ViewModelX", (Module)this, 0.0f, 0.0f, 10.0f);
    private final NumericValue y = new NumericValue("y", "ViewModelY", (Module)this, 0.0f, 0.0f, 10.0f);
    private final NumericValue z = new NumericValue("Z", "ViewModelZ", (Module)this, 0.0f, 0.0f, 10.0f);

    public MiniItems() {
        super("MiniItems", Category.RENDER);
    }
}

