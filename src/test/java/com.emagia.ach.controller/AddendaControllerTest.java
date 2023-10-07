package com.emagia.ach.controller;

import com.emagia.ach.dto.AddendaDto;
import com.emagia.ach.service.AddendaService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class AddendaControllerTest {
    private static final String ENDPOINT_URL = "/api/addenda";

    @InjectMocks
    private AddendaController addendaController;

    @Mock
    private AddendaService addendaService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(addendaController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<AddendaDto> page = new PageImpl<>(Collections.singletonList(AddendaBuilder.getDto()));

        Mockito.when(addendaService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(addendaService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(addendaService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(addendaService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(AddendaBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(addendaService, Mockito.times(1)).findById(1);
        Mockito.verifyNoMoreInteractions(addendaService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(addendaService.save(ArgumentMatchers.any(AddendaDto.class)))
                .thenReturn(AddendaBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(AddendaBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(addendaService, Mockito.times(1)).save(ArgumentMatchers.any(AddendaDto.class));
        Mockito.verifyNoMoreInteractions(addendaService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(addendaService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(AddendaBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(AddendaBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(addendaService, Mockito.times(1))
                .update(ArgumentMatchers.any(AddendaDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(addendaService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(addendaService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(AddendaBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(addendaService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(addendaService);
    }
}
