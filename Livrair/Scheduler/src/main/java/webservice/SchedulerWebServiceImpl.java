package webservice;

import entities.DailyPlanning;
import interfaces.PlanningInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.LocalDateTime;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/scheduler")
@Stateless(name = "SchedulerWS")
public class SchedulerWebServiceImpl implements SchedulerWebService {

    @EJB(name = "stateless-scheduler")
    private PlanningInterface planning;

    @Override
    public boolean planDelivery(String id, int jour, int mois, int annee, int heure, int minute) {
        try {
            planning.planDelivery(id, LocalDateTime.of(annee, mois, jour, heure, minute)).get();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // TODO return excpetion spécifique ?
        }
    }

    @Override
    public DailyPlanning getPlanning() {
        try {
            return planning.getPlanning();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
