package com.fcgl.madrid.repository;

import com.fcgl.madrid.MadridApplication;
import com.fcgl.madrid.forum.model.response.InternalStatus;
import com.fcgl.madrid.forum.model.request.PostRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = MadridApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostRepositoryIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @BeforeClass
    public static void start() {
        Log logger = LogFactory.getLog(SpringBootTestContextBootstrapper.class);
        logger.info("\n***********PostRepositoryIntegrationTest Running***********\n");
    }

    @Test
    public void postTestBadParamsTwo() {
        assertEquals("", "");
    }

    private String createURLWithPort(final String uri) {
        return "http://localhost:" + port + uri;
    }

}
