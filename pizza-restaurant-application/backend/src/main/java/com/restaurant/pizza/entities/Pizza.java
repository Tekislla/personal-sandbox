package com.restaurant.pizza.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String size;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "pizza_flavor", 
	joinColumns = { @JoinColumn(name = "pizza_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "flavor_id") })
	@JoinColumn(name = "flavor_id")
	private List<Flavor> flavor;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "edge_id")
	private Edge edge;
	private Float value;
	
	public Pizza() {
		
	}

}
