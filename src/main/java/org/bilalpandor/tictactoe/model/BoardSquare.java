package org.bilalpandor.tictactoe.model;

public class BoardSquare {
    private final Integer xCoordinate;
    private final Integer yCoordinate;

    public BoardSquare(Integer xCoordinate, Integer yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Integer getXCoordinate() {
        return xCoordinate;
    }

    public Integer getYCoordinate() {
        return yCoordinate;
    }

    @Override
    public final int hashCode() {
        return xCoordinate * yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BoardSquare))
            return false;
        BoardSquare other = (BoardSquare) o;

        boolean xCoordinateEquals = (this.xCoordinate == null && other.xCoordinate == null)
                || (this.xCoordinate != null && this.xCoordinate.equals(other.xCoordinate));

        boolean yCoordinateEquals = (this.yCoordinate == null && other.yCoordinate == null)
                || (this.yCoordinate != null && this.yCoordinate.equals(other.yCoordinate));
        
        return xCoordinateEquals && yCoordinateEquals;
    }

}
