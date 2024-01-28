plugins {
    kotlin("jvm")
    id("tz.co.asoft.library")
    kotlin("plugin.serialization")
    application
}

application {
    mainClass.set("podium.MainKt")
}

kotlin {
    target {
        application()
    }

    sourceSets {
        val main by getting {
            dependencies {
                implementation(ktor.server.cio)
                implementation(ktor.server.cors)
                implementation(libs.lexi.config)
//                implementation(libs.raven.config)
                implementation(libs.sanity.local)
                implementation(libs.sanity.flix)
//                implementation(libs.grape.mongo)
                implementation(kotlinx.serialization.json)
            }
        }
    }
}

//dockate {
//    val username = "andylamax"
//    val token = "<github-token>"
//    image(environments, "frontend-monitor") {
//        val build = build(NODE_18_19_0_ALPINE_3_18)
//        run("apk update", "apk upgrade", "apk add git")
//        workdir("/app")
//        run("git clone https://$username:$token@github.com/picortex/pimono")
//        workdir("/app/pimono")
//        run("git checkout dev", "yarn prepare", "yarn update")
//        workdir("/app/pimono/apps/pimonitor-web")
//        copy("pimono/apps/pimonitor-web/.env") {
//            +"GOOGLE_PLACES_API_KEY=AIzaSyCAGu9tCG8cDaqmuaOoB4ooENNdlU"
//            +"PIMONITOR_NAMESPACE=pimonitor.development"
//            +"PIMONITOR_URL=http://localhost:1214"
//            +"PIMONITOR_CAPITAL=http://localhost:1304"
//            +"PIMONITOR_VERIFICATION_URL=http://localhost:1204/verify"
//            +"PIMONITOR_PASSWORD_RESET_URL=http://localhost:1204/reset-password"
//            +"POST_HOG_TOKEN=phc_X9hBknuEKWNmbjykmP2SP5Of9rOzLc4sCeDSJ75biX1"
//        }
//        run("yarn build")
//        blankline()
//
//        from(NODE_18_19_0_ALPINE_3_18)
//        copy(from = build, "/app/pimono/apps/pimonitor-web/dist", "/app")
//        expose(port = 8080)
//        cmd("npx", "http-serve", "/app", "--gzip", "--proxy", "http://localhost:8080?")
//    }
//
//    image(environments, "frontend-capital") {
//        val build = build(NODE_18_19_0_ALPINE_3_18)
//        run("apk update", "apk upgrade", "apk add git")
//        workdir("/app")
//        run("git clone https://$username:$token@github.com/picortex/pimono")
//        workdir("/app/pimono")
//        run("git checkout dev", "yarn prepare", "yarn update")
//        workdir("/app/pimono/apps/picapital-web")
//        copy("pimono/apps/picapital-web/.env") {
//            +"GOOGLE_PLACES_API_KEY=AIzaSyCAGu9tCG8cDaqmuaOoB4ooENNdlU"
//            +"PIMONITOR_NAMESPACE=pimonitor.development"
//            +"PIMONITOR_URL=http://localhost:1204"
//            +"PIMONITOR_CAPITAL=http://localhost:1304"
//            +"PIMONITOR_VERIFICATION_URL=http://localhost:1204/verify"
//            +"PIMONITOR_PASSWORD_RESET_URL=http://localhost:1204/reset-password"
//            +"POST_HOG_TOKEN=phc_X9hBknuEKWNmbjykmP2SP5Of9rOzLc4sCeDSJ75biX1"
//        }
//        run("yarn build")
//        blankline()
//
//        from(NODE_18_19_0_ALPINE_3_18)
//        copy(from = build, "/app/pimono/apps/picapital-web/dist", "/app")
//        expose(port = 8080)
//        cmd("npx", "http-serve", "/app", "--gzip", "--proxy", "http://localhost:8080?")
//    }
//
//    image(environments, "backend", dependsOn = tasks.named("installDist")) {
//        from(OPEN_JDK_22_JDK_SLIM)
//        expose(port = 8080)
//        from(layout.buildDirectory.dir("install/${project.name}")) {
//            copy("bin", "/app/bin")
//            copy("lib", "/app/lib")
//        }
//
//        copy("conf/config.toml") {
//            +"[brand]"
//            -"name = '${it.label()}'"
//            -"domain = '${it.domain(pistaging.versions.server.ip.get())}'"
//            -"address = '${it.address()}'"
//            -"color.background = '${it.isolate.color.background}'"
//            -"color.foreground = '${it.isolate.color.foreground}'"
//            it.isolate.socials.forEach { s ->
//                -"[[brand.social]]"
//                -"handle = '${s.key}'"
//                -"icon = '${s.value}'"
//            }
//
//            blankline()
//            database(
//                url = "mongodb://root:pass@mongo:27017/",
//                name = it.qualifier.dashed
//            )
//
//            blankline()
//            logging(level = "debug") {
//                console(format = "json")
//            }
//
//            mail(sender = "bus")
//            mail(sender = "console")
//
//            verification(
//                name = it.label(),
//                address = "reception.${it.isolate.name}@picortex.com",
//                subject = "Your ${it.label()} account verification",
//                template = "receptionless"
//            )
//
//            recovery(
//                name = it.label(),
//                address = "security.${it.isolate.name}@picortex.com",
//                subject = "Your ${it.label()} account recovery",
//                template = "receptionless"
//            )
//        }
//        volume("/app/root")
//        cmd("/app/bin/$name", "/app/conf/config.toml")
//    }
//
//    val stacks = compose(environments) {
//        version(3.8)
//
//        val (database, root) = volumes("database", "root")
//
//        val p = it.port()
//        mongo(username = "root", password = "pass", port = p.db) {
//            volume(database to "/data/db")
//        }
//
//        service(name = "backend", tasks.named("dockerImageBuildBackend${it.taskNameTrail}")) {
//            image("backend:$version")
//            restart("always")
//            port(p.monitor_backend, 8080)
//            volume(root to "/app/root")
//            dependsOn("mongo")
//        }
//
//        service(name = "frontend-monitor", tasks.named("dockerImageBuildFrontendMonitor${it.taskNameTrail}")) {
//            image("frontend-monitor:$version")
//            restart("always")
//            port(p.monitor_frontend, 8080)
//            dependsOn("backend")
//        }
//
//        service(name = "frontend-capital", tasks.named("dockerImageBuildFrontendCapital${it.taskNameTrail}")) {
//            image("frontend-capital:$version")
//            restart("always")
//            port(p.capital_frontend, 8080)
//            dependsOn("backend")
//        }
//    }
//
//    registry(
//        stacks,
//        name = "pistaging",
//        url = pistaging.versions.server.ip.map { "http://$it:1030" }.get(),
//        user = "depo",
//        pass = "DtUsZzcKptQcwrr3ylGu3u7iUyDSVBbd",
//        workdir = "/picortex",
//    )
//
//    registry(
//        stacks,
//        name = "legacy",
//        url = "http://192.168.1.109:1030",
//        user = "andylamax",
//        pass = "andymamson",
//        workdir = "/server",
//    )
//}