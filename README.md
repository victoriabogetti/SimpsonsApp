2do Parcial - Parte Practica
Que se solicita:

El codigo tiene 10 errores. Recae en usted analizar que es un error dentro del codigo.
Los Alumnos tendran que forkear este repo como propio, hacer un issue desde Github con Comentarios refiriendo en que linea esta el error, y como se debe solucionar.
La respuesta sera con el link a ese Fork, y adentro deben estar los issues. Los profesores tenemos que poder ingresar al mismo. Recae en los alumnos asegurarse de que los profesores puedan ingresar.
Tambien pueden editar el Archivo Readme y poner los resultados dentro de sus propios forks.
https://github.com/ExBattou/SimpsonsApp


LOS ERRORES SON LOS SIGUIENTES:

Error 1: Uso de class en vez de data class en Entity de Room
Archivo: app/src/main/java/com/example/simpsonsapp/data/local/entity/EpisodeEntity.kt 
Línea(s): 7
Código actual:
Kotlin
class EpisodeEntity(
Qué está mal: La entidad de Room está definida como una class estándar. En Android/Kotlin, las entidades de base de datos deben ser data class para que Room (y herramientas de comparación como DiffUtil en la UI) puedan comparar objetos por sus valores (vía equals/hashCode generados automáticamente). Como class común, la comparación fallará y la UI no se actualizará correctamente ante cambios.
Cómo se debería solucionar: Cambiar la palabra clave class por data class.

Error 2: Uso de class en vez de data class en Entity de Room
Archivo: app/src/main/java/com/example/simpsonsapp/data/local/entity/RemoteKeyEntity.kt
Línea(s): 7
Código actual:
Kotlin
class RemoteKeyEntity(
Qué está mal: Al igual que con EpisodeEntity, esta clase se utiliza para persistir las claves de paginación y debe ser una data class para asegurar un comportamiento correcto en las operaciones de base de datos y comparaciones.
Cómo se debería solucionar: Cambiar la palabra clave class por data class.

Error 3: Nombre de método en snake_case en interfaz Kotlin
Archivo: app/src/main/java/com/example/simpsonsapp/domain/repository/EpisodeRepository.kt
Línea(s): 8
Código actual:
Kotlin
fun get_episodes(): Flow<PagingData<Episode>>
Qué está mal: El método usa snake_case (get_episodes), lo cual rompe las convenciones de nomenclatura de Kotlin (camelCase). Además, esto genera un error de compilación inmediato en la clase que implementa esta interfaz si dicha clase intenta usar el nombre correcto en camelCase.
Cómo se debería solucionar: Renombrar el método a getEpisodes() para seguir las convenciones y coincidir con la implementación.

Error 4: Discrepancia de nombres entre Interfaz e Implementación (Error de compilación)
Archivo: app/src/main/java/com/example/simpsonsapp/data/repository/EpisodeRepositoryImpl.kt
Línea(s): 20
Código actual:
Kotlin
override fun getEpisodes(): Flow<PagingData<Episode>> {
Qué está mal: La clase intenta hacer un override de getEpisodes(), pero este método no existe en la interfaz EpisodeRepository (donde se llamó get_episodes()). El código NO COMPILA porque la implementación no cumple con el contrato de la interfaz.
Cómo se debería solucionar: Asegurar que el nombre en el Repositorio coincida exactamente con el definido en la Interfaz (preferentemente cambiando ambos a getEpisodes).

Error 5: Configuración de Retrofit incompleta (Missing Base URL)
Archivo: app/src/main/java/com/example/simpsonsapp/di/DataModule.kt
Línea(s): 36-39
Código actual:
Kotlin
return Retrofit.Builder()
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
Qué está mal: El Retrofit.Builder no llama al método .baseUrl(...). Esto provocará una excepción IllegalArgumentException: Base URL required. en tiempo de ejecución al intentar construir la instancia de Retrofit. La app crashea al iniciar.
Cómo se debería solucionar: Agregar la llamada .baseUrl("https://thesimpsonsapi.com/") (o la URL que corresponda) antes de .build().

Error 6: URL absoluta en anotación de Retrofit
Archivo: app/src/main/java/com/example/simpsonsapp/data/remote/EpisodeRemoteMediator.kt
Línea(s): 115
Código actual:
Kotlin
@GET("https://thesimpsonsapi.com/api/episodes")
Qué está mal: Se está utilizando una URL completa con "https://" dentro de la anotación @GET. Las rutas en las interfaces de Retrofit deben ser relativas al baseUrl configurado en el cliente. Usar URLs completas aquí rompe la flexibilidad y la configuración centralizada de la API.
Cómo se debería solucionar: Cambiar el valor a una ruta relativa, por ejemplo: "api/episodes".

Error 7: Ruta de Java Home hardcodeada en configuración del proyecto
Archivo: gradle.properties
Línea(s): 33
Código actual:
Properties
properties
org.gradle.java.home=/opt/homebrew/Cellar/openjdk@17/17.0.15/libexec/openjdk.jdk/Contents/Home
Qué está mal: Se ha definido org.gradle.java.home con una ruta absoluta específica del sistema de archivos de una máquina particular (/Users/victoriabogetti/...). Esto impedirá que el proyecto compile en cualquier otra computadora donde Java no esté instalado exactamente en esa misma ruta.
Cómo se debería solucionar: Eliminar esa línea del archivo gradle.properties para permitir que el IDE o el sistema utilicen la variable de entorno JAVA_HOME estándar.

Error 8: Importación con wildcard redundante o inútil
Archivo: app/src/main/java/com/example/simpsonsapp/AppNavigation.kt
Línea(s): 9
Código actual:
Kotlin
import androidx.navigation.*
Qué está mal: Se está utilizando un wildcard import (*) para el paquete de navegación. Esto es una mala práctica y, en este archivo, resulta innecesario ya que se podrían importar solo las clases específicas utilizadas (NavHost, composable, etc.), evitando colisiones de nombres y mejorando la claridad.
Cómo se debería solucionar: Reemplazar el wildcard por imports específicos para cada clase de navegación utilizada.

Error 9: Importación con wildcard inútil
Archivo: app/src/main/java/com/example/simpsonsapp/AppNavigation.kt
Línea(s): 10
Código actual:
Kotlin
import androidx.compose.*
Qué está mal: El paquete androidx.compose es un paquete base que no suele contener clases directas que se utilicen mediante wildcards de esta manera; generalmente se importan subpaquetes como androidx.compose.runtime.* o clases específicas de UI. Este import no aporta nada al archivo.
Cómo se debería solucionar: Eliminar el import y usar imports específicos para las funciones de Compose requeridas.

Error 10: Bloque init y código huérfano fuera de clase
Archivo: app/src/main/java/com/example/simpsonsapp/domain/model/Episode.kt
Línea(s): 13-15
Código actual:
Kotlin
init {
    return Episode; //NO BORRAR
}
Qué está mal: Este fragmento contiene múltiples errores de sintaxis de Kotlin que rompen la compilación:
1.
Bloque huérfano: El bloque init está declarado fuera del cuerpo de la clase Episode. En Kotlin, los bloques de inicialización deben estar obligatoriamente dentro de una clase.
2.
Uso de return prohibido: No se permite el uso de la sentencia return dentro de un bloque init, ya que estos bloques no son funciones y no devuelven valores.
3.
Sintaxis de retorno inválida: Intenta retornar Episode (el nombre de la clase/tipo), lo cual es semánticamente incorrecto.
4.
Punto y coma innecesario: Incluye un ; al final de la sentencia, que aunque no siempre rompe el código, aquí refuerza el error de estructura.
Cómo se debería solucionar: Se debe eliminar completamente el bloque init que está fuera de la clase. Si se requiere alguna validación al instanciar el modelo, esta debe moverse dentro de las llaves { } de la data class Episode.
