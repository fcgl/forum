package com.fcgl.madrid.repository;

import com.fcgl.madrid.MadridApplication;
import com.fcgl.madrid.forum.model.InternalStatus;
import com.fcgl.madrid.forum.model.PostRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    //TODO: Doesn't actually do what I want it to do... Testing is weird on this one
    @Test
    public void postTestBadParamsTwo() {
        PostRequest input = new PostRequest(null, null, null, null);
        final String baseUrl = createURLWithPort("/forum/post/v1/post");
        HttpEntity<PostRequest> entity = new HttpEntity<PostRequest>(input, headers);
        ResponseEntity<InternalStatus> response = restTemplate.postForEntity(baseUrl, entity, InternalStatus.class);
        InternalStatus actual = response.getBody();
        assertEquals(2, actual.getCode());
        assertEquals(400, actual.getHttpCode());
        assertEquals(3, actual.getMessages().size());
    }

    private String createURLWithPort(final String uri) {
        return "http://localhost:" + port + uri;
    }

}
