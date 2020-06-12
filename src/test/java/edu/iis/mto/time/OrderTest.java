package edu.iis.mto.time;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    private Instant actualTime;
    private Instant laterTime;

    @Mock
    private IClock clock;

    @BeforeEach
    public void setUp() {
        order = new Order(clock);
        actualTime = Instant.now();
    }

    @Test
    public void callConfirmJustAfterSubmitTest() {
        Instant laterTime = actualTime;
        when(clock.getActualTime()).thenReturn(actualTime)
                                   .thenReturn(laterTime);
        order.submit();
        assertDoesNotThrow(order::confirm);
    }

    @Test
    public void callConfirm1SecondAfterSubmitTest() {
        Instant laterTime = actualTime.plus(Duration.standardSeconds(1));
        when(clock.getActualTime()).thenReturn(actualTime)
                                   .thenReturn(laterTime);
        order.submit();
        assertDoesNotThrow(order::confirm);
    }

    @Test
    public void callConfirm12HoursAfterSubmitTest() {
        Instant laterTime = actualTime.plus(Duration.standardHours(12));
        when(clock.getActualTime()).thenReturn(actualTime)
                                   .thenReturn(laterTime);
        order.submit();
        assertDoesNotThrow(order::confirm);
    }

    @Test
    public void callConfirm24HoursAfterSubmitTest() {
        Instant laterTime = actualTime.plus(Duration.standardHours(24));
        when(clock.getActualTime()).thenReturn(actualTime)
                                   .thenReturn(laterTime);
        order.submit();
        assertDoesNotThrow(order::confirm);
    }

    @Test
    public void callConfirm25HoursAfterSubmitTest() {
        Instant laterTime = actualTime.plus(Duration.standardHours(25));
        when(clock.getActualTime()).thenReturn(actualTime)
                                   .thenReturn(laterTime);
        order.submit();
        assertThrows(OrderExpiredException.class, order::confirm);
    }
}
