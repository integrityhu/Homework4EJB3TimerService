/**
 * 
 */
package hu.infokristaly.homework.timerservice.back.timer;

import java.text.SimpleDateFormat;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author pzoli
 *
 */
@Stateless
public class MailJobJoinedToDeliveryDate {

    @Inject
    private EntityManager em;

    private static int MAX_AMOUNT_FOR_NOT_TIMED_MAILS_PER_PERIODS = 10;
    private static int MAX_AMOUNT_FOR_TIMED_MAILS_PER_PERIODS = 50;
    private static int MAX_TRY_COUNT = 10;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

}
