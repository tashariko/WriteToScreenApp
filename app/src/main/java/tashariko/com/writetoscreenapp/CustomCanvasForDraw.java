package tashariko.com.writetoscreenapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by tashariko on 26/05/17.
 */

public class CustomCanvasForDraw extends RelativeLayout implements CustomDrawView.GetCoordinateCallback{

    private CustomDrawView customDrawView;
    private TextView startText,moveText,endText;

    private boolean isDebugEnabled=true;

    public CustomCanvasForDraw(Context context) {
        this(context,null);
    }

    public CustomCanvasForDraw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCanvasForDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.custom_canvas_for_draw,this,true);

        customDrawView=(CustomDrawView)findViewById(R.id.mainView);

        startText= (TextView) findViewById(R.id.startPointText);
        moveText= (TextView) findViewById(R.id.movingPointText);
        endText= (TextView) findViewById(R.id.endPointText);

        if(isDebugEnabled){
            startText.setVisibility(VISIBLE);
            moveText.setVisibility(VISIBLE);
            endText.setVisibility(VISIBLE);
        }else{
            startText.setVisibility(GONE);
            moveText.setVisibility(GONE);
            endText.setVisibility(GONE);
        }

        customDrawView.setThisCallback(this);

    }

    public void setDebugMode(boolean isDebugEnabled){
        this.isDebugEnabled=isDebugEnabled;
    }

    public void changeColor(int color){
        customDrawView.setDrawColor(color);
    }

    public void undoView(){
        customDrawView.undoPath();
    }

    public void increaseWidth(boolean decrease){
        customDrawView.increaseWidth(decrease);
    }

    public void resetView(){
        customDrawView.resetView();
        moveText.setText("0.0");
        startText.setText("0.0");
        endText.setText("0.0");
    }

    @Override
    public void moving(float x, float y) {
        moveText.setText(String.format("%.02f",x)+", "+ String.format("%.02f",y));
    }

    @Override
    public void start(float x, float y) {
        startText.setText(String.format("%.02f",x)+", "+ String.format("%.02f",y));
    }

    @Override
    public void end(float x, float y) {
        endText.setText(String.format("%.02f",x)+", "+ String.format("%.02f",y));
    }
}