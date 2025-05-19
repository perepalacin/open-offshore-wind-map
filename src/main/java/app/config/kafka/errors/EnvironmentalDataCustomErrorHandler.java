package app.config.kafka.errors;

import app.services.EnvironmentalService;
import app.services.TurbineService;
import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentalDataCustomErrorHandler implements ConsumerAwareListenerErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentalService.class);

    @Override
    public Object handleError (Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        logger.error("Error!!!" + message.toString() + exception.toString() + consumer.toString());
        return null;
    }
}
