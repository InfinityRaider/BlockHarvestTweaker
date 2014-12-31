package com.InfinityRaider.BlockHarvestTweaker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = BlockHarvestTweaker.modId, name = BlockHarvestTweaker.modId,version = "1.0")
public class BlockHarvestTweaker {
    public static final String modId = "BlockHarvestTweaker";
    public static String[] rawData;

    @Mod.Instance(modId)
    public static BlockHarvestTweaker instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
       //Nope
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        new ForgeHooks();
        for (int i = 0; i < rawData.length; i++) {
            String data[] = IOHelper.getData(rawData[i]);   //{block, meta, tool, level}
            Block block = (Block) Block.blockRegistry.getObject(data[0]);
            if (block != null) {
                if(data[2].equals("axe")||data[2].equals("pickaxe")||data[2].equals("shovel")) {
                    int meta = Integer.parseInt(data[1]);
                    if(meta<0) {
                        for(int j=0;j<16;j++) {
                            block.setHarvestLevel(data[2], Integer.parseInt(data[3]), j);
                            LogHelper.info("Setting harvest level of " + Block.blockRegistry.getNameForObject(block) + ":" + j + " to " + block.getHarvestLevel(j) + " (tool: " + block.getHarvestTool(j) + ")");
                        }
                    }
                    else {
                        block.setHarvestLevel(data[2], Integer.parseInt(data[3]), meta);
                        LogHelper.info("Setting harvest level of " + Block.blockRegistry.getNameForObject(block) + ":" + meta + " to " + block.getHarvestLevel(meta) + " (tool: " + block.getHarvestTool(meta) + ")");
                    }
                }
                else {
                    LogHelper.info("Tool should be axe, pickaxe or shovel, not "+data[2]);
                }
            } else {
                LogHelper.info("Something went wrong on line " + i + ": " + data[0] + "," + data[1]);
            }
        }
        rawData = null;
    }

}
