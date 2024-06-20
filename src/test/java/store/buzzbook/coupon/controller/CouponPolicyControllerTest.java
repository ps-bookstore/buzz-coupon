package store.buzzbook.coupon.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import store.buzzbook.coupon.dto.coupontype.CouponTypeResponse;
import store.buzzbook.coupon.dto.coupontype.CreateCouponTypeRequest;
import store.buzzbook.coupon.entity.CouponType;
import store.buzzbook.coupon.entity.constant.CouponRange;
import store.buzzbook.coupon.service.CouponPolicyService;
import store.buzzbook.coupon.service.CouponTypeService;

@WebMvcTest(CouponPolicyController.class)
class CouponPolicyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CouponPolicyService couponPolicyService;

	@MockBean
	private CouponTypeService couponTypeService;

	private CouponTypeResponse testCouponTypeResponse;

	@BeforeEach
	void setUp() {
		CouponType testCouponType = CouponType.builder()
			.id(1)
			.name(CouponRange.BOOK)
			.build();

		testCouponTypeResponse = CouponTypeResponse.from(testCouponType);
	}

	@Test
	@DisplayName("get coupon types")
	void getCouponTypes() throws Exception {
		// given
		when(couponTypeService.getAllCouponTypes()).thenReturn(List.of(testCouponTypeResponse));

		// when & then
		mockMvc.perform(get("/api/coupons/policies/types"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id").value(testCouponTypeResponse.id()))
			.andExpect(jsonPath("$[0].name").value(testCouponTypeResponse.name().toString()));

		verify(couponTypeService).getAllCouponTypes();
	}

	@Test
	@DisplayName("create coupon type")
	void createCouponType() throws Exception {
		// given
		CreateCouponTypeRequest request = new CreateCouponTypeRequest(CouponRange.BOOK);
		when(couponTypeService.createCouponType(any())).thenReturn(testCouponTypeResponse);

		// when & then
		mockMvc.perform(post("/api/coupons/policies/types")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(testCouponTypeResponse.id()))
			.andExpect(jsonPath("$.name").value(testCouponTypeResponse.name().toString()));

		verify(couponTypeService).createCouponType(any());
	}

	@Test
	@DisplayName("create coupon type with invalid")
	void createCouponTypeInvalid() throws Exception {
		// given
		CreateCouponTypeRequest request = new CreateCouponTypeRequest(null);

		// when & then
		mockMvc.perform(post("/api/coupons/policies/types")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.status").exists());

		verify(couponTypeService, never()).createCouponType(any());
	}

	@Test
	@DisplayName("delete coupon type")
	void deleteCouponType() throws Exception {
		// given
		doNothing().when(couponTypeService).deleteCouponType(anyInt());

		// when & then
		mockMvc.perform(delete("/api/coupons/policies/types/1"))
			.andExpect(status().isNoContent());

		verify(couponTypeService).deleteCouponType(anyInt());
	}
}
