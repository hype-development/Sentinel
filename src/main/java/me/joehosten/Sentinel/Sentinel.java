package me.joehosten.Sentinel;

import dev.demeng.sentinel.wrapper.SentinelClient;
import dev.demeng.sentinel.wrapper.controller.LicenseController;
import dev.demeng.sentinel.wrapper.exception.*;
import dev.demeng.sentinel.wrapper.exception.unchecked.UnexpectedResponseException;
import me.joehosten.Sentinel.model.AuthenticationResult;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.logging.Logger;

public class Sentinel {
    private final AuthenticationResult result;
    private final Logger logger;

    public Sentinel(@NotNull JavaPlugin plugin, @NotNull String url, @NotNull String auth, @Nullable String key, @NotNull String product, @NotNull String platform, @NotNull String platformValue) {
        this.logger = plugin.getLogger();
        SentinelClient sentinel = new SentinelClient(url, auth);

        AuthenticationResult output;
        try {
            LicenseController controller = sentinel.getLicenseController();
            controller.auth(key, product, platform, platformValue, SentinelClient.getCurrentHwid(), SentinelClient.getCurrentIp());
            output = AuthenticationResult.SUCCESS;
        } catch (InvalidLicenseException var11) {
            output = AuthenticationResult.INVALID;
        } catch (ExpiredLicenseException var12) {
            output = AuthenticationResult.EXPIRED;
        } catch (BlacklistedLicenseException var13) {
            output = AuthenticationResult.BLACKLISTED;
        } catch (ExcessiveServersException var14) {
            output = AuthenticationResult.EXCESSIVE_SERVERS;
        } catch (ExcessiveIpsException var15) {
            output = AuthenticationResult.EXCESSIVE_IPS;
        } catch (InvalidProductException var16) {
            output = AuthenticationResult.INVALID_PRODUCT;
        } catch (InvalidPlatformException var17) {
            output = AuthenticationResult.UNKNOWN_DOWNLOAD_ID;
        } catch (ConnectionMismatchException var18) {
            output = AuthenticationResult.UNKNOWN_CONNECTION;
        } catch (IOException | UnexpectedResponseException var19) {
            output = AuthenticationResult.UNEXPECTED;
        }

        this.result = output;
    }

    public boolean authenticate() {
        this.handleLicenseResult(this.result);
        return this.result == AuthenticationResult.SUCCESS;
    }

    public @NotNull AuthenticationResult result() {
        return this.result;
    }

    private void handleLicenseResult(@NotNull AuthenticationResult result) {
        this.logger.severe(result.message());
    }
}
