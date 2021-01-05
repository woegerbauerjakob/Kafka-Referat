package at.htl.data;

public class Line {
    public Position start;
    public Position end;
    public Line(){}
    public Line(Position start, Position end){
        this.start = start;
        this.end = end;
    }


    public Position getStart() {
        return start;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public Position getEnd() {
        return end;
    }

    public void setEnd(Position end) {
        this.end = end;
    }


}
