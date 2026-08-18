package p000;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gok {

    /* JADX INFO: renamed from: a */
    private boolean f20042a = false;

    /* JADX INFO: renamed from: b */
    private long f20043b = 0;

    /* JADX INFO: renamed from: c */
    private int f20044c = 0;

    /* JADX INFO: renamed from: d */
    private int f20045d = 0;

    /* JADX INFO: renamed from: e */
    private List f20046e = new ArrayList();

    /* JADX INFO: renamed from: f */
    private long f20047f = 0;

    /* JADX INFO: renamed from: g */
    private long f20048g = 0;

    /* JADX INFO: renamed from: d */
    private final double m9071d(int i) {
        if (i >= this.f20046e.size()) {
            return 0.0d;
        }
        return ((Double) this.f20046e.get(i)).doubleValue();
    }

    /* JADX INFO: renamed from: a */
    public final float m9072a(float f) {
        if (!this.f20042a) {
            return f;
        }
        double d = (this.f20048g / 1000.0d) * ((double) this.f20044c);
        int iFloor = (int) Math.floor(d);
        long j = this.f20048g;
        long j2 = this.f20043b;
        return f + ((float) ((m9071d(iFloor) + ((d - ((double) iFloor)) * (m9071d(iFloor + 1) - m9071d(iFloor)))) * (j >= j2 ? 0.0d : (j2 - j) / j2) * ((double) this.f20045d)));
    }

    /* JADX INFO: renamed from: b */
    public final void m9073b(long j, int i, int i2) {
        this.f20043b = j;
        this.f20044c = i;
        this.f20045d = i2;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < ((int) ((j / 1000.0d) * ((double) i))); i3++) {
            double dRandom = Math.random();
            arrayList.add(Double.valueOf((dRandom + dRandom) - 1.0d));
        }
        this.f20046e = arrayList;
        this.f20047f = System.currentTimeMillis();
        this.f20048g = 0L;
        this.f20042a = true;
    }

    /* JADX INFO: renamed from: c */
    public final void m9074c() {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.f20047f;
        this.f20048g = jCurrentTimeMillis;
        if (jCurrentTimeMillis > this.f20043b) {
            this.f20042a = false;
        }
    }
}
