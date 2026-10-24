# Dragon Ball Mod (dbzmod) — Minecraft 1.21.1 / Fabric

## Requisitos
- JDK 21 (Temurin de Adoptium funciona bien)
- Gradle (cualquier version reciente, solo para generar el wrapper)

## Compilar y probar

1. Genera el wrapper de Gradle (falta el .jar binario en este zip):
   ```
   gradle wrapper
   ```
2. Descarga las dependencias y mapea el codigo (la primera vez tarda varios minutos):
   ```
   ./gradlew genSources
   ```
3. Abre el juego de prueba con el mod cargado:
   ```
   ./gradlew runClient
   ```
   (En Windows: gradlew.bat runClient)
4. Para generar el .jar instalable en tu Minecraft normal:
   ```
   ./gradlew build
   ```
   El jar queda en build/libs/dragonball-mod-1.0.0.jar
   Copialo a tu carpeta .minecraft/mods (necesitas Fabric Loader y Fabric API
   instalados para la 1.21.1).

## Controles
- Click derecho con el Ki Blaster: dispara una rafaga de ki
- Tecla K: activa el Kaio-ken (velocidad, fuerza y salto x2 por 30 segundos)
- Busca los objetos en el grupo creativo "Dragon Ball"

## Personalizacion
- Sonidos: lee src/main/resources/assets/dbzmod/sounds/README-AUDIO.txt
- Musica de fondo: agrega un MusicDiscItem con tu .ogg siguiendo el mismo patron
- Animaciones 3D de personajes: instala la dependencia GeckoLib y crea entidades
  AnimatedEntity con archivos .animation.json (proximo paso, pideme el codigo)
