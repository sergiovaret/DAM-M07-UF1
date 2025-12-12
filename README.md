Guía para el alumno

El alumno debe completar la práctica final enunciada en este documento y entregarla antes del cierre programado en el calendario académico.

Los entregables son:
Un archivo comprimido (ZIP) con el proyecto Android completo, incluyendo todo el código fuente de la aplicación desarrollada.
Un documento PDF con las capturas de pantalla solicitadas (evidencias visuales de la aplicación, identificadas como [CSnn] según se indica en cada tarea).
El archivo .AAB firmado (Android App Bundle de la aplicación) listo para una hipotética publicación.
La URL del repositorio GitHub público que contiene el código fuente del proyecto (incluir en el entregable [TX01]).

Asegúrate de incluir todos los elementos anteriores en la entrega y no adjuntar ficheros adicionales no solicitados (p. ej., no se evaluarán ejecutables extra, documentación no requerida ni imágenes sueltas fuera del PDF).

Antes de entregar, verifica que el ZIP contiene todos los materiales solicitados y que estos cumplen con el formato requerido: el proyecto completo, el PDF con [CS01–CS07], [TX01-TX02] correctamente insertados, y el archivo .AAB firmado. Exporta y nombra el PDF de capturas siguiendo el patrón indicado y comprime todos los elementos en un único archivo ZIP con el mismo nombre. Solo se corregirá lo incluido dentro de este ZIP.

El documento PDF de capturas entregado tendrá el siguiente nombre:
M07-0488-UF1-PR01-“username”.pdf
“username” = nombre de usuario del alumno en la plataforma
Ejemplo: M07-0488-UF1-PR01-marialigarcia.pdf




El archivo deberá comprimirse en un ZIP con el mismo patrón:
M07-0488-UF1-PR01-“username”.zip
“username” = nombre de usuario del alumno en la plataforma
Ejemplo: M07-0488-UF1-PR01-marialigarcia.zip

Tiempo estimado para la realización de esta práctica: 20-25 horas.
Puntuación: 10 puntos.


Ejercicio #1 SpaceApps - Diseño y planificación
La empresa ficticia SpaceApps está diseñando una nueva aplicación móvil que mostrará información sobre los cohetes de SpaceX.
Antes de comenzar el desarrollo, el equipo necesita definir la estructura del proyecto y la organización interna del código.
Como desarrollador principal, tu primera misión es plantear la arquitectura general de la aplicación y reflexionar sobre cómo organizarías las capas para que el proyecto sea limpio, mantenible y escalable.


Ejercicio #1 SpaceApps – Tarea #1 Diseño conceptual de la aplicación
Antes de empezar a desarrollar la aplicación, redacta un breve texto (entre 150 y 200 palabras) donde expliques cómo estructurarías tu aplicación de cohetes para mantener una arquitectura limpia y fácil de mantener.
Incluye al menos:
Qué capas tendría tu aplicación (por ejemplo, UI, dominio, datos).
Qué responsabilidades tendría cada una.
Cómo se comunicarían entre sí.
Qué ventajas ves en separar las capas.
El objetivo de esta tarea es reflexionar sobre la organización del código antes de implementar la app. No se evaluará la terminología exacta, sino la claridad de ideas y la capacidad para razonar sobre una arquitectura bien estructurada.

Entregables:
[TX02] Texto breve (150–200 palabras) con la descripción solicitada.

Puntuación: 1,5 punto
Tiempo estimado: 2 horas


Ejercicio #2 SpaceApps – Desarrollo de la aplicación SpaceApps

Una vez definido el diseño conceptual, implementa la aplicación siguiendo los requisitos funcionales descritos a continuación. La aplicación deberá desarrollarse conforme a esos requisitos y estándares definidos: la app deberá listar un catálogo de cohetes con su información básica, permitir ver el detalle de cada cohete y gestionar adecuadamente la navegación y los estados de la interfaz (carga, vacío, error), todo ello siguiendo buenas prácticas de arquitectura, accesibilidad y calidad de código. Se valorará no solo que la funcionalidad requerida esté completa, sino también la corrección técnica de la solución: uso de Jetpack Compose para la interfaz de usuario, integración de datos con Retrofit (llamada a API web) y Room (almacenamiento local offline), patrón de arquitectura (p. ej. MVVM) para una separación clara de capas, implementación de accesibilidad en la UI, inclusión de pruebas automatizadas y cumplimiento de los criterios de calidad y entrega indicados. A continuación se detallan las tareas a realizar.


