package pl.podatek;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RekrutacjaCommand implements CommandExecutor {

    private final task2 plugin;

    public RekrutacjaCommand(task2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Ta komenda moze byc wykonywana tylko przez graczy!");
            return true;
        }
        if(!sender.hasPermission("rekrutacja.use")) {
            sender.sendMessage(ChatColor.RED + "Nie masz uprawnien do uzycia tej komendy!");
            return true;
        }

        Player player = (Player) sender;
        new BukkitRunnable() {
            int countdown = 5;

            @Override
            public void run() {
                if (countdown > 0) {
                    player.sendTitle(Integer.toString(countdown), "", 10, 20, 10);
                    countdown--;
                } else {
                    player.sendTitle("START", "", 10, 20, 10);
                    Bukkit.getOnlinePlayers().forEach(p -> {
                        ItemStack[] items = p.getInventory().getContents();
                        for (int i = 0; i < items.length; i++) {
                            if (items[i] != null && items[i].getType() == Material.DIAMOND) {
                                items[i].setType(Material.STONE);
                            }
                        }
                    });
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);

        return true;
    }
}