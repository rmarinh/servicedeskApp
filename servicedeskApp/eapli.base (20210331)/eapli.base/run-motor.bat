REM set the class path,
REM assumes the build was executed with maven copy-dependencies

SET BASE_CP=base.server.motorfluxoatividades\target\base.server.motorfluxoatividades-1.3.0-SNAPSHOT.jar;base.server.motorfluxoatividades\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% base.server.motorfluxoatividades.BaseMotorFluxoAtividadesDaemon
