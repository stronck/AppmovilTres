# AppmovilTres

Aplicación móvil Android desarrollada en Java. El proyecto muestra información de alimentos obtenida desde un archivo JSON publicado en GitHub y la presenta mediante una lista en la aplicación.

## Características

- Aplicación Android nativa desarrollada en Java.
- Consumo de datos JSON mediante una petición HTTP/HTTPS.
- Lista de alimentos con título, categoría y oferta.
- Adaptador RecyclerView para mostrar los elementos.
- Archivo JSON de datos ubicado en `data/foods.json`.
- APK de prueba incluido en la carpeta `apk/`.

## Tecnologías utilizadas

- Android
- Java 8
- Gradle
- AndroidX AppCompat
- Material Components
- ConstraintLayout
- Butter Knife
- JSON

## Requisitos

- Android Studio.
- JDK compatible con el proyecto.
- SDK de Android con API 29.
- Un dispositivo Android o un emulador.

La aplicación tiene como configuración mínima Android API 19 y como SDK objetivo API 29.

## Instalación

Clona el repositorio:

```bash
git clone https://github.com/stronck/AppmovilTres.git
cd AppmovilTres
```

Abre el proyecto en Android Studio y espera a que Gradle termine de sincronizar las dependencias.

## Compilar

Para generar el APK de depuración desde la terminal:

```bash
./gradlew assembleDebug
```

En Windows:

```bash
gradlew.bat assembleDebug
```

El APK generado se encuentra normalmente en:

```
app/build/outputs/apk/debug/app-debug.apk
```

También se incluye una versión del APK en:

```
apk/app-debug.apk
```

## Datos

La aplicación obtiene los datos desde:

```
https://raw.githubusercontent.com/stronck/AppmovilTres/main/data/foods.json
```

El servicio `FoodApiService` descarga el JSON y convierte cada elemento en un objeto `DataModel`.

Cada registro utiliza los campos:

- `title`
- `category`
- `offer`

## Estructura principal

```
AppmovilTres/
├── apk/
│   └── app-debug.apk
├── app/
│   ├── src/main/
│   │   ├── java/in/myinnos/swiggyanimation/
│   │   │   ├── DataModel.java
│   │   │   ├── FoodApiService.java
│   │   │   ├── MainActivity.java
│   │   │   └── RecyclerAdapter.java
│   │   └── res/
│   └── build.gradle
├── data/
│   └── foods.json
├── build.gradle
├── gradlew
├── gradlew.bat
└── settings.gradle
```

## Ejecución

1. Clona el repositorio.
2. Abre el proyecto en Android Studio.
3. Sincroniza Gradle.
4. Conecta un dispositivo Android o inicia un emulador.
5. Ejecuta la aplicación desde Android Studio.

La aplicación necesita permiso de Internet para consultar los datos del archivo JSON.

## Licencia

Proyecto educativo y de práctica.
