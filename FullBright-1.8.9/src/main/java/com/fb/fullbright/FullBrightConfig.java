package com.fb.fullbright;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

public class FullBrightConfig {
    private static Configuration config;
    private static int gamma = 100;
    private static boolean isInitialized = false;

    public static void init() {
        if (FullBrightConfig.isInitialized == true) return;
        FullBrightConfig.isInitialized = true;

        final File configFile = new File(Loader.instance().getConfigDir(), "fullbright.cfg");
        FullBrightConfig.config = new Configuration(configFile);
        
        load();
    }

    public static void load() {
        FullBrightConfig.config.load();
        FullBrightConfig.gamma = FullBrightConfig.config.getInt(
            "gamma",
            "fullbright",
            100,
            0,
            100,
            "fullbright prop"
        );

        FullBrightConfig.config.save();
    }

    public static void save() {
        Property property = config.get("fullbright", "gamma", gamma);
        property.setValue(FullBrightConfig.gamma);
        
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static int getGamma() {
        return FullBrightConfig.gamma;
    }

    public static void setGamma(int value) {
        FullBrightConfig.gamma = value;
        save();
    }
}
