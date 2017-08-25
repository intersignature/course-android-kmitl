package kmitl.lab03.sirichai.SimpleMyDot.model;


public class Dot {

    public interface OnDotChangedListener{
        void onDotChanged(Dot dot);
    }

    public Dot(OnDotChangedListener listener, int centerX, int centerY, int radius) {
        this.listener = listener;


        this.radius = radius;
        this.listener.onDotChanged(this);
    }

    private OnDotChangedListener listener;

    public void setListener(OnDotChangedListener listener) {
        this.listener = listener;
    }

    private int centerX;
    private int centerY;
    private int radius;

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
