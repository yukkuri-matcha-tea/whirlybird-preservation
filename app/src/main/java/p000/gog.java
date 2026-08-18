package p000;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.TypedValue;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.google.android.apps.play.games.features.eastereggs.boingo.BoingoGameActivity;
import com.google.android.play.games.R;
import java.util.Iterator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public class gog implements Runnable, View.OnTouchListener {

    /* JADX INFO: renamed from: i */
    private static final String f20020i = "Whirlybird";

    /* JADX INFO: renamed from: a */
    public final Activity f20021a;

    /* JADX INFO: renamed from: b */
    public volatile Thread f20022b;

    /* JADX INFO: renamed from: c */
    public final SurfaceView f20023c;

    /* JADX INFO: renamed from: d */
    public int f20024d = 0;

    /* JADX INFO: renamed from: e */
    public int f20025e = 0;

    /* JADX INFO: renamed from: f */
    public int f20026f = 0;

    /* JADX INFO: renamed from: g */
    public float f20027g = 0.0f;

    /* JADX INFO: renamed from: h */
    public boolean f20028h = false;

    /* JADX INFO: renamed from: j */
    private boolean f20029j = false;

    /* JADX INFO: renamed from: k */
    private final gok f20030k = new gok();

    /* JADX INFO: renamed from: l */
    private final gok f20031l = new gok();

    /* JADX INFO: renamed from: m */
    private final BoingoGameActivity f20032m;

    public gog(Activity activity, BoingoGameActivity boingoGameActivity) {
        this.f20021a = activity;
        this.f20032m = boingoGameActivity;
        SurfaceView surfaceView = new SurfaceView(activity);
        this.f20023c = surfaceView;
        activity.setContentView(surfaceView);
        surfaceView.setOnTouchListener(this);
    }

    /* JADX INFO: renamed from: a */
    public final float m9057a(float f) {
        return this.f20030k.m9072a(f + 0.0f);
    }

    /* JADX INFO: renamed from: b */
    public final float m9058b(float f) {
        return this.f20031l.m9072a(this.f20024d == 0 ? f - this.f20027g : this.f20026f - (f - this.f20027g));
    }

    /* JADX INFO: renamed from: c */
    public final int m9059c(int i) {
        return (int) TypedValue.applyDimension(1, i, this.f20021a.getApplicationContext().getResources().getDisplayMetrics());
    }

    /* JADX INFO: renamed from: d */
    public final int m9060d(float f) {
        return (int) (this.f20025e * f);
    }

    /* JADX INFO: renamed from: e */
    public final void m9061e(int i, int i2, int i3) {
        long j = i;
        this.f20030k.m9073b(j, i2, i3);
        this.f20031l.m9073b(j, i2, i3);
    }

    /* JADX INFO: renamed from: f */
    public final void m9062f() {
        boolean z;
        this.f20028h = false;
        Thread thread = this.f20022b;
        if (thread == null) {
            return;
        }
        // The original error path calls this method from the game-loop thread.
        // Joining the current thread deadlocks forever and hides the real fault.
        if (thread == Thread.currentThread()) {
            this.f20022b = null;
            return;
        }
        int i = 0;
        while (true) {
            z = true;
            if (i >= 3) {
                break;
            }
            try {
                thread.join();
                break;
            } catch (InterruptedException unused) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    Log.w(f20020i, "Failed on stop thread retry", e);
                }
                i++;
            }
        }
        while (z) {
            try {
                this.f20022b.join();
                z = false;
            } catch (InterruptedException e2) {
                Log.w(f20020i, "Unable to kill main thread", e2);
            }
        }
        this.f20022b = null;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.f20029j) {
            BoingoGameActivity boingoGameActivity = this.f20032m;
            int i = boingoGameActivity.f9371A;
            if (i != 0) {
                if (i == 2 && motionEvent.getAction() == 1) {
                    goh gohVar = boingoGameActivity.f9387w.f20059a;
                    gog gogVar = gohVar.f20033a;
                    if (gof.m9056a(gogVar.m9057a(gohVar.f20035c), gogVar.m9058b(gohVar.f20034b), gohVar.mo9065c(), gohVar.mo9064b(), gogVar.m9057a(motionEvent.getX()), gogVar.m9058b(motionEvent.getY()))) {
                        boingoGameActivity.m5373w();
                    }
                }
            } else if (motionEvent.getAction() == 1) {
                goo gooVar = boingoGameActivity.f9383s;
                gog gogVar2 = gooVar.f20056k;
                if (gof.m9056a(gogVar2.m9057a(gooVar.f20035c), gogVar2.m9058b(gooVar.f20034b), gooVar.f20057l, gooVar.f20058m, motionEvent.getX(), motionEvent.getY())) {
                    boingoGameActivity.m5373w();
                }
            }
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int[] iArr;
        gog gogVar;
        gpc govVar;
        gpc gouVar;
        gpc gotVar;
        gog gogVar2;
        gpc gpeVar;
        SurfaceHolder holder = this.f20023c.getHolder();
        while (this.f20028h && !this.f20022b.isInterrupted()) {
            if (holder.getSurface().isValid()) {
                try {
                    synchronized (holder) {
                        try {
                            Canvas canvasLockCanvas = holder.lockCanvas();
                            long jElapsedRealtime = SystemClock.elapsedRealtime();
                            int i3 = 10;
                            if (canvasLockCanvas != null) {
                                float f = 0.0f;
                                int i4 = 0;
                                int i5 = 1;
                                int i6 = 2;
                                if (this.f20029j) {
                                    BoingoGameActivity boingoGameActivity = this.f20032m;
                                    int i7 = boingoGameActivity.f9371A;
                                    if (i7 == 0) {
                                        i = 10;
                                        i2 = 1;
                                        boingoGameActivity.m5374x();
                                        boingoGameActivity.m5370t();
                                    } else if (i7 != 1) {
                                        if (i7 == 2) {
                                            gop gopVar = boingoGameActivity.f9387w;
                                            goh gohVar = gopVar.f20065g;
                                            gohVar.f20034b = gopVar.m9077b() - (gohVar.mo9064b() / 2);
                                            int iM9076a = gopVar.m9076a();
                                            int iMo9065c = gohVar.mo9065c();
                                            gohVar.f20035c = iM9076a - (iMo9065c + iMo9065c);
                                            goh gohVar2 = gopVar.f20066h;
                                            gohVar2.f20034b = gopVar.m9077b() - (gohVar.mo9064b() / 2);
                                            gohVar2.f20035c = gopVar.m9076a() + gohVar2.mo9065c();
                                            goh gohVar3 = gopVar.f20059a;
                                            gohVar3.f20034b = gopVar.m9077b() + (gopVar.f20067i / 2) + gohVar.mo9064b();
                                            gohVar3.f20035c = gopVar.m9076a() - gohVar3.m9063a();
                                        }
                                        i = 10;
                                        i2 = 1;
                                    } else {
                                        boingoGameActivity.m5374x();
                                        boingoGameActivity.m5370t();
                                        gog gogVar3 = boingoGameActivity.f9381q;
                                        int i8 = gogVar3.f20026f;
                                        double d = i8;
                                        float fM9058b = gogVar3.m9058b(boingoGameActivity.f9382r.f20034b);
                                        float f2 = i8 / 2;
                                        if (fM9058b >= f2 || boingoGameActivity.f9382r.f20140i) {
                                            float f3 = (float) (d * 0.9d);
                                            if (fM9058b > f3 && boingoGameActivity.f9382r.f20140i) {
                                                int i9 = boingoGameActivity.f9389y;
                                                if (i9 < 60) {
                                                    boingoGameActivity.f9381q.f20027g -= fM9058b - f3;
                                                }
                                                boingoGameActivity.f9389y = i9 + 1;
                                            } else if (!boingoGameActivity.f9382r.m9092l()) {
                                                boingoGameActivity.f9389y = 0;
                                            }
                                        } else {
                                            gog gogVar4 = boingoGameActivity.f9381q;
                                            float f4 = gogVar4.f20027g + (f2 - fM9058b);
                                            gogVar4.f20027g = f4;
                                            gor gorVar = boingoGameActivity.f9384t;
                                            gorVar.f20079e = Math.max((int) (f4 * gorVar.f20078d), gorVar.f20079e);
                                        }
                                        gpd gpdVar = boingoGameActivity.f9386v;
                                        List list = boingoGameActivity.f9390z;
                                        float f5 = boingoGameActivity.f9381q.f20027g;
                                        int i10 = boingoGameActivity.f9384t.f20079e;
                                        while (list.size() < i3 && f5 >= f) {
                                            float f6 = gpdVar.f20130q;
                                            int i11 = i3;
                                            if (i10 > 4000) {
                                                iArr = gow.f20094e;
                                            } else if (i10 > 2000) {
                                                iArr = gow.f20093d;
                                            } else if (i10 >= 1000) {
                                                iArr = gow.f20092c;
                                            } else {
                                                iArr = i10 >= 100 ? gow.f20091b : gow.f20090a;
                                            }
                                            int i12 = i5;
                                            List list2 = list;
                                            int i13 = iArr[(int) Math.floor(Math.random() * ((double) iArr.length))];
                                            int i14 = gpdVar.f20129p;
                                            Set set = gpd.f20114a;
                                            if (set.contains(Integer.valueOf(i13)) && set.contains(Integer.valueOf(i14))) {
                                                i13 = gow.f20095f[(int) Math.floor(Math.random() * 6.0d)];
                                            }
                                            int i15 = gpdVar.f20130q;
                                            gog gogVar5 = gpdVar.f20115b;
                                            gpdVar.f20130q = i15 + (gogVar5.f20026f / 10);
                                            switch (i13) {
                                                case 0:
                                                    gogVar = gogVar5;
                                                    gon gonVar = gpdVar.f20117d;
                                                    govVar = new gov(gonVar, f6, gpdVar.m9085a(gonVar.f20049a), gogVar, gpdVar.f20116c);
                                                    gpdVar.m9087c(govVar, i10);
                                                    gouVar = govVar;
                                                    gogVar2 = gogVar;
                                                    break;
                                                case 1:
                                                    gogVar = gogVar5;
                                                    goe goeVar = gpdVar.f20118e;
                                                    govVar = new goz(goeVar, f6, gpdVar.m9085a(goeVar.f20010a), gogVar, new goy(gpdVar.m9086b(gpdVar.f20130q)), gpdVar.f20116c);
                                                    gpdVar.m9087c(govVar, i10);
                                                    gouVar = govVar;
                                                    gogVar2 = gogVar;
                                                    break;
                                                case 2:
                                                    i4 = i4;
                                                    gol[] golVarArr = new gol[2];
                                                    gon gonVar2 = gpdVar.f20119f;
                                                    golVarArr[i4] = gonVar2;
                                                    golVarArr[i12] = gpdVar.f20120g;
                                                    gogVar = gogVar5;
                                                    gotVar = new got(golVarArr, f6, gpdVar.m9085a(gonVar2.f20049a), gogVar, gpdVar.f20116c);
                                                    gouVar = gotVar;
                                                    gogVar2 = gogVar;
                                                    break;
                                                case 3:
                                                    gogVar2 = gogVar5;
                                                    i4 = i4;
                                                    gol[] golVarArr2 = new gol[i6];
                                                    gon gonVar3 = gpdVar.f20121h;
                                                    golVarArr2[i4] = gonVar3;
                                                    golVarArr2[i12] = gpdVar.f20122i;
                                                    float fM9085a = gpdVar.m9085a(gonVar3.f20049a);
                                                    gpl gplVar = gpdVar.f20116c;
                                                    gouVar = new gou(golVarArr2, f6, fM9085a, gogVar2);
                                                    break;
                                                case 4:
                                                    goe goeVar2 = gpdVar.f20123j;
                                                    gpeVar = new gpe(goeVar2, f6, gpdVar.m9085a(goeVar2.f20010a), gogVar5, gpdVar.f20116c);
                                                    gogVar2 = gogVar5;
                                                    gouVar = gpeVar;
                                                    break;
                                                case 5:
                                                    gogVar = gogVar5;
                                                    i4 = i4;
                                                    goe goeVar3 = gpdVar.f20124k;
                                                    gotVar = new gox(goeVar3, f6, gpdVar.m9085a(goeVar3.f20010a), gogVar, gpdVar.f20116c);
                                                    gouVar = gotVar;
                                                    gogVar2 = gogVar;
                                                    break;
                                                case 6:
                                                    gogVar = gogVar5;
                                                    i4 = i4;
                                                    gol[] golVarArr3 = new gol[i6];
                                                    golVarArr3[i4] = gpdVar.f20125l;
                                                    gon gonVar4 = gpdVar.f20126m;
                                                    golVarArr3[i12] = gonVar4;
                                                    gotVar = new gpg(golVarArr3, f6, gpdVar.m9085a(gonVar4.f20049a), gogVar, new goy(gpdVar.m9086b(gpdVar.f20130q)), gpdVar.f20116c);
                                                    gouVar = gotVar;
                                                    gogVar2 = gogVar;
                                                    break;
                                                case 7:
                                                    gol[] golVarArr4 = new gol[i6];
                                                    gon gonVar5 = gpdVar.f20127n;
                                                    golVarArr4[i4] = gonVar5;
                                                    i4 = i4;
                                                    golVarArr4[i12] = gpdVar.f20128o;
                                                    gogVar = gogVar5;
                                                    gotVar = new gpf(golVarArr4, f6, gpdVar.m9085a(gonVar5.f20049a), gogVar, gpdVar.f20116c);
                                                    gouVar = gotVar;
                                                    gogVar2 = gogVar;
                                                    break;
                                                default:
                                                    gon gonVar6 = gpdVar.f20117d;
                                                    gpeVar = new gov(gonVar6, f6, gpdVar.m9085a(gonVar6.f20049a), gogVar5, gpdVar.f20116c);
                                                    gogVar2 = gogVar5;
                                                    gouVar = gpeVar;
                                                    break;
                                            }
                                            if (list2.isEmpty()) {
                                                gouVar.f20035c = (gogVar2.f20025e * 0.5f) - gouVar.m9063a();
                                            }
                                            gpdVar.f20129p = i13;
                                            list2.add(gouVar);
                                            list = list2;
                                            i3 = i11;
                                            i5 = i12;
                                            i4 = i4;
                                            f = 0.0f;
                                            i6 = 2;
                                        }
                                        i = i3;
                                        i2 = i5;
                                        List list3 = list;
                                        int i16 = i4;
                                        Iterator it = list3.iterator();
                                        while (it.hasNext()) {
                                            ((gpc) it.next()).mo9067e();
                                        }
                                        int size = list3.size();
                                        while (true) {
                                            size--;
                                            if (size >= 0) {
                                                gpc gpcVar = (gpc) list3.get(size);
                                                if (boingoGameActivity.f9381q.m9058b(gpcVar.f20034b) + gpcVar.mo9064b() > boingoGameActivity.f9381q.f20026f) {
                                                    list3.remove(size);
                                                }
                                            } else {
                                                if (boingoGameActivity.f9382r.m9092l()) {
                                                    boingoGameActivity.f9389y++;
                                                }
                                                if (boingoGameActivity.f9389y > 120) {
                                                    boingoGameActivity.f9381q.f20024d = i16;
                                                    boingoGameActivity.m5371u();
                                                    boingoGameActivity.f9371A = 2;
                                                    gor gorVar2 = boingoGameActivity.f9384t;
                                                    int i17 = gorVar2.f20079e;
                                                    if (i17 > gorVar2.f20080f) {
                                                        gorVar2.f20080f = i17;
                                                        SharedPreferences.Editor editorEdit = gorVar2.f20076b.edit();
                                                        editorEdit.putInt("high_score", gorVar2.f20080f);
                                                        editorEdit.apply();
                                                    }
                                                }
                                                 if (boingoGameActivity.f9389y > 30.0f && !boingoGameActivity.f9382r.m9092l()) {
                                                     boingoGameActivity.f9382r.m9091k(3);
                                                 }
                                                 // smali continues at :goto_e after the reverse-removal pass.
                                                 // JADX incorrectly emitted an infinite decrement loop here;
                                                 // after Integer.MIN_VALUE it wrapped to MAX_VALUE and crashed.
                                                 break;
                                             }
                                         }
                                    }
                                    canvasLockCanvas.drawColor(boingoGameActivity.f9380p);
                                    int i18 = boingoGameActivity.f9371A;
                                    if (i18 == 0) {
                                        boingoGameActivity.f9382r.mo9066d(canvasLockCanvas);
                                        goo gooVar = boingoGameActivity.f9383s;
                                        gog gogVar6 = gooVar.f20056k;
                                        float fM9058b2 = gogVar6.m9058b(gooVar.f20034b);
                                        float fM9057a = gogVar6.m9057a(gooVar.f20035c);
                                        Rect rect = new Rect();
                                        Paint paint = gooVar.f20053h;
                                        String str = gooVar.f20055j;
                                        paint.getTextBounds(str, 0, str.length(), rect);
                                        float f7 = gooVar.f20057l;
                                        float f8 = gooVar.f20058m;
                                        canvasLockCanvas.drawRect(fM9057a, fM9058b2, fM9057a + f7, fM9058b2 + f8, gooVar.f20054i);
                                        canvasLockCanvas.drawText(str, (fM9057a + (f7 / 2.0f)) - rect.centerX(), fM9058b2 + (f8 / 2.0f) + (rect.height() / 2), paint);
                                        boingoGameActivity.f9388x.mo9066d(canvasLockCanvas);
                                    } else if (i18 == i2) {
                                        Iterator it2 = boingoGameActivity.f9390z.iterator();
                                        while (it2.hasNext()) {
                                            ((gpc) it2.next()).mo9066d(canvasLockCanvas);
                                        }
                                        boingoGameActivity.f9382r.mo9066d(canvasLockCanvas);
                                        boingoGameActivity.f9384t.m9078a(canvasLockCanvas);
                                        gor gorVar3 = boingoGameActivity.f9384t;
                                        int i19 = gorVar3.f20080f;
                                        if (i19 > 0) {
                                            goq goqVar = gorVar3.f20077c;
                                            float f9 = (i19 / gorVar3.f20078d) + (gorVar3.f20075a.f20026f / 2);
                                            goqVar.f20073f = f9;
                                            goj gojVar = goqVar.f20074g;
                                            gog gogVar7 = goqVar.f20068a;
                                            canvasLockCanvas.drawLine(0.0f, gogVar7.m9058b(f9), gogVar7.f20025e, gogVar7.m9058b(goqVar.f20073f), gojVar.f20041a);
                                            String str2 = goqVar.f20070c;
                                            float f10 = gogVar7.f20025e;
                                            Paint paint2 = goqVar.f20069b;
                                            canvasLockCanvas.drawText(str2, f10 - (paint2.measureText(str2) + goqVar.f20072e), gogVar7.m9058b(goqVar.f20073f) - goqVar.f20071d, paint2);
                                        }
                                    } else if (i18 == 2) {
                                        gop gopVar2 = boingoGameActivity.f9387w;
                                        gog gogVar8 = gopVar2.f20060b;
                                        canvasLockCanvas.drawRect(0.0f, 0.0f, gogVar8.f20025e, gogVar8.f20026f, gopVar2.f20061c);
                                        String str3 = gopVar2.f20064f;
                                        int iM9077b = gopVar2.m9077b() - (gopVar2.f20067i / 2);
                                        goh gohVar4 = gopVar2.f20065g;
                                        int iMo9064b = iM9077b - gohVar4.mo9064b();
                                        Paint paint3 = gopVar2.f20062d;
                                        Rect rect2 = gopVar2.f20063e;
                                        paint3.getTextBounds("Ig", 0, 2, rect2);
                                        float fHeight = rect2.height();
                                        int i20 = 0;
                                        int i21 = 0;
                                        for (List listM18270f = Arrays.asList(str3.split("\n", -1)); i20 < listM18270f.size(); listM18270f = listM18270f) {
                                            int i22 = iMo9064b;
                                            canvasLockCanvas.drawText((String) listM18270f.get(i20), gopVar2.m9076a() - (paint3.measureText((String) listM18270f.get(i20)) / 2.0f), i22 + i21, paint3);
                                            i21 += (int) (((double) fHeight) * 1.2d);
                                            i20++;
                                            iMo9064b = i22;
                                        }
                                        gopVar2.f20059a.mo9066d(canvasLockCanvas);
                                        gohVar4.mo9066d(canvasLockCanvas);
                                        gopVar2.f20066h.mo9066d(canvasLockCanvas);
                                        boingoGameActivity.f9384t.m9078a(canvasLockCanvas);
                                    }
                                    this.f20030k.m9074c();
                                    this.f20031l.m9074c();
                                } else {
                                    i = 10;
                                    this.f20025e = canvasLockCanvas.getWidth();
                                    this.f20026f = canvasLockCanvas.getHeight();
                                    BoingoGameActivity boingoGameActivity2 = this.f20032m;
                                    if (!boingoGameActivity2.getIntent().hasExtra("com.google.android.gms.games.ui.mvpwip.PACKAGE_NAME")) {
                                        SurfaceView surfaceView = boingoGameActivity2.f9381q.f20023c;
                                        surfaceView.setAccessibilityLiveRegion(1);
                                        surfaceView.setContentDescription(boingoGameActivity2.getString(R.string.boingo_content_description_game_loaded));
                                    }
                                    gog gogVar9 = boingoGameActivity2.f9381q;
                                    gogVar9.f20024d = 1;
                                    boingoGameActivity2.f9382r = new gpi(gogVar9.f20026f / 2, gogVar9.f20025e / 2, gogVar9);
                                    boingoGameActivity2.f9388x = new goh(new goe(BitmapFactory.decodeResource(boingoGameActivity2.getResources(), R.drawable.device_tilt_sheet), boingoGameActivity2.f9381q.m9060d(0.15f), 7, 7, 160, true), 0.0f, 0.0f, boingoGameActivity2.f9381q);
                                    goh gohVar5 = boingoGameActivity2.f9388x;
                                    gohVar5.f20035c = (boingoGameActivity2.f9381q.f20025e / 2) - gohVar5.m9063a();
                                    gog gogVar10 = boingoGameActivity2.f9381q;
                                    if (gogVar10.f20024d == 0) {
                                        goh gohVar6 = boingoGameActivity2.f9388x;
                                        int iMo9064b2 = gohVar6.mo9064b();
                                        gohVar6.f20034b = iMo9064b2 + iMo9064b2;
                                    } else {
                                        goh gohVar7 = boingoGameActivity2.f9388x;
                                        int i23 = gogVar10.f20026f;
                                        int iMo9064b3 = gohVar7.mo9064b();
                                        gohVar7.f20034b = i23 - (iMo9064b3 + iMo9064b3);
                                    }
                                    String string = boingoGameActivity2.getString(R.string.boingo_start_button_label);
                                    Resources resources = boingoGameActivity2.getResources();
                                    Resources.Theme theme = boingoGameActivity2.getTheme();
                                    boingoGameActivity2.f9383s = new goo(string, resources.getColor(R.color.boingo_white, theme), boingoGameActivity2.getResources().getColor(R.color.boingo_grey, boingoGameActivity2.getTheme()), boingoGameActivity2.getResources().getDimensionPixelSize(R.dimen.boingo_start_button_y_pos), boingoGameActivity2.f9381q.f20025e, boingoGameActivity2.getResources().getDimensionPixelSize(R.dimen.boingo_start_button_height), boingoGameActivity2.f9381q);
                                    boingoGameActivity2.f9384t = new gor(boingoGameActivity2.f9381q);
                                    boingoGameActivity2.f9386v = new gpd(boingoGameActivity2.f9381q, boingoGameActivity2.f9385u);
                                    boingoGameActivity2.f9387w = new gop(boingoGameActivity2.f9381q);
                                    boingoGameActivity2.f9381q.f20024d = 1;
                                    boingoGameActivity2.m5372v();
                                    boingoGameActivity2.m5371u();
                                    boingoGameActivity2.f9371A = 0;
                                    this.f20029j = true;
                                }
                            } else {
                                i = 10;
                            }
                            if (canvasLockCanvas != null) {
                                holder.unlockCanvasAndPost(canvasLockCanvas);
                            }
                            long jElapsedRealtime2 = (int) (16 - (SystemClock.elapsedRealtime() - jElapsedRealtime));
                            if (jElapsedRealtime2 > 0) {
                                try {
                                    Thread.sleep(jElapsedRealtime2);
                                } catch (InterruptedException unused) {
                                    Log.w(f20020i, "Unable to delay the main game loop");
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } catch (Exception e) {
                    Log.e(f20020i, "Unable to render or update", e);
                    m9062f();
                }
            }
        }
    }
}
