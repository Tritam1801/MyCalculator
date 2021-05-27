package com.CodeTitan.mycalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText dis;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dis = findViewById(R.id.EnterNum);

        dis.setShowSoftInputOnFocus(false);

        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display_hint).equals(dis.getText().toString()))
                {
                    dis.setText("");
                }
            }
        });
    }

    private void update_text(String strToAdd)
    {
        String oldStr = dis.getText().toString();
        int cursorPos = dis.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display_hint).equals(dis.getText().toString()))
        {
            dis.setText(strToAdd);
        }
        else
        {
            dis.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        }
        dis.setSelection(cursorPos+1);
    }

    public void btn0(View view)
    {
        update_text("0");
    }

    public void btn1(View view)
    {
        update_text("1");
    }

    public void btn2(View view)
    {
        update_text("2");
    }

    public void btn3(View view)
    {
        update_text("3");
    }

    public void btn4(View view)
    {
        update_text("4");
    }

    public void btn5(View view)
    {
        update_text("5");
    }

    public void btn6(View view)
    {
        update_text("6");
    }

    public void btn7(View view)
    {
        update_text("7");
    }

    public void btn8(View view)
    {
        update_text("8");
    }

    public void btn9(View view)
    {
        update_text("9");
    }

    public void btn_plus(View view)
    {
        update_text("+");
    }

    public void btn_minus(View view)
    {
        update_text("-");
    }

    public void btn_mul(View view)
    {
        update_text("×");
    }

    public void btn_div(View view)
    {
        update_text("÷");
    }

    public void btn_pow(View view)
    {
        update_text("^");
    }

    public void btn_equals(View view)
    {
        String userExp = dis.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String res = String.valueOf(exp.calculate());
        dis.setText(res);
        dis.setSelection(res.length());
    }

    public void btn_sign(View view)
    {
        update_text("-");
    }

    public void btn_clear(View view)
    {
        dis.setText("");
    }

    public void btn_par(View view)
    {
        int cursorPos = dis.getSelectionStart();
        int openPar = 0, closedPar = 0;
        int textLen = dis.getText().length();

        for (int i = 0; i< cursorPos; i++)
        {
            if(dis.getText().toString().substring(i, i+1).equals("("))
            {
                openPar++;
            }
            if(dis.getText().toString().substring(i, i+1).equals(")"))
            {
                closedPar++;
            }
        }
        if (openPar == closedPar || dis.getText().toString().substring(textLen - 1, textLen).equals("("))
        {
            update_text("(");
        }
        else if (closedPar < openPar && !dis.getText().toString().substring(textLen - 1, textLen).equals("("))
        {
            update_text(")");
        }
        dis.setSelection(cursorPos + 1);
    }
    public void btn_dec(View view)
    {
        update_text(".");
    }

    public void btn_del(View view)
    {
        int cursorPos = dis.getSelectionStart();
        int textLen= dis.getText().length();

        if (cursorPos != 0 && textLen != 0)
        {
            SpannableStringBuilder selection = (SpannableStringBuilder) dis.getText();
            selection.replace(cursorPos - 1, cursorPos, "" );
            dis.setText(selection);
            dis.setSelection(cursorPos - 1);
        }
    }
}