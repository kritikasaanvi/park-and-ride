
package com.parkandride.park_and_ride_mvp;



import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:mysql://localhost:3306/park_and_ride?useSSL=false&serverTimezone=UTC",
		"spring.datasource.username=root",
		"spring.datasource.password=2004",
		"spring.jpa.hibernate.ddl-auto=none"   // prevent accidental schema overwrite during tests
})
class ParkAndRideMvpApplicationTests {

	@Test
	void contextLoads() {
		// ✅ Passes if the Spring Boot context loads without errors
	}
}
