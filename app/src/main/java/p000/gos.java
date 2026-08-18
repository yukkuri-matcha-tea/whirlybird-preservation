package p000;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: compiled from: PG */
/* JADX INFO: loaded from: classes2.dex */
public final class gos {

    /* JADX INFO: renamed from: a */
    private static final Set<String> f20086a = new HashSet<>(Arrays.asList("ar", "hy", "as", "bn", "chr", "brx", "hi", "kok", "mr", "ne", "am", "ti", "ka", "el", "gu", "pa", "iw", "id", "yi", "ji", "km", "lo", "agq", "kab", "jgo", "vi", "ml", "my", "or", "si", "ta", "te", "shi", "th", "dz", "bo", "jp", "zh", "ja"));

    /* JADX INFO: renamed from: a */
    public static Typeface m9079a(Context context) {
        Locale locale = Build.VERSION.SDK_INT >= 24
                ? context.getResources().getConfiguration().getLocales().get(0)
                : context.getResources().getConfiguration().locale;
        return !f20086a.contains(locale.getLanguage())
                ? Typeface.createFromAsset(context.getAssets(), "PressStart2P-Regular.ttf")
                : Typeface.create(Typeface.MONOSPACE, 0);
    }
}
