package com.android.gamechangesproject.di;

import com.android.gamechangesproject.repository.IssuesRepository;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(IssuesRepository issuesRepository);
}
