package org.example;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

public class TestData {

    public static void main(String[] args) throws Exception {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(5000);
        executor.setThreadNamePrefix("auto-jobbing-thread-pool-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        List<String> as = FileUtils.readLines(new File("loadids.txt"), "UTF-8");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "curl/7.58.0");

        //test
        headers.add("Authorization",
            "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IksyM0JPcUFYNHJRRVdRVXhFV3N5TyJ9.eyJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS9lbWFpbCI6ImNvb3Blci56aGFvQG5leHR0cnVja2luZy5jb20iLCJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS9yb2xlcyI6WyJST0xFX0NTIiwiUk9MRV9GSU5BTkNFIiwiUk9MRV9BVExBU19BUCIsIlJPTEVfVVNFUiIsIlJPTEVfQkFDS0VORF9BRE1JTiIsIlJPTEVfRE9DX1JFVklFV0VSIiwiUk9MRV9BVExBU19BUiIsIlJPTEVfQ0FQQUNJVFkiLCJST0xFX0FETUlOIl0sImh0dHBzOi8vYXV0aC5uZXh0dHJ1Y2tpbmcuY29tL3RlbmFudElkIjoiMjgwYzdhNjYtZDE5ZS00N2YwLWI0YmMtOTI1YTFiZWI4ZGU0IiwiaXNzIjoiaHR0cHM6Ly9hdXRoLnRlc3QubmV4dHRydWNraW5nLmNvbS8iLCJzdWIiOiJhdXRoMHw2MzFhNTg1NTAzMjRiZTRmYmRiMzA2MGQiLCJhdWQiOlsiaHR0cHM6Ly90ZXN0LW5leHR0cnVja2luZy51cy5hdXRoMC5jb20vYXBpL3YyLyIsImh0dHBzOi8vdGVzdC1uZXh0dHJ1Y2tpbmcudXMuYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTY5MDI3NDQxNCwiZXhwIjoxNjkyODY2NDE0LCJhenAiOiJXTDNzQ1dQRDhvQkZQbmo1WUJNbHJFdlZXOGVXa3BzayIsInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwifQ.Ei-Hh2lXEuqP4Fv7A_gveY1Y44Y267kkzCVtw1sapcoRA0dfANLe6fYDpuq-QKeMSwR9DLp3GDPz5mcHSFtjS_q-cn25jq-uLsk3wJFWiw0w-bibnDRR0uDEJedFA4jilU4I9-_1X7oUhW5sE0nXCg3XkCJ4oFiKZPGftoVbQHwjNgspzU24FvdwDvlHu_ktPk6E-kQDtGO7IKG3VtyW9qLQLG40WNhNsTwARVfV9uc5NzpfDCfrGNMGxyPkNBv3O7qMQD-T8zKK_3_20MLzPVQgfQZcmmwm4b0BUAtimEuKGAw84YpaXDQgya-dxTkPzdEutRGP-r0q54ECxRX-2g");

        //demo
//        headers.add("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IktrbHgtTDNXRmVWcnFKNWdYRGVNcyJ9.eyJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS9lbWFpbCI6ImNvb3Blci56aGFvK2RlbW9AbmV4dHRydWNraW5nLmNvbSIsImh0dHBzOi8vYXV0aC5uZXh0dHJ1Y2tpbmcuY29tL3JvbGVzIjpbIlJPTEVfQURNSU4iLCJST0xFX0FUTEFTX0FQIiwiUk9MRV9BVExBU19BUiIsIlJPTEVfQkFDS0VORF9BRE1JTiIsIlJPTEVfQ0FQQUNJVFkiLCJST0xFX0NTIiwiUk9MRV9ET0NfUkVWSUVXRVIiLCJST0xFX0ZJTkFOQ0UiLCJST0xFX1VTRVIiXSwiaHR0cHM6Ly9hdXRoLm5leHR0cnVja2luZy5jb20vdGVuYW50SWQiOiJlYjhlZGI0ZC1iMjRjLTQyNzctODk0Ny01YTJjYzgxYmFmNDciLCJpc3MiOiJodHRwczovL2F1dGguZGVtby5uZXh0dHJ1Y2tpbmcuY29tLyIsInN1YiI6ImF1dGgwfDYzNDA5NTNlYzI5OWU3ZTA4ZDUxYmQ1YSIsImF1ZCI6WyJodHRwczovL2RlbW8tbmV4dHRydWNraW5nLnVzLmF1dGgwLmNvbS9hcGkvdjIvIiwiaHR0cHM6Ly9kZW1vLW5leHR0cnVja2luZy51cy5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNjg2NzIzNTY4LCJleHAiOjE2ODkzMTU1NjgsImF6cCI6InhBZ3dXSXdwQWdQUWZuNll0bWNmak5uM0Nua1dWTGRjIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCJ9.NK9DG1V_g_XBmn8HnPi5MJoWAt78D7g_FBQsFAgZJJjJOxeu15UKqvqONz0ydggDMorYRYHFPhNJ48bHcfaeW5z9a8pYCGHv2SD23Mg7rjjlbpzsJj5pT-9ZJ767BEfS_Lq3Refc8qL4C5HLfIIQ-PbTGggMD2js-eRZKtuDPLJm_EUE3Q_Ki16Ws03T4EOqM8KROB7mOuY2MMWf6TJu_Gs7ZWcjQbNOUfaY98nTl9Sr7pUyGaRsQFv_cvV12P5ErB2u79meKLS8W2E2q0vyRcyugdOvKjHr9n6quvtT5U1aJ4C77m3ZzG5Et6EKUSzgqHMoTRNHYLLXTtCq8BzYZQ");

        //prod
//        headers.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InlhaUl0anBrOURqN19EUHFieGpvUyJ9.eyJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS9lbWFpbCI6ImNvb3Blci56aGFvQG5leHR0cnVja2luZy5jb20iLCJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS9yb2xlcyI6WyJST0xFX0FETUlOIiwiUk9MRV9CQUNLRU5EX0FETUlOIiwiUk9MRV9DQVBBQ0lUWSIsIlJPTEVfVVNFUiJdLCJpc3MiOiJodHRwczovL2F1dGgubmV4dHRydWNraW5nLmNvbS8iLCJzdWIiOiJva3RhfEF1dGgwLU9rdGEtUHJvZHwwMHVrMnF3cjFnZk54S0V1dzM1NyIsImF1ZCI6WyJodHRwczovL3Byb2QtbmV4dHRydWNraW5nLnVzLmF1dGgwLmNvbS9hcGkvdjIvIiwiaHR0cHM6Ly9wcm9kLW5leHR0cnVja2luZy51cy5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNjkwMzMwNDQ3LCJleHAiOjE2OTI5MjI0NDYsImF6cCI6IkU3Tk9GSGlUNnllTTBlYTg4Y2o2bHdOMVpST2xyY0d5Iiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCJ9.SPMUCTAEBNx1hb6RAIllZaHErS0uo2giImF6UNP2HmWJIs7oe268KaCZW6HO2AoVLgHvR2kjPyZ-rkm87nRFi5LCDIWlgpCGXBB_UlhpCTsS9DVFaZoZCHo9tdg4W1cuscdf59ZZNEGUxp2k3PSQyXU23qJhFrSbOIZ-URVE32Gkoj1sHIj0yOdgbnSrYcKJvZwZRMUJq5HqeGP-qZT2Qnu4-D-CERLtSpuMmV4ibT6lb-rZ-lkxVFmSd6yJbhaKHZs7oD6c29X2RLt5JqzJPAwO2xDNDX5BIttmBEOR1ISX2HKWJFmbx47dqvJNfwIdSklNqdQTvLm1pRqEHtJKbw");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String base_url = "https://svcs.us-west-2.test.aws.nexttrucking.com/trips/v1/auto/jobbing/move/sync?loadId=";

        for (String line : as) {
//            System.out.println(line);
//            System.out.println(restTemplate.exchange(base_url + line, HttpMethod.GET, entity, String.class)
//                .getBody());

            TimeUnit.MILLISECONDS.sleep(50);

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                         System.out.println(line);
                        System.out.println(restTemplate.exchange(base_url + line, HttpMethod.GET, entity, String.class)
                            .getBody());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

}
