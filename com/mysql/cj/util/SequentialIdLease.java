/*
 * Decompiled with CFR 0.150.
 */
package com.mysql.cj.util;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SequentialIdLease {
    private Set<Integer> sequentialIdsLease = new TreeSet<Integer>();

    public int allocateSequentialId() {
        int nextSequentialId = 0;
        Iterator<Integer> it = this.sequentialIdsLease.iterator();
        while (it.hasNext() && nextSequentialId + 1 == it.next()) {
            ++nextSequentialId;
        }
        this.sequentialIdsLease.add(++nextSequentialId);
        return nextSequentialId;
    }

    public void releaseSequentialId(int sequentialId) {
        this.sequentialIdsLease.remove(sequentialId);
    }
}

