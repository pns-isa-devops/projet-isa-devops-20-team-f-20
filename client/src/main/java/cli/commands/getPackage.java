package cli.commands;

import api.LivrairPublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Package;

import java.util.List;

public class getPackage extends Command<LivrairPublicAPI> {

	private String packageId;

	@Override
	public String identifier() { return "package"; }

	@Override
	public void load(List<String> args) {
		packageId = args.get(0);
	}

	@Override
	public void execute() {
		Package pack = shell.system.ccs.getPackageById(packageId);
		String s = "Package n°" + pack.getId() + " :\n";
		s += "\tReceived from : " + pack.getSupplier() + "\n";
		s += "\tDeliver to : " + pack.getCustomerName() + "\n";
		s += "\tAt " + pack.getAddress() + "\n";
		s += "\t\tSTATUS : " + pack.getPackageStatus().value() + "\n";
		System.out.println(s);
	}

	@Override
	public String describe() {
		return "Get a package by ID (package PACKAGE_ID)";
	}

}
