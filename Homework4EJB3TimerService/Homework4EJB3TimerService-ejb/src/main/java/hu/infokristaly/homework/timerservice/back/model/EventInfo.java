/**
 * 
 */
package hu.infokristaly.homework.timerservice.back.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author pzoli
 *
 */
@Entity
public class EventInfo implements Serializable {

    private static final long serialVersionUID = -8650678082991901878L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Basic
    private Date eventStart;

    @Temporal(TemporalType.DATE)
    @Basic
    private Date eventEnd;

    @Basic
    private boolean enabled;

    @Basic
    private boolean removable;

    @Basic
    private boolean isAllDay;

    @Basic
    private String title;
    
    @Basic
    private String timeZone;
    
    @Basic
    private Integer frequency;
    
    @Basic
    private Byte rememberFrequency;
    
    @Lob
    private String message;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getEventStart() {
        return eventStart;
    }
    
    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Byte getRememberFrequency() {
        return rememberFrequency;
    }

    public void setRememberFrequency(Byte rememberFrequency) {
        this.rememberFrequency = rememberFrequency;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
