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
}
