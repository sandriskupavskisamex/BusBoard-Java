package training.busboard.web;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.BusInformation;
import training.busboard.StopPointsWrapper;
import training.busboard.resultPostCode;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
@EnableAutoConfiguration
public class Website {

    public static Client getClient(SSLContext sslcontext) throws Exception{
        return ClientBuilder.newBuilder().register(JacksonFeature.class).sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
    }

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) throws Exception {

        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "9090");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "9090");

        List<BusInformation> busInfo = new ArrayList<BusInformation>();
        busInfo.add(new BusInformation(216, 416, "Hell"));
        busInfo.add(new BusInformation(666, 999999999, "Sandris' house :)"));
        busInfo.add(new BusInformation(420,7,"Enlightenment"));

        SSLContext sslcontext = SSLContext.getInstance("TLS");

        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
            }

            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, new java.security.SecureRandom());

        Client client2 = getClient(sslcontext);
        resultPostCode locationGetter = client2.target("https://api.postcodes.io/postcodes/" + "nw51tl")
                .request("text/json")
                .get(resultPostCode.class);

        double latitude = locationGetter.result.getLatitude();
        double longitude = locationGetter.result.getLongitude();

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).sslContext(sslcontext).hostnameVerifier((s1, s2) -> true).build();
        StopPointsWrapper stops = client.target("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&radius=500&modes=bus%2Ctram&lat=" + latitude + "&lon=" + longitude)
                .request("text/json")
                .get(StopPointsWrapper.class);

        System.out.println(stops.getStopPoints());

        return new ModelAndView("info", "busInfo", new BusInfo(postcode, stops));

    }

    public static void main(String[] args) throws Exception {

        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "9090");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "9090");

        SpringApplication.run(Website.class, args);



    }

}