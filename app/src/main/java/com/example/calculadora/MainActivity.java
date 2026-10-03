package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private EditText entradaEditText;
    private TextView resultadoTextView;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        entradaEditText = findViewById(R.id.editTextNumberDecimal);
        resultadoTextView = findViewById(R.id.textView2);
        calcularButton = findViewById(R.id.button16);

        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });
    }

    private void calcularResultado() {

        String expresion = entradaEditText.getText().toString().trim();

        // Eliminar espacios
        expresion = expresion.replace(" ", "");

        if (expresion.isEmpty()) {
            resultadoTextView.setText("Ingrese una expresión");
            return;
        }

        try {

            Expression expression = new ExpressionBuilder(expresion).build();

            double resultado = expression.evaluate();

            if (resultado == (long) resultado) {
                resultadoTextView.setText("Resultado: " + (long) resultado);
            } else {
                resultadoTextView.setText("Resultado: " + resultado);
            }

        } catch (Exception e) {

            resultadoTextView.setText("Error: Expresión inválida");

        }

    }

}