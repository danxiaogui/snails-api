package com.kuzank.snails.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/14
 */
@Component
public class DBInit implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger logger = LoggerFactory.getLogger(DBInit.class);

    @Autowired
    List<Initialize> initializes;

    /**
     * 初始化数据库
     *
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            initializes.forEach(initialize -> {
                try {
                    initialize.initialize();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ApplicationReadyEvent : false to init DB !");
            System.exit(0);
        }
        logger.info("ApplicationReadyEvent : init DB successful !");
    }

}
