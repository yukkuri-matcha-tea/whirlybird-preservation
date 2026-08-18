package p000;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.google.android.play.games.R;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gpi extends goh {

    /* JADX INFO: renamed from: h */
    public final gog f20139h;

    /* JADX INFO: renamed from: i */
    public boolean f20140i;

    /* JADX INFO: renamed from: j */
    private final gol[] f20141j;

    /* JADX INFO: renamed from: k */
    private int f20142k;

    /* JADX INFO: renamed from: l */
    private int f20143l;

    /* JADX INFO: renamed from: m */
    private int f20144m;

    /* JADX INFO: renamed from: n */
    private float f20145n;

    /* JADX INFO: renamed from: o */
    private float f20146o;

    /* JADX INFO: renamed from: p */
    private float f20147p;

    /* JADX INFO: renamed from: q */
    private final float f20148q;

    public gpi(float f, float f2, gog gogVar) {
        super(new gon(BitmapFactory.decodeResource(gogVar.f20021a.getResources(), R.drawable.player), gogVar.m9060d(0.08f)), f, f2, gogVar);
        this.f20141j = new gol[4];
        this.f20142k = 0;
        this.f20143l = 0;
        this.f20144m = 0;
        this.f20145n = 0.0f;
        this.f20146o = 0.0f;
        this.f20147p = 0.0f;
        this.f20140i = false;
        Resources resources = gogVar.f20021a.getResources();
        float fM9060d = gogVar.m9060d(0.08f);
        this.f20141j[0] = new gon(BitmapFactory.decodeResource(resources, R.drawable.player), fM9060d);
        this.f20141j[1] = new goe(BitmapFactory.decodeResource(resources, R.drawable.player_noogler_hat_sheet), fM9060d, 3, 3, 60, true);
        goe goeVar = new goe(BitmapFactory.decodeResource(resources, R.drawable.player_death_sheet), fM9060d, 9, 9, 60, false);
        goeVar.f20013d = new gom() { // from class: gph
            @Override // p000.gom
            /* JADX INFO: renamed from: a */
            public final void mo9075a() {
                gpi.this.m9091k(4);
            }
        };
        this.f20141j[2] = goeVar;
        this.f20141j[3] = new goe(BitmapFactory.decodeResource(resources, R.drawable.player_falling_sheet), fM9060d, 2, 2, 60, true);
        this.f20038f = (-gogVar.f20026f) * 3.2E-4f;
        this.f20139h = gogVar;
        this.f20148q = gogVar.f20021a.getResources().getDimension(R.dimen.boingo_player_x_offset_threshold);
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: d */
    public final void mo9066d(Canvas canvas) {
        if (this.f20142k != 4) {
            super.mo9066d(canvas);
        }
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: e */
    public final void mo9067e() {
        super.mo9067e();
        if (this.f20142k == 0) {
            float f = this.f20145n;
            float f2 = this.f20146o;
            float f3 = this.f20035c;
            float f4 = f + (f2 - f3);
            this.f20145n = f4;
            float f5 = this.f20148q;
            if (f4 > f5 || f4 < (-f5)) {
                this.f20145n = 0.0f;
                int i = f3 < f2 ? 1 : 0;
                if (i != this.f20143l) {
                    gol[] golVarArr = this.f20141j;
                    for (int i2 = 0; i2 < 4; i2++) {
                        golVarArr[i2].mo9054d();
                    }
                }
                this.f20143l = i;
            }
        }
        gog gogVar = this.f20139h;
        if (gogVar.f20024d == 0) {
            this.f20140i = this.f20034b > this.f20147p;
        } else {
            this.f20140i = this.f20034b < this.f20147p;
        }
        this.f20146o = this.f20035c;
        this.f20147p = this.f20034b;
        int i3 = this.f20144m;
        if (i3 > 0) {
            this.f20144m = i3 - 1;
        } else if (this.f20142k != 0) {
            m9091k(0);
            this.f20038f = (-gogVar.f20026f) * 3.2E-4f;
        }
    }

    @Override // p000.goh
    /* JADX INFO: renamed from: f */
    protected final void mo9068f(RectF rectF) {
        gog gogVar = this.f20139h;
        float fM9058b = gogVar.m9058b(this.f20034b);
        float fM9057a = gogVar.m9057a(this.f20035c);
        rectF.top = (mo9064b() + fM9058b) - gogVar.m9059c(6);
        rectF.bottom = fM9058b + mo9064b();
        rectF.left = fM9057a;
        rectF.right = fM9057a + mo9065c();
    }

    /* JADX INFO: renamed from: h */
    public final void m9088h(float f) {
        m9089i(f, 0.0165f);
    }

    /* JADX INFO: renamed from: i */
    public final void m9089i(float f, float f2) {
        gog gogVar = this.f20139h;
        if (gogVar.f20024d != 0) {
            if (f < this.f20034b + mo9064b()) {
                this.f20034b = f + mo9064b();
            }
            this.f20037e = gogVar.f20026f * f2;
        } else {
            if (f > this.f20034b + mo9064b()) {
                this.f20034b = f - mo9064b();
            }
            this.f20037e = -(gogVar.f20026f * f2);
        }
        gogVar.f20023c.performHapticFeedback(3);
    }

    /* JADX INFO: renamed from: j */
    public final void m9090j() {
        if (m9092l()) {
            return;
        }
        m9091k(2);
        this.f20036d = 0.0f;
        this.f20037e = 0.0f;
        this.f20038f = 0.0f;
        this.f20139h.m9061e(300, 80, 24);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x004b  */
    /* JADX WARN: Code duplicated, block: B:22:0x0051  */
    /* JADX WARN: Code duplicated, block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: k */
    public final void m9091k(int i) {
        this.f20142k = i;
        if (i == 1) {
            this.f20144m = 100;
            this.f20038f = 0.0f;
            this.f20037e = this.f20139h.f20026f * 0.03f;
        } else {
            this.f20038f = (-this.f20139h.f20026f) * 3.2E-4f;
        }
        if (i >= 0 && i <= 3) {
            this.f20039g = this.f20141j[i];
        }
        gol current = this.f20039g;
        if (current instanceof goe) {
            goe animation = (goe) current;
            if (!animation.f20012c) {
                animation.m9055e();
            }
        }
    }

    /* JADX INFO: renamed from: l */
    public final boolean m9092l() {
        int i = this.f20142k;
        return i == 2 || i == 4;
    }
}