Ejercicio #2 - Tarea #1 Pantalla Splash y Login
Implementa la pantalla inicial de Splash y la pantalla de Login utilizando Jetpack Compose. La pantalla de Splash debe mostrarse brevemente al iniciar la aplicación, mostrando un texto con el título “SpaceApps”, y tras dos segundos debe navegar automáticamente a la pantalla de Login. De forma opcional, puedes reemplazar el texto por un logo ficticio si lo prefieres.
En la pantalla de Login, crea un formulario con los campos de email y contraseña, un botón con el texto “He olvidado mis datos de acceso” que abra la URL https://lasallefp.com/contactar/, y un botón para iniciar sesión.
Valida los campos del formulario de manera que el email tenga un formato válido y la contraseña no pueda estar vacía (puedes añadir un requisito mínimo de longitud si lo consideras necesario). Si el usuario intenta iniciar sesión con datos inválidos, muestra mensajes de error debajo de los campos correspondientes. Si los datos son válidos pero las credenciales son incorrectas, muestra un mensaje de error mediante una Snackbar y permite que el usuario vuelva a intentarlo.
Para simplificar la autenticación, utiliza credenciales ficticias: el email debe ser admin@lasalle.es y la contraseña admin1234. Cuando el usuario inicia sesión correctamente, la aplicación debe navegar a la pantalla principal (lista de cohetes).
Asegúrate de gestionar correctamente el flujo de navegación para que, una vez que el usuario ha iniciado sesión con éxito, no pueda volver atrás al formulario de login(es decir, limpia o controla el backstack adecuadamente).
Entregables: 
[CS01] Pantalla de Splash. Captura de la pantalla inicial de Splash, mostrando el logo de SpaceApps centrado o destacado. Debe aparecer al iniciar la aplicación.
[CS02] Pantalla de Login. Captura de la pantalla de Login, mostrando los campos Email y Contraseña, el botón “He olvidado mis datos de acceso” y el botón “Iniciar sesión”, correctamente alineados y visibles.
Puntuación: 1,5 puntos.
Tiempo estimado: 3 horas.

Ejercicio #2 - Tarea #2 Listado de cohetes y filtrado
Desarrolla la pantalla principal de la aplicación como una lista scrollable de cohetes. Al abrirla, una vez el usuario se ha autenticado, la aplicación debe obtener los datos desde la API pública de SpaceX usando Retrofit, empleando obligatoriamente el endpoint real https://api.spacexdata.com/v4/rockets (no requiere autenticación; ver campos de referencia en https://github.com/r-spacex/SpaceX-API/blob/master/docs/rockets/v4/all.md). 
Integra Room para guardar en local los cohetes obtenidos y permitir que la lista se muestre también sin conexión al reabrir la aplicación y autenticarnos. En la interfaz, cada ítem debe mostrar al menos el nombre del cohete y una miniatura (usa la primera imagen disponible o una imagen predeterminada). Añade controles de filtrado y búsqueda: un switch o checkbox “Mostrar solo activos” y un campo de búsqueda por nombre con filtrado en tiempo real; ambos deben poder combinarse (por ejemplo, buscar dentro de los activos). 
Gestiona de forma reactiva los estados de la pantalla: muestra un indicador de carga mientras se obtienen los datos, un estado vacío con mensaje tipo “Sin resultados” cuando la lista quede vacía (por filtros o búsquedas), y un estado de error con mensaje y acción para reintentar (por ejemplo, botón “Reintentar”) cuando falle la carga (p. ej., por red).
Además, implementa una opción de Cerrar sesión (Logout) accesible desde la pantalla principal, que permita al usuario salir de su cuenta y volver a la pantalla de inicio de sesión. Recuerda, el usuario no debe poder volver atrás una vez hecho Logout, similar a lo que pasaba al hacer login.

