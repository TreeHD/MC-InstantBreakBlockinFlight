package me.treexhd.mc.instantbreakinflight.instantbreakblockinflight;

import me.ryanhamshire.GriefPrevention.*;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class InstantBreakBlockinFlightUtil {


    public static boolean isFly(Player player){
        Player p = player.getPlayer();
        if(p.isFlying() & p.getGameMode() == GameMode.SURVIVAL){
            return true;
        }
        return false;
    }

    public static int blockHeight(Location location){
        return location.getBlockY();
    }

    public static boolean blockinClaim(Player p,Block b){
        Plugin ClaimPlug = getServer().getPluginManager().getPlugin("GriefPrevention");

        if (ClaimPlug != null) { //Check GriefPrevention is usable
            boolean ignoreHeight = true;
            Location loc = b.getLocation();

            Claim claim= GriefPrevention.instance.dataStore.getClaimAt(b.getLocation(), true, null);
            if(claim!=null){
                return true;
            }

        }
        return false;
    }

    public static boolean playerinClaim(Player p){
        Plugin ClaimPlug = getServer().getPluginManager().getPlugin("GriefPrevention");

        if (ClaimPlug != null) { //Check GriefPrevention is usable
            boolean ignoreHeight = true;
            Location loc = p.getLocation();

            Claim claim= GriefPrevention.instance.dataStore.getClaimAt(p.getLocation(), true, null);
            if(claim!=null){
                return true;
            }

        }
        return false;
    }

    public static boolean playercanFly(Player p){
        if(InstantBreakBlockinFlightUtil.playerinClaim(p)){
            Location loc = p.getLocation();
            Claim claim= GriefPrevention.instance.dataStore.getClaimAt(loc, true, null);

            if(claim!=null){
                String result = claim.allowAccess(p);
                if(result==null){
                    return true;
                }


            }

        }
        return false;
    }


    //TODO Search the "highest block" from player Y coordinate.
    public static Location getHighestBock(Player player,World world, int x, int z){
        int i = player.getLocation().getBlockY();


        while(i>0){
            if(new Location(world, x, i, z).getBlock().getType()!=Material.AIR)
                return new Location(world, x, i, z).add(0,1,0);
            i--;
        }
        return new Location(world, x, 1, z);
    }

    public static boolean isPlayeratHeightestBlock(Player player){
        int i = player.getLocation().getBlockY();
        Location loc = getHighestBock(player,player.getWorld(),player.getLocation().getBlockX(),player.getLocation().getBlockZ());

        if(i>loc.getBlockY()){
            return false;
        }
        return true;
    }


}
