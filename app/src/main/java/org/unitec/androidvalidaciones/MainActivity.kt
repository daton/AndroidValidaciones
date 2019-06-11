package org.unitec.androidvalidaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.Length
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Password
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Validator.ValidationListener
{


    @Email(message = "correo inválido")
    @NotEmpty(message = "campo requerido")
    private var login: TextInputEditText?=null

    @Length(max=8,message = "password fuera de rango")
    @Password(min=4,scheme = Password.Scheme.ALPHA_NUMERIC)
private  var password:TextInputEditText?=null


    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        //Aqui van las acciones  a tomar en caso de validaciones erroneas
        var mensa="men"

        for (error in errors!!) {
            val view = error.view

            mensa=error.getCollatedErrorMessage(applicationContext)

            Toast.makeText(applicationContext,mensa,Toast.LENGTH_LONG).show()
        }
    }

    override fun onValidationSucceeded() {
        //Aqui van acciones a tomar si la validación fue exitosa, por ejemplo navegacion a otro activity
    val i= Intent(applicationContext,BienvenidoActivity::class.java)
        startActivity(i)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var validator = Validator(this);

        validator.setValidationListener(this);
        login= txtemail
        password=txtpassword

        button.setOnClickListener {
            validator.validate()
        }


    }
}
