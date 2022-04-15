package org.keelfy.eljur.api.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    private void publishEvent(ApplicationEvent event) {
        log.trace("Publishing {}", event.getClass().getSimpleName());
        eventPublisher.publishEvent(event);
    }

}
