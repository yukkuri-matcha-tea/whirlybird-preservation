package p000;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;
import android.os.Build;
import com.google.android.play.games.R;
import java.text.NumberFormat;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gor {

    /* JADX INFO: renamed from: a */
    public final gog f20075a;

    /* JADX INFO: renamed from: b */
    public final SharedPreferences f20076b;

    /* JADX INFO: renamed from: c */
    public final goq f20077c;

    /* JADX INFO: renamed from: d */
    public final float f20078d;

    /* JADX INFO: renamed from: e */
    public int f20079e = 0;

    /* JADX INFO: renamed from: f */
    public int f20080f;

    /* JADX INFO: renamed from: g */
    private final Context f20081g;

    /* JADX INFO: renamed from: h */
    private final Resources f20082h;

    /* JADX INFO: renamed from: i */
    private final Paint f20083i;

    /* JADX INFO: renamed from: j */
    private final Paint f20084j;

    /* JADX INFO: renamed from: k */
    private final NumberFormat f20085k;

    public gor(gog gogVar) {
        this.f20080f = 0;
        this.f20075a = gogVar;
        Activity activity = gogVar.f20021a;
        this.f20081g = activity;
        Resources resources = activity.getResources();
        this.f20082h = resources;
        SharedPreferences sharedPreferences = activity.getSharedPreferences("com.google.android.apps.play.games.features.eastereggs.boingo", 0);
        this.f20076b = sharedPreferences;
        this.f20080f = sharedPreferences.getInt("high_score", 0);
        Paint paint = new Paint();
        this.f20083i = paint;
        paint.setTextSize(resources.getDimensionPixelSize(R.dimen.boingo_current_score_text_size));
        paint.setAntiAlias(true);
        paint.setTypeface(gos.m9079a(gogVar.f20021a));
        Resources.Theme theme = activity.getTheme();
        paint.setColor(resources.getColor(R.color.boingo_grey, theme));
        Paint paint2 = new Paint();
        this.f20084j = paint2;
        paint2.setColor(resources.getColor(R.color.boingo_white, activity.getTheme()));
        this.f20077c = new goq(resources.getColor(R.color.boingo_grey, activity.getTheme()), gogVar);
        this.f20078d = 500.0f / gogVar.f20026f;
        this.f20085k = NumberFormat.getInstance(Build.VERSION.SDK_INT >= 24
                ? activity.getResources().getConfiguration().getLocales().get(0)
                : activity.getResources().getConfiguration().locale);
    }

    /* JADX INFO: renamed from: a */
    public final void m9078a(Canvas canvas) {
        Resources resources = this.f20082h;
        float dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.boingo_current_score_text_left);
        String string = this.f20085k.format(this.f20079e);
        Rect rect = new Rect();
        if (this.f20080f > 0) {
            string = resources.getString(R.string.boingo_score_manager_counter_label, Integer.valueOf(this.f20079e), Integer.valueOf(this.f20080f));
        }
        gog gogVar = this.f20075a;
        canvas.drawRect(0.0f, 0.0f, gogVar.f20025e, resources.getDimensionPixelSize(R.dimen.boingo_current_score_container_height), this.f20084j);
        SurfaceView surfaceView = gogVar.f20023c;
        if (surfaceView.getLayoutDirection() != 1) {
            canvas.drawText(string, dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.boingo_current_score_text_top) + rect.height(), this.f20083i);
            return;
        }
        Paint paint = this.f20083i;
        paint.getTextBounds(string, 0, string.length(), rect);
        canvas.drawText(string, (gogVar.f20025e - paint.measureText(string)) - dimensionPixelSize, resources.getDimensionPixelSize(R.dimen.boingo_current_score_text_top) + rect.height(), paint);
    }
}
