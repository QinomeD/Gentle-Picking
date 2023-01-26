package qinomed.gentlepicking;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("gentlepicking")
@Mod.EventBusSubscriber(modid = "gentlepicking", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GentlePicking {

    public GentlePicking() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final TagKey<Block> PICKABLE = BlockTags.create(new ResourceLocation("gentlepicking", "pickable"));
    public static final TagKey<Block> BLACKLIST = BlockTags.create(new ResourceLocation("gentlepicking", "blacklist"));

    @SubscribeEvent
    public static void useBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        BlockState state = level.getBlockState(pos);
        if ((level.mayInteract(player, pos) && !player.isSpectator() && state.is(PICKABLE) || (state.getBlock() instanceof FlowerBlock || state.getBlock() instanceof MushroomBlock || state.getBlock() instanceof FungusBlock)) && !state.is(BLACKLIST)) {
            Block.dropResources(state, level, pos);
            level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1, 1);
            level.removeBlock(pos, false);
            state.entityInside(level, pos, player);
        }
    }
}
