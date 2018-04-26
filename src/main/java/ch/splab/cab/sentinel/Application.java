/*
 *  Copyright (c) 2018. Service Prototyping Lab, ZHAW
 *   All Rights Reserved.
 *
 *       Licensed under the Apache License, Version 2.0 (the "License"); you may
 *       not use this file except in compliance with the License. You may obtain
 *       a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *       Unless required by applicable law or agreed to in writing, software
 *       distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *       WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *       License for the specific language governing permissions and limitations
 *       under the License.
 *
 *
 *       Author: Piyush Harsh,
 *       URL: piyush-harsh.info
 *       Email: piyush.harsh@zhaw.ch
 */

package ch.splab.cab.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {
        APIController.class
})
public class Application {
    final static Logger logger = Logger.getLogger(Application.class);
    public static KafkaThreadManager threadpool = new KafkaThreadManager();
    public static SeriesStructureCache msgFormatCache;
    public static ExecutorService PersistenceWorkerPool = Executors.newFixedThreadPool((int)(Math.max(Math.ceil((Runtime.getRuntime().availableProcessors() * 0.3)) - 1.0, 1.0)));
    public static ExecutorService PingWorkerPool = Executors.newFixedThreadPool((int)(Math.max(Math.ceil((Runtime.getRuntime().availableProcessors() * 0.2)), 1.0)));
    public static HealthEventsCache eventsCache;
    public static HealthCheckManager healthManager = new HealthCheckManager();

    public static void main (String[] args)
    {
        if(args!=null && args.length>0 && args[0].equalsIgnoreCase("--testrun=true"))
        {
            //test run do nothing
        }
        else {
            SpringApplication.run(Application.class, args);
        }
        //boolean status = KafkaClient.createTopic("zane-sensor-data");
        //System.out.println(status);
        //boolean status = KafkaClient.deleteTopic("testing5");
        //System.out.println(status);
        if(!Initialize.isDbValid()) {
            Initialize.prepareDbInitScripts();
            Initialize.initializeDb();
        }
        msgFormatCache = new SeriesStructureCache(AppConfiguration.getSeriesFormatCacheSize());
        eventsCache = new HealthEventsCache(100); //allowing upto 100 ping traces to be maintained

        if(AppConfiguration.getStreamDBType().equalsIgnoreCase("influxdb")) InfluxDBClient.init();
        //SqlDriver.isDuplicateUser("piyush@zhaw.ch");
        TopicsManager topicSyncProcess = new TopicsManager();
        topicSyncProcess.start(); //this consumes 1 processor core
        healthManager.start(); //this consumes 1 processor core if available
        //InfluxDBClient.getLastPoints("user-1-test", "sys-stats", 10);
    }

}
