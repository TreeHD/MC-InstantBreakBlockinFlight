package me.treexhd.mc.instantbreakinflight.instantbreakblockinflight;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.*;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.ResidencePermissions;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
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

    public static boolean blockinRes(Player p,Block b){
        Plugin resPlug = getServer().getPluginManager().getPlugin("Residence");
        if (resPlug != null) { //Check Residence is useable
            Location loc = b.getLocation();
            ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(loc);
            if(res!=null){
                ResidencePermissions perms = res.getPermissions();
                boolean hasPermission = perms.playerHas(p.getName(), "build", true);

                if(hasPermission){
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean playerinRes(Player p){
        Plugin resPlug = getServer().getPluginManager().getPlugin("Residence");
        if (resPlug != null) { //Check Residence is useable
            Location loc = p.getLocation();
            ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(loc);
            if(res!=null){
                return true;

            }
        }
        return false;
    }

    public static boolean playercanFly(Player p){
        if(InstantBreakBlockinFlightUtil.playerinRes(p)){
            Location loc = p.getLocation();
            ClaimedResidence res = Residence.getInstance().getResidenceManager().getByLoc(loc);
            ResidencePermissions perms = res.getPermissions();
            boolean hasPermission = perms.playerHas(p.getName(), "fly", true);
            if(hasPermission){
                return true;
            }
        }
        return false;
    }

    public static Location getHighestBock(Player player,World world, int x, int z){
        int i = player.getLocation().getBlockY();


        while(i>0){
            if(new Location(world, x, i, z).getBlock().getType()!=Material.AIR)
                return new Location(world, x, i, z).add(0,1,0);
            i--;
        }
        return new Location(world, x, 1, z);
    }

}
