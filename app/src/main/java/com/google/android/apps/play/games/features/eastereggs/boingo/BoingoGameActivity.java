package com.google.android.apps.play.games.features.eastereggs.boingo;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;

import com.google.android.play.games.R;

import java.util.ArrayList;
import java.util.List;

import p000.gog;
import p000.goh;
import p000.goo;
import p000.gop;
import p000.gor;
import p000.gpc;
import p000.gpd;
import p000.gpi;
import p000.gpj;
import p000.gpk;
import p000.gpl;

/**
 * Extracted Whirlybird/Boingo activity. Game-state, rendering, physics,
 * collision, scoring, animation, input and sound classes remain the original
 * decompiled implementations. Only Play Games DI/telemetry entry glue was
 * removed for standalone startup.
 */
public final class BoingoGameActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float[] filteredAcceleration;

    public int f9380p;
    public gog f9381q;
    public gpi f9382r;
    public goo f9383s;
    public gor f9384t;
    public gpl f9385u;
    public gpd f9386v;
    public gop f9387w;
    public goh f9388x;
    public int f9389y = 0;
    public final List f9390z = new ArrayList();
    public int f9371A = 3;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setFlags(128, 128);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        f9380p = getResources().getColor(R.color.boingo_white, getTheme());
        f9385u = new gpl(this);
        setRequestedOrientation(1);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_whirlybird);
        setTaskDescription(new ActivityManager.TaskDescription("Whirlybird", icon, getColor(R.color.boingo_grey)));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (f9381q != null) f9381q.m9062f();
        setVolumeControlStream(Integer.MIN_VALUE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        if (f9381q != null) {
            f9381q.f20028h = true;
            f9381q.f20022b = new Thread(f9381q, "WhirlybirdGameLoop");
            f9381q.f20022b.start();
        }
        setVolumeControlStream(3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRequestedOrientation(1);
        f9381q = new gog(this, this);
        f9381q.f20023c.setId(R.id.games__boingo__surface_view);
        f9385u.f20157f = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(new AudioAttributes.Builder().setUsage(14).setContentType(4).build())
                .build();
        f9385u.f20152a = f9385u.f20157f.load(this, R.raw.boingo_playerdeath_sound, 1);
        f9385u.f20153b = f9385u.f20157f.load(this, R.raw.boingo_jump_sound, 1);
        f9385u.f20154c = f9385u.f20157f.load(this, R.raw.boingo_nooglerhat_sound, 1);
        f9385u.f20155d = f9385u.f20157f.load(this, R.raw.boingo_platformbreak_sound, 1);
        f9385u.f20156e = f9385u.f20157f.load(this, R.raw.boingo_spring_sound, 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (f9385u.f20157f != null) {
            f9385u.f20157f.release();
            f9385u.f20157f = null;
        }
        sensorManager.unregisterListener(this);
        setRequestedOrientation(-1);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int state = f9371A;
        if ((state == 1 || state == 0) && event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            if (filteredAcceleration != null) {
                for (int i = 0; i < values.length; i++) {
                    float old = filteredAcceleration[i];
                    filteredAcceleration[i] = old + ((values[i] - old) * 0.2f);
                }
                values = filteredAcceleration;
            }
            filteredAcceleration = values;
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void m5370t() {
        int state = f9371A;
        if (state == 0) {
            goo enemy = f9383s;
            gpi player = f9382r;
            gog game = enemy.f20056k;
            float enemyTop = game.m9058b(enemy.f20034b);
            float enemyLeft = game.m9057a(enemy.f20035c);
            float playerTop = game.m9058b(player.f20034b);
            float playerLeft = game.m9057a(player.f20035c);
            if (RectF.intersects(
                    new RectF(enemyLeft, enemyTop, enemy.f20057l + enemyLeft, enemy.f20058m + enemyTop),
                    new RectF(playerLeft, playerTop, player.mo9065c() + playerLeft, player.mo9064b() + playerTop))) {
                f9382r.m9088h(f9383s.f20034b);
                f9385u.m9093a();
            }
            return;
        }
        if (state != 1 || !f9382r.f20140i) return;
        for (int i = f9390z.size() - 1; i >= 0; i--) {
            gpc platform = (gpc) f9390z.get(i);
            gpk item = platform.f20109i;
            if (item != null) {
                if (f9382r.m9069g(item)) {
                    item.f20151i = false;
                    f9382r.m9091k(1);
                    gpl sounds = ((gpj) item).f20149h;
                    sounds.f20157f.play(sounds.f20154c, 0.5f, 0.5f, 1, 0, 1.0f);
                }
            } else if (f9382r.m9069g(platform)) {
                platform.mo9080h(f9382r);
            }
        }
    }

    public void m5371u() { f9381q.f20027g = 0.0f; }

    public void m5372v() {
        f9382r.f20034b = (f9381q.f20026f / 2) - (f9382r.mo9064b() / 2);
        f9382r.f20035c = (f9381q.f20025e / 2) - f9382r.m9063a();
        f9382r.f20037e = 0.0f;
        f9382r.f20036d = 0.0f;
        f9382r.m9091k(0);
    }

    public void m5373w() {
        f9381q.f20024d = 1;
        f9384t.f20079e = 0;
        m5372v();
        m5371u();
        f9390z.clear();
        f9386v.f20130q = 0;
        f9371A = 1;
    }

    public void m5374x() {
        if (filteredAcceleration == null) return;
        float tilt = filteredAcceleration[0] * 3.0f;
        if (f9382r.m9092l()) return;
        float x = f9382r.f20035c - tilt;
        f9382r.f20035c = x;
        if (x > f9382r.f20139h.f20025e) {
            f9382r.f20035c = 0.0f;
        } else if (x < -f9382r.mo9065c()) {
            f9382r.f20035c = f9382r.f20139h.f20025e - f9382r.mo9065c();
        }
        f9382r.mo9067e();
    }
}
