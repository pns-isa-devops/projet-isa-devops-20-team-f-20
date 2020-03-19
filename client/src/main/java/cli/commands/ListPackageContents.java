package cli.commands;

import api.LivrairPublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Package;

import java.util.List;

public class ListPackageContents extends Command<LivrairPublicAPI> {

	@Override
	public String identifier() { return "packages"; }

	/*
	@Override
	public void execute() {
		List<Cookies> recipes = shell.system.ccs.listAllRecipes();
		if (recipes.isEmpty()) {
			System.out.println("  No recipes available");
		} else {
			for(Cookies c: recipes) {
			  System.out.println("  " + c.name());
			}
		}
	}
	*/

	@Override
	public void execute() {
		System.out.println("EXECUTION");
		Package pack = shell.system.ccs.getPackageById("2");
		System.out.println("Voici la reponse :");
		System.out.println(pack);
	}

	@Override
	public String describe() {
		return "List all available packages";
	}

}
