/**
 * 
 */
package hu.infokristaly.homework.timerservice.front.manager;

import hu.infokristaly.homework.timerservice.back.model.EventInfo;
import hu.infokristaly.homework.timerservice.back.timer.ScheduleService;
import hu.infokristaly.homework.timerservice.front.pfmodels.ScheduleEventInfo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.component.schedule.Schedule;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * @author pzoli
 * http://stackoverflow.com/questions/12934464/jboss-7-1-1-and-the-ejb-3-1-timer-service
 * http://stackoverflow.com/questions/13292473/set-configure-the-ejb-timer-services-datasource
 * https://issues.jboss.org/browse/JBAS-6241
 * http://theopentutorials.com/tutorials/java-ee/ejb3/ejb3-timer-service/
 *  
 * original setup in AS7 standalone.xml
 * 
 * <passivation-stores>
 *   <file-passivation-store name="file"/>
 * </passivation-stores>
 *
 * 
 * <timer-service thread-pool-name="default">
 *    <data-store path="timer-service-data" relative-to="jboss.server.data.dir"/>
 * </timer-service>
 *
 */
@Named
@SessionScoped
public class ScheduleManager implements Serializable {

    private static final long serialVersionUID = 620934289508314544L;

    private ScheduleModel eventModel = new DefaultScheduleModel();
    
    private EventInfo eventInfo;

    @Inject
    private ScheduleService scheduleService;
    
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    @PostConstruct
    private void init() {
        ServletContext context = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
        eventInfo = new EventInfo();
    }
    
    public void createEvent() {
        scheduleService.addTimer(eventInfo);
        ScheduleEvent scheduleEvent = new ScheduleEventInfo(eventInfo); 
        eventModel.addEvent(scheduleEvent);
    }

    public EventInfo getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(EventInfo eventInfo) {
        this.eventInfo = eventInfo;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        eventInfo = (EventInfo) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        Date event = (Date) selectEvent.getObject();
        //Calendar startDate = new GregorianCalendar();
        //startDate.setTime(event);
        //Date now = new Date();
        //startDate.setTime(now);
        //startDate.add(Calendar.SECOND, 10);

        Schedule schedule = (Schedule) selectEvent.getComponent();
        final TimeZone timeZone = schedule.calculateTimeZone();
        System.out.println(event + ": is in DaylightTime:" + timeZone.inDaylightTime(event) + ", getDSTSavings: " + timeZone.getDSTSavings());
        Calendar startDate = new GregorianCalendar();
        startDate.setTime(event);
        if (timeZone.inDaylightTime(event)) {
            startDate.add(Calendar.MILLISECOND, timeZone.getDSTSavings()*-1);
            event = startDate.getTime();
        }

        eventInfo = new EventInfo(); 
        eventInfo.setEventStart(event); //startDate.getTime()
    }

    public void onEventMove(ScheduleEntryMoveEvent selectEvent) {
        System.out.println("Event StartDate: "+selectEvent.getScheduleEvent().getStartDate());
        System.out.println("Event EndDate: "+selectEvent.getScheduleEvent().getEndDate());
    }

    public void onEventResize(ScheduleEntryResizeEvent selectEvent) {
        System.out.println("Event StartDate: "+selectEvent.getScheduleEvent().getStartDate());
        System.out.println("Event EndDate: "+selectEvent.getScheduleEvent().getEndDate());
    }
    
    public void deleteCurrentEvent() {
        
    }
}
