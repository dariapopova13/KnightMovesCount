package com.popova.fix.jobtest.service;

import com.popova.fix.jobtest.model.Node;
import com.popova.fix.jobtest.utils.AppUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KnightMovementServiceImplTest {

    @Autowired
    KnightMovementService knightMovementService;
    @Autowired
    AppUtils appUtils;

    @Test
    public void countMovements() {
        int width = 8, height = 8;
        int expected = 0;
        int actual = knightMovementService.countMovements(width, height, "C5", "C5");
        Assert.assertEquals(expected, actual);

        expected = 6;
        actual = knightMovementService.countMovements(width, height, "A8", "H1");
        Assert.assertEquals(expected, actual);

        expected = 3;
        actual = knightMovementService.countMovements(width, height, "C4", "D4");
        Assert.assertEquals(actual, expected);

        expected = -1;
        actual = knightMovementService.countMovements(1, height, "A1", "A8");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void countMovementsWithPathRepresentation() {
        int width = 8, height = 8;
        Node expectedNode = knightMovementService.countMovementsWithPath(width, height, "C4", "D4");
        String expected = appUtils.getStringNodeMovesRepresentation(expectedNode, new StringBuilder("")).toString();
        String actual = "C4 -> E3 -> C2 -> D4";
        Assert.assertEquals(expected, actual);
    }
}
