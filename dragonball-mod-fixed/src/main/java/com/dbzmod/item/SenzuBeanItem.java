package com.dbzmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;


public class SenzuBeanItem extends Item {

    public SenzuBeanItem(Settings settings) {
        // nutrition=20 (barras completas), saturacion alta, comestible siempre
        super(settings.food(new FoodComponent.Builder()
                .nutrition(20).saturationModifier(1.0f).alwaysEdible().build()));
    }
}
