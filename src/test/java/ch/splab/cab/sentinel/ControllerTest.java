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


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@PropertySource("classpath:application.properties")
public class ControllerTest {

    RedirectAttributes ra = new RedirectAttributes() {
        @Override
        public RedirectAttributes addAttribute(String s, Object o) {
            return null;
        }

        @Override
        public RedirectAttributes addAttribute(Object o) {
            return null;
        }

        @Override
        public RedirectAttributes addAllAttributes(Collection<?> collection) {
            return null;
        }

        @Override
        public RedirectAttributes mergeAttributes(Map<String, ?> map) {
            return null;
        }

        @Override
        public RedirectAttributes addFlashAttribute(String s, Object o) {
            return null;
        }

        @Override
        public RedirectAttributes addFlashAttribute(Object o) {
            return null;
        }

        @Override
        public Map<String, ?> getFlashAttributes() {
            return null;
        }

        @Override
        public Model addAllAttributes(Map<String, ?> map) {
            return null;
        }

        @Override
        public boolean containsAttribute(String s) {
            return false;
        }

        @Override
        public Map<String, Object> asMap() {
            return null;
        }
    };

    String loggedIn = "eyJ1c2VybmFtZSI6InRlc3R1c2VyIiwgImlzTG9nZ2VkIjoieWVzIn0=";
    String loggedOut = "eyJ1c2VybmFtZSI6InRlc3R1c2VyIiwgImlzTG9nZ2VkIjoibm8ifQ==";

    @Before
    public void setUp() throws Exception {
        Initialize.prepareDbInitScripts();
        Initialize.initializeTestDb();
        Application.eventsCache = new HealthEventsCache(100);
    }

    @Test
    public void showIndexTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showIndex(request, response, model);
        assertEquals("pinglist", value);
    }

    @Test
    public void showDashboardTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showDashboard("eyJpc0xvZ2dlZCI6Im5vIn0=", request, response, model);
        assertEquals("login", value);
    }

    @Test
    public void showDashboardTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showDashboard(loggedIn, request, response, model);
        assertEquals("visualization", value);
    }

    @Test
    public void showProfiledataTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showProfileData(loggedIn, request, response, model);
        assertEquals("profile", value);
    }

    @Test
    public void showProfiledataTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showProfileData(loggedOut, request, response, model);
        assertEquals("login", value);
    }

    @Test
    public void showSeriesDetailsTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showSeriesDetails(loggedIn, "1", request, response, model);
        assertEquals("seriesdetails", value);
    }

    @Test
    public void showSeriesDetailsTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showSeriesDetails(loggedOut, "1", request, response, model);
        assertEquals("login", value);
    }

    @Test
    public void showSpaceDetailsTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showSpaceDetails(loggedIn, "1", request, response, model);
        assertEquals("spacedetails", value);
    }

    @Test
    public void showSpaceDetailsTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showSpaceDetails(loggedOut, "1", request, response, model);
        assertEquals("login", value);
    }

    @Test
    public void showSpaceDataTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showSpaceData(loggedIn, request, response, model);
        assertEquals("space", value);
    }

    @Test
    public void showSpaceDataTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showSpaceData(loggedOut, request, response, model);
        assertEquals("login", value);
    }

    @Test
    public void showTestPageTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showTestPage(request, response, model);
        assertEquals("index", value);
    }

    @Test
    public void showOverviewTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        model.addAttribute("loginmsg", "some string");
        String value = controller.showOverview(loggedOut, request, response, model);
        assertEquals("login", value);
    }

    @Test
    public void showOverviewTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        model.addAttribute("loginmsg", "some string");
        String value = controller.showOverview(loggedIn, request, response, model);
        assertEquals("index2", value);
    }

    @Test
    public void showHealthCheckOverviewTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        model.addAttribute("loginmsg", "some string");
        String value = controller.showHealthCheckOverview(loggedOut, request, response, model);
        assertEquals("login", value);
    }

    @Test
    public void showHealthCheckTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        model.addAttribute("loginmsg", "some string");
        String value = controller.showHealthCheckOverview(loggedIn, request, response, model);
        assertEquals("healthcheck", value);
    }

    @Test
    public void showLogoutTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.showLogout(request, response, model);
        assertEquals("redirect:/", value);
    }

    @Test
    public void processLoginTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processLogin("testuser","test", request, response, model, ra);
        assertEquals("redirect:/", value);
    }

    @Test
    public void processCreateSpaceTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateSpace(loggedIn,"test", request, response, model, ra);
        assertEquals("redirect:/spaces", value);
    }

    @Test
    public void processCreateSpaceTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateSpace(loggedOut,"test", request, response, model, ra);
        assertEquals("login", value);
    }

    @Test
    public void processCreateSpaceTestV3()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateSpace(loggedIn,"testspace", request, response, model, ra);
        assertEquals("redirect:/spaces", value);
    }

    @Test
    public void processCreateSeriesTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateSeries(loggedIn,"testspace", "test", "unixtime:s msgtype:json", "", null, request, response, model, ra);
        assertEquals("redirect:/space/1", value);
    }

    @Test
    public void processCreateSeriesTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateSeries(loggedIn,"testspace", "test", "unixtime:s msgtype:json", null, null, request, response, model, ra);
        assertEquals("redirect:/space/1", value);
    }

    @Test
    public void processCreateSeriesTestV3()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateSeries(loggedOut,"testspace", "test", "unixtime:s msgtype:json", "", "yes", request, response, model, ra);
        assertEquals("login", value);
    }

    @Test
    public void processCreateSeriesTestV4()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateSeries(loggedIn,"testspace", "test", "unixtime:s msgtype:json", "", "yes", request, response, model, ra);
        assertEquals("redirect:/space/1", value);
    }

    @Test
    public void processCreateHealthCheckTest()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateHealthCheck(loggedIn,"http://www.yahoo.com", "http://localhost:3000/", "30000", "code", "2", request, response, model, ra);
        assertEquals("redirect:/healthchecks", value);
    }

    @Test
    public void processCreateHealthCheckTestV2()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateHealthCheck(loggedOut,"http://www.yahoo.com", "http://localhost:3000/", "30000", "code", "2", request, response, model, ra);
        assertEquals("login", value);
    }

    @Test
    public void processCreateHealthCheckTestV3()
    {
        Controller controller = new Controller();
        HttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String value = controller.processCreateHealthCheck(loggedIn,"", "http://localhost:3000/", "30000", "code", "2", request, response, model, ra);
        assertEquals("redirect:/healthchecks", value);
    }

}
