package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SecureSpaceMain {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "curl/7.58.0");
        headers.add("Referer","https://secur.space/supplier-inventory");
        headers.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
        headers.add("Referer","https://secur.space/supplier-inventory");
        headers.add("Referer","https://secur.space/supplier-inventory");
        headers.add("Cookie","SESSION=ZmExOTRmYWMtNzIxMS00MjIxLTg1NmMtMTIxMmRmYTk2ZWRk; __cfruid=ec8aa5dc1a4a99aba86396b6f3c6020db20ca854-1688545627; Path=/; Domain=launch.secur.space; Secure; HttpOnly; _gcl_au=1.1.1823774368.1688522458; __hs_opt_out=no; __hs_initial_opt_in=true; _ga=GA1.1.1676989153.1688522459; hubspotutk=cd80cc12d0c057308c0b7022d303d1e6; __hssrc=1; _fbp=fb.1.1688522520881.1980294057; __hstc=132574161.cd80cc12d0c057308c0b7022d303d1e6.1688522514934.1688522514934.1688545633764.2; remember-me=bWFydmVseCU0MG5leHR0cnVja2luZy5jb206MTY5MDI1NjE2NDk0OTpjYmMzYWVhM2Q5MTMxMzdkODEzZTBiZjg0Yzg0MTIwMw; SESSION=ZmExOTRmYWMtNzIxMS00MjIxLTg1NmMtMTIxMmRmYTk2ZWRk; _ga_GV0WZ8VQ2S=GS1.1.1689069444.16.0.1689069495.9.0.0; _ga_GBFTZV8HXC=GS1.1.1689069444.16.0.1689069495.9.0.0");


        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange("https://secur.space/supplier-inventory/api/suppliers/527f73ac-5af7-4de6-8bf2-3ab79cecaf56/inventory-report", HttpMethod.GET,entity,String.class);

        System.out.println(responseEntity.toString());
    }

}
