package com.emagia.ach.controller;

import com.emagia.ach.dto.FileHeaderDto;
import com.emagia.ach.service.Achfileservice;
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
public class ACHFileControllerTest {
    private static final String ENDPOINT_URL = "/api/ach/ctx";

    @InjectMocks
    private ACHFileController achFileController;

    @Mock
    private Achfileservice achfileservice;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(achFileController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {

        Mockito.when(achfileservice.createOSStringAchCTXDoc())
                .thenReturn("success");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(achfileservice, Mockito.times(1)).createOSStringAchCTXDoc();
        Mockito.verifyNoMoreInteractions(achfileservice);
    }

   /* @Test
    public void getById() throws Exception {
        Mockito.when(achfileservice.findById(Long.valueOf(ArgumentMatchers.anyLong())))
                .thenReturn(FileHeaderBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(achfileservice, Mockito.times(1)).findById(Long.valueOf(1L));
        Mockito.verifyNoMoreInteractions(achfileservice);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(achfileservice.save(ArgumentMatchers.any(FileHeaderDto.class)))
                .thenReturn(FileHeaderBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FileHeaderBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(achfileservice, Mockito.times(1)).save(ArgumentMatchers.any(FileHeaderDto.class));
        Mockito.verifyNoMoreInteractions(achfileservice);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(achfileservice.update(ArgumentMatchers.any(), Long.valueOf(ArgumentMatchers.anyLong())))
                .thenReturn(FileHeaderBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FileHeaderBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(achfileservice, Mockito.times(1))
                .update(ArgumentMatchers.any(FileHeaderDto.class), Long.valueOf(ArgumentMatchers.anyLong()));
        Mockito.verifyNoMoreInteractions(achfileservice);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(achfileservice).deleteById(Long.valueOf(ArgumentMatchers.anyLong()));

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FileHeaderBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(achfileservice, Mockito.times(1))
                .deleteById(Long.valueOf(Mockito.anyLong()));
        Mockito.verifyNoMoreInteractions(achfileservice);
    }*/
}
