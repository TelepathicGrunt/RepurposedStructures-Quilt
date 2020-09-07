package com.telepathicgrunt.repurposedstructures.utils;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loom.util.FabricApiExtension;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.BaseText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadNbtBlock extends Block {
    public LoadNbtBlock() {
        super(AbstractBlock.Settings.of(Material.METAL, MaterialColor.LIGHT_GRAY).requiresTool().strength(-1.0F, 3600000.0F).dropsNothing());
    }

    static Block LOAD_NBT_BLOCK = new LoadNbtBlock();
    public static final Item LOAD_NBT_ITEM = new BlockItem(LOAD_NBT_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE));

    public static void instantiateNbtBlock(){
        Registry.register(Registry.BLOCK, new Identifier(RepurposedStructures.MODID, "load_nbt_block"), LOAD_NBT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(RepurposedStructures.MODID, "load_nbt_block"), LOAD_NBT_ITEM);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!(world instanceof ServerWorld)) return ActionResult.PASS;

        String mainPath = FabricLoader.getInstance().getGameDir().toString().replace("\\run\\.", "");
        String resourcePath = mainPath+"\\src\\main\\resources\\data";

        player.sendMessage(new TranslatableText(" Working.... "), true);

        // Finds and gets all identifiers for pieces
        List<File> files = new ArrayList<>();
        List<Identifier> identifiers = new ArrayList<>();
        StructureNbtDataFixer.setAllNbtFilesToList(resourcePath, files);
        for(File file : files){
            String modifiedFileName = file.getAbsolutePath().replace(resourcePath+"\\","").replace("\\structures\\",":").replace(".nbt","").replace('\\','/');
            identifiers.add(new Identifier(modifiedFileName));
        }

        // Size of area we will need
        int rowCount = (int) Math.max(Math.ceil(identifiers.size()), 1);
        int columnCount = 3;
        BlockPos bounds = new BlockPos(32 * (rowCount+2), 32, 32 * columnCount);

        // Fill/clear area with structure void
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(pos);
        mutable.move(1,0,1);

        for(; mutable.getX() < pos.getX()+bounds.getX(); mutable.move(1,0,0)){
            for(; mutable.getY() < pos.getY()+bounds.getY(); mutable.move(0,1,0)){
                for(; mutable.getZ() < pos.getZ()+bounds.getZ(); mutable.move(0,0,1)){
                    if(mutable.getY() == pos.getY())
                        world.setBlockState(mutable, Blocks.STONE_SLAB.getDefaultState(), 2);
                    else
                        world.setBlockState(mutable, Blocks.STRUCTURE_VOID.getDefaultState(), 2);
                }
                mutable.move(0,0, -(bounds.getZ()-1));
            }
            mutable.move(0, -bounds.getY(), 0);

            player.sendMessage(new TranslatableText(" Working at "+mutable.toShortString()), true);
        }

        // Places structure blocks and loads pieces
        mutable.set(pos);
        mutable.move(1,0,1);

        for(int pieceIndex = 1; pieceIndex <= identifiers.size(); pieceIndex++){

            world.setBlockState(mutable, Blocks.STRUCTURE_BLOCK.getDefaultState().with(StructureBlock.MODE, StructureBlockMode.LOAD), 3);
            BlockEntity be = world.getBlockEntity(mutable);
            if(be instanceof StructureBlockBlockEntity){
                StructureBlockBlockEntity structureBlockBlockEntity = (StructureBlockBlockEntity)be;
                structureBlockBlockEntity.setStructureName(identifiers.get(pieceIndex-1)); // set identifier

                structureBlockBlockEntity.setMode(StructureBlockMode.LOAD);
                structureBlockBlockEntity.loadStructure((ServerWorld) world, false); // prepare area
                structureBlockBlockEntity.loadStructure((ServerWorld) world,true); // load structure

                structureBlockBlockEntity.setStructureName(new Identifier(identifiers.get(pieceIndex-1).toString().replace("village/", ""))); // set identifier
                structureBlockBlockEntity.setMode(StructureBlockMode.SAVE);
                //structureBlockBlockEntity.saveStructure(true); //save structure
                //structureBlockBlockEntity.setShowAir(true);
                structureBlockBlockEntity.setIgnoreEntities(false);
            }

            mutable.move(0,0,32);


            player.sendMessage(new TranslatableText(" Working making structure: "+identifiers.get(pieceIndex-1)), true);

            // Move back to start of row
            if(pieceIndex % columnCount == 0){
                mutable.move(32,0, (-32*(columnCount)));
            }
        }

        return ActionResult.SUCCESS;
    }
}
