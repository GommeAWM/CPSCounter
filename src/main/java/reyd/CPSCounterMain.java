package reyd;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import reyd.Listener.CPSListener;

public class CPSCounterMain extends PluginBase {

    @Override
    public void onEnable() {
        registerListener();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void registerListener(){
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new CPSListener(), this);
    }

}
