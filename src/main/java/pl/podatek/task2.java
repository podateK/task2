package pl.podatek;

import org.bukkit.plugin.java.JavaPlugin;

public final class task2 extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("rekrutacja").setExecutor(new RekrutacjaCommand(this));
    }

    @Override
    public void onDisable() {
        
    }
}
