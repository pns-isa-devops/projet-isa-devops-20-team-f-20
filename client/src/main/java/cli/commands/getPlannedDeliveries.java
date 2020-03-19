package cli.commands;

import api.LivrairPublicAPI;
import cli.framework.Command;
import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Delivery;
import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Package;

import java.util.List;

public class getPlannedDeliveries extends Command<LivrairPublicAPI> {

    @Override
    public String identifier() { return "deliveries"; }

    @Override
    public void execute() {
        List<Delivery> deliveries = shell.system.ccs.getPlannedDeliveries();
        System.out.println(deliveries);
        String s;
        if (deliveries != null){
            s = "All planned deliveries (" + deliveries.size() + ") :\n\n";
            for (Delivery d : deliveries) {
                s = "Delivery n°" + d.getId() + " :\n";
                s += "\tSchedule for : " + d.getDeliveryDate() + "\n";
                s += "\tAssign to drone : " + d.getDrone() + "\n";
                s += "\tAsssign to package : " + d.getAPackage() + "\n";
//                s += "\t\tSTATUS : " + d.getStatus().value() + "\n\n";
            }
        }else  {
            s = "No planned deliveries...";
        }
        System.out.println(s);
    }

    @Override
    public String describe() {
        return "Show all planned deliveries";
    }

}
