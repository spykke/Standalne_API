package com.spykke.internal.order;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spykke.internal.commondb.entity.Order;

@RestController
public class OrderController {
	
	@Autowired
	OrderServiceImpl orderService;

	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		return orderService.findAll();
	}
	
	@GetMapping("/orders/id/{id}")
	public Order getOrder(@PathVariable long id){
		return orderService.findById(id);
	}
	
	@PostMapping("/orders/")
	public ResponseEntity<Void> createOrder(@RequestBody Order order){
		Order createdOrder = orderService.save(order);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdOrder.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/orders/id/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable long id,
			@RequestBody Order order){
		Order updatedOrder = orderService.save(order);
		
		return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/orders/id/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable long id){
		orderService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
