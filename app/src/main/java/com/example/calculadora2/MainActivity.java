package com.example.calculadora2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView operacion;

    String operador = "";
    String primerNum = "";
    String segundoNum = "";

    public void clickNum(View view){
        Button button = (Button) view;

        // Obtén el texto del botón seleccionado
        String buttonText = button.getText().toString();

        // Concatena el texto del botón al valor existente de primerNum
        primerNum += buttonText;

        // Actualiza el contenido del TextView operacion
        operacion.setText(primerNum);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operacion = findViewById(R.id.textView1);
    }
}