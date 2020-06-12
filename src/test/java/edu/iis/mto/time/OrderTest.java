package edu.iis.mto.time;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import org.joda.time.Duration;
import org.joda.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

    private Order order;

    @Mock
    private IClock clock;

    @BeforeEach
    public void setUp() {
        order = new Order(clock);
    }

    @Test
    public void callConfirmJustAfterSubmitTest() {
        Instant actualTime = Instant.now();
        Instant laterTime = Instant.now();
        when(clock.getActualTime()).thenReturn(actualTime)
                                   .thenReturn(laterTime);
        order.submit();
        assertDoesNotThrow(order::confirm);
    }

    @Test
    public void callConfirm1SecondAfterSubmitTest() {
        Instant actualTime = Instant.now();
        Instant laterTime = Instant.now()
                                   .plus(Duration.standardSeconds(1));
        when(clock.getActualTime()).thenReturn(actualTime)
                                   .thenReturn(laterTime);
        order.submit();
        assertDoesNotThrow(order::confirm);
    }

    @Test
    public void callConfirm12HoursAfterSubmitTest() {
        Instant actualTime = Instant.now();
        Instant laterTime = Instant.now()
                                   .plus(Duration.standardHours(12));
        when(clock.getActualTime()).thenReturn(actualTime)
                                   .thenReturn(laterTime);
        order.submit();
        assertDoesNotThrow(order::confirm);
    }
}
