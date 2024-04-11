package com.rui.spzx.model.entity.product;

import com.rui.spzx.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "分类品牌实体类")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBrand extends BaseEntity {

	@Schema(description = "品牌id")
	private Long brandId;

	@Schema(description = "分类id")
	private Long categoryId;

	@Schema(description = "分类名称" , required = false)
	private String categoryName;

	@Schema(description = "品牌名称" , required = false)
	private String brandName;

	@Schema(description = "品牌logo" , required = false)
	private String logo;

}