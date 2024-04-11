package com.rui.spzx.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "搜索条件实体类")
public class CategoryBrandDto {

	@Schema(description = "品牌id")
	private Long brandId;

	@Schema(description = "分类id")
	private Long categoryId;
}