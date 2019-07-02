package com.popova.fix.jobtest.model;

public class Node {

    private int dest;
    private Node previous;
    private Coordinate coordinate;

    public Node() {
    }

    public Node(int dest, Node previous, Coordinate coordinate) {
        this.dest = dest;
        this.previous = previous;
        this.coordinate = coordinate;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public static class Builder {

        private int dest;
        private Node previous;
        private Coordinate coordinate;

        public Builder() {
        }

        public Builder setDest(int dest) {
            this.dest = dest;
            return this;
        }

        public Builder setCoordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public Builder setCoordianteXY(int x, int y) {
            this.coordinate = new Coordinate(x, y);
            return this;
        }

        public Builder setPrevious(Node previous) {
            this.previous = previous;
            return this;
        }

        public Node build() {
            return new Node(dest, previous, coordinate);
        }
    }
}
