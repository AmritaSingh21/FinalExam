package com.example.finalloanproject.web;

import com.example.finalloanproject.entities.Loantable;
import com.example.finalloanproject.repositories.LoantableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
class LoantableControllerTest {

    Loantable client;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    View mockView;

    @Mock
    LoantableRepository repo;
    @InjectMocks
    LoantableController controller;

    @BeforeEach
    void setup() throws ParseException {
        client = new Loantable();
        client.setClientno(100L);
        client.setClientname("Amrita");
        client.setLoanamount(1000.0);
        client.setYears(4);
        client.setLoantype("Business");
        MockitoAnnotations.openMocks(this);
        mockMvc = standaloneSetup(controller).setSingleView(mockView).build();
    }

    @Test
    void getLoantableList() throws Exception {
        List<Loantable> list = new ArrayList<Loantable>();
        list.add(client);
        list.add(client);

        when(repo.findAll()).thenReturn(list);
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("list", list))
                .andExpect(view().name("loantable"))
                .andExpect(model().attribute("list", hasSize(2)));

        verify(repo, times(1)).findAll();
        verifyNoMoreInteractions(repo);
    }

    @Test
    void delete() {
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(repo).deleteById(idCapture.capture());
        repo.deleteById(1L);
        assertEquals(1L, idCapture.getValue());
        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    void addLoan() {
        when(repo.save(client)).thenReturn(client);
        repo.save(client);
        verify(repo, times(1)).save(client);
    }
}