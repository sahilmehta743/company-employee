package com.crud.companyemployee.resource;

import com.crud.companyemployee.dto.EmployeeDto;
import com.crud.companyemployee.service.EmployeeDetailService;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeDetailResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeDetailService employeeDetailService;

    @Test
    public void shouldReturnEmployeeDataSuccessfully() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        Gson gson = new Gson();
        final String employeeJson = gson.toJson(employeeDto);
        when(employeeDetailService.getEmployeeData(1L)).thenReturn(employeeDto);

        this.mockMvc.perform(get("/employee/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(employeeJson));

        verify(employeeDetailService, times(1)).getEmployeeData(1L);
    }
}
