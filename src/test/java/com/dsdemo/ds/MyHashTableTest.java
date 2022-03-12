package com.dsdemo.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
@DisplayName("Given a Hashtable")
class MyHashTableTest {
	MyHashTable<String, String> map = new MyHashTable<>();

	@Nested
	@DisplayName("Add values")
	class WithValues {
		@DisplayName("test put and get")
		@Test
		void testPutAndGet() {
			map.put("name1", "value1");
			map.put("name2", "value2");
			map.put("name2", "value3");
			
			String value = map.get("name2");
			assertNotNull(value);
			assertEquals("value3", value);
		}
		
		@DisplayName("test remove")
		@Test
		void testRemove() {
			map.put("name1", "value1");
			map.put("name2", "value2");
			map.put("name3", "value3");
			
			map.remove("name2");
			String value = map.get("name2");
			assertNull(value);

		}
	}

}
