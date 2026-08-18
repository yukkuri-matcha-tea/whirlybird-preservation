package p000;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gpf extends gpc {

    /* JADX INFO: renamed from: k */
    private final gpl f20134k;

    public gpf(gol[] golVarArr, float f, float f2, gog gogVar, gpl gplVar) {
        super(golVarArr, f, f2, gogVar);
        this.f20134k = gplVar;
    }

    @Override // p000.gpc
    /* JADX INFO: renamed from: h */
    public final void mo9080h(gpi gpiVar) {
        gpiVar.m9089i(this.f20034b, 0.03f);
        m9083j();
        gpl gplVar = this.f20134k;
        gplVar.f20157f.play(gplVar.f20156e, 0.5f, 0.5f, 1, 0, 1.0f);
    }
}
