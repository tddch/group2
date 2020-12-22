// Generated code from Butter Knife. Do not modify!
package com.example.mymvp.mvp.ui;

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

    target.vpMain = Utils.findRequiredViewAsType(source, R.id.vp_main, "field 'vpMain'", ViewPager.class);
    target.tabMain = Utils.findRequiredViewAsType(source, R.id.tab_main, "field 'tabMain'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vpMain = null;
    target.tabMain = null;
  }
}
