package com.simulator.app.model;

public class Bulldozer {

    private int columnIndex;
    private int rowIndex;
    private Direction direction;

    private static Bulldozer instance = null;

    public static Bulldozer getInstance() {
        if (instance == null) {
            synchronized(Bulldozer.class) {
                if (instance == null) {
                    instance = new Bulldozer(Direction.EAST, -1, 0);
                }
            }
        }
        return instance;
    }

    private Bulldozer(Direction direction, int columnIndex, int rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.direction = direction;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void turnLeft() {
        this.turnLeft(this.getDirection());
    }

    private void turnLeft(Direction direction) {
        Direction newDirection = Direction.getValues()[(direction.ordinal() + (Direction.getValues().length - 1)) % Direction.getValues().length];
        this.setDirection(newDirection);
    }

    public void turnRight() {
        this.turnRight(this.getDirection());
    }

    private void turnRight(Direction direction) {
        Direction newDirection = Direction.getValues()[(direction.ordinal() + 1) % Direction.getValues().length];
        this.setDirection(newDirection);
    }

    public void advance(int moveCount) {
        Direction currentDirection = this.getDirection();
        switch (currentDirection){
            case NORTH: this.setRowIndex(this.getRowIndex() - moveCount); break;
            case EAST: this.setColumnIndex(this.getColumnIndex() + moveCount); break;
            case SOUTH: this.setRowIndex(this.getRowIndex() + moveCount); break;
            case WEST: this.setColumnIndex(this.getColumnIndex() - moveCount); break;
            default: break;
        }
    }
}