Entregables:
[CS03] Pantalla principal — Lista de cohetes. Captura de la pantalla principal mostrando la lista de cohetes, con el campo de búsqueda, el switch o checkbox “Mostrar solo activos” visibles en la parte superior y el botón de cerrar sesión.
Puntuación: 4 puntos.
Tiempo estimado: 8 horas.

Ejercicio #2 - Tarea #3 Detalle de cohete y enlace a Wikipedia
Implementa la pantalla de detalle de cohete accesible al pulsar un elemento de la lista (Tarea #2), navegando a una vista nueva que muestre una imagen grande (principal o predeterminada), el nombre completo y un mínimo de cinco datos relevantes del cohete según la API (por ejemplo, país de origen, primera fecha de lanzamiento, descripción, porcentaje de éxito, etc.). 
Cuida la disposición para pantallas pequeñas y permite scroll para textos largos como la descripción. Añade un botón que abra más información en la web mediante un intent implícito: al activarlo, la app debe lanzar el navegador externo con la URL de Wikipedia del cohete (úsala si viene en la API; si no, el botón deberá quedar “no visible”). Asegúrate de que la acción abre correctamente el navegador fuera de la app y muestra la página solicitada.

Entregables:
[CS04] Pantalla de Detalle de cohete. Captura de la pantalla de Detalle mostrando: imagen grande (principal o placeholder), nombre completo del cohete y ≥5 datos relevantes (p. ej., país de origen, primera fecha de lanzamiento, porcentaje de éxito, etapas, coste por lanzamiento). Debe verse la descripción total o parcialmente (con posibilidad de scroll en pantallas pequeñas). Si existe URL de Wikipedia en la API, el botón “Más info (Wikipedia)” debe ser visible; si no existe, el botón no debe mostrarse.
Puntuación: 2,0 puntos.
Tiempo estimado: 4 horas.


Ejercicio #2 – Tarea #4 Accesibilidad y manejo de estados
Revisa la aplicación para asegurar buenas prácticas de accesibilidad y una comunicación clara de estados al usuario: verifica que todos los elementos informativos tengan descripciones de texto adecuadas para lectores de pantalla (por ejemplo, usa contentDescription en Compose para imágenes relevantes con descripciones breves y significativas como “Fotografía del cohete Falcon 9”, nunca nombres de archivo), y comprueba que los mensajes de estado o error (p. ej., “Sin resultados” o fallos de red) sean comprensibles y anunciables por tecnologías asistivas. 
En los estados de carga, vacío y error de la Tarea #2, garantiza una representación visual clara y coherente con la app: el indicador de progreso debe ser perceptible; el mensaje de “Sin resultados” debe estar destacado con estilo sobrio, idealmente con un indicativo gráfico si procede; y los errores deben explicar qué ocurrió (por ejemplo, “Error al cargar. Por favor, verifica tu conexión”) e incluir una acción de reintento. 
Mantén posiciones consistentes (por ejemplo, centrar el texto de “Sin resultados”), estilos alineados con el resto de la UI y verifica que estos mensajes y componentes sean accesibles y se anuncien correctamente cuando aparezcan.

Entregables:
[CS05] Lista — Estado de carga. Captura con el indicador de progreso visible y perceptible (p. ej., Loading Indicator) en posición coherente con el diseño (centrado o en zona prevista).
[CS06] Lista — Estado vacío. Captura del mensaje “Sin resultados” claramente destacado y centrado, con estilo sobrio y, si aplica, indicativo gráfico (icono) alineado con la UI.
[CS07] Lista — Estado de error. Captura del mensaje de error comprensible (p. ej., “Error al cargar. Verifica tu conexión”) con el botón “Reintentar” visible y accesible.

Puntuación: 1,0 puntos.
Tiempo estimado: 3 horas.
