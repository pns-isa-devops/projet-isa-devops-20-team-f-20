package cli.commands;

import api.LivrairPublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Delivery;
import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Package;

import java.util.List;

public class getAllPackages extends Command<LivrairPublicAPI> {

    @Override
    public String identifier() { return "packages"; }

    @Override
    public void execute() {
        List<Package> packages = shell.system.ccs.getAllPackages();
        String s;
        if (packages != null){
            s = "All packages (" + packages.size() + ") :\n\n";
            for (Package pack : packages) {
                s += "Package n°" + pack.getId() + " :\n";
                s += "\tReceived from : " + pack.getSupplier() + "\n";
                s += "\tDeliver to : " + pack.getCustomerName() + "\n";
                s += "\tAt " + pack.getAddress() + "\n";
                s += "\t\tSTATUS : " + pack.getPackageStatus().value() + "\n\n";
            }
        }else  {
            s = "No packages...";
        }
        System.out.println(s);
    }

    @Override
    public String describe() {
        return "Show all packages";
    }

}

