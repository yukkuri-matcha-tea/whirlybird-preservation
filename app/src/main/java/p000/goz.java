package p000;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class goz extends gpc {

    /* JADX INFO: renamed from: k */
    private final gog f20103k;

    /* JADX INFO: renamed from: l */
    private final gpl f20104l;

    public goz(goe goeVar, float f, float f2, gog gogVar, goy goyVar, gpl gplVar) {
        super(goeVar, f, f2, gogVar);
        this.f20103k = gogVar;
        this.f20104l = gplVar;
        goyVar.m9081a(this, gogVar.m9059c(1));
    }

    @Override // p000.gpc, p000.goh
    /* JADX INFO: renamed from: e */
    public final void mo9067e() {
        float f = this.f20035c;
        if (f < 0.0f || f + mo9065c() > this.f20103k.f20025e) {
            this.f20036d = -this.f20036d;
        }
        super.mo9067e();
    }

    @Override // p000.gpc
    /* JADX INFO: renamed from: h */
    public final void mo9080h(gpi gpiVar) {
        gpiVar.m9088h(this.f20034b);
        this.f20104l.m9093a();
    }
}
