package com.qtong.core.service.scheduler.impl;

import com.qtong.core.service.scheduler.JobSchedulerService;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.util.Map;

/**
 * Created by ZML on 2015/1/15.
 */
public class JobSchedulerServiceImpl implements JobSchedulerService {
    private static Logger logger = Logger
            .getLogger(JobSchedulerServiceImpl.class);

    private Scheduler scheduler;

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void addJob(String jobName, String jobGroup, String cronString,
                       Class<? extends Job> clazz, Map<?, ?> param)
            throws SchedulerException, ParseException {

        scheduler.scheduleJob(new JobDetailImpl(jobName, jobGroup, clazz, true,
                false), createTigger(jobName, jobGroup, cronString));

    }

    @SuppressWarnings("deprecation")
    @Override
    public void startJob(String jobName, String jobGroup, String cronString,
                         Class<? extends Job> clazz, Map<String, Object> param)
            throws SchedulerException, ParseException {

        JobKey jobKey = new JobKey(jobName, jobGroup);

        if (!scheduler.checkExists(jobKey)) {
            Trigger trigger = createTigger(jobName, jobGroup, cronString);

            JobDetail jobDetail = new JobDetailImpl(jobName, jobGroup, clazz, true,
                    true);
            scheduler.scheduleJob(jobDetail, trigger);
            //获得该Job的上下文，并传参数进去
            scheduler.getJobDetail(jobKey).getJobDataMap().putAll(param);
        } else {
            //获得该Job的上下文，并传参数进去
            scheduler.getJobDetail(jobKey).getJobDataMap().putAll(param);

            scheduler.resumeJob(jobKey);
            ;
        }
    }

    @Override
    public void pauseJob(String jobName, String jobGroup) throws SchedulerException,
            ParseException {
        if (scheduler.checkExists(new JobKey(jobName, jobGroup))) {
            scheduler.pauseJob(new JobKey(jobName, jobGroup));
        } else {
            logger.warn("The '" + jobGroup + "." + jobName + "' Job does not exist");
        }
    }

    @Override
    public void removeJob(String jobName, String jobGroup) throws SchedulerException {

        if (scheduler.checkExists(new JobKey(jobName, jobGroup))) {
            scheduler.deleteJob(new JobKey(jobName, jobGroup));
        } else {
            logger.warn("The '" + jobGroup + "." + jobName + "' Job does not exist");
        }
    }

    @SuppressWarnings({"deprecation"})
    private CronTrigger createTigger(String jobName, String jobGroup,
                                     String cronString) throws ParseException {
        return new CronTriggerImpl(jobName, jobGroup, cronString);
    }
}
