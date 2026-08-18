package p000;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class got extends gpc {

    /* JADX INFO: renamed from: k */
    private final gog f20087k;

    /* JADX INFO: renamed from: l */
    private final gpl f20088l;

    public got(gol[] golVarArr, float f, float f2, gog gogVar, gpl gplVar) {
        super(golVarArr, f, f2, gogVar);
        this.f20087k = gogVar;
        this.f20088l = gplVar;
    }

    @Override // p000.gpc
    /* JADX INFO: renamed from: h */
    public final void mo9080h(gpi gpiVar) {
        gpiVar.m9088h(this.f20034b);
        this.f20087k.m9061e(100, 120, 8);
        gpl gplVar = this.f20088l;
        gplVar.f20157f.play(gplVar.f20155d, 0.5f, 0.5f, 1, 0, 1.0f);
        m9082i();
    }
}
