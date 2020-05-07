package entities;

import java.time.LocalDate;
import java.util.List;

public class DailyPlanningList {

    private List<DailyPlanning> dailyPlannings;

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


}
