package me.CrimsonCrow98.HolograpicSimple;

import org.spongepowered.api.plugin.Plugin;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;

@Plugin(id = "holosimple", name = "Holographic Simple", version = "1.0")
public class HolographicSimple {
	
	//summon ArmorStand x y z {Invisible:1,direction:[0.0,0.0,0.0],ExplosionPower:­0,CustomName:"Put text here",CustomNameVisible:true,NoGravity:1}
	//minecraft:kill @e[type=ArmorStand,name=(customName),c=1]
	
	@Listener
	public void onGameInitializationEvent(GameInitializationEvent event) {
		CommandSpec commandHolo = CommandSpec.builder()
				.permission("h.use")
        		.arguments(GenericArguments.onlyOne(GenericArguments.doubleNum(Text.of("y"))), GenericArguments.remainingJoinedStrings(Text.of("text")))
                .executor(new CommandExecutor() {
                    @Override
                    public CommandResult execute(CommandSource commandSource, CommandContext args) throws CommandException {
                    	if(commandSource instanceof Player){
                    		Player p = (Player) commandSource;
                    		double x = p.getLocation().getX();
                    		double y = args.<Double>getOne("y").get();
                    		double z = p.getLocation().getZ();
                        	String text = args.<String>getOne("text").get();
                        	Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "summon ArmorStand " + x + " " + y + " " + z + " {Invisible:1,direction:[0.0,0.0,0.0],ExplosionPower:­0,CustomName:\"" + text.replace("&", "§") + "\",CustomNameVisible:true,NoGravity:1,DisabledSlots:2039552,Marker:1}");
                    	}
                        return CommandResult.success();
                    }
                })
                .build();
        
        Sponge.getCommandManager().register(this, commandHolo, "hologram", "h");
        
        CommandSpec commandHoloDel = CommandSpec.builder()
        		.permission("delh.use")
        		.arguments(GenericArguments.remainingJoinedStrings(Text.of("text")))
                .executor(new CommandExecutor() {
                    @Override
                    public CommandResult execute(CommandSource commandSource, CommandContext args) throws CommandException {
                    	if(commandSource instanceof Player){
                    		
                        	String text = args.<String>getOne("text").get();
                        	Sponge.getCommandManager().process(commandSource, "minecraft:kill @e[type=ArmorStand,CustomName=" + text + ",c=1]");
                    	}
                        return CommandResult.success();
                    }
                })
                .build();
        
        Sponge.getCommandManager().register(this, commandHoloDel, "delhologram", "delh");
		
	       
	}
}
//Test Punnisher1661 test123