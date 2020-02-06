package co.simplon.starwarsapi;

import co.simplon.starwarsapi.model.planet.Planet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Cette classe permet de lancer des tests d'intégration

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JavaPlanetSpringTestApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPlanetList() {
        // When retrieving planets from /api/planets
        List<?> planets = this.restTemplate.getForObject("/api/planets", List.class);

        // Then a non null list should be returned
        assertThat(planets).isNotNull();
    }

    @Test
    public void getPlanet() {
        // When retrieving an existing planet by its id
        ResponseEntity<Planet> responseEntity = this.restTemplate.getForEntity("/api/planets/{planetId}", Planet.class, 1);
        Planet alderaan = responseEntity.getBody();

        // Then OK status code should be sent back and
        // the planet should be returned and should be filled with its attributes
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(alderaan.getName()).isEqualTo("Alderaan");
        assertThat(alderaan.getRotationPeriod()).isEqualTo(24);
    }


}
