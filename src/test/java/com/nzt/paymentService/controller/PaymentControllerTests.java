package com.nzt.paymentService.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import com.nzt.paymentService.model.Charge;

import java.io.IOException;
import java.io.InputStream;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentControllerTests {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9999);

    /*public WireMockRule wireMockRule = new WireMockRule(wireMockConfig()
            .dynamicPort()
            .dynamicHttpsPort()
    );*/

    @Before
    public void setUp() {
    }

    @Test
    public void test_create1() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        Charge charge = new Charge(2000L, "sdk", "jsdh", "ksjd");
        String response = restTemplate.postForObject("http://localhost:8080/create", charge, String.class);
        org.junit.Assert.assertEquals("success", response);
        //String responseString = this.convertResponseToString(response.getBody());

        verify(postRequestedFor(urlEqualTo("/create")));
        verify(postRequestedFor(urlMatching("/create"))
                .withHeader("Content-Type", notMatching("application/json")));
    }

    //@Test

    //public void test_create() throws Exception{

    //

    ////WireMockServer wireMockServer = new WireMockServer();

    ////wireMockServer.start();

    ////configureFor("localhost", 8090);

    ////stubFor(get(urlEqualTo("/create")).willReturn(aResponse().withBody("success")));

    //stubFor(post(urlPathMatching("/create"))

    //.willReturn(aResponse()

    //.withStatus(200)

    //.withHeader("Content-Type", "application/json")

    //.withBody("success")));

    //

    //

    //CloseableHttpClient httpClient = HttpClients.createDefault();

    //HttpGet request = new HttpGet("http://localhost:8080/create");

    //HttpResponse httpResponse = httpClient.execute(request);

    //String responseString = this.convertResponseToString(httpResponse);

    //

    //verify(postRequestedFor(urlEqualTo("/create")));

    //assertEquals("success", responseString);

    //

    //verify(postRequestedFor(urlMatching("/create"))

    //.withRequestBody(matching(".*amount*"))

    //.withHeader("Content-Type", notMatching("application/json")));

    //

    ////wireMockServer.stop();

    //}

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

    private void mockRemoteService() {
        stubFor(post(urlPathMatching("/create"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("success")));
    }

}
