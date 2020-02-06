package co.simplon.starwarsapi.controller;

import co.simplon.starwarsapi.model.planet.Planet;
import co.simplon.starwarsapi.repository.PlanetRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PlanetControllerTests {

    @Autowired
    MockMvc mockMvc;

//    @Autowired
//    PlanetController planetController;

    @MockBean
    PlanetRepository planetRepository;

    @Test
    public void getPlanetListTest() throws Exception {
        when(this.planetRepository.findAll()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/api/planets")).andExpect(status().isOk());
    }

    @Test
    public void getPlanetTest() throws Exception {
        when(this.planetRepository.findById(1L)).thenReturn(Optional.of(new Planet("Alderaan")));

        this.mockMvc.perform(get("/api/planets/1")).andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Alderaan"));
    }

    @Test
    public void getPlanetTestNotFound() throws Exception {
        when(this.planetRepository.findById(1L)).thenReturn(Optional.of(new Planet("Alderaan")));

        this.mockMvc.perform(get("/api/planets/2")).andExpect(status().isNotFound());
    }


    @Test
    public void createPlanetTest() throws Exception {
        when(this.planetRepository.save(any())).thenReturn(new Planet("Alcoria"));

        this.mockMvc
                .perform(post("/api/planets").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"yoloyolo\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("name").value("Alcoria"));
    }

}

//    @Test
//    public void getPlanetListTest() throws Exception {
//        when(this.planetController.getPlanetList()).thenReturn(new ArrayList<>());
//
//        this.mockMvc.perform(get("/api/planets")).andExpect(status().isOk());
//    }

//    @Test
//    public void getPlanetTest() throws Exception {
//        when(this.planetController.getPlanet(1L)).thenReturn(new Planet("Alderaan"));
//
//        this.mockMvc.perform(get("/api/planets/1")).andExpect(status().isOk())
//                .andExpect(jsonPath("name").value("Alderaan"));
//    }


//        this.mockMvc.perform(get("/api/planets/1")).andExpect(status().isOk()).andExpect(jsonPath("name").value("Paris"))
//                .andExpect(jsonPath("dptCode").value(75));
