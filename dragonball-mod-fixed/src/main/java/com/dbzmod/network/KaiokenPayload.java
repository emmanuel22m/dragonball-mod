package com.dbzmod.network;

import com.dbzmod.DragonBallMod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record KaiokenPayload() implements CustomPayload {
    public static final KaiokenPayload INSTANCE = new KaiokenPayload();
    public static final CustomPayload.Id<KaiokenPayload> ID =
            new CustomPayload.Id<>(Identifier.of(DragonBallMod.MOD_ID, "kaioken"));
    public static final PacketCodec<PacketByteBuf, KaiokenPayload> CODEC =
            PacketCodec.unit(INSTANCE);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
