package com.tvshows;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Component
@RunWith(SpringRunner.class)
public abstract class ComponentTest {
    @LocalServerPort
    public int port;
    private static int externalServicesPort = 65351;

    public static Header aValidAuthHeader() {
        return new Header("auth-token", "aValidToken");
    }

    static {
        System.setProperty("http.keepAlive", "false");
        System.setProperty("http.maxConnections", "1");
    }

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(options().port(externalServicesPort));
    @Rule
    public WireMockClassRule wireMockInstanceRule = wireMockRule;

    @Before
    public void setupComponentTest() {
        RestAssured.port = port;
    }

    @AfterClass
    public static void reset() {
        RestAssured.reset();
        WireMock.reset();
    }
}
