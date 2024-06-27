package store.buzzbook.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CouponapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponapiApplication.class, args);
	}

}
