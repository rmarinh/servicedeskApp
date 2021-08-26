REM set the class path,
REM assumes the build was executed with maven copy-dependencies

SET BASE_CP=base.server.executortarefasautomaticas\target\base.server.executortarefasautomaticas-1.3.0-SNAPSHOT.jar;base.server.executortarefasautomaticas\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% base.server.executortarefasautomaticas.ExecutorTarefasAutomaticasDaemon
