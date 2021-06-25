package com.example.familiagoogle;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import java.text.Normalizer;
import java.util.ArrayList;

public class Asistente  {

    private TextView escuchando;
    private TextView respuesta;
    private ArrayList<Respuestas> respuest;
    private TextToSpeech leer;

    public Asistente() {
        respuest = new ArrayList<>();
    }

    public Asistente(TextView escuchando, TextView respuesta, ArrayList<Respuestas> respuest, TextToSpeech leer) {
        this.escuchando = escuchando;
        this.respuesta = respuesta;
        this.respuest = respuest;
        this.leer = leer;
    }

    public void prepararRespuesta(String escuchado) {
        String normalizar = Normalizer.normalize(escuchado, Normalizer.Form.NFD);
        String sintilde = normalizar.replaceAll("[^\\p{ASCII}]", "");
        int resultado;
        String respuesta = respuest.get(0).getRespuestas();
        for (int i = 0; i < respuest.size(); i++) {
            resultado = sintilde.toLowerCase().indexOf(respuest.get(i).getCuestion());
            if(resultado != -1){
                respuesta = respuest.get(i).getRespuestas();
            }
        }
        responder(respuesta);
    }

    private void responder(String respuestita) {
        respuesta.setText(respuestita);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            leer.speak(respuestita, TextToSpeech.QUEUE_FLUSH, null, null);
        }else {
            leer.speak(respuestita, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Respuestas> proveerDatos(){
        ArrayList<Respuestas> respuestas = new ArrayList<>();
        respuestas.add(new Respuestas("defecto", "sorry This pharmacy is not in this system"));
        respuestas.add(new Respuestas("give me information about fishel", "we offer you the following branches in this application Heredia, Oxígeno and San Pablo "));
        respuestas.add(new Respuestas("give me information about bomba", "we're located in Coronado, Centro Heredia and Alajuela"));
        respuestas.add(new Respuestas("give me information about total", "we can attend you in La 32, Guápiles and Cariari" ));
        respuestas.add(new Respuestas("give me information about value", "visit us in Zapote, Hatillo and Escazú"));

        return respuestas;
    }

}
