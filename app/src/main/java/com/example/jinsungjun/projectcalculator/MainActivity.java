package com.example.jinsungjun.projectcalculator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView preview, result;
    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnDot, btnCalc, btnCancel, btnBack,btnLeft,btnRight; //나머지 연산자 버튼
    Button[] btn = new Button[10]; //0~9 버튼
    String bracketTemp = "";

    ObjectAnimator moveX,moveY,rotation,back;

    float goalX,goalY,goalWidth,goalHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preview = findViewById(R.id.preview); //계산식이 입력되는 TextView
        result = findViewById(R.id.result); //결과값이 출력되는 TextView


        //나머지 버튼 소스코드 연결, 리스너 등록
        btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener(this);

        btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener(this);

        btnMultiply = findViewById(R.id.btnMultiply);
        btnMultiply.setOnClickListener(this);

        btnDivide = findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(this);

        btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(this);

        btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(this);

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnLeft = findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(this);
        btnRight = findViewById(R.id.btnRight);
        btnRight.setOnClickListener(this);

        //나머지 버튼 소스코드 연결, 리스너 등록

        for (int i = 0; i < 10; i++) {
            //btn0~9를 소스코드로 연결, 리스너 등록
            //id를 가져온다
            int id = getResources().getIdentifier("btn" + i, "id", getPackageName());

            btn[i] = findViewById(id);
            btn[i].setOnClickListener(this);
        }

        //view 로드 체크하기
        ViewTreeObserver vto = preview.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                goalX = preview.getX();
                goalY = preview.getY();
                goalWidth = preview.getWidth();
                goalHeight = preview.getHeight();




            }
        });

    }



    @Override
    public void onClick(View view) {

        String temp = "";


        temp = preview.getText().toString(); // preview의 text를 한번 가져온다

        String pre = getResources().getString(R.string.text_preview); //preview의 default string text_preview를 가져온다
        if (pre.equals(temp)) //temp안에 text_preview만 있다면, 즉 맨 처음 사용자로부터 버튼이 클릭되면
            temp = "";

        if ("0".equals(temp)) // 맨 처음 숫자를 입력받을 때 0이 중복되서 입력받지 않도록 처리
            temp = "";

        switch (view.getId()) {

            case R.id.btn0:
                preview.setText(temp + "0");
                buttonAni(btn[0]);
                break;
            case R.id.btn1:
                preview.setText(temp + "1");
                buttonAni(btn[1]);
                break;
            case R.id.btn2:
                preview.setText(temp + "2");
                buttonAni(btn[2]);
                break;
            case R.id.btn3:
                preview.setText(temp + "3");
                buttonAni(btn[3]);
                break;
            case R.id.btn4:
                preview.setText(temp + "4");
                buttonAni(btn[4]);
                break;
            case R.id.btn5:
                preview.setText(temp + "5");
                buttonAni(btn[5]);
                break;
            case R.id.btn6:
                preview.setText(temp + "6");
                buttonAni(btn[6]);
                break;
            case R.id.btn7:
                preview.setText(temp + "7");
                buttonAni(btn[7]);
                break;
            case R.id.btn8:
                preview.setText(temp + "8");
                buttonAni(btn[8]);
                break;
            case R.id.btn9:
                preview.setText(temp + "9");
                buttonAni(btn[9]);
                break;
            case R.id.btnPlus:
                preview.setText(temp + "+");
                buttonAni(btnPlus);
                break;
            case R.id.btnMinus:
                preview.setText(temp + "-");
                buttonAni(btnMinus);
                break;
            case R.id.btnMultiply:
                preview.setText(temp + "*");
                buttonAni(btnMultiply);
                break;
            case R.id.btnDivide:
                preview.setText(temp + "/");
                buttonAni(btnDivide);
                break;
            case R.id.btnCancel:
                temp = "0";
                preview.setText(temp);
                break;
            case R.id.btnCalc:
//                bracketTemp = bracket(temp);
                temp = calc(temp);
                result.setText(temp + bracketTemp);
                break;
            case R.id.btnBack:
                temp = temp.substring(0, temp.length() - 1); //뒤로가기를 누르면 subString()을 통해 맨 마지막 문자를 제거한다.
                preview.setText(temp);
                break;
            case R.id.btnDot:
                buttonAni(btnDot);
                preview.setText(temp + ".");
                break;
            case R.id.btnLeft :
                preview.setText(temp + "(");
                break;
            case R.id.btnRight :
                preview.setText(temp + ")");
                break;
        }

    } //onClick

    public String bracket(String str) {



        String result = "";

        int leftIndex = str.indexOf("(");
        int rightIndex = str.indexOf(")");



        Log.d("leftindex",leftIndex+"");
        Log.d("rightindex",rightIndex+"");

        List<String> bracketArray = new ArrayList<>(); //계산식을 담아줄 List 선언

        for(int i=leftIndex+1;i<rightIndex;i++) {

            bracketArray =
        }

        //result = calc(str);

        return result;
    }

    public void buttonAni(final Button button) {

        final float oriX,oriY;
        oriX = button.getX();
        oriY = button.getY();
        button.setRotation(0);


        moveX = ObjectAnimator.ofFloat(button,
                "x",
                goalX + goalWidth - 170);

        moveY = ObjectAnimator.ofFloat(button,
                "y",
                goalY - 30);

        rotation = ObjectAnimator.ofFloat(button,
                "rotation",
                720);

        back = ObjectAnimator.ofFloat(button,
                View.ALPHA,
                0,1); //투명도 조정

        final AnimatorSet aniSet2 = new AnimatorSet();

        aniSet2.playTogether(back);
        aniSet2.setDuration(1000);

        final AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(moveX,moveY,rotation);
        aniSet.setDuration(1500);


        aniSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }
            @Override
            public void onAnimationEnd(Animator animator) {

                button.setX(oriX); //원래 버튼의 X좌표값을 넣어준다
                button.setY(oriY); //원래 버튼의 Y좌표값을 넣어준다

                aniSet2.start();

            }
            @Override
            public void onAnimationCancel(Animator animator) {

            }
            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        aniSet.start();
    }


    public String calc(String str) {

        String result = "";
        boolean isFloat = false; //소숫점 계산기 있는지 없는지의 여부를 담는 boolean
        String[] spitArray = str.split(""); //입력받은 계산식을 1개의 문자단위로 분할
        List<String> array = new ArrayList<>(); //계산식을 담아줄 List 선언

        String temp = "";

        for (String item : spitArray) {

            if (item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/") || item.equals(".") || item.equals("(") || item.equals(")")) {

                //만일 사칙연산 기호이면, 그 전까지의 숫자들을 모두 더한 값을 add해주고
                //temp를 초기화
                //해당 연산기호를 넣어준다

                array.add(temp);
                temp = "";
                array.add(item);
            } else {

                temp = temp + item;
            }
        } //for

        array.add(temp); //마지막 값을 넣어준다.


        for (int j = array.size(); j > 1; j--) {

            for (int i = 0; i < array.size(); i++) {

                // . 연산자 처리
                String value = array.get(i);

                if (value.equals(".")) {
                    //ex) 32.3
                    String dotPrev = array.get(i - 1); //32 (정수부분)
                    String dot = array.get(i);  // . (소숫점)
                    String dotNext = array.get(i + 1); //3 (소수부분)

                    String dotTemp = dotPrev.concat(dot); // 정수부분과 소숫점의 결합
                    dotTemp = dotTemp.concat(dotNext); //나머지 소수부분 결합

                    array.set(i, dotTemp); //List에 최종 숫자를 넣어준다
                    array.remove(i + 1);
                    array.remove(i - 1); //나머지 필요없는 부분은 삭제

                    isFloat = true; //소숫점 연산 flag on

                }
            }

            for (int i = 0; i < array.size(); i++) {

                String value = array.get(i);

                if (value.equals("*")) {

                    if (isFloat) {
                        //실수부 연산이 필요하면

                        double prev = Double.parseDouble(array.get(i - 1)); //Double형으로 변환하여 진행
                        double next = Double.parseDouble(array.get(i + 1));

                        double tempResult = 0;
                        tempResult = prev * next;

                        array.set(i, tempResult + "");
                        array.remove(i + 1);
                        array.remove(i - 1);

                    } else {
                        //정수부 연산
                        int prev = Integer.parseInt(array.get(i - 1));
                        int next = Integer.parseInt(array.get(i + 1));

                        int tempResult = 0;
                        tempResult = prev * next;

                        array.set(i, tempResult + "");
                        array.remove(i + 1);
                        array.remove(i - 1);

                    }

                }
            }


            for (int i = 0; i < array.size(); i++) {

                String value = array.get(i);

                if (value.equals("/")) {
                    if (isFloat) {
                        //실수부 연산이 필요하면
                        double prev = Double.parseDouble(array.get(i - 1)); //Double형으로 변환하여 진행
                        double next = Double.parseDouble(array.get(i + 1));

                        double tempResult = 0;
                        tempResult = prev / next;

                        array.set(i, tempResult + "");
                        array.remove(i + 1);
                        array.remove(i - 1);

                    } else {
                        //정수부 연산
                        int prev = Integer.parseInt(array.get(i - 1)); //Double형으로 변환하여 진행
                        int next = Integer.parseInt(array.get(i + 1));

                        int tempResult = 0;
                        tempResult = prev / next;

                        array.set(i, tempResult + "");
                        array.remove(i + 1);
                        array.remove(i - 1);

                    }

                }
            }

            for (int i = 0; i < array.size(); i++) {

                String value = array.get(i);

                if (value.equals("+")) {
                    if (isFloat) {
                        //실수부 연산이 필요하면
                        double prev = Double.parseDouble(array.get(i - 1)); //Double형으로 변환하여 진행
                        double next = Double.parseDouble(array.get(i + 1));

                        double tempResult = 0;
                        tempResult = prev + next;

                        array.set(i, tempResult + "");
                        array.remove(i + 1);
                        array.remove(i - 1);

                    } else {
                        //정수부 연산
                        int prev = Integer.parseInt(array.get(i - 1)); //Double형으로 변환하여 진행
                        int next = Integer.parseInt(array.get(i + 1));

                        int tempResult = 0;
                        tempResult = prev + next;

                        array.set(i, tempResult + "");
                        array.remove(i + 1);
                        array.remove(i - 1);

                    }

                }
            }

            for (int i = 0; i < array.size(); i++) {

                String value = array.get(i);

                if (value.equals("-")) {
                    if (isFloat) {
                        //실수부 연산이 필요하면
                        double prev = Double.parseDouble(array.get(i - 1));
                        double next = Double.parseDouble(array.get(i + 1));

                        double tempResult = 0;
                        tempResult = prev - next;

                        array.set(i, tempResult + "");
                        array.remove(i + 1);
                        array.remove(i - 1);

                    } else {
                        //정수부 연산
                        int prev = Integer.parseInt(array.get(i - 1));
                        int next = Integer.parseInt(array.get(i + 1));

                        int tempResult = 0;
                        tempResult = prev - next;

                        array.set(i, tempResult + "");
                        array.remove(i + 1);
                        array.remove(i - 1);

                    }
                }
            }
        }

        result = array.get(0);
        return result;
    }
}
