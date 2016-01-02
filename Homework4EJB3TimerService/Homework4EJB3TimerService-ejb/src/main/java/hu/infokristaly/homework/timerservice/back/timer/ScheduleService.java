/**
 * 
 */
package hu.infokristaly.homework.timerservice.back.timer;

import hu.infokristaly.homework.timerservice.back.model.EventInfo;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 * @author pzoli
 * 
 */
@Named
@Stateless
public class ScheduleService {

    @Resource
    TimerService timerService;

    @Inject
    private EntityManager em;

    public void addTimer(EventInfo eventInfo) {
        em.persist(eventInfo);
        TimerConfig conf = new TimerConfig();
        conf.setInfo(eventInfo);
        ScheduleExpression schedule = new ScheduleExpression();
        schedule.start(eventInfo.getEventStart());
        schedule.end(eventInfo.getEventEnd());
        schedule.timezone(eventInfo.getTimeZone());
        Timer timer = timerService.createCalendarTimer(schedule, conf);
        System.out.println(timer.getHandle());
    }

    @Timeout
    private void timeOut(Timer timer) {
        System.out.println("Timer[" + timer.getInfo() + "] TimerBean: timeout occurred");
        System.out.println("Timer[" + timer.getInfo() + "] Persistent : " + timer.isPersistent());
        if (timer.getInfo() != null && (timer.getInfo() instanceof EventInfo)) {
            if (((EventInfo) timer.getInfo()).isRemovable()) {
                timer.cancel();
                EventInfo eventInfo = em.find(EventInfo.class, ((EventInfo)timer.getInfo()).getId());
                em.remove(eventInfo);
            } else {
                if (((EventInfo) timer.getInfo()).isEnabled()) {

                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Timer[" + timer.getInfo() + "] wait expired.");
    }
}
