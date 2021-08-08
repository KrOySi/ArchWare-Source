/*
 * Decompiled with CFR 0.150.
 */
package me.archware.impl.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.archware.ArchWare;
import me.archware.base.event.EventTarget;
import me.archware.base.module.Category;
import me.archware.base.module.Module;
import me.archware.base.setting.NumericValue;
import me.archware.base.setting.StringValue;
import me.archware.impl.events.EventPacketReceive;
import me.archware.impl.events.EventRender3D;
import me.archware.impl.events.EventUpdate;
import me.archware.impl.utils.BlockUtils;
import me.archware.impl.utils.RenderUtil;
import me.archware.notifications.Notification;
import me.archware.notifications.NotificationManager;
import me.archware.notifications.NotificationType;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketMultiBlockChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class Xray
extends Module {
    private List<BlockPos> blocks = new ArrayList<BlockPos>();
    private StringValue mode = new StringValue("Mode", "XrayMode", this, "Default", new String[]{"Default", "Xray-Bypass"});
    private NumericValue range = new NumericValue("Range", "XrayRange", (Module)this, 15.0f, 5.0f, 75.0f);
    private long max;
    private BlockPos current;
    private final List<BlockPos> DETECTED_BLOCKS = new ArrayList<BlockPos>();

    public Xray() {
        super("Xray", Category.RENDER);
    }

    @Override
    public void onEnable() {
        if (this.mode.getValueString().equalsIgnoreCase("Xray-Bypass")) {
            this.DETECTED_BLOCKS.clear();
            this.blocks.clear();
            for (BlockPos block : BlockUtils.getBlocksInDistance((int)this.range.getValueNumeric())) {
                if (this.mc.world.getBlockState(block).getBlock() == Blocks.AIR || this.mc.world.getBlockState(block).getBlock() == Blocks.STONE) continue;
                this.blocks.add(block);
            }
            new Thread(() -> {
                for (BlockPos block : this.blocks) {
                    if (!this.isToggled()) {
                        return;
                    }
                    this.current = this.current == null ? this.blocks.get(0) : this.blocks.get(this.blocks.indexOf(this.current) + 1);
                    this.mc.getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.current, EnumFacing.NORTH));
                    try {
                        Thread.sleep(26L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                NotificationManager.addNotification(new Notification("Xray Bypass: Done.", NotificationType.OK));
            }).start();
        }
        super.onEnable();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        this.setSuffix(this.blocks.indexOf(this.current) + " / " + this.blocks.size());
    }

    @EventTarget
    public void onRender(EventRender3D event) {
        switch (this.mode.getValueString()) {
            case "Xray-Bypass": {
                for (BlockPos block : this.DETECTED_BLOCKS) {
                    Block b = this.mc.world.getBlockState(block).getBlock();
                    Color color = Color.WHITE;
                    if (b == Blocks.DIAMOND_ORE) {
                        color = Color.CYAN;
                    } else if (b == Blocks.GOLD_ORE) {
                        color = Color.YELLOW;
                    } else if (b == Blocks.IRON_ORE) {
                        color = Color.PINK;
                    } else if (b == Blocks.REDSTONE_ORE) {
                        color = Color.RED;
                    }
                    double[] cords = new double[]{(double)block.getX() - this.mc.getRenderManager().renderPosX, (double)block.getY() - this.mc.getRenderManager().renderPosY, (double)block.getZ() - this.mc.getRenderManager().renderPosZ};
                    RenderUtil.drawOutline(new AxisAlignedBB(cords[0], cords[1], cords[2], cords[0] + 1.0, cords[1] + 1.0, cords[2] + 1.0), 1.4f, color);
                }
                break;
            }
            case "Default": {
                for (BlockPos block : BlockUtils.getBlocksInDistance((int)this.range.getValueNumeric())) {
                    Block b = this.mc.world.getBlockState(block).getBlock();
                    if (!this.isValidBlock(b)) continue;
                    Color color = Color.WHITE;
                    if (b == Blocks.DIAMOND_ORE) {
                        color = Color.CYAN;
                    } else if (b == Blocks.GOLD_ORE) {
                        color = Color.YELLOW;
                    } else if (b == Blocks.IRON_ORE) {
                        color = Color.PINK;
                    } else if (b == Blocks.REDSTONE_ORE) {
                        color = Color.RED;
                    }
                    double[] cords = new double[]{(double)block.getX() - this.mc.getRenderManager().renderPosX, (double)block.getY() - this.mc.getRenderManager().renderPosY, (double)block.getZ() - this.mc.getRenderManager().renderPosZ};
                    RenderUtil.drawOutline(new AxisAlignedBB(cords[0], cords[1], cords[2], cords[0] + 1.0, cords[1] + 1.0, cords[2] + 1.0), 1.7f, color);
                }
                break;
            }
        }
    }

    @EventTarget
    public void onPacket(EventPacketReceive event) {
        if (this.mode.getValueString().equals("Xray-Bypass")) {
            if (event.getPacket() instanceof SPacketBlockChange) {
                SPacketBlockChange packet = (SPacketBlockChange)event.getPacket();
                if (this.isValidBlock(packet.getBlockState().getBlock()) && !this.DETECTED_BLOCKS.contains(packet.getBlockPosition())) {
                    this.DETECTED_BLOCKS.add(packet.getBlockPosition());
                    ArchWare.sendChatMessage("Detected " + packet.getBlockState().getBlock().getLocalizedName() + " at " + packet.getBlockPosition().getX() + ":" + packet.getBlockPosition().getY() + ":" + packet.getBlockPosition().getZ());
                }
            } else if (event.getPacket() instanceof SPacketMultiBlockChange) {
                SPacketMultiBlockChange packet = (SPacketMultiBlockChange)event.getPacket();
                for (SPacketMultiBlockChange.BlockUpdateData block : packet.getChangedBlocks()) {
                    if (!this.isValidBlock(block.getBlockState().getBlock()) || this.DETECTED_BLOCKS.contains(block.getPos())) continue;
                    this.DETECTED_BLOCKS.add(block.getPos());
                    ArchWare.sendChatMessage("Detected " + block.getBlockState().getBlock().getLocalizedName() + " at " + block.getPos().getX() + ":" + block.getPos().getY() + ":" + block.getPos().getZ());
                }
            }
        }
    }

    private boolean isValidBlock(Block block) {
        return block == Blocks.DIAMOND_ORE || block == Blocks.GOLD_ORE || block == Blocks.IRON_ORE || block == Blocks.REDSTONE_ORE;
    }
}

