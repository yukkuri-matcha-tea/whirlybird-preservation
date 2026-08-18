package p000;

import android.content.Context;
import android.media.SoundPool;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gpl {

    /* JADX INFO: renamed from: a */
    public int f20152a;

    /* JADX INFO: renamed from: b */
    public int f20153b;

    /* JADX INFO: renamed from: c */
    public int f20154c;

    /* JADX INFO: renamed from: d */
    public int f20155d;

    /* JADX INFO: renamed from: e */
    public int f20156e;

    /* JADX INFO: renamed from: f */
    public SoundPool f20157f;

    /* JADX INFO: renamed from: g */
    public final Context f20158g;

    public gpl(Context context) {
        this.f20158g = context;
    }

    /* JADX INFO: renamed from: a */
    public final void m9093a() {
        this.f20157f.play(this.f20153b, 0.5f, 0.5f, 1, 0, 1.0f);
    }

    /* JADX INFO: renamed from: b */
    public final void m9094b() {
        this.f20157f.play(this.f20152a, 0.5f, 0.5f, 1, 0, 1.0f);
    }
}
