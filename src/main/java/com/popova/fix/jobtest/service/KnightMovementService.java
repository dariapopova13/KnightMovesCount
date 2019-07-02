package com.popova.fix.jobtest.service;

import com.popova.fix.jobtest.model.Node;

public interface KnightMovementService {

    Node countMovementsWithPath(int width, int height,
                                String startPosition, String endPosition);


    int countMovements(int width, int height,
                       String startPosition, String endPosition);

    String countMovementsWithPathRepresentation(int width, int height,
                                                String startPosition, String endPosition);

}
