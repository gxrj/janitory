package io.github.gxrj.janitory.seed;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TableSeeder {

    @EventListener
    public void seed( ContextRefreshedEvent event ) {

    }
}