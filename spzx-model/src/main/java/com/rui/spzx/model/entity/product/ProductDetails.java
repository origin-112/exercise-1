package com.rui.spzx.model.entity.product;

import com.rui.spzx.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}