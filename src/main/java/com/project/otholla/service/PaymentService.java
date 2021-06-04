package com.project.otholla.service;


import com.project.otholla.controller.Inventory;
import com.project.otholla.dto.merchants.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
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

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(authorization_basic_key);

        HttpEntity requestEntity = new HttpEntity(headers);

        restTemplate.exchange(url, HttpMethod.GET, requestEntity, Token.class);

    }

    public String token(String myname, String currency, String language, String amount, String country) {

        log.info(" GET TOKEN ");

        String url = String.format("https://api.xsolla.com/merchant/v2/merchants/%s/token", merchant_id);

        String authorization_basic_key = Base64.encodeBase64String(api_key.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(authorization_basic_key);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String param = "  {\n" +
                "  \"purchase\": {\n" +
                "    \"virtual_currency\": {\n" +
                "      \"quantity\": " + amount + "\n" +
                "    }\n" +
                "  },\n" +
                "  \"settings\": {\n" +
                "    \"mode\": \"sandbox\",\n" +
                "    \"currency\": \"" + currency + "\",\n" +
                "    \"language\": \"" + language + "\",\n" +
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
                "    \"email\": {\n" +
                "      \"value\": \"fuga@ncsoft.com\"\n" +
                "    },\n" +
                "    \"id\": {\n" +
                "      \"value\": \"ncsoft\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"value\": \"" + myname + "\"\n" +
                "    }\n" +
                "  }\n" +
                "}";


        HttpEntity requestEntity = new HttpEntity(param, headers);

        Token token = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Token.class).getBody();

        return token.getToken();

    }


    public static void main(String[] args) {
        String authorization_basic_key = Base64.encodeBase64String("926e2ffdcf054c5f722ba63cd5312c98".getBytes());

        System.out.println(authorization_basic_key);

    }

    public Items getItemList() {

        String url = "https://store.xsolla.com/api/v2/project/132105/items/virtual_items";

//        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth(authorization_basic_key);

//        HttpEntity requestEntity = new HttpEntity(headers);

        ResponseEntity<Items> items = restTemplate.exchange(url, HttpMethod.GET, null, Items.class);


        System.out.println(items.getBody());

        return items.getBody();
    }

    public String getCommerceToken(String userId) {
        String url = String.format("https://api.xsolla.com/merchant/v2/merchants/%s/token", merchant_id);

        String authorization_basic_key = Base64.encodeBase64String(api_key.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(authorization_basic_key);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String param = "  {\n" +
                "  \"settings\": {\n" +
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
                "      \"value\": \"US\"\n" +
                "    },\n" +
                "    \"email\": {\n" +
                "      \"value\": \"fuga@ncsoft.com\"\n" +
                "    },\n" +
                "    \"id\": {\n" +
                "      \"value\": \"ncsoftfuga\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"value\": \"fugaman\"\n" +
                "    }\n" +
                "  }\n" +
                "}";


        HttpEntity requestEntity = new HttpEntity(param, headers);

        Token token = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Token.class).getBody();

        return token.getToken();
    }

    public Token purchaseItem(String sku, String token) {
        String url = "https://store.xsolla.com/api/v2/project/132105/payment/item/" + sku;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        CommercePayment commercePayment = new CommercePayment();
        Settings settings = new Settings();

        commercePayment.setSettings(settings);
        HttpEntity requestEntity = new HttpEntity(commercePayment, headers);

        Token token1 = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Token.class).getBody();

        return token1;
    }

    public Inventory inventory(String token) {
        String url = "https://store.xsolla.com/api/v2/project/132105/user/inventory/items";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        CommercePayment commercePayment = new CommercePayment();
        HttpEntity requestEntity = new HttpEntity(commercePayment, headers);

        Inventory inventory = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Inventory.class).getBody();

        return inventory;
    }

    public void itemConsume(String sku, String token) {
        String url = "https://store.xsolla.com/api/v2/project/132105/user/inventory/item/consume";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String param = "{\"sku\":\"" + sku + "\", \"quantity\": 1, \"instance_id\": null}";
        HttpEntity requestEntity = new HttpEntity(param, headers);

        restTemplate.exchange(url, HttpMethod.POST, requestEntity, Inventory.class).getBody();

    }
}
