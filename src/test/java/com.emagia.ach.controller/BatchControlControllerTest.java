package com.emagia.ach.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.emagia.ach.controller.BatchControlController;
import com.emagia.ach.controller.CustomUtils;
import com.emagia.ach.dto.BatchControlDto;
import com.emagia.ach.entity.BatchControlEntity;
import com.emagia.ach.mapper.BatchControlMapper;
import com.emagia.ach.service.BatchControlService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class BatchControlControllerTest {
    private static final String ENDPOINT_URL = "/api/batch-control";

    @InjectMocks
    private BatchControlController batchcontrolController;

    @Mock
    private BatchControlService batchcontrolService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(batchcontrolController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<BatchControlDto> page = new PageImpl<>(Collections.singletonList(BatchControlBuilder.getDto()));

        Mockito.when(batchcontrolService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(batchcontrolService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(batchcontrolService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(batchcontrolService.findById(ArgumentMatchers.any()))
                .thenReturn(BatchControlBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(batchcontrolService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(batchcontrolService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(batchcontrolService.save(ArgumentMatchers.any(BatchControlDto.class)))
                .thenReturn(BatchControlBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(BatchControlBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(batchcontrolService, Mockito.times(1)).save(ArgumentMatchers.any(BatchControlDto.class));
        Mockito.verifyNoMoreInteractions(batchcontrolService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(batchcontrolService.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(BatchControlBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(BatchControlBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(batchcontrolService, Mockito.times(1))
                .update(ArgumentMatchers.any(BatchControlDto.class), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(batchcontrolService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(batchcontrolService).deleteById(ArgumentMatchers.any());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(BatchControlBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(batchcontrolService, Mockito.times(1))
                .deleteById(Mockito.any());
        Mockito.verifyNoMoreInteractions(batchcontrolService);
    }
}
