package com.ha.hidearmor;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

public class HideArmorConfig {
    private static Configuration config;
    private static boolean toggle = false;
    private static boolean isInitialized = false;

    private static final String name = "toggle";
    private static final String category = "hidearmor";

    public static void init() {
        if (HideArmorConfig.isInitialized == true) {
            return;
        }

        HideArmorConfig.isInitialized = true;

        final File configFile = new File(Loader.instance().getConfigDir(), category + ".cfg");
        HideArmorConfig.config = new Configuration(configFile);
        
        load();
    }

    public static void load() {
        HideArmorConfig.config.load();
        HideArmorConfig.toggle = HideArmorConfig.config.getBoolean(
            name,
            category,
            false,
            "hidearmor prop"
        );

        HideArmorConfig.config.save();
    }

    public static void save() {
        Property property = config.get(category, name, toggle);
        property.setValue(HideArmorConfig.toggle);
        
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static boolean getToggle() {
        return HideArmorConfig.toggle;
    }

    public static void setToggle(boolean value) {
        HideArmorConfig.toggle = value;
        save();
    }
}
