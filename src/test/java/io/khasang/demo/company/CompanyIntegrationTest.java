package io.khasang.demo.company;

import io.khasang.moika.entity.Company;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CompanyIntegrationTest {

    @Ignore
    @Test
    public void createCompany() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        Company company = new Company();
        company.setName("Рога и копыта");
        company.setDescription("Свежее мясо");

        long id = 10L;

        HttpEntity<Company> httpEntity = new HttpEntity<>(company, headers);
        RestTemplate restTemplate = new RestTemplate();
        Company result = restTemplate.exchange
                ("http://localhost:8080/company/add/{id}",
                        HttpMethod.POST,
                        httpEntity,
                        Company.class,
                        id)
                .getBody();

        Assert.assertNotNull(result);
        Assert.assertEquals("Рога и копыта", result.getName());
        Assert.assertNotNull(result.getId());

        ResponseEntity<Company> responseEntity = restTemplate.exchange(
                "http://localhost:8080/company/{id}",
                HttpMethod.GET,
                null,
                Company.class,
                result.getId()
        );
        Company resultCompany = responseEntity.getBody();
        Assert.assertNotNull(resultCompany);
        Assert.assertEquals(resultCompany.getName(), result.getName());

    }

}
