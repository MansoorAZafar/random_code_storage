package com.fb.fullbright.mixin;

import com.fb.fullbright.FullBrightConfig;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameSettings.class)
public class FullBrightCore {
    @Shadow 
    public float gammaSetting;

    @Inject(method = "loadOptions", at = @At("RETURN"))
    private void onLoadOptionsReturn(CallbackInfo ci) {
        System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
        System.out.println("THE FULLBRIGHT MIXIN IS REALLLLL!");
        System.out.println("Gamma before: " + this.gammaSetting);
        System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");

        FullBrightConfig.init();
        System.out.println("Config gamma: " + FullBrightConfig.getGamma());
        
        this.gammaSetting = FullBrightConfig.getGamma();
    }    
}
