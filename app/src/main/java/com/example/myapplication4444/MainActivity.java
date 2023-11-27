package com.example.myapplication4444;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    ImageView round;
    ImageView obru4;
    int width_center;
    int heigth;
    int width;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        heigth = Resources.getSystem().getDisplayMetrics().heightPixels;
        width = Resources.getSystem().getDisplayMetrics().widthPixels;

        int heigth_center = heigth /4;
        width_center = width /4 ;

        round = (ImageView) findViewById(R.id.roundView);
        obru4 = (ImageView) findViewById(R.id.obru4View);
       /// simpleImageButton.setBackgroundColor(Color.BLACK);



        ///round.setLayoutParams(new LinearLayout.LayoutParams(width_center, width_center));

        round.setY((int)(heigth/2 - width_center/2));
        round.setX((int)(width/2 - width_center/2));

        //obru4.setLayoutParams(new LinearLayout.LayoutParams(width_center, width_center));

        obru4.setY((int)((heigth/2 - width_center/2) - width/8));
        obru4.setX((int)((width/2 - width_center/2) - width/8));

        round.getLayoutParams().height = width/4;
        round.getLayoutParams().width = width/4;

        obru4.getLayoutParams().height = width/2;
        obru4.getLayoutParams().width = width/2;
   /*     up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                Thread ClientThread = new Thread(runnable2);
                ClientThread.start();
            }
        });


        rigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Button2",Toast.LENGTH_LONG).show();// display the toast on home button click
            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Button1",Toast.LENGTH_LONG).show();// display the toast on home button click
            }
        });


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Button2",Toast.LENGTH_LONG).show();// display the toast on home button click
            }
        });*/


    }


    void send_data() throws IOException {
        Socket sendChannel=new Socket("localhost", 9090);
        OutputStream writer=sendChannel.getOutputStream();
        writer.write(new byte[]{1});
        writer.flush();
        InputStream reader=sendChannel.getInputStream();
        byte array[]=new byte[1];
        int i=reader.read(array);
    }


    public void client( String data) throws IOException, InterruptedException {



            long time= System.currentTimeMillis();
            boolean mRun = true;
            InetAddress serverAddr = InetAddress.getByName("100.74.103.132");///("10.7.0.85");////("192.168.240.54");///
            Socket client = new Socket();
            try {
                client.connect(new InetSocketAddress(serverAddr, 1024), 1000);
            } catch(Exception ex)
            {
                mRun = false;
            }
            if(mRun == true) {
                PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
                ///BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                InputStream input = client.getInputStream();
                output.println(data);
                output.flush();

            }

            ///System.out.println("Time = " + String.valueOf(time2 - time));
            ///Thread.sleep(100);

    }

    String position;
    String prev_position;
    private boolean isTouch = false;
    int current_x = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) event.getX();
        int Y = (int) event.getY();
        int eventaction = event.getAction();
        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:

                break;

          case MotionEvent.ACTION_MOVE:
                ///Toast.makeText(this, "MOVE "+"X: "+X+" Y: "+Y, Toast.LENGTH_SHORT).show();
                if(Y > heigth/3 && Y < heigth - heigth/3 && X > width/3 && X < width - width/3) {
                    round.setY((int)((Y - width_center / 2) - width/8));
                    round.setX((int)((X - width_center / 2) + 0));
                }
              if (Y > heigth / 2 + 100) {
                  position = "nazad";
              }
              if (Y < heigth / 2 - 100) {
                  position = "vpered";
              }
              if (X < width / 2 - 100) {
                  position = "vpravo";
              }
              if (X > width / 2 + 100) {
                  position = "vlevo";
              }
                if (prev_position != position) {
                    if (Y > heigth / 2 + 100) {
                        prev_position = "nazad";
                        Runnable runnable2 = new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    client("nazad");
                                } catch (IOException | InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        };
                        Thread ClientThread = new Thread(runnable2);
                        ClientThread.start();
                    }
                    if (Y < heigth / 2 - 100) {
                        prev_position = "vpered";
                        Runnable runnable2 = new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    client("vpered");
                                } catch (IOException | InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        };
                        Thread ClientThread = new Thread(runnable2);
                        ClientThread.start();
                    }

                    if (X < width / 2 - 100) {
                        prev_position = "vpravo";
                        Runnable runnable2 = new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    client("vpravo");
                                } catch (IOException | InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        };
                        Thread ClientThread = new Thread(runnable2);
                        ClientThread.start();
                    }

                    if (X > width / 2 + 100) {
                        prev_position = "vlevo";
                        Runnable runnable2 = new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    client("vlevo");
                                } catch (IOException | InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        };
                        Thread ClientThread = new Thread(runnable2);
                        ClientThread.start();
                    }
                }
                break;

            case MotionEvent.ACTION_UP:

                round.setY((int)(heigth/2 - width_center/2));
                round.setX((int)(width/2 - width_center/2));


                break;
        }
        return true;
    }




}