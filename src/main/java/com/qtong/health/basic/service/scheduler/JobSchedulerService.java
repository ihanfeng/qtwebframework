package com.qtong.health.basic.service.scheduler;


import org.quartz.Job;
import org.quartz.SchedulerException;

import java.text.ParseException;
import java.util.Map;

/**
 * Created by ZML on 2015/1/15.
 */
public interface JobSchedulerService {

    void addJob(String jobName, String jobGroup, String cronString, Class<? extends Job> clazz, Map<?, ?> param) throws SchedulerException, ParseException;

    void startJob(String jobName, String jobGroup, String cronString, Class<? extends Job> clazz, Map<String, Object> param) throws SchedulerException, ParseException;

    void pauseJob(String jobName, String jobGroup) throws SchedulerException, ParseException;

    void removeJob(String jobName, String jobGroup) throws SchedulerException, ParseException;
}
