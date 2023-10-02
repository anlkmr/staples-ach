package com.emagia.ach.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.emagia.ach.controller.CustomUtils;
import com.emagia.ach.controller.EntryDetailController;
import com.emagia.ach.dto.EntryDetailDto;
import com.emagia.ach.entity.EntryDetailEntity;
import com.emagia.ach.mapper.EntryDetailMapper;
import com.emagia.ach.service.EntryDetailService;
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
public class EntryDetailControllerTest {
    private static final String ENDPOINT_URL = "/api/entry-detail";

    @InjectMocks
    private EntryDetailController entrydetailController;

    @Mock
    private EntryDetailService entrydetailService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(entrydetailController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<EntryDetailDto> page = new PageImpl<>(Collections.singletonList(EntryDetailBuilder.getDto()));

        Mockito.when(entrydetailService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(entrydetailService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(entrydetailService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(entrydetailService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(EntryDetailBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(entrydetailService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(entrydetailService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(entrydetailService.save(ArgumentMatchers.any(EntryDetailDto.class)))
                .thenReturn(EntryDetailBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EntryDetailBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(entrydetailService, Mockito.times(1)).save(ArgumentMatchers.any(EntryDetailDto.class));
        Mockito.verifyNoMoreInteractions(entrydetailService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(entrydetailService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(EntryDetailBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EntryDetailBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(entrydetailService, Mockito.times(1))
                .update(ArgumentMatchers.any(EntryDetailDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(entrydetailService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(entrydetailService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EntryDetailBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(entrydetailService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(entrydetailService);
    }
}
