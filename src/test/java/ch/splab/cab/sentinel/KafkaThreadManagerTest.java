package ch.splab.cab.sentinel;
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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class KafkaThreadManagerTest {
    @Test
    public void testKafkaThreadManager()
    {
        KafkaThreadManager kThread = new KafkaThreadManager();
        assertTrue("adding a new topic for thread assignment", kThread.addTopic("sometopic"));
        assertTrue("removing an exiting topic from thread assignment", kThread.removeTopic("sometopic"));
        assertFalse("removing some non-existent topic from thread assignment", kThread.removeTopic("sometopic"));
    }
}
