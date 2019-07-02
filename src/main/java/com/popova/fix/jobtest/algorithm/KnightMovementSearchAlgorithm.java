package com.popova.fix.jobtest.algorithm;

import com.popova.fix.jobtest.model.Node;

public interface KnightMovementSearchAlgorithm {

    Node search(int width, int height, Node src, Node dest);
}
