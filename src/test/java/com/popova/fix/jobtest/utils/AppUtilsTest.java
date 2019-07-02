package com.popova.fix.jobtest.utils;

import com.popova.fix.jobtest.model.Coordinate;
import com.popova.fix.jobtest.model.Node;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppUtilsTest {

    @Autowired
    AppUtils appUtils;

    @Test
    public void getXYCoordinates() {
        Coordinate actual = new Coordinate(3, 34);
        Coordinate expected = appUtils.getXYCoordinates("C34");
        Assert.assertEquals(actual, expected);

        actual = new Coordinate(27, 27);
        expected = appUtils.getXYCoordinates("AZ27");
        Assert.assertEquals(actual, expected);
    }


    @Test(expected = IllegalArgumentException.class)
    public void getXYPositionsWithException() {
        String incorrectPosition = "{a34";
        appUtils.getXYCoordinates(incorrectPosition);
    }

    @Test
    public void checkValidBoard() {
        int width = 8, height = 8;
        Coordinate trueCoordinate = new Coordinate(3, 4);
        Coordinate falseCoordinate = new Coordinate(9, 3);
        Coordinate falseCoordianeMinus = new Coordinate(-1, 7);

        Assert.assertTrue(appUtils.checkValidBoard(width, height, trueCoordinate));
        Assert.assertFalse(appUtils.checkValidBoard(width, height, falseCoordinate));
        Assert.assertFalse(appUtils.checkValidBoard(width, height, falseCoordianeMinus));
    }

    @Test
    public void getOriginalCoordinatesRepresentation() {
        Coordinate coordinate = new Coordinate(27, 27);

        String expected = "AZ27";
        StringBuilder actual = appUtils.getOriginalCoordinatesRepresentation(coordinate);
        Assert.assertEquals(expected, actual.toString());
    }

}
