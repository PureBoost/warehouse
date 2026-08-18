package com.keyin.warehouse;

import com.keyin.warehouse.datastructure.OrderBST;
import com.keyin.warehouse.model.Order;
import com.keyin.warehouse.model.Product;
import com.keyin.warehouse.repository.ProductRepository;
import com.keyin.warehouse.service.ProductService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarehouseApplicationTests {

	@Test
	void inorderReturnsOrdersInPriorityOrder() {
		OrderBST bst = new OrderBST();

		bst.insert(new Order(LocalDate.now(), 7, null));
		bst.insert(new Order(LocalDate.now(), 3, null));
		bst.insert(new Order(LocalDate.now(), 9, null));
		bst.insert(new Order(LocalDate.now(), 1, null));
		bst.insert(new Order(LocalDate.now(), 5, null));

		List<Order> result = bst.inorder();

		assertEquals(5, result.size());
		assertEquals(1, result.get(0).getPriorityLevel());
		assertEquals(3, result.get(1).getPriorityLevel());
		assertEquals(5, result.get(2).getPriorityLevel());
		assertEquals(7, result.get(3).getPriorityLevel());
		assertEquals(9, result.get(4).getPriorityLevel());
	}

	@Test
	void findHighestAndLowestReturnCorrectOrders() {
		OrderBST bst = new OrderBST();

		bst.insert(new Order(LocalDate.now(), 7, null));
		bst.insert(new Order(LocalDate.now(), 3, null));
		bst.insert(new Order(LocalDate.now(), 9, null));
		bst.insert(new Order(LocalDate.now(), 1, null));

		assertEquals(9, bst.findHighest().getPriorityLevel());
		assertEquals(1, bst.findLowest().getPriorityLevel());
	}

	@Test
	void sortByPriceReturnsProductsInAscendingOrder() {
		ProductRepository productRepository = null;
		ProductService productService = new ProductService(productRepository);

		Product expensive = new Product();
		expensive.setName("Monitor");
		expensive.setPrice(199.99);
		expensive.setStock(10);

		Product cheap = new Product();
		cheap.setName("Mouse");
		cheap.setPrice(19.99);
		cheap.setStock(50);

		Product middle = new Product();
		middle.setName("Keyboard");
		middle.setPrice(49.99);
		middle.setStock(25);

		List<Product> products = new ArrayList<>();
		products.add(expensive);
		products.add(cheap);
		products.add(middle);

		List<Product> result = productService.sortByPrice(products);

		assertEquals("Mouse", result.get(0).getName());
		assertEquals("Keyboard", result.get(1).getName());
		assertEquals("Monitor", result.get(2).getName());
	}
}