package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //atributos
    //String principal que se muestra en pantalla
    protected String ResultadoTexto="";
    //
    protected String ResultadoTextoAnterior="";
    //String auxiliar que tendra el valor de la segunda variable numerica
    protected String aux="";
    //variable que calculara los numeros introducidos
    protected Double ResultadoNumerico=0.0;
    //variable que nos indicar que accion se realizara
    protected String accion="";

    //la verdad es que esto se me fue de las manos. Si lo hubiera hecho con el modelo de edit Text y Text viw lo hubiera hecho mejor jejeejej.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //los bonotes que tenemos
        Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDivision, btnSuma, btnResta, btnMultiplicacion, btnResultado;
        Button btnC, btnBorrar, btnDoth;

        //cogejemos los botones de la app
        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btnDoth = (Button) findViewById(R.id.buttonPunto);
        btnMultiplicacion = (Button) findViewById(R.id.buttonMultiplicar);
        btnDivision = (Button) findViewById(R.id.buttonDivision);
        btnSuma = (Button) findViewById(R.id.buttonSuma);
        btnResta = (Button) findViewById(R.id.buttonRestar);
        btnResultado = (Button) findViewById(R.id.buttonResultado);
        //both of this works to clear
        btnC = (Button) findViewById(R.id.buttonBorrar);
        btnBorrar = (Button) findViewById(R.id.buttonBorrarTodo);

        //metodo de los botones numericos
        funcionBtnNumero(btn0);
        funcionBtnNumero(btn1);
        funcionBtnNumero(btn2);
        funcionBtnNumero(btn3);
        funcionBtnNumero(btn4);
        funcionBtnNumero(btn5);
        funcionBtnNumero(btn6);
        funcionBtnNumero(btn7);
        funcionBtnNumero(btn8);
        funcionBtnNumero(btn9);

        //metodos de los botones de las acciones
        funcionBtnAccion(btnDivision);
        funcionBtnAccion(btnMultiplicacion);
        funcionBtnAccion(btnResta);
        funcionBtnAccion(btnSuma);
        funcionBtnAccion(btnResultado);
        funcionBtnAccion(btnDoth);
        funcionBtnAccion(btnC);
        funcionBtnAccion(btnBorrar);


    }


    // metodo del boton numerico que asigna en pantalla el numero que contiene
    public void funcionBtnNumero(Button Btn){
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
                //primero discrimina si ya tiene una accion a realizar para saber a que String tiene que añadir el texto del boton
                if(accion==""){
                    //una vez sabemos lo cojemos el texto del boton y lo añadimos al String principal
                    mostrarResultAcc(textViewResultado,Btn,accion);
                }else{

                    //en caso que ya tiene accion añadira el valor del boton al String auxiliar ademas de enseñarlo en pantalla.
                    aux=aux+Btn.getText();
                    mostrarResultAcc(textViewResultado,Btn,accion);
                }

            }
            });

    }
            //funcion de botones con accion que desicriminara que accion debe hacer segun el boton.
    public void funcionBtnAccion(Button Btn){
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if(Btn ==(Button)findViewById(R.id.buttonMultiplicar)){
                     if(NoRepeat(Btn)==false){
                         multiplicacion(Btn);
                     }
                 }else if(Btn==(Button)findViewById(R.id.buttonDivision)){
                     if(NoRepeat(Btn)==false){
                         division(Btn);
                     }
                 }else if(Btn==(Button)findViewById(R.id.buttonSuma)){
                     if(NoRepeat(Btn)==false){
                         suma(Btn);
                     }

                 }else if(Btn==(Button)findViewById(R.id.buttonRestar)){
                     if(NoRepeat(Btn)==false){
                         resta(Btn);
                     }
                 }else if(Btn==(Button)findViewById(R.id.buttonResultado)){
                     if(NoRepeat(Btn)==false){
                         igual();
                     }
                 }else if(Btn==(Button)findViewById(R.id.buttonPunto)){
                     if(NoRepeat(Btn)==false){
                         doth(Btn);
                     }
                 }else if(Btn==(Button)findViewById(R.id.buttonBorrarTodo)){
                         borrarTodo();
                 }else if(Btn==(Button)findViewById(R.id.buttonBorrar)){
                         borrar();
                 }

            }
        });

    }

    public void mostrarResultAcc(TextView v, Button Btn, String accion){
        ResultadoTexto=ResultadoTexto+Btn.getText();
        v.setText(ResultadoTexto);
        this.accion=accion;
    }

    public boolean NoRepeat(Button Btn){
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        if(textViewResultado.getText().equals("")){
            return false;
        }else{
            String last ="";
            int length = ResultadoTexto.length();
            last = ResultadoTexto.substring(length -1, length);

            if(last.equals("//.")||last.equals("/")||last.equals("+")||last.equals("*")||last.equals("-")){
                changeSelected(Btn, last);
                return true;
            }else{
                return false;
            }
        }
    }

    public void changeSelected(Button btn, String last){
        if(btn.getText().equals(last)){

        }else{
            switch (btn.getText()+""){
                case "+":
                    borrar();
                    suma(btn);
                    break;
                case "-":
                    borrar();
                    resta(btn);
                    break;
                case "*":
                    borrar();
                    multiplicacion(btn);
                    break;
                case "/":
                    borrar();
                    division(btn);
                    break;
                default:
                    break;
            }
        }
    }



    //metodo de suma
    public void suma(Button Btn){
        // en caso de que no tenga accin
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        if(accion==""){
            //añadira el valor numerico del String a la variable numerica
            ResultadoNumerico=Double.parseDouble(ResultadoTexto.toString());
            //ademas su simbulo aparecera en pantalla junto con el valor anterrion
            mostrarResultAcc(textViewResultado,Btn,"suma");
        }else{
            igual();
            mostrarResultAcc(textViewResultado,Btn,"suma");
        }
    }

    public void multiplicacion(Button Btn){
        // en caso de que no tenga accin
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        if(accion==""){
            if(textViewResultado.getText().equals("")){
            }else {
                //añadira el valor numerico del String a la variable numerica
                ResultadoNumerico = Double.parseDouble(ResultadoTexto.toString());
                //ademas su simbulo aparecera en pantalla junto con el valor anterrion
                mostrarResultAcc(textViewResultado, Btn, "multiplicacion");
            }
        }else{
            igual();
            mostrarResultAcc(textViewResultado,Btn,"multiplicacion");
        }
    }

    public void division(Button Btn){
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        // en caso de que no tenga accin
        if(accion==""){
            //añadira el valor numerico del String a la variable numerica
            ResultadoNumerico= Double.parseDouble(ResultadoTexto.toString());
            //ademas su simbulo aparecera en pantalla junto con el valor anterrion
            mostrarResultAcc(textViewResultado,Btn,"division");
        }else{
            igual();
            mostrarResultAcc(textViewResultado,Btn,"division");
        }
    }

    public void resta(Button Btn){
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        // en caso de que no tenga accin
        if(accion==""){
            if(textViewResultado.getText().equals("")){
                mostrarResultAcc(textViewResultado,Btn,accion);
            }else {
                //añadira el valor numerico del String a la variable numerica
                ResultadoNumerico = Double.parseDouble(ResultadoTexto.toString());
                //ademas su simbulo aparecera en pantalla junto con el valor anterrion
                mostrarResultAcc(textViewResultado, Btn, "resta");
            }
        }else{
            igual();
            mostrarResultAcc(textViewResultado,Btn,"resta");
        }
    }

            //metodo para añadir un punto en pantalla
    public void doth(Button Btn){
        //se asegura que no haya cero ya que funciona sin 0 como numero no definido
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        if(accion=="") {
            if (ResultadoTexto != "") {
                //añade el punto al String principal y a la pantalla
                mostrarResultAcc(textViewResultado,Btn,accion);
            }
        }else{
            //en caso que ya tiene accion añadira el valor del boton al String auxiliar ademas de enseñarlo en pantalla.
            if (ResultadoTexto != "") {
                aux = aux + Btn.getText();
                mostrarResultAcc(textViewResultado,Btn,accion);
            }
        }
    }
        //este metodo no esta terminada por que tal y como planteaba la aplicacion se me escapaba de las manos
    public void borrar(){
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        textViewResultado.setText(ResultadoTexto);
        int length = ResultadoTexto.length();
        if(textViewResultado.getText().equals("")){

        }else{
            if(accion.equals("")){

                ResultadoTexto = ResultadoTexto.substring(0, length - 1);
                textViewResultado.setText(ResultadoTexto);


            }else{

                String newStr = ResultadoTexto.substring(length-1, length );
                if(newStr.equals("+")||newStr.equals("-")||newStr.equals("*")||newStr.equals("/")){
                    ResultadoTexto = ResultadoTexto.substring(0, length - 1);
                    textViewResultado.setText(ResultadoTexto);
                    accion="";
                }else {
                    if (aux.equals("")){

                    }else{
                       int length2 = aux.length();
                        aux=aux.substring(0, length2 - 1);
                        ResultadoTexto = ResultadoTexto.substring(0, length - 1);
                        textViewResultado.setText(ResultadoTexto);
                    }


                }

            }
        }

    }
        //borra todo
    public void borrarTodo(){
        ResultadoTexto="";
        ResultadoNumerico=0.0;
        aux="";
        accion="";
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        TextView textViewResultadoAnterio = (TextView) findViewById(R.id.textViewResultadoAnterior);
        textViewResultadoAnterio.setVisibility(View.GONE);
        textViewResultado.setText(ResultadoTexto);
        textViewResultado.setHint("0");
        textViewResultado.setTextSize(40);

    }

    //this is for not show decimal if decimal is 0
    public String noDecimal(String Decimal){

        String value []= Decimal.split("\\.");
        String d =value [1];
        if(d.equals("0")){
            return value [0];

        }
        return Decimal;
    }

    public void ShowAndSetEquals(TextView tResultado,TextView TResAnterio){
        TResAnterio.setText("Before operation:     "+ResultadoTexto+ " =   "+ noDecimal(ResultadoNumerico+""));
        tResultado.setText(noDecimal(ResultadoNumerico+""));
        ResultadoTexto=noDecimal(ResultadoNumerico+"");
        aux="";
        //ademas de reiniciar la accion por si quieres seguir calculando
        accion="";

    }


            //metodo igual del boton igual
    public void igual(){
        //segun la accion seleccionada previamente sumara o dividira etc. y los mostrara por pantalla.
        TextView textViewResultadoAnterio = (TextView) findViewById(R.id.textViewResultadoAnterior);
        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);
        textViewResultado.setTextSize(37);
        textViewResultadoAnterio.setVisibility(View.VISIBLE);
        switch (accion){
            case "suma":
                        //como se ve aqui realiza la accion
                ResultadoNumerico= ResultadoNumerico + Double.parseDouble(aux);
                ShowAndSetEquals(textViewResultado,textViewResultadoAnterio);

                break;

            case "resta":
                //como se ve aqui realiza la accion
                ResultadoNumerico= ResultadoNumerico - Double.parseDouble(aux);
                ShowAndSetEquals(textViewResultado,textViewResultadoAnterio);

                break;
            case "division":
                //como se ve aqui realiza la accion
                ResultadoNumerico= ResultadoNumerico / Double.parseDouble(aux);
                ShowAndSetEquals(textViewResultado,textViewResultadoAnterio);

                break;
            //como se ve aqui realiza la accion
            case "multiplicacion":
                ResultadoNumerico= ResultadoNumerico * Double.parseDouble(aux);
                ShowAndSetEquals(textViewResultado,textViewResultadoAnterio);

                break;

            default:
                break;
        }

    }

}