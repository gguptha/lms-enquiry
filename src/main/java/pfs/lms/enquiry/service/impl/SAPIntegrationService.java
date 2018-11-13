package pfs.lms.enquiry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pfs.lms.enquiry.resource.SAPLoanApplicationResource;
import pfs.lms.enquiry.service.ISAPIntegrationService;

import javax.xml.ws.http.HTTPException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;


@Slf4j
@Service
@RequiredArgsConstructor
public class SAPIntegrationService implements ISAPIntegrationService {

    @Override
    public String fetchCSRFToken() {

        HttpHeaders headers = new HttpHeaders() {
            {
                String auth = "sajeev" + ":" + "sapsap";
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")) );
                String authHeader = "Basic " + new String( encodedAuth );
                set( "Authorization", authHeader );
            }
        };
        headers.add("X-Csrf-Token", "Fetch ");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        System.out.println("THE REQUEST : "+requestEntity.toString());

        URI uri = null;
        try
        {
            uri = new URI("http://192.168.1.203:8000/sap/opu/odata/sap/ZPFS_LOAN_ENQ_PORTAL_SRV/?sap-client=300$metadata" );
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        System.out.println("THE URI : "+uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        System.out.println("Headers: " + responseEntity.getHeaders());
        System.out.println("Result - status ("+ responseEntity.getStatusCode() + ") has body: " + responseEntity.hasBody());
                HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("THE STATUS CODE: "+statusCode);

        System.out.println(responseEntity.getHeaders().get("x-csrf-token").get(0));

        return responseEntity.getHeaders().get("x-csrf-token").get(0);
    }

    @Override
    public void postLoanApplication(SAPLoanApplicationResource sapLoanApplicationResource) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> createdInvoice = null;

        try {

            HttpHeaders headers = new HttpHeaders() {
                {
                    String auth = "sajeev" + ":" + "sapsap";
                    byte[] encodedAuth = Base64.encodeBase64(
                            auth.getBytes(Charset.forName("US-ASCII")) );
                    String authHeader = "Basic " + new String( encodedAuth );
                    set( "Authorization", authHeader );
                }
            };
            //headers.add("X-Csrf-Token", "Fetch ");
            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            System.out.println("THE REQUEST : "+requestEntity.toString());

            URI uri = null;
            uri = new URI("http://192.168.1.203:8000/sap/opu/odata/sap/ZPFS_LOAN_ENQ_PORTAL_SRV/?sap-client=300$metadata" );
            System.out.println("THE URI : "+uri.toString());

            restTemplate = new RestTemplate();
            //ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
            //String csrfToken = responseEntity.getHeaders().get("x-csrf-token").get(0);


            // ----------------------------------------------------------------- //
            headers = new HttpHeaders() {
                {
                    String auth = "sajeev" + ":" + "sapsap";
                    byte[] encodedAuth = Base64.encodeBase64(
                            auth.getBytes(Charset.forName("US-ASCII")) );
                    String authHeader = "Basic " + new String( encodedAuth );
                    set( "Authorization", authHeader );
                }
            };
            headers.setContentType(MediaType.APPLICATION_JSON);
            //String xCsrfToken = fetchCSRFToken();

            //String testCsrf = "VuGi64DbGq2C-wZaCTMN7g==";
           // headers.add("X-Csrf-Token", csrfToken);


            headers.add("X-Requested-With", "X");

            map= new LinkedMultiValueMap<String, String>();
            //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            //System.out.println("THE REQUEST : " + request.toString());

            System.out.println("POST Request Entity: " + requestEntity);


            HttpEntity<SAPLoanApplicationResource> requestToPost = new HttpEntity<SAPLoanApplicationResource>(sapLoanApplicationResource, headers);
            URI postURI = new URI("http://192.168.1.203:8000/sap/opu/odata/sap/ZPFS_LOAN_ENQ_PORTAL_SRV/LoanApplicationSet?sap-client=300");

            System.out.println("THE URI : " + postURI.toString());
            System.out.println("THE URI : " + requestToPost.getHeaders().toString());

            log.info("Content: " + requestToPost.toString());
            log.info("Object: " + sapLoanApplicationResource.toString());
            log.info(requestToPost.toString());

            System.out.println("POST Request Content: " + requestToPost.getBody().toString());


            createdInvoice = restTemplate.exchange(postURI, HttpMethod.POST, requestToPost, String.class);



            HttpStatus statusCode = createdInvoice.getStatusCode();

            System.out.println(createdInvoice.getBody());

        }
        catch (Exception ex) {
            System.out.println( "  ExceptionMessage :" + ex.getMessage().toString());
            ex.printStackTrace();

        }

//        catch (HTTPException httpException) {
//
//            System.out.println( "HTTP ExceptionMessage :" + httpException.getMessage().toString());
//            httpException.printStackTrace();
//        }
//        catch (URISyntaxException e)
//        {
//            e.printStackTrace();
//        }

    }

    @Override
    public void getLoanApplication(String loanApplicationId) {

        HttpHeaders headers = new HttpHeaders() {
            {
                String auth = "sajeev" + ":" + "sapsap";
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")) );
                String authHeader = "Basic " + new String( encodedAuth );
                set( "Authorization", authHeader );
            }
        };
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        System.out.println("THE REQUEST : "+requestEntity.toString());

        URI uri = null;
        try
        {
            uri = new URI("http://192.168.1.203:8000/sap/opu/odata/sap/ZPFS_LOAN_ENQ_PORTAL_SRV/LoanApplicationSet('0000010003115')?sap-client=300&$format=json" );
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
        log.info(responseEntity.getBody());
    }
}

