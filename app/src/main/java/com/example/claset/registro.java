package com.example.claset;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registro extends AppCompatActivity {
    EditText etnombre, etmat, etedad, etcarrera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etnombre = (EditText) findViewById(R.id.nombre);
        etmat = (EditText) findViewById(R.id.matricula);
        etedad = (EditText) findViewById(R.id.edad);
        etcarrera = (EditText) findViewById(R.id.carrera);
    }
    public void registro(View view) {
        AdminSQLiteOpenHelper admin =new AdminSQLiteOpenHelper (this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String matriculaText = etmat.getText().toString();

        String nombresText = etnombre.getText().toString();

        String edadText = etedad.getText().toString();

        String idCarreraText = etcarrera.getText().toString();

        if (!matriculaText.isEmpty() || !nombresText.isEmpty() || !edadText.isEmpty() || !idCarreraText.isEmpty()) {
            bd.execSQL("Insert into estudiantes (matricula, nombres, edad, id_carrera) "
                    + "values ('" + matriculaText + "','" + nombresText + "','" + edadText + "','" + idCarreraText + "')");

            bd.close();

            etmat.setText("");

            etnombre.setText("");

            etedad.setText("");

            etcarrera.setText("");

            Toast.makeText(this, "Se cerraron los datos del estudiante", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor, llene todos los campos.", Toast.LENGTH_SHORT).show();
        }
    }
    public void consultaMatricula (View view) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(  this,"administracion",  null,  1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        String matriculaText = etmat.getText().toString();

        if(!matriculaText.isEmpty()){

            Cursor fila = bd.rawQuery("select nombres, edad, id_carrera from estudiantes where " +
                    "matricula=" + matriculaText,  null);

            if (fila.moveToFirst()) {

                etnombre.setText(fila.getString(  0));
                etedad.setText(fila.getString( 1));
                etcarrera.setText(fila.getString( 2));

                Toast.makeText( this,  "Consulta exitosa. ",
                        Toast.LENGTH_SHORT).show();

            } else{

                Toast.makeText( this,"No existe un estudiante con dicha matrícula",

                        Toast.LENGTH_SHORT).show();

            }

            bd.close();

        }else{

            Toast.makeText(  this, "Ingrese un número de matrícula.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarEstudiante (View v) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this, "administracion",  null,  1);

        SQLiteDatabase bd = admin.getWritableDatabase ();

        String matriculaText = etmat.getText().toString();

        if (!matriculaText.isEmpty()) {

            bd.execSQL ("delete from estudiantes where matricula=" + matriculaText);

            bd.close();

            etmat.setText("");

            etnombre.setText("");

            etedad.setText("");

            etcarrera.setText("");

            Toast.makeText( this, "Estudiante eliminado de la base de datos.",

                    Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(  this,"Ingrese un número de matrícula.",

                    Toast.LENGTH_SHORT).show();



        }
    }
    public void modificar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(  this,  "administracion", null,  1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String matriculaText = etmat.getText().toString();

        String nombresText = etnombre.getText().toString();

        String edadText = etedad.getText().toString();

        String idCarreraText = etcarrera.getText().toString();

        if(!matriculaText.isEmpty()){

            if(nombresText.isEmpty()||edadText.isEmpty() ||idCarreraText.isEmpty()){
                Toast.makeText( this, "Ingrese todos los datos.",
                        Toast.LENGTH_SHORT).show();


            }
//try {

            bd.execSQL ("update estudiantes set matricula="+matriculaText+", nombres='"
                    +nombresText+"',edad="+edadText+",id_carrera='"
                    +idCarreraText+"' where matricula="+matriculaText);

            etmat.setText("");

            etnombre.setText("");

            etedad.setText("");

            etcarrera.setText("");

            bd.close();

            Toast.makeText(  this,  "Se modificaron los datos.", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(  this,  "Ingrese una matricula.",

                    Toast.LENGTH_SHORT).show();

        }
    }
}