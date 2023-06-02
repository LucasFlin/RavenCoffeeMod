package com.thewandererraven.ravencoffee.containers.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.thewandererraven.ravencoffee.containers.SackMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class SackScreen extends AbstractContainerScreen<SackMenu> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("ravencoffee", "textures/gui/menus/sack.png");
    public static final float STATION_NAME_YPOS = 6;
    private SackMenu sackMenu;

    public SackScreen(SackMenu sackMenu, Inventory playerInventory, Component title) {
        super(sackMenu, playerInventory, title);
    }


    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        final float LABEL_XPOS = SackMenu.PLAYER_INVENTORY_XPOS;
        final float FONT_Y_SPACING = 12;
        this.font.draw(poseStack, this.title,
                LABEL_XPOS, STATION_NAME_YPOS, Color.darkGray.getRGB());  //this.font.drawString;

        final float PLAYER_INV_LABEL_YPOS = SackMenu.PLAYER_INVENTORY_YPOS - FONT_Y_SPACING;
        this.font.draw(poseStack, this.playerInventoryTitle,                  ///    this.font.drawString
                LABEL_XPOS, PLAYER_INV_LABEL_YPOS, Color.darkGray.getRGB());
    }
}
