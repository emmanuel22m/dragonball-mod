package com.dbzmod.client;

import com.dbzmod.DragonBallMod;
import com.dbzmod.entity.KiBlastEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class KiBlastRenderer extends EntityRenderer<KiBlastEntity> {
    private static final Identifier TEXTURE =
            Identifier.of(DragonBallMod.MOD_ID, "textures/entity/ki_blast.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityTranslucent(TEXTURE);

    public KiBlastRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(KiBlastEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.scale(1.6f, 1.6f, 1.6f);
        matrices.multiply(this.dispatcher.getRotation());
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        VertexConsumer consumer = vertexConsumers.getBuffer(LAYER);
        produceVertex(matrix4f, entry, consumer, -1.0f, -1.0f, 0.0f, 1.0f);
        produceVertex(matrix4f, entry, consumer, 1.0f, -1.0f, 1.0f, 1.0f);
        produceVertex(matrix4f, entry, consumer, 1.0f, 1.0f, 1.0f, 0.0f);
        produceVertex(matrix4f, entry, consumer, -1.0f, 1.0f, 0.0f, 0.0f);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    private static void produceVertex(Matrix4f matrix, MatrixStack.Entry entry, VertexConsumer consumer,
                                      float x, float y, float u, float v) {
        consumer.vertex(matrix, x, y, 0.0f)
                .color(255, 255, 255, 255)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(15728880) // brillo maximo, el ki "emite luz"
                .normal(entry, 0.0f, 1.0f, 0.0f);
    }

    @Override
    public Identifier getTexture(KiBlastEntity entity) {
        return TEXTURE;
    }
}
