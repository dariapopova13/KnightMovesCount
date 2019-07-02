package com.popova.fix.jobtest.utils;

import com.popova.fix.jobtest.model.Coordinate;
import com.popova.fix.jobtest.model.Node;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class AppUtils {

    public boolean checkValidBoard(int width, int height, Coordinate coordinate) {
        return coordinate.getX() <= width && coordinate.getY() <= height
                && coordinate.getX() > 0 && coordinate.getY() > 0;
    }

    public Coordinate getXYCoordinates(String coordinate) {
        int x = 0, y = 0;
        String yString = StringUtils.getDigits(coordinate);
        String xString = StringUtils.removeEnd(coordinate, yString);
        for (int i = 0; i < xString.length(); i++) {
            int tmp = Character.toUpperCase(xString.charAt(i)) - 64;
            if (tmp < 1 || tmp > 26)
                throw new IllegalArgumentException("Only latin alphabet letters or digits must be used");
            x += tmp;
        }
        if (StringUtils.isEmpty(yString) || x == 0)
            throw new IllegalArgumentException("Input positions were not correct");
        y = Integer.valueOf(yString);
        return new Coordinate(x, y);
    }


    public StringBuilder getStringNodeMovesRepresentation(Node node, StringBuilder builder) {
        StringBuilder current = getOriginalCoordinatesRepresentation(node.getCoordinate());

        if (node.getPrevious() != null) {
            builder = getStringNodeMovesRepresentation(node.getPrevious(), builder);
            builder.append(" -> ").append(current);
            return builder;
        }
        current.append(builder);
        return current;
    }


    public StringBuilder getOriginalCoordinatesRepresentation(Coordinate coordinate) {
        int x = coordinate.getX();
        int count = x / 26;
        StringBuilder yOriginalRepresentation = new StringBuilder(
                IntStream.range(0, count).mapToObj(i -> "A")
                        .collect(Collectors.joining("")));
        yOriginalRepresentation.append(((char) (x - count + 64)));
        yOriginalRepresentation.append(coordinate.getY());
        return yOriginalRepresentation;
    }
}
