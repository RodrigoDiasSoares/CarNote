package br.com.CarNote.activity.model;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ValidarCampos {
    private TextInputEditText editText1;
    private TextInputEditText editText2;
    private TextInputLayout textInputLayout1;
    private TextInputLayout textInputLayout2;

    public ValidarCampos(TextInputEditText editText1, TextInputEditText editText2,
                         TextInputLayout textInputLayout1, TextInputLayout textInputLayout2) {
        this.editText1 = editText1;
        this.editText2 = editText2;
        this.textInputLayout1 = textInputLayout1;
        this.textInputLayout2 = textInputLayout2;
    }

    public TextInputEditText getEditText1() {
        return editText1;
    }

    public void setEditText1(TextInputEditText editText1) {
        this.editText1 = editText1;
    }

    public TextInputEditText getEditText2() {
        return editText2;
    }

    public void setEditText2(TextInputEditText editText2) {
        this.editText2 = editText2;
    }

    public TextInputLayout getTextInputLayout1() {
        return textInputLayout1;
    }

    public void setTextInputLayout1(TextInputLayout textInputLayout1) {
        this.textInputLayout1 = textInputLayout1;
    }

    public TextInputLayout getTextInputLayout2() {
        return textInputLayout2;
    }

    public void setTextInputLayout2(TextInputLayout textInputLayout2) {
        this.textInputLayout2 = textInputLayout2;
    }

    public boolean camposValidos(){
        String camposValidos = "true";
        boolean status = true;
        if(editText1.equals("") && editText2.equals("")){
            camposValidos = "Preencha os campos!!";
            status = false;
        }else
        if(editText1 == null && editText2 == null){
            camposValidos = "Preencha os campos!!";
            status = false;
        }else
        if(editText1 == null || editText1.equals("")){
            camposValidos = "EditText1";
            status = false;
        }else
        if(editText2 == null || editText2.equals("")){
            camposValidos = "EditText2";
            status = false;
        }


        if(!camposValidos.equals("true")){
            if(camposValidos.equals("EditText1")){
                textInputLayout1.setError("!");
                textInputLayout2.setErrorEnabled(false);
                status = false;

            }else if(camposValidos.equals("EditText2")){
                textInputLayout2.setError("!");
                textInputLayout1.setErrorEnabled(false);
                status = false;

            }else {
                textInputLayout1.setError("!");
                textInputLayout2.setError("!");
                status = false;
            }

        }

        return status;
    }
}
