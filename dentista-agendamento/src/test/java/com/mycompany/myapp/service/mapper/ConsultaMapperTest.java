package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsultaMapperTest {

    private ConsultaMapper consultaMapper;

    @BeforeEach
    public void setUp() {
        consultaMapper = new ConsultaMapperImpl();
    }
}
