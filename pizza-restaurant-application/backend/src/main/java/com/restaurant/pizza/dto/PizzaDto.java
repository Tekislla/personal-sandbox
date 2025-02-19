package com.restaurant.pizza.dto;

import java.util.List;

import lombok.Data;

@Data
public class PizzaDto {
	private String size;
	private List<FlavorDto> flavor;
	private EdgeDto edge;
	private Float value;

}
