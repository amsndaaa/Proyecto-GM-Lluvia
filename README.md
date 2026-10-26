# El Huaso con Bajón
Proyecto de **Programación Avanzada**
INF2236 · PUCV · Segundo semestre 2026
---
## Objetivo
El proyecto consiste en un videojuego desarrollado en Java con el motor **LibGDX**. Su objetivo es controlar al "Huaso", quien debe desplazarse por la pantalla recolectando alimentos dieciocheros para sumar puntos y esquivar elementos perjudiciales que le quitan vidas.
El juego cuenta principalmente con los siguientes 3 elementos:
* **Completos:** Otorgan 10 puntos por cada que se consuma.
* **Arepas:** Quitan 1 vida al ser consumidas por el jugador (de un total de 3 vidas).
* **Terremoto:** Ítem especial que aparece durante ráfagas de viento, recupera 1 vida y suma 50 puntos al consumirlo.
La dinámica incluye cambios en las condiciones del clima (viento dieciochero), el cual altera la trayectoria de caída de los objetos de línea recta a movimiento en zigzag.
## Grupo
| Amanda Letelier |
| Benjamín González |
| Martín Lira |
## Herramientas utilizadas
JDK 11.0.32 · Eclipse IDE · LibGDX · Gradle
---
## INSTRUCCIONES DE INSTALACIÓN Y USO
### Cómo abrir el proyecto
1. Extraer el ZIP en una carpeta de tu computador.
2. Abrir Eclipse.
3. Seleccionar `File` > `Import...`.
4. Elegir `Existing Gradle Project` (o `General` > `Existing Projects into Workspace`) y presionar `Next`.
5. En `Select root directory`, buscar la carpeta extraída del proyecto (`GameLluviaMenu2024`).
6. Seleccionar el proyecto y presionar `Finish`.
### Cómo ejecutar el proyecto
#### Desde Eclipse:
1. Ir a la carpeta `lwjgl3/src/main/java/puppy/code/lwjgl3/`.
2. Clic derecho en `Lwjgl3Launcher.java` > `Run As` > `Java Application`.
#### Desde Consola / Terminal:
* Ejecutar `.\gradlew.bat lwjgl3:run` (Windows) o `./gradlew lwjgl3:run` (Linux/Mac).
### Controles:
* Moverse: Flechas `Izquierda` / `Derecha` o teclas `A` / `D`.
* Pausar: Tecla `ESC`.
* Continuar: Clic en pantalla.
### El informe se encuentra en la ruta "docs/REPORTE PROYECTO.pdf"
