package p000;

import android.graphics.Paint;
import android.util.TypedValue;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class goo extends goh {

    /* JADX INFO: renamed from: h */
    public final Paint f20053h;

    /* JADX INFO: renamed from: i */
    public final Paint f20054i;

    /* JADX INFO: renamed from: j */
    public final String f20055j;

    /* JADX INFO: renamed from: k */
    public final gog f20056k;

    /* JADX INFO: renamed from: l */
    public final float f20057l;

    /* JADX INFO: renamed from: m */
    public final float f20058m;

    public goo(String str, int i, int i2, float f, float f2, float f3, gog gogVar) {
        super(null, f, 0.0f, gogVar);
        this.f20056k = gogVar;
        Paint paint = new Paint();
        this.f20054i = paint;
        paint.setColor(i2);
        Paint paint2 = new Paint();
        this.f20053h = paint2;
        paint2.setTypeface(gos.m9079a(gogVar.f20021a));
        paint2.setTextSize((int) TypedValue.applyDimension(2, 16.0f, gogVar.f20021a.getApplicationContext().getResources().getDisplayMetrics()));
        paint2.setAntiAlias(true);
        paint2.setColor(i);
        this.f20055j = str;
        this.f20057l = f2;
        this.f20058m = f3;
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: b */
    public final int mo9064b() {
        return (int) this.f20058m;
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: c */
    public final int mo9065c() {
        return (int) this.f20057l;
    }
}
