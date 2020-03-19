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
		String s = "Package n°" + pack.getId() + " :\n";
		s += "\tReceived from : " + pack.getSupplier() + "\n";
		s += "\tDeliver to : " + pack.getCustomerName() + "\n";
		s += "\tAt " + pack.getAddress() + "\n";
		s += "\t\tSTATUS : " + pack.getPackageStatus().value() + "\n";
		System.out.println(s);
	}

	@Override
	public String describe() {
		return "List all available packages";
	}

}
