package cli.commands;

import api.LivrairPublicAPI;
import cli.framework.Command;

import java.util.List;

public class ListPackageContents extends Command<LivrairPublicAPI> {

	@Override
	public String identifier() { return "hello"; }

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
		String msg = shell.system.ccs.getPackageById("3");
		System.out.println("Voici la reponse :");
		System.out.println(msg);
	}

	@Override
	public String describe() {
		return "List all available packages";
	}

}
