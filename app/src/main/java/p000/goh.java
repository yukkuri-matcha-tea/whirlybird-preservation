package p000;

import android.graphics.Canvas;
import android.graphics.RectF;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public class goh {

    /* JADX INFO: renamed from: a */
    public final gog f20033a;

    /* JADX INFO: renamed from: b */
    public float f20034b;

    /* JADX INFO: renamed from: c */
    public float f20035c;

    /* JADX INFO: renamed from: g */
    public gol f20039g;

    /* JADX INFO: renamed from: d */
    public float f20036d = 0.0f;

    /* JADX INFO: renamed from: e */
    public float f20037e = 0.0f;

    /* JADX INFO: renamed from: f */
    public float f20038f = 0.0f;

    /* JADX INFO: renamed from: h */
    private final RectF f20040h = new RectF();

    public goh(gol golVar, float f, float f2, gog gogVar) {
        this.f20039g = golVar;
        this.f20034b = f;
        this.f20035c = f2;
        this.f20033a = gogVar;
    }

    /* JADX INFO: renamed from: a */
    public final float m9063a() {
        return mo9065c() / 2;
    }

    /* JADX INFO: renamed from: b */
    public int mo9064b() {
        return this.f20039g.mo9051a();
    }

    /* JADX INFO: renamed from: c */
    public int mo9065c() {
        return this.f20039g.mo9052b();
    }

    /* JADX INFO: renamed from: d */
    public void mo9066d(Canvas canvas) {
        gog gogVar = this.f20033a;
        this.f20039g.mo9053c(canvas, gogVar.m9058b(this.f20034b), gogVar.m9057a(this.f20035c));
    }

    /* JADX INFO: renamed from: e */
    public void mo9067e() {
        float f = this.f20035c;
        float f2 = this.f20036d;
        this.f20035c = f + f2;
        float f3 = this.f20034b;
        float f4 = this.f20037e;
        this.f20034b = f3 + f4;
        this.f20036d = f2 + 0.0f;
        this.f20037e = f4 + this.f20038f;
        mo9068f(this.f20040h);
    }

    /* JADX INFO: renamed from: f */
    protected void mo9068f(RectF rectF) {
        gog gogVar = this.f20033a;
        float fM9058b = gogVar.m9058b(this.f20034b);
        float fM9057a = gogVar.m9057a(this.f20035c);
        rectF.top = fM9058b;
        rectF.left = fM9057a;
        rectF.right = fM9057a + mo9065c();
        rectF.bottom = fM9058b + mo9064b();
    }

    /* JADX INFO: renamed from: g */
    public final boolean m9069g(goh gohVar) {
        return RectF.intersects(this.f20040h, gohVar.f20040h);
    }
}
