/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.utils;

import java.util.List;
import me.archware.impl.utils.MathUtils;

public class ListUtil {
    public static <T> T getRandomItemInArrayList(List<T> item) {
        return item.get((int)MathUtils.randomNumber(0.0, item.size()));
    }

    public static <T> List<T> copy(List<T> item, List<T> item2) {
        item2.addAll(item);
        return item2;
    }
}

