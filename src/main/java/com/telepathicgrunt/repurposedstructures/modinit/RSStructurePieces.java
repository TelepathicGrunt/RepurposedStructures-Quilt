package com.telepathicgrunt.repurposedstructures.modinit;

import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.FortressJunglePieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.MansionPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSMineshaftPieces;
import com.telepathicgrunt.repurposedstructures.world.structures.pieces.RSStrongholdPieces;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;


public class RSStructurePieces {
    public static StructurePieceType MINESHAFT_ROOM_RS = RSMineshaftPieces.Room::new;
    public static StructurePieceType MINESHAFT_CORRIDOR_RS = RSMineshaftPieces.Corridor::new;
    public static StructurePieceType MINESHAFT_CROSSING_RS = RSMineshaftPieces.Cross::new;
    public static StructurePieceType MINESHAFT_STAIRS_RS = RSMineshaftPieces.Stairs::new;
    public static StructurePieceType JUNGLE_FORTRESS_START = FortressJunglePieces.Start::new;
    public static StructurePieceType JUNGLE_FORTRESS_END = FortressJunglePieces.End::new;
    public static StructurePieceType JUNGLE_FORTRESS_ENTRANCE = FortressJunglePieces.Entrance::new;
    public static StructurePieceType JUNGLE_FORTRESS_STRAIGHT = FortressJunglePieces.Straight::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_5 = FortressJunglePieces.Corridor5::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_4 = FortressJunglePieces.Corridor4::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_3 = FortressJunglePieces.Corridor3::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_2 = FortressJunglePieces.Corridor2::new;
    public static StructurePieceType JUNGLE_FORTRESS_CORRIDOR_1 = FortressJunglePieces.Corridor::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_3 = FortressJunglePieces.Crossing3::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_2 = FortressJunglePieces.Crossing2::new;
    public static StructurePieceType JUNGLE_FORTRESS_CROSSING_1 = FortressJunglePieces.Crossing::new;
    public static StructurePieceType JUNGLE_FORTRESS_MUSHROOM_ROOM = FortressJunglePieces.MushroomRoom::new;
    public static StructurePieceType JUNGLE_FORTRESS_THRONE = FortressJunglePieces.Throne::new;
    public static StructurePieceType JUNGLE_FORTRESS_STAIRS = FortressJunglePieces.Stairs::new;
    public static StructurePieceType STRONGHOLD_CHEST_CORRIDOR = RSStrongholdPieces.ChestCorridor::new;
    public static StructurePieceType STRONGHOLD_CORRIDOR = RSStrongholdPieces.Corridor::new;
    public static StructurePieceType STRONGHOLD_PORTAL_ROOM = RSStrongholdPieces.PortalRoom::new;
    public static StructurePieceType STRONGHOLD_LIBRARY = RSStrongholdPieces.Library::new;
    public static StructurePieceType STRONGHOLD_PRISON =  RSStrongholdPieces.Prison::new;
    public static StructurePieceType STRONGHOLD_ROOM_CROSSING = RSStrongholdPieces.RoomCrossing::new;
    public static StructurePieceType STRONGHOLD_CROSSING = RSStrongholdPieces.Crossing::new;
    public static StructurePieceType STRONGHOLD_LEFT_TURN = RSStrongholdPieces.LeftTurn::new;
    public static StructurePieceType STRONGHOLD_RIGHT_TURN = RSStrongholdPieces.RightTurn::new;
    public static StructurePieceType STRONGHOLD_STRAIGHT = RSStrongholdPieces.Straight::new;
    public static StructurePieceType STRONGHOLD_STAIRS = RSStrongholdPieces.Stairs::new;
    public static StructurePieceType STRONGHOLD_STAIRS_STRAIGHT = RSStrongholdPieces.StairsStraight::new;
    public static StructurePieceType STRONGHOLD_ENTRANCE_STAIRS = RSStrongholdPieces.EntranceStairs::new;
    public static StructurePieceType MANSION_PIECE = MansionPieces.Piece::new;

    public static void registerStructurePieces() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "mineshaft_room_rs"), MINESHAFT_ROOM_RS);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "mineshaft_corridor_rs"), MINESHAFT_CORRIDOR_RS);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "mineshaft_crossing_rs"), MINESHAFT_CROSSING_RS);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "mineshaft_stairs_rs"), MINESHAFT_STAIRS_RS);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_start"), JUNGLE_FORTRESS_START);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_entrance"), JUNGLE_FORTRESS_ENTRANCE);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_end"), JUNGLE_FORTRESS_END);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_straight"), JUNGLE_FORTRESS_STRAIGHT);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_5"), JUNGLE_FORTRESS_CORRIDOR_5);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_4"), JUNGLE_FORTRESS_CORRIDOR_4);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_3"), JUNGLE_FORTRESS_CORRIDOR_3);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_2"), JUNGLE_FORTRESS_CORRIDOR_2);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_corridor_1"), JUNGLE_FORTRESS_CORRIDOR_1);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_crossing_3"), JUNGLE_FORTRESS_CROSSING_3);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_crossing_2"), JUNGLE_FORTRESS_CROSSING_2);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_crossing_1"), JUNGLE_FORTRESS_CROSSING_1);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_mushroom_room"), JUNGLE_FORTRESS_MUSHROOM_ROOM);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_throne"), JUNGLE_FORTRESS_THRONE);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "jungle_fortress_stairs"), JUNGLE_FORTRESS_STAIRS);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_chest_corridor"), STRONGHOLD_CHEST_CORRIDOR);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_corridor"), STRONGHOLD_CORRIDOR);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_portal_room"), STRONGHOLD_PORTAL_ROOM);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_library"), STRONGHOLD_LIBRARY);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_prison"), STRONGHOLD_PRISON);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_room_crossing"), STRONGHOLD_ROOM_CROSSING);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_crossing"), STRONGHOLD_CROSSING);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_right_turn"), STRONGHOLD_RIGHT_TURN);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_left_turn"), STRONGHOLD_LEFT_TURN);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_straight"), STRONGHOLD_STRAIGHT);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_stairs"), STRONGHOLD_STAIRS);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_stairs_straight"), STRONGHOLD_STAIRS_STRAIGHT);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "stronghold_entrance_stairs"), STRONGHOLD_ENTRANCE_STAIRS);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(RepurposedStructures.MODID, "mansion_piece"), MANSION_PIECE);
    }

    // Initialize piece counts
//            this.pieceCounts = new HashMap<>();
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "rooms/grand_library"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.grandLibraryMaxCount);
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "rooms/library_md"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.smallLibraryMaxCount);
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "rooms/prison_lg"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.prisonMaxCount);
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "rooms/cmd_acarii"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.cmdAcariiMaxCount);
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "rooms/cmd_yung"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.cmdYungMaxCount);
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "rooms/treasure_room_lg"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.treasureRoomMaxCount);
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "portal_rooms/portal_room"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.portalRoomMaxCount);
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "rooms/armoury_md"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.armouryLargeRoomMaxCount);
//            this.pieceCounts.put(new Identifier(RepurposedStructures.MODID, "rooms/armoury_sm"), RepurposedStructures.CONFIG.RepurposedStructures.pieceSettings.armourySmallRoomMaxCount);
//            this.maxY = RepurposedStructures.CONFIG.RepurposedStructures.general.strongholdMaxY;
}
