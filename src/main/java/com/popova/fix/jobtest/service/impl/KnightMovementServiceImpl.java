package com.popova.fix.jobtest.service.impl;

import com.popova.fix.jobtest.algorithm.KnightMovementSearchAlgorithm;
import com.popova.fix.jobtest.model.Coordinate;
import com.popova.fix.jobtest.model.Node;
import com.popova.fix.jobtest.service.KnightMovementService;
import com.popova.fix.jobtest.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class KnightMovementServiceImpl implements KnightMovementService {


    private AppUtils appUtils;
    private KnightMovementSearchAlgorithm knightMovementSearchAlgorithm;

    @Autowired
    @Qualifier(value = "knightBFSSearch")
    public void setKnightMovementSearchAlgorithm(KnightMovementSearchAlgorithm knightMovementSearchAlgorithm) {
        this.knightMovementSearchAlgorithm = knightMovementSearchAlgorithm;
    }

    @Autowired
    public void setAppUtils(AppUtils appUtils) {
        this.appUtils = appUtils;
    }

    @Override
    public int countMovements(int width, int height, String startPosition, String endPosition) {
        Node node = countMovementsWithPath(width, height, startPosition, endPosition);
        return node.getDest();
    }

    public Node countMovementsWithPath(int width, int height, String start, String end) {
        Coordinate startCoordinate = appUtils.getXYCoordinates(start);
        Coordinate endCoordinate = appUtils.getXYCoordinates(end);

        if (!appUtils.checkValidBoard(width, height, startCoordinate) ||
                !appUtils.checkValidBoard(width, height, endCoordinate))
            throw new IllegalArgumentException("Width or height of the board is smaller than given positions");

        if (start.equals(end))
            return new Node.Builder()
                    .setCoordinate(startCoordinate)
                    .setDest(0)
                    .build();

        Node src = new Node.Builder()
                .setCoordinate(startCoordinate)
                .build();
        Node dest = new Node.Builder()
                .setCoordinate(endCoordinate)
                .build();
        Node result = knightMovementSearchAlgorithm.search(width, height, src, dest);
        return result;
    }

    @Override
    public String countMovementsWithPathRepresentation(int width, int height, String startPosition, String endPosition) {
        Node node = countMovementsWithPath(width, height, startPosition, endPosition);
        if (node.getDest() == -1)
            return "The target is unreachable. -1";
        StringBuilder result = new StringBuilder("Moves: ")
                .append(node.getDest()).append(". Path: ")
                .append(appUtils.getStringNodeMovesRepresentation(node, new StringBuilder()).toString());
        return result.toString();
    }

}
