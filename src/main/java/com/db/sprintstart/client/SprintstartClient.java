package com.db.sprintstart.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;

public class SprintstartClient implements ClientModInitializer {

    // Flag to ensure the toggle only happens once per game launch
    private static boolean hasSprintedThisSession = false;
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Check if player exists (meaning they've joined a world)
            // and if we haven't performed the action yet
            if (client.player != null && !hasSprintedThisSession) {

                // Get the sprint key binding
                KeyBinding sprintKey = client.options.sprintKey;

                // Simulate the press
                sprintKey.setPressed(true);
                sprintKey.setPressed(false);

                // Mark as done so it doesn't trigger again until the game restarts
                hasSprintedThisSession = true;

                // Optional: Log to console for debugging
                System.out.println("[SprintStart] Simulated initial sprint press.");
            }
        });
    }
}
