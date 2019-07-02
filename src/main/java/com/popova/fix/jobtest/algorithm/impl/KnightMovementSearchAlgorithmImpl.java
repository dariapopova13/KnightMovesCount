package com.popova.fix.jobtest.algorithm.impl;

import com.popova.fix.jobtest.algorithm.KnightMovementSearchAlgorithm;
import com.popova.fix.jobtest.model.Coordinate;
import com.popova.fix.jobtest.model.Node;
import com.popova.fix.jobtest.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component(value = "knightBFSSearch")
public class KnightMovementSearchAlgorithmImpl implements KnightMovementSearchAlgorithm {

    private AppUtils appUtils;
    private List<Coordinate> possibleKnightMoves;

    public KnightMovementSearchAlgorithmImpl() {
        this.possibleKnightMoves = new ArrayList<>(8);
        possibleKnightMoves.add(new Coordinate(2, -1));
        possibleKnightMoves.add(new Coordinate(2, 1));
        possibleKnightMoves.add(new Coordinate(-2, 1));
        possibleKnightMoves.add(new Coordinate(-2, -1));
        possibleKnightMoves.add(new Coordinate(1, 2));
        possibleKnightMoves.add(new Coordinate(1, -2));
        possibleKnightMoves.add(new Coordinate(-1, 2));
        possibleKnightMoves.add(new Coordinate(-1, -2));
    }

    @Autowired
    public void setAppUtils(AppUtils appUtils) {
        this.appUtils = appUtils;
    }

    @Override
    public Node search(int width, int height, Node src, Node dest) {
        Map<Node, Boolean> visited = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.getCoordinate().getX() == dest.getCoordinate().getX()
                    && current.getCoordinate().getY() == dest.getCoordinate().getY())
                return current;

            if (visited.get(current) == null) {
                visited.put(current, true);
                for (Coordinate possibleKnightMove : possibleKnightMoves) {
                    int x1 = current.getCoordinate().getX() + possibleKnightMove.getX();
                    int y1 = current.getCoordinate().getY() + possibleKnightMove.getY();

                    if (appUtils.checkValidBoard(width, height, new Coordinate(x1, y1))) {
                        queue.add(new Node.Builder()
                                .setCoordianteXY(x1, y1)
                                .setDest(current.getDest() + 1)
                                .setPrevious(current)
                                .build());
                    }
                }
            }
        }
        Node node = new Node();
        node.setDest(-1);
        return node;
    }
}
