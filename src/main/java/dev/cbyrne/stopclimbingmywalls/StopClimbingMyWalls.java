package dev.cbyrne.stopclimbingmywalls;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopClimbingMyWalls implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("stop-climbing-my-walls");

    private static final StopClimbingMyWalls INSTANCE = new StopClimbingMyWalls();
    private static final GameRules.Key<GameRules.BooleanRule> CAN_SPIDERS_CLIMB_WALLS = GameRuleRegistry.register(
        "canSpidersClimbWalls",
        GameRules.Category.MOBS,
        GameRuleFactory.createBooleanRule(true)
    );

    public static StopClimbingMyWalls getInstance() {
        return INSTANCE;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Spiders have become way less annoying :)");
    }

    public boolean canSpidersClimbWalls(World world) {
        return world.getGameRules().getBoolean(CAN_SPIDERS_CLIMB_WALLS);
    }
}
