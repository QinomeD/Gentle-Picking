package qinomed.gentlepicking.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GentlePickingCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ONLY_HAND;

    static {
        BUILDER.push("Gentle Picking Config");

        ONLY_HAND = BUILDER.comment("Should plants be pickable only with an empty hand?").define("Only Hand", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
