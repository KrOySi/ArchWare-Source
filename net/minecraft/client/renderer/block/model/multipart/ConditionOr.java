/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  com.google.common.collect.Iterables
 *  javax.annotation.Nullable
 */
package net.minecraft.client.renderer.block.model.multipart;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import javax.annotation.Nullable;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.multipart.ICondition;

public class ConditionOr
implements ICondition {
    final Iterable<ICondition> conditions;

    public ConditionOr(Iterable<ICondition> conditionsIn) {
        this.conditions = conditionsIn;
    }

    @Override
    public Predicate<IBlockState> getPredicate(final BlockStateContainer blockState) {
        return Predicates.or((Iterable)Iterables.transform(this.conditions, (Function)new Function<ICondition, Predicate<IBlockState>>(){

            @Nullable
            public Predicate<IBlockState> apply(@Nullable ICondition p_apply_1_) {
                return p_apply_1_ == null ? null : p_apply_1_.getPredicate(blockState);
            }
        }));
    }
}

