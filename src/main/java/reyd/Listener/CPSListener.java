package reyd.Listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.LevelSoundEventPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SplittableRandom;

public class CPSListener implements Listener {

    private final HashMap<String, List<Long>> cps = new HashMap<>();

    @EventHandler
    public void onPacket(DataPacketReceiveEvent event) {
        if (!(event.getPacket() instanceof LevelSoundEventPacket)) return;

        LevelSoundEventPacket packet = (LevelSoundEventPacket) event.getPacket();

        if (packet.sound != LevelSoundEventPacket.SOUND_ATTACK && packet.sound != LevelSoundEventPacket.SOUND_ATTACK_NODAMAGE &&
                packet.sound != LevelSoundEventPacket.SOUND_ATTACK_STRONG) return;

        List<Long> cpsList = cps.get(event.getPlayer().getName());

        if (cpsList == null) {
            cpsList = new ArrayList<>();
        }

        cpsList.add(System.currentTimeMillis());
        cps.remove(event.getPlayer().getName());
        cps.put(event.getPlayer().getName(), cpsList);
        event.getPlayer().sendActionBar(String.valueOf(gCPS(event.getPlayer())));

    }

    public int gCPS(Player player) {
        List<Long> list = cps.get(player.getName());

        if (list == null) return 0;

        list.removeIf(l -> l < System.currentTimeMillis() - 1000);

        return list.size();
    }

    @EventHandler
    public void onQuitListener(PlayerQuitEvent event){

        cps.remove(event.getPlayer().getName());

    }

}
