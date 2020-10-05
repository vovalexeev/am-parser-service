package com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.model.dto.WineDto;
import com.wine.to.up.am.parser.service.repository.WineRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author : Vladimir Alexeev
 * @since : 05.10.2020
 **/

@ExtendWith(MockitoExtension.class)
public class AmWineServiceImplTest {

    @Mock
    private WineRepository wineRepository;

    @InjectMocks
    private AmWineServiceImpl amWineServiceImpl;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllAmWinesTest(){
         List<WineDto> wineDtos = new CopyOnWriteArrayList<>();
         wineDtos.add(new WineDto());
         wineDtos.add(new WineDto());
         wineDtos.add(new WineDto());

        doReturn(wineDtos).when(wineRepository).findAll();

        List<WineDto> expected = amWineServiceImpl.getAllAmWines(); // exception - i think due to private fields in AmWineServiceImpl
        assertEquals(expected, wineDtos);
        verify(wineRepository, times(1)).findAll();
    }

    @Test
    public void updateWinesTest(){
    }
}