package me.treexhd.mc.instantbreakinflight.instantbreakblockinflight;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.events.ClaimDeletedEvent;
import me.ryanhamshire.GriefPrevention.events.ClaimModifiedEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InstantBreakBlockinFlightListener implements Listener {


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onBreakBlock(BlockDamageEvent event) {
        Player p = event.getPlayer();
        Block b = event.getBlock();
        if (InstantBreakBlockinFlightUtil.isFly(p) & InstantBreakBlockinFlightUtil.blockinClaim(p, b) & InstantBreakBlockinFlightUtil.blockHeight(b.getLocation()) > 60 & p.getAllowFlight()) {


            event.setInstaBreak(true);
        }
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) { //Disable Flying in Out of Claim
        Player p = event.getPlayer();
        Location nowLoc = p.getLocation();

        Block block = nowLoc.getBlock();

        while (block.getY() > 2 && !block.getType().isSolid() && block.getType() != Material.WATER) { //get nearest block under Player
            block = block.getRelative(BlockFace.DOWN);
        }

        Location from = event.getFrom();
        Location to = event.getTo();

        if(InstantBreakBlockinFlightUtil.playercanFly(p)){
            p.setAllowFlight(true);
            if(p.isFlying()){
                p.addPotionEffect((new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 1)));
            }
        }

        if(InstantBreakBlockinFlightUtil.isEnterClaim(from,to)){
            p.setAllowFlight(true);
            if(p.isFlying()){
                p.addPotionEffect((new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 1)));
            }
        }else if(InstantBreakBlockinFlightUtil.isLeaveClaim(from,to)){
            p.setAllowFlight(false);
            p.setFlying(false);
            if (nowLoc.getY() - block.getY() >= 4) {
                p.sendMessage("離開領地 關閉飛行 已將您傳送至安全的地方了");
                p.teleport(block.getLocation());
            }

        }



    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onTeleport(PlayerTeleportEvent event){
        Player p = event.getPlayer();
        Location nowLoc = p.getLocation();

        Block block = nowLoc.getBlock();

        while (block.getY() > 2 && !block.getType().isSolid() && block.getType() != Material.WATER) { //get nearest block under Player
            block = block.getRelative(BlockFace.DOWN);
        }

        Location from = event.getFrom();
        Location to = event.getTo();

        if(InstantBreakBlockinFlightUtil.isEnterClaim(from,to)){
            p.setAllowFlight(true);
            if(p.isFlying()){
                p.addPotionEffect((new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 1)));
            }
        }else if(InstantBreakBlockinFlightUtil.isLeaveClaim(from,to)){
            p.setAllowFlight(false);
            p.setFlying(false);
        }
    }

    @EventHandler
    public void onModifyClaim(ClaimModifiedEvent event){
        Claim claim = event.getClaim();
        World world = claim.getGreaterBoundaryCorner().getWorld();

        for (Player player : world.getPlayers()) { //check who in Claim
            if (claim.contains(player.getLocation(), false, true)) {
                player.setAllowFlight(false);
                player.setFlying(false);
            }
        }
    }

    @EventHandler
    public void onClaimDeleted(ClaimDeletedEvent event){
        Claim claim = event.getClaim();
        World world = claim.getGreaterBoundaryCorner().getWorld();

        for (Player player : world.getPlayers()) { //check who in Claim
            if (claim.contains(player.getLocation(), false, true)) {
                player.setAllowFlight(false);
                player.setFlying(false);
            }
        }
    }




//    @EventHandler
//    public void onDamage(EntityDamageEvent event){
//        if(event.getEntity() instanceof Player & event.getCause() == EntityDamageEvent.DamageCause.FALL) return;
//        if(InstantBreakBlockinFlightUtil.playerinClaim((Player) event.getEntity()) & InstantBreakBlockinFlightUtil.isLeaveClaim()){
//              event.setCancelled(true);
//        }
//
//    }

}
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





