package com.company.servlets.context;

import com.company.servlets.ConnectionDB;
import com.company.servlets.services.AuthCheck;
import com.company.servlets.services.AuthService;
import com.company.servlets.services.RegistrateService;
import com.company.servlets.services.UserRepoService;

public class ApplicationContextReflBased implements ApplicationContext{
    public final AuthService authService;
    public final AuthCheck authCheck;
    public final RegistrateService registrateService;
    public final UserRepoService repoService;
    public final ConnectionDB connectionDB;

    public ApplicationContextReflBased(AuthService authService, AuthCheck authCheck, RegistrateService registrateService, UserRepoService repoService, ConnectionDB connectionDB) {
        this.authService = authService;
        this.authCheck = authCheck;
        this.registrateService = registrateService;
        this.repoService = repoService;
        this.connectionDB = connectionDB;
    }
}
