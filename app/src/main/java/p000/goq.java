package p000;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.google.android.play.games.R;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class goq {

    /* JADX INFO: renamed from: a */
    public final gog f20068a;

    /* JADX INFO: renamed from: b */
    public final Paint f20069b;

    /* JADX INFO: renamed from: c */
    public final String f20070c;

    /* JADX INFO: renamed from: d */
    public final float f20071d;

    /* JADX INFO: renamed from: e */
    public final float f20072e;

    /* JADX INFO: renamed from: f */
    public float f20073f = 0.0f;

    /* JADX INFO: renamed from: g */
    public final goj f20074g;

    public goq(int i, gog gogVar) {
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(gogVar.f20021a.getAssets(), "PressStart2P-Regular.ttf");
        this.f20068a = gogVar;
        this.f20070c = gogVar.f20021a.getString(R.string.boingo_high_score_marker_label);
        Paint paint = new Paint();
        this.f20069b = paint;
        paint.setColor(i);
        paint.setAntiAlias(true);
        paint.setTypeface(typefaceCreateFromAsset);
        paint.setTextSize(gogVar.f20021a.getResources().getDimensionPixelSize(R.dimen.boingo_high_score_marker_label_text_size));
        this.f20071d = gogVar.f20021a.getResources().getDimensionPixelOffset(R.dimen.boingo_high_score_marker_label_y_offset);
        this.f20072e = gogVar.f20021a.getResources().getDimensionPixelOffset(R.dimen.boingo_high_score_marker_label_x_offset);
        this.f20074g = new goj(i, Paint.Style.STROKE);
    }
}
