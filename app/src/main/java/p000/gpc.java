package p000;

import android.graphics.Canvas;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public abstract class gpc extends goh {

    /* JADX INFO: renamed from: h */
    public boolean f20108h;

    /* JADX INFO: renamed from: i */
    public gpk f20109i;

    /* JADX INFO: renamed from: j */
    public gpa f20110j;

    /* JADX INFO: renamed from: k */
    private final gol[] f20111k;

    /* JADX INFO: renamed from: l */
    private final gog f20112l;

    /* JADX INFO: renamed from: m */
    private boolean f20113m;

    public gpc(gol golVar, float f, float f2, gog gogVar) {
        super(golVar, f, f2, gogVar);
        this.f20108h = true;
        this.f20113m = false;
        this.f20111k = new gol[]{golVar};
        this.f20112l = gogVar;
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: d */
    public final void mo9066d(Canvas canvas) {
        if (this.f20108h) {
            super.mo9066d(canvas);
            gpk gpkVar = this.f20109i;
            if (gpkVar != null) {
                gpkVar.mo9066d(canvas);
            }
        }
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: e */
    public void mo9067e() {
        gpk gpkVar = this.f20109i;
        if (gpkVar != null) {
            gpkVar.f20035c = (this.f20035c + m9063a()) - this.f20109i.m9063a();
            int i = this.f20112l.f20024d;
            gpk gpkVar2 = this.f20109i;
            if (i == 0) {
                gpkVar2.f20034b = this.f20034b - gpkVar2.mo9064b();
            } else {
                gpkVar2.f20034b = this.f20034b + gpkVar2.mo9064b();
            }
            this.f20109i.mo9067e();
        }
        super.mo9067e();
    }

    /* JADX INFO: renamed from: h */
    public abstract void mo9080h(gpi gpiVar);

    /* JADX INFO: renamed from: i */
    public final void m9082i() {
        if (this.f20113m) {
            return;
        }
        this.f20113m = true;
        gpa gpaVar = new gpa(this);
        if (this.f20110j != null) {
            throw new IllegalArgumentException("Animation listener already exists on this Platform");
        }
        this.f20110j = gpaVar;
        m9083j();
    }

    /* JADX INFO: renamed from: j */
    public final void m9083j() {
        m9084k();
        gol golVar = this.f20039g;
        if (!(golVar instanceof goe)) {
            throw new IllegalStateException("Could not run collision animation. Is SPRITE_HIT an instance of AnimatedSprite?");
        }
        ((goe) golVar).m9055e();
        if (this.f20110j != null) {
            ((goe) this.f20039g).f20013d = new gpb(this);
        }
    }

    /* JADX INFO: renamed from: k */
    protected final void m9084k() {
        gol golVar = this.f20111k[1];
        if (golVar != null) {
            this.f20039g = golVar;
        }
    }

    public gpc(gol[] golVarArr, float f, float f2, gog gogVar) {
        super(golVarArr[0], f, f2, gogVar);
        this.f20108h = true;
        this.f20113m = false;
        this.f20111k = golVarArr;
        this.f20112l = gogVar;
    }
}
