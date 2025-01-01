package me.joehosten.Sentinel.builder;

import me.joehosten.Sentinel.Sentinel;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class SentinelBuilder {
    private final JavaPlugin plugin;
    private String url;
    private String auth;
    private String key;
    private String product;
    private String platform;
    private String platformValue;

    public SentinelBuilder(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public SentinelBuilder url(@NotNull String url) {
        this.url = url;
        return this;
    }

    public SentinelBuilder auth(@NotNull String auth) {
        this.auth = auth;
        return this;
    }

    public SentinelBuilder key(@NotNull String key) {
        this.key = key;
        return this;
    }

    public SentinelBuilder product(@NotNull String product) {
        this.product = product;
        return this;
    }

    public SentinelBuilder platform(@NotNull String platform) {
        this.platform = platform;
        return this;
    }

    public SentinelBuilder platformValue(@NotNull String platformValue) {
        this.platformValue = platformValue;
        return this;
    }

    public Sentinel build() {
        return new Sentinel(this.plugin, this.url, this.auth, this.key, this.product, this.platform, this.platformValue);
    }
}