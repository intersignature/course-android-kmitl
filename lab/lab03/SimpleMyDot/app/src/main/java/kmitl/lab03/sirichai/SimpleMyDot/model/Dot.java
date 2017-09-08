package kmitl.lab03.sirichai.SimpleMyDot.model;


import java.util.HashMap;
import java.util.List;

public class Dot {


    public interface OnDotChangedListener{
        void onDotChanged(Dot dot);
    }

    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius, int num_color) {
        this.listener = listener;
        this.centerY = centerY;
        this.centerX = centerX;
        this.radius = radius;
        this.num_color = num_color;
        this.listener.onDotChanged(this);
    }


    private OnDotChangedListener listener;

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }

    private int centerX;
    private int centerY;
    private int radius;
    private int num_color;
    private List<Dot> dotList;

    public List<Dot> getDotList() {
        return dotList;
    }

    public void setDotList(List<Dot> dotList) {
        this.dotList = dotList;
        this.listener.onDotChanged(this);
    }

    public int getNum_color() {
        return num_color;
    }

    public void setNum_color(int num_color) {
        this.num_color = num_color;
        this.listener.onDotChanged(this);
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        this.listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;

    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

    }


}
