package p000;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gon implements gol {

    /* JADX INFO: renamed from: a */
    public final int f20049a;

    /* JADX INFO: renamed from: b */
    private final Paint f20050b = new Paint();

    /* JADX INFO: renamed from: c */
    private Bitmap f20051c;

    /* JADX INFO: renamed from: d */
    private final int f20052d;

    public gon(Bitmap bitmap, float f) {
        int i = (int) f;
        this.f20049a = i;
        int width = (int) ((f / bitmap.getWidth()) * bitmap.getHeight());
        this.f20052d = width;
        this.f20051c = Bitmap.createScaledBitmap(bitmap, i, width, false);
    }

    @Override // p000.gol
    /* JADX INFO: renamed from: a */
    public final int mo9051a() {
        return this.f20052d;
    }

    @Override // p000.gol
    /* JADX INFO: renamed from: b */
    public final int mo9052b() {
        return this.f20049a;
    }

    @Override // p000.gol
    /* JADX INFO: renamed from: c */
    public final void mo9053c(Canvas canvas, float f, float f2) {
        canvas.drawBitmap(this.f20051c, f2, f, this.f20050b);
    }

    @Override // p000.gol
    /* JADX INFO: renamed from: d */
    public final void mo9054d() {
        this.f20051c = goi.m9070a(this.f20051c);
    }
}
