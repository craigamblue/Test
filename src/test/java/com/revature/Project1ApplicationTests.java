package com.revature;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.revature.models.Beer;
import com.revature.repositories.BeerRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import com.revature.services.BeerService;

import com.revature.services.UserService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class Project1ApplicationTests {

	private static List<Beer> beers;
	private static BeerRepository mockDao;
	private static BeerService bs;
	
	@BeforeAll
	public static void setup() {
		mockDao = mock(BeerRepository.class);
		bs = new BeerService(mockDao);
		beers = new ArrayList<>();

		beers.add(new Beer(1, "Beer 1", "Type 1", 0.01, 1));
//		beers.add(new Beer(2, "Beer 2", 1.11, "Type 2"));
//		beers.add(new Beer(3, "Beer 3", 2.22, "Type 3"));
//		beers.add(new Beer(4, "Beer 4", 3.33, "Type 4"));
	}
	
	@Test
	public void testGetAll() {
		when(mockDao.findAll()).thenReturn(beers);
		System.out.println("Test all" + beers);
		System.out.println(bs.getAllBeers());		
		assertDoesNotThrow(() -> {
			assertEquals(beers, bs.getAllBeers());

		});

	} 
	
	@Test
	public void testGetBeerbyId() {
		when(mockDao.findById(1)).thenReturn(Optional.of(beers.get(0)));
		System.out.println("Test 2" + beers);
		System.out.println(bs.getBeerbyId(1));	
		assertDoesNotThrow(() -> {
			assertEquals(beers.get(0), bs.getBeerbyId(1));
		});
	}


	@Test
	void contextLoads() {
	}

//	@Test
//	public void testGetAll() {
//		beers = br.findAll();
//		assertEquals(beers, bs.getAllBeers());
//	}

}
