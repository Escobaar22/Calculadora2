package com.example.calculadora2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView operacion;

    String operador = "";
    String primerNum = "";
    String segundoNum = "";

    Button btnDiv;
    Button btnMulti;
    Button btnMas;
    Button btnMenos;
    Button btnIgual;


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
        deshabilitarOperadores();

        btnIgual = findViewById(R.id.btnIgual);
        btnIgual.setEnabled(false);
    }

    public void clickNum(View view){
        Button button = (Button) view;

        // Obtén el texto del botón seleccionado
        String buttonText = button.getText().toString();


        // Si primerNum está vacío y no hay un operador seleccionado aún
        if (primerNum.isEmpty() && operador.isEmpty()) {
            // Habilitar operadores después de ingresar el primer número
            habilitarOperadores();
        }

        // Si ya se ha ingresado un operador y se está agregando segundoNum
        if (!operador.isEmpty()) {
            segundoNum += buttonText;
        } else {
            primerNum += buttonText;
        }

        // Actualizar la operación mostrada en el TextView
        actualizarTextView();

        if (!primerNum.isEmpty() && !operador.isEmpty() && !segundoNum.isEmpty()) {
            btnIgual.setEnabled(true);
        }
    }

    public void clickOperador(View view){
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        operador += buttonText;

        // Deshabilitar operadores después de seleccionar uno
        deshabilitarOperadores();

        // Actualizar la operación mostrada en el TextView
        actualizarTextView();

        if (!primerNum.isEmpty() && !operador.isEmpty() && !segundoNum.isEmpty()) {
            btnIgual.setEnabled(true);
        }
    }


    public void clickCA(View view){
        primerNum = "";
        operador = "";
        segundoNum = "";

        actualizarTextView();

        deshabilitarOperadores();

        btnIgual.setEnabled(false);
    }

    public void clicIgual(View view) {
        if (operador.isEmpty() || segundoNum.isEmpty()) {
            return; // No hacer nada si la operación no está bien construida
        }

        int num1 = Integer.parseInt(primerNum);
        int num2 = Integer.parseInt(segundoNum);

        int resultado = 0;

        switch (operador) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                if (resultado < 0) {
                    // Alerta: No se puede hacer una resta negativa
                    Toast.makeText(this, "No se puede hacer una resta negativa", Toast.LENGTH_SHORT).show();
                    clickCA(view);
                    return;
                }
                break;
            case "×":
                resultado = num1 * num2;
                break;
            case "÷":
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    // Alerta: No se puede dividir por cero
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    clickCA(view);
                    return;
                }
                break;
        }

        operacion.setText(String.valueOf(resultado));
        primerNum += resultado;
        primerNum = String.valueOf(resultado);

        segundoNum = "";
        operador = "";
        habilitarOperadores();
    }

    private void deshabilitarOperadores(){
        btnMas.setEnabled(false);
        btnMenos.setEnabled(false);
        btnMulti.setEnabled(false);
        btnDiv.setEnabled(false);
    }

    private void habilitarOperadores(){
        btnMas.setEnabled(true);
        btnMenos.setEnabled(true);
        btnMulti.setEnabled(true);
        btnDiv.setEnabled(true);
    }

    private void actualizarTextView(){
        String operacionMostrar = primerNum + " " + operador + " " + segundoNum;
        operacion.setText(operacionMostrar);
    }
}
