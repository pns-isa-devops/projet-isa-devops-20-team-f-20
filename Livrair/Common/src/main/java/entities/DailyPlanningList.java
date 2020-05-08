package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="DailyPlanningLists")
public class DailyPlanningList implements Serializable {

    private List<DailyPlanning> dailyPlannings;
    private int id;

    public DailyPlanningList(){

    }

    public DailyPlanningList(List<DailyPlanning> dailys) {
        dailyPlannings = dailys;
    }

    public DailyPlanning getPlanningOfDate(LocalDate date) {
        DailyPlanning planning = null;
        if(dailyPlannings == null)
            dailyPlannings = new ArrayList<>();
        for (DailyPlanning dp : dailyPlannings) {
            if(dp.getPlanningDateTS().equals(String.valueOf(LocalDateTime.of(date, LocalTime.of(0,0)).toEpochSecond(ZoneOffset.UTC))))
                planning = dp;
        }

        return planning;
    }

    public void addPlanning(LocalDate date) throws Exception {
        if (getPlanningOfDate(date) != null) {
//            throw new PlanningAlreadyExistException(); TODO
            throw new Exception();
        }
        dailyPlannings.add(new DailyPlanning(date));
    }

    public void addPlanning() throws Exception {
        if (getPlanningOfDate(LocalDate.now()) != null) {
//            throw new PlanningAlreadyExistException(); TODO
            throw new Exception();
        }
        dailyPlannings.add(new DailyPlanning(LocalDate.now()));
    }


    @NotNull
    @XmlElementWrapper(name = "dailyPlannings")
    @XmlElement(name = "dailyPlanning")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<DailyPlanning> getDailyPlannings() {
        return dailyPlannings;
    }

    public void setDailyPlannings(List<DailyPlanning> dailyPlannings) {
        this.dailyPlannings = dailyPlannings;
    }

    @XmlElement(name = "idDailyPlanningList")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyPlanningList that = (DailyPlanningList) o;
        return getDailyPlannings().equals(that.getDailyPlannings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDailyPlannings());
    }

}
