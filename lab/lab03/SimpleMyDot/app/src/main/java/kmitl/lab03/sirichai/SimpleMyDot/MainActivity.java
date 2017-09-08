package kmitl.lab03.sirichai.SimpleMyDot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import kmitl.lab03.sirichai.SimpleMyDot.model.Dot;
import kmitl.lab03.sirichai.SimpleMyDot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.OnDotChangedListener {

    private DotView dotView;
    private Dot dot;
    private List<Dot> dotList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            dotView = (DotView) findViewById(R.id.dotview);
            dotList = new ArrayList<>();

        }

    public void onRandomDot(View view) {
        Random random = new Random();
        int centerX = random.nextInt(1200);
        int centerY = random.nextInt(1400);
        int num_color = random.nextInt(8);
        int radius = random.nextInt(50)+30;
        dot = new Dot(this, centerX, centerY, radius, num_color);
        dotList.add(dot);



    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dotList);
        dotView.invalidate();
    }

    public void onClearAll(View view) {
        dotList.clear();
        dotView.setDot(dotList);
        dotView.invalidate();
    }
}
