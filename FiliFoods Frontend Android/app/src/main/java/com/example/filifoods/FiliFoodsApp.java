package com.example.filifoods;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FiliFoodsApp extends Application {
    ExecutorService srv  = Executors.newCachedThreadPool();

}
