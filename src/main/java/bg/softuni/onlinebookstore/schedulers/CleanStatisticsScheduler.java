package bg.softuni.onlinebookstore.schedulers;

import bg.softuni.onlinebookstore.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanStatisticsScheduler {
    private final OrderService orderService;

    public CleanStatisticsScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void cleanNewOrdersCount() {
        orderService.cleanNewOrdersCount();
    }
}
