package com.project.otholla;

import com.project.otholla.service.WebHookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
public class SignatureTests {

    @Test
    public void test() {

        try {
            String secretKey = "U-CWaZHflG80f5KKMn__B";


            String text = "{\"notification_type\":\"payment\",\"settings\":{\"project_id\":132105,\"merchant_id\":202724},\"purchase\":{\"checkout\":{\"currency\":\"USD\",\"amount\":1111},\"total\":{\"currency\":\"USD\",\"amount\":1111}},\"user\":{\"id\":\"ncsoft\"},\"transaction\":{\"id\":112233,\"external_id\":\"11\",\"dry_run\":1,\"agreement\":204745,\"payment_date\":\"2021-05-22T09:17:18+03:00\",\"payment_method\":26},\"payment_details\":{\"payment\":{\"currency\":\"USD\",\"amount\":1111},\"payment_method_sum\":{\"currency\":\"USD\",\"amount\":1111},\"xsolla_balance_sum\":{\"currency\":\"USD\",\"amount\":0},\"payout\":{\"currency\":\"USD\",\"amount\":990.9},\"xsolla_fee\":{\"currency\":\"USD\",\"amount\":55.55},\"payment_method_fee\":{\"currency\":\"USD\",\"amount\":0},\"vat\":{\"currency\":\"USD\",\"amount\":0,\"percent\":0},\"sales_tax\":{\"currency\":\"USD\",\"amount\":0,\"percent\":0},\"direct_wht\":{\"currency\":\"USD\",\"amount\":0,\"percent\":0},\"payout_currency_rate\":\"1\",\"repatriation_commission\":{\"currency\":\"USD\",\"amount\":64.55}}}";
            String signature = "21cc1e8099d597d2c0c551f2b403a7b1c54ab69d";

            String hashed = WebHookService.sha1(text + secretKey);

            log.info("{}", text);
            log.info("{}", hashed);
            log.info("{}", signature.equals(hashed));
        } catch (Exception e) {
            log.error("{}", e);
        }
    }
}
