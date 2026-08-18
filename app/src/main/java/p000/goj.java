package p000;

import android.graphics.DashPathEffect;
import android.graphics.Paint;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class goj {

    /* JADX INFO: renamed from: a */
    public final Paint f20041a;

    public goj(int i, Paint.Style style) {
        Paint paint = new Paint();
        this.f20041a = paint;
        paint.setColor(i);
        paint.setStyle(style);
        paint.setStrokeWidth(20.0f);
        paint.setPathEffect(new DashPathEffect(new float[]{20.0f, 20.0f}, 0.0f));
    }
}
