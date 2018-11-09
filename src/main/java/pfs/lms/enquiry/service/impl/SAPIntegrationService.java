package pfs.lms.enquiry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pfs.lms.enquiry.resource.SAPLoanApplicationResource;
import pfs.lms.enquiry.service.ISAPIntegrationService;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;


@Slf4j
@Service
@RequiredArgsConstructor
public class SAPIntegrationService implements ISAPIntegrationService {

    @Override
    public String fetchCSRFToken() {

       RestTemplate restTemplate = new RestTemplate();

        /* Calling Code to get the current division*/

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic c2FqZWV2OnNhcHNhcA==");
        headers.add("X-Csrf-Token", "Fetch ");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        System.out.println("THE REQUEST : "+requestEntity.toString());

        URI uri = null;
        try
        {
            uri = new URI("http://192.168.1.203:8000/sap/opu/odata/sap/ZPFS_LMS_ENQ_PORTAL_LOAN_V2_SRV/?sap-client=300$metadata" );
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        System.out.println("THE URI : "+uri.toString());

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
            String xCsrfToken = fetchCSRFToken();

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Basic c2FqZWV2OnNhcHNhcA==");
            headers.add("X-Csrf-Token", xCsrfToken);
            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            System.out.println("THE REQUEST : " + request.toString());

            /*** POST REQUEST ****/
            HttpEntity<SAPLoanApplicationResource> requestToPost = new HttpEntity<SAPLoanApplicationResource>(sapLoanApplicationResource, headers);
            URI postURI = new URI("http://192.168.1.203:8000/sap/opu/odata/sap/ZPFS_LMS_ENQ_PORTAL_LOAN_V2_SRV/LoanApplicationSet?sap-client=300");
            System.out.println("THE URI : " + postURI.toString());

            createdInvoice = restTemplate.exchange(postURI, HttpMethod.POST, requestToPost, String.class);

            HttpStatus statusCode = createdInvoice.getStatusCode();


        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        }
    }

