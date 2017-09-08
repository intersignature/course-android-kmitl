package kmitl.lab03.sirichai.SimpleMyDot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import kmitl.lab03.sirichai.SimpleMyDot.model.Dot;


public class DotView extends View {

    private Paint paint;
    private List<Dot> dot;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(dot != null){
            for (int i=0; i<this.dot.size();i++){
                if (this.dot.get(i).getNum_color() == 0){
                    paint.setColor(Color.BLACK);
                }
                if (this.dot.get(i).getNum_color() == 1){
                    paint.setColor(Color.BLUE);
                }
                if (this.dot.get(i).getNum_color() == 2){
                    paint.setColor(Color.RED);
                }
                if (this.dot.get(i).getNum_color() == 3){
                    paint.setColor(Color.YELLOW);
                }
                if (this.dot.get(i).getNum_color() == 4){
                    paint.setColor(Color.GREEN);
                }
                if (this.dot.get(i).getNum_color() == 5){
                    paint.setColor(Color.GRAY);
                }
                if (this.dot.get(i).getNum_color() == 6){
                    paint.setColor(Color.CYAN);
                }
                if (this.dot.get(i).getNum_color() == 7){
                    paint.setColor(Color.MAGENTA);
                }
                canvas.drawCircle(this.dot.get(i).getCenterX(),
                        this.dot.get(i).getCenterY(),
                        this.dot.get(i).getRadius(), paint);
            }

        }

    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }

    public void setDot(List dot) {
        this.dot = dot;
    }
}
