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

    Button btnDiv;
    Button btnMulti;
    Button btnMas;
    Button btnMenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operacion = findViewById(R.id.textView1);

        // Inicializar botones después de setContentView
        btnDiv = findViewById(R.id.btnDiv);
        btnMulti = findViewById(R.id.btnMulti);
        btnMas = findViewById(R.id.btnMas);
        btnMenos = findViewById(R.id.btnRes);

        deshabilitarOperaciones();
    }

    public void clickNum(View view){
        Button button = (Button) view;

        // Obtén el texto del botón seleccionado
        String buttonText = button.getText().toString();

        // Concatena el texto del botón al valor existente de primerNum
        primerNum += buttonText;
        segundoNum += buttonText;

        // Actualiza el contenido del TextView operacion
        actualizarTextView();

        if (primerNum.isEmpty()) {
            deshabilitarOperaciones();
        } else {
            habilitarOperaciones();
        }
    }

    public void clickOperador(View view){
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        // Si la longitud de operador es mayor que 0, se elimina el último carácter
        if (operador.length() > 0) {
            operador = operador.substring(0, operador.length() - 1);
        }

        operador += buttonText;

        actualizarTextView();
    }

    private void actualizarTextView(){
        String operacionMostrar = primerNum + " " + operador + " " + segundoNum;

        operacion.setText(operacionMostrar);
    }

    private void deshabilitarOperaciones(){
        btnMas.setEnabled(false);
        btnMenos.setEnabled(false);
        btnMulti.setEnabled(false);
        btnDiv.setEnabled(false);
    }

    private void habilitarOperaciones(){
        btnMas.setEnabled(true);
        btnMenos.setEnabled(true);
        btnMulti.setEnabled(true);
        btnDiv.setEnabled(true);
    }
}