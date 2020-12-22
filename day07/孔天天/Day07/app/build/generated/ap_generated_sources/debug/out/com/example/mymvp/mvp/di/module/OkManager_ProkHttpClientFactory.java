// Generated by Dagger (https://dagger.dev).
package com.example.mymvp.mvp.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;

public final class OkManager_ProkHttpClientFactory implements Factory<OkHttpClient> {
  private final OkManager module;

  public OkManager_ProkHttpClientFactory(OkManager module) {
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return prokHttpClient(module);
  }

  public static OkManager_ProkHttpClientFactory create(OkManager module) {
    return new OkManager_ProkHttpClientFactory(module);
  }

  public static OkHttpClient prokHttpClient(OkManager instance) {
    return Preconditions.checkNotNull(instance.prokHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
