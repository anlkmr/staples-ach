package com.emagia.ach.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.emagia.ach.controller.CustomUtils;
import com.emagia.ach.controller.FileHeaderController;
import com.emagia.ach.dto.FileHeaderDto;
import com.emagia.ach.entity.FileHeaderEntity;
import com.emagia.ach.mapper.FileHeaderMapper;
import com.emagia.ach.service.FileHeaderService;
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
public class FileHeaderControllerTest {
    private static final String ENDPOINT_URL = "/api/file-header";

    @InjectMocks
    private FileHeaderController fileheaderController;

    @Mock
    private FileHeaderService fileheaderService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(fileheaderController)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<FileHeaderDto> page = new PageImpl<>(Collections.singletonList(FileHeaderBuilder.getDto()));

        Mockito.when(fileheaderService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(fileheaderService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(fileheaderService);
    }

    @Test
    public void getById() throws Exception {
        Mockito.when(fileheaderService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(FileHeaderBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));

        Mockito.verify(fileheaderService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(fileheaderService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(fileheaderService.save(ArgumentMatchers.any(FileHeaderDto.class)))
                .thenReturn(FileHeaderBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FileHeaderBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(fileheaderService, Mockito.times(1)).save(ArgumentMatchers.any(FileHeaderDto.class));
        Mockito.verifyNoMoreInteractions(fileheaderService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(fileheaderService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong()))
                .thenReturn(FileHeaderBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FileHeaderBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(fileheaderService, Mockito.times(1))
                .update(ArgumentMatchers.any(FileHeaderDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(fileheaderService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(fileheaderService).deleteById(ArgumentMatchers.anyLong());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FileHeaderBuilder.getIds())))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(fileheaderService, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(fileheaderService);
    }
}
