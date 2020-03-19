package cli.commands;

import api.LivrairPublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Package;

import java.util.List;


public class createDelivery extends Command<LivrairPublicAPI> {

    private String packageId;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int min;

    @Override
    public String identifier() { return "create_delivery"; }

    @Override
    public void load(List<String> args) {
        packageId = args.get(0);
        day = Integer.parseInt(args.get(1));
        month = Integer.parseInt(args.get(2));
        year = Integer.parseInt(args.get(3));
        hour = Integer.parseInt(args.get(4));
        min = Integer.parseInt(args.get(5));
    }

    @Override
    public void execute() {
        boolean created = shell.system.ccs.createDelivery(packageId, day, month, year, hour, min);
        if (created) {
            System.out.println("Created !");
        } else {
            System.out.println("Not created..");
        }
    }

    @Override
    public String describe() {
        return "create a delivery from package and date (create_delivery PACKAGE_ID DAY MONTH YEAR HOUR MIN)";
    }

}

