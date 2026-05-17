package infsus.SGF.controller;

import infsus.SGF.Controller.FestivalController;
import infsus.SGF.DTO.FestivalDTO;
import infsus.SGF.Model.Mapa;
import infsus.SGF.Service.FestivalService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FestivalController.class)
@AutoConfigureMockMvc(addFilters = false)

public class FestivalControllerTest {

    @Autowired
    FestivalController festivalController;

    @MockBean
    FestivalService festivalService;

    @Autowired
    private MockMvc mockMvc;

    private static FestivalDTO exampleFestivalDto() {
        FestivalDTO dto = new FestivalDTO();
        dto.setNazivFestivala("Ultra Festival");
        dto.setDobnaSkupina(18);
        dto.setLokacijaFestivala("Split");
        dto.setMapaID(1L);
        dto.setVrijemeKraja(LocalDateTime.of(2026, 7, 1, 10, 0));
        dto.setVrijemePocetka(LocalDateTime.of(2026, 7, 5, 10, 0));

        return dto;
    }

    @Test
    void getAll_shouldReturnList() throws Exception {

        Mockito.when(festivalService.fetchAll())
                .thenReturn(List.of(exampleFestivalDto()));

        mockMvc.perform(get("/festival/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getById_shouldReturnItem() throws Exception {

        Mockito.when(festivalService.fetchFestivalByID(1L))
                .thenReturn(exampleFestivalDto());

        mockMvc.perform(get("/festival/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nazivFestivala").value("Ultra Festival"))
                .andExpect(jsonPath("$.dobnaSkupina").value(18))
                .andExpect(jsonPath("$.lokacijaFestivala").value("Split"))
                .andExpect(jsonPath("$.mapaID").value(1L));
    }

    @Test
    void create_shouldReturnCreated() throws Exception {

        Mockito.when(festivalService.createFestival(any()))
                .thenReturn(exampleFestivalDto());

        mockMvc.perform(post("/festival/create-festival")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {"id":1}
            """))
                .andExpect(status().isOk());
    }

    @Test
    void update_shouldWork() throws Exception {

        Mockito.when(festivalService.updateFestival( any()))
                .thenReturn(exampleFestivalDto());

        mockMvc.perform(patch("/festival/update-festival")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {"id":1}
            """))
                .andExpect(status().isOk());
    }

    @Test
    void delete_shouldWork() throws Exception {

        Mockito.when(festivalService.deleteFestivalByID(1L))
                .thenReturn(exampleFestivalDto());

        mockMvc.perform(delete("/festival/delete/1"))
                .andExpect(status().isOk());
    }

}
