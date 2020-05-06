package webservice;

import entities.DailyPlanning;
import interfaces.PlanningInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public DailyPlanning getPlanning(String date) {
        try {
            return planning.getPlanning(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DailyPlanning> getPlanning(String from, String to) {
        try {
            return planning.getPlanning(LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
