package com.noty.auto.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.noty.auto.AutoTotem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

/**
 * Client-side entrypoint and core logic for AutoTotem.
 * Handles keybind toggle, health checks, and item swapping.
 */
public class AutoTotemClient implements ClientModInitializer {
    private static KeyMapping autoTotemKeybind;

    // Whether the automatic swapping feature is currently enabled
    private static boolean enabled = true;

    // Stores the slot ID (9-44) of the item that was swapped *out* of the off-hand
    private static int previousOffhandSlot = -1;

    // Constants for health thresholds (2 hearts = 4 health, 5 hearts = 10 health)
    private static final float SWAP_IN_HEALTH = 4.0F;
    private static final float SWAP_OUT_HEALTH = 10.0F;

    // Slot index for the off-hand in the player inventory screen handler
    private static final int OFFHAND_SLOT_ID = 40;

    private static final KeyMapping.Category AUTO_TOTEM_CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath(AutoTotem.MOD_ID, "general"));

    @Override
    public void onInitializeClient() {
        registerKeybind();
        registerClientTickEvent();
        AutoTotem.LOGGER.info("AutoTotem Keybind and Tick Registered.");
    }

    private void registerKeybind() {
        autoTotemKeybind = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.autototem.toggle",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                AUTO_TOTEM_CATEGORY
        ));
    }

    private void registerClientTickEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // Toggle enable/disable on key press
            while (autoTotemKeybind.consumeClick()) {
                enabled = !enabled;

                // Show status in chat
                String status = enabled ? "§aEnabled" : "§cDisabled";

                // FIXED: Removed the second 'boolean' parameter. sendOverlayMessage only takes the Component.
                client.player.sendOverlayMessage(
                        net.minecraft.network.chat.Component.literal("AutoTotem: " + status)
                );

                AutoTotem.LOGGER.info("AutoTotem toggled: {}", enabled ? "Enabled" : "Disabled");
            }

            // Only run automatic swapping when the feature is enabled
            if (enabled && client.player.isAlive()) {
                handleHealthBasedSwap(client);
            }

            // Reset state if player dies or leaves the world
            if (!client.player.isAlive() || client.level == null) {
                previousOffhandSlot = -1;
            }
        });
    }

    private int findTotemSlot(Minecraft client) {
        Inventory inventory = client.player.getInventory();
        for (int i = 0; i < Inventory.INVENTORY_SIZE; i++) {
            if (inventory.getItem(i).is(Items.TOTEM_OF_UNDYING)) {
                return i < 9 ? i + 36 : i;
            }
        }
        return -1;
    }

    private void swapItems(Minecraft client, int sourceSlot, boolean recordOriginalSlot) {
        if (client.player == null || client.gameMode == null) return;

        if (recordOriginalSlot) {
            previousOffhandSlot = sourceSlot;
        }

        client.gameMode.handleContainerInput(
                client.player.containerMenu.containerId,
                sourceSlot,
                OFFHAND_SLOT_ID,
                ContainerInput.SWAP,
                client.player
        );
        AutoTotem.LOGGER.info("Performed Totem Swap from slot {} to off-hand.", sourceSlot);
    }

    private void swapBackItems(Minecraft client) {
        if (client.player == null || client.gameMode == null || previousOffhandSlot == -1) return;

        client.gameMode.handleContainerInput(
                client.player.containerMenu.containerId,
                OFFHAND_SLOT_ID,
                previousOffhandSlot,
                ContainerInput.SWAP,
                client.player
        );
        AutoTotem.LOGGER.info("Performed Auto-Swap Back to original slot {}.", previousOffhandSlot);
        previousOffhandSlot = -1;
    }

    private void handleHealthBasedSwap(Minecraft client) {
        Inventory inventory = client.player.getInventory();
        float health = client.player.getHealth();

        boolean isOffhandTotem = inventory.getItem(OFFHAND_SLOT_ID).is(Items.TOTEM_OF_UNDYING);
        int totemSlotId = findTotemSlot(client);

        // Auto-Swap IN (health ≤ 2 hearts)
        if (health <= SWAP_IN_HEALTH && !isOffhandTotem && totemSlotId != -1) {
            swapItems(client, totemSlotId, true);
            return;
        }

        // Auto-Swap BACK (health > 5 hearts)
        if (isOffhandTotem && previousOffhandSlot != -1 && health > SWAP_OUT_HEALTH) {
            swapBackItems(client);
        }
    }
}