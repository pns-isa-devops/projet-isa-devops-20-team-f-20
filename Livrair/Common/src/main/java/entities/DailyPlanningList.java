package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="DailyPlanningLists")
public class DailyPlanningList implements Serializable {

    private List<DailyPlanning> dailyPlannings;
    private int id;

    public DailyPlanningList() {
        dailyPlannings = new ArrayList<>();
    }

    public DailyPlanning getPlanningOfDate(LocalDate date) {
        DailyPlanning planning = null;
        for (DailyPlanning dp : dailyPlannings) {
            if(dp.getDate().equals(date))
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

    @OneToMany(cascade = {CascadeType.REMOVE})
    public List<DailyPlanning> getDailyPlannings() {
        return dailyPlannings;
    }

    public void setDailyPlannings(List<DailyPlanning> dailyPlannings) {
        this.dailyPlannings = dailyPlannings;
    }

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
