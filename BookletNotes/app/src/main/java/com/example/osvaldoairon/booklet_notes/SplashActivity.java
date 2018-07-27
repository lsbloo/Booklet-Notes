package com.example.osvaldoairon.booklet_notes;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private static final int TEMPO_EXIBICAO=3000;

    private ProgressBar barraProgresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        barraProgresso=(ProgressBar)findViewById(R.id.progressBar);



        showConnectUser(checkInternetConnect());



        /*
        É necessário criar um objeto do Tipo Handler que contem o metodo PostDelayed
        esse metodo executa uma ação do criando um novo objeto Runnable;
        que contem um metodo que vai ser executado e o tempo que deve ser executado;
         */
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                Faz a barra de progresso ficar Visivel para o usuario.
                */
                barraProgresso.setVisibility(View.GONE);

                Intent at = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(at);
                finish();
            }
        }, TEMPO_EXIBICAO);



    }


    public void showConnectUser(boolean connect){
        if(!connect){
            Toast.makeText(this, "Dispositivo não esta conectado!", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkInternetConnect(){

        /*
        Responsavel por gerenciar a conexão no dispositivo
        é necessario passar o tipo da conexao no construtor
        Necessita de um objeto do tipo NETWORKINFO para exibir as informacoes
         */
        ConnectivityManager gerenciadorConexao = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        /*
        Objeto networkinfo, utilizado para exibir a informaçoes
        é necessario passar o tipo de informacao sobre o servico que vai ser checado
        no caso WIFI
         */
        NetworkInfo wifi = gerenciadorConexao.getNetworkInfo(gerenciadorConexao.TYPE_WIFI);
        NetworkInfo mob = gerenciadorConexao.getNetworkInfo(gerenciadorConexao.TYPE_MOBILE);

        if(wifi.isConnected() || mob.isConnected()){
            /*
            Verifica se o dispositivo esta conectado a INTERNET WIFI.
             */
            return true;
        }
        return false;
    }
}
