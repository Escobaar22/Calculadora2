/**
 * @author: Poly Escobar Sanchez
 * @version: 1.0
 * @date: 2023-11-22
 */

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
    String resultadoNum = "";
    Button btnDiv;
    Button btnMulti;
    Button btnMas;
    Button btnMenos;
    Button btnIgual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazar la vista TextView para mostrar la operación
        operacion = findViewById(R.id.textView1);

        // Inicializar botones buscando su id
        btnDiv = findViewById(R.id.btnDiv);
        btnMulti = findViewById(R.id.btnMulti);
        btnMas = findViewById(R.id.btnMas);
        btnMenos = findViewById(R.id.btnRes);

        // Deshabilitar operadores al inicio de la aplicación
        deshabilitarOperadores();

        // Enlazar el botón de igual
        btnIgual = findViewById(R.id.btnIgual);

    }

    public void clickNum(View view){
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        if (resultadoNum.isEmpty()) {
            // Verificar si no hay ningún primer número ni operador establecido
            if (primerNum.isEmpty() && operador.isEmpty()) {
                // Habilitar los operadores para iniciar una nueva operación
                habilitarOperadores();
            }

            // Comprobar si ya se ha seleccionado un operador
            if (!operador.isEmpty()) {
                // Añadir el número a segundoNum si ya hay un operador
                segundoNum += buttonText;
            } else {
                // Agregar el número a primerNum si no hay operador seleccionado
                primerNum += buttonText;
            }

            // Actualizar el TextView con los números ingresados
            actualizarTextView();

            // Habilitar el botón de igual si todos los campos necesarios para la operación están completos
            if (!primerNum.isEmpty() && !operador.isEmpty() && !segundoNum.isEmpty()) {
                btnIgual.setEnabled(true);
            }
        } else {
            // Comenzar una nueva operación con el número presionado
            // Establecer el número presionado como el nuevo primer número
            primerNum = buttonText;
            // Reiniciar el resultado previo
            resultadoNum = "";
            // Reiniciar el segundo número
            segundoNum = "";
            // Reiniciar el operador
            operador = "";
            // Deshabilitar el botón de igualdad
            btnIgual.setEnabled(false);

            // Actualizar el TextView con el nuevo número
            actualizarTextView();
        }
    }


    public void clickOperador(View view){
        Button button = (Button) view;

        String buttonText = button.getText().toString();

        // Si hay un resultado existente, actualizar primerNum con el resultado y continuar con el operador seleccionado
        if (!resultadoNum.isEmpty()) {
            primerNum = resultadoNum;
            // Reiniciar el resultado
            resultadoNum = "";
            // Reiniciar el segundo número
            segundoNum = "";
            // Establecer el operador seleccionado
            operador = buttonText;
            // Deshabilitar el botón de igual
            btnIgual.setEnabled(false);
        } else {
            // Establecer el operador seleccionado
            operador = buttonText;
            // Deshabilitar otros operadores hasta que se ingrese el segundo número
            deshabilitarOperadores();
        }

        // Actualizar el TextView con el operador seleccionado
        actualizarTextView();

        // Habilitar el botón de igual si todos los campos necesarios para la operación están completos
        if (!primerNum.isEmpty() && !operador.isEmpty() && !segundoNum.isEmpty()) {
            btnIgual.setEnabled(true);
        }
    }


    public void clickCA(View view){
        // Limpiar todos los campos posibles del textView
        primerNum = "";
        operador = "";
        segundoNum = "";
        resultadoNum = "";

        // Actualizar el textView
        actualizarTextView();

        // Deshabilitamos los operadores y el boton igual
        deshabilitarOperadores();
        btnIgual.setEnabled(false);
    }

    public void clicIgual(View view) {
        // Verificar si la operación está bien construida
        if (operador.isEmpty() || segundoNum.isEmpty()) {
            return; // No hacer nada si la operación no está bien construida
        }

        // Convertir los números de String a enteros
        int num1 = Integer.parseInt(primerNum);
        int num2 = Integer.parseInt(segundoNum);

        int resultado = 0;

        // Realizar la operación correspondiente según el operador seleccionado
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

        // Mostrar el resultado en el TextView de operación
        operacion.setText(String.valueOf(resultado));
        resultadoNum += String.valueOf(resultado); // Agregar el resultado a la variable de resultadoNum
        primerNum = resultadoNum; // Establecer el resultado como el nuevo primer número

        segundoNum = ""; // Reiniciar el segundo número
        operador = ""; // Reiniciar el operador
        habilitarOperadores();  // Habilitar los operadores para una nueva operación
    }


    // Metodo para deshabilitar los operadores
    private void deshabilitarOperadores(){
        btnMas.setEnabled(false);
        btnMenos.setEnabled(false);
        btnMulti.setEnabled(false);
        btnDiv.setEnabled(false);
    }

    // Metodo para habilitar los operadores
    private void habilitarOperadores(){
        btnMas.setEnabled(true);
        btnMenos.setEnabled(true);
        btnMulti.setEnabled(true);
        btnDiv.setEnabled(true);
    }

    // Metodo para actualizar el textViews
    private void actualizarTextView(){
        String operacionMostrar = primerNum + " " + operador + " " + segundoNum;
        operacion.setText(operacionMostrar);
    }
}
