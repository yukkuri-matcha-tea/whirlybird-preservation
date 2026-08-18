package p000;

import android.graphics.RectF;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gpg extends gpc {

    /* JADX INFO: renamed from: k */
    private final gog f20135k;

    /* JADX INFO: renamed from: l */
    private final gpl f20136l;

    /* JADX INFO: renamed from: m */
    private boolean f20137m;

    public gpg(gol[] golVarArr, float f, float f2, gog gogVar, goy goyVar, gpl gplVar) {
        super(golVarArr, f, f2, gogVar);
        this.f20137m = false;
        this.f20135k = gogVar;
        this.f20136l = gplVar;
        goyVar.m9081a(this, gogVar.m9059c(1));
        gol golVar = this.f20039g;
        if (golVar instanceof goe) {
            ((goe) golVar).m9055e();
        }
    }

    @Override // p000.gpc, p000.goh
    /* JADX INFO: renamed from: e */
    public final void mo9067e() {
        float f = this.f20035c;
        if (f < 0.0f || f + mo9065c() > this.f20135k.f20025e) {
            this.f20036d = -this.f20036d;
        }
        super.mo9067e();
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: f */
    protected final void mo9068f(RectF rectF) {
        super.mo9068f(rectF);
        float fM9059c = this.f20135k.m9059c(8);
        rectF.inset(fM9059c, fM9059c);
    }

    @Override // p000.gpc
    /* JADX INFO: renamed from: h */
    public final void mo9080h(gpi gpiVar) {
        gol golVar = this.f20039g;
        if ((golVar instanceof goe) && ((gpc) this).f20108h && ((goe) golVar).f20011b < 2 && !gpiVar.m9092l()) {
            gpiVar.m9090j();
            this.f20136l.m9094b();
        } else {
            if (this.f20137m) {
                return;
            }
            this.f20136l.m9093a();
            gpiVar.m9088h(this.f20034b);
            this.f20137m = true;
            this.f20038f = (-this.f20135k.f20026f) * 3.2E-4f;
            m9084k();
        }
    }
}
