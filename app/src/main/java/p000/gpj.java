package p000;

import android.graphics.RectF;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gpj extends gpk {

    /* JADX INFO: renamed from: h */
    public final gpl f20149h;

    /* JADX INFO: renamed from: j */
    private final gog f20150j;

    public gpj(goe goeVar, gog gogVar, gpl gplVar) {
        super(goeVar, gogVar);
        this.f20149h = gplVar;
        this.f20150j = gogVar;
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: f */
    protected final void mo9068f(RectF rectF) {
        super.mo9068f(rectF);
        float fM9059c = this.f20150j.m9059c(8);
        rectF.inset(fM9059c, fM9059c);
    }
}
