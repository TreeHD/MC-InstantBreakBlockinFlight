package me.treexhd.mc.instantbreakinflight.instantbreakblockinflight;

import com.bekvon.bukkit.residence.Residence;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class InstantBreakBlockinFlightListener implements Listener {

    private Residence plugin;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBreakBlock(BlockDamageEvent event) {
        Player p = event.getPlayer();
        Block b = event.getBlock();
        if (InstantBreakBlockinFlightUtil.isFly(p) & InstantBreakBlockinFlightUtil.blockinRes(p, b) & InstantBreakBlockinFlightUtil.blockHeight(b.getLocation()) > 60 & p.getAllowFlight()) {


            event.setInstaBreak(true);
        }
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onFlying(PlayerMoveEvent event) { //Disable Flying in Out of Residence
        Player p = event.getPlayer();
        if (!InstantBreakBlockinFlightUtil.playercanFly(p) & p.getGameMode() == GameMode.SURVIVAL) {
            p.setAllowFlight(false);
            p.setFlying(false);
        }
//        if(InstantBreakBlockinFlightUtil.playercanFly(p) & p.getGameMode() == GameMode.SURVIVAL){
//            p.setAllowFlight(true);
//
//        }else if(!InstantBreakBlockinFlightUtil.playercanFly(p) & !InstantBreakBlockinFlightUtil.isPlayeratHeightestBlock(p) & p.getGameMode() == GameMode.SURVIVAL){
//            p.setAllowFlight(false);
//            p.setFlying(false);
////teleport
//            p.teleport(InstantBreakBlockinFlightUtil.getHighestBock(p,p.getWorld(),p.getLocation().getBlockX(),p.getLocation().getBlockZ()));
//        }
//        else if(!InstantBreakBlockinFlightUtil.playercanFly(p) & p.getGameMode() == GameMode.SURVIVAL){
//
//
//        }


    }

//    @EventHandler
//    public void onDamage(EntityDamageEvent event){
//        if(event.getEntity() instanceof Player & event.getCause() == EntityDamageEvent.DamageCause.FALL){
//            if(InstantBreakBlockinFlightUtil.playerinRes((Player) event.getEntity())){
//                event.setCancelled(true);
//            }
//        }
//    }

//    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
//    public void onPlayerChangeRes(ResidenceChangedEvent event){
//        Player p = event.getPlayer();
//
//            if(InstantBreakBlockinFlightUtil.playercanFly(p) & p.getGameMode() == GameMode.SURVIVAL){
//                p.setAllowFlight(true);
//
//            }//else if(!InstantBreakBlockinFlightUtil.playercanFly(p) & !InstantBreakBlockinFlightUtil.isPlayeratHeightestBlock(p) & p.getGameMode() == GameMode.SURVIVAL){
//
//
////                p.teleport(InstantBreakBlockinFlightUtil.getHighestBock(p,p.getWorld(),p.getLocation().getBlockX(),p.getLocation().getBlockZ()));
//                //p.setAllowFlight(false);
//              //  p.setFlying(false);
//            //}
//            else if(!InstantBreakBlockinFlightUtil.playercanFly(p) & p.getGameMode() == GameMode.SURVIVAL){
//                p.setAllowFlight(false);
//                p.setFlying(false);
//            }
//}





}
