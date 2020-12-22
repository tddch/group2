// Generated by Dagger (https://dagger.dev).
package com.example.mymvp.mvp.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;

public final class OkManager_ProBuilderFactory implements Factory<OkHttpClient.Builder> {
  private final OkManager module;

  public OkManager_ProBuilderFactory(OkManager module) {
    this.module = module;
  }

  @Override
  public OkHttpClient.Builder get() {
    return proBuilder(module);
  }

  public static OkManager_ProBuilderFactory create(OkManager module) {
    return new OkManager_ProBuilderFactory(module);
  }

  public static OkHttpClient.Builder proBuilder(OkManager instance) {
    return Preconditions.checkNotNull(instance.proBuilder(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
