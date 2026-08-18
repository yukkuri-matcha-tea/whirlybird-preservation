package p000;

import android.graphics.RectF;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gpe extends gpc {

    /* JADX INFO: renamed from: k */
    private final gpl f20132k;

    /* JADX INFO: renamed from: l */
    private final gog f20133l;

    public gpe(goe goeVar, float f, float f2, gog gogVar, gpl gplVar) {
        super(goeVar, f, f2, gogVar);
        this.f20132k = gplVar;
        this.f20133l = gogVar;
        goeVar.m9055e();
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: f */
    protected final void mo9068f(RectF rectF) {
        gog gogVar = this.f20133l;
        float fM9058b = gogVar.m9058b(this.f20034b);
        float fM9057a = gogVar.m9057a(this.f20035c);
        rectF.top = fM9058b;
        rectF.bottom = fM9058b + gogVar.m9059c(8);
        rectF.left = fM9057a;
        rectF.right = fM9057a + mo9065c();
    }

    @Override // p000.gpc
    /* JADX INFO: renamed from: h */
    public final void mo9080h(gpi gpiVar) {
        if (!gpiVar.m9092l()) {
            this.f20132k.m9094b();
        }
        gpiVar.m9090j();
    }
}
