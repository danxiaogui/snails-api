package com.kuzank.snails;

import com.kuzank.snails.util.UuidUtil;
import org.junit.jupiter.api.Test;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/7
 */
public class CommonTest {

    @Test
    public void test() {
        for (int i = 0; i < 20; i++) {
            System.out.println(UuidUtil.randomUUID());
        }
    }
}
