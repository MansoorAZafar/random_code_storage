package com.ha.hidearmor.mixin;

import com.ha.hidearmor.HideArmorConfig;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameSettings.class)
public class HideArmorCore {
    @Inject(method = "loadOptions", at = @At("RETURN"))
    private void onLoadOptionsReturn(CallbackInfo ci) {
        System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        System.out.println("THE MIXIN IS REALLLLL!");
        System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");

        HideArmorConfig.init();
        System.out.println("Config gamma: " + HideArmorConfig.getToggle());
    }    
}
