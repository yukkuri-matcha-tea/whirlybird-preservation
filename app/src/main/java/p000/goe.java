package p000;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class goe implements gol {

    /* JADX INFO: renamed from: a */
    public final int f20010a;

    /* JADX INFO: renamed from: c */
    public boolean f20012c;

    /* JADX INFO: renamed from: d */
    public gom f20013d;

    /* JADX INFO: renamed from: f */
    private final Bitmap[] f20015f;

    /* JADX INFO: renamed from: g */
    private final int f20016g;

    /* JADX INFO: renamed from: h */
    private final int f20017h;

    /* JADX INFO: renamed from: i */
    private long f20018i;

    /* JADX INFO: renamed from: j */
    private boolean f20019j;

    /* JADX INFO: renamed from: e */
    private final Paint f20014e = new Paint();

    /* JADX INFO: renamed from: b */
    public int f20011b = 0;

    public goe(Bitmap bitmap, float f, int i, int i2, int i3, boolean z) {
        this.f20018i = SystemClock.uptimeMillis();
        this.f20012c = false;
        this.f20016g = i3;
        this.f20019j = z;
        int i4 = i2 / i;
        int height = bitmap.getHeight() / i4;
        int width = bitmap.getWidth() / i;
        Bitmap[] bitmapArr = new Bitmap[i2];
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < i; i6++) {
                int i7 = (i * i5) + i6;
                if (i7 < i2) {
                    bitmapArr[i7] = Bitmap.createBitmap(bitmap, i6 * width, i5 * height, width, height);
                }
            }
        }
        this.f20015f = bitmapArr;
        this.f20010a = (int) f;
        this.f20017h = (int) ((f / bitmapArr[0].getWidth()) * bitmapArr[0].getHeight());
        for (int i8 = 0; i8 < i2; i8++) {
            Bitmap[] bitmapArr2 = this.f20015f;
            bitmapArr2[i8] = Bitmap.createScaledBitmap(bitmapArr2[i8], this.f20010a, this.f20017h, false);
        }
        this.f20018i = SystemClock.uptimeMillis();
        this.f20012c = true;
    }

    @Override // p000.gol
    /* JADX INFO: renamed from: a */
    public final int mo9051a() {
        return this.f20017h;
    }

    @Override // p000.gol
    /* JADX INFO: renamed from: b */
    public final int mo9052b() {
        return this.f20010a;
    }

    @Override // p000.gol
    /* JADX INFO: renamed from: c */
    public final void mo9053c(Canvas canvas, float f, float f2) {
        Bitmap[] bitmapArr = this.f20015f;
        canvas.drawBitmap(bitmapArr[this.f20011b], f2, f, this.f20014e);
        if (!this.f20012c || SystemClock.uptimeMillis() <= this.f20018i + ((long) this.f20016g)) {
            return;
        }
        this.f20018i = SystemClock.uptimeMillis();
        int i = this.f20011b;
        this.f20011b = i + 1;
        if (i + 2 > bitmapArr.length) {
            if (this.f20019j) {
                this.f20011b = 0;
                return;
            }
            gom gomVar = this.f20013d;
            if (gomVar != null) {
                gomVar.mo9075a();
            }
            this.f20012c = false;
            this.f20011b--;
        }
    }

    @Override // p000.gol
    /* JADX INFO: renamed from: d */
    public final void mo9054d() {
        int i = 0;
        while (true) {
            Bitmap[] bitmapArr = this.f20015f;
            if (i >= bitmapArr.length) {
                return;
            }
            bitmapArr[i] = goi.m9070a(bitmapArr[i]);
            i++;
        }
    }

    /* JADX INFO: renamed from: e */
    public final void m9055e() {
        this.f20012c = true;
        this.f20011b = 0;
    }
}
