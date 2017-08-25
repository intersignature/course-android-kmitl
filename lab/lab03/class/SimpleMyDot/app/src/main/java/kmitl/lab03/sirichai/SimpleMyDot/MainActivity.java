package kmitl.lab03.sirichai.SimpleMyDot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.sirichai.SimpleMyDot.model.Dot;
import kmitl.lab03.sirichai.SimpleMyDot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private DotView dotView;
    private Dot dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotview);
        dot = new Dot(this, 0, 0, 20);

    }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(1000);
        int centerY = random.nextInt(1000);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);


    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }
}
