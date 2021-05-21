package com.project.otholla.service;


import com.project.otholla.dto.merchants.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    @Qualifier("restTemplateHttpComponentsInsecure")
    RestTemplate restTemplate;

    String merchant_id = "202724";
    String api_key = "202724:0162dad822d04190f578080c2e4869e1";


    public void authenticationSample() {

        log.info(" SAMPLE SERVICE ");

        String url = String.format("https://api.xsolla.com/merchant/v2/merchants/%s/events/messages", merchant_id);

        String authorization_basic_key = Base64.encodeBase64String(api_key.getBytes());
        log.info(">>>>>>>>>>>; {}", authorization_basic_key);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(authorization_basic_key);

        HttpEntity requestEntity = new HttpEntity(headers);

        restTemplate.exchange(url, HttpMethod.GET, requestEntity, Token.class);

    }

    public String token(String myname, String currency, String amount, String country) {

        log.info(" GET TOKEN ");

        String url = String.format("https://api.xsolla.com/merchant/v2/merchants/%s/token", merchant_id);

        String authorization_basic_key = Base64.encodeBase64String(api_key.getBytes());
        log.info(">>>>>>>>>>>; {}", authorization_basic_key);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(authorization_basic_key);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String param = "  {\n" +
                "  \"purchase\": {\n" +
                "    \"virtual_currency\": {\n" +
                "      \"quantity\": 1\n" +
                "    },\n" +
                "    \"virtual_items\": {\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"amount\": 1,\n" +
                "          \"sku\": \"SKU01\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"settings\": {\n" +
                "    \"mode\": \"sandbox\",\n" +
                "    \"currency\": \"" + currency + "\",\n" +
                "    \"language\": \"kr\",\n" +
                "    \"project_id\": 132105,\n" +
                "    \"ui\": {\n" +
                "      \"components\": {\n" +
                "        \"virtual_currency\": {\n" +
                "          \"custom_amount\": true\n" +
                "        }\n" +
                "      },\n" +
                "      \"desktop\": {\n" +
                "        \"virtual_item_list\": {\n" +
                "          \"button_with_price\": true,\n" +
                "          \"layout\": \"list\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"size\": \"medium\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"user\": {\n" +
                "    \"country\": {\n" +
                "      \"allow_modify\": true,\n" +
                "      \"value\": \"" + country + "\"\n" +
                "    },\n" +
                "    \"age\": 19,\n" +
                "    \"email\": {\n" +
                "      \"value\": \"fuga@ncsoft.com\"\n" +
                "    },\n" +
                "    \"id\": {\n" +
                "      \"value\": \"ncsoft\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"value\": \"" + "gavinkim" + "\"\n" +
                "    }\n" +
                "  }\n" +
                "}";


        HttpEntity requestEntity = new HttpEntity(param, headers);

        Token token = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Token.class).getBody();

        return token.getToken();

    }

}
