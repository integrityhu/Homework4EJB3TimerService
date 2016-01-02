/**
 * 
 */
package hu.infokristaly.homework.timerservice.front.pfmodels;

import hu.infokristaly.homework.timerservice.back.model.EventInfo;

import java.util.Date;

import org.primefaces.model.ScheduleEvent;

/**
 * @author pzoli
 *
 */
public class ScheduleEventInfo implements ScheduleEvent {

    private EventInfo eventInfo;
    
    public ScheduleEventInfo(EventInfo eventInfo) {
        this.eventInfo = eventInfo;
    }
    
    @Override
    public String getId() {
        return String.valueOf(eventInfo.getId());
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public Object getData() {
        return eventInfo;
    }

    @Override
    public String getTitle() {
        return eventInfo.getTitle();
    }

    @Override
    public Date getStartDate() {
        return eventInfo.getEventStart();
    }

    @Override
    public Date getEndDate() {
        return eventInfo.getEventEnd();
    }

    @Override
    public boolean isAllDay() {
        return eventInfo.isAllDay();
    }

    @Override
    public String getStyleClass() {
        return null;
    }

    @Override
    public boolean isEditable() {
        return eventInfo.isEnabled();
    }

}
