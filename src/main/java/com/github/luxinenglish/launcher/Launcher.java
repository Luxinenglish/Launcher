package com.github.luxinenglish.launcher;

import com.github.luxinenglish.launcher.ui.PanelManager;
import com.github.luxinenglish.launcher.ui.panels.pages.App;
import com.github.luxinenglish.launcher.ui.panels.pages.Login;
import com.github.luxinenglish.launcher.utils.Helpers;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.model.AuthProfile;
import fr.litarvan.openauth.model.response.RefreshResponse;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;



public class Launcher extends Application {
    private static Launcher instance;
    private final ILogger logger;
    private final File launcherDir = Helpers.generateGamePath("launcher-fx");
    private final Saver saver;
    private PanelManager panelManager;
    private AuthProfile authProfile = null;

    public Launcher() {
        instance = this;
        this.logger = new Logger("[LauncherFX]", new File(this.launcherDir, "launcher.log"));
        if (!this.launcherDir.exists()) {
            if (!this.launcherDir.mkdir()) {
                this.logger.err("Unable to create launcher folder");
            }
        }

        saver = new Saver(new File(launcherDir, "config.properties"));
        saver.load();
    }

    public static Launcher getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) {
        this.logger.info("Starting launcher");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();

        if (this.isUserAlreadyLoggedIn()) {
            logger.info("Hello " + authProfile.getName());

            this.panelManager.showPanel(new App());
        } else {
            this.panelManager.showPanel(new Login());
        }
    }

    public boolean isUserAlreadyLoggedIn() {
        if (saver.get("accessToken") != null && saver.get("clientToken") != null) {
            Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

            try {
                RefreshResponse response = authenticator.refresh(saver.get("accessToken"), saver.get("clientToken"));
                saver.set("accessToken", response.getAccessToken());
                saver.set("clientToken", response.getClientToken());
                saver.save();
                this.authProfile = response.getSelectedProfile();

                return true;
            } catch (AuthenticationException ignored) {
                saver.remove("accessToken");
                saver.remove("clientToken");
                saver.save();
            }
        } else if (saver.get("offline-username") != null) {
            this.authProfile = new AuthProfile(saver.get("offline-username"), null);
            return true;
        }

        return false;
    }

    public AuthProfile getAuthProfile() {
        return authProfile;
    }

    public void setAuthProfile(AuthProfile authProfile) {
        this.authProfile = authProfile;
    }

    public ILogger getLogger() {
        return logger;
    }

    public Saver getSaver() {
        return saver;
    }
}