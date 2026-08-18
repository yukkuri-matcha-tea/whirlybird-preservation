package p000;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import com.google.android.play.games.R;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gop {

    /* JADX INFO: renamed from: a */
    public final goh f20059a;

    /* JADX INFO: renamed from: b */
    public final gog f20060b;

    /* JADX INFO: renamed from: c */
    public final Paint f20061c;

    /* JADX INFO: renamed from: d */
    public final Paint f20062d;

    /* JADX INFO: renamed from: e */
    public final Rect f20063e;

    /* JADX INFO: renamed from: f */
    public final String f20064f;

    /* JADX INFO: renamed from: g */
    public final goh f20065g;

    /* JADX INFO: renamed from: h */
    public final goh f20066h;

    /* JADX INFO: renamed from: i */
    public int f20067i;

    public gop(gog gogVar) {
        this.f20067i = 0;
        Resources resources = gogVar.f20021a.getResources();
        this.f20060b = gogVar;
        Paint paint = new Paint();
        this.f20061c = paint;
        Resources.Theme theme = gogVar.f20021a.getTheme();
        paint.setColor(resources.getColor(R.color.boingo_white, theme));
        Paint paint2 = new Paint();
        this.f20062d = paint2;
        paint2.setColor(resources.getColor(R.color.boingo_grey, gogVar.f20021a.getTheme()));
        paint2.setTextSize(resources.getDimensionPixelSize(R.dimen.boingo_game_over_text_size));
        paint2.setAntiAlias(true);
        paint2.setTypeface(gos.m9079a(gogVar.f20021a));
        this.f20063e = new Rect();
        this.f20064f = resources.getString(R.string.boingo_game_over_label);
        this.f20067i = resources.getDimensionPixelOffset(R.dimen.boingo_game_over_text_vertical_margin);
        goh gohVar = new goh(new gon(BitmapFactory.decodeResource(resources, R.drawable.ic_restart_pixel), gogVar.m9060d(0.1f)), 0.0f, 0.0f, gogVar);
        this.f20059a = gohVar;
        goh gohVar2 = new goh(new goe(BitmapFactory.decodeResource(resources, R.drawable.jump_plank_sheet), gogVar.m9060d(0.1f), 7, 7, 90, true), 0.0f, 0.0f, gogVar);
        this.f20065g = gohVar2;
        goh gohVar3 = new goh(new goe(BitmapFactory.decodeResource(resources, R.drawable.jump_crumbler_sheet), gogVar.m9060d(0.1f), 17, 17, 90, true), 0.0f, 0.0f, gogVar);
        this.f20066h = gohVar3;
        gohVar2.f20034b = m9077b() - (gohVar2.mo9064b() / 2);
        int iM9076a = m9076a();
        int iMo9065c = gohVar2.mo9065c();
        gohVar2.f20035c = iM9076a - (iMo9065c + iMo9065c);
        gohVar3.f20034b = m9077b() - (gohVar2.mo9064b() / 2);
        gohVar3.f20035c = m9076a() + gohVar3.mo9065c();
        gohVar.f20034b = m9077b() + (this.f20067i / 2) + gohVar2.mo9064b();
        gohVar.f20035c = m9076a() - gohVar.m9063a();
    }

    /* JADX INFO: renamed from: a */
    public final int m9076a() {
        return this.f20060b.f20025e / 2;
    }

    /* JADX INFO: renamed from: b */
    public final int m9077b() {
        return this.f20060b.f20026f / 2;
    }
}
