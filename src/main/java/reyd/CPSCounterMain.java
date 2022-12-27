package reyd;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import reyd.Listener.CPSListener;
import reyd.Listener.CpsLimiter;


public class CPSCounterMain extends PluginBase {

    public static boolean cpsActionBar;
    public static int maxCps;
    public static String message;
    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        cpsActionBar = getConfig().getBoolean("cpsActionBar");
        maxCps = getConfig().getInt("maxCps.cps");
        message = getConfig().getString("maxCps.message");

        registerListener();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void registerListener(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new CPSListener(this), this);
        pluginManager.registerEvents(new CpsLimiter(this) , this);
    }

}
