package reyd.Listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import reyd.CPSCounterMain;

public class CpsLimiter  implements Listener {

    private final CPSCounterMain plugin;

    public CpsLimiter(CPSCounterMain plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("unused")
    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Config conf = plugin.getConfig();
            int maxCps = conf.getInt("maxCps.cps");
            Player p = (Player) e.getDamager();
            if(CPSListener.gCPS(p) > maxCps){
                e.setCancelled();

                p.sendMessage(TextFormat.colorize('&' , conf.getString("maxCps.message")
                        .replace("<maxCps>" , String.valueOf(maxCps))
                        .replace("<cps>" , String.valueOf(CPSListener.gCPS(p)))

                ));
            }
        }
    }
}