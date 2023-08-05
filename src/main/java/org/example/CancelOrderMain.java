package org.example;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CancelOrderMain {

    public static void main(String[] args) {

        List<String> as = Lists.newArrayList(
            "866f0236-2716-436c-a685-e1c9bb681312",
            "29cf4a57-5f4c-433b-914c-932a3ad2d314",
            "118256fc-23f9-412f-9470-52a044179237",
            "6f73bc18-4211-431b-bf85-89f8d4cc93ef",
            "bad97d0b-b172-4cff-930a-2f10ce287148",
            "5d74d009-5c50-4cba-aba8-5b9abb6977dc",
            "f05d1ae6-5fc6-4d0d-9148-757a72cf8bfe",
            "5d927f4d-d15e-4103-b8ed-8c8e41d0e4cc",
            "f9c5e382-440a-496a-b26b-7e228b312b12",
            "57960f9a-14d9-40d5-851d-54a7a6b657eb",
            "f639bff3-11ab-427e-bda5-67b0c0836b66",
            "d7eeeff7-06e1-49c8-b384-a13004a52d3f",
            "f07841c1-36d9-41d7-8ed4-9128e9fcaa8b",
            "26e23f7e-7a6c-462a-b23a-43cb293aa558",
            "005054f8-8a67-497d-b3a9-0870a72532c1",
            "5f134511-d522-4bb5-a4fd-d505fc9a122e",
            "a8ed104e-26bd-4156-8ed4-0a2185f67775",
            "25628e1c-71a0-490b-8043-5499f17f6638",
            "3c2e1e8d-73f1-4f73-ab3c-f47d82a9dd23",
            "4776e1d1-70e2-48c2-9bee-057f589f2cd6",
            "ee155cbb-c56b-4137-8bc6-33b252f82d10",
            "33a60ef0-aa55-4ba2-867a-9dcdce82e5c4",
            "6802c766-92bd-4922-9cb7-1d44e797ff6e",
            "967a767d-94a1-4ed7-a05c-ecc57405df12",
            "07601651-9027-4f42-98a6-b8100104bb79",
            "476eef10-2e81-487b-95d3-1b1178c19ff5"

        );
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "curl/7.58.0");

        headers.add("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InlhaUl0anBrOURqN19EUHFieGpvUyJ9.eyJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS9lbWFpbCI6ImNvb3Blci56aGFvQG5leHR0cnVja2luZy5jb20iLCJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS9yb2xlcyI6WyJST0xFX0FETUlOIiwiUk9MRV9CQUNLRU5EX0FETUlOIiwiUk9MRV9DQVBBQ0lUWSIsIlJPTEVfVVNFUiJdLCJpc3MiOiJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS8iLCJzdWIiOiJva3RhfEF1dGgwLU9rdGEtUHJvZHwwMHVrMnF3cjFnZk54S0V1dzM1NyIsImF1ZCI6WyJodHRwczovL3Byb2QtbmV4dHRydWNraW5nLnVzLmF1dGgwLmNvbS9hcGkvdjIvIiwiaHR0cHM6Ly9wcm9kLW5leHR0cnVja2luZy51cy5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNjg2NzI3NjMzLCJleHAiOjE2ODkzMTk2MzIsImF6cCI6IkU3Tk9GSGlUNnllTTBlYTg4Y2o2bHdOMVpST2xyY0d5Iiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCJ9.TVHzedJ6xo2GdCX7_EaiXJQgFMpTwW4m89QvAQuhwnSZpclJFpLic9coVf-Evm8aYHqtg6wb-0x-wrBR_QXDY0bYnzGrGNJEiH6K6RIj1GapuHts94-X4yHdnJam2Z2yq1uhryteoRTzgn-jcX8FcHNKjs6-vmJ1R8hFezHSaNzgIK3REUh4Ib_8LoQK5O4x1eDDULye8jkdPYtPBd3_bU0jytACXUz6GdHdjuu31fYaRrT5LKBR8pO57DlCq2sDEopfddXqCVDuyi0QADbmULZdN_0IkQxeGYW6HukPkmjqRsgUpaVFRKvNy_Ts5M8RQzgnyDn3XSE341f13YTJSg");

        String base_url = "https://svcs.us-west-2.prod.aws.nexttrucking.com/trips/v1/businessEvent/publish/sync";

        String basePayLout = "{\"orderId\":\"%s\",\"orderCancellationReason\":\"\"}";


        for(String orderId:as) {

            BusinessEventDTO businessEventDTO = new BusinessEventDTO();

            businessEventDTO.setPayload(String.format(basePayLout, orderId));
            businessEventDTO.setType("CANCEL_ORDER");
            businessEventDTO.setPublishedBy("64720");
            businessEventDTO.setSource("INTERNAL_USER");

            HttpEntity<String> entity = new HttpEntity<>(JSONObject.toJSONString(businessEventDTO), headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(base_url, entity, String.class);

            System.out.println(responseEntity.toString());
        }







    }

}
