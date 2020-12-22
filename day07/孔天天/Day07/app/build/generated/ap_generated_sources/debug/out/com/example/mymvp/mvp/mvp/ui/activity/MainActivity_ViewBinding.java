// Generated code from Butter Knife. Do not modify!
package com.example.mymvp.mvp.mvp.ui.activity;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.mymvp.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.mainActVp = Utils.findRequiredViewAsType(source, R.id.main_act_vp, "field 'mainActVp'", ViewPager.class);
    target.mainActTab = Utils.findRequiredViewAsType(source, R.id.main_act_tab, "field 'mainActTab'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mainActVp = null;
    target.mainActTab = null;
  }
}
