package me.treexhd.mc.instantbreakinflight.instantbreakblockinflight;

import com.bekvon.bukkit.residence.Residence;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import com.Zrips.CMI.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import static me.treexhd.mc.instantbreakinflight.instantbreakblockinflight.InstantBreakBlockinFlightUtil.isFly;
import static org.bukkit.Bukkit.getServer;

public class InstantBreakBlockinFlightListener implements Listener {

    private Residence plugin;

    @EventHandler
    public void onBreakBlock(BlockDamageEvent event){
        Player p = event.getPlayer();
        Block  b = event.getBlock();
        if(InstantBreakBlockinFlightUtil.isFly(p) & InstantBreakBlockinFlightUtil.blockinRes(p,b) & InstantBreakBlockinFlightUtil.blockHeight(b.getLocation()) > 60 & p.getAllowFlight()){
            

            event.setInstaBreak(true);
        }
    }

    @EventHandler
    public void onFly(PlayerMoveEvent event){ //Disable Flying in Out of Residence
        Player p = event.getPlayer();
        if(InstantBreakBlockinFlightUtil.playercanFly(p) & p.getGameMode() == GameMode.SURVIVAL){
            p.setAllowFlight(true);

        }else if(!InstantBreakBlockinFlightUtil.playercanFly(p) & !InstantBreakBlockinFlightUtil.isPlayeratHeightestBlock(p) & p.getGameMode() == GameMode.SURVIVAL){
            p.setAllowFlight(false);
            p.setFlying(false);
//teleport
//            p.teleport(InstantBreakBlockinFlightUtil.getHighestBock(p,p.getWorld(),p.getLocation().getBlockX(),p.getLocation().getBlockZ()));
        }
        else if(!InstantBreakBlockinFlightUtil.playercanFly(p) & p.getGameMode() == GameMode.SURVIVAL){


        }



    }

//TODO
//    @EventHandler
//    public void onDamage(EntityDamageEvent event){
//        if(event.getEntity() instanceof Player & event.getCause() == EntityDamageEvent.DamageCause.FALL){
//            if(InstantBreakBlockinFlightUtil.playerinRes((Player) event.getEntity())){
//                event.setCancelled(true);
//            }
//        }
//    }
}
