package p000;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import com.google.android.play.games.R;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gpd {

    /* JADX INFO: renamed from: a */
    public static final Set f20114a = Collections.unmodifiableSet(new HashSet(Arrays.asList(4, 3)));

    /* JADX INFO: renamed from: b */
    public final gog f20115b;

    /* JADX INFO: renamed from: c */
    public final gpl f20116c;

    /* JADX INFO: renamed from: d */
    public final gon f20117d;

    /* JADX INFO: renamed from: e */
    public final goe f20118e;

    /* JADX INFO: renamed from: f */
    public final gon f20119f;

    /* JADX INFO: renamed from: g */
    public final goe f20120g;

    /* JADX INFO: renamed from: h */
    public final gon f20121h;

    /* JADX INFO: renamed from: i */
    public final goe f20122i;

    /* JADX INFO: renamed from: j */
    public final goe f20123j;

    /* JADX INFO: renamed from: k */
    public final goe f20124k;

    /* JADX INFO: renamed from: l */
    public final goe f20125l;

    /* JADX INFO: renamed from: m */
    public final gon f20126m;

    /* JADX INFO: renamed from: n */
    public final gon f20127n;

    /* JADX INFO: renamed from: o */
    public final goe f20128o;

    /* JADX INFO: renamed from: p */
    public int f20129p;

    /* JADX INFO: renamed from: q */
    public int f20130q = 0;

    /* JADX INFO: renamed from: r */
    private final goe f20131r;

    public gpd(gog gogVar, gpl gplVar) {
        Resources resources = gogVar.f20021a.getResources();
        this.f20115b = gogVar;
        this.f20116c = gplVar;
        this.f20117d = new gon(BitmapFactory.decodeResource(resources, R.drawable.normal_platform), gogVar.m9060d(0.08f));
        this.f20118e = new goe(BitmapFactory.decodeResource(resources, R.drawable.moving_platform_sheet), gogVar.m9060d(0.08f), 2, 2, 60, true);
        this.f20119f = new gon(BitmapFactory.decodeResource(resources, R.drawable.breakable_platform), gogVar.m9060d(0.08f));
        this.f20120g = new goe(BitmapFactory.decodeResource(resources, R.drawable.breakable_platform_sheet), gogVar.m9060d(0.08f), 4, 4, 60, false);
        this.f20121h = new gon(BitmapFactory.decodeResource(resources, R.drawable.cloud_platform), gogVar.m9060d(0.08f));
        this.f20122i = new goe(BitmapFactory.decodeResource(resources, R.drawable.cloud_platform_sheet), gogVar.m9060d(0.08f), 7, 7, 20, false);
        this.f20123j = new goe(BitmapFactory.decodeResource(resources, R.drawable.spikes_platform_sheet), gogVar.m9060d(0.08f), 8, 8, 60, true);
        this.f20124k = new goe(BitmapFactory.decodeResource(resources, R.drawable.hider_platform_sheet), gogVar.m9060d(0.08f), 10, 10, 120, true);
        this.f20125l = new goe(BitmapFactory.decodeResource(resources, R.drawable.stickler_sheet), gogVar.m9060d(0.08f), 7, 7, 60, true);
        this.f20126m = new gon(BitmapFactory.decodeResource(resources, R.drawable.stickler_hit), gogVar.m9060d(0.08f));
        this.f20127n = new gon(BitmapFactory.decodeResource(resources, R.drawable.spring), gogVar.m9060d(0.068f));
        this.f20128o = new goe(BitmapFactory.decodeResource(resources, R.drawable.spring_sheet), gogVar.m9060d(0.068f), 4, 4, 30, false);
        this.f20131r = new goe(BitmapFactory.decodeResource(resources, R.drawable.noogler_hat_sheet), gogVar.m9060d(0.062f), 4, 4, 60, true);
    }

    /* JADX INFO: renamed from: a */
    public final float m9085a(int i) {
        return (this.f20115b.f20025e - i) * ((new SecureRandom().nextFloat() * 0.6f) + 0.2f);
    }

    /* JADX INFO: renamed from: b */
    public final int m9086b(int i) {
        return i % 2;
    }

    /* JADX INFO: renamed from: c */
    public final void m9087c(gpc gpcVar, int i) {
        int[] iArr;
        if (i > 4000) {
            iArr = gow.f20100k;
        } else if (i > 2000) {
            iArr = gow.f20099j;
        } else if (i >= 1000) {
            iArr = gow.f20098i;
        } else {
            iArr = i >= 100 ? gow.f20097h : gow.f20096g;
        }
        if (iArr[(int) Math.floor(Math.random() * ((double) iArr.length))] != 1) {
            return;
        }
        gpcVar.f20109i = new gpj(this.f20131r, this.f20115b, this.f20116c);
    }
}
