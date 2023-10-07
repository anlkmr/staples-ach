package com.emagia.ach.controller;

import com.emagia.ach.dto.BatchHeaderDto;
import com.emagia.ach.service.BatchHeaderService;
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
public class BatchHeaderControllerTest {
    private static final String ENDPOINT_URL = "/api/batch-header";

    @InjectMocks
    private BatchHeaderController batchheaderController;

    @Mock
    private BatchHeaderService batchheaderService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(batchheaderController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<BatchHeaderDto> page = new PageImpl<>(Collections.singletonList(BatchHeaderBuilder.getDto()));

        Mockito.when(batchheaderService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(batchheaderService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(batchheaderService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(batchheaderService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(BatchHeaderBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(batchheaderService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(batchheaderService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(batchheaderService.save(ArgumentMatchers.any(BatchHeaderDto.class)))
                .thenReturn(BatchHeaderBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(BatchHeaderBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(batchheaderService, Mockito.times(1)).save(ArgumentMatchers.any(BatchHeaderDto.class));
        Mockito.verifyNoMoreInteractions(batchheaderService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(batchheaderService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(BatchHeaderBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(BatchHeaderBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(batchheaderService, Mockito.times(1))
                .update(ArgumentMatchers.any(BatchHeaderDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(batchheaderService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(batchheaderService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(BatchHeaderBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(batchheaderService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(batchheaderService);
    }
}
